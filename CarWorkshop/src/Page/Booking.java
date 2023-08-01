package Page;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

abstract class ServiceBooking {
    // Abstract method for booking a service
    abstract void bookCar(String name, String carmodel, String licnum, String phno, String address)
            throws ClassNotFoundException, SQLException;
}

public class Booking extends ServiceBooking implements sqlconnection{
    static Scanner sc = new Scanner(System.in);
    @Override
	void bookCar(String name, String carmodel, String licnum, String phno, String address) throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		Services.serviceReq();
		String ser = sc.nextLine();
		String service ="";
		String price = "";
		String days = "";
		String carModel = "";
		String licNum = "";
		String Id = "";
		try(Connection con = DriverManager.getConnection(url, uname, pass);
				PreparedStatement stb = con.prepareStatement("SELECT*FROM service where id=?")){
			stb.setString(1, ser);
			ResultSet rs = stb.executeQuery();
			while(rs.next()) {
				service = rs.getString("service");
				price = rs.getString("Price");
				days = rs.getString("days");
			}
		}
		try(Connection con = DriverManager.getConnection(url, uname, pass);
				PreparedStatement st1 = con.prepareStatement("INSERT INTO booking (name, carmodel, licnum, phno, address, service, price, days) values(?, ?, ?, ?, ?, ? , ?, ?)")){
			st1.setString(1, name);
			st1.setString(2, carmodel);
			st1.setString(3, licnum);
			st1.setString(4, phno);
			st1.setString(5, address);
			st1.setString(6, service);
			st1.setString(7,  price);
			st1.setString(8, days);
			int rowsAffected = st1.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("*******************************************************");
				System.out.println("*******************************************************");
                System.out.println("* Your " + service + " Booked Sucessfully Successful! *");
                System.out.println("* You can collect your car in " + days + "            *");
                System.out.println("* Your Service payment is " + price + "               *");
                System.out.println("*******************************************************");
				System.out.println("*******************************************************\n");
//                Login.userLogin();
				System.out.println("*****************************");
				System.out.println("* Enter 1 - Booking History *");
				System.out.println("* Enter 2 - Logout          *");
				System.out.println("*****************************");
				String choice = sc.nextLine();
				if(choice.equals("1")) {
					try(Connection con2 = DriverManager.getConnection(url, uname, pass);
							PreparedStatement sth = con2.prepareStatement("SELECT*FROM booking where name=?")){
						sth.setString(1, name);
						ResultSet rsh = sth.executeQuery();
						System.out.println("Booking Details");
						System.out.println(
								"**************************************************************************************");
						System.out.format("%-15s%-25s%-20s%-15s%-15s\n", "ID", "SERVICE", "PRICE", "DAYS", "CARMODEL", "LICNUM");
						System.out.println(
								"--------------------------------------------------------------------------------------");
						while(rsh.next()) {
							service = rsh.getString("service");
							price = rsh.getString("Price");
							days = rsh.getString("days");
							carModel = rsh.getString("carmodel");
							licNum = rsh.getString("licnum");
							Id = rsh.getString("bid");
							System.out.format("%-15s%-25s%-20s%-15s%-15s\n" ,Id, service , price , days ,
									carModel ,licNum);
							System.out.println(
									"----------------------------------------------------------------------------------");
							
						}
					}
				}
				else if(choice.equals(2)) {
					System.out.println("Sucessfully logged out");
					System.exit(0);
				}
            } else {
            	System.out.println("****************************************");
                System.out.println("* Failed to Sign Up. Please try again. *");
                System.out.println("****************************************\n");
            }
		}
		catch(SQLException e){
            e.printStackTrace();
        }
	}
	
	static void bookservice() throws ClassNotFoundException, SQLException {
		System.out.println("***************************");
		System.out.println("***************************");
		System.out.println("** Book your Car service **");
		System.out.println("***************************");
		System.out.println("***************************\n");
		System.out.println("************************");
		System.out.println("* Enter your name: --> *");
		System.out.println("************************\n");
		String name = sc.nextLine();
		System.out.println("*****************************");
		System.out.println("* Enter your car model: --> *");
		System.out.println("*****************************\n");
		String carmodel = sc.nextLine();
		System.out.println("**********************************");
		System.out.println("* Enter your licence number: --> *");
		System.out.println("**********************************\n");
		String licnum = sc.nextLine();
		System.out.println("********************************");
		System.out.println("* Enter your phone number: --> *");
		System.out.println("********************************\n");
		String phno = sc.nextLine();
		System.out.println("***************************");
		System.out.println("* Enter your address: --> *");
		System.out.println("***************************\n");
		String address = sc.nextLine();
		Booking booking = new Booking();
		booking.bookCar(name, carmodel, licnum, phno, address);
		
	}
}
