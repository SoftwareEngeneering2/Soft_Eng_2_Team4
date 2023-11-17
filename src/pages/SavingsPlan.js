import React, { useState } from 'react';
import { Container, Form, Button, Row, Col } from 'react-bootstrap';
import backgroundVideo from '../videos/homeScreenBg.mp4'; // Make sure the path is correct
import './SavingsPlan.css'; // Ensure this CSS file exists with the appropriate styles

const SavingsPlan = () => {
  const [APY, setAPY] = useState('');
  const [target, setTarget] = useState('');
  const [expectedMonthlySavings, setExpectedMonthlySavings] = useState('');
  const [desiredMonthsToSaveFor, setDesiredMonthsToSaveFor] = useState('');
  const [calculationOption, setCalculationOption] = useState('expectedMonthlySavings');
  const [result, setResult] = useState('');

  const calculateSavingsPlan = () => {
    const requestData = {
      apy: parseFloat(APY),
      target: parseFloat(target),
    };

    let endpoint = '';
    if (calculationOption === 'expectedMonthlySavings') {
      requestData.monthlyAmountSaved = parseFloat(expectedMonthlySavings);
      endpoint = 'http://localhost:8080/api/v1/savings_controller/months';
    } else {
      requestData.months = parseInt(desiredMonthsToSaveFor);
      endpoint = 'http://localhost:8080/api/v1/savings_controller/monthly_payment';
    }

    // Make HTTP POST request to the Spring Boot backend
    fetch(endpoint, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(requestData),
    })
    .then((response) => {
      if (!response.ok) {
        throw new Error('Error: ' + response.status);
      }
      return response.json();
    })
    .then(data => {
        if (calculationOption === 'expectedMonthlySavings') {
          setResult(`Duration of Saving: ${data} month${data == 1 ? "" : "s"}`)
        } else {
          setResult(`Amount Saved Monthly: $${data}`)
        }
      }
    )
    .catch(error => console.error('Error:', error));
  };

  return (
    <div className="content-background">
      <video autoPlay loop muted className="background-video">
        <source src={backgroundVideo} type="video/mp4" />
        Your browser does not support the video tag.
      </video>
      <Container className="savings-plan-content">
        <h2 className="text-white">Savings Plan Calculator</h2>
        <Form>
        <Form.Group as={Row} className="mb-3">
            <Form.Label column sm="4" className="text-white">Target Savings ($):</Form.Label>
            <Col sm="8">
              <Form.Control
                type="number"
                value={target}
                onChange={(e) => setTarget(e.target.value)}
                min="0"
              />
            </Col>
          </Form.Group>
          <Form.Group as={Row} className="mb-3">
            <Form.Label column sm="4" className="text-white">APY (%):</Form.Label>
            <Col sm="8">
              <Form.Control
                type="number"
                value={APY}
                onChange={(e) => setAPY(e.target.value)}
                min="0"
                step="0.01"
              />
            </Col>
          </Form.Group>
          <Row className="mb-3">
            <Col sm="6">
              <Form.Check 
                type="radio"
                label="Expected Monthly Savings"
                name="calculationOption"
                value="expectedMonthlySavings"
                checked={calculationOption === 'expectedMonthlySavings'}
                onChange={() => setCalculationOption('expectedMonthlySavings')}
                className="text-white"
              />
              <Form.Control
                type="number"
                value={expectedMonthlySavings}
                onChange={(e) => setExpectedMonthlySavings(e.target.value)}
                disabled={calculationOption !== 'expectedMonthlySavings'}
                min="0"
              />
            </Col>
            <Col sm="6">
              <Form.Check 
                type="radio"
                label="Desired Months to Save For"
                name="calculationOption"
                value="desiredMonthsToSaveFor"
                checked={calculationOption === 'desiredMonthsToSaveFor'}
                onChange={() => setCalculationOption('desiredMonthsToSaveFor')}
                className="text-white"
              />
              <Form.Control
                type="number"
                value={desiredMonthsToSaveFor}
                onChange={(e) => setDesiredMonthsToSaveFor(e.target.value)}
                disabled={calculationOption !== 'desiredMonthsToSaveFor'}
                min="0"
              />
            </Col>
          </Row>

          <Button variant="success" onClick={calculateSavingsPlan} className="w-100">
            Calculate
          </Button>
          {result && <div className="result-message mt-3">{result}</div>}
        </Form>
      </Container>
    </div>
  );
};

export default SavingsPlan;