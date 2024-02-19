import java.sql.*;
import java.util.Scanner;

import static java.lang.Class.forName;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException{

        String url ="jdbc:mysql://localhost:3306/shruti";
        String username = "root";
        String password = "Mysqltyagi541";
       // String query = "Select *from employees where name = ?";
        String query = "insert into employees(id, name, job_title, salary) values(?,?,?,?)";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("driver load successfully");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        try{
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("connection established successfully !!");
            Scanner sc = new Scanner(System.in);
            int id = sc.nextInt();
            sc.nextLine();
            String name = sc.nextLine();
            String job_title = sc.nextLine();
            Double salary = sc.nextDouble();

           //  Statement stmt = con.createStatement();
            /* This is for fetch the data
              PreparedStatement = con.prepareStatement(query);
              preparedStatement.setString(1,"shruti");
              ResultSet resultSet = preparedStatement.executeQuery();
              while (resultSet.next()){
                  int id = resultSet.getInt("id");
                  String name = resultSet.getString("name");
                  String job_title = resultSet.getString("job_title");
                  double salary = resultSet.getDouble("salary");
                  System.out.println("ID :" + id);
                  System.out.println("NAME : " + name);
                  System.out.println("JOB TITLE : " + job_title);
                  System.out.println("SALARY : " + salary);
              } */
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,name);
            preparedStatement.setString(3, job_title);
            preparedStatement.setDouble(4,salary);

            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected>0){
                System.out.println("insert successfully");
            }else{
                System.out.println("insertion failed");
            }

            preparedStatement.close();
             con.close();
            System.out.println("close connection");

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}