var films =[];
async function GetFilms(){
    const response = await fetch('/getFilms',{
        method:"GET",
        headers:{
            "Content-type":"application/json",
            "Accept":"application/json"
        }
    })
    if(response.ok){
        let respJson = await response.json();
        films = respJson;
        console.log(respJson);
        DisplayAllFilms(films)
    }
    else{
        console.log('error')
    }
}
function DisplayAllFilms(Films){
    let filmsDiv = document.getElementById("films");
    Films.forEach(film=>{
        let hr = document.createElement('hr');
        hr.id = `hr-${film.all_INFORMATION_ABOUT_FILM_ID}`
        filmsDiv.append(hr);
        filmsDiv.append(CreateFilmView(film));
    });
}


function CreateFilmView(film){
    let divFilm = document.createElement('div');
    divFilm.addEventListener('click',()=>{
        // console.log(film.all_INFORMATION_ABOUT_FILM_ID);
        window.location.href = `/UserFilmView?filmID=${film.all_INFORMATION_ABOUT_FILM_ID}`;
    })
    divFilm.id = `film-${film.all_INFORMATION_ABOUT_FILM_ID}`
    divFilm.classList.add('film');
        let divLeft = document.createElement('div');
        divLeft.classList.add('film-id');
            let FilmIdH3 = document.createElement('h3');
            FilmIdH3.id = "film-id";
            FilmIdH3.innerHTML = film.all_INFORMATION_ABOUT_FILM_ID;
        divLeft.append(FilmIdH3);
    divFilm.append(divLeft);
        let posterDiv = document.createElement('div');
        posterDiv.classList.add('poster');
        posterDiv.id = `poster-film-${film.all_INFORMATION_ABOUT_FILM_ID}`;
        BlobToImage(film.poster,posterDiv);
    divFilm.append(posterDiv);
        let briefDiv = document.createElement('div');
        briefDiv.classList.add('briefInformation');
            let h4Tittle = document.createElement('h4');
            h4Tittle.classList.add('title')
            h4Tittle.innerHTML = film.title;
        briefDiv.append(h4Tittle);
            let divRow2 = document.createElement('div');
            divRow2.classList.add('Row2');
                let pOriginalName = document.createElement('p');
                pOriginalName.id = "OriginalName";
                pOriginalName.innerHTML = `${film.original_TITLE},`;
            divRow2.append(pOriginalName);
                let pDate = document.createElement('p');
                pDate.id = "Date";
                pDate.innerHTML = `${film.year_OF_POSTING},`;
            divRow2.append(pDate);
                let pViewingTime = document.createElement('p');
                pViewingTime.id = "ViewingTime";
                pViewingTime.innerHTML = `${film.viewing_TIME} мин.`
            divRow2.append(pViewingTime);
        briefDiv.append(divRow2);
            let divRow3 = document.createElement('div');
            divRow3.classList.add('Row3');
                let pCountry = document.createElement('p');
                pCountry.id = "Country";
                pCountry.innerHTML = `${film.country} • `;
            divRow3.append(pCountry);
                let pFilmGenreContainer = document.createElement('p');
                pFilmGenreContainer.id = "FilmGenre";
                for(let i =0 ; i<film.genres.length;i++){
                    if(i===1){
                        let label = document.createElement('label')
                        label.id = film.genres[i].genreId;
                        label.innerHTML = `${film.genres[i].genreName} `;
                        pFilmGenreContainer.append(label);
                        break;
                    }
                    let label = document.createElement('label')
                    label.id = film.genres[i].genreId;
                    label.innerHTML = `${film.genres[i].genreName}, `;
                    pFilmGenreContainer.append(label);
                }
            divRow3.append(pFilmGenreContainer);
                let pDirector = document.createElement('p');
                pDirector.id = "Director";
                pDirector.innerHTML = `Режиссер: ${film.directors[0].directorName}`
                pDirector.style = "margin-left:4px";
            divRow3.append(pDirector);
        briefDiv.append(divRow3);
            let divRow4 = document.createElement('div');
            divRow4.classList.add('Row4');
                let pActors = document.createElement('p');
                pActors.id = "Actors";
                let textHtml = 'В ролях: ';
                for(let i = 0;i<film.actors.length;i++){
                    if(i===1){
                        textHtml += `, ${film.actors[i].actorName}`;
                        break;
                    }
                    textHtml += `${film.actors[i].actorName}`;
                }
                pActors.innerHTML = textHtml;
            divRow4.append(pActors);
        briefDiv.append(divRow4);
    divFilm.append(briefDiv);
        let divOther = document.createElement('div');
        divOther.classList.add("otherActions");
            let divRating = document.createElement('div');
            divRating.classList.add("rating");
                let divKp = document.createElement('div');
                divKp.classList.add("KP");
                    let LabelEditor = document.createElement('label');
                    LabelEditor.classList.add("editorial");
                    LabelEditor.innerHTML = "KP:";
                divKp.append(LabelEditor);
                    let LabelMark = document.createElement('label');
                    LabelMark.classList.add("mark");
                    LabelMark.innerHTML = film.rating_KP;
                divKp.append(LabelMark);
            divRating.append(divKp);
                let divIMdB = document.createElement('div');
                divIMdB.classList.add("IMdB");
                    let LabelEditor1 = document.createElement('label');
                    LabelEditor1.classList.add("editorial");
                    LabelEditor1.innerHTML = "imdb:";
                divIMdB.append(LabelEditor1);
                    let LabelMark1 = document.createElement('label');
                    LabelMark1.classList.add("mark");
                    LabelMark1.innerHTML = film.rating_IMDb;
                divIMdB.append(LabelMark1);
            divRating.append(divIMdB);
        divOther.append(divRating);
    divFilm.append(divOther);
    return divFilm;
}

