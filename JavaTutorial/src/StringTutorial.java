
public class StringTutorial {
	public static void main(String[] args) {
		String str ="This is a test string";
		String str1 = "hello";
		String str2 = "hello";
		String str3 = "World";
		String emp ="";
		String str4 = "   String with spaces   ";
		System.out.println(str);
		System.out.println(str.length());
		System.out.println(str.charAt(3));
		System.out.println(str.concat(" This is the appended string"));
		System.out.println(str.contains("is"));
		System.out.println(str.contains("crab"));
		System.out.println(str.startsWith("T"));
		System.out.println(str.startsWith("t"));
		System.out.println(str.endsWith("z"));
		System.out.println(str.endsWith("string"));
		System.out.println(str1.equals(str2));
		System.out.println(str1.equals(str3));
		System.out.println(str.indexOf("s")); //returns the index. Indexes starts from 0,1,2 onwards.
		System.out.println(str.indexOf("q")); //returns -1 since char not there
		System.out.println(str.isEmpty());
		System.out.println(emp.isEmpty());
		System.out.println(str4);
		System.out.println(str4.trim());
		System.out.println(str.replace("s", "z"));
		System.out.println(str.substring(6));
		System.out.println(str.substring(5, 10));
		
		char[] charArray = str.toCharArray();
		for (int i=0; i<charArray.length; i++){
			System.out.println(charArray[i]);
		}
		
		System.out.println(str.toLowerCase());
		System.out.println(str.toUpperCase());
		System.out.println(str + str2);
		System.out.println(str.concat("") + str4);

	}

}
