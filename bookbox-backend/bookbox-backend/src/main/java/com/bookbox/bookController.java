package com.bookbox;
import com.fasterxml.jackson.databind.ObjectMapMapper;//Convertimos objetos Java a JSON y viceversa 
import javax.servlet.ServletException;//manejo de excpeciones de servlets 
import javax.annotation.WebServlet;//Mapea un servlet a una direccion URL especifica 
import javax.servlet.http.HttpServlet;//extendemos esta clase para manejar peticiones HTTP 
import javax.servlet.http.HttpServletRequest;//manejamos solicitudes HTTP 
import javax.servlet.http.HttpServletResònse;//manejamos respuestas HTTP 
import java.io.IOEXception; //Importamos para manejar excepciones de entrada /salida 
import java.io.IOException;
import java.sql.*;//Importamos toda la JDBC para manjear la base de datos 
import java.util.ArrayList;//Lista dinamica DE OBJETOS 
import java.util.List; 


@WebServlet("/books")//Esta anotacion mapea la url a "/books" 
public class bookController extends HttpServlet {

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    response.setHeader("Access-Control-Allow-Origin", "*");//Permitir acceso desde cualquier origen 
    response.setHeader("Access-Control-Allow-Methods", "*");//Métodos permitidos 
    response.setHeader("Access-Control-Allow-Headers", "Content-Type");//cabeceras permitidas  

    ConnectionToDB connectionDB = new ConnectionToDB();//Creamos una nueva conexión a la base de datos (acá se llama al constructor)
    Connection cn = connectionDB.getConnection();  

    try{
        ObjectMapper mapper = new ObjectMapper();// Creamos un objeto ObjectMapper para converetir JSON a objetos Java 
        Book book = mapper.readValue(request.getInputStram(), Book.class);// Convertimos el JSON de la solicitud a un objeto BOOK 


        //Consulta SQL para insertar un nuevo libro en la tablas 'books' (este es sólo un ejemplo)
        //                                      1       2       3      4      5
        String queryInsert = "INSERT INTO books (title, author, pages, about, genere, image ) VALUES (?, ?, ?, ?, ?, ?)"; 
        PreparedStatement statement = cn.prepareStatement(queryInsert, Statement.RETURN_GENERATED_KEYS); //indicamos que queremos obtener las keys generadas 

        //Establecemos los parametros de la consulta de insercción: 
        statement.setString(1, book.getTitle()); 
        statement.setString(2, book.getAuthor()); 
        statement.setInt(3, book.getPages()); 
        statement.setString(4, book.getGenre()); 
        statement.setString(5, book.getAbout()); 
        statement.setString(6, book.getImage()); 
        
        statement.executeUpdate();  //Ejecutamos la consulta de insercción en la base de datos 

        //obtenemos las claves generadas (en este caso el ID) 
        ResultSet rs = statement.getGeneratedKeys(); 
        if(rs.next()){
            Long idBook = rs.getLong(1);//obtenemos el valor del primer campo generado automaticamente 

            //Devolvemos el ID de la pelicula insertada como JSON en la respuesta 
            response.setContentType("application/json"); 
            String json = mapper.writeValuesAsString(idBook); 
            response.getWriter().write(json); //Escribimos el JSON en el cuerpo de la respuesta HTTP
        } 

        response.setStatus(HttpServletResponse.SC_CREATED); // configuramos el código de estado a 201 

    }catch(SQLExceotion e){
        e.printStackTrace();
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }catch(IOException e){
        e.printStackTrace();
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }finally{
        connectionDB.close();
    }
    
} 

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      //configuramos las cabeceras CORS 
      response.setHeader("Access-Control-Allow-Origin", "*");//Permitir acceso desde cualquier origen 
      response.setHeader("Access-Control-Allow-Methods", "*");//Métodos permitidos 
      response.setHeader("Access-Control-Allow-Headers", "Content-Type");//cabeceras permitidas 
  
      ConnectionToDB connectionDB = new ConnectionToDB();//Creamos una nueva conexión a la base de datos (acá se llama al constructor)
      Connection cn = connectionDB.getConnection(); 
  
      try{
          String querySelect = "SELECT * FROM books"; 
          Statement statement = cn.createStatement(); 
          ResultSet resultSet = statement.executeQuery(querySelect); 
  
          List<Book> books = new ArrayList<>();//creamos una lista para almacenar los libros 
  
          while(resultSet.next()){
              //Creamos un libro con los datos de cada Fila 
              Book currentBook = new Book(
                  resultSet.getInt("book_id"), 
                  resultSet.getString("title"),
                  resultSet.getString("author"),
                  resultSet.getInt("pages"),
                  resultSet.getString("genre"),
                  resultSet.getString("about"), 
                  resultSet.getString("image")
              ); 
  
              books.add(currentBook);
          } 
  
          ObjectMapper mapper = new ObjectMapper(); //creamos un objeto mapper 
          String json = mapper.writeValueAsString(books);//convertimos la lista en un arreglo JSON 
  
          response.setContentType("application/json");//Establecemos el tipo de contenido de la respuesta como JSON 
          response.getWriter.write(json);//Y ahora escribimos el JSON en el cuerpo de la respuesta HTTP 
  
  
  
      }catch(SQLException e){
          e.printStackStrace(); 
          response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      }finally{ 
  
        connectionDB.close();
      }
  
 
}
}//class