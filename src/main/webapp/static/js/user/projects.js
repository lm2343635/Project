$(document).ready(function () {

    checkSession(function (user) {
        $("#user-name").text(user.uname);

        loadUserProjects();
    });

    $("#modify-password-submit").click(function () {
        var newPassword = $("#modify-password-new").val();
        var validate = true;
        if (newPassword == "" || newPassword == null) {
            $("#modify-password-new").parent().addClass("has-error");
            validate = false;
        } else {
            $("#modify-password-new").parent().removeClass("has-error");
        }
        if (validate) {
            UserManager.modifyPassword(newPassword, function (success) {
                if (!success) {
                    location.href = "session.html";
                    return;
                }
                $("#modify-password-modal").modal("hide");
                $.messager.popup("新密码已修改成功！");
            });
        }
    });

    $("#modify-password-modal").on("hidden.bs.modal", function (e) {
        $("#modify-password-new").val("");
    });

});

function loadUserProjects() {
    ProjectManager.getProjectsForUser(function (projects) {
        if (projects == null) {
            location.href = "session.html";
            return;
        }

        $("#project-list").mengularClear();

        for (var i in projects) {
            var project = projects[i];
            $("#project-list").mengular(".project-list-template", {
                pid: project.pid,
                name: project.name,
                updateAt: project.updateAt.format(DATE_HOUR_MINUTE_FORMAT),
                expireAt: project.expireAt.format(YEAR_MONTH_DATE_FORMAT)
            });
        }
    });
}