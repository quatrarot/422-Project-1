# 422-Project-1
This is the first assignment for CIS 422 at the University of Oregon, a simple lightweight user/developer friendly program that creates optimal grouping from a list of People. 

This project was produced by Tyler Quatraro, a Senior at the University of Oregon.  The idea of the project was to use minimalism
to create a well working and intuitive grouping program. Currently this is only the MVP (Minimum Viable Product). Special emphasis was placed
on user end simplicity and future development/implementation. All code is done in java on JKE 1.8 or higher.

[Installation & Running Instructions](#installing-and-running)

[Development & Testing](https://github.com/quatrarot/422-Project-1/blob/master/Development%20%26%20Testing.md)



### Installing and Running ###

1) Produce a CSV file from a google Form. (Properly formatted form can be copied from here: https://docs.google.com/forms/d/1pH1hWg1FS-BJfn3Je1iEriytKAFij_IZYbrpeUqxWIk/copy)
    
2) Download [groupMyPeople.jar](https://github.com/quatrarot/422-Project-1/blob/master/Executable%20Jar/groupMyPeople.jar)

3) Run groupMyPeople

       If on a windows machine: 
      
            Simply double click the file and it will run
            
       If on a Unix/Linux machine:
       
            Use terminal to navigate to the file where you save groupMyPeople.jar. 
            type "java -jar groupMyPeople.jar"
            
4) The program will prompt you to "Enter Total # of People" & "Enter # People per Group"

        Delete the text in these two fields and fill them with appropriate values. These Must be correct for the program to run.

5) Once you've entered people & group size press "Select CSV File"

        A windows explorer will open. Use this to navigate and select the CSV file you generated in step 1.
        
6) The program will generate the best teams based on availability and present them in a popup table

7) Press the Save button and select where and what you'd like to save results as

8) This program will save a text file with each team serperated by a team title. Refer back to teams generated by opening this file.
