package Implementation;

import interfaces.IPress;
import interfaces.ITimedBasedComponent;


public class Press implements IPress, ITimedBasedComponent {
	
	private enum PressState {
		IDLE, CREATINGCHARGE, RECEIVING, WORKING
	}
	
	private int _id;
	private PressState _state;
	private int _timeRemaining;
	private int _cycleTime;
	private int _totalMoldsCreated;
	private int _charge;
	private int _limit;
	private int _position;
	
	public Press(int id, int cycleTime, int charge, int limit, int position) {
		_id = id;
		_state = PressState.IDLE;
		_timeRemaining = 0;
		
		_cycleTime = cycleTime;
		_charge = charge;
		_limit = limit;
		_position = position;
		
		_totalMoldsCreated = 0;
	}
	
	@Override
	public void process() {
		if(_state == PressState.WORKING) {
			_timeRemaining--;
			
			if(_timeRemaining == 0) {
				_state = PressState.IDLE;
				_totalMoldsCreated++;
			}
		}
		else if(_state == PressState.RECEIVING) {
			_state = PressState.WORKING;
			_timeRemaining = _cycleTime;
		}
	}

	@Override
	public int getTimeRemaining() {
		return _timeRemaining;

	}

	@Override
	public boolean isActive() {
		return _state != PressState.IDLE;
	}

	@Override
	public int getScore() {
		return _cycleTime + _charge;
	}

	@Override
	public int getPosition() {
		return _position;
	}

	@Override
	public int getLimit() {
		return _limit;
	}

	@Override
	public int getTotalMoldsCreated() {
		return _totalMoldsCreated;
	}

	@Override
	public int getCharge() {
		return _charge;
	}

	@Override
	public int getId() {
		return _id;
	}

	@Override
	public void receiveCharge() {
		_state = PressState.RECEIVING;		
	}

	@Override
	public void creatingCharge() {
		_state = PressState.CREATINGCHARGE;
	}
	
	@Override
	public int getRemaningMolds() {
		if(_limit > _totalMoldsCreated) {
			return _limit - _totalMoldsCreated;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean LimitReached() {
		return this.getRemaningMolds() == 0;
	}

}
