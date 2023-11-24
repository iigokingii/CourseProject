document.getElementById('SignUp').addEventListener('submit',async function(e){
    e.preventDefault();
    const login = document.getElementById("login").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    const repetedPassword = document.getElementById("repetedPassword").value;
    if(ValidateLogin(login)&&ValidateEmail(email) && ValidatePassword(password) && ValidateRepetedPassword(repetedPassword) && ValidatePasswordMatch(password,repetedPassword)){
        const response = await fetch("/api/auth/signup",{
            method:"POST",
            headers:{
                "Content-type": "application/json",
                "Accept": "application/json"
            },
            body:JSON.stringify({
                "login":login,
                "email":email,
                "password":password
            })
        });
        if(response.ok){
            let responseJson = await response.json();
            if(responseJson.exception===""){
                JwtCookie('jwt',responseJson.token,2);
                window.location.href = '/SignIn';
            }
            else{
                console.log('Error');
                HandleError(responseJson.exception);
            }
        }
        else{
            const error = await response.json();
            console.log(error);
        }
    }
});

