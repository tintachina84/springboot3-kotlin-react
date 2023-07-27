import './App.css';
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Carlist from "./components/Carlist";
import Login from "./components/Login";

function App() {
    const token = sessionStorage.getItem("jwt");
    if (token) {
        return (
            <div className="App">
                <AppBar position="static">
                    <Toolbar>
                        <Typography variant="h6">
                            Carshop
                        </Typography>
                    </Toolbar>
                </AppBar>
                <Carlist />
            </div>
        );
    } else {
        return (
            <div className="App">
              <AppBar position="static">
                <Toolbar>
                  <Typography variant="h6">
                    Carshop
                  </Typography>
                </Toolbar>
              </AppBar>
              <Login />
            </div>
          );
        }

}

export default App;
