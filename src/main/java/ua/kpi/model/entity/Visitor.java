package ua.kpi.model.entity;

public class Visitor {
    private Integer id;
    private String fullName;
    private String email;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }

    public static class Builder {
        private Visitor instance = new Visitor();
        public Builder setId(int id) { instance.id = id; return this; }
        public Builder setFullName(String name) { instance.fullName = name; return this; }
        public Builder setEmail(String email) { instance.email = email; return this; }
        public Visitor build() { return instance; }
    }
}