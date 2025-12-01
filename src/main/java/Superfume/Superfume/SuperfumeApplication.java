package Superfume.Superfume;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SuperfumeApplication {

    public static void main(String[] args) {
        // Load .env into system properties so Spring can read values like ${DB_PASSWORD}
        loadDotEnv();

        SpringApplication.run(SuperfumeApplication.class, args);
    }

    private static void loadDotEnv() {
        try {
            java.nio.file.Path envPath = java.nio.file.Paths.get(".env");
            if (!java.nio.file.Files.exists(envPath)) {
                return;
            }
            java.util.List<String> lines = java.nio.file.Files.readAllLines(envPath);
            int loaded = 0;
            for (String line : lines) {
                String trimmed = line.trim();
                if (trimmed.isEmpty() || trimmed.startsWith("#")) {
                    continue;
                }
                int eq = trimmed.indexOf('=');
                if (eq <= 0) continue;
                String key = trimmed.substring(0, eq).trim();
                String val = trimmed.substring(eq + 1).trim();
                // remove surrounding quotes if present
                if ((val.startsWith("\"") && val.endsWith("\"")) || (val.startsWith("'") && val.endsWith("'"))) {
                    val = val.substring(1, val.length() - 1);
                }
                if (key.isEmpty()) continue;
                // set as system property so Spring will resolve ${KEY}
                System.setProperty(key, val);
                // also set common spring datasource property names if present
                if ("DB_PASSWORD".equals(key) && System.getProperty("spring.datasource.password") == null) {
                    System.setProperty("spring.datasource.password", val);
                }
                if ("DB_URL".equals(key) && System.getProperty("spring.datasource.url") == null) {
                    System.setProperty("spring.datasource.url", val);
                }
                if ("DB_USERNAME".equals(key) && System.getProperty("spring.datasource.username") == null) {
                    System.setProperty("spring.datasource.username", val);
                }
                loaded++;
            }
            if (loaded > 0) {
                System.out.println("Loaded " + loaded + " entries from .env");
            }
        } catch (Exception e) {
            System.err.println("Could not load .env: " + e.getMessage());
        }
    }

}
