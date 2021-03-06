
function updateOnLoad() {
    var dateDiv = document.getElementById("end-date-row");
    var checkBox = document.getElementById("ongoing");
    var endDateInput = document.getElementById("enddate")

    if (endDateInput.value != '' && endDateInput.value != null) {
        dateDiv.hidden = false;
        endDateInput.required = true;
        checkBox.checked = false;
    } else {
        dateDiv.hidden = true;
        endDateInput.value = '';
        endDateInput.required = false;
    }

    var reduction = document.getElementById("reduction");
    var amountInput = document.getElementById("amountInput");
    var amountUnit = document.getElementById("unit");
    var amount = document.getElementById("amount");
    var amountSelect = document.getElementById("amountselect");
    var customAmountInput = document.getElementById("customamount");

    if (reduction.options[reduction.selectedIndex].dataset.average == 1.0) {
        amountInput.hidden = true;
        amount.value = '';
        amount.required = false;
    } else {
        amountInput.hidden = false;
        amountUnit.innerText = reduction.options[reduction.selectedIndex].dataset.unit + "/day";

        if (amount.value != '' && amount.value != null) {
            amountSelect.value = 'custom';
            customAmountInput.hidden = false;
            amount.required = true;
        } else {
            amountSelect.value = 'average';
            amount.value = '';
            amount.required = false;
        }
    }
}

function ongoingCheck() {

    var dateDiv = document.getElementById("end-date-row");
    var checkBox = document.getElementById("ongoing");
    var endDateInput = document.getElementById("enddate")

    if (checkBox.checked) {
        dateDiv.hidden = true;
        endDateInput.value = '';
        endDateInput.required = false;
    } else {
        dateDiv.hidden = false;
        endDateInput.required = true;
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

function amountCheck() {
    var reduction = document.getElementById("reduction");
    var amountInput = document.getElementById("amountInput");
    var amountUnit = document.getElementById("unit");
    var amount = document.getElementById("amount");

    if (reduction.options[reduction.selectedIndex].dataset.average == 1.0) {
        amountInput.hidden = true;
        amount.value = '';
        amount.required = false;
    } else {
        amountInput.hidden = false;
        amountUnit.innerText = reduction.options[reduction.selectedIndex].dataset.unit + "/day";
    }
}

function averageAmountCheck() {
    var reduction = document.getElementById("reduction");
    var amountSelect = document.getElementById("amountselect");
    var amountInput = document.getElementById("customamount");
    var amountUnit = document.getElementById("unit");
    var amount = document.getElementById("amount");

    if (amountSelect.value == 'average') {
        amountInput.hidden = true;
        amount.value = '';
        amount.required = false;
    } else {
        amountInput.hidden = false;
        amountUnit.innerText = reduction.options[reduction.selectedIndex].dataset.unit + "/day";
        amount.required = true;
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