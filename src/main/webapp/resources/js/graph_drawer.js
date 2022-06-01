"use strict";

const svg = document.getElementById("graph");
const rect = document.getElementById("rect");
const triangle = document.getElementById("triangle");
const path = document.getElementById("path");
const status = 3; // number of colum with status

document.addEventListener("DOMContentLoaded", () => {
    svg.addEventListener("click", event => {
        if (validateR()) {
            let position = getMousePosition(svg, event);
            x = ((position.x - 150) / 125 * r).toFixed(6);
            y = ((150 - position.y) / 125 * r).toFixed(6);
            sendRequest([{name:"X-field", value:x}, {name:"Y-field", value:y}, {name:"R-field", value:r}]);
            redrawGraph();
            loadDots();
            redrawGraph();
        }
    });
});

function getMousePosition(svg, event) {
    let rect = svg.getBoundingClientRect();
    return {
        x: event.clientX - rect.left,
        y: event.clientY - rect.top
    };
}

function redrawGraph() {
    let outputContainer = document.getElementById("outputContainer");
    if (outputContainer !== null) outputContainer.parentNode.removeChild(outputContainer);
    // rect.setAttribute("width", `${r * 12}`);
    // rect.setAttribute("height", `${r * 24}`);
    // rect.setAttribute("y", `${150 - r * 24}`);
    // triangle.setAttribute("points", `150,150 150,${150 + 25 * r} ${150 + 25 * r},150`);
    // path.setAttribute("d", `M 150 150 L ${150 - r*12.5} 150 A ${r*12.5} ${r*12.5} 0 0 0 150 ${150 + r*12.5} L 150 150`);
    let texts = document.getElementsByClassName("r-gr");
    for (let i = 0; i < texts.length; i++) {
        texts.item(i).innerHTML = r;
    }
    setTimeout(loadDots, 400); //нужно дождаться получения dom с обновлённой таблицей, иначе новая точка не появляется на графике - МЕГАКОСТЫЛЬ
}

function loadDots() {
    let oldDots = document.querySelectorAll("circle");
    oldDots.forEach(dot => dot.parentNode.removeChild(dot));
    let table = document.getElementById("resTable");
    for (let i = 1; table.rows.length; ++i) {
        let cells = table.rows.item(i).cells;
        let dotCoords = {
            x: parseFloat(cells.item(0).innerHTML.trim())/r * 125 + 150,
            y: 150 - parseFloat(cells.item(1).innerHTML.trim()) / r * 125
        };
        let dot = document.createElementNS("http://www.w3.org/2000/svg", "circle");
        dot.setAttribute("r", "5");
        dot.setAttribute("cx", `${dotCoords.x}`);
        dot.setAttribute("cy", `${dotCoords.y}`);
        dot.setAttribute("fill", cells.item(status).innerHTML.trim() === "true" ? "green" : "red");
        svg.appendChild(dot);
    }
}