import javax.swing.*;
import java.io.*;

class Lab_12_File_Away {
    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select text file");

        // Make it so it only accept text files
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Text files", "txt"));

        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("File selected: " + selectedFile.getAbsolutePath());
            processFile(selectedFile);
        } else {
            System.out.println("File not selected.");
        }
    }

    private static void processFile(File file) {
        int numLines = 0;
        int numWords = 0;
        int numChars = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                numLines++;
                numChars += line.length(); // count characters

                // count words
                String[] words = line.split("\\s+"); // Make lines one or more spaces
                numWords += words.length;
            }
        } catch (IOException e) {
            System.err.println("Error reading your file: " + e.getMessage());
            return;
        }

        System.out.println("Summary:");
        System.out.println("File: " + file.getName());
        System.out.println("How many Lines: " + numLines);
        System.out.println("How many Words: " + numWords);
        System.out.println("How many Characters: " + numChars);
    }
}
