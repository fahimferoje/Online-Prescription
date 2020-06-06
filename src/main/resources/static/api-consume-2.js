$(document).ready(function () {

    $.get("https://rxnav.nlm.nih.gov/REST/interaction/interaction.json?rxcui=341248"
        , function (data, status) {

        console.log('============================Calling================');

            const { interactionTypeGroup } = data;
            const { interactionType } = interactionTypeGroup[0];
            const { interactionPair } = interactionType[0];

            console.log(interactionPair);
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

    });

    // const dd = [
    //     {
    //         "interactionConcept": [
    //             {
    //
    //                 "minConceptItem": {
    //                     "rxcui": "341248",
    //                     "name": "ezetimibe",
    //                     "tty": "IN"
    //                 },
    //                 "sourceConceptItem": {
    //                     "id": "DB00973",
    //                     "name": "Ezetimibe",
    //                     "url": "http://www.drugbank.ca/drugs/DB00973#interactions"
    //                 }
    //             },
    //             {
    //                 "minConceptItem": {
    //                     "rxcui": "3008",
    //                     "name": "cyclosporine",
    //                     "tty": "IN"
    //                 },
    //                 "sourceConceptItem": {
    //                     "id": "DB00091",
    //                     "name": "Cyclosporine",
    //                     "url": "http://www.drugbank.ca/drugs/DB00091#interactions"
    //                 }
    //             }
    //         ],
    //         "severity": "N/A",
    //         "description": "The serum concentration of Cyclosporine can be increased when it is combined with Ezetimibe."
    //     },
    //     {
    //         "interactionConcept": [
    //             {
    //                 "minConceptItem": {
    //                     "rxcui": "341248",
    //                     "name": "ezetimibe",
    //                     "tty": "IN"
    //                 },
    //                 "sourceConceptItem": {
    //                     "id": "DB00973",
    //                     "name": "Ezetimibe",
    //                     "url": "http://www.drugbank.ca/drugs/DB00973#interactions"
    //                 }
    //             },
    //             {
    //                 "minConceptItem": {
    //                     "rxcui": "290",
    //                     "name": "adenine",
    //                     "tty": "IN"
    //                 },
    //                 "sourceConceptItem": {
    //                     "id": "DB00173",
    //                     "name": "Adenine",
    //                     "url": "http://www.drugbank.ca/drugs/DB00173#interactions"
    //                 }
    //             }
    //         ],
    //         "severity": "N/A",
    //         "description": "The metabolism of Ezetimibe can be decreased when combined with Adenine."
    //     },
    //     {
    //         "interactionConcept": [
    //             {
    //                 "minConceptItem": {
    //                     "rxcui": "341248",
    //                     "name": "ezetimibe",
    //                     "tty": "IN"
    //                 },
    //                 "sourceConceptItem": {
    //                     "id": "DB00973",
    //                     "name": "Ezetimibe",
    //                     "url": "http://www.drugbank.ca/drugs/DB00973#interactions"
    //                 }
    //             },
    //             {
    //                 "minConceptItem": {
    //                     "rxcui": "42463",
    //                     "name": "pravastatin",
    //                     "tty": "IN"
    //                 },
    //                 "sourceConceptItem": {
    //                     "id": "DB00175",
    //                     "name": "Pravastatin",
    //                     "url": "http://www.drugbank.ca/drugs/DB00175#interactions"
    //                 }
    //             }
    //         ],
    //         "severity": "N/A",
    //         "description": "Pravastatin may decrease the excretion rate of Ezetimibe which could result in a higher serum level."
    //     }
    // ];


});

