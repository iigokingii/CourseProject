var filmId;
var filmBlob;
var comments =[];
document.addEventListener('DOMContentLoaded', async function() {
    var id = document.getElementById('filmID');
    filmId = id.value;
    if(filmId=='')
        return;
    const response = await fetch(`/GetFilmAllInfo?filmID=${filmId}`,{
        method:"POST",
        headers: {
            "Content-type": "application/json",
            "Accept": "application/json"
        },
    });
    if(response.ok){
        var responseJson = await response.json();
        comments = responseJson;
        FillDataToInputs(comments[0]);
    } 
    else{
        console.log('Error');
    }
});

document.getElementById("AddCommentButton").addEventListener('click',async ()=>{
    let comment = document.getElementById('comment').value;
    let filmId = document.getElementById('filmID').value;
    let dateIso = new Date().toISOString();
    var commentObj = {
        "filmId":filmId,
        "comment":comment,
        "userId":'',
        "date":dateIso
    }
    if(comment!==''){
        const response = await fetch('/AddCommentToFilm',{
            method:"POST",
            headers:{
                "Content-type": "application/json",
                "Accept": "application/json"
            },
            body:JSON.stringify(
                commentObj
            )
        });
        if(response.ok){
            console.log('Comment Added');
            document.getElementById('comment').innerHTML = "";
            window.location.reload();
        }
        else{
            console.log('error');
        }
    }
    else
        alert(`comment's length should be in range (1,2000)`);
})

document.getElementById('AddToSaved').addEventListener('click',async ()=>{
    let filmId = document.getElementById('filmID').value;
    let request = {
        "filmId":filmId,
        "userId":'',
    }
    const response = await fetch('/AddToSaved',{
        method:"POST",
            headers:{
                "Content-type": "application/json",
                "Accept": "application/json"
            },
            body:JSON.stringify(
                request
            )
    })
    if(response.ok){
        alert('film saved to watch later category')
    }
    else{
        console.log('error');
    }
})

document.getElementById('AddToLiked').addEventListener('click',async ()=>{
    let filmId = document.getElementById('filmID').value;
    let request = {
        "filmId":filmId,
        "userId":'',
    }
    const response = await fetch('/AddToLiked',{
        method:"POST",
            headers:{
                "Content-type": "application/json",
                "Accept": "application/json"
            },
            body:JSON.stringify(
                request
            )
    })
    if(response.ok){
        alert('film saved to liked category')
    }
    else{
        console.log('error');
    }
})


function AddNewDirectorInput(director = {}){
    let container = document.getElementById("Directors");
    const inputs = container.querySelectorAll('p');
    const count = inputs.length;
    let newInput = document.createElement("p");
    newInput.classList.add("generatedInput");
    if(director=== null){
        newInput.id=`Director${count}`;
        container.append(newInput);
    }
    else{
        if(count===1 && director.directorId===0){
            document.getElementById("Director0").innerHTML = director.directorName;
        }
        else{
            newInput.id=`Director${director.directorId}`;
            newInput.innerHTML = director.directorName;
            container.append(newInput);
        }
    }
}

function AddNewGenreInput(genre={}){
    let container = document.getElementById("Genres");
    const inputs = container.querySelectorAll('p');
    const count = inputs.length;
    let newInput = document.createElement("p");
    newInput.classList.add("generatedInput");
    if(genre=== null){
        newInput.id=`Genre${count}`;
        container.append(newInput);
    }
    else{
        if(count===1 && genre.genreId===0){
            document.getElementById("Genre0").innerHTML = genre.genreName;
        }
        else{
            newInput.id=`Genre${count}`;
            newInput.innerHTML = genre.genreName;
            container.append(newInput);
        }
    }
}
function AddNewActorInput(actor={}){
    let container = document.getElementById("Actors");
    const inputs = container.querySelectorAll('p');
    const count = inputs.length;
    let newInput = document.createElement("p");
    newInput.classList.add("generatedInput");
    if(actor=== null){
        newInput.id=`Actor${count}`;
        container.append(newInput);
    }
    else{
        if(count===1 && actor.actorId===0){
            document.getElementById("Actor0").innerHTML = actor.actorName;
        }
        else{
            newInput.id=`Actor${count}`;
            newInput.innerHTML = actor.actorName;
            container.append(newInput);
        }
    }
}

