let butttonShowModal = document.getElementById("add-book"); 
let modal = document.getElementById("modal"); 

butttonShowModal.addEventListener('click', ()=>{
    console.log("show modal"); 
    modal.classList.remove("isHide"); 


    document.getElementById("send-book").addEventListener('click', async(e)=>{
        e.preventDefault();
        
        let title = document.getElementById("title").value;
        let author = document.getElementById("author").value;
        let genre = document.getElementById("genre").value;
        let about = document.getElementById("about").value;
        let pages = document.getElementById("pages").value;
        
        
        
        const book = {
            title, 
            author, 
            genre, 
            about,
            pages 
        }

        console.log(JSON.stringify(book));

        try {
            let response = await fetch('http://localhost:8080/bookbox/books', {
                method: "POST",
                body: JSON.stringify(book),
                headers: {"Content-type": "application/json; charset=UTF-8"}
            });

            if (response.ok) {
                console.log("Book added successfully");
                document.getElementById("post-book").reset(); 
                modal.classList.add("isHide");
                location.reload();
                
            } else {
                console.error("Failed to add book");
            }
        } catch (error) {
            console.error("Error:", error);
        }


    });





});
