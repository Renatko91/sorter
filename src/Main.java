import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Generator generator = new Generator();
        generator.generateFile(120, 20);

        Sorter sorter = new Sorter(35);
        sorter.mergeFile("big-file.txt");
    }
}
