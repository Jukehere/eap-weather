
/**
 *
 * @author sasga
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLcheck {
    
    public static void main(String[] args) {
        // Ορίστε τα στοιχεία σύνδεσης στη βάση δεδομένων
        String url = "jdbc:derby://localhost:1527/weatherdata"; // Π.χ., jdbc:mysql://localhost:3306/yourDatabase
        String user = "weatherdata";
        String password = "weatherdata";

        // Η SQL εντολή που θέλετε να εκτελέσετε
        String query = "SELECT City.areaname, City.views, Citydata.weatherdesc FROM City JOIN Citydata ON City.areaname = Citydata.cityname";

        try (
            // Συνδεθείτε στη βάση δεδομένων
            Connection con = DriverManager.getConnection(url, user, password);
            // Δημιουργήστε ένα PreparedStatement για την εκτέλεση της εντολής
            PreparedStatement pst = con.prepareStatement(query);
            // Εκτελέστε την εντολή και λάβετε τα αποτελέσματα σε ένα ResultSet
            ResultSet rs = pst.executeQuery()) {

            // Εκτυπώστε τα αποτελέσματα
            while (rs.next()) {
                String areaname = rs.getString("areaname");
                int views = rs.getInt("views");
                String weatherdesc = rs.getString("weatherdesc");
                System.out.println("City Name: " + areaname + ", Views: " + views + ", Weather Description: " + weatherdesc);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

