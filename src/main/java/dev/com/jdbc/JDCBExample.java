package dev.com.jdbc;

import java.sql.*;

public class JDCBExample {
    static final String DRIVER = "com.mysql.jdbc.Driver";
    static final String DB = "jdbc:mysql://localhost/world?useSSL=false";
    static final String USER = "root";
    static final String PASSWORD = "root";

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement  = null;
        CallableStatement callableStatement = null;
        String updateQuery = "UPDATE `city` SET `Population`= ? WHERE `id` = ?";
        String filmInStockQuery  = "{call film_in_stock(?,?,?)}";


        try {
            //Driver registration
            Class.forName(DRIVER);

            //Open connection
            connection = DriverManager.getConnection(DB, USER, PASSWORD);
            preparedStatement =  connection.prepareStatement(updateQuery);
            callableStatement = connection.prepareCall(filmInStockQuery);

            preparedStatement.setInt(1, 1000500);
            preparedStatement.setInt(2, 1);

            callableStatement.setInt(1, 10);
            callableStatement.setInt(2, 5);
            int fRes =  callableStatement.getInt(3);

            int rows = preparedStatement.executeUpdate();
            System.out.println("rows = "+rows);

            preparedStatement.close();
            connection.close();

        } catch (ClassNotFoundException e) {
            System.out.println("Can not find JDBC Driver");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
