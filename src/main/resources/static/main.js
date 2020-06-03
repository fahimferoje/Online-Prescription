$(document).ready(function () {

    $('.newButton, .table .editBtn').on('click',function (e) {

        e.preventDefault();

        $.get("/patients", function(patients, status){
            $('.editForm #patientId').empty();
            $.each(patients, function(i, patient) {
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

                const {id, diagnosis, medicines, createdAt, nextVisitDate, patient } = prescription;

                $('.editForm #id').val(id);
                $('.editForm #diagnosis').val(diagnosis);
                $('.editForm #medicines').val(medicines);
                $('.editForm #nextVisitDateString').val(nextVisitDate);
                $('.editForm #patientId').val(patient.id);

            });
            $('.editForm #exampleModal').modal();
        }

    });

    const $dateRange = $('#dateRange');

    $dateRange.daterangepicker();
    $dateRange.on('apply.daterangepicker', function (ev, picker) {
        $('input[name="dateFrom"]').val(picker.startDate.format('DD/MM/YYYY'));
        $('input[name="dateTo"]').val(picker.endDate.format('DD/MM/YYYY'));
    });

});