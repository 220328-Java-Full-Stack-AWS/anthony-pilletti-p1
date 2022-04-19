package com.revature.ers.util;

import java.sql.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConnectionManager {
    private static Connection connection; //declare a connection of type Connection
    private ConnectionManager(){
    } // no args constructor

    public static Connection getConnection(){
        if (connection == null) {
            connection = connect();
        }
        return connection;
    }

    public static void close() { //meant to close connection when done=
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Connection connect(){
        try{
            Class.forName("org.postgresql.Driver");
            Properties props = new Properties();
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream input = loader.getResourceAsStream("application.properties");
            props.load(input);

            String connectionString = "jdbc:postgresql://" +
                    props.getProperty("hostname") + ":" +
                    props.getProperty("port") + "/" +
                    props.getProperty("dbname") + "?schemaName=" +
                    props.getProperty("schemaName");

            String username = props.getProperty("username");
            String password = props.getProperty("password");

            connection = DriverManager.getConnection(connectionString, username, password);

            System.out.println("Connection String: " + connectionString);

        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
