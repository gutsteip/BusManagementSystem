import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.*;
import java.util.Arrays;

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
		
		//Make sure stops is made correctly
		System.out.println(numStops);
		System.out.println(stops[0].getStopName());
		System.out.println(stops[61].getStopName());
		System.out.println(stops[numStops-1].getStopName());
	}
	
	
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
