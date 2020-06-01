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
                console.log('data.................');
                console.log(data)

                // var json = "<h4>Ajax Response</h4>&lt;pre&gt;"
                //     + JSON.stringify(data, null, 4) + "&lt;/pre&gt;";
                // $('#feedback').html(json);
                //
                // console.log("SUCCESS : ", data);
                // $("#btn-search").prop("disabled", false);

             },
             error: function (e) {

                 console.log(e);

                // var json = "<h4>Ajax Response</h4>&lt;pre&gt;"
                //     + e.responseText + "&lt;/pre&gt;";
                // $('#feedback').html(json);
                //
                // console.log("ERROR : ", e);
                // $("#btn-search").prop("disabled", false);

            }
        });

    });

});
