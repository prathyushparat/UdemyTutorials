package OverLoading;
import java.util.Scanner;


public class OverLoading {

	public static void main(String[] args) {
		Scanner sc  = new Scanner(System.in);
		System.out.println("Enter the length of the side of a square");
		if(sc.hasNextInt()){
			int a = sc.nextInt();
			area(a);
		}
		else{
			double b = sc.nextDouble();
			area(b);
		}		

 

	}
	
	public static void area(int a){
		int ar = a*a;
		System.out.println("Area of square is "+ ar);
	}
	public static void area(double a){
		double ar = a*a;
		System.out.println("Area of square is " + ar);
	}

}
