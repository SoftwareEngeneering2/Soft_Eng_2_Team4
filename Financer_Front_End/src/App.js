import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';

// add the import of App.css here
import NavigationBar from './components/Navbar';
import DebtPaymentPlan from './pages/DebtPaymentPlan';
import Home from './pages/Home';
import LogIn from './pages/LogIn';
import LoggedIn from './pages/LoggedIn';
import SavingsPlan from './pages/SavingsPlan';
import SignUp from './pages/SignUp';
import SignedUp from './pages/signedup';
import AppDownload from './components/AppDownload';
import RequestResetPassword from './pages/RequestResetPassword';
import ResetRequestSent from './pages/ResetRequestSent';
import ResetPasswordForm from './pages/ResetPasswordForm';
import ResetPasswordSuccess from './pages/ResetPasswordSuccess';
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
        <Route path="/logged-in" element={<LoggedIn/>} />
        <Route path="/signup" element={<SignUp/>} />
        <Route path="/download-app" element={<AppDownload/>} /> 
        <Route path="/signed-up" element={<SignedUp/>} /> 
        <Route path="/reset-request" element={<RequestResetPassword/>} /> 
        <Route path="/reset-link-sent" element={<ResetRequestSent/>} />
        <Route path="/reset-password" element={<ResetPasswordForm/>} />
        <Route path="/reset-password-success" element={<ResetPasswordSuccess/>} />

        {/* Define other routes here */}
      </Routes>
    </BrowserRouter>
  );
}

export default App;
