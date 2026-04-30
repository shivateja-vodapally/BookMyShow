# 🎟️ BookMyShow - Movie Ticket Booking System (Spring Boot + Kafka)

## 🚀 Overview


This project is a full-stack Spring Boot BookMyShow-style application for browsing shows, booking seats, and sending Kafka-based email notifications.
It allows users to browse shows, select seats, make payments, and receive ticket confirmations via email.

The system is designed using **Spring Boot microservice-style architecture** with **Kafka for asynchronous communication**.

---

## 🧠 Key Features

* 🔍 Browse shows by city
* 🎬 View theatres and show timings
* 💺 Real-time seat availability
* 🔒 Seat locking mechanism to avoid double booking
* 💳 Simulated UPI payment
* 🎟️ Ticket generation
* 📧 Email notification using Kafka (async)

---

## 🏗️ Architecture

```
Frontend (HTML + JS)
        ↓
Spring Boot Controllers
        ↓
Service Layer (Business Logic)
        ↓
Repository Layer (JPA)
        ↓
SQL Server Database

        ↓ (Async)
Kafka Topic → Email Consumer → Email भेजना
```

---

## 🔁 Application Flow

1. User enters city → fetches shows
2. Select show → fetch available seats
3. Select seats → proceed to payment
4. Payment success:

   * Seats marked as BOOKED
   * Ticket created
   * Kafka event published
5. Email service consumes event and sends ticket email

---

## 🧩 Tech Stack

### Backend

* Java 17
* Spring Boot
* Spring Web
* Spring Data JPA
* SQL Server
* Kafka
* Lombok

### Frontend

* HTML
* CSS
* JavaScript (Vanilla)

### Messaging

* Apache Kafka

### Email

* Spring Boot Mail (JavaMailSender)

---

## 📦 Project Structure

```
controller/   → REST APIs
service/      → Business logic
repository/   → DB interaction
models/       → Entities
dto/          → Data transfer objects
events/       → Kafka events
```

---

## 🔐 Seat Booking Design (Important)

* `Seat` → Physical seat (A1, A2)
* `ShowSeat` → Seat for a specific show

### Status flow:

```
AVAILABLE → LOCKED → BOOKED
```

This prevents:

* Double booking
* Race conditions

---

## ⚡ Kafka Flow

```
PaymentService
    ↓
Kafka Topic (ticket-booked)
    ↓
TicketEmailConsumer
    ↓
Send Email
```

---

## 📡 APIs

### 1. Get Shows by City

```
GET /api/bms/home/{city}
```

### 2. Get Seats by Show

```
GET /api/bms/shows/{showId}/seats
```

### 3. Payment API

```
POST /api/bms/payments/upi
```

---

## ⚙️ Configuration

### Kafka

```
spring.kafka.bootstrap-servers=localhost:9092
```

### Email

```
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your-email
spring.mail.password=your-app-password
```

---

## 🚀 How to Run

1. Start Kafka & Zookeeper
2. Run Spring Boot application
3. Open browser:

```
http://localhost:8080/index.html
```

---

## 🧠 Design Highlights (Interview Points)

* Constructor-based dependency injection
* Transaction management using `@Transactional`
* Isolation level: `SERIALIZABLE` for seat booking
* Event-driven architecture using Kafka
* Separation of concerns (Controller → Service → Repository)

---

## ⚠️ Improvements (Future Scope)

* Real payment gateway integration
* Redis for seat locking
* Authentication & Authorization (JWT)
* UI upgrade (React)
* Docker deployment

---

## 👨‍💻 Author

**Shiva Teja Vodapally**

---

## ⭐ If you like this project

Give it a ⭐ on GitHub!

## Additional Note

This project supports Kafka-based email notifications.  
Line A - resolved after commit 3
Merge-commit - Testing the merge commit
