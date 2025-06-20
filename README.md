<h1 align="center">👁️ EYEPATH - Visual Assistance Collision-Avoidance AI Service</h1>

<p align="center">
  <img src="https://img.shields.io/badge/SpringBoot-3.4.5-brightgreen.svg" />
  <img src="https://img.shields.io/badge/Java-17-blue.svg" />
  <img src="https://img.shields.io/badge/MongoDB-NoSQL-green.svg" />
  <img src="https://img.shields.io/badge/WebSocket-RealTime-lightgrey.svg" />
</p>

> 💡 A real-time AI-driven assistive technology service that predicts and alerts users of potential collisions.

---

## 🚀 Overview

**EYEPATH** is an **AI-powered assistive mobility system** designed to support users with visual impairments.  
This Spring Boot backend server, **Tablula**, handles user authentication, authorization, profile management, token issuance, and usage tracking through REST APIs and JWT-based secure communication.  
All visual inference is handled by the Argus service, while Tablula acts as the **Auth and User Info gateway**.

> 🛠️ Core technologies: Spring Boot, MongoDB, JWT, Spring Security, WebSocket (via Argus)

---

## 📦 Technology Stack

- **Backend**: Java 17, Spring Boot 3.4.5
- **Security**: Spring Security, JWT (Access/Refresh Token Management)
- **Database**: MongoDB (NoSQL)
- **Communication**: REST APIs, WebSocket (through AI module)
- **Deployment**: Docker, GitHub Actions, Kubernetes

---

## 🔑 Authentication & Security

- **Login / Logout**: JWT-based token issued on login, HTTP-only Secure Refresh Token stored in cookies.
- **Access Token Renewal**: If Access Token is expired, Refresh Token is used to reissue it (`/auth/reissue`).
- **Token Filtering**: Custom filter intercepts every secured request and validates Authorization header.
- **Logout**: Refresh token is invalidated and deleted from database and cookie.

---

## 🧩 Key Features

| Feature              | Description |
|----------------------|-------------|
| 🔐 Sign-up / Login   | Registers users, issues JWT tokens |
| 🧾 Profile Management| View and update personal info |
| 📈 Usage Monitoring  | Tracks session duration, image count |
| ⚠️ Real-time AI Inference | Triggered through Argus WebSocket |
| 🔁 Token Reissue     | Secure refresh of access token |

---

## 🗂️ API Specification

| Method | Endpoint            | Description                      |
|--------|---------------------|----------------------------------|
| `POST` | `/users`            | Register new user                |
| `POST` | `/auth/login`       | Login and receive JWT tokens     |
| `POST` | `/auth/logout`      | Logout and delete refresh token |
| `POST` | `/auth/reissue`     | Reissue expired Access Token     |
| `PUT`  | `/users`            | Update user info                 |
| `GET`  | `/users`            | Retrieve current user info       |
| `GET`  | `/usage/{userId}`   | Get usage records for a user     |
| `POST` | `/logs`             | Save session usage logs          |
| `WS`   | `/ws/collision`     | Real-time AI Inference via Argus |

---

## 📁 Directory Structure

```
📦src
 ┣ 📂controller
 ┃ ┣ 📂auth            → Login, logout, token endpoints
 ┃ ┣ 📂signup          → User registration
 ┃ ┗ 📂userinfo        → Profile info endpoints
 ┣ 📂security           → JWT token handling, filters
 ┣ 📂service            → Business logic (auth, profile, signup)
 ┣ 📂domain             → MongoDB entities
 ┣ 📂repository         → Mongo repositories
 ┣ 📂dto                → Request/Response DTOs
 ┗ 📜application.yml    → MongoDB, JWT config
```

---

## 🔄 Authentication Flow

1. Client logs in with `/auth/login` → receives `accessToken` (header) and `refreshToken` (cookie).
2. Authenticated requests must include:  
   `Authorization: Bearer <accessToken>`
3. If accessToken expires, call `/auth/reissue` → returns a new token if valid refreshToken is present.
4. On logout, `/auth/logout` deletes refresh token from DB and expires cookie.

---

## 🧪 Running Locally

### Prerequisites

- Java 17
- MongoDB running on `localhost:27017`

### Build & Run

```bash
./gradlew build
java -jar build/libs/SNAPSHOT.jar
```

### Using Docker

```bash
docker build -t tablula .
docker run -p 8080:8080 tablula
```

---

## 🧾 Sample JWT Payload

```json
{
  "sub": "userId",
  "role": "user",
  "iat": 1718773152,
  "exp": 1718776752
}
```

---

## 🔗 Related Repositories

| Module     | Repository |
|------------|------------|
| Auth + Profile (Tablula) | https://github.com/EYEPATH-EMBEDDED/tablula |
| AI Inference (Argus)     | https://github.com/EYEPATH-EMBEDDED/argus |
| Usage Tracking (Fiscus)  | https://github.com/EYEPATH-EMBEDDED/fiscus |
| AI Model Code (YOLO + ConvLSTM) | https://github.com/EYEPATH-EMBEDDED/AI.git |
| Android Client App       | https://github.com/EYEPATH-EMBEDDED/EYEPATH-APP.git |

---

## 👥 Contributors

> Dankook University  
> Department of Mobile System Engineering (Embedded Systems)

- Kim Woosung  
- Lee Youngjoo  
- Lim Seokbeom
