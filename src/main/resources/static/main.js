$(document).ready(function () {

    $('.newButton, .table .editBtn').on('click',function (e) {

        e.preventDefault();

        $.get("/patients", function(patients, status){
            $('.editForm #patientId').empty();
            $.each(patients, function(i, patient) {
                console.log('Appending patient');
                $('.editForm #patientId').append($('<option></option>').val(patient.id).html(patient.name));
            });
        });

        const href = $(this).attr('href');
        const buttonText = $(this).text();

        if(buttonText === 'New') {
            $('.editForm #diagnosis').val('');
            $('.editForm #medicine').val('');
            $('.editForm #nextVisitDate').val('');
            $('.editForm #exampleModal').modal();
        }
        else {
            $.get(href, function (prescription, status) {

                const { diagnosis, medicines, createdAt, nextVisitDate, patient } = prescription;

                $('.editForm #diagnosis').val(diagnosis);
                $('.editForm #medicine').val(medicines);
                $('.editForm #nextVisitDate').val("2020-06-02T11:42:13.510");

            });
            $('.editForm #exampleModal').modal();
        }

    });

});