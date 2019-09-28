public class Lesson {
	
	private int id;
	private String name;
	private String class_n;
	private int hours;

	public Lesson(int id, String name, String class_n, int hours) {
		this.id = id;
		this.name = name;
		this.class_n = class_n;
		this.hours = hours;	
	}
	
	public int get_Lesson_Id() {
		return id;
	}

	public String get_Lesson_Name() {
		return name;
	}
	
	public String get_Class_N() {
		return class_n;
	}
	
	public int get_Hours() {
		return hours;
	}
	
	public void set_Lesson_Id(int id) {
		this.id = id;
	}
	
	public void set_Lesson_Name(String name) {
		this.name = name;
	}
	
	public void set_Class_N(String class_n) {
		this.class_n = class_n;
	}
	
	public void set_Hours(int hours) {
		this.hours = hours;
	}
	
	public String toString() {
		return ("\nID: " + get_Lesson_Id() + "\nName: " + get_Lesson_Name() + "\nClass: " + get_Class_N() + "\nHours/Week: " + get_Hours() + "\n");
	}
}
