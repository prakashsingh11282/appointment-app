import { useEffect, useState } from 'react';
import SignIn from './SignIn';
import SignUp from './SignUp';
import Dashboard from './Dashboard';
import './App.css';

function App() {
  const [currentPage, setCurrentPage] = useState('signin');
  const [user, setUser] = useState(null);

  useEffect(() => {
    const token = localStorage.getItem('token');
    const userId = localStorage.getItem('userId');
    const userName = localStorage.getItem('userName');
    if (token && userId && userName) {
      setUser({ userId, name: userName });
      setCurrentPage('dashboard');
    }
  }, []);

  const handleSignInSuccess = (data) => {
    setUser(data);
    setCurrentPage('dashboard');
  };

  const handleSignUpSuccess = (data) => {
    setUser(data);
    setCurrentPage('dashboard');
  };

  const handleLogout = () => {
    setUser(null);
    setCurrentPage('signin');
  };

  return (
    <div className="app">
      {currentPage === 'signin' && (
        <>
          <SignIn onSignInSuccess={handleSignInSuccess} />
          <div className="page-switch">
            <button onClick={() => setCurrentPage('signup')}>Create New Account</button>
          </div>
        </>
      )}
      {currentPage === 'signup' && (
        <>
          <SignUp onSignUpSuccess={handleSignUpSuccess} />
          <div className="page-switch">
            <button onClick={() => setCurrentPage('signin')}>Back to Sign In</button>
          </div>
        </>
      )}
      {currentPage === 'dashboard' && user && (
        <Dashboard user={user} onLogout={handleLogout} />
      )}
    </div>
  );
}

export default App;

