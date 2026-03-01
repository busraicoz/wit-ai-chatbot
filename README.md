# ING Women in Tech AI Chatbot

This project is a Retrieval-Augmented Generation (RAG) chatbot built with Spring Boot and Spring AI, powered by Google Vertex AI Gemini. It allows you to chat with an LLM (Gemini) that can answer questions using both its own knowledge and your own documents. The chatbot demonstrates how to combine enterprise data with generative AI for secure, context-aware conversations, and includes a simple ING-branded web UI for interaction.

## Features
- Chat with Gemini (Vertex AI) using your own documents
- RAG pipeline with vector search
- Simple web UI
- Easy configuration via `application.properties`
- Optional: Citations/Grounding (show sources for answers)

## Prerequisites
- Java 21+
- Maven 3.8+
- Google Cloud account with Vertex AI enabled
- Vertex AI Gemini API access and project credentials

## Setup
1. **Clone the repository:**
   ```sh
   git clone <repo-url>
   cd wit-ai-chatbot
   ```
2. **Configure application properties:**
   Edit `src/main/resources/application.properties` and set your GCP project ID and location:
   ```ini
   spring.ai.vertex.ai.gemini.project-id=YOUR_PROJECT_ID
   spring.ai.vertex.ai.gemini.location=europe-west4
   spring.ai.vertex.ai.gemini.chat.options.model=gemini-2-pro
   spring.ai.vertex.ai.embedding.options.model=gemini-embedding-001
   spring.ai.vertex.ai.embedding.project-id=YOUR_PROJECT_ID
   spring.ai.vertex.ai.embedding.location=europe-west4
   ```
3. **Build the project:**
   ```sh
   mvn clean package
   ```
4. **Run the application:**
   ```sh
   mvn spring-boot:run
   ```
5. **Access the chatbot UI:**
   Open [http://localhost:8080/](http://localhost:8080/) in your browser.

## Document Ingestion
- Place your PDF or text files in `src/main/resources/documents/` (e.g., `policy.pdf`).
- The ingestion service will process these files for RAG context.

## API
- **POST** `/api/chat` — Send a chat message. Expects `{ "query": "your text" }` and returns `{ "response": "model reply" }`.

## Project Structure
- `src/main/java/com/ing/wit/ai/` — Main Java source code
- `src/main/resources/` — Configuration, static files, and guides
- `src/main/resources/static/` — Web UI
- `src/main/resources/documents/` — Your ingested documents
