import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class Genetic {
//ArrayList that contains the current population of chromosomes
	private ArrayList<Chromosome> population;
	private InsertInput input;
	private int [][][]childGenes=new int[5][7][9];
    /*
     * ArrayList that contains indexes of the chromosomes in the population ArrayList
     * Each chromosome index exists in the ArrayList as many times as its fitness score
     * By creating this ArrayList so, and choosing a random index from it,
     * the greater the fitness score of a chromosome the greater chance it will be chosen.
    */
	private ArrayList<Integer> fitnessBounds;
	
	public Genetic(){
		this.population = null;
		this.fitnessBounds = null;
		input=new InsertInput();
		input.readDataTeachers("teachers.txt");
		input.readDataLessons("lessons.txt");
	}
	
	public Chromosome geneticAlgorithm(int populationSize, double mutationProbability, int minimumFitness, int maximumSteps){
        //We initialize the population
		initializePopulation(populationSize);
		Random r = new Random();
		for(int step=0; step < maximumSteps; step++){
            //Initialize the new generated population
			ArrayList<Chromosome> newPopulation = new ArrayList<Chromosome>();
			for(int i=0; i < populationSize; i++)
			{
                //We choose two chromosomes from the population
                //Due to how fitnessBounds ArrayList is generated, the propability of
                //selecting a specific chromosome depends on its fitness score
				int xIndex = this.fitnessBounds.get(r.nextInt(this.fitnessBounds.size()));
				Chromosome x = this.population.get(xIndex);
				int yIndex = this.fitnessBounds.get(r.nextInt(this.fitnessBounds.size()));
				while(yIndex == xIndex)
				{
					yIndex = this.fitnessBounds.get(r.nextInt(this.fitnessBounds.size()));
				}
				Chromosome y = this.population.get(yIndex);
                //We generate the "child" of the two chromosomes
				Chromosome child = this.reproduce(x, y);
                //We might then mutate the child
				if(r.nextDouble() < mutationProbability)
				{
					child.mutate();
				}
                //...and finally add it to the new population
				newPopulation.add(child);
			}
			this.population = new ArrayList<Chromosome>(newPopulation);

            //We sort the population so the one with the greater fitness is first
			 Collections.sort(this.population, new Comparator<Chromosome>() 
             {
                 public int compare(Chromosome f1, Chromosome f2) 
                 {
                     return f2.getFitness() - f1.getFitness();
                 }
             });
            //If the chromosome with the best fitness is acceptable we return it
			if(this.population.get(0).getFitness() >= minimumFitness)
			{
                System.out.println("Finished after " + step + " steps...");
                this.Print_Sched();
				return this.population.get(0);
			}
            //We update the fitnessBounds arrayList
			this.updateFitnessBounds();
		}

        System.out.println("Finished after " + maximumSteps + " steps...");
        this.Print_Sched();
		return this.population.get(0);
	}
	
	
	public void initializePopulation(int populationSize)	//arxikopoihsh plithismou
	{
		this.population = new ArrayList<Chromosome>();
		for(int i=0; i<populationSize; i++)
		{
			this.population.add(new Chromosome(input));
		}
		this.updateFitnessBounds();
	}
	
	public void updateFitnessBounds(){
		this.fitnessBounds = new ArrayList<Integer>();
		for (int i=0; i<this.population.size(); i++){
			this.population.get(i).calculateFitness(input);
			for(int j=0; j<this.population.get(i).getFitness(); j++){
				
                //Each chromosome index exists in the ArrayList as many times as its fitness score
                //By creating this ArrayList so, and choosing a random index from it,
                //the greater the fitness score of a chromosome the greater chance it will be chosen.
				fitnessBounds.add(i);
			}
		}
	}
	
	//"Reproduces" two chromosomes and generated their "child"
		public Chromosome reproduce(Chromosome x, Chromosome y){
			Random r = new Random();
	        //Randomly choose the intersection point
			
			int intersectionPoint = r.nextInt(4) + 1;
	        //The child has the left side of the x chromosome up to the intersection point...
			for(int i=0; i<intersectionPoint; i++){
				for(int j=0; j<childGenes[0].length; j++){
					for(int k=0; k<childGenes[0][0].length; k++){
						childGenes[i][j][k] = x.getGenes()[i][j][k];
					}
				}					
			}
	        //...and the right side of the y chromosome after the intersection point
			for(int i=intersectionPoint; i<childGenes.length; i++){
				for(int j=0; j<childGenes[0].length; j++){
					for(int k=0; k<childGenes[0][0].length; k++){
						childGenes[i][j][k] = y.getGenes()[i][j][k];
					}
				}	
			}
			return new Chromosome(input,childGenes);
		}
		
		public void Print_Sched(){
			
			ArrayList <Teacher> teachers=new ArrayList<Teacher>(input.getTeacher());
	    	ArrayList <Lesson> lessons=new ArrayList<Lesson>(input.getLesson());
	    	String lesson[][][]=new String[5][7][9];
	    	String teacher[][][]=new String[5][7][9];
	    	System.out.println("					WROLOGIO PROGRAMMA MATHIMATWN GUMNASIOU 			 \n" );
			
	    	for(int class_n=0; class_n<9; class_n++){
	    		if(class_n==0){
	    			System.out.println("TMHMA A1\n");
	    		}else if(class_n==1){
	    			System.out.println("TMHMA A2\n");
	    		}else if(class_n==2){
	    			System.out.println("TMHMA A3\n");
	    		}else if(class_n==3){
	    			System.out.println("TMHMA B1\n");
	    		}else if(class_n==4){
	    			System.out.println("TMHMA B2\n");
	    		}else if(class_n==5){
	    			System.out.println("TMHMA B3\n");
	    		}else if(class_n==6){
	    			System.out.println("TMHMA C1\n");
	    		}else if(class_n==7){
	    			System.out.println("TMHMA C2\n");
	    		}else if(class_n==8){
	    			System.out.println("TMHMA C3\n");
	    		}
	    		
	    		for(int day=0; day<5; day++){
	    			if(day==0){
		    			System.out.println("DEYTERA\n");
		    		}else if(day==1){
		    			System.out.println("TRITH\n");
		    		}else if(day==2){
		    			System.out.println("TETARTH\n");
		    		}else if(day==3){
		    			System.out.println("PEMPTI\n");
		    		}else if(day==4){
		    			System.out.println("PARASKEYH\n");
		    		}
	    			
	    			
	    			for(int j=0; j<7; j++){//j=wres
	    				for(int l=0; l<lessons.size(); l++){
	    					for(int t=0; t<teachers.size(); t++){
	    						if(j==0){		//an einai i prwti wra
	    							if(childGenes[day][j][class_n]==teachers.get(t).get_Teacher_Id()){
	    								if(teachers.get(t).get_lesson_id()==lessons.get(l).get_Lesson_Id()){
	    									if(lessons.get(l).get_Class_N().equals("A")){		//einai sti taksi A
	    										if(class_n<3){
	    											lesson[day][j][class_n]=lessons.get(l).get_Lesson_Name();
			                    					teacher[day][j][class_n]=teachers.get(t).get_Teacher_Name();
			                    					break;
	    										}
	    									}else if(lessons.get(l).get_Class_N().equals("B")){
	    										if(class_n>=3 && class_n<6){
	    											lesson[day][j][class_n]=lessons.get(l).get_Lesson_Name();
			                    					teacher[day][j][class_n]=teachers.get(t).get_Teacher_Name();
			                    					break;
	    										}
	    									}else if(lessons.get(l).get_Class_N().equals("C")){
	    										if(class_n>=6 && class_n<9){
	    											lesson[day][j][class_n]=lessons.get(l).get_Lesson_Name();
			                    					teacher[day][j][class_n]=teachers.get(t).get_Teacher_Name();
			                    					break;
	    										}
	    									}
	    								}
	    							}
	    						}else if(j==1){
	    							if(childGenes[day][j][class_n]==teachers.get(t).get_Teacher_Id()){
	    								if(teachers.get(t).get_lesson_id()==lessons.get(l).get_Lesson_Id()){
	    									if(lessons.get(l).get_Class_N().equals("A")){		//einai sti taksi A
	    										if(class_n<3){
	    											lesson[day][j][class_n]=lessons.get(l).get_Lesson_Name();
			                    					teacher[day][j][class_n]=teachers.get(t).get_Teacher_Name();
			                    					break;
	    										}
	    									}else if(lessons.get(l).get_Class_N().equals("B")){
	    										if(class_n>=3 && class_n<6){
	    											lesson[day][j][class_n]=lessons.get(l).get_Lesson_Name();
			                    					teacher[day][j][class_n]=teachers.get(t).get_Teacher_Name();
			                    					break;
	    										}
	    									}else if(lessons.get(l).get_Class_N().equals("C")){
	    										if(class_n>=6 && class_n<9){
	    											lesson[day][j][class_n]=lessons.get(l).get_Lesson_Name();
			                    					teacher[day][j][class_n]=teachers.get(t).get_Teacher_Name();
			                    					break;
	    										}
	    									}
	    								}
	    							}
	    						}else if(j==2){
	    							if(childGenes[day][j][class_n]==teachers.get(t).get_Teacher_Id()){
	    								if(teachers.get(t).get_lesson_id()==lessons.get(l).get_Lesson_Id()){
	    									if(lessons.get(l).get_Class_N().equals("A")){		//einai sti taksi A
	    										if(class_n<3){
	    											lesson[day][j][class_n]=lessons.get(l).get_Lesson_Name();
			                    					teacher[day][j][class_n]=teachers.get(t).get_Teacher_Name();
			                    					break;
	    										}
	    									}else if(lessons.get(l).get_Class_N().equals("B")){
	    										if(class_n>=3 && class_n<6){
	    											lesson[day][j][class_n]=lessons.get(l).get_Lesson_Name();
			                    					teacher[day][j][class_n]=teachers.get(t).get_Teacher_Name();
			                    					break;
	    										}
	    									}else if(lessons.get(l).get_Class_N().equals("C")){
	    										if(class_n>=6 && class_n<9){
	    											lesson[day][j][class_n]=lessons.get(l).get_Lesson_Name();
			                    					teacher[day][j][class_n]=teachers.get(t).get_Teacher_Name();
			                    					break;
	    										}
	    									}
	    								}
	    							}
	    						}else if(j==3){
	    							if(childGenes[day][j][class_n]==teachers.get(t).get_Teacher_Id()){
	    								if(teachers.get(t).get_lesson_id()==lessons.get(l).get_Lesson_Id()){
	    									if(lessons.get(l).get_Class_N().equals("A")){		//einai sti taksi A
	    										if(class_n<3){
	    											lesson[day][j][class_n]=lessons.get(l).get_Lesson_Name();
			                    					teacher[day][j][class_n]=teachers.get(t).get_Teacher_Name();
			                    					break;
	    										}
	    									}else if(lessons.get(l).get_Class_N().equals("B")){
	    										if(class_n>=3 && class_n<6){
	    											lesson[day][j][class_n]=lessons.get(l).get_Lesson_Name();
			                    					teacher[day][j][class_n]=teachers.get(t).get_Teacher_Name();
			                    					break;
	    										}
	    									}else if(lessons.get(l).get_Class_N().equals("C")){
	    										if(class_n>=6 && class_n<9){
	    											lesson[day][j][class_n]=lessons.get(l).get_Lesson_Name();
			                    					teacher[day][j][class_n]=teachers.get(t).get_Teacher_Name();
			                    					break;
	    										}
	    									}
	    								}
	    							}
	    						}else if(j==4){
	    							if(childGenes[day][j][class_n]==teachers.get(t).get_Teacher_Id()){
	    								if(teachers.get(t).get_lesson_id()==lessons.get(l).get_Lesson_Id()){
	    									if(lessons.get(l).get_Class_N().equals("A")){		//einai sti taksi A
	    										if(class_n<3){
	    											lesson[day][j][class_n]=lessons.get(l).get_Lesson_Name();
			                    					teacher[day][j][class_n]=teachers.get(t).get_Teacher_Name();
			                    					break;
	    										}
	    									}else if(lessons.get(l).get_Class_N().equals("B")){
	    										if(class_n>=3 && class_n<6){
	    											lesson[day][j][class_n]=lessons.get(l).get_Lesson_Name();
			                    					teacher[day][j][class_n]=teachers.get(t).get_Teacher_Name();
			                    					break;
	    										}
	    									}else if(lessons.get(l).get_Class_N().equals("C")){
	    										if(class_n>=6 && class_n<9){
	    											lesson[day][j][class_n]=lessons.get(l).get_Lesson_Name();
			                    					teacher[day][j][class_n]=teachers.get(t).get_Teacher_Name();
			                    					break;
	    										}
	    									}
	    								}
	    							}
	    						}else if(j==5){
	    							if(childGenes[day][j][class_n]==teachers.get(t).get_Teacher_Id()){
	    								if(teachers.get(t).get_lesson_id()==lessons.get(l).get_Lesson_Id()){
	    									if(lessons.get(l).get_Class_N().equals("A")){		//einai sti taksi A
	    										if(class_n<3){
	    											lesson[day][j][class_n]=lessons.get(l).get_Lesson_Name();
			                    					teacher[day][j][class_n]=teachers.get(t).get_Teacher_Name();
			                    					break;
	    										}
	    									}else if(lessons.get(l).get_Class_N().equals("B")){
	    										if(class_n>=3 && class_n<6){
	    											lesson[day][j][class_n]=lessons.get(l).get_Lesson_Name();
			                    					teacher[day][j][class_n]=teachers.get(t).get_Teacher_Name();
			                    					break;
	    										}
	    									}else if(lessons.get(l).get_Class_N().equals("C")){
	    										if(class_n>=6 && class_n<9){
	    											lesson[day][j][class_n]=lessons.get(l).get_Lesson_Name();
			                    					teacher[day][j][class_n]=teachers.get(t).get_Teacher_Name();
			                    					break;
	    										}
	    									}
	    								}
	    							}
	    						}else if(j==6){
	    							if(childGenes[day][j][class_n]==teachers.get(t).get_Teacher_Id()){
	    								if(teachers.get(t).get_lesson_id()==lessons.get(l).get_Lesson_Id()){
	    									if(lessons.get(l).get_Class_N().equals("A")){		//einai sti taksi A
	    										if(class_n<3){
	    											lesson[day][j][class_n]=lessons.get(l).get_Lesson_Name();
			                    					teacher[day][j][class_n]=teachers.get(t).get_Teacher_Name();
			                    					break;
	    										}
	    									}else if(lessons.get(l).get_Class_N().equals("B")){
	    										if(class_n>=3 && class_n<6){
	    											lesson[day][j][class_n]=lessons.get(l).get_Lesson_Name();
			                    					teacher[day][j][class_n]=teachers.get(t).get_Teacher_Name();
			                    					break;
	    										}
	    									}else if(lessons.get(l).get_Class_N().equals("C")){
	    										if(class_n>=6 && class_n<9){
	    											lesson[day][j][class_n]=lessons.get(l).get_Lesson_Name();
			                    					teacher[day][j][class_n]=teachers.get(t).get_Teacher_Name();
			                    					break;
	    										}
	    									}
	    								}
	    							}
	    						}
	    					}
	    				}
	    				System.out.println((j+1)+"h wra "+lesson[day][j][class_n]+" "+teacher[day][j][class_n]);
	    				System.out.println(" ");
	    			}
	    		}
	    	}
		}
}
