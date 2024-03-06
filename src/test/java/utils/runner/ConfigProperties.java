package utils.runner;

import utils.LoggerUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigProperties {
    private static Properties properties = initProperties();

    

    private static Properties initProperties() {
        properties = new Properties();

        try {
            FileInputStream file = new FileInputStream("src/test/resources/config.properties");
            properties.load(file);

        } catch (IOException e) {
            LoggerUtils.logError("ERROR: Properties file NOT found");
//            System.exit(1);
        }
        return properties;
    }
}
