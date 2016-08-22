package Implementation;

import interfaces.IExtruder;
import interfaces.IPress;
import interfaces.IReporter;
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
		reset();
	}

	@Override
	public void handOffCharge(IPress press) {
		IReporter reporter = InstanceFactory.get().getReporter();
		reporter.chargeCompleted(this, press);
		
		_press.receiveCharge();
	}

	@Override
	public void process() {
		if(!_isActive) {
			IScheduler scheduler = InstanceFactory.get().getSceduler();
			_press = scheduler.getNextPress();
			
			if(_press != null) {
				_isActive = true;
				_press.creatingCharge();
			}
		}
		
		if(_press != null) {
			_currentCharge++;
			
			if(_currentCharge == _press.getCharge() + _press.getDistance()) {
				handOffCharge(_press);
				reset();
			}
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

	@Override
	public int getId() {
		return _id;
	}
}
