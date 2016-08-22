package mocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Implementation.InstanceFactory;
import interfaces.IPress;
import interfaces.IScheduler;
import interfaces.ITimedBasedComponent;

public class MockScheduler implements IScheduler {

	@Override
	public IPress getNextPress() {
		List<IPress> availablePresses = new ArrayList<IPress>();
		Random rand = new Random(1);
		
		for(IPress press : InstanceFactory.get().GetAllPresses()) {
			if(press instanceof ITimedBasedComponent) {
				if(((ITimedBasedComponent) press).isActive() == false) {
					availablePresses.add(press);
				}
			}
		}
		
		if(availablePresses.isEmpty() == false) {
			int index = rand.nextInt(availablePresses.size());
			return availablePresses.get(index);
		}
		
		return null;
	}

}
