/********************************************
 * Covering (Acoperire)
 * @author: Ioana Pascu
 * Created: 10/11/2016
 ********************************************/
package ex1.acoperire;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Ex1Acoperire {
    static int a, b; //margins of the first interval
    static int n; //number of intervals given
    static int start = 0;
    static ArrayList<Interval> arr = new ArrayList<>();//will contain the given intervals
    static ArrayList<Interval> sol = new ArrayList<>();//will contain all the intervals that form the solution
    
    static Interval max_fin(int t){//returns the interval that contains t and goes as further right as possible
        int i = start;//we won't start the search from where we left in the last function call
        Interval max = null;
        while (true){    
            if (i >= arr.size()) break; //we exit if we are out of bounds
            if (arr.get(i).getA() > t) break;
            int left = arr.get(i).getA();
            int right = arr.get(i).getB();
            
            if (left <= t && right >=t && max == null)
                max = new Interval(left, right);
   
            if (left <= t && right >= t && right >= max.getB())
                max = new Interval(left, right);
                           
            i++;
        }
        start = i; //next function call, we start searching from where we stopped
        return max;       
    }
    
    static void print(){
        Collections.sort(arr);//we sort the array by the left margin (a)
        
        try{
            PrintStream printer = new PrintStream(new File("date.out"));
            int t = a;
            int fin;
            
            while (t < b){//we stop when we reach the right margin
                Interval max = max_fin(t);
                if (max != null){
                    fin = max.getB();
                    sol.add(max);
                    t = fin;
                } 
                else {//there is no way we can cover the whole interval
                    sol.clear();
                    break;
                }
            }
            if (sol.size() != 0) 
                for (int i = 0; i < sol.size(); i++)
                    printer.println(sol.get(i));
            else printer.println("-1");
        }
        catch(Exception FileNotFoundException){
            System.out.println("Fisier inexistent!");
        }
    }
    
    static void read(){//reads the data and adds it in an array
        try{
            Scanner sc = new Scanner (new File("date.in"));
            a = sc.nextInt();
            b = sc.nextInt();
            n = sc.nextInt();
            
            for (int i = 0; i < n; i++){
                int a = sc.nextInt();//left margin of interval
                int b = sc.nextInt();//right margin of interval
                arr.add(new Interval(a, b));
            }                       
        }
        catch(Exception FileNotFoundException){
            System.out.println("Fisier inexistent!");
        }
    }
    
    public static void main(String[] args) {
        read();
        print();
    }
    
}
