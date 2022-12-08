package src.heimaufgaben;

public class Main {
    public static void main(String[] args) {
        Server server = new Server(2022); //noch anzupassen
        server.run();
        System.out.println("Der Server wurde beendet.");
    }
}
