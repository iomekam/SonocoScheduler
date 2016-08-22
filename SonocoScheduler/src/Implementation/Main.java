package Implementation;

import interfaces.IComponentTimer;

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
		
		IComponentTimer timer = new ComponentTimer();
		timer.start();
	}
}
