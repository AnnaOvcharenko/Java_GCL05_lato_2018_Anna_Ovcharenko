package clientserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

//Implements all server functionality, based on an example
public class CustomServer {

    //constants for switch in doClientLogic, same as for client
    private static final int PING_FRAME = 2;
    private static final int LOGOUT_FRAME = 1;
    private static final int ECHO_FRAME = 3;
    private static final int DISCONNECT_FRAME = 4;

    private boolean started = false;
    private boolean looped = false;

    //hashmap with connected clients
    private HashMap<InetAddress, Socket> clientsSockets = new HashMap<InetAddress, Socket>();

    private ServerSocket serverSocket;
    public Thread serverThread;

    //arrays of registered users and their passwords, actually, cna be another hashmap
    private String[] goodLogins;
    private String[] goodPasswords;

    private int clientNumber = 0;

    public boolean isStarted() {
        return this.started;
    }

    public boolean isLooped() {
        return this.looped;
    }

    public CustomServer() { //generates some registered users, should be written from encodedd file in theory
        goodLogins = new String[3];
        goodLogins[0] = "root";
        goodLogins[1] = "root1";
        goodLogins[2] = "root2";

        goodPasswords = new String[3];
        goodPasswords[0] = "password";
        goodPasswords[1] = "password1";
        goodPasswords[2] = "password2";
    }

    //starts client in a new thread
    private void prepareClient(final Socket clientSocket) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    clientNumber++;
                    System.out.println("Client number " + clientNumber + " is connected");
                    CustomServer.this.doClientLogic(clientSocket);
                } catch (IOException ex) {
                    Logger.getLogger(CustomServer.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    CustomServer.this.releaseClient(clientSocket);
                }
            }
        };
        thread.start();

        //adds an opened socked to a hashmap
        synchronized (this.clientsSockets) {
            this.clientsSockets.put(clientSocket.getInetAddress(), clientSocket);
        }
    }

    //closes a client, from an example
    private void releaseClient(Socket clientSocket) {
        Utils.close(clientSocket);

        synchronized (this.clientsSockets) {
            this.clientsSockets.remove(clientSocket.getInetAddress());
            this.clientNumber--;
        }
    }

    //closes all client, from an example
    private void finalizeClients() {
        synchronized (this.clientsSockets) {
            for (Socket el : this.clientsSockets.values()) {
                Utils.close(el);
            }
        }

        while (true) {
            synchronized (this.clientsSockets) {
                if (this.clientsSockets.size() == 0) {
                    break;
                }
            }
            Utils.sleep(100);
        }
    }

    //starts a server    
    public void run(int port) throws IOException {

        if (this.started) {
            throw new IOException("Server is already started.");
        }

        this.serverSocket = new ServerSocket(port);
        this.serverThread = new Thread() {
            @Override
            public void run() {
                while (CustomServer.this.looped) {
                    Socket clientSocket = null;

                    try {
                        //waits for clients to connect
                        clientSocket = CustomServer.this.serverSocket.accept();
                    } catch (IOException e) {
                        CustomServer.this.looped = false;
                        Utils.close(CustomServer.this.serverSocket);
                        break;
                    }
                    if (CustomServer.this.clientNumber >= 5) //checks if there are too many ckients
                    {

                        Logger.getLogger(CustomServer.class.getName()).log(Level.SEVERE, null, new IOException("Too many clients"));

                    } else {
                        CustomServer.this.prepareClient(clientSocket);
                    }

                }
                //closes everything		
                CustomServer.this.finalizeClients();
                CustomServer.this.started = false;
            }
        };

        this.looped = true;

        this.serverThread.start();
        this.started = true;
    }

    //stops the server
    public void stop() {
        if (this.started) {
            this.looped = false;

            Utils.close(this.serverSocket);
            Utils.join(this.serverThread);
        }
    }

    //does everything you need
    private void doClientLogic(Socket socket) throws IOException {
        try (DataInputStream input = new DataInputStream(socket.getInputStream());//why!??
                DataOutputStream output = new DataOutputStream(socket.getOutputStream());) {
            while (!Thread.interrupted()) {
                //data streams for communication with client
                int chosenOption;

                //reads username and passwod from client
                String username = input.readUTF();
                String password = input.readUTF();

                //checks if they are in the set of registered users
                Boolean canBeLogged = false;
                for (int i = 0; i < goodLogins.length; i++) {
                    if ((goodLogins[i].equals(username)) && (goodPasswords[i].equals(password))) {
                        canBeLogged = true;
                        break;
                    }
                }

                //returns to a client if login was succesful or not
                output.writeBoolean(canBeLogged);
                output.flush();

                //loop for logged users
                while (!Thread.interrupted() && canBeLogged) {
                    //reads a command for a client
                    chosenOption = input.readInt();

                    String receivedText;

                    switch (chosenOption) {
                        case PING_FRAME: //responses to ping
                            int tmp = input.readInt();
                            if (tmp == PING_FRAME) {
                                output.writeBoolean(true);
                                output.flush();
                            } else {
                                output.writeBoolean(false);
                                output.flush();
                            }
                            break;
                        case LOGOUT_FRAME: //logouts a users
                            canBeLogged = input.readBoolean();
                            break;
                        case ECHO_FRAME: //sends back an echoText
                            receivedText = input.readUTF();
                            output.writeUTF(receivedText);
                            output.flush();
                            break;
                        case DISCONNECT_FRAME: //disconnects a client
                            CustomServer.this.releaseClient(socket);
                            Utils.close(output);
                            Utils.close(input);
                    }
                }

            }
        }
    }
}
