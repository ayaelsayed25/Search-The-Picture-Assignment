
package eg.edu.alexu.csd.datastructure.iceHockey.cs22;
import java.awt.Point;
import java.io.*;
import java.lang.*;
import java.util.*;
public class Main {

    public static void main(String[] args) {
        String[] arr = {
"44444H44S4",
"K444K4L444",
"4LJ44T44XH",
"444O4VIF44",
"44C4D4U444",
"4V4Y4KB4M4",
"G4W4HP4O4W",
"4444ZDQ4S4",
"4BR4Y4A444",
"4G4V4T4444"
};
        Picture myObj = new Picture();
        Point p;
        java.awt.Point[] points = myObj.findPlayers(arr, 4, 16);
        for(int i=0; i <myObj.getSize(); i++)
        {
            p = points[i];
            System.out.println(p.getX() + " " + p.getY());
        }
    }
}