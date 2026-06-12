import { useEffect, useState } from 'react';
import './App.css';

const API_BASE = 'http://localhost:8080/api/appointments';

function App() {
  const [appointments, setAppointments] = useState([]);
  const [title, setTitle] = useState('');
  const [date, setDate] = useState('');
  const [time, setTime] = useState('');
  const [location, setLocation] = useState('');
  const [filterDate, setFilterDate] = useState('');
  const [error, setError] = useState('');

  useEffect(() => {
    fetchAppointments();
  }, []);

  const fetchAppointments = async () => {
    try {
      const url = filterDate ? `${API_BASE}?date=${filterDate}` : API_BASE;
      const response = await fetch(url);
      const data = await response.json();
      setAppointments(data);
    } catch (err) {
      setError('Unable to load appointments.');
    }
  };

  const createAppointment = async (event) => {
    event.preventDefault();
    setError('');
    if (!title || !date || !time || !location) {
      setError('Please fill in all fields.');
      return;
    }

    try {
      const response = await fetch(API_BASE, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ title, date, time, location }),
      });

      if (!response.ok) {
        throw new Error('Create failed');
      }
      setTitle('');
      setDate('');
      setTime('');
      setLocation('');
      await fetchAppointments();
    } catch (err) {
      setError('Unable to create appointment.');
    }
  };

  const deleteAppointment = async (id) => {
    try {
      const response = await fetch(`${API_BASE}/${id}`, { method: 'DELETE' });
      if (!response.ok) {
        throw new Error('Delete failed');
      }
      await fetchAppointments();
    } catch (err) {
      setError('Unable to remove appointment.');
    }
  };

  const applyFilter = async (event) => {
    event.preventDefault();
    await fetchAppointments();
  };

  const clearFilter = async () => {
    setFilterDate('');
    await fetchAppointments();
  };

  return (
    <div className="app-shell">
      <header>
        <h1>Appointment Scheduler</h1>
      </header>

      <section className="card">
        <h2>Create Appointment</h2>
        <form onSubmit={createAppointment} className="form-grid">
          <label>
            Title
            <input value={title} onChange={(e) => setTitle(e.target.value)} placeholder="Meeting with team" />
          </label>
          <label>
            Date
            <input type="date" value={date} onChange={(e) => setDate(e.target.value)} />
          </label>
          <label>
            Time
            <input type="time" value={time} onChange={(e) => setTime(e.target.value)} />
          </label>
          <label>
            Location
            <input value={location} onChange={(e) => setLocation(e.target.value)} placeholder="Office, Zoom" />
          </label>
          <button type="submit">Save Appointment</button>
        </form>
      </section>

      <section className="card">
        <div className="section-header">
          <h2>Appointments</h2>
          <form onSubmit={applyFilter} className="filter-form">
            <input
              type="date"
              value={filterDate}
              onChange={(e) => setFilterDate(e.target.value)}
              aria-label="Filter by date"
            />
            <button type="submit">Filter</button>
            <button type="button" className="secondary" onClick={clearFilter}>
              Clear
            </button>
          </form>
        </div>

        {error && <p className="error">{error}</p>}

        {appointments.length === 0 ? (
          <p className="empty-state">No appointments found.</p>
        ) : (
          <ul className="appointment-list">
            {appointments.map((appointment) => (
              <li key={appointment.id} className="appointment-item">
                <div>
                  <strong>{appointment.title}</strong>
                  <div>{appointment.date} at {appointment.time}</div>
                  <div>{appointment.location}</div>
                </div>
                <button onClick={() => deleteAppointment(appointment.id)}>Remove</button>
              </li>
            ))}
          </ul>
        )}
      </section>
    </div>
  );
}

export default App;
