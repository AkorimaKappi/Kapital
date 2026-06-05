import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DatabaseMenu {
    private static final Scanner scn = new Scanner(System.in);
    static void main(String[] args) throws IOException {
        FileWorker.newFile();
        int n;
        boolean code = true;
        while (code) {
            System.out.println("1.Login\n2.Register\n3.Stop the program.\nMake a choice:");
            n = InputChecker.wasInt();
            OUTER:
            switch (n) {
                case 1 -> {
                    ArrayList<User> users = FileWorker.readFile();
                    String usname, password;
                    System.out.println("Enter your username:");
                    usname = scn.nextLine();
                    int k = Finder.search(usname, 1);
                    switch (k) {
                        case -1 -> {
                            System.out.println("Wrong Username.\nTry again.");
                            break OUTER;
                    }
                        case -2 -> {
                            System.out.println("There is no user in the system.\nPlease register first.");
                            break OUTER;
                    }
                        default -> {
                            System.out.println("Enter your Password:");
                            password = scn.nextLine();
                            if (users.get(k).getPassword().equals(password)) {
                                System.out.println("Access was confirmed.\nWelcome to the system\n\n");
                                boolean lop = true;
                                while (lop) {
                                    System.out.println("1.Show users\n2.Get by username/if\n3.Delete by username/id\n4.Log out\nChoose an option:");
                                    int op, choice;
                                    op = InputChecker.wasInt();
                                    switch (op) {
                                        case 1 -> {
                                            for (User x : users) {
                                                x.showUser();
                                                System.out.println("\n\n");
                                            }
                                        }
                                        case 2 -> {
                                            System.out.println("Would you like to choose by Username(1) or ID(2)?\nMale a choice:");
                                            choice = InputChecker.wasInt();
                                            while (choice < 1 || choice > 2) {
                                                System.out.println("You may only choose 1 or 2.\nTry again.\nMake a choice:");
                                                choice = InputChecker.wasInt();
                                            }
                                            users.get(Finder.findUser(choice)).showUser();
                                            System.out.println("\n\n");
                                        }
                                        case 3 -> {
                                            System.out.println("Would you like to choose by Username(1) or ID(2)?\nMale a choice:");
                                            choice = InputChecker.wasInt();
                                            while (choice < 1 || choice > 2) {
                                                System.out.println("You may only choose 1 or 2.\nTry again.\nMake a choice:");
                                                choice = InputChecker.wasInt();
                                            }
                                            UsersDatabaseController.deleteUser(choice, usname);
                                        }
                                        case 4 -> {
                                            System.out.println("Are you sure that you want to end the work and log out(1-Yes/2-No)? ");
                                            int end = InputChecker.wasInt();
                                            while (end > 2 || end < 1) {
                                                System.out.println("You may choose only 1 or 2.\nTry again.\nDo you want to end the work and log out(1-Yes/2-No)? ");
                                                end = InputChecker.wasInt();
                                            }
                                            lop = end == 2;
                                        }
                                        default -> System.out.println("Your choice can be only in between 1 and 3. Try again.\n");
                                    }
                                }
                            } else {
                                System.out.println("Wrong password.\nTry again.");
                                break OUTER;
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