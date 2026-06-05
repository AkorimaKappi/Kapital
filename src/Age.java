public class Age {
    public static int newAge(){
        int age;
        System.out.println("Enter your Age:");
        age=InputCheck.wasInt();
        while(age<=18||age>=100){
            System.out.println("Your age can be only in between 19 and 99 because \nchildren are not allowed to this program and those who are older than 100 \nare probably too old for it.\nEnter your age:");
            age=InputCheck.wasInt();
        }
        return age;
    }
}
