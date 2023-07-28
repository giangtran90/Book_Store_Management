import React from 'react'

const Book = ({book}) => {
  return (
    <tr key={book.id}>
        <td className='text-left px-6 py-4 whitespace-nowrap'>
            <div className='text-sm text-gray-500'>{book.name}</div>
        </td>
        <td className='text-left px-6 py-4 whitespace-nowrap'>
            <div className='text-sm text-gray-500'>{book.description}</div>
        </td>
        <td className='text-left px-6 py-4 whitespace-nowrap'>
            <div className='text-sm text-gray-500'>{book.price}</div>
        </td>
        <td className='text-left px-6 py-4 whitespace-nowrap font-medium text-sm'>
            <a className='text-indigo-600 hover:text-indigo-800 px-4 hover:cursor-pointer'>Edit</a>
            <a className='text-indigo-600 hover:text-indigo-800 px-4 hover:cursor-pointer'>Delete</a>
        </td>
    </tr>
  )
}

export default Book