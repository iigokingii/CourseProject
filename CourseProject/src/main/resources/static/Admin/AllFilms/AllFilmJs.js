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
        let filmsDiv = document.getElementById("films");
        respJson.forEach(film=>{
            filmsDiv.append(CreateFilmView(film))
        });
    }
    else{
        console.log('error')
    }
}

function CreateFilmView(film){
    return "s";
}

GetFilms();