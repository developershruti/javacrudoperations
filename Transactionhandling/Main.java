import javax.naming.ldap.PagedResultsResponseControl;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) throws SQLException {
        String url ="jdbc:mysql://localhost:3306/shruti";
        String username = "root";
        String password = "Mysqltyagi541";
        String withdrawQuery = "UPDATE accounts SET balance = balance - ? WHERE accounts_number = ?";
        String depositQuery = "UPDATE accounts SET balance = balance + ? WHERE accounts_number = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("driver load successfully");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        try{
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("Connection established successfully");
            con.setAutoCommit(false);
            try {
                PreparedStatement withdrawStatement = con.prepareStatement(withdrawQuery);
                PreparedStatement depositStatement = con.prepareStatement(depositQuery);
                withdrawStatement.setDouble(1, 500.00);
                withdrawStatement.setString(2, "account123");
                depositStatement.setDouble(1, 500.00);
                depositStatement.setString(2, "account456");
               int rowsAffectedWithdraw = withdrawStatement.executeUpdate();
               int rowsAffectedDeposit =  depositStatement.executeUpdate();
               if(rowsAffectedDeposit>0 && rowsAffectedWithdraw>0){
                   con.commit();
                   System.out.println("Transaction Successful!");
               }else{
                   con.rollback();
                   System.out.println("Transaction Failed!!");
               }

            } catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }


        }
    }
