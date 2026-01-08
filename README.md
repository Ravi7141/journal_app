# Journal App

A secure and feature-rich journal application built with Spring Boot and MongoDB, allowing users to create, manage, and organize their personal journal entries with JWT authentication, weather integration, and sentiment analysis.

## 🚀 Features

- **User Authentication & Authorization**
  - JWT-based authentication
  - Role-based access control (User & Admin)
  - Secure password encryption using BCrypt

- **Journal Management**
  - Create, read, update, and delete journal entries
  - Automatic timestamp tracking
  - Sentiment analysis for entries
  - User-specific journal isolation

- **Weather Integration**
  - Real-time weather information
  - Personalized greetings with weather context

- **Admin Features**
  - User management
  - Admin account creation
  - Application cache management

- **Performance Optimization**
  - Redis caching for improved performance
  - Scheduled tasks for automated operations

- **Email Notifications**
  - Automated email service
  - User notifications and reminders

## 🛠️ Tech Stack

- **Framework:** Spring Boot 3.4.3
- **Language:** Java 21
- **Database:** MongoDB
- **Caching:** Redis
- **Security:** Spring Security + JWT (JSON Web Tokens)
- **Build Tool:** Maven
- **Libraries:**
  - Lombok (Code generation)
  - JJWT (JWT implementation)
  - Spring Boot Starter Mail
  - Spring Data MongoDB

## 📋 Prerequisites

Before running this application, ensure you have:

- Java 21 or higher
- MongoDB installed and running
- Redis server installed and running
- Maven (or use included Maven wrapper)

## ⚙️ Installation & Setup

### 1. Clone the repository
```bash
git clone <repository-url>
cd journal-app
```

### 2. Configure MongoDB
Update `src/main/resources/application.properties` with your MongoDB connection:
```properties
spring.data.mongodb.uri=mongodb://localhost:27017/journaldb
```

### 3. Configure Redis
```properties
spring.redis.host=localhost
spring.redis.port=6379
```

### 4. Configure Email Service
```properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your-email@gmail.com
spring.mail.password=your-app-password
```

### 5. Configure JWT Secret
```properties
jwt.secret=your-secret-key-here
```

## 🏃 Running the Application

### Using Maven Wrapper (Recommended)
```bash
./mvnw spring-boot:run
```

### Using Maven
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## 📡 API Endpoints

### Public Endpoints (No Authentication Required)

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/public/signup` | Register a new user |
| POST | `/public/login` | Login and receive JWT token |

### User Endpoints (Authentication Required)

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/user` | Get personalized greeting with weather |
| PUT | `/user` | Update user profile |
| DELETE | `/user` | Delete user account |

### Journal Endpoints (Authentication Required)

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/journal` | Get all journal entries of logged-in user |
| POST | `/journal` | Create a new journal entry |
| GET | `/journal/id/{id}` | Get specific journal entry by ID |
| PUT | `/journal/id/{id}` | Update a journal entry |
| DELETE | `/journal/id/{id}` | Delete a journal entry |

### Admin Endpoints (Admin Role Required)

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/admin/getAllUsers` | Get all users |
| POST | `/admin/createAdmin` | Create a new admin user |
| GET | `/admin/clear-app-cache` | Clear application cache |

## 🔐 Authentication Flow

1. **Sign Up:** POST to `/public/signup` with username and password
2. **Login:** POST to `/public/login` to receive JWT token
3. **Use Token:** Include token in `Authorization` header as `Bearer <token>` for all authenticated requests

### Example Login Request
```json
POST /public/login
{
  "username": "john_doe",
  "password": "securePassword123"
}
```

### Example Response
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

## 📝 Usage Examples

### Creating a Journal Entry
```json
POST /journal
Authorization: Bearer <your-jwt-token>

{
  "title": "My First Day",
  "content": "Today was an amazing day! I learned so much about Spring Boot."
}
```

### Updating a Journal Entry
```json
PUT /journal/id/{entryId}
Authorization: Bearer <your-jwt-token>

{
  "title": "Updated Title",
  "content": "Updated content for my journal entry."
}
```

## 🏗️ Project Structure

```
journal-app/
├── src/
│   ├── main/
│   │   ├── java/com/edigest/journalapp/
│   │   │   ├── api/          # External API responses
│   │   │   ├── cache/        # Caching configuration
│   │   │   ├── config/       # Security & Redis config
│   │   │   ├── controller/   # REST controllers
│   │   │   ├── entity/       # MongoDB entities
│   │   │   ├── enums/        # Enumerations
│   │   │   ├── filter/       # JWT filter
│   │   │   ├── repository/   # Data access layer
│   │   │   ├── scheduler/    # Scheduled tasks
│   │   │   ├── service/      # Business logic
│   │   │   └── utilis/       # Utility classes
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── pom.xml
└── README.md
```

## 🗄️ Database Schema

### User Collection
```json
{
  "_id": "ObjectId",
  "username": "string (unique)",
  "email": "string",
  "password": "string (encrypted)",
  "sentimentAnalysis": "boolean",
  "journalEntries": ["JournalEntry references"],
  "roles": ["string array"]
}
```

### Journal Entry Collection
```json
{
  "_id": "ObjectId",
  "title": "string",
  "content": "string",
  "date": "LocalDateTime",
  "sentiment": "enum (POSITIVE, NEGATIVE, NEUTRAL)"
}
```

## 🔧 Configuration

### Application Properties
Key configurations in `application.properties`:
- MongoDB connection
- Redis configuration
- JWT secret key
- Email server settings
- Server port

## 📊 Features in Detail

### Sentiment Analysis
Journal entries can be analyzed for sentiment (positive, negative, neutral) to help users track their emotional journey.

### Weather Integration
The app integrates with a weather API to provide contextual information when users access their journals.

### Scheduled Tasks
Automated tasks run periodically to:
- Send reminder emails
- Clean up old data
- Perform maintenance operations

### Caching Strategy
Redis is used to cache:
- Frequently accessed user data
- Configuration settings
- Weather information

## 🔒 Security Features

- **Password Encryption:** BCrypt hashing algorithm
- **JWT Tokens:** Stateless authentication
- **Role-Based Access:** User and Admin roles
- **Request Filtering:** JWT validation on protected endpoints
- **CORS Configuration:** Controlled cross-origin requests

## 🧪 Testing

Run tests using:
```bash
./mvnw test
```

## 🐛 Troubleshooting

### MongoDB Connection Issues
- Ensure MongoDB is running on the configured port
- Check connection string in `application.properties`

### Redis Connection Issues
- Verify Redis server is running
- Check Redis host and port configuration

### JWT Token Errors
- Ensure the JWT secret is properly configured
- Check token expiration settings

## 📈 Future Enhancements

- [ ] Frontend web application
- [ ] Mobile app integration
- [ ] Advanced sentiment analysis with ML
- [ ] Journal entry search functionality
- [ ] Export journals to PDF
- [ ] Multi-language support
- [ ] Social sharing features

## 👨‍💻 Author

**Created by Ravi Rajput**

## 🤝 Contributing

Contributions, issues, and feature requests are welcome!


