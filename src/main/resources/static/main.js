$(document).ready(function () {

    $('.table .editBtn').on('click',function (e) {

        e.preventDefault();

        const href = $(this).attr('href');

        $.get(href, function (prescription, status) {

            console.log(prescription);

            const { diagnosis, medicines, createdAt, nextVisitDate, patient } = prescription;

            $('.editForm #diagnosis').val(diagnosis);
            $('.editForm #medicine').val(medicines);
            $('.editForm #nextVisitDate').val("2020-06-02T11:42:13.510");

        });

        $('.editForm #exampleModal').modal();

    });

});