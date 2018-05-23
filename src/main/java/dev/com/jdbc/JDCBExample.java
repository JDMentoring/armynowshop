package dev.com.jdbc;

import java.sql.*;

public class JDCBExample {
    static final String DRIVER = "com.mysql.jdbc.Driver";
    static final String DB = "jdbc:mysql://localhost/world?useSSL=false";
    static final String USER = "root";
    static final String PASSWORD = "root";

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;

        try {
            //Driver registration
            Class.forName(DRIVER);

            //Open connection
            connection = DriverManager.getConnection(DB, USER, PASSWORD);
            statement = connection.createStatement();

            //Execute query
            String sqlQuery = "SELECT * FROM city";
            ResultSet rs = statement.executeQuery(sqlQuery);

            System.out.println("ID\tNAME\tCODE\tDistrict\tPopulation");
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String code = rs.getString(3);
                String district = rs.getString(4);
                int pop = rs.getInt(5);


                System.out.println(id + "\t" + name + "\t" + code + "\t" + district + "\t" + pop);
            }


            rs.close();
            statement.close();
            connection.close();

        } catch (ClassNotFoundException e) {
            System.out.println("Can not find JDBC Driver");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
