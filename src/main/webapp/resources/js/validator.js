"use strict";

let x, y, r;

//Обновляет значение x в соответсвии с нажатой кнопкой, добавляет ей эффекты (подсветка и увеличение), убирая их для остальных кнопок группы.
document.addEventListener("DOMContentLoaded", () => {
    let buttons = document.querySelectorAll("input[type=button]");
    buttons.forEach(click);
    function click(element) {
        element.onclick = function () {
            r = this.value;
            buttons.forEach(function (element) {
                element.style.boxShadow = null;
                element.style.backgroundColor = null;
                element.style.color = null;
            });
            this.style.boxShadow = "0 0 40px 5px #f41c52";
            this.style.color = "white";
            redrawGraph();
        }
    }
});

document.getElementById("checkButton").onclick = function () {
    if (validateX() && validateY() && validateR()) {
        sendRequest([{name:"X-field", value:x}, {name:"Y-field", value:y}, {name:"R-field", value:r}]);
        redrawGraph();
    }
};

function createNotification(message) {
    let outputContainer = document.getElementById("outputContainer");
    if (outputContainer.contains(document.querySelector(".notification"))) {
        let stub = document.querySelector(".notification");
        stub.textContent = message;
        stub.classList.replace("outputStub", "errorStub");
    } else {
        let notificationTableRow = document.createElement("h4");
        notificationTableRow.innerHTML = "<span class='notification errorStub'></span>";
        outputContainer.prepend(notificationTableRow);
        let span = document.querySelector(".notification");
        span.textContent = message;
    }
}

function validateX() {
    if (isNumeric(x)) return true;
    else createNotification("X не выбран");
}

//Позволяет получить значение x из нестандартного элемента p:oneSelectRadio
function reactToChangeXRadio(param) {
    x = param;
}

function validateY() {
    y = document.querySelector("#Y-field").value.replace(",", "."); //замена разделителя дробной части числа
    if (y === undefined || y === null) {
        createNotification("Y не введён");
        return false;
    } else if (!isNumeric(y)) {
        createNotification("Y не число");
        return false;
    } else if (!((y > -5) && (y < 3))) {
        createNotification("Y не входит в область допустимых значений");
        return false;
    } else return true;
}

function validateR() {
    if (isNumeric(r)) return true;
    else {
        createNotification("R не выбран");
        return false;
    }
}

function isNumeric(n) {
    return !isNaN(parseFloat(n)) && isFinite(n);
}