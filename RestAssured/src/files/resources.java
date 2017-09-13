package files;

public class resources {
	public static String getSaltResource(){
		String saltResource="/in/user/auth/6019/salt";
		return saltResource;
	}
	
	public static String getOTPResource(){
		String otpResource = "/sampleMerchant/getOtp.jsp";
		return otpResource;
	}
	
	public static String getCReatePwdResource(){
		String createPwdResource = "/sampleMerchant/passwordEncrypt.jsp";
		return createPwdResource;
	}
	
	public static String postLoginResource(){
		String createPwdResource = "/in/user/auth/6019/login";
		return createPwdResource;
	}
	
	public static String postCompleteLoginResource(){
		String completeLoginResource = "/in/user/auth/6019/completeLoginFromNewDevice";
		return completeLoginResource;
	}
	

	

}
