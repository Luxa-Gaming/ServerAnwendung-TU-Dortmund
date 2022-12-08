package heimaufgaben;

import java.time.LocalDate;
import java.time.LocalTime;

public class functions {

    public static String basis(String input) {
        String output = "";
        switch (input) {
            case "PING" -> output = "PONG";
            case "ECHO " -> output = input.substring(5);
            case "CURRENT DATE" -> output = "DATE: " + LocalDate.now();
            case "CURRENT TIME" -> output = "TIME: " + LocalTime.now();
            default -> System.out.println("Ungültige Eingabe");
        }
        return output;
    }

}
