document.addEventListener('DOMContentLoaded', async()=>{
    const options = {
        method: 'GET', 
        headers: {
            'Content-Type':'application/json', 
        }
    } 

    const response = await fetch('http://localhost:8080/bookbox/books', {mode:'cors'}); 
    const books = await response.json();

    //#list-cards-books
   books.forEach(book => {
    const listCardBooks = document.getElementById("list-cards-books");
    
    let cardDiv = document.createElement('div');
    cardDiv.classList.add("card"); 
    cardDiv.setAttribute("id", `${book.book_id}`);
    let innerImg = document.createElement("div");
    innerImg.classList.add("img"); 
    let imgBookcase = document.createElement("img"); 
    imgBookcase.setAttribute("id", "img-bookcase");
    imgBookcase.setAttribute("src", `https://picsum.photos/200/200`)
    innerImg.appendChild(imgBookcase); 


    let innerInfo = document.createElement("div"); 
    innerInfo.classList.add("info"); 
    let title = document.createElement("h3");
    title.innerHTML = `${book.title}`;
    let ulInfo = document.createElement("ul"); 
    let liAuthor = document.createElement("li");
    liAuthor.innerText = `${book.author}`;
    let liGenero = document.createElement("li");
    liGenero.innerText = `${book.genre}`;
    let liPages = document.createElement("li");
    liPages.innerText = `${book.pages}` 
    ulInfo.appendChild(liAuthor);
    ulInfo.appendChild(liGenero);
    ulInfo.appendChild(liPages); 

    innerInfo.appendChild(title);
    innerInfo.appendChild(ulInfo);





    let innerButtons = document.createElement("div"); 
    innerButtons.classList.add("handle-card");
    let buttonDelete = document.createElement("button");
    buttonDelete.setAttribute("id", "delete-card")
    let buttonEdit = document.createElement("button");
    buttonEdit.setAttribute("id", "edit-card"); 

    innerButtons.appendChild(buttonDelete);
    innerButtons.appendChild(buttonEdit);
  
    cardDiv.appendChild(innerImg);
    cardDiv.appendChild(innerInfo);
    cardDiv.appendChild(innerButtons); 

    listCardBooks.appendChild(cardDiv);
   });
})