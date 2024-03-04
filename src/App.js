import React from 'react';
import './styles/styles.css'; // Import the styles.css file
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './components/Home';
import Registration from './components/Registration';
import Login from './components/Login';
import ZipCodePage from './components/ZipCodePage'; // Corrected file path

function App() {
  return (
    <Router>
      <div>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/register" element={<Registration />} />
          <Route path="/login" element={<Login />} />
          <Route path="/zipcode" element={<ZipCodePage />} /> {/* Updated route for ZipCodePage */}
        </Routes>
      </div>
      <div className="App">
      {/* Your app content */}
    </div>
    </Router>
  );
}

export default App;
