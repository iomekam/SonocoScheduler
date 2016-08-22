package Implementation;

import interfaces.IPress;
import interfaces.ITimedBasedComponent;

public class Press implements IPress, ITimedBasedComponent {
	
	private int _id;
	
	public Press(int id) {
		_id = id;
	}
	
	@Override
	public void process() {
		//System.out.println(String.format("Press %d is processesing", _id));
	}

	@Override
	public int getTimeRemaining() {
		return 0;
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getScore() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getDistance() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getLimit() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalMoldsCreated() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCharge() {
		return 10;
	}

	@Override
	public int getId() {
		return _id;
	}

	@Override
	public void receiveCharge() {
		// TODO Auto-generated method stub
		
	}

}
