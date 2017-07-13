var uid = request("uid");

$(document).ready(function () {

    checkAdminSession(function () {
        UserManager.getUser(uid, function (user) {
            if (user == null) {
                location.href = "link.html";
                return;
            }

            $("#create-project").attr("href", "edit.html?uid=" + uid);

            $("#project-panel .panel-title").fillText({
                uname: user.uname
            });
        });
    });



});