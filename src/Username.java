import java.util.Scanner;

public class Username {
    private static final Scanner scn = new Scanner(System.in);
    public static String newUsername(){
        String username;
        System.out.println("Enter your Username(in one word):");
        username=scn.nextLine();
        while(username.isBlank()){
            System.out.println("Your username cannot be blank.\nEnter your username:");
            username=scn.nextLine();
        }
        while(username.contains(" ")){
            System.out.println("Your username should not have any space in it. Write it in one word.\nEnter your username:");
            username=scn.nextLine();
        }
        if(Finder.search(username,1)==-2){
            return username;
        }
        while(Finder.search(username,1)!=-1){
            System.out.println("This username is already used, please, choose another one.\nEnter your username:");
            username=scn.nextLine();
        }
        return username;
    }
}
