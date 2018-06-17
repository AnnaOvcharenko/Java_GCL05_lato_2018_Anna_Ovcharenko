package clientserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

//All client functionalities are here, based on an example
public class CustomClient {

    //consants for switch in clientLogic
    private static final int PING_FRAME = 2;
    private static final int LOGOUT_FRAME = 1;
    private static final int ECHO_FRAME = 3;
    private static final int DISCONNECT_FRAME = 4;

    //flags
    private boolean connected = false;
    private boolean looped = false;

    //Socked and therad for particular client
    private Socket clientSocket;
    private Thread clientThread;

    //flag, indicates if client is logged in or not
    private Boolean loggedIn = false;

    //data streams for communication with server
    DataInputStream input;
    DataOutputStream output;

    public CustomClient() {

    }

    public boolean isConnected() {
        return this.connected;
    }

    public boolean isLooped() {
        return this.looped;
    }

    //Starts connection
    public void connect(String host, int port) throws IOException {
        if (this.connected) {
            throw new IOException("Client is already connected.");
        }

        this.clientSocket = new Socket(host, port);
        this.input = new DataInputStream(clientSocket.getInputStream());
        this.output = new DataOutputStream(clientSocket.getOutputStream());
        this.clientThread = new Thread() {
            @Override
            public void run() {
                try {
                    //function responsible for doing logic an dcommunication with server
                    CustomClient.this.performClient(CustomClient.this.clientSocket);
                } catch (IOException ex) {
                    Logger.getLogger(CustomClient.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    Utils.close(CustomClient.this.clientSocket);
                    CustomClient.this.connected = false;
                }
            }
        };

        this.looped = true;

        this.clientThread.start();
        this.connected = true;
    }

    //disconnect, makes client exit the loop, closes the socekt and waits for others for 20 ms
    public void disconnect() {
        if (this.connected) {
            this.looped = false;

            Utils.close(this.clientSocket);
            Utils.join(this.clientThread, 20);
        }
    }

    //login function, send a logic+password combination to a server, returns either success or failure
    public boolean login(String username, String password) throws IOException {
        this.output.writeUTF(username);
        this.output.flush();
        this.output.writeUTF(password);
        this.output.flush();
        return this.input.readBoolean();
    }

    //logout function, send to server info about logging out
    public void logout() throws IOException {
        loggedIn = false;
        this.output.writeBoolean(false);
    }

    //sends and int number to a server, cheks if it can rceive it properly, returns either success or failure
    public boolean ping() throws IOException {
        output.writeInt(PING_FRAME);
        this.output.flush();
        return this.input.readBoolean();
    }

    //sens some text to echo on a server, receives it back, returns either success or failure
    public boolean echo(String text) throws IOException {
        this.output.writeUTF(text);
        this.output.flush();

        String response = this.input.readUTF();
        if (response.equals(text)) {
            System.out.println(response);
            return true;
        } else {
            System.out.println("Echo error");
            return false;
        }
    }

    //does everything you need
    private void performClient(Socket clientSocket) throws IOException {

        while (this.looped) {
            //asks for login and password
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Login as:");
            String login = keyboard.nextLine();
            System.out.println("Password for " + login + ":");
            String password = keyboard.nextLine();

            //tries to log in on a server
            loggedIn = login(login, password);

            //part only for logged users
            if (loggedIn) {
                while (this.looped && loggedIn) {
                    System.out.println("Select an option: 1- logout, 2- ping, 3 - echo text, 4 -disconnect");
                    int chosenOption = keyboard.nextInt();
                    output.writeInt(chosenOption); //sends the chosen option to a server
                    output.flush();

                    switch (chosenOption) {
                        case PING_FRAME:
                            if (ping()) {
                                System.out.println("Connection is working");
                            } else {
                                System.out.println("No response to ping");
                            }
                            break;
                        case LOGOUT_FRAME:
                            logout();
                            break;
                        case ECHO_FRAME:
                            
                            String textToEcho = keyboard.nextLine();;
                            echo(textToEcho);
                            break;
                        case DISCONNECT_FRAME:
                            this.disconnect();
                            Utils.close(output);
                            Utils.close(input);
                            break;
                    }
                }
            } else {
                System.out.println("Invalid username or password");
            }

        }

    }

}
