import React, { useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom';
import BookService from '../services/BookService';

const EditBook = () => {

    const { id } = useParams();

    const navigate = useNavigate();

    const [book, setBook] = useState({
        id:id,
        name:"",
        desciption:"",
        price:""
    })

    const handleChange = (e) => {
        const value = e.target.value;
        setBook({...book, [e.target.name]: value})
    }

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await BookService.getBookById(id);
                setBook(response.data);
            } catch (error) {
                console.log(error);
            }
        };
        fetchData();
    }, [])

  return (
    <div className='flex max-w-xl mx-auto shadow border-b'>
        <div className='px-5 py-5'>
            <div className='font-thin text-2xl tracking-wider'>
                <h1>Edit Book</h1>
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
                <button className='rounded text-white font-semibold bg-green-400 hover:bg-green-700 px-6 py-2'>Save</button>
                <button onClick={() => {navigate("/home")}} className='rounded text-white font-semibold bg-blue-400 hover:bg-blue-700 px-6 py-2'>Cancel</button>
            </div>
        </div>
    </div>
  )
}

export default EditBook