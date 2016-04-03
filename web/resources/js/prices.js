$(document).ready(
    function() {
        // Sets table sorter
        $("#prices-table").tablesorter({headers: { 5:{sorter: false}}});

        // Sets data picker
        $('.input-group.date').datepicker({
            format: "dd/mm/yyyy",
            autoclose: true
        });

        // Changes input price value following user selection
        $('#price-type-div').find('input[type="radio"]').on('click change', function(e) {
            var element = e.toElement;
            if (element != undefined) {
                if (stringStartsWith(element.value, "percentage")) {
                    $('#addon-input-price').html("%");
                    $('#input-price').attr("placeholder", "Percentage");
                } else {
                    $('#addon-input-price').html("€");
                    $('#input-price').attr("placeholder", "Price");
                }
            }
        });

        // Changes input required following user selection
        $('#end-div').find('input[type="radio"]').on('click change', function(e) {
            var element = e.toElement;
            if (element != undefined) {
                if (element.value == "endDate") {
                    $('#input-end-date').prop('required', true);
                    $('#input-occurrences').prop('required', false);
                } else if (element.value == "occurrences") {
                    $('#input-occurrences').prop('required', true);
                    $('#input-end-date').prop('required', false);
                } else {
                    $('#input-occurrences').prop('required', false);
                    $('#input-end-date').prop('required', false);
                }
            }
        });

        // Changes times following user selection
        $('#div-repeat-select').find('select').on('change', function () {

            // Gets the element selected
            var element = this.selectedOptions[0];

            // Gets select with name times
            var times = $("select[name='times']");

            // Removes all options
            times.find("option").remove();

            var counter = 1;
            var replyString;

            var generateOptions = function(data) {
                if (data != null && typeof data == 'object') {
                    replyString = dwr.util.toDescriptiveString(data, 2);
                } else {
                    replyString = dwr.util.toDescriptiveString(data, 1);
                }
                for (counter; counter < parseInt(replyString); counter++) {
                    times.append($("<option></option>").text(counter));
                }
            };

            function addSelectDays(select) {
                $('<div id="div-select-days" class="form-group"><label for="input-price">Days</label></div>').insertAfter("#div-repeat");

                var div = $('#div-select-days');
                if (select == 1) {
                    var counter;
                    for (counter = 0; counter <=7; counter++) {
                        div.append('<label class="checkbox-inline"><input type="checkbox" id="inlineCheckbox1" value="option1"> 1 </label>');
                    }
                }

            }

            if (element.value == "everyDays") {
                PriceBean.getMaxRepetitionDays(generateOptions);
            } else if (element.value == "everyWeeks") {
                PriceBean.getMaxRepetitionWeeks(generateOptions);
                addSelectDays();


            } else if (element.value == "everyMonths") {
                PriceBean.getMaxRepetitionMonths(generateOptions);
            } else if (element.value == "everyYears") {
                PriceBean.getMaxRepetitionYears(generateOptions);
            } else if (element.value == "everyWeekEnds") {
                PriceBean.getMaxRepetitionWeekends(generateOptions);
            } else if (element.value == "everyWorkDays") {
                PriceBean.getMaxRepetitionWorkdays(generateOptions);
            } else if (element.value == "everyNoWorkDays") {
                PriceBean.getMaxRepetitionNoWorkdays(generateOptions);
            }

            // Selects the first option
            times.find('option:eq(0)').attr("selected", "selected");
        });

        // Creates the summary string of the repetition
        function refreshSummaryString() {
            var startDate = $('#input-start-date').val();
            var times = $('select[name=times] option:selected').text();
            if (times == "") {
                times = "1";
            }
            var timeType = $('select[name=repetitionType] option:selected').text().split(' ')[1].toLocaleLowerCase();
            var summary = "Repeat every " + times + " " + timeType + " from " + startDate;

            var endType = $('#end-div').find('input[type="radio"]:checked').val();
            if (endType == "occurrences") {
                summary = summary + ", " + $('#input-occurrences').val() + " occurrences";
            } else if (endType == "never") {
                summary = summary + ""
            } else {
                var endDate = $('#input-end-date').val();
                summary = summary + " to " + endDate;
            }

            summary = summary + ".";

            $('#summary').html(summary);
        }

        // Refresh summary string every time something change into create modal
        $('#createModal').on('change', refreshSummaryString);

        // Create summary string
        refreshSummaryString();
    }
);

function stringStartsWith(string, prefix) {
    return string.slice(0, prefix.length) == prefix;
}

function stringEndsWith(string, suffix) {
    return suffix == '' || string.slice(-suffix.length) == suffix;
}
