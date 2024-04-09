package config.utils;

import org.testng.ITestResult;
import org.yaml.snakeyaml.Yaml;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class ConfigReader {

    public String urlBrowserStack(ITestResult test) throws IOException {
        Map<String, Object> config = readConfigFromFile();

        String username = System.getenv("userName");
        if (username == null) {
            Object usernameObj = config.get("userName");
            if (usernameObj instanceof String) {
                username = (String) usernameObj;
            }
        }
        String accessKey = (String) config.get("accessKey");
        if (accessKey == null) {
            accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
        }

        String server = (String) config.get("server");
        if (server == null) {
            server = "";
        }
        return "http://" + username + ":" + accessKey + "@" + server + "/wd/hub";
    }

    private Map<String, Object> readConfigFromFile() throws IOException {
        Yaml yaml = new Yaml();
        try (FileReader fileReader = new FileReader("browserstack.yml")) {
            return yaml.load(fileReader);
        }
    }
}
