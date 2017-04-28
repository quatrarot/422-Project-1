// This is the Team class. A Team is Person[]. 
// This class is important for adjusting the score and adding other scoring mechanisms or factors

public class Team {
	private Person[] team;
	private int Teamscore;
	private int hourScore;
	private int teamLength = -1;
	
	public Team(Person[] teamInput)
	{
		team = teamInput;
		length();
		Teamscore = teamScorer();
	}
	
	/// Team scorer is where you'll do most configuration if you add or want to change what groups look like. 
	
	// Current configuration is based on availability each hour over the week
	// the below blocked out section within teamScorer() shows how another factor can be included in team Score (individual Skills)
	public int teamScorer()
	  {
	    int score = 0;
	    int points = 1;
	    
	    //Adding score for overlapping hours
	    
	    for (int d = 0;d < 7 ; d++)
	    {
		    for(int t = 0 ; t < 24 ; t++)
		    {
		      points = 1;
		      for(int i = 0 ; i < teamLength ; i++)
		      {
		        if(team[i].isFree(d,t) == false)
		        {
		          points = 0;
		        }
		      }
		      score += points;
		    }
	    }

	    //////////////////////////////////////////////////////////////////////////////////////
	    // Adding the score from skills
	    
	    // create upper tier for skills
	    int numMaxSkills = 0;
	    for(int w = 0 ; w<teamLength;w++)
	    	numMaxSkills += team[w].getSkills().length;
	    String[] skills = new String[numMaxSkills];
	    int skillPoints = 0;
	    for(int z = 0 ; z<teamLength ;z++)
	    {
	    	String[] skillsList = team[z].getSkills();
	    	for(int sk = 0 ; sk<skillsList.length ; sk++)
	    	{
	    		String skill = skillsList[sk];
	    		boolean newSkill = true;
	    		for(int sm = 0 ; sm<skillPoints; sm++)
	    		{
	    			if(skill.equals(skills[sm]))
	    			{
	    				newSkill = false;
	    				
	    			}
	    		}
	    		if (newSkill == true)
	    		{
	    			skills[skillPoints] = skill;
	    			skillPoints += 1;
	    		}
	    	}
	    }
	    ///// Add skills score to score
	    score += skillPoints;
	    //////////////////////////////////////////////////////////////////////////////////////////////
	    
	    return score;
	  }
	
	// This is managed so a Group can generically be initiated while the class handles different sizing options. All Teams current Max Size is PeoplePer
	// This shouldn't need to be touched
	
	public int length()
	{
		if(teamLength == -1)
		{
			int holdLength = 0;
			for(int i = 0 ; i<team.length;i++)
			{
				if(team[i] == null)
					break;
				holdLength +=1;
			}
			teamLength = holdLength;
		}
		
		return teamLength;
	}
	
	public Person getPerson(int index)
	{
		return team[index];
	}
	
	public void setPerson(int index, Person person)
	{
		team[index] = person;
		Teamscore = teamScorer();
	}
	
	//// Change this to alter the formatting of team. Currently prints out each member's name and availability. 
	/// for testing if you want to print out other facts about teams include that here
	
	public void printTeam()
	{
		for(int i = 0 ; i<teamLength ; i++)
		{
			team[i].printPerson();
		}
		System.out.println("Team score is: " + Teamscore);
	}
	
	public int getScore()
	{
		return Teamscore;
	}
	

}
