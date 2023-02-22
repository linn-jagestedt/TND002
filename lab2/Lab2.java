package lab2;
import java.io.*;

public class Lab2 
{    
    public static BufferedReader consoleScanner = new BufferedReader(
        new InputStreamReader(
            System.in
        )
    );

    public static void main(String[] args) 
    {
        Dictionary dictionary = new Dictionary();

        System.out.print("Enter filename: ");

        File file = new File("lab2/TextSource.txt");
        System.out.println("");

        /* 
        File file = null;
        String input = "";
         
        while (file == null) 
        {
            try {
                input = consoleScanner.readLine();
                file = findFile(input);
            } catch (FileNotFoundException e) {
                System.out.println(e);
                System.out.println("Failed to find file: " + input);
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("Too many failed attempts");
                return;
            }
        }
        */

        String content = ""; 

        try {
            content = readFileContent(file);
        } catch(IOException e) {
            System.out.println("Failed to read content of file: " + file.getPath());
            System.out.println(e);
            return;
        }

        String[] words = content.split(" ", 0);

        for (int i = 0; i < words.length; i++) {
            String trimmedWord = words[i].trim();
            System.out.println(dictionary.addString(trimmedWord));
        }
    
        System.out.println();
        System.out.println(dictionary);

        System.out.println(dictionary.sortList(Word.BYCOUNTS));
        System.out.println(dictionary);

        System.out.println(dictionary.sortList(Word.BYNAME));
        System.out.println(dictionary);
        
        String output = "";
        
        int i = 0;
        while (dictionary.getWordAtIndex(i) != "end") {
            output += dictionary.getWordAtIndex(i).toString();
            output += "\n";
            i++;
        }

        File outFile;
        try {
            outFile = createFile("lab2/result.txt");
        } catch (IOException e) {
            System.out.println("Failed to create file: result.txt");
            System.out.println(e);
            return;
        }

        try {
            writeToFile(outFile, output);
        } catch (IOException e) {
            System.out.println("Failed to write to file: result.txt");
            System.out.println(e);
            return;
        }   
    }


    public static int attempts = 0;

    public static File findFile(String filePath) throws Exception, FileNotFoundException
    {
        if (attempts > 2) {
            throw new FileNotFoundException();
        }
        
        File file = new File(filePath);
        
        if (!file.exists()) {
            attempts++; 
            throw new FileNotFoundException();
        } else {
            attempts = 0;
        }

        return file;
    }

    public static String readFileContent(File file) throws IOException
    {
        char[] content = new char[(int)file.length()];

        FileReader fileReader = new FileReader(file);
        fileReader.read(content);
        fileReader.close();
        
        return new String(content);
    }

    public static File createFile(String filePath) throws IOException
    {
        File outputFile = new File(filePath);
        
        if (!outputFile.exists()) {
            try {
                outputFile.createNewFile();
            } catch(IOException e) {
                throw new IOException();
            }
        } 

        return outputFile;
    }

    public static void writeToFile(File file, String content) throws IOException
    {
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(content);
        fileWriter.close();
    }
}
