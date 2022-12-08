package src.heimaufgaben;

import java.net.*;
import java.util.*;
import java.io.*;
import java.text.*;

public class Server {

    private final ServerSocket serv;


    public Server(int port) {
        try {
            if (port == 2022) {
                serv = new ServerSocket(2022);
            } else {
                System.err.println("Kein korrekter Port:");
                System.err.println("Aktuell ist nur Port 2022 erlaubt.");
                throw new IllegalArgumentException("Der Server wurde beendet");
            }

        } catch (IOException e) {
            System.err.println("couldn't initiate Server");
            throw new RuntimeException(e);
        }
    }

    public void run() {
        try {
            Socket client = serv.accept();
            DataInputStream ClientInput = new DataInputStream(client.getInputStream());
            DataOutputStream ClientOutput = new DataOutputStream(client.getOutputStream());
            boolean closer = true;
            String Input;
            while (closer) {
                System.out.println("Eingabe: ");
                Input = ClientInput.readUTF();

                if (Input.matches("PING")) {
                    ClientOutput.writeUTF("PONG");
                }

                if (Input.startsWith("ECHO ")) {
                    ClientOutput.writeUTF(Input);
                }

                if (Input.matches("CURRENT DATE")) {
                    SimpleDateFormat DF = new SimpleDateFormat("dd.MM.yyyy");
                    ClientOutput.writeUTF("DATE: " + DF.format(new Date()));
                }
                if (Input.matches("CURRENT TIME")) {
                    SimpleDateFormat TF = new SimpleDateFormat("HH:mm:ss");
                    ClientOutput.writeUTF("TIME: " + TF.format(new Date()));
                }
                if (Input.matches("CLOSE")) {
                    closer = false;
                    client.close();
                } else {
                    ClientOutput.writeUTF("Keine gültige Eingabe.");
                }

            }
            ClientOutput.writeUTF("Die Verbindung zum Server wurde beendet.");
            ClientInput.close();
            ClientOutput.close();
            serv.close();


        } catch (SocketException e) {
            System.err.println("Error 500 Bad Gateway");
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
