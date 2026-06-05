import java.io.Serializable;

public class User implements Serializable {
    private int id,age;
    private String username,password,email,fullName;
    public User(){
        this.id=Id.newId();
        this.username=Username.newUsername();
        this.fullName=FullName.newFullNAme();
        this.password=Password.newPassword();
        this.email=Email.newEmail();
        this.age=Age.newAge();
    }
    public int getId(){
        return this.id;
    }
    public int getAge(){
        return this.age;
    }
    public String getUsername(){
        return this.username;
    }
    public String getFullName(){
        return this.fullName;
    }
    public String getPassword(){
        return this.password;
    }
    public String getEmail(){
        return this.email;
    }
    public void showUser(){
        String out = "User's ID:" + this.id+"\n"+ "User's username: "+ this.username +"\n"+ "User's Full Name: " + this.fullName +"\n"+ "User's email: " + this.email +"\n"+ "User's age: "+ this.age;
        System.out.println(out);
    }
}