///// The class Group is an array of Teams. Which effectively makes it a Person[][]
///// The key value in this class is score and groupScorer. Here you can change weights and add other methodologies to the macro scoring process.

import java.util.*; 

public class Group
{
	private Team[] group;
	private int score = -1;
	private int numPeople; //number of total People making up this group
	private int peoplePer; // ideal number of people in each team
	
	public Group(Team[] subGroup, int numPeopler, int peoplesPer)
	{
		group = subGroup;
		numPeople = numPeopler;
		peoplePer = peoplesPer;		
	}
	
	////////Assigns an overall score to group 
	
	////////// Currently Score = a 5x weight lowest team score + all other teams score's
	
	public int groupScorer(Team[] group, int numPeople, int peoplePer)
	  {
		
		
	    int score = 0;
	    int smallestScore = -1;
	    for(int i = 0 ; i < numTeams() ; i++)
	    {
	      int tempScore = group[i].getScore();
	      
	      /// Checks for smallest score
	      if(smallestScore == -1)
	        smallestScore = tempScore;
	      if(tempScore < smallestScore)
	        smallestScore = tempScore;
	      
	      //// Updates score
	      
	      score += tempScore;
	    }
	    
	    /////////////SMALLESTSCORE WEIGHT
	    
	    score = (score - smallestScore) + smallestScore*5;
	    return score;
	  }
	
	public int numTeams()
	{
		if(numPeople%peoplePer != 0)
			return (numPeople/peoplePer + 1);
		else 
			return numPeople/peoplePer;
	}
	
	public int getScore()
	{
		if(score == -1)
		{
			score = groupScorer(group, numPeople, peoplePer);
		}
		return score;
	}
	
	public Team getTeam(int index)
	{
		return group[index];
	}
	
	public void printGroup()
	{
		groupPrinter(group, numPeople, peoplePer);
	}
	
	///// Change this if you'd like to change the formatting for groups
	
	private void groupPrinter(Team[] group,int numPeople,int peoplePer)
	  {
		/// This part prints teams and any data or text pre/proceeding each team print. Use this for testing
		
		for(int j = 0 ; j < group.length ; j++)
		{
			System.out.println("Team " + (j+1));
			group[j].printTeam();
			System.out.println();
		}
		if(score == -1)
			score = groupScorer(group, numPeople, peoplePer);
		System.out.println("Group score: " + score);
	  }
}