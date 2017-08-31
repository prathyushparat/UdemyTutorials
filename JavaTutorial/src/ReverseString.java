
public class ReverseString {

	public static void main(String[] args) {
	
	String input="this is a string";
	reverse(input);
	}	
	
	public static void reverse(String input){
		String reverse="";
		if(input.length()<=1)
			System.out.println(input);
		else{
			String[] original=input.split("\\s");
			for(String item :original){
				reverse=item+" " + reverse;
			}
		System.out.println(reverse);
		}
	}

}
