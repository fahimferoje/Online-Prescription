// let globalData = [];
// $( document ).ready(function() {
//     console.log( "=========ready=============!" );
//     $.ajax({
//         type: "GET",
//         contentType: "application/json",
//         url: "/api/v1/prescription",
//         headers: {
//             'Authorization':`Bearer ${sessionStorage.getItem('token')}`
//         },
//         dataType: 'json',
//         cache: false,
//         timeout: 600000,
//         success: function (data) {
//             globalData = data;
//         },
//         error: function (e) {
//             console.log(e);
//         }
//     });
// });
//
// console.log(globalData);