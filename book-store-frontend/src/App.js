import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import Navbar from './components/Navbar';
import Register from './components/Register';
import Login from './components/Login';
import Home from './components/Home';
import AddBook from './components/AddBook';
import EditBook from './components/EditBook';

function App() {
  return (
    <>
      <BrowserRouter>
        <Navbar></Navbar>
        <Routes>
          <Route path='/' element={<Login/>}></Route>
          <Route path='/register' element={<Register />}></Route>
          <Route path='/home' element={<Home />}></Route>
          <Route path='/addBook' element={<AddBook />}></Route>
          <Route path='/editBook/:id' element={<EditBook />}></Route>
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
