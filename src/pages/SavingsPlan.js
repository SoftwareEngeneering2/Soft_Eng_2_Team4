import React, { useState } from 'react';
import { Container, Form, Button, Row, Col, Table, Alert } from 'react-bootstrap';
import backgroundVideo from '../videos/homeScreenBg.mp4';
import './SavingsPlan.css';

const SavingsPlan = () => {
  const [expenses, setExpenses] = useState([]);
  const [expenseFormData, setExpenseFormData] = useState({
    name: '',
    amount: ''
  });
  const [savingsInfo, setSavingsInfo] = useState({
    target: '',
    apy: '',
    months: ''
  });
  const [result, setResult] = useState(null);
  const [error, setError] = useState('');

  const handleExpenseChange = (e) => {
    setExpenseFormData({ ...expenseFormData, [e.target.name]: e.target.value });
  };

  const handleExpenseSubmit = (e) => {
    e.preventDefault();
    setExpenses([...expenses, expenseFormData]);
    setExpenseFormData({ name: '', amount: '' }); // Reset the form fields
  };

  const handleSavingsInfoChange = (e) => {
    setSavingsInfo({ ...savingsInfo, [e.target.name]: e.target.value });
  };

  const handleSavingsSubmit = async (e) => {
    e.preventDefault();
    // Replace the below URL with your actual API endpoint
    const apiUrl = 'http://yourapi/savings';
    try {
      const response = await fetch(apiUrl, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(savingsInfo),
      });
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      const data = await response.json();
      setResult(data);
    } catch (error) {
      console.error('Error:', error);
      setError('An error occurred while calculating the savings plan.');
    }
  };

  return (
    <div className="savings-plan-container">
      <video autoPlay loop muted className="background-video">
        <source src={backgroundVideo} type="video/mp4" />
        Your browser does not support the video tag.
      </video>
      <Container className="pt-5">
        <div className="p-3 mb-2 bg-gradient-info text-white">
          <h1 className="text-center" variant="">Savings Plan Page</h1>
          
          {/* Form for adding expenses */}
          <Form onSubmit={handleExpenseSubmit} className="form-spacing">
            <Row>
              <Col>
                <Form.Group controlId="expenseName">
                  <Form.Label>Expense Name</Form.Label>
                  <Form.Control 
                    type="text" 
                    name="name" 
                    value={expenseFormData.name} 
                    onChange={handleExpenseChange} 
                  />
                </Form.Group>
              </Col>
              <Col>
                <Form.Group controlId="expenseAmount">
                  <Form.Label>Amount</Form.Label>
                  <Form.Control 
                    type="number" 
                    name="amount" 
                    value={expenseFormData.amount} 
                    onChange={handleExpenseChange} 
                  />
                </Form.Group>
              </Col>
            </Row>
            <Button type="submit" className="mt-2">Add Expense</Button>
          </Form>

          {/* Display the expenses in a table */}
          <Table striped bordered hover className="mt-4">
            <thead>
              <tr>
                <th>Expense Name</th>
                <th>Amount</th>
              </tr>
            </thead>
            <tbody>
              {expenses.map((expense, index) => (
                <tr key={index}>
                  <td>{expense.name}</td>
                  <td>{expense.amount}</td>
                </tr>
              ))}
            </tbody>
          </Table>

          {/* Form for savings goal */}
          <Form onSubmit={handleSavingsSubmit} className="form-spacing">
            <Row>
              <Col>
                <Form.Group controlId="target">
                  <Form.Label>Savings Goal</Form.Label>
                  <Form.Control 
                    type="number" 
                    name="target" 
                    value={savingsInfo.target} 
                    onChange={handleSavingsInfoChange} 
                  />
                </Form.Group>
              </Col>
              <Col>
                <Form.Group controlId="apy">
                  <Form.Label>Annual Percentage Yield (APY)</Form.Label>
                  <Form.Control 
                    type="number" 
                    name="apy" 
                    value={savingsInfo.apy} 
                    onChange={handleSavingsInfoChange} 
                  />
                </Form.Group>
              </Col>
              <Col>
                <Form.Group controlId="months">
                  <Form.Label>Timeframe (Months)</Form.Label>
                  <Form.Control 
                    type="number" 
                    name="months" 
                    value={savingsInfo.months} 
                    onChange={handleSavingsInfoChange} 
                  />
                </Form.Group>
              </Col>
            </Row>
            <Button type="submit" variant="primary" className="mt-2">Calculate Savings Plan</Button>
          </Form>

          {/* Display the calculation results */}
          {result && (
            <div className="mt-4">
              <Alert variant="success">
                To reach your savings goal of {savingsInfo.target}, you need to save {result.monthlyAmountNeeded} each month for {result.monthsToSave} months.
              </Alert>
            </div>
          )}

          {/* Display error message */}
          {error && (
            <Alert variant="danger" className="mt-4">{error}</Alert>
          )}
        </div>
      </Container>
    </div>
  );
};

export default SavingsPlan;

