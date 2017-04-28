//Person Class holds everything about the input people. Holds all data that is used for scoring, comparisons, and optimization

public class Person {
  private int[][] availability; // initial raw availability, list of all days with a list of ints with available time. No standard size 
  private String name;
  private int[][] schedule = new int[7][24]; // Schedule is used to create consistency. Hours are denoted by the index and all values are a 0 or 1 (1 = free)
  String[] skills; // a string of their selected "skills", in this case programming languages
  
  
  //Include any other details about the people within constructor. I.e favorite place to work, hair. In this case this String goodAt as an example appended
  public Person(int[][] time, String id, String[] goodAt)
  {
	for(int i = 0 ; i<7;i++)
		for (int n = 0 ; n<24 ; n++)
			schedule[i][n] = 0;
    availability = new int[7][];
    availability = time;
    for(int w = 0 ; w < 7 ; w++)
    {
    	int[] tempInt = time[w];
    	availability[w] = tempInt;
    }
    name = id;
    skills = goodAt;
    timeChecker();
  }
  
  //An important instance of time data. Checks to see if Person is free at a given day/hour.
  public boolean isFree(int day, int hour)
  {
    if(schedule[day][hour] == 1)
      return true;
    else
      return false; 
  }
  
  //fills in 1's in schedule using data from availability. This shouldn't need to be touched as long as availability is formatted properly.
  private void timeChecker()
  {
    for(int i = 0 ; i < 7 ; i++)
    {
    	int [] hours = availability[i];
    	for(int z = 0 ; z<hours.length ; z++ )
    		schedule[i][availability[i][z]-1] = 1;
    }
  }
  
  public String getName()
  {
    return name; 
  }
  
  public String[] getSkills()
  {
	  return skills;
  }
  
  public void printPerson()
  {
    System.out.print("Name: " + name + " - Hours Free: ");
    for(int i = 0; i<7; i++)
    {
    	System.out.print("day - " + (i+1) + " > ");
    	for (int n = 0 ; n < 24 ; n++)
    	{
    		if(isFree(i,n))
    			System.out.print((n+1) + ", ");
    	}
    }
    System.out.println();
  }
  
  
}
