import java.util.Scanner;

public class InputChecker {
    private static final Scanner scn = new Scanner(System.in);

    public static int wasInt() {
        while (!scn.hasNextInt()) {
            scn.nextLine();
            System.out.println("This parameter may consist of integers only\nTry again.");
        }
        int i = scn.nextInt();
        scn.nextLine();
        return i;
    }
}
