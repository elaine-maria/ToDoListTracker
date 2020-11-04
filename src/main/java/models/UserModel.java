package models;

public class UserModel {

    private int id;
    private String email;

    public void setId(int id) {
        this.id = id;
    }
    public int getId(){
        return this.id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }
}
