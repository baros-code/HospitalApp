package hospital;

import java.util.Scanner;

public class Receptionist {




    public void registerPatient(Hospital hospital,Scanner in) {
        System.out.println("Enter patient name.");
        String patientName = in.nextLine();
        System.out.println("Enter patient type should be one of : Walking/Emergency/Inmate");
        String patientType = in.nextLine();
        System.out.println("Enter the doctor name.");
        String doctorName = in.nextLine();
        Patient patient;
        try {
            if (patientType.equals("Walking")) {
                patient = new WalkingPatient(patientName);
            }
            else if(patientType.equals("Emergency")) {
                patient = new EmergencyPatient(patientName);
            }else if(patientType.equals("Inmate")) {
                patient = new InmatePatient(patientName);
            }else {
                throw new IllegalArgumentException();
            }
            if (hospital.addPatient(patient,doctorName)) {
                System.out.println("Patient successfully registered.Patient name: "+patientName);
            }else {
                System.out.println("There's no such doctor with name: "+doctorName);
            }
        }catch (IllegalArgumentException e) {
            System.out.println("Patient type is invalid!");
        }
    }
}
