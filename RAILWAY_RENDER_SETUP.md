# Railway Deployment Guide

## Step-by-Step Setup

### 1. Create Railway Account

- Go to [railway.app](https://railway.app)
- Click "Login with GitHub"
- Authorize Railway to access your GitHub account

### 2. Create New Project

- Click "New Project"
- Select "Deploy from GitHub repo"
- Search for `appointment-app` (or your repo name)
- Click "Import"

### 3. Configure Database

- Railway will detect it's a Spring Boot app
- Click "Add Database" → Select "PostgreSQL"
- Railway automatically creates the database and sets `DATABASE_URL`

### 4. Set Environment Variables

- Go to "Variables" tab
- Add these variables:
  ```
  JWT_SECRET = your-secret-key-here-make-it-long
  JWT_EXPIRATION = 86400000
  JAVA_TOOL_OPTIONS = -Dserver.port=$PORT
  ```

### 5. Deploy

- Click "Deploy"
- Railway builds and deploys automatically
- Takes 2-5 minutes

### 6. Get Your Live URL

- Go to "Settings" tab
- Copy the generated domain (e.g., `https://labour-portal.railway.app`)
- **Your app is now LIVE!**

---

## Render Deployment Guide

### 1. Create Render Account

- Go to [render.com](https://render.com)
- Click "Sign up with GitHub"
- Authorize Render

### 2. Create New Web Service

- Click "New +"
- Select "Web Service"
- Connect GitHub repo

### 3. Configure Service

- **Name**: `labour-portal`
- **Branch**: `main`
- **Build Command**: `cd backend && mvn clean package -DskipTests`
- **Start Command**: `java -jar backend/target/*.jar`
- **Runtime**: Java 17

### 4. Add PostgreSQL Database

- Click "New +"
- Select "PostgreSQL"
- Keep default settings
- Click "Create Database"

### 5. Configure Environment Variables

Go to "Environment" and add:

```
DATABASE_URL = (auto-filled by Render)
JWT_SECRET = your-secret-key-here
JWT_EXPIRATION = 86400000
PORT = (auto-set, usually 8080)
```

### 6. Deploy

- Click "Create Web Service"
- Render builds and deploys
- Takes 3-7 minutes

### 7. Get Live URL

- Dashboard shows: `https://labour-portal.onrender.com`
- **Your app is LIVE!**

---

## After Deployment

### Test Your App

```bash
# Sign up
curl -X POST https://your-domain/api/auth/signup \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "email": "john@example.com",
    "password": "password123",
    "phone": "1234567890",
    "skills": "Carpentry, Plumbing"
  }'

# Sign in
curl -X POST https://your-domain/api/auth/signin \
  -H "Content-Type: application/json" \
  -d '{
    "email": "john@example.com",
    "password": "password123"
  }'

# Get all labour profiles
curl https://your-domain/api/labour/all
```

### Update Frontend API URL

In `frontend/src/App.jsx`, change:

```javascript
const API_BASE = "https://your-domain/api";
```

### Redeploy After Changes

```bash
git add .
git commit -m "Update API URL for production"
git push origin main
```

Both platforms auto-redeploy on push!

---

## Troubleshooting

### App won't start

- Check "Logs" in dashboard
- Verify `JWT_SECRET` is set
- Ensure `DATABASE_URL` is correct

### 502 Bad Gateway

- Wait 2-3 minutes for full deployment
- Restart service from dashboard
- Check PostgreSQL is running

### Authentication failing

- Make sure `JWT_SECRET` is set in environment
- Check token expiration with `JWT_EXPIRATION`

### Database connection error

- Verify `DATABASE_URL` format
- Check PostgreSQL is not sleeping (Render free tier hibernates)

---

## Cost

**Railway**: $5/month (includes $20 monthly credit)
**Render**: Free tier available (with 15 min inactivity limit)

Choose based on your needs!
