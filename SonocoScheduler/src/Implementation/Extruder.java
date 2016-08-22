package Implementation;

import interfaces.IExtruder;
import interfaces.IPress;
import interfaces.IScheduler;
import interfaces.ITimedBasedComponent;

public class Extruder implements IExtruder, ITimedBasedComponent {
	
	private int _id;
	private boolean _isActive;
	private IPress _press;
	private int _currentCharge;
	private final int HUMANERROR = 5;
	
	public Extruder(int id) {
		_id = id;
		_isActive = false;
		_press = null;
		_currentCharge = -HUMANERROR;
	}

	@Override
	public void handOffCharge(IPress press) {
		System.out.println(
			String.format("Extruder %d has finished creating the charge for Press %d", _id, _press.getId())
		);
		
		_press.receiveCharge();
	}

	@Override
	public void process() {
		if(!_isActive) {
			IScheduler scheduler = InstanceFactory.get().getSceduler();
			_press = scheduler.getNextPress();
			_isActive = true;
		}
		
		_currentCharge++;
		
		if(_currentCharge == _press.getCharge() + _press.getDistance()) {
			handOffCharge(_press);
			reset();
		}
	}

	@Override
	public int getTimeRemaining() {
		return (_press.getCharge() + _press.getDistance()) - _currentCharge;
	}

	@Override
	public boolean isActive() {
		return _isActive;
	}
	
	private void reset() {
		_press = null;
		_currentCharge = -HUMANERROR;
		_isActive = false;
	}
}
