public class BusStop 
{
	private String stopName, stopDesc, stopLongitude, stopLatitude, stopZoneID;
	private int stopID;
	private Integer stopCode;
	
	public BusStop(String input)
	{
		String[] parts = input.trim().split(",");
		
		stopID = Integer.valueOf(parts[0]);
		
		if(!parts[1].equals(" "))
			stopCode = Integer.valueOf(parts[1]);
		
		stopName = parts[2];
		
		if(stopName.contains("NB") || stopName.contains("SB") || stopName.contains("EB") || stopName.contains("WB"))
			stopName = stopName.substring(3) + " " + stopName.substring(0, 2);
		
		stopDesc = parts[3];
		stopLatitude = parts[4];
		stopLongitude = parts[5];
		stopZoneID = parts[6];
	}
	
	public String getStopName()
	{
		return stopName;
	}
	public String getStopLatitude()
	{
		return stopLatitude;
	}
	public String getStopLongitude()
	{
		return stopLongitude;
	}
	public int getStopID()
	{
		return stopID;
	}
	public int getStopCode()
	{
		return stopCode;
	}
	
	public String toString()
	{
		String s = stopID + ", ";
		
		if(stopCode != null)
			s += stopCode + ", ";
		
		s += stopName +", " + stopDesc +", " + stopLongitude + ", " + stopLatitude + ", " + stopZoneID;
		
		return s;
	}
}