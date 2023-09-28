package example.myapp1.service;

public class User
{
    private String firstName;
    private String lastName;
    private String uerName;
    private String password;
    private String location;
    private String gender;

    public User(String firstName, String lastName, String uerName, String password, String location, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.uerName = uerName;
        this.password = password;
        this.location = location;
        this.gender = gender;
    }

    public User(String firstName, String lastName, String uerName, String password, String location) {
        this(firstName, lastName, uerName, password, location, null);
    }
    public User(String firstName, String lastName, String uerName, String password) {
        this(firstName, lastName, uerName, password, null, null);
    }
    public User(String firstName, String lastName, String uerName) {
        this(firstName, lastName, uerName, null, null, null);
    }
    public User(String firstName, String lastName) {
        this(firstName, lastName, null, null, null, null);
    }
    public User(String firstName) {
        this(firstName, null, null, null, null, null);
    }
    public User() {
        this(null, null, null, null, null, null);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUerName() {
        return uerName;
    }

    public void setUerName(String uerName) {
        this.uerName = uerName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

