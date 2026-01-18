import { Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import DataTable from "./pages/DataTable";
import Profile from "./pages/Profile.jsx";
import {useAuth0} from "@auth0/auth0-react";
import Navbar from "./pages/Navbar.jsx";

function App() {
    const { isAuthenticated, isLoading, error } = useAuth0();

    if (isLoading) {
        return (
            <div className="app-container">
                <div className="loading-state">
                    <div className="loading-text">Loading...</div>
                </div>
            </div>
        );
    }

    if (error) {
        return (
            <div className="app-container">
                <div className="error-state">
                    <div className="error-title">Oops!</div>
                    <div className="error-message">Something went wrong</div>
                    <div className="error-sub-message">{error.message}</div>
                </div>
            </div>
        );
    }

    return (
        <>
            <Navbar />
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/datatable" element={<DataTable />} />
                <Route path="/profile" element={<Profile />} />
            </Routes>
        </>
    );
}

export default App;
