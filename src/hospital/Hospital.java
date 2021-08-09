package hospital;

import examination.Analysis;
import examination.Surgery;
import examination.Treatment;

import java.time.LocalDate;
import java.util.*;

public class Hospital {
    private HashMap<Doctor,List<Patient>> doctorHashMap;
    private List<Analysis> analysisList;
    private List<Treatment> treatmentList;    //contains surgeries,prescriptions,therapies .....


    public Hospital() {
        this.doctorHashMap = new HashMap<>();
        this.treatmentList = new ArrayList<>();
        this.analysisList = new ArrayList<>();
    }


    public Hospital(HashMap<Doctor,List<Patient>> doctorHashMap) {
        this.doctorHashMap = doctorHashMap;
    }

    public Hospital(HashMap<Doctor,List<Patient>> doctorHashMap,List<Analysis> analysisList,List<Treatment> treatmentList) {
        this.doctorHashMap = doctorHashMap;
        this.analysisList = analysisList;
        this.treatmentList = treatmentList;
    }



    private List<Surgery> createSurgeryListForSurgeon(Surgeon surgeon) {
        List<Surgery> surgeries = new ArrayList<>();
        for(Treatment treatment : treatmentList) {
            if(treatment.getType().equals("Surgery")) {
                Surgery surgery = (Surgery) treatment;
                if(surgery.getSurgeon().getName().equals(surgeon.getName()) && !surgery.isDone()) {
                    surgeries.add(surgery);
                }
            }
        }
        return surgeries;
    }

    public Surgery getNextSurgeryForSurgeon(Surgeon surgeon) {
        List<Surgery> surgeries = createSurgeryListForSurgeon(surgeon);
        Iterator<Surgery> surgeryIterator = surgeries.iterator();
        while(surgeryIterator.hasNext()) {
            Surgery surgery = surgeryIterator.next();
            if(surgery.getDate().equals(LocalDate.now())) {       //if surgery date is today
                return surgery;
            }
        }
        return null;
    }



    public boolean addPatient(Patient patient,String doctorName) {
        Doctor doctor = getDoctor(doctorName);
        if(doctor == null) {
            return false;
        }else {
            List<Patient> patientList = doctorHashMap.get(doctor);
            if(patientList != null) {
                patientList.add(patient);
                return true;
            }else {
                List<Patient> tempList = new ArrayList<>();
                tempList.add(patient);
                doctorHashMap.replace(doctor,tempList);
                return true;
            }
        }
    }

    public boolean addDoctor(String doctorName) {
        if(getDoctor(doctorName) != null) {           //if theres a doctor with same name
            System.out.println("Couldn't add.There is already a doctor with name:"+doctorName);
            return false;
        }else{
            Doctor doctor = new Doctor(doctorName,this);
            List<Patient> patientList = new ArrayList<>();
            doctorHashMap.put(doctor,patientList);
            return true;
        }
    }
    public boolean addDoctor(String doctorName,String type) {         //type is surgeon or not ....
        if(getDoctor(doctorName) != null) {           //if theres a doctor with same name
            System.out.println("Couldn't add.There is already a doctor with name:"+doctorName);
            return false;
        }else{
            List<Patient> patientList = new ArrayList<>();
            if(type.equals("Surgeon")) {
                Doctor doctor = new Surgeon(doctorName,this);
                doctorHashMap.put(doctor,patientList);
                return true;
            }else {
                System.out.println("There is no doctor type defined in the system!");
                return false;
            }
        }
    }

    public void addTreatment(Treatment treatment) {
        treatmentList.add(treatment);
    }

    public void addAnalysis(Analysis analysis) {
        analysisList.add(analysis);
    }

    public List<Patient> getPatientsDoctorWillSeeThatDay(Doctor doctor) {
        List<Patient> patientList = doctorHashMap.get(doctor);
        List<Patient> thatDayList = new ArrayList<>();
        for(Patient patient : patientList) {
            if (patient.getExaminationDate().equals(LocalDate.now()) && !patient.isExamined()) {
                thatDayList.add(patient);
            }
        }
        return thatDayList;
    }

