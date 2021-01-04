import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 * @author Garrett
 */
public class threads{

    public static void main(String[] args){
        if(args.length < 2){
            System.out.println("Not enough arguments entered.  Exiting...");
            System.exit(1);
        }
        
        int argIndex;
        int count = 0;
        
        AppThread[] obj = new AppThread[args.length - 1];

        String file = "bible.txt";
        File inFile = new File(file);
        
        if(!inFile.exists()){
            System.out.println("File not found");
            System.exit(2);
        }
        
        long startTime = 0;
        
        if(args[0].equals("s")){
            startTime = System.nanoTime();
            for(argIndex = 1; argIndex < args.length; argIndex++){
                try {
                    Scanner fin = new Scanner(inFile);
                    args[argIndex] = args[argIndex].toLowerCase();
                    while(fin.hasNext()) {
                        String line = fin.nextLine();
                        line = line.toLowerCase();
                        if(line.matches(".*\\b" + args[argIndex] + "\\b.*"))
                            count++;               
                    }   
                    fin.close();
                    System.out.println("Found " + count + " lines that contain " + args[argIndex]);
                    count = 0;
                } catch (FileNotFoundException e){
                    System.out.println("File read error");
                }
            }
        }
        else if(args[0].equals("m")){
            startTime = System.nanoTime();
            for(count = 0; count < obj.length; count++)
                obj[count] = new AppThread(args[count + 1].toLowerCase());
            obj[--count].setPriority(1);
            for(AppThread x:obj){
                x.start();
            }           
        }
        else{
            System.out.println("Invalid command line thread specifier.  Exiting...");
            System.exit(3);
        }
        if(args[0].equals("m")){
            while(obj[count].isAlive()){;}
        }
        long endTime = System.nanoTime();
        System.out.println("Execution time in milliseconds:  " + ((endTime - startTime)/1000000));
        
    }   
}

