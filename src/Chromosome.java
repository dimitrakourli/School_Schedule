import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class Chromosome {
 //Table that holds the chromosome itself
	private int [][][] genes;
    //Integer that holds the fitness score of the chromosome
	private int fitness;
	int numOfclassA;
	int numOfclassB;
	int numOfclassC;
	
//Constructs a chromosome
	public Chromosome(InsertInput input){
		this.genes = new int[5][7][9];
		this.setInfoTable(input);
		this.calculateFitness(input);
	}
	
public void setInfoTable(InsertInput input){	//gemizoume ton trisdiastato pinaka
		
		numOfclassA=0;	// # mathimatwn prwtis taksis(poses fores pragmatopoiountai)
		numOfclassB=0;	// # mathimatwn deuteris taksis(poses fores pragmatopoiountai)
		numOfclassC=0;	// # mathimatwn tritis taksis(poses fores pragmatopoiountai)
		ArrayList<Teacher> teachers=new ArrayList<>(input.getTeacher());
		Collections.sort(teachers, new Comparator<Teacher>() //taksinomisi kathigitwn me vasi tous kwdikous mathimatwn
		{
		    @Override public int compare(Teacher t1, Teacher t2) 
		    {
		        return t1.get_lesson_id() - t2.get_lesson_id(); 
		    }
		});
		for (int i=0; i<teachers.size(); i++){
			if(teachers.get(i).get_lesson_id()/100==1){
				numOfclassA++;
			}else if(teachers.get(i).get_lesson_id()/100==2){
				numOfclassB++;
			}else{
				numOfclassC++;
			}
		}
		//i=imeres ,j=wres ,k=takseis
		
		for(int i=0; i<5; i++){
			for(int j=0; j<7; j++){
				for(int k=0; k<9; k++){
					int r; //random number
					if(k==0 || k==1 || k==2){ //an einai stin A tote to prosthetoume ston pinaka tuxaia alla stin meria tou pinaka pou antistoixei stin proti taksi
						r= (int)( Math.random()*(numOfclassA-1));
						genes[i][j][k]=teachers.get(r).get_Teacher_Id();
					}else if(k==3 || k==4 || k==5){
						r=(int)( Math.random()*(numOfclassB-1)+numOfclassA);
						genes[i][j][k]=teachers.get(r).get_Teacher_Id();
					}else{
						r=(int)( Math.random()*(numOfclassC-1)+numOfclassA+numOfclassB);
						genes[i][j][k]=teachers.get(r).get_Teacher_Id();
					}
				}
				
			}
		}
	
	}

//Constructs a copy of a chromosome
	public Chromosome(InsertInput input,int [][][] genes){
		this.genes = genes;
	}
	
	public int[][][] getGenes(){
		return this.genes;
	}
	
	public int getFitness(){
		return this.fitness;
	}
	
	public void setGenes(int[][][] genes){
		for(int i=0; i<5; i++){
			for(int j=0; j<7; j++){
				for(int k=0; k<9; k++){							
					this.genes[i][j][k] = genes[i][j][k];
				}
			}
		}
	}
	
	public void setFitness(int fitness){
		this.fitness = fitness;
	}
	
	public void calculateFitness(InsertInput input){
		int score = 0;
		ArrayList<Teacher> teachers=input.getTeacher();
		ArrayList<Lesson> lessons=input.getLesson();
		int id_t[]=new int [17];
		
		for(int i=0; i<17; i++){		//vazoume se enan pinaka ta id twn kathigitwn
			id_t[i]=i;
		}
		
		//an o kathigitis einai sto programma pio polu apo oti epitrepete tin vdomada
		
		int hours_a_week[]=new int[17];
		for(int i=0; i<5; i++){
			for(int j=0; j<7; j++){
				for(int k=0; k<9; k++){
					hours_a_week[genes[i][j][k]-1]++;
				}
			}
		}

		for(int x=0; x<hours_a_week.length; x++){
			for(int y=0; y<teachers.size(); y++){
				if(hours_a_week[x]>teachers.get(y).get_Max_Week() && teachers.get(y).get_Teacher_Id()==id_t[x]){
					score-=6;
				}else if(teachers.get(y).get_Teacher_Id()==id_t[x]){
					score+=23;
				}
			}	
		}
		
		//an o kathigitis einai sto programma tin mera pio polu apo oti prepei
		
		int hours_a_day[][]=new int[17][5];
		for(int i=0; i<5; i++){
			for(int j=0; j<7; j++){
				for(int k=0; k<9; k++){							
					hours_a_day[genes[i][j][k]-1][i]++;
				}
			}
		}
		for(int x=0; x<hours_a_day.length; x++){
			for(int y=0; y<hours_a_day[0].length; y++){
				for(int z=0; z<teachers.size(); z++){	
					if(hours_a_day[x][y]>teachers.get(x).get_Max_Day()&& teachers.get(x).get_Teacher_Id()==id_t[x]){
						score-=6;
					}else if(teachers.get(x).get_Teacher_Id()==id_t[x]){
						score+=22;
					}
				}
			}
		}
		
		//oi wres pou didaskoun oi kathigites ti vdomada na einai omoiomorfa katanemhmenes
		
		for(int x=0; x<hours_a_week.length; x++){
			for(int y=hours_a_week.length-1; y>=0; y--){
				if(Math.abs(hours_a_week[x]-hours_a_week[y])>3){		//estw katanemimenoi omoiomorfa theorountai an exoun to polu 3 wres diafora
					score-=4;
				}else{
					score+=8;
				}
			}
		}
		
		//an ta mathimata de didaskontai oses wres prepei
		int hours_lesson[]=new int[lessons.size()];
		for(int t=0; t<teachers.size(); t++) {
			for(int i=0; i<5; i++){
				for(int j=0; j<7; j++){
					for(int k=0; k<9; k++){	
						if(genes[i][j][k]==teachers.get(t).get_Teacher_Id() ){
							int c=1;
							if(teachers.get(t).get_lesson_id()/100==2){
								c=2;
							}else if(teachers.get(t).get_lesson_id()/100==3){
								c=3;
							}
							hours_lesson[(teachers.get(t).get_lesson_id()-(c*100)+18*(c-1))-1]++;
						}
					}
				}
			}
		}
		for(int x=0; x<hours_lesson.length; x++){
			if(hours_lesson[x]!=lessons.get(x).get_Hours()){
				score-=5;
			}else{
				score+=25;
			}
		}
		
		//oi wres pou didaskontai ta mathimata ti vdomada na einai omoiomorfa katanemhmenes
		
		int hours_lesson_per_day[][]=new int[lessons.size()][5];
		for(int t=0; t<teachers.size(); t++) {
			for(int i=0; i<5; i++){
				for(int j=0; j<7; j++){
					for(int k=0; k<9; k++){	
						if(genes[i][j][k]==teachers.get(t).get_Teacher_Id() ){
							int c=1;
							if(teachers.get(t).get_lesson_id()/100==2){
								c=2;
							}else if(teachers.get(t).get_lesson_id()/100==3){
								c=3;
							}
							hours_lesson_per_day[(teachers.get(t).get_lesson_id()-(c*100)+18*(c-1))-1][i]++;
						}
					}
				}
			}
		}
		
		for(int x=0; x<hours_lesson_per_day.length; x++){
			for(int y=0; y<hours_lesson_per_day[0].length; y++){
				if(hours_lesson_per_day[x][y]>1){		//estw katanemimena ortha einai ta mathimata pou ginontai to polu 1 fora ti mera
					score-=1;
				}else{
					score+=10;
				}
			}
		}
		this.fitness = score;
	}

	
	//Mutate by randomly changing the day,time,class
	public void mutate() {
		
		Random r = new Random();
		for(int j=0; j<7; j++){
			for(int k=0; k<9; k++){
				while(this.genes[r.nextInt(5)][r.nextInt(7)][r.nextInt(9)]<=0){
					this.genes[r.nextInt(5)][r.nextInt(7)][r.nextInt(9)] = r.nextInt(18);
				}
			}
		}
	}
}