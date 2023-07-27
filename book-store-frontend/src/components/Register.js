import React, { useState } from 'react'
import UserService from '../services/UserService';

const Register = () => {
    // init object
    const [user, setUser] = useState({
        id: "",
        username: "",
        email: "",
        password: ""
    });
    
    // event handle change
    const handleChange = (e) => {
        const value = e.target.value;
        setUser({...user, [e.target.name]: value})
    }

    // click button save
    const saveUser = (e) => {
        e.preventDefault();
        UserService.registerUser(user).then((response) => {
            console.log(response);
        }).catch((error) => {
            console.log(error);
        })
    }

    // click button clear
    const clearInput = (e) => {
        e.preventDefault();
        setUser({
            id: "",
            username: "",
            email: "",
            password: ""
        });
    }

  return (
    <div className='flex max-w-xl mx-auto shadow border-b'>
        <div className='px-5 py-5'>
            <div className='font-thin text-2xl tracking-wider'>
                <h1>Register</h1>
            </div>
            <div className='items-center justify-center h-14 w-full my-4'>
                <label className='block text-gray-600 text-sm font-normal'>User Name</label>
                <input type='text' name='username' value={user.username} onChange={(e)=>handleChange(e)} className='h-10 w-96 border mt-2 px-2 py-2'></input>
            </div>
            <div className='items-center justify-center h-14 w-full my-4'>
                <label className='block text-gray-600 text-sm font-normal'>Email</label>
                <input type='email' name='email' value={user.email} onChange={(e)=>handleChange(e)} className='h-10 w-96 border mt-2 px-2 py-2'></input>
            </div>
            <div className='items-center justify-center h-14 w-full my-4'>
                <label className='block text-gray-600 text-sm font-normal'>Password</label>
                <input type='password' name='password' value={user.password} onChange={(e)=>handleChange(e)} className='h-10 w-96 border mt-2 px-2 py-2'></input>
            </div>
            <div className='items-center justify-center h-14 w-full my-4 space-x-3 pt-4'>
                <button onClick={saveUser} className='rounded text-white font-semibold bg-green-400 hover:bg-green-700 px-6 py-2'>Save</button>
                <button onClick={clearInput} className='rounded text-white font-semibold bg-red-400 hover:bg-red-700 px-6 py-2'>Clear</button>
            </div>
        </div>
    </div>
  )
}

export default Register