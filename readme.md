# Java Socket Chat Application

This project is a simple multi-client chat application implemented using Java sockets. It allows multiple clients to connect to a server and communicate with each other in a group chat format. The project demonstrates the use of Java networking concepts such as `ServerSocket`, `Socket`, threading, and I/O streams.

## Features

- **Multi-Client Support**: Multiple clients can connect to the server and send messages to each other in real-time.
- **Broadcasting Messages**: Messages sent by one client are broadcasted to all other connected clients.
- **Client Handling**: Each client is handled in a separate thread, ensuring simultaneous communication between multiple clients.
- **Server Management**: The server can start, accept client connections, and manage the lifecycle of these connections.

## Project Structure

- **Server.java**: This class represents the server that listens for incoming client connections on a specified port. It accepts connections, creates a new `ClientHandler` for each client, and runs each handler in a separate thread.

- **ClientHandler.java**: This class is responsible for managing the communication between the server and a single client. It reads messages from the client and broadcasts them to all other clients. It also handles the cleanup when a client disconnects.

- **Client.java**: This class represents a client that can connect to the server. It handles sending messages to the server and listening for incoming messages from other clients. Each client runs in its own thread.

## How to Run

### Running the Server

1. Compile the Java files:
    ```bash
    javac Server.java ClientHandler.java Client.java
    ```

2. Start the server:
    ```bash
    java Server
    ```
   The server will start and listen for client connections on port `1236`.

### Running the Client

1. Compile the Java files if not already compiled:
    ```bash
    javac Server.java ClientHandler.java Client.java
    ```

2. Start a client:
    ```bash
    java Client
    ```
   The client will prompt you to enter a username. After entering your username, you will join the chat room and can start sending messages.

3. You can run multiple clients by opening multiple terminal windows and running the above command in each window.

## Example Usage

1. Start the server in one terminal window.
2. Run multiple clients in separate terminal windows.
3. Enter a username for each client.
4. Start chatting! Messages sent by one client will be seen by all other connected clients.

## Notes

- The server listens on port `1236`. If this port is already in use on your system, you can change it in the `Server.java` file.
- The client currently connects to `localhost`. If you want to run the server and clients on different machines, you need to replace `"localhost"` in the `Client.java` file with the server's IP address.