package interfaces;

public interface IPress {
	int getScore();
	int getDistance();
	int getLimit();
	int getCharge();
	
	int getId();
	
	void creatingCharge();
	void receiveCharge();
	
	int getTotalMoldsCreated();
}
