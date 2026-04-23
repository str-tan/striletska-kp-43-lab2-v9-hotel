package ua.kpi.model.entity;

public class Visitor {
    private Integer id;
    private String fullName;
    private String email;

    public Visitor() {}

    public Visitor(String fullName, String email) {
        this.fullName = fullName;
        this.email = email;
    }

    public Visitor(Integer id, String fullName, String email) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
    }

    public Integer getId() { return id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }

    public boolean isValidEmail() {
        return email != null && email.contains("@") && email.contains(".");
    }
}