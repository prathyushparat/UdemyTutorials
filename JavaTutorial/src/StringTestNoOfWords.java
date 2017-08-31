
public class StringTestNoOfWords {

	public static void main(String[] args) {
		int countOfWords=0;
		String str = "Today is 5th day of the week";
		str=str.trim();
		char[] charArray = str.toCharArray();
		System.out.println(charArray.length);
		System.out.println(str.length());
		System.out.println(charArray);
		for(int i=0;i<charArray.length;i++){
			if(charArray[i]==' '){
				countOfWords=countOfWords+1;
				
			}
		}
		System.out.println("No: of words is " +countOfWords);
		for(int i=0;i<charArray.length;i++){
			
		}
				
		}
	
		

	}


