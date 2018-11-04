import java.io.*;
import java.util.*;
/**
*@author Marcus Gonzalez
*@version 20170429
*/
public class Payroll {
	private ObjectListInterface payroll;
	PrintWriter pw;
	private int employeeCount;
	/**
	*Constructor for objects of class EvalPostfix
	*@param pw prints to "csis.txt"
	*/
	public Payroll(PrintWriter pw){
		this.pw = pw;
		payroll = new ObjectList();
		employeeCount = 0;
	}
	/**
	*reads file to enter employees into payroll
	*/
	public void readFile()throws IOException{
		Scanner sc = new Scanner(new File("payfile.txt"));
		StringBuilder sb = new StringBuilder();
		StringBuilder sb1 = new StringBuilder();
		String gender = null;
		int tenure = 0;
		String rate = null;
		float salary = 0;
		
		while(sc.hasNextLine()){
			sb.append(sc.next());
			sb1.append(sc.next());
			gender = sc.next();
			tenure = sc.nextInt();
			rate = sc.next();
			salary = sc.nextFloat();
						
			Employee temp = new Employee(sb.toString(), sb1.toString(), gender);
			temp.setTenure(tenure);
			temp.setRate(rate);
			temp.setSalary(salary);
			payroll.addLast(temp);
			sb.setLength(0);
			sb1.setLength(0);
			++employeeCount;
		}
	}
	/**
	*outputs current payroll
	*/
	public void outputPayroll(){
		ObjectListNode p = new ObjectListNode();
		p = payroll.getFirstNode();
		Employee temp = (Employee)p.getInfo();
		this.header();
		for(int i = 0; i < employeeCount;i++){
			System.out.printf("%s\t%s\t%s\t%d\t%s\t%.2f\n",temp.getfName(),temp.getlName(),temp.getGender(),temp.getTenure(),temp.getRate(),temp.getSalary());
			pw.printf("%-10s\t%-10s\t\t%s\t\t%d\t\t%s\t\t%.2f\n",temp.getfName(),temp.getlName(),temp.getGender(),temp.getTenure(),temp.getRate(),temp.getSalary());
			if (p.getNext() != null){
				p = p.getNext();
				temp = (Employee)p.getInfo();
			}
		}
		
	}
	/**
	*outputs employee count
	*/
	public void employeeCount(){
		System.out.println("\nNumber of employees:"+employeeCount+"\n");
		pw.println("\nNumber of employees:"+employeeCount+"\n");

	}
	/**
	*Outputs all employees of given gender
	*@param gender
	*/
	public void outputGender(String gender){
		Employee temp = new Employee();
		ObjectListNode p = new ObjectListNode();
		p = payroll.getFirstNode();
		if (gender == "F"){
			System.out.println("Female Employees:");
			pw.println("Female Employees:");
		}
		else{
			System.out.println("Male Employees:");
			pw.println("Male Employees:");
		}
		for(int i = 0; i < payroll.size();i++){
			temp = (Employee)p.getInfo();
			if(temp.getGender().equals(gender)){
				System.out.printf("%s\t%s\n",temp.getfName(),temp.getlName());
				pw.printf("%-10s\t%s\n",temp.getfName(),temp.getlName());

			}
			p = p.getNext();
		}
		
	}
	/**
	*outputs all employees with the minimum params given
	*@param salary
	*@param rate
	*@param tenure
	*/
	public void salaryMinimum(float salary,String rate,int tenure){
		System.out.printf("\nEmployees that make over %.2f a year:\n",salary);
		pw.printf("\nEmployees that make over %.2f a year:\n",salary);
		Employee temp = new Employee();
		ObjectListNode p = new ObjectListNode();
		p = payroll.getFirstNode();
		for(int i = 0; i < payroll.size();i++){
			temp = (Employee)p.getInfo();
			if (temp.getRate().equals(rate) && temp.getTenure() >= tenure){
				if(temp.getSalary()*52 > salary){
					System.out.printf("%s\t%s\t%.2f\n",temp.getfName(),temp.getlName(),temp.getSalary());
					pw.printf("%-10s\t%-10s\t%.2f\n",temp.getfName(),temp.getlName(),temp.getSalary());
				}
			}
			p = p.getNext();
		}
	}
	/**
	*Gives a raise to employees receiving less than given salary and with given rate
	*@param rate
	*@param salary
	*param raise
	*/
	public void raise(String rate, float salary, float raise){
		Employee temp = new Employee();
		ObjectListNode p = new ObjectListNode();
		p = payroll.getFirstNode();
		if (rate.equals("H")){
			System.out.println("Hourly Employees receiving a raise:");
			pw.println("Hourly Employees receiving a raise:");
		}
		else{
			System.out.println("Weekly Employees receiving a raise:");
			pw.println("Weekly Employees receiving a raise:");
		}
		
		for(int i = 0; i < payroll.size();i++){
			temp = (Employee)p.getInfo();
			if (temp.getRate().equals(rate) && temp.getSalary() < salary){
				temp.setSalary(temp.getSalary()+raise);
				System.out.printf("%s\t%s\t%.2f\n",temp.getfName(),temp.getlName(),temp.getSalary());
				pw.printf("%-10s\t%-10s\t%.2f\n",temp.getfName(),temp.getlName(),temp.getSalary());
			}
			p = p.getNext();
		}
	}
	/**
	*sorts payroll into alphabetical order
	*/
	public void sort(){
		ObjectListInterface newList = new ObjectList();
		Employee temp = new Employee();
		Employee temp1 = new Employee();
		ObjectListNode payrollPointerLag = new ObjectListNode();
		ObjectListNode payrollPointerLead = new ObjectListNode();
		ObjectListNode newListPointerlead = new ObjectListNode();
		ObjectListNode newListPointerlag = new ObjectListNode();
		
		payrollPointerLead = payroll.getFirstNode();
		payroll.removeFirst();
		newList.insert(payrollPointerLead);
		newListPointerlead = newList.getFirstNode();
		
		for(int i = payroll.size(); i > 0;i--){
			payrollPointerLead = payroll.getFirstNode();
			payroll.removeFirst();
			temp = (Employee)payrollPointerLead.getInfo();
			newListPointerlead = newList.getFirstNode();
			newListPointerlag = null;
			temp1 = (Employee)newListPointerlead.getInfo();
			for(int j = 0; j < newList.size();j++){
				if(temp.compareTo((Object)temp1) < 0){
					if (newListPointerlag == null){
						newList.addFirst(payrollPointerLead);
						break;
					}
					else {
						newList.insertAfter(newListPointerlag, payrollPointerLead);
						break;
					}
				}
				
				else if(j == newList.size()-1){
					newList.insertAfter(newListPointerlead, payrollPointerLead);
					break;
				}
				else{
					newListPointerlag = newListPointerlead;
					newListPointerlead = newListPointerlead.getNext();
					temp1 = (Employee)newListPointerlead.getInfo();	
				}
			}
		}
		payroll = newList;
		payrollPointerLead = payroll.getFirstNode();
		temp = (Employee)payrollPointerLead.getInfo();
		System.out.println("Name\t\tSalary");
		pw.println("Name\t\tSalary");
		for(int i = 0; i < employeeCount;i++){
			System.out.printf("%s\t%s\t%.2f\n",temp.getfName(),temp.getlName(),temp.getSalary());
			pw.printf("%-10s\t%-10s\t%.2f\n",temp.getfName(),temp.getlName(),temp.getSalary());
			if (payrollPointerLead.getNext() != null){
				payrollPointerLead = payrollPointerLead.getNext();
				temp = (Employee)payrollPointerLead.getInfo();
			}
		}
	}
	/**
	*hires employees from hirefile.txt
	*/
	public void hire()throws IOException{
		Scanner sc = new Scanner(new File("hirefile.txt"));
		StringBuilder sb = new StringBuilder();
		StringBuilder sb1 = new StringBuilder();
		String gender = null;
		int tenure = 0;
		String rate = null;
		float salary = 0;
		ObjectListNode payrollPointerlead = new ObjectListNode();
		ObjectListNode payrollPointerlag = new ObjectListNode();
		Employee temp1 = new Employee();

		
		while(sc.hasNextLine()){
			sb.append(sc.next());
			sb1.append(sc.next());
			gender = sc.next();
			tenure = sc.nextInt();
			rate = sc.next();
			salary = sc.nextFloat();
			
			payrollPointerlag = null;
			payrollPointerlead = payroll.getFirstNode();
			temp1 = (Employee)payrollPointerlead.getInfo();

			Employee temp = new Employee(sb.toString(), sb1.toString(), gender);
			temp.setTenure(tenure);
			temp.setRate(rate);
			temp.setSalary(salary);

			//insert in sorted payroll
			for(int j = 0; j < payroll.size();j++){
				if(temp.compareTo((Object)temp1) < 0){
					if (payrollPointerlag == null){
						payroll.addFirst((Object)temp);
						break;
					}
					else {
						payroll.insertAfter(payrollPointerlag, temp);
						break;
					}
				}
				
				else if(j == payroll.size()-1){
					payroll.insertAfter(payrollPointerlead, temp);
					break;
				}
				else{
					payrollPointerlag = payrollPointerlead;
					payrollPointerlead = payrollPointerlead.getNext();//incrementing
					temp1 = (Employee)payrollPointerlead.getInfo();	
				}
			}
			
			sb.setLength(0);
			sb1.setLength(0);
			++employeeCount;
		}
		payrollPointerlead = payroll.getFirstNode();
		Employee temp = new Employee();
		temp = (Employee)payrollPointerlead.getInfo();

		System.out.println("Updated Payroll:");
		pw.println("Updated Payroll:");
		for(int i = 0; i < employeeCount;i++){
			System.out.printf("%s\t%s\n",temp.getfName(),temp.getlName());
			pw.printf("%-10s\t%-10s\n",temp.getfName(),temp.getlName());

			if (payrollPointerlead.getNext() != null){
				payrollPointerlead = payrollPointerlead.getNext();
				temp = (Employee)payrollPointerlead.getInfo();
			}
		}

	}
	/**
	*fires employees from firefile.txt
	*/
	public void fire()throws IOException{
			Scanner sc = new Scanner(new File("firefile.txt"));
			StringBuilder sb = new StringBuilder();
			StringBuilder sb1 = new StringBuilder();
			ObjectListNode payrollPointer = new ObjectListNode();
			
			while(sc.hasNextLine()){
				sb.append(sc.next());
				sb1.append(sc.next());
				Employee temp = new Employee();
				payrollPointer = payroll.getFirstNode();
				
				for(int j = 0; j < payroll.size();j++){
					temp = (Employee)payrollPointer.getInfo();
					if ((sb.toString()).equals(temp.getfName()) && (sb1.toString()).equals(temp.getlName())){
						payroll.remove(temp);
						break;
					}
					payrollPointer = payrollPointer.getNext();		
				}
				sb.setLength(0);
				sb1.setLength(0);
				--employeeCount;	
			}
			payrollPointer = payroll.getFirstNode();
			Employee temp = new Employee();
			temp = (Employee)payrollPointer.getInfo();

			System.out.println("Updated Payroll:");
			pw.println("Updated Payroll:");
			for(int i = 0; i < employeeCount;i++){
				System.out.printf("%s\t%s\n",temp.getfName(),temp.getlName());
				pw.printf("%-10s\t%s\n",temp.getfName(),temp.getlName());
				if (payrollPointer.getNext() != null){
					payrollPointer = payrollPointer.getNext();
					temp = (Employee)payrollPointer.getInfo();
				}
			}
		}
	/**
	*Header for output payroll method
	*/
	public void header(){
		System.out.printf("%-20s\tGender\tTenure\tRate\tSalary\n","Name");
		pw.printf("%-20s\t\tGender\tTenure\tRate\t\tSalary\n","Name");
	}
	
}