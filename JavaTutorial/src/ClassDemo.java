
public class ClassDemo {

	public static void main(String[] args) {
		
		
		CarClass c1 = new CarClass();
		c1.model="Tesla";
		//c1.year=2014;
		System.out.println(c1.model);
		System.out.println(c1.year);
		System.out.println(c1.abc());
	}
	
	

}

class CarClass {
	int year=2000;
	String model="Audi";
	
	public int abc(){
		return this.year;
	}
}
