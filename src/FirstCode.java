import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class FirstCode{

    private static final File file = new File("users.dat");
    private static final Scanner scn = new Scanner(System.in);
    public static ArrayList<User> getFile(){
        ArrayList<User> users = new ArrayList<>();
        if (file.length()==0){
            return users;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            users = (ArrayList<User>) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return users;
    }
    public static boolean hasDigit(String str){
        boolean hasDigit=false;
        for(char x:str.toCharArray()){
            if(Character.isDigit(x)){
                hasDigit=true;
            }
        }
        return hasDigit;
    }
    public static boolean idIsUnique(int id,ArrayList<User> users){
        for(User us : users){
            if(id==us.getId()){
                return false;
            }
        }
        return true;
    }
    public static String passwordAllowed(String password){
        boolean pass=false;
        while(!pass){
            if(password.length()<8) {
                System.out.println("Your password must have at least 8 characters.\nEnter your password:");
                password = scn.nextLine();
            }
            else if(password.contains(" ")){
                System.out.println("Your password may not have space.\nEnter your password:");
                password = scn.nextLine();
            }
            else if(password.length()>25){
                System.out.println("Your password should not have more than 25 characters. It would be too difficult to remember it.\nEnter your password:");
                password = scn.nextLine();
            }
            else if(password.equals(password.toLowerCase())){
                System.out.println("Your password must have at least one Upper case character\nEnter your password:");
                password = scn.nextLine();
            }
            else if(password.equals(password.toUpperCase())){
                System.out.println("Your password must have at least one Lower case character\nEnter your password:");
                password = scn.nextLine();
            }
            else if (!hasDigit(password)){
                System.out.println("Your password must have at least one digit\nEnter your password:");
                password = scn.nextLine();
            }
            else{
                pass=true;
            }
        }
        return password;
    }
    public static String emailAllowed(String email){
        boolean allowed=false;
        while(!allowed) {
            if(email.contains(" ")) {
                System.out.println("Please enter your email in one word.\nEnter your email:");
                email = scn.nextLine();
            }
            else if(!email.contains("@")) {
                System.out.println("Your email must have @ in it, to show that it is an email.\nEnter your email:");
                email = scn.nextLine();
            }
            else{
                String[] garbage;
                garbage=email.split("@");
                if(garbage.length>2){
                    System.out.println("Your email must have only one @ in it.\nEnter your email:");
                    email = scn.nextLine();
                }
                else{
                    if(!garbage[1].contains(".")){
                        System.out.println("Your email must have a dot after the @ to show that it is the real email.\nEnter your email:");
                        email = scn.nextLine();
                    }
                    else{
                        allowed=true;
                    }
                }
            }
        }
        return email;
    }
    public static int wasInt(){
        while (!scn.hasNextInt()){
            scn.nextLine();
            System.out.println("Your age may consist of integers only\nTry again.");
        }
        int i = scn.nextInt();
        scn.nextLine();
        return i;
    }
    public static void createUser(){
        Random ran=new Random();
        System.out.println("Create an account.");
        int age;
        String username,password,email,fullName;
        System.out.println("Enter your Username(in one word):");
        username=scn.nextLine();
        while(username.isBlank()){
            System.out.println("Your username cannot be blank.\nEnter your username:");
            username=scn.nextLine();
        }
        while(username.contains(" ")){
            System.out.println("Your username should not have any space in it. Write it in one word.\nEnter your username:");
            username=scn.nextLine();
        }
        while(search(username,1)!=-1){
            System.out.println("This username is already used, please, choose another one.\nEnter your username:");
            username=scn.nextLine();
        }
        System.out.println("Enter your FullName:");
        fullName=scn.nextLine();
        while(fullName.isBlank()){
            System.out.println("Your full name cannot be blank.\nEnter your full name:");
            fullName=scn.nextLine();
        }
        while(hasDigit(fullName)){
            System.out.println("Your real full name may not have any digits\nEnter your full name:");
            fullName = scn.nextLine();
        }
        System.out.println("Enter your password:");
        password=scn.nextLine();
        password=passwordAllowed(password);
        System.out.println("Enter your Email(in one word):");
        email=scn.nextLine();
        email=emailAllowed(email);
        System.out.println("Enter your Age:");
        age=wasInt();
        while(age<=18||age>=100){
            System.out.println("Your age can be only in between 19 and 99 because \nchildren are not allowed to this program and those who are older than 100 \nare probably too old for it.\nEnter your age:");
            age=wasInt();
        }
        User us = new User(username,fullName,password,email,age);
        ArrayList<User> useres = getFile();
        while(!idIsUnique(us.getId(),useres)){
            us.setId(ran.nextInt(99999,999999));
        }
        useres.add(us);
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("users.dat"))){
            oos.writeObject(useres);
            System.out.println("The new user was saved.");
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }
    public static <T> int search(T param, int n){
        ArrayList<User> users = getFile();
        if(users.isEmpty()){
            return -2;
        }
        int k=-1;
        if(n==1){
            for(User x : users){
                k++;
                if(x.getUsername().equals(param)) {
                    break;
                }
            }
            if(!users.get(k).getUsername().equals(param)){
                k=-1;
            }
        }
        else{
            for(User x : users){
                k++;
                if(x.getId()==(Integer)param){
                    break;
                }
            }
            if(!(users.get(k).getId()==(Integer)param)){
                k=-1;
            }
        }
        return k;
    }
    public static int getUser(){
        System.out.println("Would you like to choose by Username(1) or ID(2)?\nMale a choice:");
        int cho = wasInt();
        while (cho < 1 || cho > 2) {
            System.out.println("You may only choose 1 or 2.\nTry again.\nMake a choice:");
            cho = wasInt();
        }
        while (cho == 1) {
            System.out.println("Enter the username you search for:");
            String searchUsName = scn.nextLine();
            int intUser=search(searchUsName, 1);
            if (intUser == -1) {
                System.out.println("Wrong Username.\nTry again.\n");
            } else {
                return intUser;
            }
        }
        while (cho == 2) {
            System.out.println("Enter the ID you search for:");
            int searchId = wasInt();
            int intId=search(searchId, 2);
            if (searchId < 99999 || searchId >= 999999 || intId == -1) {
                System.out.println("Wrong Id.\nTry again.\n");
            } else {
                return intId;
            }
        }
        return -1;
    }
    static void main(String[] args) throws IOException {
        if(file.createNewFile()){
            System.out.println("Created file for saving users. File:"+file.getName());
        }
        else{
            System.out.println("You have a file for saving users. File:"+file.getName());
        }
        int n;
        boolean code=true;
        while(code) {
            System.out.println("1.Login\n2.Register\n3.Stop the program.\nMake a choice:");
            n = wasInt();
            switch (n) {
                case 1:
                    ArrayList<User> users = getFile();
                    String usname,password;
                    System.out.println("Enter your username:");
                    usname=scn.nextLine();
                    int k = search(usname,1);
                    if(k==-1){
                        System.out.println("Wrong Username.\nTry again.");
                        break;
                    }
                    else if(k==-2){
                        System.out.println("There is no user in the system.\nPlease register first.");
                        break;
                    }
                    else{
                        System.out.println("Enter your Password:");
                        password=scn.nextLine();
                        if(users.get(k).getPassword().equals(password)){
                            System.out.println("Access was confirmed.\nWelcome to the system\n\n");
                            boolean lop=true;
                            while(lop){
                                System.out.println("1.Show users\n2.Get by username/if\n3.Delete by username/id\nChoose an option:");
                                int op;
                                op = wasInt();
                                switch (op) {
                                    case 1:
                                        for (User x : users) {
                                            x.showUser();
                                            System.out.println("\n\n");
                                        }
                                        break;
                                    case 2:
                                        int usn=getUser();
                                        users.get(usn).showUser();
                                        System.out.println("\n\n");
                                        break;
                                    case 3:
                                        int delus=getUser();
                                        if(users.get(delus).getUsername().equals(usname)){
                                            System.out.println("You cannot delete your own account.");
                                            break;
                                    }
                                        System.out.println("Are you sure that you want to delete this user(1-Yes/2-No)? ");
                                        int delit= wasInt();
                                        while(delit>2||delit<1){
                                            System.out.println("You may choose only 1 or 2.\nTry again.\nDo you want to delete this user(1-Yes/2-No)? ");
                                            delit=wasInt();
                                        }
                                        if(delit==1){
                                            users.remove(delus);
                                            try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){
                                                oos.writeObject(users);
                                                System.out.println("The user was deleted.");
                                            } catch (IOException ex){
                                                ex.printStackTrace();
                                            }
                                        }
                                        break;
                                }
                                System.out.println("Do you want to end the work and log out(1-Yes/2-No)? ");
                                int end= wasInt();
                                while(end>2||end<1){
                                    System.out.println("You may choose only 1 or 2.\nTry again.\nDo you want to end the work and log out(1-Yes/2-No)? ");
                                    end=wasInt();
                                }
                                lop=end==2;
                            }
                        }
                        else{
                            System.out.println("Wrong password.\nTry again.");
                            break;
                        }
                    }
                    break;
                case 2:
                    createUser();
                    break;
                case 3:
                    code=false;
                    System.out.println("The code was executed\nGood luck, dear user.");
                    break;
                default:
                    System.out.println("Your choice can be only in between 1 and 3. Try again.\n");
                    break;
            }
        }
    }
}