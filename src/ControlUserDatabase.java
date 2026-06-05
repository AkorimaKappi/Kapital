import java.util.ArrayList;

public class ControlUserDatabase {
    public static void createUser(){
        System.out.println("Create an account.");
        User us = new User();
        ArrayList<User> useres = FileWorker.readFile();
        useres.add(us);
        FileWorker.saveFile(useres);
    }
    public static void deleteUser(int choice,String userName){
        ArrayList<User> useres = FileWorker.readFile();
        int found = Finder.findUser(choice);
        if(useres.get(found).getUsername().equals(userName)){
            System.out.println("You cannot delete your own account.");
        }
        else{
            System.out.println("Are you sure that you want to delete this user(1-Yes/2-No)? ");
            int delete= InputCheck.wasInt();
            while(delete>2||delete<1){
                System.out.println("You may choose only 1 or 2.\nTry again.\nDo you want to delete this user(1-Yes/2-No)? ");
                delete=InputCheck.wasInt();
            }
            if(delete==1){
                useres.remove(found);
                if(FileWorker.saveFile(useres)){
                    System.out.println("The user was deleted.");
                }
            }
            else{
                System.out.println("You decided to not delete this user.\nYou will be returned to the options choice page.\n");
            }
        }
    }
}
