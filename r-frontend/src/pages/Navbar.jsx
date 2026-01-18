import { Link } from "react-router-dom";
import { useAuth0 } from "@auth0/auth0-react";
import LoginButton from "../pages/LoginButton";
import LogoutButton from "../pages/LogoutButton";

export default function Navbar() {
    const { isAuthenticated, isLoading } = useAuth0();

    if (isLoading) return null;

    return (
        <nav className="navbar">
            <div className="nav-left">
                <Link to="/" className="nav-link">
                    MuseumsApp
                </Link>

                <Link to="/datatable" className="nav-link">
                    Pregled podataka
                </Link>
            </div>

            <div className="nav-right">
                {isAuthenticated && (
                    <Link to="/profile" className="nav-link">
                        <img
                            src="/data/avatar.png"
                            alt='User'
                            className="profile-picture"
                        />
                        Korisnički profil
                    </Link>
                )}
                {isAuthenticated ? <LogoutButton /> : <LoginButton />}
            </div>
        </nav>
    );
}
