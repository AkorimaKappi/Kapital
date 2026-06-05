import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class User implements Serializable {
    private final int ID, AGE;
    private final String USERNAME, PASSWORD, EMAIL, FULL_NAME;

    public static int addId() {
        Random ran = new Random();
        int id = ran.nextInt(99999, 999999);
        while (!checkIdUniqueness(id)) {
            id = ran.nextInt(99999, 999999);
        }
        return id;
    }

    public static String addUsername() {
        String username;
        System.out.println("Enter your Username(in one word):");
        username = InputSection.SCN.nextLine();
        while (username.isBlank()) {
            System.out.println("Your username cannot be blank.\nEnter your username:");
            username = InputSection.SCN.nextLine();
        }
        while (username.contains(" ")) {
            System.out.println("Your username should not have any space in it. Write it in one word.\nEnter your username:");
            username = InputSection.SCN.nextLine();
        }
        if (Finder.search(username, 1) == -2) {
            return username;
        }
        while (Finder.search(username, 1) != -1) {
            System.out.println("This username is already used, please, choose another one.\nEnter your username:");
            username = InputSection.SCN.nextLine();
        }
        return username;
    }

    public static String addFullName() {
        String fullName;
        System.out.println("Enter your FullName:");
        fullName = InputSection.SCN.nextLine();
        while (fullName.isBlank()) {
            System.out.println("Your full name cannot be blank.\nEnter your full name:");
            fullName = InputSection.SCN.nextLine();
        }
        while (hasDigit(fullName)) {
            System.out.println("Your real full name may not have any digits\nEnter your full name:");
            fullName = InputSection.SCN.nextLine();
        }
        return fullName;
    }

    public static String addPassword() {
        String password;
        System.out.println("Enter your password:");
        password = InputSection.SCN.nextLine();
        boolean pass = false;
        while (!pass) {
            if (password.length() < 8) {
                System.out.println("Your password must have at least 8 characters.\nEnter your password:");
                password = InputSection.SCN.nextLine();
            } else if (password.contains(" ")) {
                System.out.println("Your password may not have space.\nEnter your password:");
                password = InputSection.SCN.nextLine();
            } else if (password.length() > 25) {
                System.out.println("Your password should not have more than 25 characters. It would be too difficult to remember it.\nEnter your password:");
                password = InputSection.SCN.nextLine();
            } else if (password.equals(password.toLowerCase())) {
                System.out.println("Your password must have at least one Upper case character\nEnter your password:");
                password = InputSection.SCN.nextLine();
            } else if (password.equals(password.toUpperCase())) {
                System.out.println("Your password must have at least one Lower case character\nEnter your password:");
                password = InputSection.SCN.nextLine();
            } else if (!hasDigit(password)) {
                System.out.println("Your password must have at least one digit\nEnter your password:");
                password = InputSection.SCN.nextLine();
            } else {
                pass = true;
            }
        }
        return password;
    }

    public static String addEmail() {
        String email;
        System.out.println("Enter your Email(in one word):");
        email = InputSection.SCN.nextLine();
        boolean allowed = false;
        while (!allowed) {
            if (email.contains(" ")) {
                System.out.println("Please enter your email in one word.\nEnter your email:");
                email = InputSection.SCN.nextLine();
            } else if (!email.contains("@")) {
                System.out.println("Your email must have @ in it, to show that it is an email.\nEnter your email:");
                email = InputSection.SCN.nextLine();
            } else if (Finder.search(email, 3) != -1) {
                System.out.println("You are not allowed to create more than 1 account by using 1 email.\nEnter your email:");
                email = InputSection.SCN.nextLine();
            } else {
                String[] garbage;
                garbage = email.split("@");
                if (garbage.length > 2) {
                    System.out.println("Your email must have only one @ in it.\nEnter your email:");
                    email = InputSection.SCN.nextLine();
                } else {
                    if (!garbage[1].contains(".")) {
                        System.out.println("Your email must have a dot after the @ to show that it is the real email.\nEnter your email:");
                        email = InputSection.SCN.nextLine();
                    } else {
                        allowed = true;
                    }
                }
            }
        }
        return email;
    }

    public static int addAge() {
        int age;
        System.out.println("Enter your Age:");
        age = InputSection.wasInt();
        while (age <= 18 || age >= 100) {
            System.out.println("Your age can be only in between 19 and 99 because \nchildren are not allowed to this program and those who are older than 100 \nare probably too old for it.\nEnter your age:");
            age = InputSection.wasInt();
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

    public static boolean checkIdUniqueness(int id) {
        ArrayList<User> users = FileWorker.readFile();
        for (User us : users) {
            if (id == us.getId()) {
                return false;
            }
        }
        return true;
    }

    public User() {
        this.ID = addId();
        this.USERNAME = addUsername();
        this.FULL_NAME = addFullName();
        this.PASSWORD = addPassword();
        this.EMAIL = addEmail();
        this.AGE = addAge();
    }

    public int getId() {
        return this.ID;
    }

    public int getAge() {
        return this.AGE;
    }

    public String getUsername() {
        return this.USERNAME;
    }

    public String getFullName() {
        return this.FULL_NAME;
    }

    public String getPassword() {
        return this.PASSWORD;
    }

    public String getEmail() {
        return this.EMAIL;
    }

    public void showUser() {
        String out = "User's ID:" + this.ID + "\n" + "User's username: " + this.USERNAME + "\n" + "User's Full Name: " + this.FULL_NAME + "\n" + "User's email: " + this.EMAIL + "\n" + "User's age: " + this.AGE;
        System.out.println(out);
    }
}