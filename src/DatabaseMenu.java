import java.io.*;

public class DatabaseMenu {
    static void main(String[] args) throws IOException {
        FileWorker.createFile();
        int n;
        boolean code = true;
        while (code) {
            System.out.println("1.Login\n2.Register\n3.Stop the program.\nMake a choice:");
            n = InputSection.wasInt();
            switch (n) {
                case 1 -> {
                    String loginUsername, loginPassword;
                    System.out.println("Enter your username:");
                    loginUsername = InputSection.SCN.nextLine();
                    int k = Finder.search(loginUsername, 1);
                    switch (k) {
                        case -1 -> {
                            System.out.println("Wrong Username.\nTry again.");
                        }
                        case -2 -> {
                            System.out.println("There is no user in the system.\nPlease register first.");
                        }
                        default -> {
                            System.out.println("Enter your Password:");
                            loginPassword = InputSection.SCN.nextLine();
                            if (UsersDatabaseController.getUsers().get(k).getPassword().equals(loginPassword)) {
                                System.out.println("Access was confirmed.\nWelcome to the system\n\n");
                                boolean lop = true;
                                while (lop) {
                                    System.out.println("1.Show users\n2.Get by username/if\n3.Delete by username/id\n4.Log out\nChoose an option:");
                                    int op, choice;
                                    op = InputSection.wasInt();
                                    switch (op) {
                                        case 1 -> {
                                            for (User x : UsersDatabaseController.getUsers()) {
                                                x.showUser();
                                                System.out.println("\n\n");
                                            }
                                        }
                                        case 2 -> {
                                            System.out.println("Would you like to choose by Username(1) or ID(2)?\nMale a choice:");
                                            choice = InputSection.wasInt();
                                            while (choice < 1 || choice > 2) {
                                                System.out.println("You may only choose 1 or 2.\nTry again.\nMake a choice:");
                                                choice = InputSection.wasInt();
                                            }
                                            UsersDatabaseController.getUsers().get(Finder.findUser(choice)).showUser();
                                            System.out.println("\n\n");
                                        }
                                        case 3 -> {
                                            System.out.println("Would you like to choose by Username(1) or ID(2)?\nMale a choice:");
                                            choice = InputSection.wasInt();
                                            while (choice < 1 || choice > 2) {
                                                System.out.println("You may only choose 1 or 2.\nTry again.\nMake a choice:");
                                                choice = InputSection.wasInt();
                                            }
                                            UsersDatabaseController.deleteUser(choice, loginUsername);
                                        }
                                        case 4 -> {
                                            System.out.println("Are you sure that you want to end the work and log out(1-Yes/2-No)? ");
                                            int end = InputSection.wasInt();
                                            while (end > 2 || end < 1) {
                                                System.out.println("You may choose only 1 or 2.\nTry again.\nDo you want to end the work and log out(1-Yes/2-No)? ");
                                                end = InputSection.wasInt();
                                            }
                                            lop = end == 2;
                                        }
                                        default ->
                                                System.out.println("Your choice can be only in between 1 and 3. Try again.\n");
                                    }
                                }
                            } else {
                                System.out.println("Wrong password.\nTry again.");
                            }
                        }
                    }
                }
                case 2 -> UsersDatabaseController.createUser();
                case 3 -> {
                    code = false;
                    System.out.println("The code was executed\nGood luck, dear user.");
                }
                default -> System.out.println("Your choice can be only in between 1 and 3. Try again.\n");
            }
        }
    }
}