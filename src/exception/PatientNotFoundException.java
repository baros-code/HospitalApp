package exception;

public class PatientNotFoundException extends Exception {

    public PatientNotFoundException() {
        super("Patient not found!");
    }

    public PatientNotFoundException(String message) {
        super(message);
    }


}
