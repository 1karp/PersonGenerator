import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Reader {

    public List<String> read(String fileName) {
        List<String> result = new ArrayList<>();
        File file = new File(fileName);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String strLine;
            while ((strLine = br.readLine()) != null) {
                result.add(strLine);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
