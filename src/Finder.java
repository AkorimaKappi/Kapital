import java.util.ArrayList;
import java.util.Scanner;

public class Finder {
    private static final Scanner scn = new Scanner(System.in);

    public static <T> int search(T param, int n) {
        ArrayList<User> users = FileWorker.readFile();
        if (users.isEmpty()) {
            return -2;
        }
        int k = -1;
        if (n == 1) {
            for (User x : users) {
                k++;
                if (x.getUsername().equals(param)) {
                    break;
                }
            }
            if (!users.get(k).getUsername().equals(param)) {
                k = -1;
            }
        } else {
            for (User x : users) {
                k++;
                if (x.getId() == (Integer) param) {
                    break;
                }
            }
            if (!(users.get(k).getId() == (Integer) param)) {
                k = -1;
            }
        }
        return k;
    }

    public static int findUser(int choice) {
        while (choice == 1) {
            System.out.println("Enter the username you search for:");
            String searchUsName = scn.nextLine();
            int intUser = search(searchUsName, 1);
            if (intUser == -1) {
                System.out.println("Wrong Username.\nTry again.\n");
            } else {
                return intUser;
            }
        }
        while (true) {
            System.out.println("Enter the ID you search for:");
            int searchId = InputChecker.wasInt();
            int intId = search(searchId, 2);
            if (searchId < 99999 || searchId >= 999999 || intId == -1) {
                System.out.println("Wrong Id.\nTry again.\n");
            } else {
                return intId;
            }
        }
    }
}
