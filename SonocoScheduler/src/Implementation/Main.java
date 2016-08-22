package Implementation;

import interfaces.IComponentTimer;
import mocks.MockScheduler;

public class Main {

	public static void main(String[] args) {
		InstanceFactory factory = InstanceFactory.get();
		
		factory.InitializePress(new Press(1, 90, 30, 200,1));
		factory.InitializePress(new Press(2, 240, 60, 215,2));
		factory.InitializePress(new Press(3, 150, 22, 200,3));
		factory.InitializePress(new Press(4, 90, 34, 215,4));
		factory.InitializePress(new Press(5, 130, 24, 200,5));
		factory.InitializePress(new Press(6, 90, 34, 215,6));
		
		factory.InitializeExtuder(new Extruder(1));
		factory.InitializeExtuder(new Extruder(2));
		
		factory.InitializeScheduler(new MockScheduler());
		factory.InitializeReporter(new Reporter());
		
		IComponentTimer timer = new ComponentTimer(33300); //9.25 hours
		factory.InitializeComponentTimer(timer);
		
		timer.start();
	}
}
