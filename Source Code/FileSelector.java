import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.JPanel;

import java.util.Random;
import java.util.Scanner;

import javax.swing.*;

public class FileSelector extends JFrame{
	
	static public Person[] people;
	static private File CSVfile = null;
	static private int numPeople;
	static private int groupSize;
	static private boolean done = false;
	public FileSelector()
	{
		super("Select File");

	}
	
	public void FileSelectorStart() throws FileNotFoundException 
	{
		//Person[] people = new Person[15];
		setSize(350,350);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		//Container tain = getContentPane();
		//tain.setLayout(new FlowLayout());
		
		JPanel numPeopleArea = new JPanel();
		JPanel groupArea = new JPanel();
		JPanel buttonArea = new JPanel();
		
		
		JButton openFile = new JButton("Select CSV File");
		JTextField numPeopleBox = new JTextField("Enter Total # of People");
		JTextField groupSizeBox = new JTextField("Enter # People per Group");

		
		buttonArea.add(openFile);
		numPeopleArea.add(numPeopleBox);
		groupArea.add(groupSizeBox);
		
		final JLabel display = new JLabel("This is where groups will go");
		
		openFile.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				boolean numSet = false;
				boolean groupSet = false;
				try 
				{
					numPeople = Integer.parseInt(numPeopleBox.getText());
					numSet = true;
					
				}
				catch (NumberFormatException e)
				{
					System.out.println(e);
				}
				
				try
				{
					groupSize = Integer.parseInt(groupSizeBox.getText());
					groupSet = true;
				}
				catch (NumberFormatException e)
				{
					System.out.println(e);
				}
				if(numSet == true & groupSet == true)
				{
					JFileChooser fileSelection = new JFileChooser();
					int option = fileSelection.showOpenDialog(FileSelector.this);
									
					if (option == JFileChooser.APPROVE_OPTION)
					{
						File csvFile = fileSelection.getSelectedFile();
						display.setText("You chose" + csvFile);
						CSVfile = csvFile;
					}
				}
				
			}
			
		});
		this.getContentPane().add(buttonArea, BorderLayout.SOUTH);
		this.getContentPane().add(groupArea, BorderLayout.CENTER);
		this.getContentPane().add(numPeopleArea, BorderLayout.NORTH);
		this.revalidate();
		//tain.add(numPeopleBox);

		while(CSVfile == null)
		{
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		people = new Person[numPeople];
		parseFile(CSVfile);
		this.setVisible(false);
	}
	
	public int getNumPeople()
	{
		return numPeople;
	}
	
	public int getGroupSize()
	{
		return groupSize;
	}
	
	public static boolean isDone()
	{
		return done;
	}
	
	public static Person[] getPeople()
	{
		return people;
	}
	
	public static Person[] parseFile(File file) throws FileNotFoundException
	{
		Scanner s = new Scanner(file);
		s.nextLine();
		int p = 0;
		int counter = 0;
		while(s.hasNextLine() & counter < numPeople)
		{
			counter += 1;
			String personLine = s.nextLine();
			Scanner personScan = new Scanner(personLine);
			
			//CHANGE DOUBLE QUOTE TO SINGLE QUOTE
			personScan.useDelimiter(",");
			personScan.next(); //Throw out time stamp junk
			
			//Collect info to make Person
			String name = personScan.next(); // name
			
			
			int[][] availability = new int[7][];
			int[] tempHours; 
			int size;
			for (int i = 0 ; i < 3 ; i++)
			{
				tempHours = new int[24];
				size = 0;
				String hours = personScan.next();
				Scanner hoursScan = new Scanner(hours);
				hoursScan.useDelimiter(";");
				while(hoursScan.hasNext())
				{
					String times = hoursScan.next();
					switch(times) {
						case "8 - 10 am":	size += 2;
											tempHours[size-2] = 8;
											tempHours[size-1] = 9;
											break;
						case "10 - 12 pm":	size += 2;
											tempHours[size-2] = 10;
											tempHours[size-1] = 11;
											break;
						case "12 - 2 pm":	size += 2;
											tempHours[size-2] = 12;
											tempHours[size-1] = 13;
											break;
						case "2 - 4 pm":	size += 2;
											tempHours[size-2] = 14;
											tempHours[size-1] = 15;
											break;
						case "5 - 6 pm":	size += 2;
											tempHours[size-2] = 16;
											tempHours[size-1] = 17;
											break;
						case "6 - 8 pm":	size += 2;
											tempHours[size-2] = 18;
											tempHours[size-1] = 19;
											break;
						case "8 - 10 pm":	size += 2;
											tempHours[size-2] = 20;
											tempHours[size-1] = 21;
											break;
					}
				}
				if(i == 0)
				{
					availability[0] = new int[size];
					availability[2] = new int[size];
					availability[4] = new int[size];
					for (int v = 0 ; v < size ; v++)
					{
						availability[0][v] = availability[2][v] = availability[4][v] = tempHours[v];
					}
				}
				
				if(i == 1)
				{
					availability[1] = new int[size];
					availability[3] = new int[size];
					for (int v = 0 ; v < size ; v++)
					{
						availability[1][v] = availability[3][v] = tempHours[v];
					}
				}
				
				if(i == 2)
				{
					availability[5] = new int[size];
					availability[6] = new int[size];
					for (int v = 0 ; v < size ; v++)
					{
						availability[5][v] = availability[6][v] = tempHours[v];
					}
				}
				
					
			}
			/*
			String[] skills = {"j", "x", "y", "l", "m", "n","o","p"};
			Random rand = new Random();
			String[] personSkills = new String[2];
			for(int two = 0 ; two < 2 ; two ++)
				personSkills[two] = skills[rand.nextInt(skills.length)];*/
			String[] personSkills = new String[0];
			Person person = new Person(availability, name, personSkills);
			people[p] = person;
			p++;
			
		}
		s.close();
		done = true;
		return people;
	}

}
