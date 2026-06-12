import { useEffect, useState } from 'react';
import './Dashboard.css';

const Dashboard = ({ user, onLogout }) => {
  const [labours, setLabours] = useState([]);
  const [myProfile, setMyProfile] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    setLoading(true);
    try {
      const token = localStorage.getItem('token');

      // Fetch all labour profiles
      const allResponse = await fetch('http://localhost:8080/api/labour/all');
      if (allResponse.ok) {
        setLabours(await allResponse.json());
      }

      // Fetch my profile
      const myResponse = await fetch('http://localhost:8080/api/labour/profile', {
        headers: { Authorization: `Bearer ${token}` },
      });
      if (myResponse.ok) {
        setMyProfile(await myResponse.json());
      }
    } catch (err) {
      setError('Failed to load data');
    } finally {
      setLoading(false);
    }
  };

  const handleLogout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('userId');
    localStorage.removeItem('userName');
    onLogout();
  };

  return (
    <div className="dashboard">
      <header className="dashboard-header">
        <h1>Labour Portal</h1>
        <div className="header-actions">
          <span>Welcome, {user.name}</span>
          <button className="logout-btn" onClick={handleLogout}>
            Logout
          </button>
        </div>
      </header>

      <div className="dashboard-content">
        {myProfile && (
          <section className="profile-card">
            <h2>Your Profile</h2>
            <div className="profile-info">
              <p>
                <strong>Name:</strong> {myProfile.name}
              </p>
              <p>
                <strong>Email:</strong> {myProfile.email}
              </p>
              <p>
                <strong>Phone:</strong> {myProfile.phone}
              </p>
              <p>
                <strong>Skills:</strong> {myProfile.skills}
              </p>
            </div>
          </section>
        )}

        <section className="labour-list-section">
          <h2>Available Labour Profiles ({labours.length})</h2>
          {error && <div className="error">{error}</div>}
          {loading ? (
            <p>Loading...</p>
          ) : labours.length === 0 ? (
            <p>No labour profiles found</p>
          ) : (
            <div className="labour-grid">
              {labours.map((labour) => (
                <div key={labour.id} className="labour-card">
                  <h3>{labour.name}</h3>
                  <p>
                    <strong>Email:</strong> {labour.email}
                  </p>
                  <p>
                    <strong>Phone:</strong> {labour.phone}
                  </p>
                  <p>
                    <strong>Skills:</strong> {labour.skills}
                  </p>
                </div>
              ))}
            </div>
          )}
        </section>
      </div>
    </div>
  );
};

export default Dashboard;
