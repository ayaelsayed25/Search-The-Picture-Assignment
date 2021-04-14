
package eg.edu.alexu.csd.datastructure.iceHockey.cs22;
import java.awt.Point;
import java.io.*;
import java.lang.*;
import java.util.*;
public class Picture {
    int lenCols = 0;
    int lenRows = 0;
    int[] numOfRelatedSquares = new int[100];
    ArrayList<MyPoint> list  = new ArrayList<MyPoint>();
    int numPoints = 0, numSq= 0, totalPoints = 0;
    Object[] arrlist;
    int arrofPoints = 0;
    char[][] arr;
    int counter = 0;
    public boolean checkThreshold(int threshold, int num)
    {
        float numOfsquares = (float)threshold / 4;
        if(numOfsquares > num)
            return false;
        else 
            return true;
    }
    public Point getCentre(int elements)
    {  
            MyPoint[] myarr = new MyPoint[elements];
            for(int i = 0; i<elements; i++)
            {
                myarr[i] = (MyPoint) arrlist[counter];
                counter++;
            }
            
            int maxX =  getMaxX(myarr); int maxY = getMaxY(myarr); int minX = getMinX(myarr); int minY =getMinY(myarr); 
            int centreX = (maxY + minY +1);
            int centreY = (maxX + minX +1) ;
            Point po = new Point(centreX, centreY);
            return po;
        
        
    }
    java.awt.Point[] findPlayers(String[] photo, int team, int threshold)
    {
        arr = convertToCharArr(photo);
        checkAll(team);
        java.awt.Point[] FinalPoints = new java.awt.Point[50];
        for(int i=0; i<numSq; i++)
        {
            
            if(checkThreshold(threshold, numOfRelatedSquares[i] ))
            {
                FinalPoints[arrofPoints++] = getCentre(numOfRelatedSquares[i]);
            }
            else
                counter = counter + numOfRelatedSquares[i] ;
            
        }
        return FinalPoints;
    }
    
    public int getMaxX(MyPoint[] arr)
    {
        int max = arr[0].GetX();
        for(int i=0; i<arr.length; i++)
        {
            if(arr[i].GetX() > max)
                max = arr[i].GetX();
        }
        return max;
    }
    public int getMaxY(MyPoint[] arr)
    {
        int max = arr[0].GetY();
        for(int i=0; i<arr.length; i++)
        {
            if(arr[i].GetY() > max)
                max = arr[i].GetY();
        }
        return max;
    }
    public int getMinX(MyPoint[] arr)
    {
        int min = arr[0].GetX();
        for(int i=0; i<arr.length; i++)
        {
            if(arr[i].GetX() < min)
                min = arr[i].GetX();
        }
        return min;
    }
    public int getMinY(MyPoint[] arr)
    {
        int min = arr[0].GetY();
        for(int i=0; i<arr.length; i++)
        {
            if(arr[i].GetY() < min)
                min = arr[i].GetY();
        }
        return min;
    }
    
    
    public char[][] convertToCharArr(String[] words)
    {
        char[][] myarr = new char[50][50];
        lenRows = words.length;
        
        for(int i=0; i<lenRows; i++)
        {
            String mine = words[i];
            lenCols = mine.length();
            for(int j =0; j<lenCols ; j++)
            {
                myarr[i][j] = mine.charAt(j);
            }
        }
        
        return myarr;
    }
    
    public void checkDown (int i, int j, int val)
    {
        
        if((arr[i][j] != (char)(val + '0') ) || exists(i, j))
            return;
        list.add( new MyPoint(i, j));
        numPoints++;
        totalPoints++;
        checkLeft(i, j-1, val);
        checkRight(i, j+1,val);
        
        checkUp(i-1, j,val);
        checkDown(i+1, j, val);
        
    }
    public void checkRight (int i, int j,int val)
    {
        
        if((arr[i][j] != (char)(val + '0') ) || exists(i, j))
            return;
        list.add( new MyPoint(i, j));
        numPoints++;
        totalPoints++;
        checkLeft(i, j-1, val);
        checkUp(i-1, j, val);
        checkDown(i+1, j, val);
        checkRight(i, j+1, val);
    }
    public void checkLeft (int i, int j, int val)
    {
        
        if(j<0 || (arr[i][j] != (char)(val + '0') ) || exists(i, j))
            return;
        list.add( new MyPoint(i, j));
        numPoints++;
        totalPoints++;
        checkUp(i-1, j, val);
        checkRight(i, j+1, val);
        checkDown(i+1, j, val);
        checkLeft(i, j-1,  val);
        
    }
    public void checkUp (int i, int j, int val)
    {
        
        if(i<0 || (arr[i][j] != (char)(val + '0') ) || exists(i, j))
            return;
        list.add( new MyPoint(i, j));
        numPoints++;
        totalPoints++;
        checkRight(i, j+1, val);
        checkLeft(i, j-1, val);
        checkDown(i+1, j, val);
        checkUp(i-1, j,val);
    }
    public boolean exists(int i, int j)
    {
        for(int k =0; k< list.size() ; k++)
        {
            if(list.get(k).GetX() == i && list.get(k).GetY() == j)
                return true;
        }
        return false;
    }
    public void checkAll( int val)
    {
        for(int i =0; i<lenRows; i++)
        {
            for(int j=0; j<lenCols; j++)
            {
                if(arr[i][j] == (char)(val + '0') && !(exists(i, j)))
                {    
                checkDown(i, j, val);
                
                numOfRelatedSquares[numSq] = numPoints;
                numPoints =0;
                numSq ++;
                
                }
            }
        }
        arrlist = list.toArray();
    }
    public int getSize()
    {
        return arrofPoints;
    }
   
}
