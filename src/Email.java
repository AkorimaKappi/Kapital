import java.util.Scanner;

public class Email {
    private static final Scanner scn = new Scanner(System.in);
    public static String newEmail(){
        String email;
        System.out.println("Enter your Email(in one word):");
        email=scn.nextLine();
        boolean allowed=false;
        while(!allowed) {
            if(email.contains(" ")) {
                System.out.println("Please enter your email in one word.\nEnter your email:");
                email = scn.nextLine();
            }
            else if(!email.contains("@")) {
                System.out.println("Your email must have @ in it, to show that it is an email.\nEnter your email:");
                email = scn.nextLine();
            }
            else{
                String[] garbage;
                garbage=email.split("@");
                if(garbage.length>2){
                    System.out.println("Your email must have only one @ in it.\nEnter your email:");
                    email = scn.nextLine();
                }
                else{
                    if(!garbage[1].contains(".")){
                        System.out.println("Your email must have a dot after the @ to show that it is the real email.\nEnter your email:");
                        email = scn.nextLine();
                    }
                    else{
                        allowed=true;
                    }
                }
            }
        }
        return email;
    }
}
