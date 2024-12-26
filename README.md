## Custom Web Server
A lightweight, extensible web server built from scratch in Java that supports dynamic endpoint registration and request handling. The primary goal is to replace Node.js backend dependencies in URL shortening applications with this custom server implementation.
Features

### Custom HTTP Server: Built using Java ServerSockets listening on port 8080
### Concurrent Request Handling: Multithreaded architecture for handling multiple simultaneous requests
### Dynamic Endpoint Registration: Register custom endpoints and map them to specific functions
### Efficient Path Mapping: TRIE-based path resolution for optimal routing performance
### Java Reflection: Dynamic method execution based on registered endpoints
### Request Support: Currently handles GET and POST methods
### Response Types: Supports HTML and String response formats
### Object Mapping: Uses Jackson Mapper for automatic request body to object mapping

## Technical Stack

### Java
### Java Reflection API
### Jackson Mapper
### HTTP 1.1 Protocol
### Computer Networks fundamentals

## Project Goal
The primary objective is to create a self-sufficient web server that can replace Node.js backend dependencies in my previous URL shortening applications. This server aims to provide all necessary functionality to handle:

User request processing
Concurrent connection management
Database operations for URL storage
Custom endpoint handling

## Architecture
The server utilizes a custom implementation of the HTTP 1.1 protocol, leveraging Java's ServerSocket for network communications. It employs a TRIE data structure for efficient endpoint routing and Java Reflection for dynamic method invocation, allowing for flexible request handling and response generation.

## Prerequisites
Java JDK 8 or higher
Maven (for dependency management)
