let startData = {};
let actualData = {};

function fillTable(data) {
    const tableBody = document.querySelector('table tbody');
    tableBody.innerHTML = '';

    data.forEach(museum => {
        const row = document.createElement('tr');
        
        row.innerHTML = `
            <td>${museum.nazivMuzeja}</td>
            <td>${museum.drzava}</td>
            <td>${museum.grad}</td>
            <td>${museum.godinaOsnivanja}</td>
            <td>${museum.tipMuzeja}</td>
            <td>${museum.velicinaKolekcije}</td>
            <td>${museum.posjetitelji}</td>
            <td>${museum.izlozbeniProstor}</td>
            <td><a href="${museum.webStranica}" target="_blank">link</a></td>
            <td>${museum.onlineSetnja ? 'Da' : 'Ne'}</td>
            ${museum.eksponati.map(eksponat => `<td>${eksponat.nazivEksponata}, ${eksponat.tipEksponata}</td>`).join('')}
        `;
        
        tableBody.appendChild(row);
    });
}

async function initTable() {
    let response;

    try {
        response = await fetch('http://localhost:8087/start');
        
        if (!response.ok) {
            throw new Error('Backend response was not ok');
        }
    } catch (error) {
        console.error('There was a problem with the fetch operation: ', error);
        return;
    }

    const museums = await response.json();
    startData = museums;
    actualData = museums;

    fillTable(museums);
}

async function filterData() {
    let response;

    const selectedColumn = document.getElementById('atribut').value;

    const filterText = document.getElementById('pretraga').value;

    if (filterText === '') {
        fillTable(startData);
        actualData = startData;
        return;
    }

    try {
        if (selectedColumn === 'wildcard') {
            response = await fetch(`http://localhost:8087/filter/wildcard?term=${filterText}`);

        } else {
            response = await fetch(`http://localhost:8087/filter?column=${selectedColumn}&term=${filterText}`);
            
        }
        
        if (!response.ok) {
            throw new Error('Backend response was not ok');
        }
        
    } catch (error) {
        console.error('There was a problem with the fetch operation: ', error);
        return;
    }

    const museums = await response.json();
    actualData = museums;

    fillTable(museums);
}

function downloadJSON() {
    
    const jsonString = JSON.stringify(actualData, null, 2);
    const blob = new Blob([jsonString], { type: "application/json" });
    const url = URL.createObjectURL(blob);

    const a = document.createElement("a");
    a.href = url;
    a.download = "muzeji.json"; 
    document.body.appendChild(a);
    a.click();

    document.body.removeChild(a);
    URL.revokeObjectURL(url);
}

function formatComma(value) {
    const strValue = String(value);
    return strValue.includes(",") ? `"${strValue}"` : strValue;
}

function downloadCSV() {
    let csv = "nazivMuzeja,drzava,grad,godinaOsnivanja,tipMuzeja,velicinaKolekcije,posjetitelji,izlozbeniProstor,webStranica,onlineSetnja,nazivEksponata,tipEksponata\n";

    actualData.forEach(museum => {
        museum.eksponati.forEach(exhibit => {
            csv += `${formatComma(museum.nazivMuzeja)},${formatComma(museum.drzava)},${formatComma(museum.grad)},${formatComma(museum.godinaOsnivanja)},${formatComma(museum.tipMuzeja)},${formatComma(museum.velicinaKolekcije)},${formatComma(museum.posjetitelji)},${formatComma(museum.izlozbeniProstor)},${formatComma(museum.webStranica)},${formatComma(museum.onlineSetnja)},${formatComma(exhibit.nazivEksponata)},${formatComma(exhibit.tipEksponata)}\n`;
        });
    });

    const blob = new Blob([csv], { type: "text/csv;charset=utf-8;" });
    const url = URL.createObjectURL(blob);

    const a = document.createElement("a");
    a.href = url;
    a.download = "muzeji.csv"; 
    document.body.appendChild(a);
    a.click();

    document.body.removeChild(a);
    URL.revokeObjectURL(url);
}

document.addEventListener("DOMContentLoaded", initTable);
document.getElementById("download-json").addEventListener("click", downloadJSON);
document.getElementById("download-csv").addEventListener("click", downloadCSV);

