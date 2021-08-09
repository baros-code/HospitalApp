package hospital;

import examination.Analysis;
import examination.Treatment;

public class Examination {
    private final Doctor doctor;
    private Patient patient;
    private Treatment treatment;
    private Analysis analysis;


    public Examination(Doctor doctor,Patient patient) {
        this.doctor = doctor;
        this.patient = patient;
    }

    private void setIsExamined(boolean isExamined) {
        patient.setExamined(isExamined);
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void changeCurrentPatientWith(Patient patient) {
        setPatient(patient);
    }


    public void givePrescription() {
        this.treatment = doctor.writePrescription(patient);
    }

    public void askAnalysis(String analysisType) {
        this.analysis = doctor.askAnalysis(patient,analysisType);
    }

    public void decideSurgery() {
        this.treatment = doctor.decideSurgery(patient);
    }

    public void end() {
        setIsExamined(true);
    }





}
