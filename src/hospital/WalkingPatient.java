package hospital;

public class WalkingPatient extends Patient {
    private String type;

    public WalkingPatient(String name) {
        super(name);
        this.type = "Walking";
    }

    @Override
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
