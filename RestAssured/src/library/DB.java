package library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.Assert;


/**
 * The Class DB contains all static methods pertaining to DB Interactions 
 * 
 *
 */
public class DB
{
	public static String DB_URL=Generic.getPropetyValues("DB_URL");
	private static String DB_USER;
	private static String DB_PWD;
	
	private static Connection conn;		
	private static Statement stmt;
	
	private boolean DBflag=true;
	
	
	
	/**
	 * Opens a DB connection statement. Singleton class.
	 * 
	 * @return Connection Statement 
	 * @see result()
	 */
	public static Statement stmt()
	{
		
		DB_USER= Generic.getPropetyValues("DB_USER"); 
		DB_PWD=Generic.getPropetyValues("DB_PWD");
		
		try
		{
			System.out.println("Connecting to DB as "+DB_USER+ ' '+DB_PWD);
			if (stmt!=null)
				return stmt;
			else
				return (conn=DriverManager.getConnection(DB_URL, DB_USER, DB_PWD)).createStatement();
		}
		catch(SQLException s) 
		{
			closeDB();
			Assert.fail("Error connecting to DB \n"+s.getSQLState());s.printStackTrace();
		}
		return stmt;
	}
	
	private static ResultSet result(String query)
	{
		try
		{
			return stmt().executeQuery(query);
		}
		catch(SQLException s)
		{
			Assert.fail("Unable to Execute Query : "+query+'\n'+s.getSQLState());
			return null;
		}
	}
	
	public static void update(String query)
	{
		try
		{
			ELog.info("==== Executing Update Query : "+query+" ====");
			stmt().executeUpdate(query);
		}
		catch(SQLException s)
		{
			Assert.fail("Unable to Execute Query : "+query+'\n'+s.getSQLState());s.printStackTrace();
		}
	}
	
	/**
	 * Closes the current ResultSet. To be used under the corresponding method
	 * 
	 * @param result
	 */
	private static void close(ResultSet result)
	{
		if(result!=null)
		try {
				System.out.println("Closing DB Result set");
				result.close();
			} catch (SQLException e) {
				System.err.println("Warning : Error Closing ResultSet ");
				e.printStackTrace();
			}
	}
	
	/**
	 * Closes the DB connection. To be used under @AfterSuite
	 * 
	 * @see close(ResultSet)
	 */
	public static void closeDB()
	{
		try
		{
			
			if(conn!=null && !conn.isClosed())
			{
				System.out.println("Closing DB Connection  "+conn.getCatalog());
				conn.close();
			}
			if(stmt!=null && !stmt.isClosed())
			{
				System.out.println("Closing DB Statement ");
				stmt.close();
			}
			conn=null;stmt=null;
		}
		catch(Exception e){System.err.println("Warning : Error closing DB Connection");e.printStackTrace();}
	}
	
//=====================================================================================================================================================================//
	/**
	 * 	Returns claim code from the DB
	 * Can be overloaded to include TXN_ID as param
	 * 
	 * 
	 * @param mobileNo
	 * @return ClaimCode 
	 * 
	 */
	public static String getClaimCode(String mobileNo)
	{
		String claimCodeQuery="SELECT * FROM wibmo_in_6019_data.unclamed_user_funds where ACCESS_DATA='mobileNo'" 	 // and OF_TXN_ID='201504301113217338lI63nM2'
							  .replace("mobileNo", mobileNo),
							  
			   claimCode="",
			   column_label="CONFIRMATION_CODE";
		
		ResultSet result=null;
		
		try
		{
			ELog.info("==== Executing Query : "+claimCodeQuery+" ====");
			result=result(claimCodeQuery);
			result.next();  // Result pointer starts from null 
			
			return result.getString("CONFIRMATION_CODE");
			
		}
		catch(SQLException e)
		{
			Assert.fail("Unable to obtain ClaimCode. "+column_label+'\n'+e.getMessage()); e.printStackTrace();
		}
		finally
		{
			close(result);
		}

		return claimCode;		
	}
	
	public static String getPCACNumber(String mobileNo)
	{
		
		String pcAcNumberQuery="select * from wibmo_in.geo_user_reg_info "
				+ "where access_data = 'mobileNo' and program_id=6019".replace("mobileNo", mobileNo);
		String pcAcNumber=null , column_label="PC_AC_NUMBER";
		ResultSet result=null;
		
		try
		{
			ELog.info("==== Executing Query : "+pcAcNumberQuery+" ====");
			result=result(pcAcNumberQuery);
			result.next();
			pcAcNumber=result.getString(column_label);
			ELog.info("=== PC AC number of "+mobileNo+" is ==="+pcAcNumber);
		}
		catch(Exception e)
		{
			Assert.fail("Unable to obtain "+column_label+'\n'+e.getMessage());
		}
		finally
		{
			close(result);
		}

		return pcAcNumber;		
	}
	
	public static String getURN(String mobileNo)
	{
		
		String urnQuery="select * from wibmo_in_6019_data.pc_ac_master "
				+ "where pc_ac_number="
				+ "(select PC_AC_NUMBER from wibmo_in.geo_user_reg_info "
				+ "where access_data = 'mobileNo' and program_id=6019)".replace("mobileNo", mobileNo);
		String urn=null , column_label="BANK_URN";
		ResultSet result=null;
		try
		{
			ELog.info("==== Executing Query : "+urnQuery+" ====");
			result=result(urnQuery);
			result.next();
			urn=result.getString(column_label);
		}
		catch(Exception e)
		{
			Assert.fail("Unable to obtain "+column_label+'\n'+e.getMessage());
		}
		finally
		{
			close(result);
		}

		return urn;		
	}
	
