package com.revature;

import java.sql.ResultSet;
import java.sql.SQLException;

//This class will attempt to access the Database and fish out request numbers for each server.
public class ServletCounter 
{   public int temp1 = 0;
    public int temp2 = 0;
    public int temp3 = 0;

    //Gets request numbers from each server
    public void getTheCounts() 
    {   ResultSet rs = null;
        AccessDB access = new AccessDB();
        rs = access.getCounts();
        try 
        {   rs.next();
            System.out.println(rs.getInt("requests"));
            this.temp1 = rs.getInt("requests");
            rs.next();
            System.out.println(rs.getInt("requests"));
            this.temp2 = rs.getInt("requests");
            rs.next();
            System.out.println(rs.getInt("requests"));
            this.temp3 = rs.getInt("requests");
        }   catch (SQLException s) 
        {   s.printStackTrace();    }
    }

    //Add one request counter to appropriate server
    protected void add1()
    {   this.temp1 += 1;
        int r = 1;
        AccessDB access = new AccessDB();
        access.addCount(r, this.temp1);
    }

    protected void add2()
    {   this.temp2 += 1;
        int r = 2;
        AccessDB access = new AccessDB();
        access.addCount(r, this.temp2);
    }
    
    protected void add3()
    {   this.temp3 += 1;
        int r = 3;
        AccessDB access = new AccessDB();
        access.addCount(r, this.temp3);
    }

    //Return "temp" request numbers back to LoadBalancer
    protected int getCount1()
    {   return temp1;   }

    protected int getCount2()
    {   return temp2;   }

    protected int getCount3()
    {   return temp3;   }
}