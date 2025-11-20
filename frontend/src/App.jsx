import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./page/auth/Login";
import Register from "./page/auth/Register";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
      </Routes>
    </Router>
  );
}

export default App;
