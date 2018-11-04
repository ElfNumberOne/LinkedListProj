import java.io.*;
/**
*@author Marcus Gonzalez
*ID:011017835
*/
public class Driver {
	public static void main(String[] args) throws IOException{
		PrintWriter pw = new PrintWriter(new FileWriter("csis.txt"));

		Payroll payroll = new Payroll(pw);
		String gender = "F";
		float salary = 35000;
		String rate = "W";
		int tenure = 5;
		
		payroll.readFile();
		payroll.outputPayroll();
		payroll.employeeCount();
		payroll.outputGender(gender);
		payroll.salaryMinimum(salary,rate,tenure);
		payroll.raise("H", (float)10,(float) .75);
		payroll.raise("W", (float)350,(float)50);
		payroll.sort();
		payroll.hire();
		payroll.fire();
		
		pw.close();

	}
}