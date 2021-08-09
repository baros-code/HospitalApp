package hospital;

import data.Input;
import exception.AnalysisNotFoundException;
import exception.PatientNotFoundException;

import java.time.LocalDate;
import java.util.Scanner;

public class HospitalView {
    private Hospital hospital;
    private Scanner in = new Scanner(System.in);



    public HospitalView() {
        this.hospital = new Hospital();
    }

    public HospitalView(Hospital hospital) {
        this.hospital = hospital;
    }




    public void displayUserMenu() {
        System.out.println("Welcome to the hospital system! Choose your role.");
        System.out.println();
        while(true) {
            System.out.println("[1] Doctor");     //surgeon or not
            System.out.println("[2] Receptionist");
            System.out.println("[3] Exit");
            int cmdId = in.nextInt();
            in.nextLine();
            switch (cmdId) {
                case 1:
                    loginMenu();
                    break;
                case 2:
                    receptionistMenu();
                    break;
                case 3:
                    in.close();
                    System.exit(0);
                default:
                    System.out.println("Please enter a valid command.");
                    break;
            }
        }
    }

    //Doctor should visit inmate patient before release


    public void receptionistMenu() {
        Receptionist receptionist = new Receptionist();
        while (true) {
            System.out.println("[1] Register patient");
            System.out.println("[2] Back to main menu");
            int cmdId = in.nextInt();
            in.nextLine();
            switch (cmdId) {
                case 1:
                    receptionist.registerPatient(hospital,in);      //ı used same scanner to avoid error
                    break;
                case 2:
                    displayUserMenu();
                    break;
                default:
                    System.out.println("Please enter a valid command.");
                    break;
            }
        }
    }

    public void loginMenu() {
        System.out.println("Select a command");
        while(true) {
            System.out.println("[1] User login");
            System.out.println("[2] Sign up");
            System.out.println("[3] Back to main menu");
            System.out.println("[4] Exit");
            int cmdId = in.nextInt();
            in.nextLine();
            switch (cmdId) {
                case 1:
                    Doctor doctor = getDoctor();
                    if(doctor != null) {  //if found
                        displayDoctorMenu(doctor);
                    }else {
                        System.out.println("No user found!");
                    }
                    break;
                case 2:
                    System.out.println("Enter user name that you want to sign up.");
                    String name = in.nextLine();
                    System.out.println("If you're a surgeon enter y otherwise n.");
                    String validation = in.nextLine();
                    if(validation.equals("y")) {
                        if(hospital.addDoctor(name,"Surgeon")) {
                            System.out.println("Successfully signed up now you can login with your username!");
                        }
                    }else if(validation.equals("n")) {
                        if(hospital.addDoctor(name)) {
                            System.out.println("Successfully signed up now you can login with your username!");
                        }
                    }else {
                        System.out.println("Just press y or n.");
                        throw new IllegalArgumentException();
                    }
                    break;
                case 3:
                    displayUserMenu();
                    break;
                case 4:
                    in.close();
                    System.exit(0);
                default:
                    System.out.println("Please enter a valid command.");
                    break;
            }
        }

    }


    public void displayDoctorMenu(Doctor doctor) {
        while(true) {
        System.out.println("[1] Start Examination");
        System.out.println("[2] Search/Review");
        System.out.println("[3] Do surgery (Surgeons are allowed)");
        System.out.println("[4] Back to main menu");
        System.out.println("[5] Exit");
        int cmdId = in.nextInt();
        in.nextLine();
        switch (cmdId) {
            case 1:
                Patient patient = doctor.callNextPatientForExamination();
                if(patient != null) { //if there's patient to examine
                    displayExamination(new Examination(doctor,patient));
                }else {
                    System.out.println("There is no patient in the queue for examination.");
                }
                break;
            case 2:
                displaySearchReview(doctor);
                break;
            case 3:
                if(doctor.getType().equals("Surgeon")) {
                    Surgeon surgeon = (Surgeon) doctor;
                    surgeon.makeSurgery();                                   //automatically takes surgeries for today
                }else {
                    System.out.println("You're not allowed for this operation!");
                }
                break;
            case 4:
                displayUserMenu();
                break;
            case 5:
                in.close();
                System.exit(0);
            default:
                System.out.println("Please enter a valid command.");
                break;
        }
    }



    }

    private Doctor getDoctor() {
        System.out.println("Enter your username. It is your real name.");
        String name = in.nextLine();
        Doctor doctor = hospital.getDoctor(name);
        return doctor;
    }


