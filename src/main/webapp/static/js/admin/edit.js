var uid = request("uid");
var pid = request("pid");

$(document).ready(function () {

    checkAdminSession(function () {

        ConfigManager.getConfigObject(function (config) {

            var global = config["global"];

            $("#project-content").summernote({
                toolbar: SUMMERNOTE_TOOLBAR_FULL,
                lang: "zh-CN",
                height: 400,
                placeholder: global.content
            });

            $("#project-name").fillText({
                name: global.name
            })

            if (pid != "") {
                ProjectManager.getProject(pid, function (project) {
                    if (project == null) {
                        location.href = "link.html";
                        return;
                    }

                    $("#project-panel .panel-title").fillText({
                        task: "编辑",
                        pname: project.name
                    });
                    loadUserInfo(project.uid);

                    $("#project-name input").val(project.name);
                    for (var attributeName in project.attributes) {
                        $("#project-attributes").mengular(".attribute-list-template", {
                            attributeName: attributeName,
                            attributeValue: project.attributes[attributeName]
                        });
                    }
                    $("#project-attributes").mengularClearTemplate();

                    $("#project-content").summernote("code", project.content);

                });

                return;
            }

            if (uid != "") {
                $("#project-panel .panel-title").fillText({
                    task: "新建",
                    pname: ""
                });
                loadUserInfo(uid);

                var attributes = global.attributes.split("/");
                for (var i in attributes) {
                    $("#project-attributes").mengular(".attribute-list-template", {
                        attributeName: attributes[i],
                        attributeValue: ""
                    });
                }
                $("#project-attributes").mengularClearTemplate();
            }
        });

    });


    $("#project-submit").click(function () {
        var name = $("#project-name input").val();
        if (name == "" || name == null) {
            $.messager.popup("名称不能为空！");
            return;
        }
        var attributes = {};
        $(".attribute-list-template").each(function() {
            attributes[$(this).attr("id")] = $(this).find("input").val();
        });
        var content = $("#project-content").summernote("code");
        $(this).attr("disabled", "disabled");

        if (uid != "") {
            ProjectManager.addProject(name, JSON.stringify(attributes), content, uid, function (pid) {
                if (pid == null) {
                    location.href = "session.html";
                    return;
                }
                $.messager.popup("保存成功！");
                setTimeout(function () {
                    location.href = "edit.html?pid=" + pid;
                }, 1000);
            });
            return;
        }

        if (pid != "") {
            ProjectManager.modifyProject(pid, name, JSON.stringify(attributes), content, function (success) {
                if (!success) {
                    location.href = "session.html";
                    return;
                }
                $("#project-submit").removeAttr("disabled");
                $.messager.popup("修改成功！");
            });
            return;
        }

    });

});

function loadUserInfo(uid) {
    UserManager.getUser(uid, function (user) {
        if (user == null) {
            location.href = "link.html";
            return;
        }

        $("#back").attr("href", "projects.html?uid=" + uid);

        $("#project-panel .panel-title").fillText({
            uname: user.uname
        });
    });
}