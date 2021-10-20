import React from "react";
import axios from "axios";
import UserService from "./UserService";
import UserComponent from "./UserComponent";

class SignUp extends React.Component{
    constructor(props) {
        super(props);
        this.state={
            username:'',
            firstName:'',
            lastName:'',
            bio:'',
            email:'',
            dateOfBirth:'',
            password:'',
            usersData:[]
        }

    }


    addUsers = async (event) => {

        event.preventDefault();
        const URL = `http://localhost:8089/api/applicationuser`;
        const firstName=event.target.firstName.value;
        const lastName=event.target.lastName.value;
        const username=event.target.username.value;
        const bio=event.target.bio.value;
        const email=event.target.email.value;
        const dateOfBirth=event.target.date.value;
        const password=event.target.password.value;

        const userData ={
           firstName: firstName,
            lastName: lastName,
            username:username,
            bio:bio,
            email:email,
            dateOfBirth: dateOfBirth,
            password:password
        }
        console.log(userData);
        axios.post(URL, userData)
            .then((data => {
                try {
                    this.setState({
                        usersData: data.data

                    })
                }
                catch (e) {

console.log(e);
                }
                console.log('from  blog function', this.state.usersData.data);

            }))
            .catch((err) => {
                console.log(err);
                console.log('from  blog function', this.state.usersData);

                alert(err);

            })
    }
    // componentDidMount() {
    //     this.addUsers();
    // }

    render()
    {
        return(

                <form onSubmit={this.addUsers}>
                <input type="text" placeholder="first name" name='firstName' />
                    <input type="text" placeholder="last name" name='lastName' />
                    <input type="text" placeholder="username" name='username' />
                    <input type="email" placeholder="email" name='email' />
                    <input type="password" placeholder="password" name='password' />
                    <input type="text" placeholder="bio" name='bio' />
                    <input type="date"  name='date' />
                    <button type="submit">
                    Submit
                    </button>

            </form>

        );
    }
}


export default SignUp;
