import React, { useState } from 'react';
import { Form, Button, Container, Row, Col } from 'react-bootstrap';
import backgroundVideo from '../videos/homeScreenBg.mp4'; // Make sure this path is correct
import './ResetPasswordForm.css'; // Make sure this path is correct
import { useNavigate } from 'react-router-dom';

const ResetPasswordForm = () => {
  const email = new URLSearchParams(window.location.search).get('email');
  const checksum = new URLSearchParams(window.location.search).get('checksum');
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    userLoginModel: {
        email: email,
        password: ''
    },
    checksum: checksum
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevState) => ({
      ...prevState,
      userLoginModel: {
        ...prevState.userLoginModel,
        [name]: value,
      },
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // Implement your login logic here
    let endpoint = 'http://localhost:8080/api/v2/login/resetPassword'
    let pack = {
        "userLoginModel": {
            "email": formData.userLoginModel.email,
            "password": formData.userLoginModel.password
        },
        "checksum": formData.checksum
    }
    fetch(endpoint, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(pack),
    })
    .then((response) => {
      if (!response.ok) {
        throw new Error('Error: ' + response.status);
      }
    })
    .then(() => {
      navigate('/reset-password-success')
    })
    .catch((error) => {
      console.error('Error:', error);
    });
  };

  return (
    <div className="reset-password-container">
      <video autoPlay loop muted className="background-video">
        <source src={backgroundVideo} type="video/mp4" />
        Your browser does not support the video tag.
      </video>
      <Container >
        <Row className="justify-content-md-center" style = {{ marginTop: '20vh'}}>
          <Col xs={12} sm={8} md={6} lg={4}>
            <Form onSubmit={handleSubmit} className="reset-password-form">
              <h2 className="text-center mb-4">Reset Password</h2>
              <Form.Group controlId="formBasicEmail" className="mb-3">
                <Form.Label>Email address</Form.Label>
                <Form.Control
                  type="email"
                  name="email"
                  placeholder="Enter email"
                  value={formData.userLoginModel.email}
                />
              </Form.Group>

              <Form.Group controlId="formBasicPassword" className="mb-3">
                <Form.Label>Password</Form.Label>
                <Form.Control
                  type="password"
                  name="password"
                  placeholder="Password"
                  value={formData.userLoginModel.password}
                  onChange={handleChange}
                />
              </Form.Group>

              <div className="text-center">
                <Button variant="primary" type="submit">
                  Reset Password
                </Button>
              </div>
            </Form>
          </Col>
        </Row>
      </Container>
    </div>
  );
};

export default ResetPasswordForm;
