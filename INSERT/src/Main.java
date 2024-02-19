import java.sql.*;

import static java.lang.Class.forName;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException{

        String url ="jdbc:mysql://localhost:3306/shruti";
        String username = "root";
        String password = "Mysqltyagi541";
        String query = "INSERT INTO employees(id, name, job_title, salary) values(4, 'shivani', 'full stack developer',90000.0)";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("driver load successfully");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        try{
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("connection established successfully !!");
             Statement stmt = con.createStatement();
             int rowsaffected = stmt.executeUpdate(query);

             if(rowsaffected>0){
                 System.out.println("insert successfully " + rowsaffected + "row(s) affected");
             }else{
                 System.out.println("insertion failed!!");
             }



             stmt.close();
             con.close();
            System.out.println("close connection");

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}