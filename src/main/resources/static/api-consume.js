$(document).ready(function () {

    // $.get("https://rxnav.nlm.nih.gov/REST/interaction/interaction.json?rxcui=341248"
    //     , function (data, status) {
    //
    //         const { interactionTypeGroup } = data;
    //         const { interactionType } = interactionTypeGroup[0];
    //         const { interactionPair } = interactionType[0];
    //
    //         const table = $('#example').DataTable({
    //             "data": interactionPair,
    //             "columns": [
    //                 {
    //                     "className": 'details-control',
    //                     "orderable": false,
    //                     "data": null,
    //                     "defaultContent": '<i class="fas fa-plus"></i>'
    //                 },
    //                 {"data": "severity"},
    //                 {"data": "description"},
    //             ]
    //             ,
    //             "order": [[1, 'asc']]
    //         });
    //
    //         // Add event listener for opening and closing details
    //         $('#example tbody').on('click', 'td.details-control', function () {
    //             var tr = $(this).closest('tr');
    //             var row = table.row(tr);
    //
    //             if (row.child.isShown()) {
    //                 // This row is already open - close it
    //                 row.child.hide();
    //                 tr.removeClass('shown');
    //             } else {
    //                 // Open this row
    //                 row.child(format(row.data())).show();
    //                 tr.addClass('shown');
    //             }
    //         });
    //
    //         /* Formatting function for row details - modify as you need */
    //         function format(d) {
    //
    //             const {interactionConcept: interactionConceptArray} = d;
    //             let tableContents = '';
    //             for (const itc of interactionConceptArray) {
    //
    //                 const {
    //                     minConceptItem: {name: minName, rxcui, tty}
    //                     , sourceConceptItem: {id, name: srcName, url}
    //                 } = itc;
    //
    //                 tableContents += `<tr>
    //                             <td>${minName}</td>
    //                             <td>${rxcui}</td>
    //                             <td>${tty}</td>
    //                             <td>${id}</td>
    //                             <td>${srcName}</td>
    //                             <td>${url}</td>
    //                          </tr>`;
    //
    //             }
    //
    //             const tableStruc = `<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">
    //                             <thead class="thead-dark">
    //                                 <tr>
    //                                     <th>name</th>
    //                                     <th>Rxcui</th>
    //                                     <th>tty</th>
    //                                     <th>id</th>
    //                                     <th>name</th>
    //                                     <th>url</th>
    //                                 </tr>
    //                             </thead>
    //                             <tbody>
    //                                 ${tableContents}
    //                             </tbody>
    //                         </table>`;
    //             return tableStruc;
    //         }
    //
    // });

    jQuery.ajax({
        type: "GET",
        url: "https://rxnav.nlm.nih.gov/REST/interaction/interaction.json?rxcui=341248",
        beforeSend: function() {
            console.log('called before');
            $("#overlay").show();
        },
        success: function(data) {
            $("#overlay").hide();

            const {interactionTypeGroup} = data;
            const {interactionType} = interactionTypeGroup[0];
            const {interactionPair} = interactionType[0];

            const table = $('#example').DataTable({
                "data": interactionPair,
                "columns": [
                    {
                        "className": 'details-control',
                        "orderable": false,
                        "data": null,
                        "defaultContent": '<i class="fas fa-plus"></i>'
                    },
                    {"data": "severity"},
                    {"data": "description"},
                ]
                ,
                "order": [[1, 'asc']]
            });

            // Add event listener for opening and closing details
            $('#example tbody').on('click', 'td.details-control', function () {
                var tr = $(this).closest('tr');
                var row = table.row(tr);

                if (row.child.isShown()) {
                    // This row is already open - close it
                    row.child.hide();
                    tr.removeClass('shown');
                } else {
                    // Open this row
                    row.child(format(row.data())).show();
                    tr.addClass('shown');
                }
            });

            /* Formatting function for row details - modify as you need */
            function format(d) {

                const {interactionConcept: interactionConceptArray} = d;
                let tableContents = '';
                for (const itc of interactionConceptArray) {

                    const {
                        minConceptItem: {name: minName, rxcui, tty}
                        , sourceConceptItem: {id, name: srcName, url}
                    } = itc;

                    tableContents += `<tr>
                                        <td>${minName}</td>
                                        <td>${rxcui}</td>
                                        <td>${tty}</td>
                                        <td>${id}</td>
                                        <td>${srcName}</td>
                                        <td>${url}</td>
                                     </tr>`;

                }

                const tableStruc = `<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">
                                        <thead class="thead-dark">
                                            <tr>
                                                <th>name</th>
                                                <th>Rxcui</th>
                                                <th>tty</th>
                                                <th>id</th>
                                                <th>name</th>
                                                <th>url</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            ${tableContents}
                                        </tbody>
                                    </table>`;
                return tableStruc;
            }
        }
    });
});

