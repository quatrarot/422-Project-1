import java.util.*; 

public class OptimalGroup 
{
	Person[] people;
	int numPeople;
	int peoplePer;
	
  public OptimalGroup(Person[] peopleL, int numPeopleL, int peoplePerGroupL)
  {
	people = peopleL;
	numPeople = numPeopleL;
	peoplePer = peoplePerGroupL;
  }
  
  
  //call this to begin the process 
  
  public Group getBestGroup()
  {
	  return groupOptimum(people, numPeople, peoplePer);
  }
  
  ///// Main Call Function. Here we can optimize running times and test frequencies in order to find best group.
  
  public Group groupOptimum(Person[] people , int numPeople, int peoplePer)
  {
	
    int classRoomsize = 5;  // Number of initial random Groups to make. # of Starting points, and maintained Groups
    int search = 25;  // Number of alternate groups created from a single Classroom Group. To be compared for Higher Result.
    int loopTimes = 500;  // Number of times to search (i.e create search mutations and select the highest scoring)
    Random rand = new Random();
    
    Group[] topClass = new Group[classRoomsize];  // Stores maintained Groups that mutate and compare against. The Best
    
    
    // Creates ClassRoomsize number of Groups 
    for(int i = 0 ; i < classRoomsize ; i++)
    {
      topClass[i] = groupCreator(people, numPeople, peoplePer);
    }
    
    
    Group[] tempVariations = new Group[search]; //holds our search mutations
    
    // loop number of times to create and edit topclass
    for(int q = 0 ; q<loopTimes ; q++)
    {
      int groupToVary = rand.nextInt(classRoomsize);
      Group group = topClass[groupToVary];
      for (int n = 0 ; n < search ; n++)
      {
    	    	
        tempVariations[n] = groupSwap(group, numPeople, peoplePer);
      }
      
      // Takes our classRoom Groups and search mutation groups and compares them. Selects classRoomSize best options and saves in classRoom, runs again
      
      topClass = groupSort(topClass, tempVariations, classRoomsize, search, numPeople, peoplePer);
    }
    
    ////// topClass[0] = highest scoring overall program
   
    return topClass[0];   
    
  }
  
  ////// Main sorting algorithm for selecting highest scoring Group from range of options. Should not be touched.
  
  public static Group[] groupSort(Group[] real, Group[] tempVariations,int classRoomsize, int search, int numPeople, int peoplePer)
  { 
    int[][] ranking = new int[classRoomsize + search][3];
    
    for (int i = 0 ; i < classRoomsize ; i++)
    {
      ranking[i][0] = i;
      ranking[i][1] = 0;
      ranking[i][2] = real[i].getScore(); 
    }
    
    for (int j = 0 ; j < search ; j++)
    {
      ranking[classRoomsize + j][0] = j;
      ranking[classRoomsize + j][1] = 1;
      ranking[classRoomsize + j][2] = tempVariations[j].getScore();
    }
    
    //actually sorting ranking
    for (int z = 0 ; z < classRoomsize ; z++)
    {
      int[] largest = ranking[z];
      for(int y = z ; y < (search + classRoomsize) ; y++)
      {
        if(ranking[y][2] > largest[2])
        {
          ranking[z] = ranking[y];
          ranking[y] = largest;
          largest = ranking[z];
        }
      }
    }
    
    //Take top (classRoomsize) and those values to return 
    
    Group[] topScorers = new Group[classRoomsize];
    
    for(int a = 0 ; a < classRoomsize ; a++)
    {
      if(ranking[a][1] == 0)
    	  
        topScorers[a] = real[ranking[a][0]];
      else
        topScorers[a] = tempVariations[ranking[a][0]];
    }
    
    return topScorers; 
    
  }
  
  /// Returns the number of teams. 
  
  public int numTeams()
  {
	  if(numPeople%peoplePer != 0)
		  return (numPeople/peoplePer) + 1;
	  else
		  return numPeople/peoplePer;
  }
  
  
///////// Simple function that swaps two random Person's in a Group
  
  
  public Group groupSwap(Group group, int numPeople, int peoplePer)
  {
	  
	/// Creates a local clone of the group to mutate
	Group holdGr;
	Team[] teamHolder = new Team[numTeams()];
	
	for(int i = 0 ; i< numTeams(); i++)
	{
		int teamSize = group.getTeam(i).length();
		Person[] personHolder = new Person[teamSize];
		for (int s = 0 ; s<teamSize ; s++)
		{
			Person tempPer = group.getTeam(i).getPerson(s);
			personHolder[s] = tempPer;
		}
		Team tempTeam = new Team(personHolder);
		teamHolder[i] = tempTeam;
	}
	holdGr = new Group(teamHolder, numPeople, peoplePer);
	///////////
	
	////////////////////////////// Selects two random ints, which are mapped to a corresponding person and then swapped
	
	//// Random
	
    Random rand = new Random();
    int randomPerson1 = rand.nextInt(numPeople);
    int randomPerson2 = rand.nextInt(numPeople);
    
    //// Swapping
    Person temp = holdGr.getTeam(randomPerson1/peoplePer).getPerson(randomPerson1%peoplePer);
    holdGr.getTeam(randomPerson1/peoplePer).setPerson(randomPerson1%peoplePer,holdGr.getTeam(randomPerson2/peoplePer).getPerson(randomPerson2%peoplePer));
    holdGr.getTeam(randomPerson2/peoplePer).setPerson(randomPerson2%peoplePer, temp); 
    
    
    return holdGr;
  }
  
  
  
///////////////////// Generates a group of x teams with y people each from all the People passed into optimalGroups
//////////////////// This is called from the initial optimizer function. It uses this to create random initial Groups and sorts them from there.
  //////////////////////
  ////////////////////// This code should only be touched if you are trying to change the default configuration of a Group.
  /////////////////////        For example: If you'd like to add flexibility to individual team sizes this code would need to be changed.
  
  public Group groupCreator(Person[] allParticipants, int numPeople, int peoplePer)
  {
	/// Creates local clone of all People
	  
    Person[] people = new Person[allParticipants.length];
    for(int w = 0 ; w<numPeople ; w++)
    {
      people[w] = allParticipants[w];
    }
    
    
    
    int n = 0;
    int selection;
    Random rand = new Random();
    Person[] team = new Person[peoplePer];
    
    Team[] groupMedium = new Team[numTeams()];
    
    ///// Loops through all people. Manages two pointers. One to beginning of people list and one at the end (numPeople - 1 -i)
    
    for(int i = 0 ; i < numPeople ; i++)
    {
      
      selection = rand.nextInt(numPeople-i);
      team[n] = people[selection];
      people[selection] = people[numPeople-1-i];
      n++;
      
      ////Takes team built above and stores it in Team[]
      
      
      if(n == peoplePer)
      {
        n = 0;
        Team newTeam = new Team(team);
        groupMedium[i/peoplePer] = newTeam;
        team = new Person[peoplePer];
      }
      
    }
    
    //// Checks for remainder - which implies extra group (numPeople/peoplePer + 1 = totalNumTeams)
    
    if(numPeople%peoplePer != 0)
    {
    	Team newTeam = new Team(team);
        groupMedium[numTeams()-1] = newTeam;
    }
    
    
    Group group = new Group(groupMedium,numPeople,peoplePer);
    

    return group;
  }
   
}
