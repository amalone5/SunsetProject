import java.io.InputStreamReader;
import java.io.BufferedReader;

import java.util.Scanner;
import java.util.ArrayList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Bash {
    public void executeCommand(String command) {
        Runtime runtime = Runtime.getRuntime();
        String[] commands = {"bash", "-c", command};
        try {
            Process process = runtime.exec(commands);

            process.waitFor();
            BufferedReader bufferedReader
                    = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                bufferedReader.close();
            }
        } catch (Exception e) {
            System.err.println("Failed to execute bash with command: " + command);
            e.printStackTrace();
        }
    }

    public Scanner getFile(String filename) {
        Scanner fileIn = null; // Initializes fileIn to empty
        try {
            fileIn = new Scanner(
                    new FileInputStream(filename));
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(0);
        }
        return fileIn;
    }

    public ArrayList<String> getFilesFromDirectory(String path) {
        executeCommand("find " + path + " -maxdepth 1 -type f > files");
        Scanner files = getFile("files");
        ArrayList<String> listOfFiles = new ArrayList<>();

        while (files.hasNext())
            listOfFiles.add(files.nextLine());

        executeCommand("rm files");
        return listOfFiles;
    }

}
