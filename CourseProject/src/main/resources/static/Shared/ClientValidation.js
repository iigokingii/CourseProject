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

function ValidateUserRole(role){
    if (role !== '')
        return true;
    else {
        HandleError('Choose user role')
        return false;
    }
}

function ValidateUserAvatar(avatar){
    if (avatar !== undefined )
        return true;
    else {
        HandleError('no avatar installed')
        return false;
    }
}

function ValidateEmail(email,isSignUp = true){
    if(email===''){
        HandleError('Email field is empty');
        return false;
    }
    if(isSignUp){
        const pattern = /^[\w.-]+@[\w.-]+\.\w+$/;
        if(pattern.test(email))
            return true;
        else{
            HandleError('Email format is invalid. ')
            return false;
        }
    }
    return true;
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

function ValidatePassword(password,isSignUp=true){
    if(password===''){
        HandleError('Password field is empty');
        return false;
    }
    if(isSignUp){
        const pattern = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}$/;
        if(pattern.test(password))
            return true
        else{
            HandleError('Password format is invalid. It have to contain at least one digit, one upper case, one lower case and 8 symbols from mentioned characters');
            return false;
        }
    }
    return true;
};

function IsValidId(id) {
    if (!isNaN(id))
        return true
    else {
        HandleError('Incorrect id');
        return false;
    }
}

function HandleError (ErrorMsg){
    document.getElementById('ErrorMsg').innerHTML = ErrorMsg;
};
function ClearError(){
    document.getElementById('ErrorMsg').innerHTML ='';
};