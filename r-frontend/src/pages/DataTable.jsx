import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { makeCsv } from "../utils/helpers.js";

function DataTable() {
    const [startData, setStartData] = useState([]);
    const [actualData, setActualData] = useState([]);
    const [filterText, setFilterText] = useState("");
    const [column, setColumn] = useState("wildcard");

    useEffect(() => {
        fetch("http://localhost:8087/start")
            .then(res => {
                if (!res.ok) throw new Error("Fetch failed");
                return res.json();
            })
            .then(data => {
                setStartData(data);
                setActualData(data);
            })
            .catch(err => console.error(err));
    }, []);

    async function filterData() {
        if (!filterText) {
            setActualData(startData);
            return;
        }

        const url =
            column === "wildcard"
                ? `http://localhost:8087/filter/wildcard?term=${filterText}`
                : `http://localhost:8087/filter?column=${column}&term=${filterText}`;

        const res = await fetch(url);
        const data = await res.json();
        setActualData(data);
    }

    function downloadJSON() {
        const json = JSON.stringify(actualData, null, 2);
        const blob = new Blob([json], { type: "application/json" });
        const url = URL.createObjectURL(blob);

        const a = document.createElement("a");
        a.href = url;
        a.download = "muzeji.json";
        a.click();
        URL.revokeObjectURL(url);
    }

    function downloadCSV() {
        let csv = makeCsv(actualData);

        const blob = new Blob([csv], { type: "text/csv;charset=utf-8;" });
        const url = URL.createObjectURL(blob);

        const a = document.createElement("a");
        a.href = url;
        a.download = "muzeji.csv";
        a.click();
        URL.revokeObjectURL(url);
    }

    return (
        <div className="container datatable">
            <h1>Filtriranje podataka</h1>

            {/* FILTER FORM */}
            <form onSubmit={e => e.preventDefault()}>
                <label>Odaberite stupac za filtriranje:</label>
                <select id="select-column" value={column} onChange={e => setColumn(e.target.value)}>
                    <option value="wildcard">Svi stupci (wildcard)</option>
                    <option value="nazivMuzeja">Naziv muzeja</option>
                    <option value="drzava">Država</option>
                    <option value="grad">Grad</option>
                    <option value="godinaOsnivanja">Godina osnivanja</option>
                    <option value="tipMuzeja">Tip muzeja</option>
                    <option value="velicinaKolekcije">Veličina kolekcije</option>
                    <option value="posjetitelji">Broj posjetitelja</option>
                    <option value="izlozbeniProstor">Izložbeni prostor</option>
                    <option value="webStranica">Web sjedište</option>
                    <option value="onlineSetnja">Online šetnja</option>
                    <option value="nazivEksponata">Naziv eksponata</option>
                    <option value="tipEksponata">Tip eksponata</option>
                </select>

                <br /><br />

                <label>Kriterij za filtriranje:</label>
                <input
                    id="pretraga"
                    type="text"
                    placeholder="Unesite tekst..."
                    value={filterText}
                    onChange={e => setFilterText(e.target.value)}
                />

                <button type="button" onClick={filterData}>
                    Filtriraj
                </button>
            </form>

            {/* TABLE */}
            <div id="data-table">
                <table id="tablica">
                    <thead>
                    <tr>
                        <th>Naziv</th>
                        <th>Država</th>
                        <th>Grad</th>
                        <th>GOSN*</th>
                        <th>Tip</th>
                        <th>VKOL*</th>
                        <th>BRPOS*</th>
                        <th>IZLPR*</th>
                        <th>Web</th>
                        <th>OLŠET*</th>
                        <th>Eksponat #1</th>
                        <th>Eksponat #2</th>
                    </tr>
                    </thead>
                    <tbody>
                    {actualData.map((m, i) => (
                        <tr key={i}>
                            <td>{m.nazivMuzeja}</td>
                            <td>{m.drzava}</td>
                            <td>{m.grad}</td>
                            <td>{m.godinaOsnivanja}</td>
                            <td>{m.tipMuzeja}</td>
                            <td>{m.velicinaKolekcije}</td>
                            <td>{m.posjetitelji}</td>
                            <td>{m.izlozbeniProstor}</td>
                            <td>
                                <a href={m.webStranica} target="_blank" rel="noreferrer">
                                    link
                                </a>
                            </td>
                            <td>{m.onlineSetnja ? "Da" : "Ne"}</td>
                            {m.eksponati.slice(0, 2).map((e, idx) => (
                                <td key={idx}>
                                    {e.nazivEksponata}, {e.tipEksponata}
                                </td>
                            ))}
                        </tr>
                    ))}
                    </tbody>
                </table>
            </div>

            {/* LEGEND */}
            <div className="legend-container">
                <strong>Legenda:</strong>
                <p className="legend">
                    <strong>GOSN</strong> – Godina osnivanja muzeja<br />
                    <strong>VKOL</strong> – Veličina kolekcije<br />
                    <strong>BRPOS</strong> – Broj posjetitelja (2023.)<br />
                    <strong>IZLPR</strong> – Izložbeni prostor (m²)<br />
                    <strong>OLŠET</strong> – Online šetnja
                </p>
            </div>

            <div className="divider"></div>

            {/* DOWNLOADS */}
            <div className="download-links">
                <p>Preuzmite filtrirani skup podataka:</p>
                <a href="#" onClick={downloadCSV} className="btn">
                    Preuzmi CSV
                </a>
                <a href="#" onClick={downloadJSON} className="btn">
                    Preuzmi JSON
                </a>
            </div>

            {/* NAVIGATION */}
            <div className="navigation-link">
                <p>
                    Povratak na <Link to="/" className="link">glavnu stranicu</Link>.
                </p>
            </div>
        </div>
    );
}

export default DataTable;
