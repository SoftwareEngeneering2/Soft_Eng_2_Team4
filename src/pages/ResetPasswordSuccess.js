import { Form, Container, Row, Col } from 'react-bootstrap';
import backgroundVideo from '../videos/homeScreenBg.mp4'; // Make sure this path is correct
import './ResetPasswordSuccess.css'; // Ensure you create a SignUp.css file

const ResetPasswordSuccess = () => {
    const message = "Password Reset"
  return (
    <div>
      <video autoPlay loop muted className="background-video">
        <source src={backgroundVideo} type="video/mp4" />
        Your browser does not support the video tag.
      </video>
      <Container className="reset-container">
        <Row className="justify-content-md-center align-items-center" style={{ minHeight: '100vh' }}>
          <Col xs={12} sm={8} md={6} lg={4}>
            <Form className="reset-form">
              <h2 className="text-center mb-4">{message}</h2>
            </Form>
          </Col>
        </Row>
      </Container>
    </div>
  );
};

export default ResetPasswordSuccess;