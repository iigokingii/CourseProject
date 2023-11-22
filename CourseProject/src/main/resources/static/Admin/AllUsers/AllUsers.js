async function GetUsers(){
    const response = await fetch('/getUsers',{
        method:"GET",
        headers:{
            "Content-type":"application/json",
            "Accept":"application/json"
        }
    })
    if(response.ok){
        let responseJson = await response.json();
        let tableBody = document.querySelector('tbody');
        responseJson.forEach(user => {
            tableBody.append(CreateRow(user))
        });
        console.log('resp:'+responseJson);
    }
    else{
        console.log('Error');
    }
}
//TODO если удаляешь пользователя под которым сейчас нужно удалить jwt и перейти на страницу регистрации
async function DeleteUser(id) {
    const response = await fetch(
        `/DeleteUser?userID=${id}`, {
        method: "DELETE",
        headers: {
            "Accept": "application/json"
        },
    });
    if (response.ok) {
        document.querySelector(`tr[data-rowid='${id}']`).remove();
    }
    else {
        const error = await response.json();
        console.log(error.message);
    }
}
async function getUserById(id){
    const response = await fetch(`/GetUserByID?userID=${id}`, {
        method: "GET",
        headers: {
            "Accept": "application/json"
        },
    });
    if (response.ok) {
        let activeUser = await response.json();
        FillDataToInputs(activeUser);
        AddPopUp();
    }
    else {
        const error = await response.json();
        console.log(error.message);
    }
}
async function BlobToImage(blobDataString, container) {
    //const blobData = new Blob([blobDataString], { type : 'plain/text' });
    const blobData = await fetch(`data:plain/text;base64,${blobDataString}`).then(res => res.blob());
    var blob;
    if (blobData instanceof Blob || blobData instanceof File) {
      blob = blobData;
    } else {
      console.error('Invalid blobData: ' + blobData);
      return;
    }
  
    var imageUrl = URL.createObjectURL(blob);
    var imgElement = document.createElement('img');
    imgElement.src = imageUrl;
    imgElement.classList.add("default-avatar");

    container.appendChild(imgElement);
}

function CreateRow(user){
    //console.log(user);
    let row = document.createElement('tr');
    row.setAttribute("data-rowid",user.user_PROFILE_ID)

    let login = document.createElement('td');
    login.innerHTML = user.login;
    row.append(login);

    let avatar = document.createElement('td');
    //blob->img
    BlobToImage(user.avatar,avatar);
    row.append(avatar);

    let email = document.createElement('td');
    email.innerHTML = user.email;
    row.append(email);

    let password = document.createElement('td');
    password.innerHTML = user.password;
    row.append(password);

    let role = document.createElement('td');
    role.innerHTML = user.user_ROLE;
    row.append(role);

    let Links = document.createElement('td');

    let editLink = document.createElement('button');
    editLink.append('Edit');
    editLink.classList.add("open-popup")
    editLink.addEventListener('click',async()=>{
        //console.log('Edit clicked');
        getUserById(user.user_PROFILE_ID);
    });
    Links.append(editLink);
    
    let deleteLink = document.createElement('button');
    deleteLink.append('Delete');
    deleteLink.classList.add("delete-button");
    deleteLink.addEventListener('click',async()=>{
        //console.log('delete clicked');
        DeleteUser(user.user_PROFILE_ID);
    })
    Links.append(deleteLink);

    row.append(Links);
    return row;
}

//Отправка на сервер данных
document.getElementById('PopUpForm').addEventListener('submit', async function (event) {
    event.preventDefault();
    const id = document.getElementById("id").value;
    const Login = document.getElementById("Login").value;
    const Avatar = document.getElementById("Avatar").value;
    const Email = document.getElementById("Email").value;
    const Password = document.getElementById("Password").value;
    const User_Role = document.getElementById("User_Role").value;
    //input name = "id" empty, so add new contact
    if (id == '') {
        if (ValidateLogin(Login)&&ValidateEmail(Email) && ValidatePassword(Password) && ValidateUserRole(User_Role) && ValidateUserAvatar(Avatar)) {
            let newUser = {
                "user_PROFILE_ID":'',
                "login":Login,
                "email":Email,
                "user_ROLE":User_Role,
                "avatar":Avatar,
                "password":Password
            }
            const response = await fetch("/AddUser", {
                method: "POST",
                headers: {
                    "Content-type": "application/json",
                    "Accept": "application/json"
                },
                body: JSON.stringify(
                    newUser
                )
            });
            if (response.ok === true) {
                console.log("Created");
                let respJson = await response.json();
                if(respJson.exception===""){
                    newUser.user_PROFILE_ID=respJson.id;
                    newUser.password = respJson.encodedPass;
                    let rows = document.querySelector("tbody");
                    rows.append(CreateRow(newUser));
                    AddPopUp();
                    ClearInputs();
                    ClearError();
                }
                else{
                    HandleError(respJson.exception);
                }    
            }
            else {
                const error = await response.json();
                console.log(error.message);
            }
        }
    }
    //input name = "id" has number value, update
    else if(IsValidId(id)){
        if (ValidateLogin(Login)&&ValidateEmail(Email) && ValidateUserRole(User_Role)) {
            let newUser = {
                "user_PROFILE_ID":id,
                "login":Login,
                "email":Email,
                "user_ROLE":User_Role,
                "avatar":Avatar,
                "password":Password
            }
            const response = await fetch("/UpdateUser", {
                method: "POST",
                headers: {
                    "Content-type": "application/json",
                    "Accept": "application/json"
                },
                body: JSON.stringify(
                    newUser
                )
            });
            if (response.ok === true) {
                console.log("Updated");
                document.querySelector(`tr[data-rowid='${newUser.user_PROFILE_ID}']`).replaceWith(CreateRow(newUser));
                AddPopUp();
                ClearInputs();
                ClearError();
            }
            else {
                const error = await response.json();
                console.log(error.message);
            }
        }  
    }
            
})
function ClearInputs() {
    document.getElementById("id").value = "";
    document.getElementById("Login").value = "";
    document.getElementById("Avatar").value = "";
    document.getElementById("Email").value = "";
    document.getElementById("Password").value = "";
    document.getElementById("User_Role").value = "";
}
//Появление / закрытие попап меню

function AddPopUp() {
    let popupBb = document.querySelector('.popup_bg');
    let popup = document.querySelector('.popup');
    popup.style.fontFamily = "'Poppins', sans-serif";
    let openPopUpButton = document.querySelectorAll('.open-popup');
    let closePopUpButton = document.querySelector('.close-popup');

    openPopUpButton.forEach((button) => {
        button.addEventListener('click', (e) => {
            e.preventDefault();
            popupBb.classList.add('active');
            popup.classList.add('active');
        })
    });

    closePopUpButton.addEventListener('click', () => {
        popupBb.classList.remove('active');
        popup.classList.remove('active');
        ClearInputs();
        ClearError();
    });

    document.addEventListener('click', (e) => {
        if (e.target === popupBb) {
            popupBb.classList.remove('active');
            popup.classList.remove('active');
            ClearInputs();
            ClearError();
        }
    })
};
function FillDataToInputs(user){
    document.getElementById("id").value=user.user_PROFILE_ID;
    document.getElementById("Login").value=user.login;
    document.getElementById("Avatar").value=user.avatar;
    document.getElementById("Email").value=user.email;
    document.getElementById("Password").value=user.password;
    document.getElementById("User_Role").value=user.user_ROLE;
}

AddPopUp();
GetUsers();
