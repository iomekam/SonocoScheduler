package Implementation;

import interfaces.IExtruder;
import interfaces.IPress;
import interfaces.IReporter;
import interfaces.IScheduler;
import interfaces.ITimedBasedComponent;
import java.lang.Math;

public class Extruder implements IExtruder, ITimedBasedComponent {
	
	private int _id;
	private boolean _isActive;
	private IPress _press;
	private int _currentCharge;
	private final int HUMANERROR = 5;
	private int _currentPosition;
	private int _lastPosition;
	
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
				_currentPosition = _press.getPosition();
			}
		}
		
		if(_press != null) {
			_currentCharge++;
			
			if(_currentCharge == _press.getCharge() + getDistance()) {
				handOffCharge(_press);
				reset();
			}
		}
	}

	@Override
	public int getTimeRemaining() {
		return (_press.getCharge() + getDistance()) - _currentCharge;
	}

	@Override
	public boolean isActive() {
		return _isActive;
	}
	
	private void reset() {
		if (_press != null) {
			_lastPosition = _press.getPosition();
		}
		_press = null;
		_currentCharge = -HUMANERROR;
		_isActive = false;
	}
	
	private int getDistance() {
		return Math.abs(_currentPosition - _lastPosition);
	}
	@Override
	public int getId() {
		return _id;
	}
}
