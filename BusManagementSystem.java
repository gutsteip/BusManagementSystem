import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.*;
import java.util.*;

public class BusManagementSystem 
{

	public static void main(String[] args) 
	{
		//Get the total number of stops and trips in the system
		int numStops = 0;
		int numTrips = 0;
		try 
			{numStops = (int)(Files.lines(Paths.get("stops.txt")).count()-1);}
		catch(Exception e)
			{e.printStackTrace();}
		
		try 
			{numTrips = (int)(Files.lines(Paths.get("stop_times.txt")).count()-1);}
		catch(Exception e)
			{e.printStackTrace();}
		
		//Load all stops into BusStop Array
		BusStop[] stops = getStops(numStops);
		
		//Load all trips into Trip Array
		Trip[] trips = getTrips(numTrips);
						
		//Add edges from transfers to stops for part 1
		//addTransferEdges(stops);
		
		//Construct a TST for stop names for part 2
		TernarySearchTree<String> stopNames = new TernarySearchTree<String>();
		for(int i = 0; i < numStops; i++)
			stopNames.put(stops[i].getStopName(), stops[i].getStopName());
				
		mainUserLoop(numStops, stops, stopNames, trips);
	}
	
	//Main input loop for the program
	public static void mainUserLoop(int numStops, BusStop[] stops, TernarySearchTree<String> stopNames, Trip[] trips)
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
			}
			else if(userInputString.equals("3"))
			{
				searchTrip(userInput, trips);
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
				if(!printedResult)
					System.out.println("\nstop_id,stop_code,stop_name,stop_desc,stop_lat,stop_lon,zone_id,location_type");
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
	
	//Search for trip with specific arrival time
	public static void searchTrip(Scanner userInput, Trip[] trips)
	{
		String timeInput = "";
		Boolean printedResult = false;
		ArrayList<Trip> validTrips = new ArrayList<Trip>();
		
		while(timeInput.equals(""))
		{
			System.out.print("Please search for the arrival time you'd like information on.\n>");
			
			//Get entire line of input instead of first word
			if(userInput.hasNext())
			{
				timeInput = userInput.next();
				timeInput += userInput.nextLine();
			}
			
			if(timeInput.equals(""))
				System.out.println("\nINVALID INPUT: Please try again.");
		}
		
		//Go through trips array and construct arrayList out of trips that match arrival time
		for(Trip t : trips)
		{
			if(t.getArrivalTime().equals(timeInput))
				validTrips.add(t);
		}
		
		//Print out arrayList in order by trip ID
		while(!validTrips.isEmpty())
		{
			int indexOfSmallestID = 0;
			
			//Go through array and find smallest 
			for(int i = 0; i < validTrips.size(); i++)
			{
				if(validTrips.get(indexOfSmallestID).getTripID() > validTrips.get(i).getTripID())
					indexOfSmallestID = i;
			}
			
			//Print out smallest tripID and then remove it from the arraylist
			System.out.println(validTrips.get(indexOfSmallestID));
			validTrips.remove(indexOfSmallestID);
			printedResult = true;
		}
		
		//Print out a line for spacing reasons
		System.out.println();
		//If search returned nothing
		if(!printedResult)
			System.out.println("No arrival times matched your search.\n");
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
	
	//Generate array of trips
	public static Trip[] getTrips(int numTrips)
	{
		//Initialize BusStop array
		Trip[] trips = new Trip[numTrips];
		try 
		{
			//Open file reader and clear first line (i.e. header)
			BufferedReader read = new BufferedReader(new FileReader("stop_times.txt"));
			read.readLine();
			
			//Create new BusStop objects from file reader input
			for(int i = 0; i < numTrips; i++)
				trips[i] = new Trip(read.readLine());
		
			read.close();
		}
		catch(Exception e)
			{e.printStackTrace();}
		
		//Return completed BusStop array
		return trips;
	}
	
	//Makes sure arrival time is valid for a trip
	public static boolean isValidArrTime(Trip trip)
	{
		if(trip.getArrHour() > 23 || trip.getArrHour() < 0)
			return false;
		if(trip.getArrMin() > 59 || trip.getArrMin() < 0)
			return false;
		if(trip.getArrSec() > 59 || trip.getArrSec() < 0)
			return false;
		return true;
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
	
	//Add edges to stops via transfers.txt
	public static void addTransferEdges(BusStop[] stops)
	{
		try 
		{
			BufferedReader read = new BufferedReader(new FileReader("transfers.txt"));
			
			//Prepare for loop below
			String inputString;
			read.readLine();
			
			//Go until file is empty
			while((inputString = read.readLine()) != null)
			{
				String[] parts = inputString.trim().split(",");
				
				//Split parts of input line up and assign their values
				int startStopID = Integer.valueOf(parts[0]);
				int endStopID = Integer.valueOf(parts[1]);
				int transferType = Integer.valueOf(parts[2]);
				
				//Get stops from their IDs
				BusStop startStop = stops[getStopIndex(startStopID, stops)];
				BusStop endStop = stops[getStopIndex(endStopID, stops)];
				
				//Assign proper weight to edge
				if(transferType == 0)
					startStop.addEdge(new Edge(startStop, endStop, 2));
				else
				{
					double transferTime = Integer.valueOf(parts[3]);
					startStop.addEdge(new Edge(startStop, endStop, transferTime));
				}
			}
			
			read.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

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
