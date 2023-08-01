package Page;

import java.sql.SQLException;
import java.util.Scanner;
// import Page.SignUp;
public class Index {
//	static void logout() {
//		Index.main(null);
//	}
     public static void main(String args[]) throws ClassNotFoundException, SQLException{
        Scanner sc = new Scanner(System.in);
        System.out.println("**********************************");
        System.out.println("* Welcome to our Car WorkShop!!! *");
        System.out.println("**********************************\n");
        System.out.println("**************************************");
        System.out.println("* Please Login or Signup to Continue *");
        System.out.println("* Enter 1 to SignUp -->              *");
        System.out.println("* Enter 2 to Login -->               *");
        System.out.println("**************************************\n");
        int x = sc.nextInt();

        // SignUp signup = new SignUp();
        if(x==1){
            // SignUp.newAccount();
            SignUp.newAccount();
        }
        if(x==2){
            // SignUp.newAccount();
            Login.userLogin();
        }
    }
}
