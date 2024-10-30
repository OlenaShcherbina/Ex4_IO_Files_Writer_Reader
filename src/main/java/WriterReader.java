import jdk.dynalink.StandardOperation;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class WriterReader {

  private static final String FILE_NAME = "output.txt";

  private static final String EXIT = "-q";

  private static final String LINE_SEPARATOR = System.lineSeparator();

  public static void main(String[] args) {

    // TODO write your code here...
    Path filePath = Paths.get(FILE_NAME);
    try {
      if (Files.exists(filePath)) {
        Files.delete(filePath);
      }
    } catch (IOException e) {
      System.out.println("Failed file deleting" + e.getMessage());
    }
    try {
      Files.createFile(filePath);
    } catch (IOException e) {
      System.out.println("Failed file creation" + e.getMessage());
    }

    try (BufferedReader theKeyboard = new BufferedReader(new InputStreamReader(System.in))) {
      String input = "";

      while(true){
        System.out.println("Write a sentence to be written in the file: ");
        input = theKeyboard.readLine();
        if(!input.equals(EXIT)) {
          Files.writeString(filePath, input + LINE_SEPARATOR, StandardOpenOption.APPEND);
        }else{
          System.out.println("Exiting the loop..");
          break;
        }
      }

      System.out.println("===================");
      System.out.println("Content written: ");
      System.out.println();

      // TODO step 5 goes here

      List<String> fileContent = Files.readAllLines(filePath);
      for(String line: fileContent){
        System.out.println(line);
      }


      System.out.println("===================");
      System.out.println("Exiting...");

    }catch(IOException e){
      e.getStackTrace();
    }
  }
}
