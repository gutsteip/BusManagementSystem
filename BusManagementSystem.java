import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.*;
import java.util.Arrays;

public class BusManagementSystem 
{

	public static void main(String[] args) 
	{
		int numStops = 0;
		
		try 
			{numStops = (int)(Files.lines(Paths.get("stops.txt")).count()-1);}
		catch(Exception e)
			{e.printStackTrace();}
		
	}
	
	/*
	public static void readStops()
	{
		try 
		{
			BufferedReader read = new BufferedReader(new FileReader("stops.txt"));

			read.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	*//*
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
