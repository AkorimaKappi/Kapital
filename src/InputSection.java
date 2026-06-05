import java.util.Scanner;

public class InputSection {
    protected static final Scanner SCN = new Scanner(System.in);

    public static int wasInt() {
        while (!SCN.hasNextInt()) {
            SCN.nextLine();
            System.out.println("This parameter may consist of integers only\nTry again.");
        }
        int i = SCN.nextInt();
        SCN.nextLine();
        return i;
    }

}
