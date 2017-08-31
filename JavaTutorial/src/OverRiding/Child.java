package OverRiding;

public class Child extends Parent {

	public static void main(String[] args) {
		Parent c1 = new Child();
		Parent c2 = new Parent();
		c2.userInput();
		c1.userInput();

		
	}
		
	public void userInput(){
		super.userInput();
		System.out.println("Overridden");
			
			
		}
		
}
