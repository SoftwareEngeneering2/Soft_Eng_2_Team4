import React from 'react';
import { Container, Navbar } from 'react-bootstrap';
import HeroSection from '../components/HeroSection';
import './Home.css';
import backgroundVideo from '../videos/homeScreenBg.mp4';

const Home = () => {
  return (
    <>
      <div className="home-container">
        <video autoPlay loop muted className="background-video">
          <source src={backgroundVideo} type="video/mp4" />
          Your browser does not support the video tag.
        </video>
        <Navbar />
        <HeroSection />
      </div>
    </>
  );
};

export default Home;
