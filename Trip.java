public class Trip 
{
	private int tripID, stopID, stopSeq;
	private int arrivalHour, arrivalMin, arrivalSec;
	private int depHour, depMin, depSec;
	private String arrivalTime, depTime;
	
	public Trip(String input)
	{
		String[] parts = input.trim().split(",");
		tripID = Integer.valueOf(parts[0]);
		stopID = Integer.valueOf(parts[3]);
		stopSeq = Integer.valueOf(parts[4]);
		
		String[] arrivalParts = parts[1].substring(1).split(":");
		arrivalHour = Integer.valueOf(arrivalParts[0]);
		arrivalMin = Integer.valueOf(arrivalParts[1]);
		arrivalSec = Integer.valueOf(arrivalParts[2]);
		arrivalTime = parts[1].substring(1);
		
		String[] depParts = parts[2].substring(1).split(":");
		depHour = Integer.valueOf(depParts[0]);
		depMin = Integer.valueOf(depParts[1]);
		depSec = Integer.valueOf(depParts[2]);
		depTime = parts[2].substring(1);
	}
	
	
	public int getTripID()
	{
		return tripID;
	}
	public int getStopID()
	{
		return stopID;
	}
	public int getStopSeq()
	{
		return stopSeq;
	}

	public int getArrHour()
	{
		return arrivalHour;
	}
	public int getArrMin()
	{
		return arrivalMin;
	}
	public int getArrSec()
	{
		return arrivalSec;
	}
	public String getArrivalTime()
	{
		return arrivalTime;
	}
	
	public int getDepHour()
	{
		return depHour;
	}
	public int getDepMin()
	{
		return depMin;
	}
	public int getDepSec()
	{
		return depSec;
	}
	public String getDepartureTime()
	{
		return depTime;
	}

	public String toString()
	{
		return tripID + ", " + arrivalTime + ", " + depTime + ", " + stopID + ", " + stopSeq;
	}
}
