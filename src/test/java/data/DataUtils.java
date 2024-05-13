package data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import static data.DataFilesPathConstants.DATA_FILE_PATH;
import static data.DataFilesPathConstants.projectPath;



public class DataUtils {


    public static Object[][] readAndProvideJsonData(String fileName) throws IOException {
        String jsonContent = FileUtils.readFileToString(new File(projectPath + DATA_FILE_PATH + fileName), StandardCharsets.UTF_8);

        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> map = mapper.readValue(jsonContent, new TypeReference<>() {});

        Object[][] data = new Object[map.size()][1];

        for (int i = 0; i < map.size(); i++) {
            data[i][0] = map.get(i);
        }

        return data;
    }
}

