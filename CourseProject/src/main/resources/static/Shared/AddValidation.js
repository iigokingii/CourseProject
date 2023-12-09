var errBlock = document.getElementById('ErrorMsg');
function ValidateFilmData(posterInputBlob){
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
    if(CheckEmptyValue(TITLE,"Title")&&CheckEmptyValue(ORIGINAL_TITLE,"original title")&&compareDate(YEAR_OF_POSTING)&&CheckEmptyValue(COUNTRY,"country")&&
    CheckNumberValue(RATING_IMDb,"Rating IMdB")&&CheckNumberValue(RATING_KP,"Rating KP")&&CheckNumberValue(BOX_OFFICE_RECEIPTS,"Box office receipts")&&
    CheckNumberValue(BUDGET,"Budget")&&CheckAgeValue(AGE,"Age")&&CheckTime(VIEWING_TIME,"Viewving time")&&CheckArray(GenresArray,"genres")&&
    CheckArray(DirectorsArray,"directors")&&CheckArray(ActorsArray,"actors")&&CheckArray(FactsArray,"facts")&&CheckEmptyValue(Description,"Desciption")&&CheckPoster(posterInputBlob))
        return true;
    return false;
}
function CheckPoster(posterBlob){
    if(posterBlob!==undefined){
        ClearError();
        return true;
    }
    else{
        HandleError(`Add poster of a movie.`)
        return false;
    }
}

function CheckArray(array,name){
    if (array.length>=1){
        for(let j =0;j<array.length;j++){
            if(!CheckEmptyValue(array[j],name)){
                return false;
            }
        }
        ClearError();
        return true;
    }
    else {
        HandleError(`Input at least one value of ${name}.`)
        return false;
    }
}

function CheckTime(data,name){
    if (data !== ''){
        if(checkTimeFormat(data)){
            ClearError();
            return true;
        }
        else{
            HandleError(`${name} should be in format: H:MM`);
            return false;
        }
    }
    else {
        HandleError(`${name} field is empty`)
        return false;
    }
}
function checkTimeFormat(inputValue) {
    var regex = /^([0-9]|1[0-9]|2[0-3]):[0-5][0-9]$/;
    return regex.test(inputValue);
}

function compareDate(yearOfPosting) {
    if(yearOfPosting!==''){
        let currentDate = new Date().toISOString().split("T")[0];
        if(currentDate>=yearOfPosting){
            ClearError();
            return true;
        }
        HandleError('Error in date of release')
        return false;
    }
    else{
        HandleError(`date of release should have value`)
        return false;
    }
    
}
function CheckEmptyValue(data,name){
    if (data !== ''){
        ClearError();
        return true;
    }
    else {
        HandleError(`${name} field is empty`)
        return false;
    }
}
function CheckNumberValue(data,name){
    if (data !== '')
        if (!isNaN(parseFloat(data)) && isFinite(data)) {
            ClearError();
            return true;
        }
        else{
            HandleError(`${name} field not a number`)
            return false;
        }
    else {
        HandleError(`${name} field is empty`)
        return false;
    }
}
function CheckAgeValue(data,name){
    if (data !== '')
        if (!isNaN(parseFloat(data)) && isFinite(data)) {
            let age = parseFloat(data);
            if(age>100||age<0){
                HandleError(`${name} should be in range (0,100)`)
                return false;
            }
            else{
                ClearError();
                return true;
            }
        }
        else{
            HandleError(`${name} field not a number`)
            return false;
        }
    else {
        HandleError(`${name} field is empty`)
        return false;
    }
}


function HandleError(msg){
    errBlock.innerHTML = msg;
}
function ClearError(){
    errBlock.innerHTML='';
}
