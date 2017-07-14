var uid = request("uid");
var pid = request("pid");

$(document).ready(function () {

    checkAdminSession(function () {

        if (pid != "") {
            ProjectManager.getProject(pid, function (project) {
               if (project == null) {
                   location.href = "link.html";
                   return;
               }

            });

            return;
        }

        if (uid != "") {
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

            ConfigManager.getConfigObject(function (config) {
                var global = config["global"];
                $("#project-name").fillText({
                    name: global.name
                })

                var attributes = global.attributes.split("/");
                for (var i in attributes) {
                    $("#project-attributes").mengular(".attribute-list-template", {
                        attributeName: attributes[i],
                        attributeValue: ""
                    });
                }

                $("#project-attributes").mengularClearTemplate();

                $("#project-content").summernote({
                    toolbar: SUMMERNOTE_TOOLBAR_FULL,
                    lang: "zh-CN",
                    height: 400,
                    placeholder: global.content
                });
            });
        }

    });


    $("#project-submit").click(function () {
        var name = $("#project-name input").val();
        if (name == "" || name == null) {
            $.messager.popup("名称不能为空！");
            return;
        }
        var attributes = [];
        $(".attribute-list-template").each(function() {
            attributes[$(this).attr("id")] = $(this).find("input").val();
        });
        var content = $("#project-content").summernote("code");
        $(this).attr("disabled", "disabled");
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
    });

});