import React, { useState } from 'react';
import { Container, Form, Button, InputGroup, Row, Col } from 'react-bootstrap';
import backgroundVideo from '../videos/homeScreenBg.mp4'; // Ensure this is the correct path to your video file
import './DebtPaymentPlan.css'; // Make sure to create and link this CSS file

const DebtPaymentPlan = () => {
  const [debtDetails, setDebtDetails] = useState({
    balanceOwed: '',
    interestRate: '',
    monthlyPayment: '',
    monthsToPayOff: '',
    paymentOption: 'monthlyPayment', // Default to 'monthlyPayment'
  });

  const handleInputChange = (e) => {
    setDebtDetails({ ...debtDetails, [e.target.name]: e.target.value });
  };

  const handleRadioChange = (e) => {
    setDebtDetails({
      ...debtDetails,
      [e.target.value]: '',
      paymentOption: e.target.value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // Handle the form submission logic here
    console.log(debtDetails);
  };

  return (
    <div className="debt-payment-container">
      <video autoPlay loop muted className="background-video">
        <source src={backgroundVideo} type="video/mp4" />
        Your browser does not support the video tag.
      </video>
      <Container className="debt-payment-content">
        <h2 className="text-white">Debt Repayment Calculator</h2>
        <p className="text-white">See how long it could take to pay off your credit card debt.</p>
        <Form onSubmit={handleSubmit}>
          <Form.Group as={Row} className="mb-3">
            <Form.Label column sm="4" className="text-white">Balanced owed</Form.Label>
            <Col sm="8">
              <Form.Control
                type="number"
                name="balanceOwed"
                value={debtDetails.balanceOwed}
                onChange={handleInputChange}
                min="0"
              />
            </Col>
          </Form.Group>
          
          <Form.Group as={Row} className="mb-3">
            <Form.Label column sm="4" className="text-white">Estimated interest rate</Form.Label>
            <Col sm="8">
              <InputGroup>
                <Form.Control
                  type="number"
                  name="interestRate"
                  value={debtDetails.interestRate}
                  onChange={handleInputChange}
                  min="0"
                  step="0.01"
                />
                <InputGroup.Text>%</InputGroup.Text>
              </InputGroup>
            </Col>
          </Form.Group>

          <Row className="mb-3">
            <Col sm="6">
              <Form.Check 
                type="radio" 
                label="Expected monthly payment" 
                name="paymentOption" 
                value="monthlyPayment" 
                checked={debtDetails.paymentOption === 'monthlyPayment'} 
                onChange={handleRadioChange} 
                className="text-white mb-2"
              />
              <Form.Control
                type="number"
                name="monthlyPayment"
                value={debtDetails.monthlyPayment}
                onChange={handleInputChange}
                disabled={debtDetails.paymentOption !== 'monthlyPayment'}
                min="0"
              />
            </Col>
            <Col sm="6">
              <Form.Check 
                type="radio" 
                label="Desired months to pay off" 
                name="paymentOption" 
                value="monthsToPayOff" 
                checked={debtDetails.paymentOption === 'monthsToPayOff'} 
                onChange={handleRadioChange} 
                className="text-white mb-2"
              />
              <Form.Control
                type="number"
                name="monthsToPayOff"
                value={debtDetails.monthsToPayOff}
                onChange={handleInputChange}
                disabled={debtDetails.paymentOption !== 'monthsToPayOff'}
                min="0"
              />
            </Col>
          </Row>

          <Button variant="success" type="submit" className="w-100">
            Calculate
          </Button>
        </Form>
      </Container>
    </div>
  );
};

export default DebtPaymentPlan;
