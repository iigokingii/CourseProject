//удаление jwt по завершении сессии
document.getElementById("logout").addEventListener("click", function() {
    deleteCookie('jwt');
    console.log('jwt cookie deleted');
    window.location.href = '/SignIn';
});
