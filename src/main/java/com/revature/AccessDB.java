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
        {   p = c.prepareStatement("SELECT requests FROM Count;");   
            rs = p.executeQuery();
        }
        catch (SQLException s)
        {   s.printStackTrace();    }

        return rs;
    }

    public ResultSet getid()
    {   Connect connect = new Connect();
        Connection c = connect.getConnect();
        PreparedStatement p = null;
        ResultSet rs2 = null;

        try
        {   p = c.prepareStatement("SELECT id FROM COUNT;");
            rs2 = p.executeQuery();
        }   catch (SQLException s)
        {   s.printStackTrace();    }

        return rs2;
    }

    public void addCount(int r, int temp)
    {   Connect connect = new Connect();
        Connection c = connect.getConnect();
        PreparedStatement p = null;
        ResultSet rs = null;
        String query = "UPDATE Count SET requests=" + temp + " WHERE id=" + r + ";"; 
        
        try
        {   p = c.prepareStatement(query);
            p.executeQuery();
        }   catch (SQLException s)
        {   s.printStackTrace();    }

        query = "UPDATE Count SET servers='Server 1' WHERE id =1; UPDATE Count SET servers='Server 2' WHERE id=2; UPDATE Count SET servers='Server 3' WHERE id=3;";

        try
        {   p = c.prepareStatement(query);
            p.executeQuery();
        }   catch (SQLException s)
        {   s.printStackTrace();    }

        
        query = "Select * FROM Count;";
        
        try 
        {   p = c.prepareStatement(query);
            rs = p.executeQuery();
        }   catch (SQLException s)
        {   s.printStackTrace();    }

       
        {   
            try 
            {   while (rs.next())
                System.out.println("id# " + rs.getString("id") + "  |  Server: " + rs.getString("Servers")
                        + "  |  requests: " + rs.getString("requests"));
            } catch (SQLException e) 
            {   e.printStackTrace();    }
        }
    }   
}