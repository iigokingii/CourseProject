function ValidatePasswordMatch(password,repetedPassword){
    return password===repetedPassword;
};

function ValidateLogin(login){
    if (login !== '')
        return true;
    else {
        HandleError('Login field is empty')
        return false;
    }
};

function ValidateEmail(email){
    if(email===''){
        HandleError('Email field is empty');
        return false;
    }
    const pattern = /^[\w.-]+@[\w.-]+\.\w+$/;
    if(pattern.test(email))
        return true;
    else{
        HandleError('Email format is invalid. ')
        return false;
    }
};

function ValidateRepetedPassword(password){
    if(password===''){
        HandleError('Repeat password field is empty');
        return false;
    }
    /*
        (?=.*\d)          // should contain at least one digit
        (?=.*[a-z])       // should contain at least one lower case
        (?=.*[A-Z])       // should contain at least one upper case
        [a-zA-Z0-9]{8,}   // should contain at least 8 from the mentioned characters
        $
    */
    const pattern = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}$/;
    if(pattern.test(password))
        return true
    else{
        HandleError('Password format is invalid. It have to contain at least one digit, one upper case, one lower case and 8 symbols from mentioned characters');
        return false;
    }
};

function ValidatePassword(password){
    if(password===''){
        HandleError('Password field is empty');
        return false;
    }
    const pattern = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}$/;
    if(pattern.test(password))
        return true
    else{
        HandleError('Password format is invalid. It have to contain at least one digit, one upper case, one lower case and 8 symbols from mentioned characters');
        return false;
    }
};


function HandleError (ErrorMsg){
    document.getElementById('ErrorMsg').innerHTML = ErrorMsg;
};
function ClearError(){
    document.getElementById('ErrorMsg').innerHTML ='';
};