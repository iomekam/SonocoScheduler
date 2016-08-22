package interfaces;

public interface IPress {
	int getScore();
	int getPosition();
	int getLimit();
	int getCharge();
	
	int getId();
	
	void creatingCharge();
	void receiveCharge();
	
	int getTotalMoldsCreated();
	int getRemaningMolds();
	
	boolean LimitReached();
}
