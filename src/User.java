import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class User implements Serializable {
    private final int id, age;
    private final String username, password, email, fullName;
    private static final Scanner scn = new Scanner(System.in);

    public static int newId() {
        Random ran = new Random();
        int id = ran.nextInt(99999, 999999);
        while (!idIsUnique(id)) {
            id = ran.nextInt(99999, 999999);
        }
        return id;
    }

    public static String newUsername() {
        String username;
        System.out.println("Enter your Username(in one word):");
        username = scn.nextLine();
        while (username.isBlank()) {
            System.out.println("Your username cannot be blank.\nEnter your username:");
            username = scn.nextLine();
        }
        while (username.contains(" ")) {
            System.out.println("Your username should not have any space in it. Write it in one word.\nEnter your username:");
            username = scn.nextLine();
        }
        if (Finder.search(username, 1) == -2) {
            return username;
        }
        while (Finder.search(username, 1) != -1) {
            System.out.println("This username is already used, please, choose another one.\nEnter your username:");
            username = scn.nextLine();
        }
        return username;
    }

    public static String newFullNAme() {
        String fullName;
        System.out.println("Enter your FullName:");
        fullName = scn.nextLine();
        while (fullName.isBlank()) {
            System.out.println("Your full name cannot be blank.\nEnter your full name:");
            fullName = scn.nextLine();
        }
        while (hasDigit(fullName)) {
            System.out.println("Your real full name may not have any digits\nEnter your full name:");
            fullName = scn.nextLine();
        }
        return fullName;
    }

    public static String newPassword() {
        String password;
        System.out.println("Enter your password:");
        password = scn.nextLine();
        boolean pass = false;
        while (!pass) {
            if (password.length() < 8) {
                System.out.println("Your password must have at least 8 characters.\nEnter your password:");
                password = scn.nextLine();
            } else if (password.contains(" ")) {
                System.out.println("Your password may not have space.\nEnter your password:");
                password = scn.nextLine();
            } else if (password.length() > 25) {
                System.out.println("Your password should not have more than 25 characters. It would be too difficult to remember it.\nEnter your password:");
                password = scn.nextLine();
            } else if (password.equals(password.toLowerCase())) {
                System.out.println("Your password must have at least one Upper case character\nEnter your password:");
                password = scn.nextLine();
            } else if (password.equals(password.toUpperCase())) {
                System.out.println("Your password must have at least one Lower case character\nEnter your password:");
                password = scn.nextLine();
            } else if (!hasDigit(password)) {
                System.out.println("Your password must have at least one digit\nEnter your password:");
                password = scn.nextLine();
            } else {
                pass = true;
            }
        }
        return password;
    }

    public static String newEmail() {
        String email;
        System.out.println("Enter your Email(in one word):");
        email = scn.nextLine();
        boolean allowed = false;
        while (!allowed) {
            if (email.contains(" ")) {
                System.out.println("Please enter your email in one word.\nEnter your email:");
                email = scn.nextLine();
            } else if (!email.contains("@")) {
                System.out.println("Your email must have @ in it, to show that it is an email.\nEnter your email:");
                email = scn.nextLine();
            } else {
                String[] garbage;
                garbage = email.split("@");
                if (garbage.length > 2) {
                    System.out.println("Your email must have only one @ in it.\nEnter your email:");
                    email = scn.nextLine();
                } else {
                    if (!garbage[1].contains(".")) {
                        System.out.println("Your email must have a dot after the @ to show that it is the real email.\nEnter your email:");
                        email = scn.nextLine();
                    } else {
                        allowed = true;
                    }
                }
            }
        }
        return email;
    }

    public static int newAge() {
        int age;
        System.out.println("Enter your Age:");
        age = InputChecker.wasInt();
        while (age <= 18 || age >= 100) {
            System.out.println("Your age can be only in between 19 and 99 because \nchildren are not allowed to this program and those who are older than 100 \nare probably too old for it.\nEnter your age:");
            age = InputChecker.wasInt();
        }
        return age;
    }

    public static boolean hasDigit(String str) {
        boolean hasDigit = false;
        for (char x : str.toCharArray()) {
            if (Character.isDigit(x)) {
                hasDigit = true;
            }
        }
        return hasDigit;
    }

    public static boolean idIsUnique(int id) {
        ArrayList<User> users = FileWorker.readFile();
        for (User us : users) {
            if (id == us.getId()) {
                return false;
            }
        }
        return true;
    }

    public User() {
        this.id = newId();
        this.username = newUsername();
        this.fullName = newFullNAme();
        this.password = newPassword();
        this.email = newEmail();
        this.age = newAge();
    }

    public int getId() {
        return this.id;
    }

    public int getAge() {
        return this.age;
    }

    public String getUsername() {
        return this.username;
    }

    public String getFullName() {
        return this.fullName;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

    public void showUser() {
        String out = "User's ID:" + this.id + "\n" + "User's username: " + this.username + "\n" + "User's Full Name: " + this.fullName + "\n" + "User's email: " + this.email + "\n" + "User's age: " + this.age;
        System.out.println(out);
    }
}