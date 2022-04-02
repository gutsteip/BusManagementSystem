public class BusStop 
{
	private String stopName;
	private int stopID, stopCode;
	
	public BusStop(String input)
	{
		String[] parts = input.trim().split(",");
		
		stopID = Integer.valueOf(parts[0]);
		stopCode = Integer.valueOf(parts[1]);
		
		stopName = parts[2];
		
		if(stopName.contains("NB") || stopName.contains("SB") || stopName.contains("EB") || stopName.contains("WB"))
			stopName = stopName.substring(3) + " " + stopName.substring(0, 2);
		
	}
	
	public String getStopName()
	{
		return stopName;
	}
	public int getStopID()
	{
		return stopID;
	}
	public int getStopCode()
	{
		return stopCode;
	}

}
