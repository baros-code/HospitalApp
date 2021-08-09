package hospital;

import data.Input;
import examination.*;
import exception.AnalysisNotFoundException;
import exception.PatientNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Doctor {            //As ı thought doctor should be exist
    private final String name;
    private String type = "Doctor";
    private Hospital hospital;

    public Doctor(String name) {
        this.name = name;
    }

    public Doctor(String name,Hospital hospital) {
        this.name = name;
        this.hospital = hospital;
    }


    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Hospital getHospital() {
        return hospital;
    }


    public Analysis askAnalysis(Patient patient, String analysisType) {
        Analysis analysis;
        if (analysisType.equals("Blood Test")) {
            analysis = new BloodTest(patient.getName());
        }
        else if(analysisType.equals("Radiology")) {
            analysis = new Radiology(patient.getName());
        }else {
            System.out.println("There's no such analysis type!");
            throw new IllegalArgumentException();
        }
        hospital.addAnalysis(analysis);
        return analysis;
    }

    public Treatment writePrescription(Patient patient) {
        String [] arr;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter medicines with comma separated. (Nurofen,Minoset,.....)");
        String input = scanner.nextLine();
        arr = input.split(",");
        Treatment treatment = new Prescription(patient.getName(),arr,LocalDate.now());
        hospital.addTreatment(treatment);
        System.out.println("Prescription has been made successfully for patient: "+patient.getName());
        return treatment;
    }



    /*
    while (isNotValid) {
        System.out.println("Enter the surgery information as following: Surgery date,Doctor name,Number of days that patient will rest");
        String input = scanner.nextLine();
        String[] arr = input.split(",");
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
            LocalDate localDate = LocalDate.parse(arr[0], formatter);
            Integer i = Integer.parseInt(arr[2]);
            treatment = new Surgery(localDate,arr[1],localDate.plus(i),patient.getName());
            isNotValid = false;
        } catch (IllegalArgumentException e) {
            System.out.println("Please enter a valid input.");
        }
    }
    */

    public Treatment decideSurgery(Patient patient) {
        System.out.println("Enter the surgery date in format: 28/12/2018");
        LocalDate date = Input.getValidDate();
        System.out.println("Enter the rest time for patient as an integer number.");
        int restTime = Input.getValidInteger();
        System.out.println("Enter the surgeon name for this surgery appointment.");
        Doctor doctor = Input.getValidSurgeon(hospital);
        Treatment surgery = new Surgery(date,(Surgeon) doctor,date.plusDays(restTime),patient.getName());
        hospital.addTreatment(surgery);
        patient.setType("Inmate");
        hospital.addPatient(patient,doctor.getName());      //it's now also the surgeon's patient
        return surgery;
    }

    public void printPatientsWillSeeThatDay() {
        List<Patient> patientList = hospital.getPatientsDoctorWillSeeThatDay(this);
        System.out.println("The patients will be seen are following: ");
        int counter = 1;
        System.out.println("--------------------------------------------------");
        for(Patient patient : patientList) {
            System.out.println("["+counter+"] "+patient.getName()+" :Type "+patient.getType());
            counter++;
        }
        System.out.println("--------------------------------------------------");
    }

    public void seePatientUnderHisHerCare() {
        List<Patient> patientList = hospital.getPatientsUnderDoctorCare(this);
        System.out.println("The patients under your care are following: ");
        int counter = 1;
        System.out.println("--------------------------------------------------");
        for(Patient patient : patientList) {
            System.out.println("["+counter+"] "+patient.getName()+" :Type "+patient.getType());
            counter++;
        }
        System.out.println("--------------------------------------------------");
    }

    public void seeAllPatientsExamined() {
        List<Patient> patientList = hospital.getAllPatientsExaminedAsOneList();
        int counter = 1;
        System.out.println("--------------------------------------------------");
        for(Patient patient : patientList) {
            System.out.println("["+counter+"] "+patient.getName()+" :Type "+patient.getType());
            counter++;
        }
        System.out.println("--------------------------------------------------");
    }

    public void searchPatientExaminedInPast(String patientName) throws PatientNotFoundException {
        Patient found = hospital.searchPatientExaminedInPast(patientName);
        if(found != null) {
            List<Treatment> treatmentList = hospital.searchTreatmentForPatient(patientName);
            List<Analysis> analysisList = hospital.searchAnalysisForPatient(patientName);
            System.out.println("Patient found!");
            System.out.println("Name:"+found.getName()+", Type:"+found.getType());
            System.out.println("Analyses for that patient:");
            System.out.println("------------------------------------------------");
            for(Analysis analysis : analysisList) {
                System.out.println(analysis.getType());
            }
            System.out.println("------------------------------------------------");
            System.out.println("Treatments for that patient:");
            System.out.println("------------------------------------------------");
            for(Treatment treatment : treatmentList) {
                System.out.println(treatment.toString());
            }
            System.out.println("------------------------------------------------");
        }else {
            throw new PatientNotFoundException();
        }
    }


    public void searchOrSeeResultOfAnalysis(String patientName) throws AnalysisNotFoundException {
        List<Analysis> analysisList = hospital.searchAnalysisForPatient(patientName);
        if(analysisList.size() != 0) {
            System.out.println("Analyses found!");
            System.out.println("--------------------------------------------------");
            for(Analysis analysis : analysisList) {
                System.out.println(analysis.toString());
            }
            System.out.println("--------------------------------------------------");
        }else {
            throw new AnalysisNotFoundException();
        }
    }

    public Patient callNextPatientForExamination() {
        return hospital.getNextPatientOfDoctor(this);
    }


}
