package OverLoading;
import java.util.Scanner;


public class area {
	/***
	 * 
	 * @param args
	 */
//method overloading due to different no: of parameters
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter square's length");
		double a = sc.nextDouble();
		area(a);
		System.out.println("Enter legth of sides of rectangle");
		double b = sc.nextDouble();
		double c = sc.nextDouble();
		area(b , c);
		sc.close();
	}
		public static void area(double a) {
			double ar=a*a;
			System.out.println("The area of square is " + ar);
		}
		
		public static void area(double a , double b){
			double ar = a*b;
			System.out.println("Area of rectangle is " + ar);
		}
		
	}




