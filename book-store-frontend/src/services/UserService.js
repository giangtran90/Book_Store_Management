import axios from "axios";

const USER_API_BASE_URL = "http://localhost:8080/api/v1/";

class UserService {
    
    registerUser(user){
        return axios.post(USER_API_BASE_URL + "users", user);
    }
}

export default new UserService()