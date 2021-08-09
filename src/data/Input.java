package data;

import hospital.Doctor;
import hospital.Hospital;
import hospital.Surgeon;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Input {



    public static LocalDate getValidDate() {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate localDate = null;     //to keep compiler happy
        boolean isNotValid = true;
        while (isNotValid) {
            String input = scanner.nextLine();
            try {
                localDate = LocalDate.parse(input,formatter);
                isNotValid = false;
            }
            catch (IllegalArgumentException e) {
                System.out.println("It is invalid date.Please try again.");
            }
        }
        return localDate;
    }

    public static int getValidInteger() {
        Scanner scanner = new Scanner(System.in);
        int value = -1;   //to keep compiler happy
        boolean isNotValid = true;
        while (isNotValid) {
            String input = scanner.nextLine();
            try {
                value = Integer.parseInt(input);
                isNotValid = false;
            }
            catch (IllegalArgumentException e) {
                System.out.println("It is invalid integer.Please try again.");
            }
        }
        return value;
    }


    public static Surgeon getValidSurgeon(Hospital hospital) {
        Scanner scanner = new Scanner(System.in);
        Surgeon surgeon = null;   //to keep compiler happy
        boolean isNotValid = true;
        while(isNotValid) {
            String input = scanner.nextLine();
            Doctor doctor = hospital.getDoctor(input);
            if (doctor != null) {
                if (doctor.getType().equals("Surgeon")) {
                    surgeon =(Surgeon) doctor;
                    isNotValid = false;
                }else {
                    System.out.println("There is no such surgeon with name: "+input);
                    System.out.println("Please try again.");
                }
            }else {
                System.out.println("There is no such surgeon with name "+input);
                System.out.println("Please try again.");
            }
        }
        return surgeon;
    }


}
