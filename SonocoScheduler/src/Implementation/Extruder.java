package Implementation;

import interfaces.IExtruder;
import interfaces.IPress;
import interfaces.ITimedBasedComponent;

public class Extruder implements IExtruder, ITimedBasedComponent {
	
	private int _id;
	
	public Extruder(int id) {
		_id = id;
	}

	@Override
	public IPress requestNextPress() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void handOffCharge(IPress press) {
		// TODO Auto-generated method stub

	}

	@Override
	public void process() {
		System.out.println(String.format("Extruder %d is processesing", _id));
	}

	@Override
	public void getTimeRemaining() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return false;
	}

}
