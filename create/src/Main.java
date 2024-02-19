import java.sql.*;

import static java.lang.Class.forName;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException{

        String url ="jdbc:mysql://localhost:3306/shruti";
        String username = "root";
        String password = "Mysqltyagi541";
        String query = "select *from employees";

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
             ResultSet rs = stmt.executeQuery(query);
             while(rs.next()){
                 int id = rs.getInt("id");
                 String name = rs.getString("name");
                 String job_title = rs.getString("job_title");
                 double salary = rs.getDouble("salary");
                 System.out.println();
                 System.out.println("=============================");
                 System.out.println("ID = " +id);
                 System.out.println("NAME = " +name);
                 System.out.println("JOB TITLE = " +job_title);
                 System.out.println("salary = " +salary);
             }
             rs.close();
             stmt.close();
             con.close();
            System.out.println("close connection");

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}