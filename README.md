# Labour Registration Portal

A full-stack web application for labour registration with JWT authentication and real-time profile management.

## 🚀 Deploy Live in 5 Minutes

**No local database setup needed!**

- **Railway**: [RAILWAY_RENDER_SETUP.md](RAILWAY_RENDER_SETUP.md) (Recommended)
- **Render**: [RAILWAY_RENDER_SETUP.md](RAILWAY_RENDER_SETUP.md)

Both platforms auto-provision PostgreSQL and deploy your app to the internet.

## 💻 Local Development

### Prerequisites
- Java 17+
- Maven 3.9+
- Node.js 18+

### Backend

```bash
cd backend
/path/to/maven/bin/mvn spring-boot:run
```

Backend runs on `http://localhost:8080`

### Frontend

```bash
cd frontend
npm install
npm run dev
```

Frontend runs on `http://localhost:3000`

## ✨ Features

- User signup and signin with JWT authentication
- Labour profile creation (name, email, phone, skills)
- Browse all registered labour profiles
- Secure authentication with token-based authorization
- Responsive modern UI
- PostgreSQL database integration
- Production-ready deployment

## 📚 Documentation

- [Railway/Render Setup](RAILWAY_RENDER_SETUP.md) - Deploy live
- [DEPLOYMENT.md](DEPLOYMENT.md) - Advanced deployment options
