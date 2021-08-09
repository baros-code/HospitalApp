package examination;

import java.time.LocalDate;

public class Prescription extends Treatment {
    private String type;
    private String [] medicines;

    public Prescription(String patientName, String [] medicines, LocalDate date) {
        super(patientName,date);
        this.medicines = medicines;
        this.type = "Prescription";
    }

    @Override
    public String getType() {
        return type;
    }

    public String medicinesToString() {
        String s = "";
        for(int i=0;i<medicines.length;i++) {
            s += medicines[i]+",";
        }
        return s;
    }
    @Override
    public String toString() {
        return "Prescription: "+medicinesToString();
    }

}
