# SecureVault API 🛡️

Implementación de referencia para una API bancaria transaccional construida bajo principios de **Security-by-Design**.

Este proyecto demuestra cómo proteger datos financieros sensibles y mitigar vulnerabilidades comunes (OWASP Top 10) utilizando Java Enterprise y criptografía aplicada, sin comprometer el rendimiento.

---

## 🔒 Security Features

A diferencia de una API estándar, SecureVault implementa capas de defensa en profundidad:

### 1. Column-Level Encryption (TDE)
Los datos PII (Identificación Nacional, Email) nunca se guardan en texto plano.
- **Implementación:** `AttributeConverter` personalizado con **AES-256**.
- **Resultado:** Si la base de datos es comprometida, los datos son ilegibles sin la llave de aplicación.

### 2. Anti-Brute Force / Rate Limiting
Protección proactiva en endpoints de autenticación usando **Bucket4j**.
- **Regla:** Algoritmo *Token Bucket* (e.g., máx 5 intentos por minuto por IP).
- **Efecto:** Mitigación de ataques de fuerza bruta y DDoS a nivel de aplicación.

### 3. Integridad Transaccional & Auditoría
- Uso estricto de transacciones **ACID** (`@Transactional`) para movimientos financieros.
- **Audit Logs:** Registro inmutable de cada operación sensible (quién, qué, cuánto, cuándo) para trazabilidad forense.

### 4. Autenticación Stateless
- Implementación de **JWT (JSON Web Tokens)** con firma criptográfica HS256.
- Validación estricta de sesión sin estado en el servidor.

---

## 🛠️ Tech Stack

* **Core:** Java 17 (LTS), Spring Boot 3.4.
* **Database:** PostgreSQL 15 (Dockerized).
* **Security:** Spring Security 6, JJWT, Java Cryptography Architecture (JCA).
* **Tools:** Maven, Lombok, Docker Compose.

---

## 🚀 Quick Start

### Prerrequisitos
* Java 17+
* Docker & Docker Compose
* Maven (opcional, wrapper incluido)

### 1. Clonar el repositorio
```bash
git clone [https://github.com/nicolasllerenas/secure-vault-api.git](https://github.com/nicolasllerenas/secure-vault-api.git)
cd secure-vault-api
