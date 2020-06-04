$(document).ready(function () {
    $.get("https://rxnav.nlm.nih.gov/REST/interaction/interaction.json?rxcui=341248"
        , function (data, status) {

            const { interactionTypeGroup } = data;
            const { interactionType } = interactionTypeGroup[0];
            const { interactionPair } = interactionType[0];

            for (const singleIntegration of interactionPair) {

                const {interactionConcept, severity = '', description = ''} = singleIntegration;

                let header = `<tr><td scope="col" colspan="3"><b>Min Concept Item</b></td>
                       <td colspan="3"><b>Source Concept Item</b></td>
                       <td><b>Severity</b></td>
                       <td><b>Description</b></td></tr>`;

                $("#myTable").append(header);

                header = `<tr><td><b>rxcui</b></td>
                       <td><b>name</b></td>
                       <td><b>tty</b></td>
                       <td><b>id</b></td>
                       <td><b>name</b></td>
                       <td><b>url</b></td>
                       <td>${severity}</td>
                       <td>${description}</td></tr>`;
                $("#myTable").append(header);

                for (const interact of interactionConcept) {
                    const {
                        minConceptItem: {rxcui, name: minName, tty}
                        , sourceConceptItem: {id, name: srcName, url}
                    } = interact;

                    const data = `<tr>
                        <td>${rxcui}</td>
                        <td>${minName}</td>
                        <td>${tty}</td>
                        <td>${id}</td>
                        <td>${srcName}</td>
                        <td>${url}</td>
                    </tr>`;

                    $("#myTable").append(data);
                }

            }

        });
});

