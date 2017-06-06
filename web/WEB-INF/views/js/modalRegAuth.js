/**
 * Здесь описана логика работы модальных окон регистрации и аутентификации --%>
 * Created by Aleksandr Kovalev, moved to this file by Rostislav Dublin
 */

var hasErr = function () {
    var result = false;
    $("#regForm").find('[active]').each(function () {
        var div = $(this).closest('div.form-group');
        if (div.hasClass('has-error')) {
            result = true;
            return;
        }
    });
    return result;
};

setChangeListener('#regInputPassword1', 1500, function (elem) {
    if (elem.val() && $('#regInputPassword').val() == elem.val()) {
        setStatusElement('#regInputPassword1', 'success');
    } else {
        setStatusElement('#regInputPassword1', 'error', 'Пароли должны совпадать!');
    }
});

setChangeListener('#regInputPhone', 1500, function (elem) {
    if (elem.val()) {
        setStatusElement('#regInputPhone', 'none');
    } else {
        setStatusElement('#regInputPhone', 'error', 'Строка не должна быть пустой!');
    }
});

setChangeListener('#regInputFirstName', 1500, function (elem) {
    if (elem.val()) {
        setStatusElement('#regInputFirstName', 'none');
    } else {
        setStatusElement('#regInputFirstName', 'error', 'Строка не должна быть пустой!');
    }
});
setActiveFormInput("/users/validation", '#regInputEmail');
setActiveFormInput("/users/validation", '#regInputLogin');
setActiveFormInput("/users/validation", '#regInputPhone');
setActiveFormInput("/users/validation", '#regInputPassword');

$("#loginBtn").click(function () {
    var formDataJSON = parseFormToJSON('#loginForm');
    var headers = getCsrfTokenHeader();
    $.post({
            url: "/users/login", data: formDataJSON,
            contentType: "application/json;charset=UTF-8",
            dataType: "json",
            headers: headers,
            success: function (data) {
                if (data.success)
                    window.location.replace(" ");
                else if (data.error) {
                    var div = $('#loginForm').find('div.alert');
                    $(div).find("p").text(data.error);
                    div.removeClass('hide');
                }
            }
        }
    );
});

$("#regBtn").click(function () {
    if (!hasErr()) {
        var formDataJSON = parseFormToJSON("#regForm");
        var headers = getCsrfTokenHeader();
        $.post({
            url: "/users", data: formDataJSON,
            contentType: "application/json;charset=UTF-8",
            dataType: "json",
            headers: headers,
            success: function (data) {
                if (data.success) {
                    window.location.replace(" ");
                } else if (data.errors) {
                    processErrors(data.errors);
                }
            }
        });
    }
});
