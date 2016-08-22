package mocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Implementation.InstanceFactory;
import interfaces.IPress;
import interfaces.IScheduler;
import interfaces.ITimedBasedComponent;

public class MockScheduler implements IScheduler {
	private int _totalRemainingMolds;
	@Override
	public IPress getNextPress() {
		List<IPress> availablePresses = new ArrayList<IPress>();
		//Random rand = new Random(1);
		for(IPress press : InstanceFactory.get().GetAllPresses()) {
			if(press instanceof ITimedBasedComponent) {
				if(((ITimedBasedComponent) press).isActive() == false) {
					availablePresses.add(press);
				}
			}
		}
		
		_totalRemainingMolds = this.moldsRemaining(availablePresses);
		int index = -1;
		for(IPress press : availablePresses) {
			if (index < 0 && !availablePresses.isEmpty()) {
				index = 0;
			}
			else if (weightedScore(press) < weightedScore(availablePresses.get(index))){
				index = availablePresses.indexOf(press);
			}
		}
		
		if(availablePresses.isEmpty() == false) {
			//int index = rand.nextInt(availablePresses.size());
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
	public boolean isLimitReachable(int timeInDay) {
		// TODO Auto-generated method stub
		return false;
	}
}
