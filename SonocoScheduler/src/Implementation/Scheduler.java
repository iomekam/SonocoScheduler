package Implementation;

import java.util.ArrayList;
import java.util.List;

import Implementation.InstanceFactory;
import interfaces.IPress;
import interfaces.IScheduler;
import interfaces.ITimedBasedComponent;

import java.lang.Math;

public class Scheduler implements IScheduler {
	private int _totalRemainingMolds;
	@Override
	public IPress getNextPress() {
		List<IPress> availablePresses = new ArrayList<IPress>();
		for(IPress press : InstanceFactory.get().GetAllPresses()) {
			if(press instanceof ITimedBasedComponent) {
				if(((ITimedBasedComponent) press).isActive() == false) {
					availablePresses.add(press);
				}
			}
		}
		
		_totalRemainingMolds = this.moldsRemaining(InstanceFactory.get().GetAllPresses());
		return nextPress(availablePresses);
	}

	private IPress nextPress(List<IPress> availablePresses) {
		int index = -1;
		List<IPress> pressesNotAtLimit = new ArrayList<IPress>();
		//Search through each press to see if the limit has been reached
		for (IPress press : availablePresses) {
			if(press.LimitReached() == false) {
				pressesNotAtLimit.add(press);
			}
		}
		//Find next press that still needs to get to limit
		if(pressesNotAtLimit.isEmpty() == false) {
			for(IPress press : pressesNotAtLimit) {
				if (index < 0) {
					index = 0;
				}
				else if (weightedScore(press)+ getDistance(press,pressesNotAtLimit.get(index)) < weightedScore(pressesNotAtLimit.get(index))){
					index = pressesNotAtLimit.indexOf(press);
				}
			}
			return pressesNotAtLimit.get(index);
		}// Find next press if all availble have reached limit
		else if (availablePresses.isEmpty() == false){
			for(IPress press : availablePresses) {
				if (index < 0 ) {
					index = 0;
				}
				else if (weightedScore(press) + getDistance(press,availablePresses.get(index)) < weightedScore(availablePresses.get(index))){
					index = availablePresses.indexOf(press);
				}
			}
			return availablePresses.get(index);
		}
		return null;
	}

	private int weightedScore(IPress press) {
		if (press.getRemaningMolds() > 0) {
			return press.getScore() * (1 - (press.getRemaningMolds()/ _totalRemainingMolds));
		}
		else {
			return press.getScore();
		}
	}

	private int moldsRemaining(List<IPress> availablePresses) {
		int moldsRemaining = 0;
		for(IPress press : availablePresses) {
			moldsRemaining += press.getRemaningMolds();
		}
		return moldsRemaining;
	}

	@Override
	public boolean isLimitReachable(int totalTimeInDay) {
		int minTimeNeeded = 0;
		List<IPress> allPresses= InstanceFactory.get().GetAllPresses();
		for (IPress press : allPresses) {
			minTimeNeeded += (press.getScore() * press.getLimit());
		}
		
		return minTimeNeeded < totalTimeInDay;
	}
	
	private int getDistance(IPress p1, IPress p2) {
		return Math.abs(p1.getPosition() - p2.getPosition());
	}
}
