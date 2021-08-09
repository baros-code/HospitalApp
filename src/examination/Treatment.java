package examination;

import java.time.LocalDate;

public abstract class Treatment {
    private final String patientName;
    private LocalDate treatmentDate;

    public Treatment(String patientName,LocalDate treatmentDate) {
        this.patientName = patientName;
        this.treatmentDate = treatmentDate;
    }

    public abstract String getType();

    public String getPatientName() {
        return patientName;
    }


    public LocalDate getDate() {
        return treatmentDate;
    }


    public abstract String toString();



}
