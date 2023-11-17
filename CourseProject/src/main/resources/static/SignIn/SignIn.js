document.getElementById('SignIn').addEventListener('submit',async function (e){
    e.preventDefault();
    const email = document.getElementById('email').value;
    const password =document.getElementById('password').value;
    if(ValidateEmail(email)&&ValidatePassword(password)){
        const response = await fetch('/api/auth/signin',{
            method:"POST",
            headers:{
                "Content-type": "application/json",
                "Accept": "application/json"
            },
            body:JSON.stringify({
                "email":email,
                "password":password
            })
        });
        if(response.ok){
            let JWTToken = await response.json();
            JwtCookie('jwt',JWTToken.token,2);
            window.location.href = '/';
        }
        else{
            const error = await response.json();
            console.log(error);
        }
    }
})