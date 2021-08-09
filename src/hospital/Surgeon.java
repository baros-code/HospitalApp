package hospital;

import data.Input;
import examination.Surgery;
import examination.Treatment;

import java.time.LocalDate;
import java.util.List;

public class Surgeon extends Doctor {
    private String type = "Surgeon";


    public Surgeon(String name) {
        super(name);
        this.type = "Surgeon";
    }


    public Surgeon(String name,Hospital hospital) {
        super(name,hospital);
        this.type = "Surgeon";
    }

    @Override
    public String getType() {
        return type;
    }


    public void searchAppointedSurgery(String patientName) {
        List<Surgery> surgeryList = getHospital().searchSurgery(patientName,this);
        if(surgeryList.size() != 0) {
            System.out.println("Surgery found!");
            for(Surgery surgery : surgeryList) {
                System.out.println(surgery.toString());
            }
        }else {
            System.out.println("Surgery not found with patient name:"+patientName);
        }
    }

    public void searchAppointedSurgery(LocalDate date) {
        List<Surgery> surgeryList = getHospital().searchSurgery(date,this);
        if(surgeryList.size() > 0) {
            System.out.println("Surgery found!");
            for(Surgery surgery : surgeryList) {
                System.out.println(surgery.toString());
            }
        }else{
            System.out.println("Surgery not found with date:"+date.toString());
        }
    }


    public Treatment decideSurgery(Patient patient) {
        System.out.println("Enter the surgery date in format: 28/12/2018");
        LocalDate date = Input.getValidDate();
        System.out.println("Enter the rest time for patient as an integer number.");
        int restTime = Input.getValidInteger();
        System.out.println("You are automatically assigned for this surgery.");
        Treatment surgery = new Surgery(date,this,date.plusDays(restTime),patient.getName());
        patient.setType("Inmate");
        getHospital().addTreatment(surgery);
        return surgery;
    }


    public void makeSurgery() {
        Surgery surgery = getHospital().getNextSurgeryForSurgeon(this);
        if(surgery != null) {
            surgery.setDone(true);
            System.out.println("The surgery of patient "+surgery.getPatientName()+" is successfully done.");
        }else{
            System.out.println("There is no surgery in the queue for today.");
        }
    }

}
