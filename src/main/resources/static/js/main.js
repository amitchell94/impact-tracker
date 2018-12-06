function ongoingCheck() {

    var dateDiv = document.getElementById("end-date-row");
    var checkBox = document.getElementById("ongoing");
    var endDateInput = document.getElementById("enddate")

    if (checkBox.checked) {
        dateDiv.hidden = true;
        endDateInput.value = '';
    } else {
        dateDiv.hidden = false;
    }
}

function dateCheck() {

    var startDateInput = document.getElementById("startdate");
    var endDateInput = document.getElementById("enddate");
    var checkBox = document.getElementById("ongoing");

    if (endDateInput.value < startDateInput.value) {
        if (!checkBox.checked) {
            endDateInput.value = startDateInput.value;
        }
    }

}

function impactTime() {

    var reduction = document.getElementById("reduction");

    switch (reduction.value) {
        case "total":
            window.location.replace('/impacts');
            break;
        case "week":
            window.location.replace('/impacts/week');
            break;
        case "month":
            window.location.replace('/impacts/month');
            break;
        case "year":
            window.location.replace('/impacts/year');
            break;
    }
}