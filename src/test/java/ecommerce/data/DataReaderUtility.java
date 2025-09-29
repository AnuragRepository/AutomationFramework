package ecommerce.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class DataReaderUtility {

    public List<HashMap<String, String>> readJson() throws IOException {
        //Covert Json to String
        String jsonFilePathString = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"/src/test/java/ecommerce/data/externalDataFile.json"),StandardCharsets.UTF_8);

        //Convert String to HashMap using jackson databind dependancy for dataProvider as it takes hashMap
        ObjectMapper objectMapper = new ObjectMapper();
        List<HashMap<String, String>> data = objectMapper.readValue(jsonFilePathString, new TypeReference<List<HashMap<String, String>>>() {
        }
        );


        return data;

       /* public List<HashMap<String, String>> readJson(File filePath) throws IOException {
            //Covert Json to String
            String jsonFilePathString = FileUtils.readFileToString(filePath,StandardCharsets.UTF_8);

            //Convert String to HashMap using jackson databind dependancy for dataProvider as it takes hashMap
            ObjectMapper objectMapper = new ObjectMapper();
            List<HashMap<String, String>> data = objectMapper.readValue(jsonFilePathString, new TypeReference<List<HashMap<String, String>>>() {
            });

            return data*/
    }



}
