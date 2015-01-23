/**
 * Created by ivald79 on 13/09/2014.
 */
$(document).ready(function () {

    $(function () {
        $(".form-control").focus(function () {
            $(this).closest(".textbox-wrap").addClass("focused");
        }).blur(function () {
            $(this).closest(".textbox-wrap").removeClass("focused");
        });
    });

    // login html
    $('#button_login').click(function () {
        var user = $('#UID').val();
        var pass = $('#PID').val();

        $('#loginDiv').find('input').each(function () {
            validTextField(this);
        });

        if (user == '' || pass == '') {
            return false;
        }

        $('#button_login').addClass('loader');

        $.ajax({
            type: 'GET',
            complete: function () {
                $('#button_login').removeClass('loader');
            },
            dataType: 'json',
            contentType: "application/json",
            url: mainRootPath + "login/authentication/username/" + user + "/password/" + pass,
            success: function (data) {
                if (data.responseCode == 'FAILED') {
                    showMessage(data.responseMessage);
                    return false;
                } else {
                    window.location = "console/welcome.html";
                    //var window_dimensions = "addressbar=0, status=no,titlebar=no,toolbar=no,menubar=no,location=no,scrollbars=yes,resizable=yes,height=" + screen.height + ", width=" + screen.width;
                    //window.open(htmlRootPath + "pages/console/welcome.html","_blank",  window_dimensions);
                }
            },
            error: function (xhr, err) {
                console.log('Sample of error data:', err);
                console.log("readyState: " + xhr.readyState + "\nstatus: "
                    + xhr.status + "\nresponseText: "
                    + xhr.responseText);
                showMessage(xhr.responseText);
            }
        });
    });

    // register html
    $('#button_register').click(function () {
        var firstName = $('#FN').val();
        var lastName = $('#LN').val();
        var user = $('#UID').val();
        var pass = $('#PID').val();
        var confPass = $('#CID').val();

        $('#registerDiv').find('input').each(function () {
            validTextField(this);
        });

        if (firstName == '' || lastName == '' || pass == '' || user == '' || confPass == '') {
            return false;
        }

        if (!checkPassword($('#PID')) || !checkPassword($('#CID'))) {
            return false;
        }

        if (pass != confPass) {
            showMessage("The passwords didn't match.");
            return false;
        }

        //create json object
        var userObj = {"userName": user, "password": pass};
        var contactObj = {"firstName": firstName, "lastName": lastName};
        userObj.contact = contactObj;

        $('#button_register').addClass('loader');

        $.ajax({
            type: 'POST',
            complete: function () {
                $('#button_register').removeClass('loader');
            },
            dataType: 'json',
            contentType: "application/json",
            url: mainRootPath + "register/registration",
            data: JSON.stringify(userObj),
            success: function (data) {
                showMessage(data.responseMessage);
                return false;
            },
            error: function (xhr, err) {
                console.log('Sample of error data:', err);
                console.log("readyState: " + xhr.readyState + "\nstatus: "
                    + xhr.status + "\nresponseText: "
                    + xhr.responseText);
                showMessage(xhr.responseText);
            }
        });
    });

    $("#exitBtn").on("click", function() {
        bootbox.confirm("Are you sure?", function(result) {
            if(result == true) {

                $.ajax({
                    type: 'GET',
                    complete: function () {

                    },
                    dataType: 'json',
                    contentType:  "application/json",
                    url: mainRootPath + "logout/",
                    success: function (data) {
                        window.location = "login.html";
                    },
                    error: function (xhr, err) {
                        console.log('Sample of error data:', err);
                        console.log("readyState: " + xhr.readyState + "\nstatus: "
                        + xhr.status + "\nresponseText: "
                        + xhr.responseText);
                        showMessage(xhr.responseText);
                    }
                });
            }
        });
    });

});

function validTextField(elem) {
    var textField = $(elem).val();
    if (textField == '') {
        $(elem).addClass('valid_failed1');
    } else if (textField != '') {
        $(elem).removeClass('valid_failed1');
    }
}

function checkPassword(elem) {
    var textField = $(elem).val();
    // at least one number, one lowercase and one uppercase letter
    // at least six characters that are letters, numbers or the underscore
    // Aa123456
    var re = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])\w{6,}$/;
    if (re.test(textField)) {
        return true;
    } else {
        showMessage("An one of the passwords is invalid.");
        return false;
    }
}

function showMessage(str) {
    $('#divAlerts').find('div').remove();
    $(document).trigger("add-alerts", [
        {
            'message': str,
            'priority': 'error'
        }
    ]);
}
