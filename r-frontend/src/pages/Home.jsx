import { Link } from "react-router-dom";
import {useAuth0} from "@auth0/auth0-react";
import {useState} from "react";

function Home() {
    const { isAuthenticated } = useAuth0();
    const [lastRefresh, setLastRefresh] = useState("");

    const handleRefresh = async () => {
        try {
            const response = await fetch("http://localhost:8087/refresh", {
                method: "POST",
            });

            if (response.ok) {
                setLastRefresh((new Date).toLocaleString())
            }
        } catch (error) {
            console.error("Error:", error);
            alert("An error occurred while refreshing");
        }
    };

    return (
        <div className="container index">
            <h1>Skup podataka o muzejima</h1>

            <p>
                Ovaj skup podataka obuhvaća osnovne informacije o muzejima diljem svijeta,
                uključujući njihove lokacije, vrstu i opseg kolekcije, godišnju
                posjećenost te dodatne digitalne resurse poput web sjedišta i virtualne
                šetnje.
            </p>

            <p><strong>Jezik skupa:</strong> hrvatski</p>
            <p><strong>Datum objave:</strong> 2024-10-27</p>
            <p><strong>Licencija:</strong> Creative Commons Zero v1.0 Universal</p>
            <p><strong>Ključne riječi:</strong> muzej, posjetitelj, eksponat</p>

            <hr />

            <p>Preuzmite cijeli skup podataka:</p>

            <div className="btns-container">
                <a href="http://localhost:8087/export/csv" className="btn">Preuzmi CSV</a>
                <a href="http://localhost:8087/export/json" className="btn">Preuzmi JSON</a>
            </div>
            {isAuthenticated && (
                <a
                    className="btnRefresh"
                    onClick={(e) => {
                        e.preventDefault();
                        handleRefresh();
                    }}
                    title="Osvježi preslike"
                >
                    <img
                        id="refresh" src="/data/Refresh_icon.svg.png"
                        alt="Refresh"
                        style={{ cursor: "pointer" }}
                    />
                    Osvježi preslike
                </a>

            )}

            {lastRefresh && (
                <>
                <br/>
                <p><i>Preslike uspješno osvježene: {lastRefresh}</i></p>
                </>
            )}
            <hr />
            <div className="navigation-link">
                <p>
                    Želite li filtrirati podatke? Posjetite{" "}
                    <Link to="/datatable" className="link">
                        stranicu za filtriranje podataka
                    </Link>.
                </p>
            </div>
        </div>
    );
}

export default Home;
