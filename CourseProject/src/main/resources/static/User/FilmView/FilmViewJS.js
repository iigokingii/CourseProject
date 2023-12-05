var filmId;
var filmBlob;
document.addEventListener('DOMContentLoaded', async function() {
    var id = document.getElementById('filmID');
    filmId = id.value;
    if(filmId=='')
        return;
    const response = await fetch(`/GetFilm?filmID=${filmId}`,{
        method:"GET",
        headers: {
            "Content-type": "application/json",
            "Accept": "application/json"
        },
    });
    if(response.ok){
        var responseJson = await response.json();
        FillDataToInputs(responseJson);
    } 
    else{
        console.log('Error');
    }
});


function AddNewDirectorInput(director = {}){
    let container = document.getElementById("Directors");
    const inputs = container.querySelectorAll('p');
    const count = inputs.length;
    let newInput = document.createElement("p");
    if(director=== null){
        newInput.id=`Director${count}`;
        newInput.classList.add("generatedInput");
        container.append(newInput);
    }
    else{
        if(count===1 && director.directorId===0){
            document.getElementById("Director0").innerHTML = director.directorName;
        }
        else{
            newInput.id=`Director${director.directorId}`;
            newInput.innerHTML = director.directorName;
            newInput.classList.add("generatedInput");
            container.append(newInput);
        }
    }
}

function AddNewGenreInput(genre={}){
    let container = document.getElementById("Genres");
    const inputs = container.querySelectorAll('p');
    const count = inputs.length;
    let newInput = document.createElement("p");
    if(genre=== null){
        newInput.id=`Genre${count}`;
        newInput.classList.add("generatedInput");
        container.append(newInput);
    }
    else{
        if(count===1 && genre.genreId===0){
            document.getElementById("Genre0").innerHTML = genre.genreName;
        }
        else{
            newInput.id=`Genre${count}`;
            newInput.innerHTML = genre.genreName;
            newInput.classList.add("generatedInput");
            container.append(newInput);
        }
    }
}
function AddNewActorInput(actor={}){
    let container = document.getElementById("Actors");
    const inputs = container.querySelectorAll('p');
    const count = inputs.length;
    let newInput = document.createElement("p");
    if(actor=== null){
        newInput.id=`Actor${count}`;
        newInput.classList.add("generatedInput");
        container.append(newInput);
    }
    else{
        if(count===1 && actor.actorId===0){
            document.getElementById("Actor0").innerHTML = actor.actorName;
        }
        else{
            newInput.id=`Actor${count}`;
            newInput.innerHTML = actor.actorName;
            newInput.classList.add("generatedInput");
            container.append(newInput);
        }
    }
}

function AddNewFactInput(fact = {}){
    let container = document.getElementById("Facts");
    const inputs = container.querySelectorAll('p');
    const count = inputs.length;
    let newInput = document.createElement("p");
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
    document.getElementById("BOX_OFFICE_RECEIPTS").innerHTML = film.box_OFFICE_RECEIPTS;
    document.getElementById("BUDGET").innerHTML = film.budget;
    document.getElementById("AGE").innerHTML = film.age;
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
}

document.getElementById("AddCommentButton").addEventListener('click',()=>{
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
        const response = fetch('/AddCommentToFilm',{
            method:"POST",
            body:JSON.stringify(
                commentObj
            )
        });
        if(response.ok){
            console.log('Comment Added');
        }
        else{
            console.log('error');
        }
    }
})