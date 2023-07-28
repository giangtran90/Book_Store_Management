import React from 'react'
import { NumericFormat } from "react-number-format";

const Book = ({book,deleteBook}) => {
  return (
    <tr key={book.id}>
        <td className='text-left px-6 py-4 whitespace-nowrap'>
            <div className='text-sm text-gray-500'>{book.name}</div>
        </td>
        <td className='text-left px-6 py-4 whitespace-nowrap'>
            <div className='text-sm text-gray-500'>{book.description}</div>
        </td>
        <td className='text-left px-6 py-4 whitespace-nowrap'>
            <div className='text-sm text-gray-500'>
                <NumericFormat value={book.price} thousandSeparator=","></NumericFormat>
            </div>
        </td>
        <td className='text-left px-6 py-4 whitespace-nowrap font-medium text-sm'>
            <a className='text-indigo-600 hover:text-indigo-800 px-4 hover:cursor-pointer'>Edit</a>
            <a onClick={(e,id) => deleteBook(e,book.id)} className='text-indigo-600 hover:text-indigo-800 px-4 hover:cursor-pointer'>Delete</a>
        </td>
    </tr>
  )
}

export default Book