import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import styles from './index.module.css'


const NavBar = (props: any) => {
  return (
    <>
      <Navbar bg="dark" data-bs-theme="dark">
        <Container>
          <Navbar.Brand href="/" >Core Spring Security</Navbar.Brand>
          <Nav >
            <Nav.Link href="/users" >
              <span className='font-bold'> HOME </span>
            </Nav.Link>
            
          </Nav>

        </Container>
      </Navbar>


    </>
  )
}

export default NavBar;