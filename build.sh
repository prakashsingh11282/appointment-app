#!/bin/bash
set -e

echo "Building Labour Portal Application..."

# Build backend
echo "Building backend..."
cd backend
/Users/macbook/.maven/maven-3.9.16/bin/mvn clean package
cd ..

# Build frontend
echo "Building frontend..."
cd frontend
npm install
npm run build
cd ..

echo "Build complete!"
