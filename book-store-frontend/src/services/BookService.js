import axios from "axios";

const BOOK_API_BASE_URL = "http://localhost:8080/api/v1/books";

class BookService {
    
    createBook(book){
        return axios.post(BOOK_API_BASE_URL, book);
    }

    getAllBooks(){
        return axios.get(BOOK_API_BASE_URL);
    }

    deleteBook(id) {
        return axios.delete(BOOK_API_BASE_URL + "/" + id);
    }
}

export default new BookService()