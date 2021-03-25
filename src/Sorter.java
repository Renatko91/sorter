import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Sorter {
    private int ram;

    public Sorter(int ram) {
        this.ram = ram;
    }

    void mergeFile(String location) throws IOException {
        List<String> fileList = fileSpliter(location);
        String need = "need-file1.txt";
        String need2;
        FileWriter fw;
        BufferedReader bf = new BufferedReader(new FileReader(new File(fileList.get(0))));
        BufferedReader bf2 = new BufferedReader(new FileReader(new File(fileList.get(1))));
        String s1;
        String s2;
        for (int i = 2; i <= fileList.size(); i++) {
            fw = new FileWriter(new File(need));
                //new File(fileList.get(i)).delete();
                //new File(need).delete();
            s1 = bf.readLine();
            s2 = bf2.readLine();
            while (true) {
                if (s1 != null && s2 != null) {
                    if (s1.compareTo(s2) < 0) {
                        fw.write(s1);
                        s1 = bf.readLine();
                    } else if (s1.compareTo(s2) == 0) {
                        fw.write(s1);
                        fw.write(s2);
                        s1 = bf.readLine();
                        fw.write("\n");
                        s2 = bf2.readLine();
                    } else {
                        fw.write(s2);
                        s2 = bf2.readLine();
                    }
                } else if (s1 != null) {
                    while (bf.ready()) {
                        fw.write(s1);
                        s1 = bf.readLine();
                        fw.write("\n");
                    }
                    fw.write(s1);
                    break;
                } else if (s2 != null) {
                    while (bf2.ready()) {
                        fw.write(s2);
                        s2 = bf2.readLine();
                        fw.write("\n");
                    }
                    fw.write(s2);
                    break;
                }
                fw.write("\n");
            }
            need2 = need;
            if (need.equals("need-file1.txt")) {
                need = "need-file2.txt";
            } else {
                need = "need-file1.txt";
            }
            fw.close();
            bf.close();
            bf2.close();
            if (i < fileList.size()) {
                bf = new BufferedReader(new FileReader(new File(fileList.get(i))));
                bf2 = new BufferedReader(new FileReader(new File(need2)));
            }
        }
        new File(need).delete();
        for (String file : fileList) {
            new File(fileList.get(fileList.indexOf(file))).delete();
        }
    }

    public List<String> fileSpliter(String location) throws IOException {
        int postFix = 1;
        int count = 0;
        boolean notEnd = true;

        List<String> fileNameList = new ArrayList<>();
        List<String> needStr = new ArrayList<>();
        BufferedReader bf = new BufferedReader(new FileReader(new File(location)));
        FileWriter fw = new FileWriter(new File("file_" + postFix + ".txt"));

        while (notEnd) {
            fileNameList.add("file_" + postFix + ".txt");
            String str = bf.readLine();
            while (str != null) {
                count++;
                if (count == ram) {
                    needStr.add(str);
                    break;
                }
                needStr.add(str);
                str = bf.readLine();
            }
            needStr = needStr.stream().sorted().collect(Collectors.toList());
            for (int i = 0; i < needStr.size(); i++) {
                fw.write(needStr.get(i));
                if (i != needStr.size() - 1) {
                    fw.write("\n");
                }
            }
            needStr.clear();
            fw.close();
            if (count < ram) {
                notEnd = false;
            } else {
                postFix++;
                count = 0;
                fw = new FileWriter(new File("file_" + postFix + ".txt"));
            }
        }
        fw.close();
        bf.close();
        return fileNameList;
    }
}
