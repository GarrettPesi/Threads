
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 *
 * @author Garrett
 */
public class AppThread extends Thread{
    private String word;
    private int count;

    public AppThread(String word) {
        this.word = word;
    }
    
    @Override
    public void run() {
        try {
            Scanner fin = new Scanner(new File("bible.txt"));
            while(fin.hasNext()) {
                String line = fin.nextLine();
                line = line.toLowerCase();
                if(line.matches(".*\\b" + word + "\\b.*"))
                    count++;
            }   
            fin.close();
            System.out.println("Found " + count + " lines that contain " + word);
        } catch (FileNotFoundException e){
            System.out.println("File read error");
        } 
    }
}
