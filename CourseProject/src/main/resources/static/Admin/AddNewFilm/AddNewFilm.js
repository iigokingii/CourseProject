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
        let div = document.getElementById("SendToServer");
        let button = div.querySelector('button');
        button.innerHTML = "update";
        console.log(responseJson);
        FillDataToInputs(responseJson);
    } 
    else{
        console.log('Error');
    }
});

document.getElementById("addNewDirectorInput").addEventListener('click',(e)=>{
    e.preventDefault();
    AddNewDirectorInput();
});

function AddNewDirectorInput(director = {}){
    let container = document.getElementById("Directors");
    const inputs = container.querySelectorAll('input');
    const count = inputs.length;
    let newInput = document.createElement("input");
    if(Object.keys(director).length === 0){
        newInput.id=`Director${count}`;
        newInput.classList.add("generatedInput");
        container.append(newInput);
    }
    else{
        if(count===1 && director.directorId===0){
            document.getElementById("Director0").value = director.directorName;
        }
        else{
            newInput.id=`Director${director.directorId}`;
            newInput.value = director.directorName;
            newInput.classList.add("generatedInput");
            container.append(newInput);
        }
    }
}

document.getElementById("addNewGenreInput").addEventListener('click',(e)=>{
    e.preventDefault();
    AddNewGenreInput();
});

function AddNewGenreInput(genre={}){
    let container = document.getElementById("Genres");
    const inputs = container.querySelectorAll('input');
    const count = inputs.length;
    let newInput = document.createElement("input");
    if(Object.keys(genre).length === 0){
        newInput.id=`Genre${count}`;
        newInput.classList.add("generatedInput");
        container.append(newInput);
    }
    else{
        if(count===1 && genre.genreId===0){
            document.getElementById("Genre0").value = genre.genreName;
        }
        else{
            newInput.id=`Genre${count}`;
            newInput.value = genre.genreName;
            newInput.classList.add("generatedInput");
            container.append(newInput);
        }
    }
}


document.getElementById("addNewActorInput").addEventListener('click',(e)=>{
    e.preventDefault();
    AddNewActorInput();
});

function AddNewActorInput(actor={}){
    let container = document.getElementById("Actors");
    const inputs = container.querySelectorAll('input');
    const count = inputs.length;
    let newInput = document.createElement("input");
    if(Object.keys(actor).length === 0){
        newInput.id=`Actor${count}`;
        newInput.classList.add("generatedInput");
        container.append(newInput);
    }
    else{
        if(count===1 && actor.actorId===0){
            document.getElementById("Actor0").value = actor.actorName;
        }
        else{
            newInput.id=`Actor${count}`;
            newInput.value = actor.actorName;
            newInput.classList.add("generatedInput");
            container.append(newInput);
        }
    }
}

document.getElementById("addNewFactInput").addEventListener('click',(e)=>{
    e.preventDefault();
    let container = document.getElementById("Facts");
    const inputs = container.querySelectorAll('textarea');
    const count = inputs.length;
    let newInput = document.createElement("textarea");
    newInput.id=`Fact${count}`;
    container.append(newInput);
});

function AddNewFactInput(fact = {}){
    let container = document.getElementById("Facts");
    const inputs = container.querySelectorAll('textarea');
    const count = inputs.length;
    let newInput = document.createElement("textarea");
    if(Object.keys(fact).length === 0){
        newInput.id=`Fact${count}`;
        container.append(newInput);
    }
    else{
        if(count===1 && fact.factId===0){
            document.getElementById("Fact0").value = fact.fact;
        }
        else{
            newInput.id=`Fact${count}`;
            newInput.value = fact.fact;
            container.append(newInput);
        }
    }
}

document.getElementById("deleteNewDirectorInput").addEventListener('click',(e)=>{
    e.preventDefault();
    let container = document.getElementById("Directors");
    const inputs = container.querySelectorAll('input');
    const count = inputs.length;
    if(count!=1){
        let inputToDelete = document.getElementById(`Director${count-1}`);
        container.removeChild(inputToDelete);
    }
});

document.getElementById("deleteNewGenreInput").addEventListener('click',(e)=>{
    e.preventDefault();
    let container = document.getElementById("Genres");
    const inputs = container.querySelectorAll('input');
    const count = inputs.length;
    if(count!=1){
        let inputToDelete = document.getElementById(`Genre${count-1}`);
        container.removeChild(inputToDelete);
    }
});

document.getElementById("deleteNewActorInput").addEventListener('click',(e)=>{
    e.preventDefault();
    let container = document.getElementById("Actors");
    const inputs = container.querySelectorAll('input');
    const count = inputs.length;
    if(count!=1){
        let inputToDelete = document.getElementById(`Actor${count-1}`);
        container.removeChild(inputToDelete);
    }
});

document.getElementById("deleteNewFactInput").addEventListener('click',(e)=>{
    e.preventDefault();
    let container = document.getElementById("Facts");
    const inputs = container.querySelectorAll('textarea');
    const count = inputs.length;
    if(count!=1){
        let inputToDelete = document.getElementById(`Fact${count-1}`);
        container.removeChild(inputToDelete);
    }
});

document.getElementById("posterInput").addEventListener('change',(e)=>{
    var file = e.target.files[0];
    var reader = new FileReader();
    reader.onload = (e)=>{
        var imageUrl=e.target.result;
        var posterSt = document.getElementById("poster").style;
        posterSt.backgroundImage = 'url(' + imageUrl + ')'; 
    };
    reader.readAsDataURL(file);

})



