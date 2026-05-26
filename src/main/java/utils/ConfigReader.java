package utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    static {
        try {
            // 👇 Reads from main/resources — no hardcoded path needed
            InputStream input = ConfigReader.class
                    .getClassLoader()
                    .getResourceAsStream("config.properties");

            if (input == null) {
                throw new RuntimeException("config.properties not found in resources/");
            }

            properties = new Properties();
            properties.load(input);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties: " + e.getMessage());
        }
    }

    public static String get(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Key not found in config: " + key);
        }
        return value.trim();
    }

    public static String getBaseUrl()  {
        String env = get("env");
        return get("url." + env);  }
    public static String getUsername() { return get("username");  }
    public static String getPassword() { return get("password");  }
    public static String getBrowser()  { return get("browser");   }
    public static boolean isHeadless() { return Boolean.parseBoolean(get("headless")); }
}