import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class Read {

    Object file;
    public Object readFile(String jsonPath) {
        JSONParser parser = new JSONParser();
        try {
            FileReader reader = new FileReader(jsonPath);
            file = parser.parse(reader);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException parse) {
            parse.printStackTrace();
        } finally {
            return file;
        }
    }
}
