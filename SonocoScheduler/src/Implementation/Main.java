package Implementation;

import interfaces.IComponentTimer;

public class Main {

	static final int  _timelimit = 33300; // 9.25 hours
	public static void main(String[] args) {
		InstanceFactory factory = InstanceFactory.get();
		
		factory.InitializePress(new Press(1, 9, 3, 2,1));
		factory.InitializePress(new Press(2, 90, 6, 50,2));
		factory.InitializePress(new Press(3, 9, 2, 2,3));
		factory.InitializePress(new Press(4, 9, 3, 2,4));
		factory.InitializePress(new Press(5, 9, 2, 2,5));
		factory.InitializePress(new Press(6, 9, 3, 2,6));
		
		factory.InitializeExtuder(new Extruder(1));
		factory.InitializeExtuder(new Extruder(2));
		
		factory.InitializeScheduler(new Scheduler());
		factory.InitializeReporter(new Reporter());
		
		IComponentTimer timer = new ComponentTimer(_timelimit);
		factory.InitializeComponentTimer(timer);
		
		timer.start();
	}
}
