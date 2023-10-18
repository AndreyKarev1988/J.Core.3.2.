import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class Main {

    public static void main(String[] args) {
        GameProgress gameOne = new GameProgress(10, 333, 1, 111.1);
        GameProgress gameTwo = new GameProgress(30, 777, 2, 222.2);
        GameProgress gameThree = new GameProgress(60, 999, 3, 333.3);
        saveGame("C://Games//savegames//gameOne.dat", gameOne);
        saveGame("C://Games//savegames//gameTwo.dat", gameTwo);
        saveGame("C://Games//savegames//gameThree", gameThree);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("C://Games//savegames//gameOne.dat");
        arrayList.add("C://Games//savegames//gameTwo.dat");
        arrayList.add("C://Games//savegames//gameThree");
        zipFiles("C://Games//savegames//zip.zip", arrayList);
        File game1 = new File("C://Games//savegames//gameOne.dat");
        File game2 = new File("C://Games//savegames//gameTwo.dat");
        File game3 = new File("C://Games//savegames//gameThree");
        if (game1.delete()) System.out.println("Файл \"gameOne.dat\" удален");
        if (game2.delete()) System.out.println("Файл \"gameTwo.dat\" удален");
        if (game3.delete()) System.out.println("Файл \"gameThree.dat\" удален");
    }

    private static void saveGame(String path, GameProgress game) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(game);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void zipFiles(String path, List<String> arrayList) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(path))) {
            for (String arr : arrayList) {
                try (FileInputStream fis = new FileInputStream(arr)) {
                    ZipEntry entry = new ZipEntry(arr);
                    zout.putNextEntry(entry);
                    while (fis.available() > 0) {
                        zout.write(fis.read());
                    }
                    zout.closeEntry();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}