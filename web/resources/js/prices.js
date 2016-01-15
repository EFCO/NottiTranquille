$(document).ready(function()
    {
        // Sets table sorter
        $("#prices-table").tablesorter({headers: { 5:{sorter: false}}});

        // Sets data picker
        $('.input-group.date').datepicker({
            format: "dd/mm/yyyy",
            autoclose: true
        });
    }
);