import React, { useEffect, useState } from 'react'
import BookService from '../services/BookService';
import Book from './Book';

const Home = () => {

  const [loading, setLoading] = useState(true);
  const [books, setBooks] = useState(null);

  useEffect(() => {
    const fetchBooks = async () => {
      setLoading(true);
      try {
        const response = await BookService.getAllBooks();
        console.log(response);
        setBooks(response.data);
      } catch (error) {
        console.log(error);
      };
      setLoading(false);
    };
    fetchBooks();
  }, [])
  
  return (
    <div className='container mx-auto my-8'>
      <div className='h-12'>
        <button className='rounder bg-slate-600 text-white px-6 py-2 font-semibold'>Add Book</button>
      </div>
      <div className='flex shadow border-b'>
        <table className='min-w-full'>
          <thead className='bg-gray-100'>
            <tr>
              <th className='text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6'>Book Name</th>
              <th className='text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6'>Description</th>
              <th className='text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6'>Price</th>
              <th className='text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6'>Actions</th>
            </tr>
          </thead>
          {!loading && (
            <tbody className='bg-white'>
              {books.map((book) => (
                <Book book={book} key={book.id}></Book>
              ))}
            </tbody>
          )}
        </table>
      </div>
    </div>
  )
}

export default Home