function AddNewFactInput(fact = {}){
    let container = document.getElementById("Facts");
    const inputs = container.querySelectorAll('p');
    const count = inputs.length;
    let newInput = document.createElement("p");
    newInput.classList.add("generatedInput");
    if(fact=== null){
        newInput.id=`Fact${count}`;
        container.append(newInput);
    }
    else{
        if(count===1 && fact.factId===0){
            document.getElementById("Fact0").innerHTML = fact.fact;
        }
        else{
            newInput.id=`Fact${count}`;
            newInput.innerHTML = fact.fact;
            container.append(newInput);
        }
    }
}

async function FillDataToInputs(film){
    const posterDivST = document.getElementById("poster").style;
    let imageUrl = await BlobToImageURL(film.poster);
    posterDivST.backgroundImage = 'url(' + imageUrl + ')';
    filmBlob= film.poster;

    document.getElementById("TITLE").innerHTML = film.title;
    document.getElementById("ORIGINAL_TITLE").innerHTML = film.original_TITLE;
    document.getElementById("YEAR_OF_POSTING").innerHTML = film.year_OF_POSTING;
    document.getElementById("COUNTRY").innerHTML = film.country;
    document.getElementById("RATING_IMDb").innerHTML = film.rating_IMDb;
    document.getElementById("RATING_KP").innerHTML = film.rating_KP;
    document.getElementById("BOX_OFFICE_RECEIPTS").innerHTML = `$ ${film.box_OFFICE_RECEIPTS}`;
    document.getElementById("BUDGET").innerHTML = `$ ${film.budget}`;
    document.getElementById("AGE").innerHTML =`${film.age}+`;
    document.getElementById("VIEWING_TIME").innerHTML = film.viewing_TIME;
    document.getElementById("Description").innerHTML = film.description;
    film.directors.forEach(director=>{
        AddNewDirectorInput(director);
    })
    film.genres.forEach(genre=>{
        AddNewGenreInput(genre);
    })
    film.interesting_FACT.forEach(fact=>{
        AddNewFactInput(fact);
    })
    film.actors.forEach(actor=>{
        AddNewActorInput(actor);
    })  
    if(film.date_OF_REVIEW!==null || date_OF_REVIEW!==''){
        comments.forEach(comment=>{
            genereteComment(comment.avatar,comment.login,comment.date_OF_REVIEW,comment.user_REVIEW_TEXT);
        })
    }   
}

async function genereteComment(avatar,login,reviewDate,commentText){
    let comments = document.getElementById('comments');
    let divs = comments.querySelectorAll('div');
    let count = divs.length;
    let divComment = document.createElement("div");
    divComment.classList.add('comment');
    divComment.id = `comment${count}`;
        let divHead = document.createElement("div");
        divHead.classList.add('headComment');
            let divCred = document.createElement("div");
            divCred.classList.add('userCredentials');
                let divAvatar = document.createElement('div');
                divAvatar.classList.add('avatar');
                let imageUrl = await BlobToImageURL(avatar);
                divAvatar.style.backgroundImage = 'url(' + imageUrl + ')';
            divCred.append(divAvatar);
                let labelLog = document.createElement('label');
                labelLog.innerHTML = login;
            divCred.append(labelLog);
        divHead.append(divCred);
            let divDate = document.createElement('div');
                let dateLabel = document.createElement('label');
                dateLabel.classList.add('date');
                dateLabel.innerHTML = reviewDate;
            divDate.append(dateLabel);
        divHead.append(divDate);
    divComment.append(divHead);
        let divCommentText = document.createElement('div');
        divCommentText.classList.add('CommentText');
            let ReviewText = document.createElement('p');
            ReviewText.innerHTML = commentText;
        divCommentText.append(ReviewText);
    divComment.append(divCommentText);
    comments.append(divComment);


}

