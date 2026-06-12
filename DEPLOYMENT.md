## Labour Registration Portal

A full-stack web application for labour registration with authentication and live deployment.

### Features

- **User Authentication**: Secure signup and signin with JWT
- **Labour Profile Management**: Register with skills and contact information
- **Browse Labour**: View all registered labour profiles
- **Responsive Design**: Works on desktop and mobile
- **Database**: PostgreSQL for persistent storage
- **Production Ready**: Deployed on Railway/Render

### Tech Stack

**Backend**

- Spring Boot 3.2.8
- Spring Security with JWT
- JPA/Hibernate
- PostgreSQL
- Java 17

**Frontend**

- React 18
- Vite
- CSS3

### Local Development Setup

#### Prerequisites

- Java 17+
- Maven 3.9+
- Node.js 18+
- PostgreSQL

#### Backend Setup

1. Install PostgreSQL and create database:

```bash
createdb labour_db
```

2. Build and run backend:

```bash
cd backend
/path/to/maven/bin/mvn clean package
/path/to/maven/bin/mvn spring-boot:run
```

Backend runs on `http://localhost:8080`

#### Frontend Setup

1. Install dependencies:

```bash
cd frontend
npm install
```

2. Start dev server:

```bash
npm run dev
```

Frontend runs on `http://localhost:3000`

### API Endpoints

**Authentication**

- `POST /api/auth/signup` - Create new account
- `POST /api/auth/signin` - Login

**Labour**

- `GET /api/labour/all` - Get all labour profiles
- `GET /api/labour/profile` - Get current user's profile (requires JWT)

### Environment Variables

For production deployment, set these:

- `DATABASE_URL` - PostgreSQL connection URL
- `DB_USERNAME` - Database username
- `DB_PASSWORD` - Database password
- `JWT_SECRET` - Secret key for JWT tokens
- `JWT_EXPIRATION` - Token expiration time in ms (default: 86400000 = 24h)
- `PORT` - Server port (default: 8080)

### Deployment on Railway

1. Connect your GitHub repository to Railway
2. Create new project and select your repo
3. Add PostgreSQL plugin
4. Set environment variables
5. Railway automatically builds and deploys

Railway URL will be provided in your dashboard.

### Deployment on Render

1. Create new Web Service on Render
2. Connect GitHub repository
3. Set build command: `mvn clean package && cd frontend && npm install && npm run build`
4. Add PostgreSQL database
5. Set environment variables
6. Deploy

### Build for Production

```bash
./build.sh
docker build -t labour-portal .
docker run -p 8080:8080 labour-portal
```

### Git Push

```bash
git add .
git commit -m "Labour registration portal with auth"
git push -u origin main
```
