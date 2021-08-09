package hospital;

public class EmergencyPatient extends Patient {
    private String type;

    public EmergencyPatient(String name) {
        super(name);
        this.type = "Emergency";
    }

    @Override
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
