package Tester;

import Data.Json;

import javax.xml.parsers.DocumentBuilderFactory;
import java.util.Scanner;

public class Run {
    public static void main(String[] args) {
        String motorCycle;

        do {
            Scanner s = new Scanner(System.in);
            System.out.print("Please enter a motorcycle's licensing number (Only digits): ");
            motorCycle = s.nextLine();
            System.out.println();
            Json data = new Json(motorCycle);
            System.out.println("The details of vehicle no. "+motorCycle+" are:");
            System.out.println();
            data.printJsonData();
            System.out.println();
        } while (motorCycle.length() > 2);
    }
}

