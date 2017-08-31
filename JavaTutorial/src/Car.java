
public class Car {
	private String make ;
	int speed;
	int gear;
	
	public Car(){
		this.speed=0;
		this.gear=0;
		System.out.println("Executing constructor without arguements");
		
	}
	
	public Car(int speed , int gear){
		System.out.println("Executing constructor with arguements");
		this.speed=speed;
		this.gear=gear;
	}
	
	public void setmake(String make){
		this.make=make;
		
	}
	
	public void showMake(){
		System.out.println("Make is :" + make);
	}
	

}
