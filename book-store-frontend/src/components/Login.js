import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import UserService from '../services/UserService';

const Login = () => {
    // init object
    const [login, setLogin] = useState({
        email: "",
        password: ""
    });
    
    const navigate = useNavigate();

    // event handle change
    const handleChange = (e) => {
        const value = e.target.value;
        setLogin({...login, [e.target.name]: value})
    }

    // click button Login
    const loginUser = (e) => {
        e.preventDefault();
        UserService.loginByEmailAndPassword(login).then((response) => {
            if (response.data.message == "Login Success"){
                navigate("/home");
            } else {
                alert ("Email or password not right")
            }
        }).catch((error) => {
            console.log(error);
        })

    }

  return (
    <div className='flex max-w-xl mx-auto shadow border-b'>
        <div className='px-5 py-5'>
            <div className='font-thin text-2xl tracking-wider'>
                <h1>Login</h1>
            </div>
            <div className='items-center justify-center h-14 w-full my-4'>
                <label className='block text-gray-600 text-sm font-normal'>Email</label>
                <input type='email' name='email' value={login.email} onChange={(e) => {handleChange(e)}} className='h-10 w-96 border mt-2 px-2 py-2'></input>
            </div>
            <div className='items-center justify-center h-14 w-full my-4'>
                <label className='block text-gray-600 text-sm font-normal'>Password</label>
                <input type='password' name='password' value={login.password} onChange={(e) => {handleChange(e)}} className='h-10 w-96 border mt-2 px-2 py-2'></input>
            </div>
            <div className='items-center justify-center h-14 w-full my-4 space-x-3 pt-4'>
                <button onClick={loginUser} className='rounded text-white font-semibold bg-green-400 hover:bg-green-700 px-6 py-2'>Login</button>
            </div>
            <div className='items-center justify-center h-14 w-full my-4 space-x-3 pt-4'>
                <span>Don't have an account?</span>
                <a onClick={() => {navigate("/register")}} className='text-blue-800 hover:text-red-500 hover:cursor-pointer'>Register</a>
            </div>
        </div>
    </div>
  )
}

export default Login