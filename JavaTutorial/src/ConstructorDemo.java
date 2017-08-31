
public class ConstructorDemo {

	public static void main(String[] args) {
		Car c1 = new Car();
		c1.setmake("Audi");
		c1.showMake();
		System.out.println(c1.gear);
		System.out.println(c1.speed);
		Car c2 = new Car(10 , 1);
		System.out.println(c2.speed);
		System.out.println(c2.gear);
		
		

	}

}
