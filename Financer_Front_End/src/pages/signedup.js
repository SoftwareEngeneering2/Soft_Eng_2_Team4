import { Form, Container, Row, Col } from 'react-bootstrap';
import backgroundVideo from '../videos/homeScreenBg.mp4'; // Make sure this path is correct
import './signedup.css'; // Ensure you create a SignUp.css file
import { Link } from 'react-router-dom';

const SignedUp = () => {
  return (
    <div>
      <video autoPlay loop muted className="background-video">
        <source src={backgroundVideo} type="video/mp4" />
        Your browser does not support the video tag.
      </video>
      <Container className="signedup-container">
        <Row className="justify-content-md-center align-items-center" style={{ minHeight: '100vh' }}>
          <Col xs={12} sm={8} md={6} lg={4}>
            <Form className="signedup-form">
              <h2 className="text-center mb-4">Account successfully Created!</h2>
              <p className='mt-3 text-center'>
              <Link to="/login" className="sign-up-link">Login here</Link>
            </p>
            </Form>
            
          </Col>
        </Row>
      </Container>
    </div>
  );
};

export default SignedUp;