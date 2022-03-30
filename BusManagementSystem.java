import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class BusManagementSystem 
{

	public static void main(String[] args) 
	{
		System.out.println("Test");
		
		System.out.println("Test2");
		try 
		{
			BufferedReader read = new BufferedReader(new FileReader("stops.txt"));
			System.out.println(read.readLine());
			read.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