    /* sdasdsadsa **/

    public List<Patient> getPatientsUnderDoctorCare(Doctor doctor) {
        List<Patient> patientList = doctorHashMap.get(doctor);
        List<Patient> underCareList = new ArrayList<>();
        for(Patient patient : patientList) {
            LocalDate tempDate = patient.getExaminationDate();
            if(patient.isExamined()) {
                underCareList.add(patient);
            }
        }
        return underCareList;
    }

    public List<Patient> getAllPatientsExaminedAsOneList() {
        List<Patient> allPatientsList = new ArrayList<>();
        for(Map.Entry<Doctor,List<Patient>> entry : doctorHashMap.entrySet()) {
            for(Patient patient : entry.getValue()) {
                if(patient.isExamined()) {
                    allPatientsList.add(patient);
                }
            }
        }
        return allPatientsList;
    }

    public Patient searchPatientExaminedInPast(String patientName) {      //returns null if not found
        for(Patient patient : getAllPatientsExaminedAsOneList()) {
            if(patient.getName().equals(patientName)) {
                return patient;
            }
        }
        return null;
    }

    public List<Treatment> searchTreatmentForPatient(String patientName) {    //returns null if not found
        List<Treatment> foundList = new ArrayList<>();
        for(Treatment treatment : treatmentList) {
            if(treatment.getPatientName().equals(patientName)) {
                foundList.add(treatment);
            }
        }
        return foundList;
    }

    public List<Analysis> searchAnalysisForPatient(String patientName) {    //returns null if not found
        List<Analysis> foundList = new ArrayList<>();
        for(Analysis analysis : analysisList) {
            if(analysis.getPatientName().equals(patientName)) {
                foundList.add(analysis);
            }
        }
        return foundList;
    }

    public List<Surgery> searchSurgery(String patientName,Doctor doctor) {
        List<Surgery> foundList = new ArrayList<>();
        for(Treatment treatment : treatmentList) {
            if(treatment.getType().equals("Surgery") && treatment.getPatientName().equals(patientName)) {
                Surgery surgery = (Surgery) treatment;
                if(surgery.getSurgeon().getName().equals(doctor.getName())) {
                    foundList.add(surgery);
                }
            }
        }
        return foundList;
    }
    public List<Surgery> searchSurgery(LocalDate date,Doctor doctor) {
        List<Surgery> foundList = new ArrayList<>();
        for(Treatment treatment : treatmentList) {
            if(treatment.getType().equals("Surgery") && treatment.getDate().equals(date)) {
                Surgery surgery = (Surgery) treatment;
                if(surgery.getSurgeon().getName().equals(doctor.getName())) {
                    foundList.add(surgery);
                }
            }
        }
        return foundList;
    }

    /*
    private Doctor getDoctorOrCreateOneAndAddToHospitalSystem(String doctorName) {    //for user login
        Doctor result = getDoctorFromHashMap(doctorName);
        if(result == null) {
            Doctor doctor = new Doctor(doctorName);
            List<Patient> patientList = new ArrayList<>();
            doctorHashMap.put(doctor,patientList);
            return doctor;
        }
        else {
            return result;
        }
    }
    */


    public Doctor getDoctor(String doctorName) {   //returns null if there's no such doctor with name
        for(Map.Entry<Doctor,List<Patient>> entry : doctorHashMap.entrySet()) {
            if (entry.getKey().getName().equals(doctorName)) {         // == yapınca kabul etmiyor neden?
                return entry.getKey();
            }
        }
        return null;
    }

    public Patient getNextPatientOfDoctor(Doctor doctor) {
        List<Patient> patientList = getPatientsDoctorWillSeeThatDay(doctor);
        Iterator<Patient> patientIterator = patientList.iterator();
        if(patientIterator.hasNext()) {
            return patientIterator.next();
        }
        return null;
    }




}
