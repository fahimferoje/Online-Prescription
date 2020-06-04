$(function() {
    $("form[name='addEditPrescriptionForm']").validate({
        rules: {
            diagnosis: {
                required: true
            },
            medicines: {
                required: true,
            }
        },
        messages: {
            diagnosis: "Please provide diagnosis",
            medicines: "Please provide medicines",

        },
        submitHandler: function(form) {
            form.submit();
        }
    });
});
