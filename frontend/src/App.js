import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import "./App.css";
import Home from "./screens/Home";
import Market from "./screens/Market";
import Profile from "./screens/Profile";
import Navbar from "./screens/Navbar";
import { AccountProvider } from "./context/Account.js";

function App() {
  return (
    <AccountProvider>
      <Router>
        <div className="App">
          <Navbar />
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/market" element={<Market />} />
            <Route path="/profile" element={<Profile />} />
          </Routes>
        </div>
      </Router>
    </AccountProvider>
  );
}

export default App;
