## Running the Project with Docker

This project provides a Docker setup for building and running the Java-based Patient Service application. The Docker configuration uses multi-stage builds for efficient image creation and runs the application as a non-root user for improved security.

### Project-Specific Docker Requirements
- **Base Image:** Uses `eclipse-temurin:21-jdk` for building and `eclipse-temurin:21-jre` for running (Java 21).
- **Build Tool:** Maven Wrapper (`mvnw`) is used for building the project inside the container.
- **Application Port:** The service listens on port **4000** (exposed in both Dockerfile and Docker Compose).
- **User:** Runs as a non-root user (`appuser`) inside the container.

### Environment Variables
- No required environment variables are specified in the Dockerfile or Docker Compose file.
- If you add environment variables, you can use a `.env` file and uncomment the `env_file` line in `docker-compose.yml`.

### Build and Run Instructions
1. **Build and start the service:**
   ```sh
   docker compose up --build
   ```
   This will build the Docker image and start the `java-patient_service` container.

2. **Accessing the Service:**
   - The application will be available on [http://localhost:4000](http://localhost:4000)

### Special Configuration
- No external services (such as databases) are configured or required by default.
- No persistent volumes or custom networks are defined.
- If you add a database or other services, update `docker-compose.yml` accordingly.

### Ports
- **4000:** Exposed by the service for application access.

---

*If you make changes to the application's configuration (such as adding environment variables or external services), update the Docker Compose file and this section accordingly.*
