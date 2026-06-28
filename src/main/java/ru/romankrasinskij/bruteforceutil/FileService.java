package ru.romankrasinskij.bruteforceutil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileService {

    private static File passwordDictionary;
    private static List<String> passwordList;
    private static String grantedPassword;

    public static void createFile() {
        passwordDictionary = new File("dictionary.txt");

        if (passwordDictionary.exists()) {
            System.out.println("The dictionary was successfully loaded.");
        } else {
            System.out.println("The dictionary wasn't found. Please, put it to project folder.");
        }
    }

    public static void convertPasswordFileToList() {
        passwordList = new ArrayList<>();

        try (Scanner fileScanner = new Scanner(passwordDictionary)) {
            while (fileScanner.hasNextLine()) {
                passwordList.add(fileScanner.nextLine());
            }
        } catch (Exception e) {
            System.out.println("An error occured!");
            e.printStackTrace();
        }
    }

    private static void readPasswordFromConsole() {
        try (Scanner passwordScanner = new Scanner(System.in)) {
            System.out.print("Enter password: ");
            grantedPassword = passwordScanner.nextLine();
        } catch (Exception e) {
            System.out.println("An error occured!");
            e.printStackTrace();
        }
    }

    private static void findGrantedPasswordInDictionary() {
        int stepsCount = 0;

        for (String password : passwordList) {
            stepsCount++;

            if (password.equals(grantedPassword)) {
                System.out.println("The granted password was found in a dictionary: " + password);
                break;
            }

            else if (stepsCount == passwordList.size()) {
                System.out.println("The granted password wasn't found in a dictionary.");
            }
        }
    }

    public static void run() {
        createFile();
        convertPasswordFileToList();
        readPasswordFromConsole();

        long startTime = System.currentTimeMillis();
        findGrantedPasswordInDictionary();
        long searchingTime = System.currentTimeMillis() - startTime;

        System.out.println("Searching time: " + searchingTime + " ms");
    }
}
