package interfaces;

public interface IReporter {
	void chargeCompleted(IExtruder extruder, IPress press);
	
	void limitStatistics();
}
