import java.io.*;
import java.sql.*;
import java.util.Scanner;

import static java.lang.Class.forName;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException{

        String url ="jdbc:mysql://localhost:3306/shruti";
        String username = "root";
        String password = "Mysqltyagi541";
        String folder_path = "C:\\Users\\DELL\\Desktop\\images\\";
        String query = "select image_data from image_table where image_id = (?)";
//        String image_path = "C:\\Users\\DELL\\Desktop\\hanuman.jpeg";

//        String query = "insert into image_table(image_data) values(?)";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("driver load successfully");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        try{
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("Connection established successfully");
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1,1);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                byte[] image_data = resultSet.getBytes("image_data");
                String image_path = folder_path+"extractedImage.jpeg";
                OutputStream outputStream = new FileOutputStream(image_path);
                outputStream.write(image_data);
                System.out.println("image found");
            }else{
                System.out.println("image not found!!");

            }
            /*
            FileInputStream fileInputStream = new FileInputStream(folder_path);
            byte[] imageData = new byte[fileInputStream.available()];
            fileInputStream.read(imageData);
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setBytes(1,imageData);
            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows>0){
                System.out.println("Image inserted successfully");
            }else{
                System.out.println("Image not inserted");
            } */
            
             con.close();
            System.out.println("close connection");

        }catch (SQLException e){
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}