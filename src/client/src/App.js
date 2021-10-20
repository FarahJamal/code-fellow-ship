import logo from './logo.svg';
import './App.css';
import UserComponent from "./components/UserComponent";
import SignUp from "./components/SignUp";
import Login from "./components/Login";

import {
    BrowserRouter as Router,
    Switch,
    Route
} from "react-router-dom";

function App() {
  return (
    <div className="App">
        <Router>
            <Switch>
                <Route path="/users">
                <UserComponent/>
            </Route>
                <Route path="/signup">
                    <SignUp />
                </Route>

                <Route path="/login">
                    <Login/>
                </Route>
            </Switch>
        </Router>
    </div>
  );
}

export default App;
