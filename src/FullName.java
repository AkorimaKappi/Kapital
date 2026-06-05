import java.util.Scanner;

public class FullName {
    private static final Scanner scn = new Scanner(System.in);
    public static String newFullNAme() {
        String fullName;
        System.out.println("Enter your FullName:");
        fullName = scn.nextLine();
        while (fullName.isBlank()) {
            System.out.println("Your full name cannot be blank.\nEnter your full name:");
            fullName = scn.nextLine();
        }
        while (Password.hasDigit(fullName)) {
            System.out.println("Your real full name may not have any digits\nEnter your full name:");
            fullName = scn.nextLine();
        }
        return fullName;
    }
}
