package examination;

public class BloodTest extends Analysis {
    private final String type;

    public BloodTest(String patientName) {
        super(patientName);
        this.type = "Blood Test";
    }



    @Override
    public String getType() {
        return "Blood Test";
    }

}