document.getElementById("SendToServer").addEventListener('click',async (e)=>{
    e.preventDefault();
    let posterInput = document.getElementById("posterInput").files[0];
    let posterInputBlob;
    if(posterInput===undefined){
        // let poster = document.getElementById("poster");
        // var style = window.getComputedStyle(poster);
        // styleUrl = style.getPropertyValue('background-image');
        // var url = styleUrl.replace('url("', '').replace('")', '');
        // url = url.replace('blob:', '');
        // const response = await fetch(url);
        // posterInputBlob = await response.blob();
        // console.log(posterInputBlob);
        posterInputBlob = filmBlob;
    }
    else
        posterInputBlob = await fileToByteArray(posterInput);
    //let trailer = document.getElementById("trailerInput").files[0];
    let trailer = '';
    let TITLE = document.getElementById("TITLE").value;
    let ORIGINAL_TITLE = document.getElementById("ORIGINAL_TITLE").value;
    let YEAR_OF_POSTING = document.getElementById("YEAR_OF_POSTING").value.toString();
    let COUNTRY = document.getElementById("COUNTRY").value;
    let RATING_IMDb = document.getElementById("RATING_IMDb").value;
    let RATING_KP = document.getElementById("RATING_KP").value;
    let BOX_OFFICE_RECEIPTS = document.getElementById("BOX_OFFICE_RECEIPTS").value;
    let BUDGET = document.getElementById("BUDGET").value;
    let AGE = document.getElementById("AGE").value;
    let VIEWING_TIME = document.getElementById("VIEWING_TIME").value;
    
    let Genres = document.getElementById("Genres");
    let GenresArray = ExtractTextArrayFromInputContainer(Genres);

    let Directors = document.getElementById("Directors");
    let DirectorsArray = ExtractTextArrayFromInputContainer(Directors);

    let Actors = document.getElementById("Actors");
    let ActorsArray = ExtractTextArrayFromInputContainer(Actors);

    let Facts= document.getElementById("Facts");
    let FactsArray = ExtractTextArrayFromTextareaContainer(Facts);
    
    let Description = document.getElementById("Description").value;

    if(ValidateFilmData(posterInputBlob)){
        var VIEWING_TIME_IN_MIN = convertTimeToMinutes(VIEWING_TIME);
        let newFilm={
            "filmID":filmId ,
            "poster":posterInputBlob,
            "trailer":trailer,
            "title":TITLE,
            "originalTitle":ORIGINAL_TITLE,
            "yearOfPosting":YEAR_OF_POSTING,
            "country":COUNTRY,
            "ratingIMdB":RATING_IMDb,
            "ratingKP":RATING_KP,
            "boxOfficeReceipts":BOX_OFFICE_RECEIPTS,
            "budget":BUDGET,
            "age":AGE,
            "viewingTime":VIEWING_TIME_IN_MIN,
            "directors":DirectorsArray,
            "genres":GenresArray,
            "actors":ActorsArray,
            "interestingFact":FactsArray,
            "description":Description
        }
        if(filmId===''){
            let response = await fetch('/addNewFilm',{
                method:"POST",
                headers: {
                    "Content-type": "application/json",
                    "Accept": "application/json"
                },
                body: JSON.stringify(
                    newFilm
                )
            });
            if(response.ok){
                
                window.location.reload();
            }
            else{
                let error = await response.json();
                console.log(error);
            }
        }
        else{
            let response = await fetch('/UpdateFilm',{
                method:"PUT",
                headers: {
                    "Content-type": "application/json",
                    "Accept": "application/json"
                },
                body: JSON.stringify(
                    newFilm
                )
            });
            if(response.ok){
                // let respJson = await response.json();
                // console.log(respJson);
                window.location.href = '/AllFilms';
            }
            else{
                let error = await response.json();
                console.log(error);
            }
        }
    }

    
});

function ExtractTextArrayFromInputContainer(container){
    let inputs = container.querySelectorAll('input');
    let textArray = [];
    for(let i = 0; i<inputs.length;i++){
        textArray.push(inputs[i].value);
    }
    return textArray;
}

function ExtractTextArrayFromTextareaContainer(container){
    let textareas = container.querySelectorAll('textarea');
    let textArray = [];
    for(let i = 0; i<textareas.length;i++){
        textArray.push(textareas[i].value);
    }
    return textArray;
}

async function FillDataToInputs(film){
    const posterDivST = document.getElementById("poster").style;
    let imageUrl = await BlobToImageURL(film.poster);
    posterDivST.backgroundImage = 'url(' + imageUrl + ')';
    filmBlob= film.poster;

    document.getElementById("TITLE").value = film.title;
    document.getElementById("ORIGINAL_TITLE").value = film.original_TITLE;
    document.getElementById("YEAR_OF_POSTING").value = film.year_OF_POSTING;
    document.getElementById("COUNTRY").value = film.country;
    document.getElementById("RATING_IMDb").value = film.rating_IMDb;
    document.getElementById("RATING_KP").value = film.rating_KP;
    document.getElementById("BOX_OFFICE_RECEIPTS").value = film.box_OFFICE_RECEIPTS;
    document.getElementById("BUDGET").value = film.budget;
    document.getElementById("AGE").value = film.age;
    document.getElementById("VIEWING_TIME").value = film.viewing_TIME;
    document.getElementById("Description").value = film.description;
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

function convertTimeToMinutes(timeString) {
    const [hours, minutes] = timeString.split(':');
    const totalMinutes = parseInt(hours) * 60 + parseInt(minutes);
    return totalMinutes;
  }