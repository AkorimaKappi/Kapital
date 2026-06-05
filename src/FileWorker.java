import java.io.*;
import java.util.ArrayList;

public class FileWorker {
    private static final File file = new File("users.dat");

    public static void createFile() throws IOException {
        if (file.createNewFile()) {
            System.out.println("Created file for saving users. File:" + file.getName());
        } else {
            System.out.println("You have a file for saving users. File:" + file.getName());
        }
    }

    public static ArrayList<User> readFile() {
        ArrayList<User> users = new ArrayList<>();
        if (file.length() == 0) {
            return users;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            users = (ArrayList<User>) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return users;
    }

    public static boolean saveFile(ArrayList<User> useres) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("users.dat"))) {
            oos.writeObject(useres);
            System.out.println("The changes were saved.");
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public static File getFile() {
        return file;
    }
}
