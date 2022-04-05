import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.*;
import java.util.Arrays;
import java.util.Scanner;

public class BusManagementSystem 
{

	public static void main(String[] args) 
	{
		//Get the total number of stops in the system
		int numStops = 0;
		try 
			{numStops = (int)(Files.lines(Paths.get("stops.txt")).count()-1);}
		catch(Exception e)
			{e.printStackTrace();}
		
		//Load all stops into BusStop Array
		BusStop[] stops = getStops(numStops);
				
		//Construct a TST for stop names for part 2
		TernarySearchTree<String> stopNames = new TernarySearchTree<String>();
		for(int i = 0; i < numStops; i++)
			stopNames.put(stops[i].getStopName(), stops[i].getStopName());
		
		mainUserLoop(numStops, stops, stopNames);
	}
	
	//Main input loop for the program
	public static void mainUserLoop(int numStops, BusStop[] stops, TernarySearchTree<String> stopNames)
	{
		//Boolean to exit main loop
		Boolean stillUsingSystem = true;
		Scanner userInput = new Scanner(System.in);
		
		//Basic user input/output loop to use functions of program
		while(stillUsingSystem)
		{
			String userInputString = "";
			
			System.out.print("Welcome to the bus management system! Please enter the number of the option you'd like:\n"
					+ "To find a path between two stops, enter 1\n"
					+ "To search for a bus stop, enter 2\n"
					+ "To search for a specific trip, enter 3\n"
					+ "To exit the system, enter 4\n>");
			
			if(userInput.hasNext())
				userInputString = userInput.next();
			
			if(userInputString.equals("1"))
			{
				System.out.println("\nPATH BETWEEN TWO STOPS\n");
			}
			else if(userInputString.equals("2"))
			{
				searchBusStop(userInput, stops, stopNames);
				/*
				String stopPrefix = "";
				Boolean printedResult = false;
				
				while(stopPrefix.equals(""))
				{
					System.out.print("Please search for the stop you'd like information on.\n>");
					if(userInput.hasNext())
						stopPrefix = userInput.next();
					
					if(stopPrefix.equals(""))
						System.out.println("\nINVALID INPUT: Please try again.");
				}
				
				//Search through stopNames to get the ones that work
				Iterable<String> allSearchStops = stopNames.keysWithPrefix(stopPrefix.toUpperCase());
				
				//Print out results of search
				for(String stopName : allSearchStops)
				{
					int stopIndex = getStopIndex(stopName, stops);
					if(stopIndex != -1)
					{
						System.out.println(stops[stopIndex]);
						printedResult = true;
					}
				}
				
				//Print out a line for spacing reasons
				System.out.println();
				//If search returned nothing
				if(!printedResult)
					System.out.println("No stops matched your search.\n");*/
			}
			else if(userInputString.equals("3"))
			{
				System.out.println("\nSPECIFIC TRIP\n");
			}
			else if(userInputString.equals("4"))
			{
				System.out.println("Thank you for using the system. Have a great day!");
				stillUsingSystem = false;
			}
			else
				System.out.println("\nINVALID INPUT: Please try again.");	
		}
		userInput.close();
	}
	
	//Search for bus stop
	public static void searchBusStop(Scanner userInput, BusStop[] stops, TernarySearchTree<String> stopNames)
	{
		String stopPrefix = "";
		Boolean printedResult = false;
		
		while(stopPrefix.equals(""))
		{
			System.out.print("Please search for the stop you'd like information on.\n>");
			
			//Get entire line of input instead of first word
			if(userInput.hasNext())
			{
				stopPrefix = userInput.next();
				stopPrefix += userInput.nextLine();
			}
			
			if(stopPrefix.equals(""))
				System.out.println("\nINVALID INPUT: Please try again.");
		}
		
		//Search through stopNames to get the ones that work
		Iterable<String> allSearchStops = stopNames.keysWithPrefix(stopPrefix.toUpperCase());
		
		//Print out results of search
		for(String stopName : allSearchStops)
		{
			int stopIndex = getStopIndex(stopName, stops);
			//If the index isn't null, print it out and say you printed it out
			if(stopIndex != -1)
			{
				System.out.println(stops[stopIndex]);
				printedResult = true;
			}
		}
		
		//Print out a line for spacing reasons
		System.out.println();
		//If search returned nothing
		if(!printedResult)
			System.out.println("No stops matched your search.\n");
	}
	
	//Generate array of bus stops
	public static BusStop[] getStops(int numStops)
	{
		//Initialize BusStop array
		BusStop[] stops = new BusStop[numStops];
		try 
		{
			//Open file reader and clear first line (i.e. header)
			BufferedReader read = new BufferedReader(new FileReader("stops.txt"));
			read.readLine();
			
			//Create new BusStop objects from file reader input
			for(int i = 0; i < numStops; i++)
				stops[i] = new BusStop(read.readLine());
		
			read.close();
		}
		catch(Exception e)
			{e.printStackTrace();}
		
		//Return completed BusStop array
		return stops;
	}
	
	//Get index of a specific stop in the stops array via it's stopID
	public static int getStopIndex(int stopID, BusStop[] stops)
	{
		for(int i = 0; i < (stops.length-1); i++)
		{
			if(stops[i].getStopID() == stopID)
				return i;
		}
		return -1;
	}
	
	//Get index of a specific stop in the stops array via it's stopName
	public static int getStopIndex(String stopName, BusStop[] stops)
	{
		for(int i = 0; i < (stops.length-1); i++)
		{
			if(stops[i].getStopName().equals(stopName))
				return i;
		}
		return -1;
	}
	/*
	public static void readTransfers()
	{
		try 
		{
			BufferedReader read = new BufferedReader(new FileReader("transfers.txt"));
			read.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	*/
	/*
	public static void readStopTimes()
	{
		try 
		{
			BufferedReader read = new BufferedReader(new FileReader("stop_times.txt"));
			read.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	*/

}
