package application;

import application.entity.Order;
import application.entity.OrderStatus;
import static application.handler.OrderHandler.update;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.HttpResponse;

/**
 *
 * @author SE-lnwza
 */
public class Delivery {
    
    private static final String URL = "https://lnwza.earthpyy.com/delivery/";
    
    public static String gainTrackNo(String postCode) {
        return request(URL + "newtrackno.php?shopId=" + AppProperties.getShopId() + "&postCode=" + postCode);
    }
    
    public static Double getCost(String postCode) {
        return Double.valueOf(request(URL + "getcost.php?postCode=" + postCode));
    }
    
    public static String getLastStatus(Order order) {
        String status = "ERROR";
        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT `status` FROM `status` WHERE `orderId` = ? LIMIT 1");
            ps.setInt(1, order.getId().intValue());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                status = rs.getString("status");
            }
        } catch (SQLException ex) {
        }
        return status;
    }
    
    public static String request(String url) {
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(url);
            
//        request.addHeader("User-Agent", USER_AGENT);
            HttpResponse response = client.execute(request);

            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            StringBuilder result = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        } catch (IOException ex) {
        }
        return null;
    }
    
}
