
public class Finder {
    public static <T> int search(T param, int n) {
        if (UsersDatabaseController.getUsers().isEmpty()) {
            return -2;
        }
        int k = -1;
        switch (n) {
            case 1 -> {
                for (User x : UsersDatabaseController.getUsers()) {
                    k++;
                    if (x.getUsername().equals(param)) {
                        break;
                    }
                }
                if (!UsersDatabaseController.getUsers().get(k).getUsername().equals(param)) {
                    k = -1;
                }
            }
            case 2 -> {
                for (User x : UsersDatabaseController.getUsers()) {
                    k++;
                    if (x.getId() == (Integer) param) {
                        break;
                    }
                }
                if (!(UsersDatabaseController.getUsers().get(k).getId() == (Integer) param)) {
                    k = -1;
                }
            }
            default -> {
                for (User x : UsersDatabaseController.getUsers()) {
                    k++;
                    if (x.getEmail().equals(param)) {
                        break;
                    }
                }
                if (!UsersDatabaseController.getUsers().get(k).getEmail().equals(param)) {
                    k = -1;
                }
            }
        }
        return k;
    }

    public static int findUser(int choice) {
        while (choice == 1) {
            System.out.println("Enter the username you search for:");
            String searchUsName = InputSection.SCN.nextLine();
            int intUser = search(searchUsName, 1);
            if (intUser == -1) {
                System.out.println("Wrong Username.\nTry again.\n");
            } else {
                return intUser;
            }
        }
        while (true) {
            System.out.println("Enter the ID you search for:");
            int searchId = InputSection.wasInt();
            int intId = search(searchId, 2);
            if (searchId < 99999 || searchId >= 999999 || intId == -1) {
                System.out.println("Wrong Id.\nTry again.\n");
            } else {
                return intId;
            }
        }
    }
}
