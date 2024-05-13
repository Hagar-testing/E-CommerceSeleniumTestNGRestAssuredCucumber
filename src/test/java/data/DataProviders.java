package data;

import org.testng.annotations.DataProvider;

import java.io.IOException;

import static data.DataFilesPathConstants.REGISTER_DATA_FILE_PATH;
import static data.DataFilesPathConstants.REGISTER_USER_DATA;

public class DataProviders {

    @DataProvider(name = REGISTER_USER_DATA)
    public static Object[][] registerUserData() throws IOException {
        return DataUtils.readAndProvideJsonData(REGISTER_DATA_FILE_PATH);
    }


}