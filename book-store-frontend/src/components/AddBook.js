import React, { useState } from 'react'
import BookService from '../services/BookService';
import { useNavigate } from 'react-router-dom';

const AddBook = () => {
    
    const navigate = useNavigate();

    const [book, setBook] = useState({
        id:"",
        name:"",
        desciption:"",
        price:""
    })

    const handleChange = (e) => {
        const value = e.target.value;
        setBook({...book, [e.target.name]: value})
    }

    const saveBook = (e) => {
        e.preventDefault();
        BookService.createBook(book).then((response) => {
            navigate("/home");
        }).catch((error) => {
            console.log(error);
        })
    }

    const clearBook = (e) => {
        e.preventDefault();
        setBook({
            id:"",
            name:"",
            desciption:"",
            price:""
        });
    }

  return (
    <div className='flex max-w-xl mx-auto shadow border-b'>
        <div className='px-5 py-5'>
            <div className='font-thin text-2xl tracking-wider'>
                <h1>Add New Book</h1>
            </div>
            <div className='items-center justify-center h-14 w-full my-4'>
                <label className='block text-gray-600 text-sm font-normal'>Book Name</label>
                <input type='text' name='name' value={book.name} onChange={(e) => handleChange(e)} className='h-10 w-96 border mt-2 px-2 py-2'></input>
            </div>
            <div className='items-center justify-center h-15 w-full my-4'>
                <label className='block text-gray-600 text-sm font-normal'>Desciption</label>
                <textarea name='description' onChange={(e) => handleChange(e)} className='h-20 w-96 border mt-2 px-2 py-2'></textarea>
            </div>
            <div className='items-center justify-center h-10 w-full my-4'>
                <label className='block text-gray-600 text-sm font-normal'>Price</label>
                <input type='number' name='price' value={book.price} onChange={(e) => handleChange(e)} className='h-10 w-96 border mt-2 px-2 py-2'></input>
            </div>
            <div className='items-center justify-center h-14 w-full my-4 space-x-3 pt-4'>
                <button onClick={saveBook} className='rounded text-white font-semibold bg-green-400 hover:bg-green-700 px-6 py-2'>Save</button>
                <button onClick={clearBook} className='rounded text-white font-semibold bg-red-400 hover:bg-red-700 px-6 py-2'>Clear</button>
            </div>
        </div>
    </div>
  )
}

export default AddBook