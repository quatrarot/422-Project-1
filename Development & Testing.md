The code is designed to be built on. Below is a basic mapping of how it works.

1. [SortGroups.java](https://github.com/quatrarot/422-Project-1/blob/master/Source%20Code/SortGroups.java)

  SortGroups contains the main function and initializes everything. It starts by creating a new FileSelector object.
  
  The [FileSelector] class creates the initial input gui and is responsible for collecting initializing variables for constructing optimum Teams.
  
  These variables include the totalNumberofPeople, idealPeoplePerGroup, and all the individual People.
  
  It grabs these by first using JFileChooser to navigate to the CSV file. It passes these to the function parseFile.
  
  If you make any changes to the google doc or want to change how it parses the file you'll want to edit the parseFile function.
  
2. Classes and creating a Group.

   Note: All classes have detailed commenting in the source file describing the flow, function operations, and examples to extend its function.
  
  The program uses 3 classes to organize the people. 
  
    a) the class [Person] stores individual people. It includes their name and availability. 
          Currently the main objective of Person is turning hoursAvailable from the CSV into a comparable and extendable object.
        
          If you implement any other characteristics/options for people you will want to include them in the Person initializer. 
    
    b) Next is the class [Team]. This class is an array of Person.
           The main purpose of this class is storing individual teams (that are equal to idealGroupSize or idealGroupSize - 1)
           
           Key importance of this Class for future development is scoring. All team scoring is done within here. If you'd like to add other paramaters that contribute to score
           (i.e extra characterstics you added to Person) you'll want to add that here. Weights are easily implemented at this point.
           
           *** Within this Class is an area commented as //Adding the score from skills. Although skills is empty it shows how the characteristic can be used to change the score and how to include it in the calculations.
           
     c) The Class [Group] is an array of Teams. Its total size = totalNumPeople. 
            The main purpose of Group is to return the end result. The main optimization functions takes a Person[] and finds the optimal Group to return.
            
            Key importance of this Class for future development is also scoring. Scoring functions in Group create an overall single score for the current Grouping configuration.
            *** Currently the overall score is the sum of each teams total overlapping hours with a weight placed on the Team with the lowest score.

3.  [OptimalGroup](https://github.com/quatrarot/422-Project-1/blob/master/Source%20Code/OptimalGroup.java) is the main meat of the processes for this project.
    
    Once selectFile returns the initialization parameters SortGroups calls OptimalGroup.
    
    OptimalGroup: 
                  1. Creates V random groups from the  people available. It stores these potential Groups in a class array (Group[])
    
                  2. A random Group is picked from the class and two elements are randomly swapped. The new Group is stored in a temp array.
                     The Project generatex x mutated Groups from the random Groups. These are then compared with the intial V random groups and the top V are selected based on Group sccore.
                 
                  3. OptimalGroup repeats the above two steps z times, constantly saving the best groups. 
                  
                  4. Finally, OptimalGroup selects the highest ranking Group generated and returns that to SortGroups.
                  
     
     The main functions of concern in OptimalGroup for future development are groupSwap() and OptimalGroup().
     
                  If you want to change how Groups are mutated you'll need to change groupSwap() 
                  To change current running paramaters (V,x,z) and optimize sorting of Groups you'll need to work in OptimalGroup()
  
  4. [displayResults](https://github.com/quatrarot/422-Project-1/blob/master/Source%20Code/displayResults.java) works much like fileSelector.
                  
                  displayResults takes the group returned from OptimalGroup and prints the teams in a JTable.
                  It then uses a JFileChooseer to navigate to a save location. Finally, the function saveFile records the Optimal Group into the chosen .txt.
                  
                  IMPORTANT: displayResults does not automatically close. That is dependant on the user
                  
 TESTING
      
      There are a few basic tests in SortGroup beneath all the intialization. There are 7 functions commented out. If the comments are removed a few basic tests will run to confirm: 
                                              Team Length preservation, 
                                              Group & Team Score Preservation (only applies if keeping current scoring and weights),
                                              OptimumGroup Generation,
                                              Team Member Insertion,
                                             
      [Student Profile.csv](https://github.com/quatrarot/422-Project-1/blob/master/Test%20Data/Student%20Profile.csv) is an example CSV generated for testing purposes. It contains 15 people with varrying availability
                                              For simplicty StudentProfile assumes all M/W/F, T/Th, Sa/Sun schedules are the same
                                              
      Each class includes a Person/Team/Group printer that prints out detailed information on Groups/teams, scores, and availability.
      