    private void displayAnalysisMenu(Examination examination) {
        while(true) {
            System.out.println("[1] Blood Test");
            System.out.println("[2] Radiology");
            System.out.println("[3] Back to main menu");
            int cmdId = in.nextInt();
            in.nextLine();
            switch (cmdId) {
                case 1:
                    examination.askAnalysis("Blood Test");
                    System.out.println("Analysis successfully requested.");
                    break;
                case 2:
                    examination.askAnalysis("Radiology");
                    System.out.println("Analysis successfully requested.");
                    break;
                case 3:
                    displayExamination(examination);
                    break;
                default:
                    System.out.println("Please enter a valid command.");
                    break;
            }
        }
    }


    public void displayExamination(Examination examination) {
        while(true) {
            System.out.println("Current patient:"+examination.getPatient().getName());
            System.out.println();
            System.out.println("[1] Write prescription");
            System.out.println("[2] Make surgery appointment");
            System.out.println("[3] Ask for analysis");
            System.out.println("[4] Call next patient");
            System.out.println("[5] End examination and back to main menu");
            int cmdId = in.nextInt();
            in.nextLine();
            switch (cmdId) {
                case 1:
                    examination.givePrescription();
                    break;
                case 2:
                    examination.decideSurgery();
                    break;
                case 3:
                    displayAnalysisMenu(examination);
                    break;
                case 4:
                    examination.end();
                    Patient nextPatient = examination.getDoctor().callNextPatientForExamination();
                    if(nextPatient != null) {
                        examination.changeCurrentPatientWith(nextPatient);
                        displayExamination(examination);
                    }else {
                        System.out.println("There is no more patient in the queue.Exited from examination!");
                        displayDoctorMenu(examination.getDoctor());
                    }
                    break;
                case 5:
                    examination.end();
                    displayDoctorMenu(examination.getDoctor());
                    break;
                default:
                    System.out.println("Please enter a valid command.");
                    break;
            }
        }
    }


    public void displaySearchReview(Doctor doctor) {
        while(true) {
            System.out.println("[1] See patients you will see today");
            System.out.println("[2] See all patients under your care");
            System.out.println("[3] See all patients examined");
            System.out.println("[4] Search any patient examined in past");
            System.out.println("[5] Search result of analysis");
            System.out.println("[6] Search appointed surgery for you.(Surgeons are allowed)");
            System.out.println("[7] Back to main menu");
            int cmdId = in.nextInt();
            in.nextLine();
            switch (cmdId) {
                case 1:
                    doctor.printPatientsWillSeeThatDay();
                    break;
                case 2:
                    doctor.seePatientUnderHisHerCare();
                    break;
                case 3:
                    doctor.seeAllPatientsExamined();
                    break;
                case 4:
                    System.out.println("Enter the patient name you want to search.(case sensitive)");
                    String patientName = in.nextLine();
                    try {
                        doctor.searchPatientExaminedInPast(patientName);
                    }catch (PatientNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("Enter the patient name you want to search.(case sensitive)");
                    String patientName1 = in.nextLine();
                    try{
                        doctor.searchOrSeeResultOfAnalysis(patientName1);
                    }catch (AnalysisNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6:
                    if(doctor.getType().equals("Surgeon")) {
                        Surgeon surgeon = (Surgeon) doctor;
                        surgerySearchMenu(surgeon);
                    }else {
                        System.out.println("Only surgeons are allowed!");
                    }
                    break;
                case 7:
                    displayDoctorMenu(doctor);
                    break;
                default:
                    System.out.println("Please enter a valid command.");
                    break;
            }
        }
    }


    private void surgerySearchMenu(Surgeon surgeon) {
        while(true) {
            System.out.println("[1] Search by patient name");
            System.out.println("[2] Search by date");
            System.out.println("[3] Back to main menu");
            int cmdId = in.nextInt();
            in.nextLine();
            switch (cmdId) {
                case 1:
                    System.out.println("Enter the patient name");
                    String name = in.nextLine();
                    surgeon.searchAppointedSurgery(name);
                    break;
                case 2:
                    System.out.println("Enter the date in the form: 31/12/2018");
                    LocalDate date = Input.getValidDate();
                    surgeon.searchAppointedSurgery(date);
                    break;
                case 3:
                    displaySearchReview(surgeon);
                    break;
                default:
                    System.out.println("Please enter a valid command.");
                    break;
            }
        }
    }

}
