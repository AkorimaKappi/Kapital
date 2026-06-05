import java.util.ArrayList;
import java.util.Random;

public class Id {
    public static boolean idIsUnique(int id){
        ArrayList<User> users = FileWorker.readFile();
        for(User us : users){
            if(id==us.getId()){
                return false;
            }
        }
        return true;
    }
    public static int newId(){
        Random ran=new Random();
        int id = ran.nextInt(99999,999999);
        while(!idIsUnique(id)){
            id=ran.nextInt(99999,999999);
        }
        return id;
    }
}
