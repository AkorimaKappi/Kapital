import java.io.Serializable;
import java.util.Random;

public class User implements Serializable {
    private int id,age;
    private String username,password,email,fullName;
    public User(String username,String fullName,String password,String email,int age){
        Random ran=new Random();
        this.id= ran.nextInt(99999,999999);
        this.username=username;
        this.fullName=fullName;
        this.password=password;
        this.email=email;
        this.age=age;
    }
    public void setId(int id){
        this.id=id;
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
        String out = "User's ID:" + this.id+"\n"+ "User's username:" +"\n"+ this.username +"\n"+ "User's Full Name:" +"\n"+ this.fullName +"\n"+ "User's email" +"\n"+ this.email +"\n"+ "User's age:" +"\n"+ this.age;
        System.out.println(out);
    }
}