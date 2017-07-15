var LoginSuccessPage = "projects.html";

$(document).ready(function () {

    $("#login-submit").click(function () {
        var name = $("#login-username").val();
        var password = $("#login-password").val();
        var validate = true;
        if (name == "" || name == null) {
            $("#login-username").parent().addClass("has-error");
            validate = false;
        } else {
            $("#login-username").parent().removeClass("has-error");
        }
        if (password == "" || password == null) {
            $("#login-password").parent().addClass("has-error");
            validate = false;
        } else {
            $("#login-password").parent().removeClass("has-error");
        }
        if (validate) {
            UserManager.login(name, password, function (success) {
                if (!success) {
                    $("#login-username, #login-password").parent().addClass("has-error");
                    $.messager.popup("用户名或密码错误");
                    return;
                }
                location.href = LoginSuccessPage;
            });
        }
    });

    $("body").keydown(function () {
        if (event.keyCode == 13) {
            $("#login-submit").click();
        }
    });

});