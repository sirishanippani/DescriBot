# DescriBot

DescriBot is a Spring Boot + OpenAI powered prototype that generates catchy, human-like product descriptions from just a product name and category. It comes with a clean REST API and a cute pastel-themed single-page UI.

Live On: https://describot.onrender.com/

## Features
- Java Spring Boot CRUD backend
- GPT-powered automatic product descriptions
- Vanilla JS + HTML/CSS frontend with loading spinner
- Playful fallback messages
- Modular and easy to extend

## How to Use
1. Clone this repository
2. Add your OpenAI API key to `src/main/resources/application.properties`
3. Run the app with `./mvnw spring-boot:run`
4. Navigate to `http://localhost:8080/index.html`
5. Enter a product name and category, hit generate, and see the description appear!

## Screenshots
Add screenshots of your pastel-themed UI here.

## Tech Stack
- Java 17
- Spring Boot, Spring Data JPA, WebClient
- OpenAI GPT-3.5 Turbo
- Vanilla JavaScript, HTML, and CSS
