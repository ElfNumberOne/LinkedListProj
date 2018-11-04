/**
*@author Marcus Gonzalez
*@version 20170429
*/
public class Employee implements Comparable{
	private String lname;
	private String fname;
	private String gender;
	private int tenure;
	private String rate;
	private float salary;
	/**
	*Constructor for objects of class Employee
	*/
	public Employee(){
		fname = null;
		lname = null;
		gender = null;
		tenure = 0;
		rate = null;
		salary = 0;
	}
	/**
	*Overload Constructor for objects of class Employee
	*@param fname
	*@param lname
	*/
	public Employee(String fname,String lname){
				this.fname = fname;
				this.lname = lname;
				gender = null;
				tenure = 0;
				rate = null;
				salary = 0;
		}
	/**
	*Overload Constructor for objects of class Employee
	*@param fname
	*@param lname
	*@param gender
	*/
	public Employee(String fname,String lname, String gender){
			this.fname = fname;
			this.lname = lname;
			this.gender = gender;
			tenure = 0;
			rate = null;
			salary = 0;
	}
	/**
	*get method for first name
	*@return fname
	*/
	public String getfName(){
		return fname;
	}
	/**
	*get method for last name
	*@return lname
	*/
	public String getlName(){
			return lname;
		}
	/**
	*get method for gender
	*@return gender
	*/
	public String getGender(){
		return gender;
	}
	/**
	*get method for tenure
	*@return tenure
	*/
	public int getTenure(){
		return tenure;
	}
	/**
	*get method for rate
	*@return rate
	*/
	public String getRate(){
		return rate;
	}
	/**
	*get method for salary
	*@return salary
	*/
	public float getSalary(){
		return salary;
	}
	/**
	*set method for tenure
	*@param tenure
	*/
	public void setTenure(int tenure){
		this.tenure = tenure;
	}
	/**
	*set method for rate
	*@param rate
	*/
	public void setRate(String rate){
		this.rate = rate;
	}
	/**
	*set method for salary
	*@param salary
	*/
	public void setSalary(float salary){
		this.salary = salary;	
	}
	/**
	*method for comparing employee names
	*@param o
	*@return int
	*/
	public int compareTo(Object o){
		Employee temp = new Employee();
		temp = (Employee)o;
		if (((this.getlName()).compareTo(temp.getlName()))!=0){
			return (this.getlName()).compareTo(temp.getlName());
		}
		else{
			return (this.getfName()).compareTo(temp.getfName());
		}
	}
	
}