$(document).ready(function () {

    $('.table .editBtn').on('click',function (e) {

        e.preventDefault();

        const href = $(this).attr('href');

        console.log(href);

        $.get(href, function (prescription, status) {

            console.log('============');
            console.log(prescription);
            console.log('Diagnosis '+prescription.diagnosis);

            $('.myForm #medicine').val(prescription.diagnosis);

        });

        $('.editForm #exampleModal').modal();

    });

});