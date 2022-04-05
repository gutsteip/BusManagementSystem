public class Edge 
{
	private double weight;
	private BusStop startStop, endStop; 
	
	public Edge(BusStop stopA, BusStop stopB, double w)
	{
		startStop = stopA;
		endStop = stopB;
		weight = w;
	}
	
	public BusStop getStartStop()
	{
		return startStop;
	}
	public BusStop getEndStop()
	{
		return endStop;
	}
	public double getWeight()
	{
		return weight;
	}
}
