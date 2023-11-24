document.getElementById("addNewDirectorInput").addEventListener('click',(e)=>{
    e.preventDefault();
    let container = document.getElementById("Directors");
    const inputs = container.querySelectorAll('input');
    const count = inputs.length;
    let newInput = document.createElement("input");
    newInput.id=`Director${count}`;
    newInput.classList.add("generatedInput");
    container.append(newInput);
});

document.getElementById("addNewActorInput").addEventListener('click',(e)=>{
    e.preventDefault();
    let container = document.getElementById("Actors");
    const inputs = container.querySelectorAll('input');
    const count = inputs.length;
    let newInput = document.createElement("input");
    newInput.id=`Actor${count}`;
    newInput.classList.add("generatedInput");
    container.append(newInput);
});

document.getElementById("addNewFactInput").addEventListener('click',(e)=>{
    e.preventDefault();
    let container = document.getElementById("Facts");
    const inputs = container.querySelectorAll('textarea');
    const count = inputs.length;
    let newInput = document.createElement("textarea");
    newInput.id=`Fact${count}`;
    
    container.append(newInput);
});

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

document.getElementById("SendToServer").addEventListener('click',async (e)=>{
    e.preventDefault();
    let posterInput = document.getElementById("posterInput").files[0];
    let posterInputBlob = await fileToByteArray(posterInput);
    //let trailer = document.getElementById("trailerInput").files[0];
    let trailer = '';
    let TITLE = document.getElementById("TITLE").value;
    let ORIGINAL_TITLE = document.getElementById("ORIGINAL_TITLE").value;
    let YEAR_OF_POSTING = document.getElementById("YEAR_OF_POSTING").value;
    let COUNTRY = document.getElementById("COUNTRY").value;
    let RATING_IMDb = document.getElementById("RATING_IMDb").value;
    let RATING_KP = document.getElementById("RATING_KP").value;
    let BOX_OFFICE_RECEIPTS = document.getElementById("BOX_OFFICE_RECEIPTS").value;
    let BUDGET = document.getElementById("BUDGET").value;
    let AGE = document.getElementById("AGE").value;
    let VIEWING_TIME = document.getElementById("VIEWING_TIME").value;
    
    let Directors = document.getElementById("Directors");
    let DirectorsArray = ExtractTextArrayFromInputContainer(Directors);

    let Actors = document.getElementById("Actors");
    let ActorsArray = ExtractTextArrayFromInputContainer(Actors);

    let Facts= document.getElementById("Facts");
    let FactsArray = ExtractTextArrayFromTextareaContainer(Facts);
    
    let Description = document.getElementById("Description").value;



    if(true){
        let newFilm={
            "poster":posterInputBlob,
            "trailer":trailer,
            "movieTitle":TITLE,
            "movieOriginalTitle":ORIGINAL_TITLE,
            "yearOfPosting":YEAR_OF_POSTING,
            "country":COUNTRY,
            "ratingIMdB":RATING_IMDb,
            "ratingKP":RATING_KP,
            "boxOfficeReceipts":BOX_OFFICE_RECEIPTS,
            "budget":BUDGET,
            "age":AGE,
            "viewingTime":VIEWING_TIME,
            "directors":DirectorsArray,
            "actors":ActorsArray,
            "interestingFact":FactsArray,
            "description":Description
        }
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
            let respJson = await response.json();
            console.log(respJson);
        }
        else{
            let error = await response.json();
            console.log(error);
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