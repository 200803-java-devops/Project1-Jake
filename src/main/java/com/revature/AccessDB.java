package com.revature;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccessDB 
{   public ResultSet getCounts()
    {   Connect connect = new Connect();
        Connection c = connect.getConnect();
        PreparedStatement p = null;
        ResultSet rs = null;

        try
        {   p = c.prepareStatement("SELECT requests FROM Count");   
            rs = p.executeQuery();
        }
        catch (SQLException s)
        {   s.printStackTrace();    }

        return rs;
    }

    public void addCount(int r, int temp)
    {   Connect connect = new Connect();
        Connection c = connect.getConnect();
        PreparedStatement p = null;
        ResultSet rs = null;
        String query = "UPDATE Count SET requests=" + temp + " WHERE id=" + r; 
        
        try
        {   p = c.prepareStatement(query);
            p.executeQuery();
        }   catch (SQLException s)
        {   s.printStackTrace();    }

        query = "Select * FROM Count";
        
        try 
        {   p = c.prepareStatement(query);
            rs = p.executeQuery();
        }   catch (SQLException s)
        {   s.printStackTrace();    }

       
        {   
            try 
            {   while (rs.next())
                System.out.println("id# " + rs.getString("id") + "  |  Servlet: " + rs.getString("Servlets")
                        + "  |  requests: " + rs.getString("requests"));
            } catch (SQLException e) 
            {   e.printStackTrace();    }
        }
    }   
}