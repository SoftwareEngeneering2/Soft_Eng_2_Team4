import React, { useState } from 'react';
import { Form, Button, Container, Row, Col } from 'react-bootstrap';
import backgroundVideo from '../videos/homeScreenBg.mp4'; // Make sure this path is correct
import './RequestResetPassword.css'; // Make sure this path is correct
import { useNavigate } from 'react-router-dom';

const RequestResetPassword = () => {
  let [errorMessage, setErrorMessage] = useState('')
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    email: ''
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prevState => ({
      ...prevState,
      [name]: value
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log(formData.email)
    // Implement your login logic here
    let endpoint = 'http://localhost:8080/api/v2/login/requestResetPassword'
    fetch(endpoint, {
      method: 'POST',
      headers: {
        'Content-Type': 'text/plain',
      },
      body: formData.email,
    })
    .then((response) => {
      if (!response.ok) {
        throw new Error('Error: ' + response.status);
      }
    })
    .then(() => {
      navigate(`/reset-link-sent?email=${formData.email}`)
    })
    .catch((error) => {
      console.error('Error:', error);
      setErrorMessage("")
      setTimeout(setErrorMessage, 100, "Could not send Reset Password link")
      // setResult('Failed to calculate. Please try again.');
    });
    // You would typically handle server communication here
  };

  return (
    <div className="reset-request-container">
      <video autoPlay loop muted className="background-video">
        <source src={backgroundVideo} type="video/mp4" />
        Your browser does not support the video tag.
      </video>
      <Container >
        <Row className="justify-content-md-center" style = {{ marginTop: '20vh'}}>
          <Col xs={12} sm={8} md={6} lg={4}>
            <Form onSubmit={handleSubmit} className="reset-request-form">
              <h4 className="text-center mb-4">Request Reset Password Link</h4>
              <div className="incorrect">{errorMessage}</div>
              <Form.Group controlId="formBasicEmail" className="mb-3">
                <Form.Label>Email address</Form.Label>
                <Form.Control
                  type="email"
                  name="email"
                  placeholder="Enter email"
                  value={formData.email}
                  onChange={handleChange}
                />
              </Form.Group>

              <div className="text-center">
                <Button variant="primary" type="submit">
                  Generate Link
                </Button>
              </div>
            </Form>
          </Col>
        </Row>
      </Container>
    </div>
  );
};

export default RequestResetPassword;
