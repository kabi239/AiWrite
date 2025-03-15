# AiWrite Backend

AiWrite is a Spring Boot application that generates essays based on user input. The backend service interacts with the Gemini API to generate the content.

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Configuration](#configuration)
- [Frontend](#frontend)
- [License](#license)

## Installation

1. Clone the repository:

    ```bash
    git clone https://github.com/yourusername/aiwrite.git
    cd aiwrite/backend
    ```

2. Build the project using Maven:

    ```bash
    mvn clean install
    ```

3. Run the Spring Boot application:

    ```bash
    mvn spring-boot:run
    ```

## Usage

The backend service provides an API endpoint to generate essays. You can use tools like Postman or cURL to interact with the API.

## API Endpoints

### Generate Essay

- **URL:** `/api/generate`
- **Method:** `POST`
- **Content-Type:** `application/json`
- **Request Body:**

    ```json
    {
        "topic": "Your essay topic",
        "word_count": 500,
        "no_of_paragraphs": 5,
        "level": "BEGINNER"
    }
    ```

- **Response:**

    ```json
    {
        "topic": "Your essay topic",
        "content": "Generated essay content"
    }
    ```

## Configuration

The application uses properties defined in the `application.properties` file to configure the Gemini API key and URL.

- **application.properties:**

    ```properties
    gemini.api.key=YOUR_GEMINI_API_KEY
    gemini.api.url=https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=${gemini.api.key}
    ```

## Frontend

The frontend is a simple HTML, CSS, and JavaScript application that interacts with the backend API to generate essays.
