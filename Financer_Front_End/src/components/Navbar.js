import React from 'react';
import { Navbar, Nav, Button, Container } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import './AppDownload.css';

const NavigationBar = () => {
  return (
    <Navbar bg="dark" variant="dark" expand="lg">
      <Container>
        <Navbar.Brand as={Link} to="/">Financer's Guide to Life</Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="me-auto mx-auto"> {/* Center the navigation links */}
            <Nav.Link as={Link} to="/">Home</Nav.Link>
            <Nav.Link as={Link} to="/savings-plan">Savings Plan</Nav.Link>
            <Nav.Link as={Link} to="/debt-payment-plan">Debt Payment Plan</Nav.Link>
          </Nav>
          <Nav> {/* Align the buttons to the right */}
            <Button variant="outline-info" as={Link} to="/login" className="button-outline-purple me-2">Log In</Button>
            <Button variant="info" as={Link} to="/signup" className="button-purple">Sign Up</Button>
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
};

export default NavigationBar;
