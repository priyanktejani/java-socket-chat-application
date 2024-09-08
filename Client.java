import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;

    public Client(Socket socket, String username) {
        try {
            this.socket = socket;

            // to send the data use output stream or to read input stream 
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.username = username;
        } catch (IOException e) {
            closeConnection(socket, bufferedWriter, bufferedReader);
        }
    }

    public void sendMessage() {
        try {
            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            Scanner scanner = new Scanner(System.in);
            while (socket.isConnected()) {
                String messageToSend = scanner.nextLine();
                bufferedWriter.write(username + ": " + messageToSend);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
            scanner.close();
        } catch (IOException e) {
            closeConnection(socket, bufferedWriter, bufferedReader);
        }
    }

    public void listenMessage() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                String messaageFromGroupChat;

                while (socket.isConnected()) {
                    try {
                        messaageFromGroupChat = bufferedReader.readLine();
                        System.out.println(messaageFromGroupChat);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            }

        }).start();
    }

    public void closeConnection(Socket socket, BufferedWriter bufferedWriter, BufferedReader bufferedReader) {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws UnknownHostException, IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username");

        // get user name
        String username = scanner.nextLine();

        // create new socket with unique port
        Socket socket =  new Socket("localhost", 1236);

        // create new client each time run method is called
        Client client = new Client(socket, username);
        client.listenMessage();
        client.sendMessage();
        scanner.close();
    }

}
