package by.javaguru.je.jdbc.entity;

public class Subjects {
    private long id;
    private String subject;

    public Subjects(long id, String subject) {
        this.id = id;
        this.subject = subject;
    }

    public Subjects(String subject) {
        this.subject = subject;
    }

    public Subjects() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "SchoolSubjects{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                '}';
    }
}
