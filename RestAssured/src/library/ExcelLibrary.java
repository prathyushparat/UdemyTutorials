package library;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelLibrary {
	//static String testDataFile = ".\\TestData\\TestData.xlsx";
	static String testDataFile=".\\TestData\\TestData.xlsx";
	static String sheetName = "Login";
	static String testCaseId = null;
	static String testCaseName = null, automationTestData = null;
	static XSSFWorkbook wBook;
	static XSSFSheet wSheet;
	static XSSFCell testCaseNameCell, testCaseIdCell, testDataCell;
	
	public static String getTestData(String TCId , String sheetName) {
		try {
			FileInputStream testData = new FileInputStream(testDataFile);
			wBook = new XSSFWorkbook(testData);
			wSheet = wBook.getSheet(sheetName);
			int columnNum = 0, rowNum = 0;
			int totalRows = wSheet.getLastRowNum(); // returns the last
													// populated row index
			// System.out.println("No of tests are "+totalRows);

			for (int i = 0; i <= totalRows; i++) {
				testCaseId = wSheet.getRow(i).getCell(0).getStringCellValue();
				// System.out.println("==== Test case ID under loop ==== "+testCaseId);

				if (testCaseId.equals(TCId)) {

					// System.out.println("==== Test case ID is ==== "+testCaseId);
					testDataCell = wSheet.getRow(i).getCell(2);
					automationTestData = testDataCell.getStringCellValue();
					System.out.println("==== Test data is ==== "
							+ automationTestData);
					return automationTestData;

					/* break; */

				}
			}
			return "default";
			// System.out.println("==== Test case ID is ==== "+testCaseId);
			// return testCaseId;

		} catch (Exception e) {
			e.printStackTrace();
			return "Invalid test case ID";
		}

	}

	public static String getTestCase(String TCId , String sheetName) {

		try {
			FileInputStream testData = new FileInputStream(testDataFile);
			wBook = new XSSFWorkbook(testData);
			wSheet = wBook.getSheet(sheetName);
			int columnNum = 0, rowNum = 0;
			int totalRows = wSheet.getLastRowNum(); // returns the last
													// populated row index
			// System.out.println("No of tests are "+totalRows);

			for (int i = 0; i <= totalRows; i++) {
				testCaseId = wSheet.getRow(i).getCell(0).getStringCellValue();
				// System.out.println("==== Test case ID under loop ==== "+testCaseId);

				if (testCaseId.equals(TCId)) {

					System.out.println("==== Test case ID is ==== "
							+ testCaseId);
					testCaseNameCell = wSheet.getRow(i).getCell(1);
					testCaseName = testCaseNameCell.getStringCellValue();
					System.out.println("==== Test case name is ==== "
							+ testCaseName);
					return testCaseName;

					/* break; */

				}
			}
			return "default";
			// System.out.println("==== Test case ID is ==== "+testCaseId);
			// return testCaseId;

		} catch (Exception e) {
			e.printStackTrace();
			return "Invalid test case ID";
		}

	}


}
