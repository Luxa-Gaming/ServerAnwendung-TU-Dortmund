package heimaufgaben;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server{
    private boolean active;
    private ServerSocket server;
    private static int port;

    public Server() {
        try {
                server = new ServerSocket(port);
                active = true;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void running() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bitte Port angeben: ");
        String eingabe;
        String closed = "Der Server wurde beendet.";
        try{
            port = scanner.nextInt();
            if(port == 2022) {
                System.out.println("Port festgelegt.");
                active = true;
            }else{
                System.err.println("Aktuell ist nur Port '2022' auswählbar.");
                throw new IllegalArgumentException(closed);
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.out.println(closed);
        }
        System.out.println("Zum beenden des Servers bitte 'exit' eingeben.");
        eingabe = scanner.nextLine();
        if(eingabe.equalsIgnoreCase("exit")) {
            active = false;
            System.out.println(closed);
        }

        while(active) {
            try {
                System.out.println("Warten auf Clienten " + server.getLocalPort());
                Socket client = server.accept();
                DataOutputStream ServerOutput = new DataOutputStream(client.getOutputStream());
                if(client.isConnected()) {
                    ServerOutput.writeUTF("Herzlich willkommen auf dem Server!");
                }
                DataInputStream ServerInput = new DataInputStream(client.getInputStream());
                String input = ServerInput.readUTF();
                System.out.println("Server ist gestartet.");
                ServerOutput.writeUTF("Befehle werden empfangen: ");
                ServerOutput.writeUTF("$ ");
                System.out.println(functions.basis(input));


            } catch (IOException e) {
                active = false;
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Server.jar geladen.");
    }

}
