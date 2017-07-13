var uid = request("uid");
var pid = request("pid");

$(document).ready(function () {

    checkAdminSession(function () {

        if (pid != "") {


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

                $("#project-content").summernote({
                    toolbar: SUMMERNOTE_TOOLBAR_FULL,
                    lang: "zh-CN",
                    height: 400,
                    placeholder: global.content
                });
            });
        }

    });



});