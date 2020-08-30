package com.revature;

import java.sql.*;

public class Connect {
    static final String URL = "jdbc:postgresql://localhost:5431/Count";
    static final String USER = "Count";
    static final String PASS = "world";

    private static Connection c = null;

    public Connection getConnect()
    {   if (c == null)
        {   try   
            {   Class.forName("org.postgresql.Driver");
                c = DriverManager.getConnection(URL, USER, PASS);
                System.out.println("Connection Successful");
            }   catch (SQLException sqlException)
            {   System.out.println("Connection to SQL database failed");
                sqlException.printStackTrace();
            }   catch (Exception stackException)
            {   System.out.println("Could not load driver");
                stackException.printStackTrace();
            }
        }
        return c;
    }
}