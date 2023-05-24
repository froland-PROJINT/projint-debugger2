package be.eafcuccle.projint.debugger2;

import java.util.HashSet;
import java.util.Set;

public class RegistrationForm {
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String passwordConfirm;
    private Set<String> errors = new HashSet<>();

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = firstname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public void validate() {
        if (!password.equals(passwordConfirm)) {
            errors.add("passwordsDontMatch");
        }
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }
}
