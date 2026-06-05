import java.util.Scanner;

public class Password {
    private static final Scanner scn = new Scanner(System.in);
    public static boolean hasDigit(String str) {
        boolean hasDigit = false;
        for (char x : str.toCharArray()) {
            if (Character.isDigit(x)) {
                hasDigit = true;
            }
        }
        return hasDigit;
    }
    public static String newPassword() {
        String password;
        System.out.println("Enter your password:");
        password=scn.nextLine();
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
}
