package examination;

import hospital.Surgeon;

import java.time.LocalDate;

public class Surgery extends Treatment {
    private String type;
    private Surgeon surgeon;
    private LocalDate releaseDate;
    private boolean isDone;

    public Surgery(LocalDate surgeryDate,Surgeon surgeon,LocalDate releaseDate,String patientName) {    //composition
        super(patientName,surgeryDate);
        this.surgeon = surgeon;
        this.releaseDate = releaseDate;
        this.isDone = false;
        this.type = "Surgery";
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    public String getType() {
        return type;
    }

    public Surgeon getSurgeon() {
        return surgeon;
    }

    public String toString() {
        return "Surgery date:"+getDate().toString()+" ,Surgeon name:"+surgeon.getName()+" ,Release date:"+releaseDate.toString()+" ,Patient name:"+getPatientName();
    }

}
