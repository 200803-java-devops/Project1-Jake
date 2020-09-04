package com.revature;

public class Loadbalancer 
{   //Get request numbers from ServletCounter class
    public int router()
    {   ServletCounter counter = new ServletCounter();
        int route = 0;
        counter.getTheCounts();

        int temp1 = counter.getCount1();
        int temp2 = counter.getCount2();
        int temp3 = counter.getCount3();
        int[] array = new int[]{temp1,temp2,temp3};

        //Sort request numbers and set least connections first on array
        int num = array.length;     
        for (int i=0; i < num-1; i++)
        {   for (int j=0; j < num-i-1; j++)
            {   if (array[j] > array[j+1])
                {   int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }

        //Check to see which server it is and use appropriate route
        //Update numbers on database
        if (temp1 == array[0])
        {   counter.add1();
            route = 1;
        }
        else if (temp2 == array[0])
        {   counter.add2();
            route = 2;
        } 
        else if (temp3 == array[0])
        {   counter.add3();
            route = 3;
        }
        return route;
    }
}