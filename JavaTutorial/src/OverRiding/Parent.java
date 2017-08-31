package OverRiding;

import java.util.Scanner;

public class Parent {
	//Accept inputs from user
	public void userInput(){
	Scanner sc = new Scanner(System.in);
	System.out.println("Enter 1st i/p");
	int a = sc.nextInt();
	System.out.println("Enter 2nd input");
	int b = sc.nextInt();
	
	sc.close();
	}


}
