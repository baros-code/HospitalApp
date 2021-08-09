package examination;

import java.util.Random;

public abstract class Analysis {
    private final String patientName;
    private boolean isReady;
    private String result;

    public Analysis(String patientName) {
        this.patientName = patientName;
        this.isReady = false;
        this.result = "null";
        Random r = new Random();
        if (r.nextInt(2) == 0) {      //between 0-1
            this.isReady = true;
            Random temp = new Random();
            if(temp.nextInt(2) == 0) {
                this.result = "Normal";
            }else {
                this.result = "Anormal";
            }
        }
    }

    public boolean isReady() {
        return isReady;
    }

    public abstract String getType();

    public String getPatientName() {
        return patientName;
    }

    public String toString() {
        return "Patient name:"+patientName+" ,Available:"+isReady+" ,Result:"+result;
    }



}
