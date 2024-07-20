package com.bookbox;

import java.sql.Connection;
import java.sql.DriveManager; 
import java.sql.SQLExceotion; 

public class ConnectionToDB {
    private Connection connection;
    //constructor (cuando definimos una instancia, este se ejecuta, llamando a la conexión)
    public connectionToDB(){
        try{
            //1: Cargamos dinamicamente el driver de jdbc
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2: Establecemos la conexion con la base de datos 'BookBox' en el localhost 
            this.connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/bookbox-cac", 
                "root", //user
                ""//pass
            );
        }catch(ClassNotFoundExceotion e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    //método conectar GETTER: 
    public Connection getConnection(){
        return this.connection;
    }

    //Cerrar la conexion: 
    public void close(){
        try{
            if(connection != null && !connection.isClosed()){
                connection.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
