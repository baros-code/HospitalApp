package hospital;

import java.time.LocalDate;

public class InmatePatient extends Patient {
    private String type;
    private boolean isVisited;
    private LocalDate relaseDate;

    public InmatePatient(String name) {
        super(name);
        this.type = "Inmate";
        this.isVisited = false;
    }

    @Override
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

}
