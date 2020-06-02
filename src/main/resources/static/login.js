$("#signin").click(function() {
    $("#second").fadeOut("fast", function() {
        $("#first").fadeIn("fast");
    });
});

$(function() {
    $("form[name='login']").validate({
        rules: {

            email: {
                required: true,
                email: true
            },
            password: {
                required: true,

            }
        },
        messages: {
            email: "Please enter a valid email address",

            password: {
                required: "Please enter password",

            }

        },
        submitHandler: function(form) {
            console.log('submit.................')
            form.submit();
        }
    });
});

$(document).ready(function () {

    $("#login").submit(function (event) {
        //stop submit the form, we will post it manually.
        event.preventDefault();

        const search = {
            username: $("#email").val(),
            password: $("#password").val()
        };
        //console.log('sssssssssssssssssssss');
        //console.log(search);

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/api/v1/login",
            data: JSON.stringify(search),
            dataType: 'json',
            cache: false,
            timeout: 600000,
            success: function (data) {
                sessionStorage.setItem('token', data.token);
                window.location.href = "http://localhost:8080/prescription";
             },
             error: function (e) {
                 console.log(e);
            }
        });

    });

});
