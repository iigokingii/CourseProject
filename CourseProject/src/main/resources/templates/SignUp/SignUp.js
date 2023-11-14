document.querySelector('login').addEventListener('submit',async function(e){
    const firstName = document.getElementById("firstName").value;
    const lastName = document.getElementById("lastName").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    if(ValidateFirstName(firstName)&&ValidateEmail(email)&&ValidateLastName(lastName)&&ValidatePassword(password)){
        const SignUpRequest = {
            firstName:firstName,
            lastName:lastName,
            email:email,
            password:password
        }
        const response = await fetch("",{

        })


    }

});

function ValidateFirstName(firstname){
    if (firstname !== '')
        return true;
    else {
        HandleError('Name is empty')
        return false;
    }
}
function ValidateLastName(lastName){
    if (lastName !== '')
        return true;
    else {
        HandleError('Name is empty')
        return false;
    }
}
function ValidateEmail(email){
    if(email===''){
        HandleError('Email is empty');
        return false;
    }
    const pattern = /^[\w.-]+@[\w.-]+\.\w+$/;
    if(pattern.test(email))
        return true
    else{
        HandleError('Email format is invalid. ')
        return false;
    }
}
function ValidatePassword(password){
    if(password===''){
        HandleError('Email is empty');
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
}


function HandleError (ErrorMsg){
    document.getElementById('ErrorMsg').innerHTML = ErrorMsg;
}
function ClearError(){
    document.getElementById('ErrorMsg').innerHTML ='';
}
