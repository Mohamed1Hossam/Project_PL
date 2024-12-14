package com.mycompany.project_pl2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private String filename;

    public FileManager(String filename) {
        this.filename = filename;
    }

    public List<String> readFromFile() {
        ArrayList<String> data = new ArrayList<>();
        File file = new File(filename);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line);  // Add each line of data to the list
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void writeToFile(List<String> data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String line : data) {
                writer.write(line);
                writer.newLine();  
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
