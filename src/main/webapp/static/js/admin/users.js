$(document).ready(function () {

    checkAdminSession(function () {
        loadUsers("");
    });

    $("#create-user-submit").click(function () {
        var name = $("#create-user-name").val();
        var password = $("#create-user-password").val();
        var validate = true;
        if (name == "" || name == null) {
            $("#create-user-name").parent().addClass("has-error");
            validate = false;
        } else {
            $("#create-user-name").parent().removeClass("has-error");
        }
        if (password == "" || password == null) {
            $("#create-user-password").parent().addClass("has-error");
            validate = false;
        } else {
            $("#create-user-password").parent().removeClass("has-error");
        }
        if (validate) {
            UserManager.isUserExist(name, function (exist) {
                if (exist) {
                    $.messager.popup("该人员已存在，不能重复添加！");
                    return;
                }
                UserManager.addUser(name, password, function (uid) {
                    if (uid == null) {
                        location.href = "session.html";
                        return;
                    }
                    $("#create-user-modal").modal("hide");
                    loadUsers("");
                });
            });

        }
    });

    $("#create-user-modal").on("hidden.bs.modal", function () {
        $("#create-user-name").val("");
    });

    $("#search-submmit").click(function () {
        var keyword = $("#search-name").val();
        loadUsers(keyword);
    });

});

function loadUsers(keyword) {
    UserManager.search(keyword, function (users) {
        if (users == null) {
            location.href = "session.html";
            return;
        }

        $("#user-list tbody").mengularClear();
        for (var i in users) {
            var user = users[i];
            $("#user-list tbody").mengular(".user-list-template", {
                uid: user.uid,
                createAt: user.createAt.format(DATE_HOUR_MINUTE_FORMAT),
                uname: user.uname,
                password: user.password
            });

            $("#" + user.uid + " .user-list-remove").click(function () {
                var uid = $(this).mengularId();
                var uname = $("#" + uid + " .user-list-uname").text();
                $.messager.confirm("警告", "确认删除用户" + uname + "吗？<strong class='text-danger'>删除该用户后该用户的所有工程也将被删除！</strong>", function () {
                    UserManager.removeUser(uid, function (success) {
                        if (!success) {
                            location.href = "session.html";
                            return;
                        }
                        $("#" + uid).remove();
                    });
                });
            });
        }
    });
}