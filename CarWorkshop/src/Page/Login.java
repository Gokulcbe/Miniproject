package Page;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.cj.protocol.Resultset;

public class Login implements sqlconnection{
    public String getUrl() {
        return url;
    }

    public String getUname() {
        return uname;
    }

    public String getPass() {
        return pass;
    }

    public String getDriver() {
        return driver;
    }
    
	static void loginuser(String username,String password) throws SQLException, ClassNotFoundException {
		Class.forName(driver);
		try (Connection con = DriverManager.getConnection(url, uname, pass);
				PreparedStatement stl = con.prepareStatement("SELECT*FROM workshoplogin where userame=?")){
			stl.setString(1, username);
			ResultSet rs = stl.executeQuery();
			if(rs.next()) {
				String u = rs.getString("userame");
				String p = rs.getString("password");
				if(u.equals(username) && p.equals(password)) {					
					System.out.println("****************************");
					System.out.println("* Sucessfully Logged in!!! *");
					System.out.println("****************************\n");
					Booking.bookservice();
				}
				else {
					System.out.println("******************");
					System.out.println("* Wrong Password *");
					System.out.println("******************\n");
					userLogin();
				}
			}
			else {
				System.out.println("******************************************");
				System.out.println("* User has not Registered! Please signup *");
				System.out.println("******************************************\n");
				SignUp.newAccount();				
			}
		}
	}
	static void userLogin() throws ClassNotFoundException, SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.println("****************************");
        System.out.println("* Enter Your username: --> *");
        System.out.println("****************************\n");
        String username = sc.nextLine();
        System.out.println("****************************");
        System.out.println("* Enter your Password: --> *");
        System.out.println("****************************\n");
        String password = sc.nextLine();
        loginuser(username, password);
	}
}
