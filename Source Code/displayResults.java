import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.JPanel;
import javax.swing.*;

public class displayResults extends JFrame{
	
	static private Group myGroup;
	static private int numPeople;
	static private int groupSize;
	
	public displayResults(Group group, int numPeople, int peoplePer)
	{
		super("display results");
		//JFrame frame = new JFrame();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		myGroup = group;
		this.numPeople = numPeople;
		this.groupSize = peoplePer;
		
		int numTeams = numTeams(numPeople, peoplePer);
		String[][] displayGroups = new String[numTeams][peoplePer + 1];
		
		
		
		for (int i = 0 ; i < numTeams ; i++)
		{
			displayGroups[i][0] = "Team " + Integer.toString(i+1);
			for(int t = 0 ; t < group.getTeam(i).length() ; t++)
				displayGroups[i][t+1] = group.getTeam(i).getPerson(t).getName();
		}
		
		String[] columnNames = new String[peoplePer+1];
		
		for(int v = 0 ; v<peoplePer + 1 ; v++)
		{
			if(v == 0)
				columnNames[v] = "Teams";
			else
				columnNames[v] = "Mem " + Integer.toString(v);
		}
		
		JPanel buttonArea = new JPanel();
		JPanel tableArea = new JPanel();
		
		JButton saveFile = new JButton("Select Location to Save CSV File");
		JTable table = new JTable(displayGroups, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		
		buttonArea.add(saveFile);
		tableArea.add(scrollPane);
		
		
		
		
		this.getContentPane().add(scrollPane, BorderLayout.CENTER);
		this.getContentPane().add(buttonArea, BorderLayout.SOUTH);
		this.setSize(350, 350);
		this.setVisible(true);
		
		
		
		saveFile.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				JFileChooser folderSave = new JFileChooser();
				int option = folderSave.showSaveDialog(displayResults.this);
				//folderSave.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				
				if (option == JFileChooser.APPROVE_OPTION)
				{
					File folder = folderSave.getSelectedFile();
					saveFile(folder);
				}
			}
		});
		
		this.getContentPane().add(buttonArea, BorderLayout.SOUTH);

		
		
		
		
	}
	
	public void saveFile(File folder)
	{

		FileWriter fileWrite;
		
		String filePath = folder.getAbsolutePath() + ".txt";
		
		File file = new File(filePath);
		
		String separator = System.getProperty("line.separator");

		try {
			fileWrite = new FileWriter(file, false);
			for(int i = 0 ; i < numTeams(numPeople, groupSize); i++)
			{
				Team tempTeam = myGroup.getTeam(i);
				fileWrite.write(("Team" + Integer.toString(i+1) + separator));
				for(int d = 0 ; d < tempTeam.length(); d++)
				{
					fileWrite.write(tempTeam.getPerson(d).getName() + separator);
				}
				fileWrite.write(separator);
			}
			fileWrite.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public int numTeams(int numPeople, int peoplePer)
	  {
		  if(numPeople%peoplePer != 0)
			  return (numPeople/peoplePer) + 1;
		  else
			  return numPeople/peoplePer;
	  }
		

}