	/**
	 *  
	 * 
	 * @return DEVICE_REG_MAX_COUNT under program_parameters . -1 if query unsuccessful
	 */
	public static int getDeviceRegMaxCountParameter()
	{
		String query="SELECT PARAM_VALUE FROM wibmo_in.program_parameters where PARAM_NAME='DEVICE_REG_MAX_COUNT' and PROGRAM_ID = '6019'";
		String column_label="PARAM_VALUE";
		
		ResultSet result=null;
		
		try
		{
			ELog.info("==== Executing Query : "+query+" ====");
			result=result(query);
			result.next();  	 
			
			return Integer.parseInt(result.getString(column_label));
			
		}
		catch(SQLException e){Assert.fail("Unable to obtain  column value "+column_label+'\n'+e.getMessage()); e.printStackTrace();}
		finally{close(result);}
		
		return -1;
	}
	
	/**
	 * 	Sets the current registration count in data.user_registration_device
	 * 
	 * @param deviceId
	 * @return count set , if successful , -1 otherwise
	 * 
	 */
	public static int setDeviceRegistrationCount(String deviceId,int count)
	{
		String setCountQuery="UPDATE wibmo_in_6019_data.user_registration_device SET COUNT='count' WHERE DEVICE_ID='deviceId'"
													.replace("deviceId", deviceId)
													.replace("count", count+"");
		
		try
		{
			ELog.info("==== Executing Query : "+setCountQuery+" ====");
			update(setCountQuery);
			return count;
		}
		catch(Exception e)
		{
			Assert.fail("Unable to update count for deviceId  "+deviceId+'\n'+e.getMessage());
			return -1;
		}
		
	}
	
	/**
	 * Method to update a merchant permission to charge again if 1st charge fails
	 * Sets the value to 1
	 * @param merchantId
	 * @see updateMerchantToPreventChargeReAttempt()
	 */
	public static void updateMerchantToAllowChargeReAttempt(String merchantId)
	{
		String updateQuery="UPDATE `wibmo_in_6019_data`.`merchant_iap_master` SET `ALLOW_CHARGE_REATTEMPT_ON_FAILURE`='1'"
				+ " WHERE `MERCHANT_ID`='merchantId'".replace("merchantId", merchantId);
		ResultSet result=null;
		try
		{
			ELog.info("==== Executing Query : "+updateQuery+" ====");
			update(updateQuery);
		}
		catch(Exception e)
		{
			Assert.fail("Unable to update merchant to allow charge reattempt");
		}
		finally
		{
			close(result);
		}
	}
	
	/**
	 * Method to update a merchant permission to prevent charge again if 1st charge fails
	 * Sets the value to 0
	 * @param merchantId
	 * @see updateMerchantToAllowChargeReAttempt()
	 * 
	 */
	public static void updateMerchantToPreventChargeReAttempt(String merchantId)
	{
		String updateQuery="UPDATE `wibmo_in_6019_data`.`merchant_iap_master` SET `ALLOW_CHARGE_REATTEMPT_ON_FAILURE`='0'"
				+ " WHERE `MERCHANT_ID`='merchantId'".replace("merchantId", merchantId);
		ResultSet result=null;
		try
		{
			ELog.info("==== Executing Query : "+updateQuery+" ====");
			update(updateQuery);
		}
		catch(Exception e)
		{
			Assert.fail("Unable to update merchant to prevent charge reattempt");
		}
		finally
		{
			close(result);
		}
	}
	
	/***
	 * Method to covert an account to desired KYC level
	 * @param mobNo
	 * @param kycLevel
	 */
	public static void updateKYCAutoUpgradeLevel(String mobNo , int kycLevel) {
		String pcAcNumber=getPCACNumber(mobNo);
		String updateQuery="UPDATE `wibmo_in_6019_data`.`pc_ac_master` SET `KYC_AUTO_UPGRADE`='1', `KYC_AUTO_UPGRADE_LEVEL`='"+kycLevel+"' "
				+ "WHERE `PC_AC_NUMBER`='"+pcAcNumber+"'" ;
		ResultSet result=null;
		try
		{
			ELog.info("==== Executing Query : "+updateQuery+" ====");
			update(updateQuery);
		}
		catch(Exception e)
		{
			Assert.fail("Unable to update kyc auto upgrade level");
		}
		finally
		{
			close(result);
		}
	}
	
	public static void deleteUser(String mobileNo)
	{
		
		String deleteUserQuery1="delete from wibmo_in_6019_usr_data.user_mobile_info "
														+ "where mobile='mobileNo'".replace("mobileNo", mobileNo);
		
		String deleteUserQuery2="delete from wibmo_in.geo_user_reg_info "
				+ "where access_data='mobileNo' and program_id=6019".replace("mobileNo", mobileNo);
		
		String urn=null;
		ResultSet result=null;
		
		try
		{
			ELog.info("==== Executing Query : "+deleteUserQuery1+" ====");
			update(deleteUserQuery1);
			ELog.info("==== Executing Query : "+deleteUserQuery2+" ====");
			update(deleteUserQuery2);
		}
		catch(Exception e)
		{
			Assert.fail("Unable to delete user "+'\n'+e.getMessage());
		}
		finally
		{
			close(result);
		}

	}

	
	
	
	
	
	
	
	
}