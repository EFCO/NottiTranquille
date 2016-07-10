$(document).ready(
    function() {

        // Sets dates into modal
        function setDateModal() {
            var today = new Date();
            var day = today.getDate();
            var month = today.getMonth() + 1;
            var year = today.getFullYear();
            if (day < 10) {
                day = "0" + day;
            }
            if (month < 10) {
                month = "0" + month;
            }
            var todayString = day + "/" + month + "/" + year;

            $("#input-start-date").val(todayString);
            $("#input-end-date").val(todayString);
        }
        
        setDateModal();
        
        // Sets table sorter
        $("#prices-table").tablesorter({headers: { 6:{sorter: false}}});

        // Sets data picker
        $('.input-group.date').datepicker({
            format: "dd/mm/yyyy",
            autoclose: true
        });

        // Changes input price value following user selection
        function changePercentageFix(value) {
            if (stringStartsWith(value, "percentage")) {
                $('#addon-input-price').html("%");
                $('#input-price').attr("placeholder", "Percentage");
            } else {
                $('#addon-input-price').html("€");
                $('#input-price').attr("placeholder", "Price");
            }
        }

        function addSelectDays(selectedDays) {
            var days = ["MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"];
            var daysShort = ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"];


            if ($("#div-select-days").length == 0) {
                $('<div id="div-select-days" class="form-group"></div>').insertAfter("#div-repeat");

                var div = $('#div-select-days');
                var counter;
                for (counter = 0; counter < 7; counter++) {
                    var check = "";
                    if ($.inArray(days[counter], selectedDays) != -1) {
                        check = "checked";
                    }

                    div.append(' <label class="checkbox-inline"><input name="daysWeek" type="checkbox" value=' + days[counter] + ' ' + check + '> ' + daysShort[counter] + ' </label>');
                }
            }
        }

        $('#price-type-div').find('input[type="radio"]').on('click change', function(e) {
            var element = e.toElement;
            if (element != undefined) {
                changePercentageFix(element.value);
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
        $('#div-repeat-select').find('select[name="repetitionType"]').on('change', function() {

            // Gets the element selected
            var element = this.selectedOptions[0];


            var divTimes = $("#div-times");

            // Gets select with name times
            var times = $("select[name='times']");

            // Removes all options
            times.find("option").remove();

            var counter = 1;

            var generateOptions = function(data) {
                for (counter; counter < data; counter++) {
                    times.append($("<option></option>").text(counter));
                }
            };

            function removeSelectDays(select) {
                $("#div-select-days").remove();
            }

            if (element.value == "everyDay") {
                generateOptions(maxRepetitionDay);
                removeSelectDays();
                divTimes.show();
            } else if (element.value == "everyWeek") {
                generateOptions(maxRepetitionWeek);
                addSelectDays();
                divTimes.show();
            } else if (element.value == "everyMonth") {
                generateOptions(maxRepetitionMonth);
                removeSelectDays();
                divTimes.show();
            } else if (element.value == "everyYear") {
                generateOptions(maxRepetitionYear);
                removeSelectDays();
                divTimes.show();
            } else if (element.value == "everyWeekend") {
                removeSelectDays();
                divTimes.hide();
            } else if (element.value == "everyWorkday") {
                removeSelectDays();
                divTimes.hide();
            } else if (element.value == "everyNoWorkday") {
                removeSelectDays();
                divTimes.hide();
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

        $(document).on("click", ".deletePrice", function() {
            var priceId = $(this).data('id');
            $(".modal-body #price-id").val(priceId);
        });

        $(document).on("click", ".updatePrice", function() {

            $(".modal-header #createModalLabel").text("Update price");
            $(".modal-footer #create").attr("name", "update");
            $(".modal-footer #create").attr("value", "update");
            $(".modal-footer #create").text("Update price");
            
            var priceId = $(this).data('id');
            var repetitionType = toCamelCase($(this).data('repetitiontype'));
            console.log(repetitionType);
            var priceType = $(this).data('pricetype').charAt(0).toLowerCase() + $(this).data('pricetype').slice(1);
            var times = $(this).data('times');
            var value = $(this).data('value');
            var startDate = $(this).data('startdate');
            var comment = $(this).data('comment');
            var endDate = $(this).data('enddate');
            var occurrences = $(this).data('occurrences');

            var rawDays = $(this).data('days');

            console.log("all'aggiornamento days = " + rawDays);
            if (rawDays.length != 0) {
                var days = (rawDays).slice(1, -1).replace(/ /g, '').split(',');
                addSelectDays(days);
            }
            
            console.log("all'aggiornamento days = " + days);

            changePercentageFix(priceType);

            $(".modal-body input[name=id]").val(priceId);
            $(".modal-body input[name=priceType][value=" + priceType + "]").prop('checked', true);
            $(".modal-body input[name=value]").val(value);
            $(".modal-body input[name=startDate]").val(startDate);
            $(".modal-body input[name=comment]").val(comment);
            $(".modal-body select[name=repetitionType]").val(repetitionType);

            if (endDate != "31/12/9999") {
                $(".modal-body input[name=option-radio-end][value=endDate]").prop('checked', true);
                $(".modal-body input[name=endDate]").val(endDate);
            } else {
                $(".modal-body input[name=option-radio-end][value=never]").prop('checked', true);
            }

            refreshSummaryString();
        });

        // Reset modal after close
        $("#createModal").on('hidden.bs.modal', function() {
            $("#createModal .modal-body").html(createModalHTML);
            setDateModal();
        });
    }
);

function toCamelCase(str) {
    // Lower cases the string
    return str.toLowerCase()
    // Replaces any - or _ characters with a space 
        .replace( /[-_]+/g, ' ')
        // Removes any non alphanumeric characters 
        .replace( /[^\w\s]/g, '')
        // Uppercases the first character in each group immediately following a space 
        // (delimited by spaces) 
        .replace( / (.)/g, function($1) { return $1.toUpperCase(); })
        // Removes spaces 
        .replace( / /g, '' );
}


function stringStartsWith(string, prefix) {
    return string.slice(0, prefix.length) == prefix;
}

function stringEndsWith(string, suffix) {
    return suffix == '' || string.slice(-suffix.length) == suffix;
}
