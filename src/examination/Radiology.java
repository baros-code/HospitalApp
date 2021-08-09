package examination;

public class Radiology extends Analysis {
    private final String type;

    public Radiology(String patientName) {
        super(patientName);
        this.type = "Radiology";
    }



    @Override
    public String getType() {
        return "examination.Radiology";
    }
}
