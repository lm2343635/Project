$(document).ready(function() {


    checkAdminSession(function () {
       loadUsers();
    });
    
});

function loadUsers() {
    UserManager.getAll(function (users) {
        if (users == null) {
            location.href = "session.html";
            return;
        }


    });
}