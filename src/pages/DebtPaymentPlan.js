import React, { useState } from 'react';
import { Container, Form, Button, InputGroup, Row, Col } from 'react-bootstrap';
import backgroundVideo from '../videos/homeScreenBg.mp4';
import './DebtPaymentPlan.css';

const DebtPaymentPlan = () => {
  const [principal, setPrincipal] = useState('');
  const [interest, setInterest] = useState('');
  const [monthlyPayment, setMonthlyPayment] = useState('');
  const [monthsToPayOff, setMonthsToPayOff] = useState('');
  const [paymentOption, setPaymentOption] = useState('monthlyPayment');
  const [result, setResult] = useState('');

  const handleRadioChange = (e) => {
    setPaymentOption(e.target.value);
    if (e.target.value === 'monthlyPayment') {
      setMonthsToPayOff('');
    } else {
      setMonthlyPayment('');
    }
  };

  const calculateResult = () => {
    // Convert inputs to numbers
    const principalAmount = parseFloat(principal);
    const interestRate = parseFloat(interest);
    const payload = {
      principal: principalAmount,
      interest: interestRate,
    };

    let endpoint = '';
    if (paymentOption === 'monthlyPayment') {
      payload.monthlyPayment = parseFloat(monthlyPayment);
      endpoint = '/api/v1/debt_payoff/months';
    } else {
      payload.totalMonths = parseInt(monthsToPayOff);
      endpoint = '/api/v1/debt_payoff/monthly_payment';
    }

    // Make HTTP POST request to the Spring Boot backend
    fetch(endpoint, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(payload),
    })
    .then((response) => {
      if (!response.ok) {
        throw new Error('Error: ' + response.status);
      }
      return response.json();
    })
    .then((data) => {
      setResult(typeof data === 'string' ? data : JSON.stringify(data));
    })
    .catch((error) => {
      console.error('Error:', error);
      setResult('Failed to calculate. Please try again.');
    });
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
        <Form>
          <Form.Group as={Row} className="mb-3">
            <Form.Label column sm="4" className="text-white">Principal Amount ($):</Form.Label>
            <Col sm="8">
              <Form.Control
                type="number"
                value={principal}
                onChange={(e) => setPrincipal(e.target.value)}
                min="0"
              />
            </Col>
          </Form.Group>
  
          <Form.Group as={Row} className="mb-3">
            <Form.Label column sm="4" className="text-white">Annual Interest Rate (%):</Form.Label>
            <Col sm="8">
              <InputGroup>
                <Form.Control
                  type="number"
                  value={interest}
                  onChange={(e) => setInterest(e.target.value)}
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
                checked={paymentOption === 'monthlyPayment'}
                onChange={handleRadioChange}
                className="text-white"
              />
              <Form.Control
                type="number"
                name="monthlyPayment"
                value={monthlyPayment}
                onChange={(e) => setMonthlyPayment(e.target.value)}
                disabled={paymentOption !== 'monthlyPayment'}
                min="0"
              />
            </Col>
            <Col sm="6">
              <Form.Check 
                type="radio"
                label="Desired months to pay off"
                name="paymentOption"
                value="monthsToPayOff"
                checked={paymentOption === 'monthsToPayOff'}
                onChange={handleRadioChange}
                className="text-white"
              />
              <Form.Control
                type="number"
                name="monthsToPayOff"
                value={monthsToPayOff}
                onChange={(e) => setMonthsToPayOff(e.target.value)}
                disabled={paymentOption !== 'monthsToPayOff'}
                min="0"
              />
            </Col>
          </Row>
  
          <Button variant="success" onClick={calculateResult} className="w-100">
            Calculate
          </Button>
          {result && <div className="result-message mt-3">{result}</div>}
        </Form>
      </Container>
    </div>
  );
};

export default DebtPaymentPlan;





    


