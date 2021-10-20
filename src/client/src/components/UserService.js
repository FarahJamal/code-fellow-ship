import axios from 'axios'

const USERS_REST_API_URL = 'http://localhost:8089/api/users';

class UserService {

    getUsers=async()=>{

        return axios.get(USERS_REST_API_URL);
    }
}

export default new UserService();

