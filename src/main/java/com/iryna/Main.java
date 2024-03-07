package com.iryna;

import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
//        String className = "com.iryna.Main";
        // Get input string
        String className = sc.nextLine();
        // Create inspector class
        ClassInspector inspector = new ClassInspector();
        // Get output string
        System.out.println(inspector.inspect(className));
    }
}
