import java.io.*;
import java.util.Random;

public class Generator {
    final private String ABC = "abcdefghijklmnopqrstuvwxyz";

    public void generateFile(int strCount, int strLength) throws IOException {
        File file = new File("big-file.txt");
        FileWriter fw = new FileWriter(file);
        for (int i = 0; i < strCount; i++) {
            fw.write(generateStr(strLength));
            if (i != strCount - 1) {
                fw.write("\n");
            }
        }
        fw.close();
    }

    private String generateStr(int strLength) {
        char[] chars = ABC.toCharArray();
        Random random = new Random();
        StringBuilder needStr = new StringBuilder();

        for (int i = 0; i < strLength; i++) {
            int index = random.nextInt(chars.length);
            needStr.append(chars[index]);
        }
        return needStr.toString();
    }
}
