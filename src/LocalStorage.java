import java.io.*;

public class LocalStorage {
    static int getProgress() {
        String fileName = "progress.txt";
        String line = "1";

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            line = bufferedReader.readLine();
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
        return Integer.parseInt(line);
    }

    static void saveProgress(int level) {
        String fileName = "progress.txt";
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(String.valueOf(level));

            bufferedWriter.close();

        } catch (IOException ex) {
            System.out.println("Error writing to file");
        }
    }
}
