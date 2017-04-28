import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import javafx.*;
import javax.swing.JFileChooser;


public class SortGroups
{
  public static void main(String[] args) throws FileNotFoundException
  {
	  // Create a file Selector
	  FileSelector file = new FileSelector();
	  file.setVisible(true);
	  
	  // run file selector (grabs input data file and parses it)
	  file.FileSelectorStart();
	  
	  /// Grab all the people from the input file
	  Person[] people = file.getPeople();
	  
	  // create a new instance of an OptimalGroup
	  OptimalGroup bestGrouper = new OptimalGroup(people, file.getNumPeople(), file.getGroupSize());
	  
	  // saves best Group as a Group class 
	  Group bestGroup = bestGrouper.getBestGroup();
	  
	  displayResults returnDisplay = new displayResults(bestGroup, file.getNumPeople(), file.getGroupSize());
	  
	  
	  //Uncomment the below code to enable tests to run. 
	  /* 
	  TestTeamLength();
	  TestTeamScore();
	  TestPersonFreeTime();
	  TestInsertToTeam();
	  TestGroupScore();
	  TestOptimumGroup();
	  */
  }
  
  
  
  ///// SMALL TESTS FOR COMMON MISTAKES AND SOURCES OF ERROR IN THE ALGORITHM
  
  static void TestOptimumGroup()
  {
	  boolean failed = false;
	  Person[] people = CreateTestPeople();
	  OptimalGroup grouping = new OptimalGroup(people, 4, 2);
	  for(int i = 0 ; i<20 ; i++)
	  {
		  Group optimum = grouping.getBestGroup();
		  String name0 = optimum.getTeam(0).getPerson(0).getName();
		  String name1 = optimum.getTeam(0).getPerson(1).getName();
		  String name2 = optimum.getTeam(1).getPerson(0).getName();
		  String name3 = optimum.getTeam(1).getPerson(1).getName();
		  
		  if (name0 == "rocking rollo riptide" | name0 == "baby hersh")
		  {
			  if (name1 != "rocking rollo riptide" & name1 != "baby hersh")
			  {
				  failed = true;
				  System.out.println("Failed Optimum Test! rocking rollo riptide and baby hersh should always be in a team together");
				  break;
			  }  
		  }
		  else if (name0 == "lil 2" | name0 == "stretch Diamond")
		  {
			  if (name1 != "lil 2" & name1 != "stretch Diamond")
			  {
				  failed = true;
				  System.out.println("Failed Optimum Test! rocking rollo riptide and baby hersh should always be in a team together");
				  break;
				  
			  }
		  }

	  }
	  if(failed == false)
		  System.out.println("Passed the Basic Optimizating Group Test");
  }
  
  static void TestGroupScore()
  {
	  // only relevant if team and group scoring hasn't been altered
	  Person[] people = CreateTestPeople();
	  Person[] teamOne = {people[0], people[1]};
	  Person[] teamTwo = {people[2],people[3]};
	  
	  Team team1 = new Team(teamOne);
	  Team team2 = new Team(teamTwo);
	  Team[] group = {team1, team2};
	  
	  Group testGroup = new Group(group, 4, 2);
	  
	  if(testGroup.getScore() != 104)
		  System.out.println("Failed Test Group Score Test. Score should have been 104, not " + testGroup.getScore());
	  else
		  System.out.println("Passed Test Group Score");
  }
  
  static void TestInsertToTeam()
  {
	  Person[] people = CreateTestPeople();
	  Person perTest = people[0];
	  
	  Person[] teamArray = {people[1],people[2],people[3]};
	  
	  Team test = new Team(teamArray);
	  
	  test.setPerson(1, perTest);
	  
	  if(test.getPerson(1).getName().equals(perTest.getName()))
		  System.out.println("Passed Test Team Insertion");
	  else
		  System.out.println("Team Member index 1's name should have been lil 2, not " + test.getPerson(1).getName());

  }
  
  static void TestTeamScore()
  {
	  /// This test only applies with default team scoring preferences/weights
	  
	  Team test1 = new Team(CreateTestPeople());
	  if(test1.getScore() != 11)
		  System.out.println("TestTeamScore Failed: test1.getScore() should equal 11, Not " + test1.getScore());
	  else
		  System.out.println("Passed Test Team Score");
  }
  
  static void TestPersonFreeTime()
  {
	  boolean failed = false;
	  Person test = CreateTestPeople()[0];
	  int[][] avail = {{12,2},{2,3,4},{13,14,4},{17,9,3,2},{7},{8,6,3},{5,2}};
	  for(int i = 0 ; i < 7 ; i++)
	  {
		  int[] hours = avail[i];
		  for(int w = 0 ; w <hours.length; w++)
		  {
			  if(test.isFree(i, hours[w]-1) != true)
			  {
				  System.out.println("Test Free Time Implementation Failed! Hour" + hours[w] + " on day " + i +" should have been free.");
				  failed = true;
			  }
		  }
	  }
	  
	  
	  if (failed == false)
		  System.out.println("Passed Test Time Implementation");
  }
  
  static void TestTeamLength()
  {
	  boolean fail = false;
	  Person[] people = CreateTestPeople();
	  Team test1 = new Team(people);
	  
	  if(test1.length() != 4)
	  {
		  System.out.println("TestTeamLength Part 1 Failed: test1.length() should equal 4, Not " + test1.length());
		  fail = true;
	  }
	  
	  Person pers;
	  Person[] tempPeeps = new Person[4];
	  tempPeeps[0] = people[0];
	  tempPeeps[1] = people[1];
	  tempPeeps[2] = people[2];
	  
	  Team test2 = new Team(tempPeeps);
	  
	  if(test2.length() != 3)
	  {
		  System.out.println("TestTeamLength Part 2 Failed: test2.length() should equal 3, Not " + test1.length());
		  fail = true;
	  }
	  
	  if(fail == false)
		  System.out.println("Passed Test Team Length");
	    
  }
  
  static Person[] CreateTestPeople()
  {
	  Person[] testPeople = new Person[4];
	  
	  int[][] availability1 = {{12,2},{2,3,4},{13,14,4},{17,9,3,2},{7},{8,6,3},{5,2}};
	  int[][] availability2 = {{2},{3,1},{13,4,6,7,11},{17,2},{7,2,6,5},{13,3,6},{5,2}};
	  int[][] availability3 = {{22},{21,22},{13,22},{1,5,3,2},{22,24},{7,13,6},{1}};
	  int[][] availability4 = {{22},{21,3,22},{13,14,22},{6,4,3,2},{7,11,15},{6,3},{1,2}};
	  int[][][] timeArray = {availability1, availability2, availability3, availability4};
	  
	  String[] names = {"lil 2", "stretch Diamond", "rocking rollo riptide", "baby hersh"};

	  
	  String[] skills = {"j", "x", "y", "l", "m", "n","o","p"};

	  for(int i = 0 ; i < 4 ; i++)
	  {
		  testPeople[i] = new Person(timeArray[i], names[i], skills);
	  }
	  
	  return testPeople;
	  
	  
  }
}