package ui;

import java.io.FileNotFoundException;

// Citation: sample code from JsonSerializationDemo

public class Main {
    public static void main(String[] args) {
        try {
            new CoffeePlatform();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
