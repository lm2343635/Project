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

            loadProjects();
        });
    });

});

function loadProjects() {
    ProjectManager.getProjectsByUid(uid, function (projects) {
        $("#project-list tbody").mengularClear();

        for (var i in projects) {
            var project = projects[i];
            $("#project-list tbody").mengular(".project-list-template", {
                pid: project.pid,
                createAt: project.createAt.format(DATE_HOUR_MINUTE_FORMAT),
                updateAt: project.updateAt.format(DATE_HOUR_MINUTE_FORMAT),
                name: project.name
            });

            $("#" + project.pid + " .project-list-remove").click(function () {
                var pid = $(this).mengularId();
                var name = $("#" + pid + " .project-list-name").text();
                $.messager.confirm("警告", "确认删除工程" + name + "吗？", function () {
                    ProjectManager.removeProject(pid, function (success) {
                        if (!success) {
                            location.href = "session.html";
                            return;
                        }
                        $("#" + pid).remove();
                    });
                });
            });
        }
    });
}