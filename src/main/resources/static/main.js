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
            $('.editForm #addEditPrescLabel').text('Add Prescription');
            $('.editForm #diagnosis').val('');
            $('.editForm #medicine').val('');
            $('.editForm #nextVisitDate').val('');
            $('.editForm #exampleModal').modal();
        }
        else {
            $('.editForm #addEditPrescLabel').text('Edit Prescription');
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

    $('.showDailyReportBtn').on('click',function (ev) {

        ev.preventDefault();

        const href = $(this).attr('href');

        $(".dailyPrescriptionWindow #classTable").find("tr:gt(0)").remove();

        $.get("/daily-prescription-count", function(dailyCountData, status){
            $.each(dailyCountData, function(i, dailyCount) {
                const { day, prescriptionCount} = dailyCount;
                $(".dailyPrescriptionWindow #classTable")
                    .append("<tr>" +
                        "<td>" + day + "</td>" +
                        "<td>" + prescriptionCount + "</td>" +
                        "</tr>");
            });

            $('.dailyPrescriptionWindow #dailyPrescriptionModal').modal();
        });

    });

    const $dateRange = $('#dateRange');

    $dateRange.daterangepicker();
    $dateRange.on('apply.daterangepicker', function (ev, picker) {
        $('input[name="dateFrom"]').val(picker.startDate.format('DD/MM/YYYY'));
        $('input[name="dateTo"]').val(picker.endDate.format('DD/MM/YYYY'));
    });

    const MAX_NEXT_VISIT_DATE = 5;
    const MIN_NEXT_VISIT_DATE = 1;

    $(function(){
        $('[type="date"]').prop('max', function(){
            return new Date(new Date().setFullYear(new Date().getFullYear() + MAX_NEXT_VISIT_DATE))
                .toJSON().split('T')[0];
        });

        $('[type="date"]').prop('min', function(){
            return new Date(new Date().setDate(new Date().getDate() + MIN_NEXT_VISIT_DATE))
                .toJSON().split('T')[0];
        });
    });

});