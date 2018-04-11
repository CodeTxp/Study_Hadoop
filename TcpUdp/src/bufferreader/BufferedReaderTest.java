package bufferreader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;



public class BufferedReaderTest {  
	  
    private static final int LEN = 5;  
  
    public static void main(String[] args) throws IOException {  
    	FileReader fis = new FileReader("c://data.txt");
        BufferedReader br = new BufferedReader(fis);
        String line = null;
        while((line = br.readLine())!=null){
            System.out.println(line);
        }
    }  
  
  
}  