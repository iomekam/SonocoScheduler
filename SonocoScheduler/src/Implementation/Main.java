package Implementation;

import interfaces.IComponentTimer;
import mocks.MockScheduler;

public class Main {

	public static void main(String[] args) {
		InstanceFactory factory = InstanceFactory.get();
		
		factory.InitializePress(new Press(1));
		factory.InitializePress(new Press(2));
		factory.InitializePress(new Press(3));
		factory.InitializePress(new Press(4));
		factory.InitializePress(new Press(5));
		factory.InitializePress(new Press(6));
		
		factory.InitializeExtuder(new Extruder(1));
		factory.InitializeExtuder(new Extruder(2));
		
		factory.InitializeScheduler(new MockScheduler());
		factory.InitializeReporter(new Reporter());
		
		IComponentTimer timer = new ComponentTimer(45);
		timer.start();
	}
}
