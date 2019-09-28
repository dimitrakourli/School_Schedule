import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class InsertInput {

private ArrayList<Lesson> lessons;
private ArrayList<Teacher> teachers;
	
	public InsertInput(){
		lessons = new ArrayList<Lesson>();
		teachers = new ArrayList<Teacher>();
	}

	public ArrayList<Lesson> readDataLessons(String path){

		try {
			Scanner fileIn = new Scanner(new File(path));
			while(fileIn.hasNext()){
				
				lessons.add(new Lesson(Integer.parseInt(fileIn.next()), fileIn.next(),fileIn.next(),Integer.parseInt(fileIn.next())));
			}
			
		} catch (FileNotFoundException e) {
			System.err.println("Error reading file");
			e.printStackTrace();
		}
		return lessons;
	}
	
	public ArrayList<Teacher> readDataTeachers(String path){
		
		try {
			Scanner fileIn2 = new Scanner(new File(path));
			while(fileIn2.hasNext()){
				teachers.add(new Teacher(Integer.parseInt(fileIn2.next()),fileIn2.next(),Integer.parseInt(fileIn2.next()),Integer.parseInt(fileIn2.next()),Integer.parseInt(fileIn2.next())));
			}
		} catch (FileNotFoundException e) {
			System.err.println("Error reading file");
			e.printStackTrace();
		}
		return teachers;
	}
	
	public ArrayList<Lesson> getLesson(){
		return lessons;	
	}
	
	public ArrayList<Teacher> getTeacher(){
		return teachers;
	}
}