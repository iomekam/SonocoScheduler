package interfaces;

public interface IPress {
	int getScore();
	int getDistance();
	int getLimit();
	int getCharge();
	
	int getId();
	
	void receiveCharge();
	
	int getTotalMoldsCreated();
}
