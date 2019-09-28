public class Teacher {

	private int id;
	private String name;
	private int lesson_id;
	private int maxday;
	private int maxweek;

	public Teacher(int id, String name,int lesson_id, int maxday, int maxweek) {
		this.id = id;
		this.name = name;
		this.lesson_id=lesson_id;
		this.maxday = maxday;
		this.maxweek = maxweek;
	}
	
	public int get_Teacher_Id() {
		return id;
	}
	
	public String get_Teacher_Name() {
		return name;
	}
	
	public int get_lesson_id(){
		return lesson_id;
	}
	
	public int get_Max_Day() {
		return maxday;
	}
	
	public int get_Max_Week() {
		return maxweek;
	}
	
	public void set_Teacher_Id(int id) {
		this.id = id;
	}
	
	public void set_Teacher_Name(String name) {
		this.name = name;
	}
	
	public void set_lesson_id(int lesson_id){
		this.lesson_id=lesson_id;
	}
	
	public void set_Max_Day(int maxday) {
		this.maxday = maxday;
	}
	
	public void set_Max_Week(int maxweek) {
		this.maxweek = maxweek;
	}
	
	public String toString() {
		return ("Ta xarakthristika tou teacher einai: \nID: " + get_Teacher_Id() + "\nName: " + get_Teacher_Name()+"\nKwdikos Mathimatos: "+ get_lesson_id() + "\nMegistos Arithmos Wrwn/Evdomada: " + get_Max_Day() + "\nMegistos Arithmos Wrwn/Hmera: " + get_Max_Week() + "\n");
	}
	
	public int compare(Teacher p1, Teacher p2) {
        return p1.get_lesson_id() - p2.get_lesson_id(); 
	}
	
}