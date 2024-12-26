import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TextReader {

    public void readFile(Map<Integer, List<String>> fileMapping, String file){
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            bufferedReader.readLine();
            String line;
            int i=0;
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split("\\s+");
                if(words.length > 1){
                    fileMapping.put(i, Arrays.asList(words));
                    i++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
