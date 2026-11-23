# 🚁 Drone Medication Delivery API

A **Spring Boot REST API** that manages a fleet of **drones delivering medications**. You can register drones, load them with medications, check what each drone is carrying, and monitor battery levels. A scheduled job keeps a **battery audit log** for every drone.

## ✨ What it does
- 🛸 **Register drones** with a serial number, model, weight limit and battery capacity
- 💊 **Load medications** onto a drone, with validation on medication name and code
- ⚖️ **Weight limit check**: a drone can't carry more than its maximum weight
- 🔋 **Battery rule**: a drone can't be loaded when its battery is **25% or lower**
- 📋 List drones **available for loading**
- ⏱️ **Scheduled battery audit** every 10 seconds, saved as an event history log
- 🗄️ **In-memory H2 database**, so it runs with zero setup

### Drone models and states
| Models | States |
|--------|--------|
| `LIGHTWEIGHT`, `MIDDLEWEIGHT`, `CRUISERWEIGHT`, `HEAVYWEIGHT` | `IDLE` → `LOADING` → `LOADED` → `DELIVERING` → `DELIVERED` → `RETURNING` |

## 🛠️ Tech stack
| Layer | Technology |
|-------|------------|
| Language | Java 8 |
| Framework | Spring Boot 2.6 (Web, Data JPA, Validation, Data REST) |
| Database | H2 (in-memory) |
| Scheduling | Spring `@Scheduled` |
| Testing | JUnit, Spring Boot Test |

## 🚀 Run it
The application lives in the `drone-web-service` folder.

~~~bash
git clone https://github.com/LazaroDevelop/Spring-REST-API-for-drones.git
cd Spring-REST-API-for-drones/drone-web-service
./mvnw spring-boot:run
~~~

## 📡 API tour
Base path: `/v1/api`

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/ping` | Health check |
| `GET` | `/drone-all` | List all drones |
| `POST` | `/drone-new` | Register a new drone |
| `PUT` | `/add-medication/{serialNumber}` | Load a medication onto a drone |
| `GET` | `/drone-medications/{serialNumber}` | List the medications loaded on a drone |
| `GET` | `/drone-battery-level/{serialNumber}` | Check a drone's battery level |
| `GET` | `/drone-for-loading` | List drones available for loading |
| `GET` | `/show-log-history` | Battery audit history |

### Example: register a drone
~~~bash
curl -X POST http://localhost:8080/v1/api/drone-new \
  -H "Content-Type: application/json" \
  -d '{
    "serialNumber": "DRN-0001",
    "model": "LIGHTWEIGHT",
    "weightLimit": 500,
    "batteryCapacity": 100,
    "state": "IDLE"
  }'
~~~

## 🏗️ Project structure
~~~text
drone-web-service/src/main/java/.../dronewebservice
├── controller   # DispatchController
├── entity       # DroneEntity, MedicationEntity, CheckBatteryEvent
├── enums        # EModels, EState
├── exceptions   # Weight limit, low battery, drone not found
├── repository   # Spring Data JPA repositories
└── service      # DroneService, CheckBatteryService (scheduled audit)
~~~

## 🗺️ Roadmap
- [ ] Upgrade to Java 21 and Spring Boot 3
- [ ] Add unit and integration tests for the business rules
- [ ] Add OpenAPI documentation
- [ ] Add a Dockerfile and a GitHub Actions build

## 👤 Author

**Lazaro Noel Guerra Medina**, Java Backend Engineer

> *"Make it work, make it right, make it fast."* ☕
