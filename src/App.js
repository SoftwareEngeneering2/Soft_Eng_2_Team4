import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';

// add the import of App.css here
import NavigationBar from './components/Navbar';
import DebtPaymentPlan from './pages/DebtPaymentPlan';
import Home from './pages/Home';
import LogIn from './pages/LogIn';
import SavingsPlan from './pages/SavingsPlan';
import SignUp from './pages/SignUp';
// Import other pages here

function App() {
  return (
    <BrowserRouter>
      <NavigationBar />
      <Routes>
        <Route path="/" element={<Home/>} />
        <Route path="/savings-plan" element={<SavingsPlan/>} />
        <Route path="/debt-payment-plan" element={<DebtPaymentPlan/>} />
        <Route path="/login" element={<LogIn/>} />
        <Route path="/signup" element={<SignUp/>} />
        {/* Define other routes here */}
      </Routes>
    </BrowserRouter>
  );
}

export default App;
