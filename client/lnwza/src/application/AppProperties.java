package application;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author SE-lnwza
 */
public class AppProperties {

    private static String dbIP;
    private static String dbName;
    private static String dbUser;
    private static String dbPass;

    public static void load() {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = AppProperties.class.getResourceAsStream("/application/resources/config.properties");
            prop.load(input);

            dbIP = prop.getProperty("dbIP");
            dbName = prop.getProperty("dbName");
            dbUser = prop.getProperty("dbUser");
            dbPass = prop.getProperty("dbPass");

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getDBIP() {
        return dbIP;
    }

    public static String getDBName() {
        return dbName;
    }

    public static String getDBUser() {
        return dbUser;
    }

    public static String getDBPass() {
        return dbPass;
    }

}
