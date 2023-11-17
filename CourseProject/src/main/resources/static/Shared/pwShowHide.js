const passwordShowHide =document.querySelectorAll(".eye-icon");
passwordShowHide.forEach(pass=>{
    pass.addEventListener('click',()=>{
    let passwordFields = pass.parentElement.parentElement.querySelectorAll(".password");
    passwordFields.forEach(password=>{
        if(password.type==="password"){
            password.type = "text";
            pass.classList.replace("bxs-hide","bxs-show");
            return;
        }
        password.type = "password";
        pass.classList.replace("bxs-show","bxs-hide");
    })
})
})