document.getElementById("Find").addEventListener('click',()=>{
    let name = document.getElementById("nameOfFilm").value;
    let ReqName = name.trim();
    ReqName = ReqName.trimEnd();
    ReqName = ReqName.toLowerCase();
    const findFilm = films.find(film=>film.title.toLowerCase() === ReqName || film.original_TITLE.toLowerCase() === ReqName);
    if(findFilm !== undefined){
        deleteFilmsFromDiv();

        let filmsDiv = document.getElementById('films');
        let hr = document.createElement('hr');
        hr.id = `hr-${findFilm.all_INFORMATION_ABOUT_FILM_ID}`
        filmsDiv.append(hr);
        filmsDiv.append(CreateFilmView(findFilm));
    }
    else{
        deleteFilmsFromDiv();
        DisplayAllFilms(films);
    }
})

function deleteFilmsFromDiv(){
    let filmsDiv = document.getElementById('films');
    let films = filmsDiv.querySelectorAll('div');
    let hrs = filmsDiv.querySelectorAll('hr');
    films.forEach(div=>{
        div.remove();
    })
    hrs.forEach(ht=>{
        ht.remove();
    })
}
let click1 = 1;
document.getElementById("alphabet").addEventListener('click',()=>{
    const sortedItems = [...films];
    if(click1%2==0){
        sortedItems.sort((a, b) => {
            if (a.title < b.title) {
            return -1; // a должен быть перед b
            } else if (a.title > b.title) {
            return 1; // a должен быть после b
            } else {
            return 0; // a и b равны
            }
        });
        click1++;
    }
    else{
        sortedItems.sort((a, b) => {
            if (a.title < b.title) {
            return 1; // a должен быть перед b
            } else if (a.title > b.title) {
            return -1; // a должен быть после b
            } else {
            return 0; // a и b равны
            }
        });
        click1--;
    }
    deleteFilmsFromDiv()
    DisplayAllFilms(sortedItems);
    
})
let click2 = 1;
document.getElementById("Genre").addEventListener('click',()=>{
    const sortedItems = [...films];
    if(click2%2==0){
        sortedItems.sort((a, b) => {
            if (a.genres[0].genreName < b.genres[0].genreName) {
            return -1; // a должен быть перед b
            } else if (a.genres[0].genreName > b.genres[0].genreName) {
            return 1; // a должен быть после b
            } else {
            return 0; // a и b равны
            }
        });
        click2++;
    }
    else{
        sortedItems.sort((a, b) => {
            if (a.genres[0].genreName < b.genres[0].genreName) {
            return 1; // a должен быть перед b
            } else if (a.genres[0].genreName > b.genres[0].genreName) {
            return -1; // a должен быть после b
            } else {
            return 0; // a и b равны
            }
        });
        click2--;
    }
    deleteFilmsFromDiv()
    DisplayAllFilms(sortedItems);
    
})

let click3 = 1;
document.getElementById("viewingTime").addEventListener('click',()=>{
    const sortedItems = [...films];
    if(click3%2==0){
        sortedItems.sort((a, b) => {
            if (a.viewing_TIME < b.viewing_TIME) {
            return -1; // a должен быть перед b
            } else if (a.viewing_TIME > b.viewing_TIME) {
            return 1; // a должен быть после b
            } else {
            return 0; // a и b равны
            }
        });
        click3++;
    }
    else{
        sortedItems.sort((a, b) => {
            if (a.viewing_TIME < b.viewing_TIME) {
            return 1; // a должен быть перед b
            } else if (a.viewing_TIME > b.viewing_TIME) {
            return -1; // a должен быть после b
            } else {
            return 0; // a и b равны
            }
        });
        click3--;
    }
    deleteFilmsFromDiv()
    DisplayAllFilms(sortedItems);
})

let click4 = 1;
document.getElementById("country").addEventListener('click',()=>{
    const sortedItems = [...films];
    if(click4%2==0){
        sortedItems.sort((a, b) => {
            if (a.country < b.country) {
            return -1; // a должен быть перед b
            } else if (a.country > b.country) {
            return 1; // a должен быть после b
            } else {
            return 0; // a и b равны
            }
        });
        click4++;
    }
    else{
        sortedItems.sort((a, b) => {
            if (a.country < b.country) {
            return 1; // a должен быть перед b
            } else if (a.country > b.country) {
            return -1; // a должен быть после b
            } else {
            return 0; // a и b равны
            }
        });
        click4--;
    }
    deleteFilmsFromDiv()
    DisplayAllFilms(sortedItems);
})



GetFilms();