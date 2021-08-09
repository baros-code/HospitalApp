package hospital;

import java.time.LocalDate;

public abstract class Patient {

    private final String name;
    private LocalDate examinationDate;
    private boolean isExamined;


    public Patient(String name) {     //default today date
        this.name = name;
        this.examinationDate = LocalDate.now();
        this.isExamined = false;
    }

    public Patient(String name,LocalDate examinationDate)
    {
        this.name = name;
        this.examinationDate = examinationDate;
        this.isExamined = false;
    }

    public String getName() {
        return name;
    }

    public boolean isExamined() {
        return isExamined;
    }

    public LocalDate getExaminationDate() {
        return examinationDate;
    }

    public void setExamined(boolean examined) {
        isExamined = examined;
    }   //doctor/examination will use

    public abstract String getType();

    public abstract void setType(String type);
}
