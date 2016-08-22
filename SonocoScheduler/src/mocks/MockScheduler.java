package mocks;

import Implementation.InstanceFactory;
import interfaces.IPress;
import interfaces.IScheduler;

public class MockScheduler implements IScheduler {

	@Override
	public IPress getNextPress() {
		return InstanceFactory.get().GetAllPresses().get(0);
	}

}
