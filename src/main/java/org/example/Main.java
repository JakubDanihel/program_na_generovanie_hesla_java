package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Generator generator = new Generator(scanner);

        generator.mainLoop();
        scanner.close();
    }
}