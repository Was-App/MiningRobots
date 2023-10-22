import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Input {
    public JSONObject readTerrain(String jsonPath) {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            FileReader reader = new FileReader(jsonPath);
            jsonObject = (JSONObject) parser.parse(reader);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException parse) {
            parse.getErrorType();
        }
        return jsonObject;
    }

    public String readGameSettings(String path){
        StringBuilder jsonText = new StringBuilder();
        try {
            BufferedReader bufferedReader =
                    new BufferedReader(new FileReader(path));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                jsonText.append(line).append("\n");
            }

            bufferedReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonText.toString();
    }

}
