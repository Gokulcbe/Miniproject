package Page;

import java.util.*;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SignUp implements sqlconnection{
    

    static void isAuth(String username, String password, String cpassword) throws ClassNotFoundException, SQLException{
         if(!password.equals(cpassword)){
//        	 @Override
//        	 public void passmatch() {
        	 System.out.println("*******************************************************************");
             System.out.println("* Password and Confirm password does not match!! Please try again *");
             System.out.println("*******************************************************************");
             newAccount();
//        	 }
         
         }
         else {
        	 
        // String query = "select*from workshopUser";
        Class.forName(driver);
        try(Connection con = DriverManager.getConnection(url, uname, pass);
        PreparedStatement st = con.prepareStatement("INSERT INTO workshoplogin (userame, password) VALUES (?, ?)")){
            st.setString(1, username);
            st.setString(2, cpassword);
            // ResultSet rs = st.executeQuery(query);
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
            	System.out.println("***********************");
                System.out.println("* Sign Up Successful! *");
                System.out.println("***********************\n");
                Login.userLogin();
            } else {
            	System.out.println("****************************************");
                System.out.println("* Failed to Sign Up. Please try again. *");
                System.out.println("****************************************\n");
            }
        }
        catch(SQLException e){
        	System.out.println("*****SignUp catch******");
            e.printStackTrace();
        }
         }
        // return true;
    }
    static void newAccount() throws ClassNotFoundException, SQLException{
        Scanner sc = new Scanner(System.in);
        System.out.println("****************************");
        System.out.println("* Enter Your username: --> *");
        System.out.println("****************************\n");
        String username = sc.nextLine();
        System.out.println("****************************");
        System.out.println("* Enter your Password: --> *");
        System.out.println("****************************\n");
        String password = sc.nextLine();
        System.out.println("*************************");
        System.out.println("* Confirm Password: --> *");
        System.out.println("*************************\n");
        String cpassword = sc.nextLine();
        isAuth(username, password, cpassword);
        // if(isAuth(username, password, cpassword)){
        //     System.out.println("Sucessfully Registered");
        // }
        // else{
        //     System.out.println("Wrong Password ! Try Again");
        // }
        
    }
    // public static void main(String args[]){
         
    // }
}
