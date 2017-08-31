package OverLoading;
import java.util.Scanner;


public class DifferentDataTypeOverloadning {
/***
 * overloading due to different data type but same no: of args
 * @param args
 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the no: of oranges");
		int a = sc.nextInt();
		Orange(a);
		System.out.println("What is the first colour in Indian flag");
		String b = sc.next();
		Orange(b);
		sc.close();
	}
	
	public static void Orange(int count){
		System.out.println("No: of oranges are " + count);
	}
	
	public static void Orange(String colour){
		System.out.println("The colour is " + colour);
	}

}
