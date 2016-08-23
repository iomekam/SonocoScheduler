package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.List;

import Implementation.ComponentTimer;
import Implementation.Extruder;
import Implementation.InstanceFactory;
import Implementation.Press;
import Implementation.Reporter;
import Implementation.Scheduler;
import interfaces.IComponentTimer;
import interfaces.IExtruder;
import interfaces.IPress;
import interfaces.IReporter;
import interfaces.IScheduler;
import interfaces.ITimedBasedComponent;

public class AlgorithmTests {

	@Test
	public void testNoPressSkipped() {
		InstanceFactory factory = setup();
		IPress testPress = new Press(1, 900, 24, 100, 2);
		
		factory.InitializePress(new Press(2, 90, 30, 10, 1));
		factory.InitializePress(testPress);
		factory.InitializePress(new Press(3, 90, 33, 10, 3));
		factory.InitializePress(new Press(4, 90, 25, 10, 4));
		factory.InitializePress(new Press(5, 90, 20, 10, 5));
		factory.InitializePress(new Press(6, 90, 30, 10, 6));
		
		IComponentTimer timer = new ComponentTimer(2000);
		factory.InitializeComponentTimer(timer);
		
		System.out.println("testNoPressSkipped");
		timer.start();
		
		assertTrue(testPress.getTotalMoldsCreated() != 0);	
		
	}
	
	@Test
	public void testAllPressesReachLimit() {
		InstanceFactory factory = setup();
		
		
		factory.InitializePress(new Press(1, 90, 30, 100, 1));
		factory.InitializePress(new Press(2, 240, 30, 100, 1));
		factory.InitializePress(new Press(3, 150, 33, 100, 3));
		factory.InitializePress(new Press(4, 110, 25, 100, 4));
		factory.InitializePress(new Press(5, 70, 20, 100, 5));
		factory.InitializePress(new Press(6, 180, 30, 100, 6));
		
		int totalProcessTime = (90 + 150 + 240 + 70 + 110 + 180)* 100; //Maximum process time needed to make molds
		ComponentTimer timer = new ComponentTimer(totalProcessTime);
		factory.InitializeComponentTimer(timer);
		timer.start();
		
		List<IPress> presses = factory.GetAllPresses();
		int partsRemaining = 0;
		
		for (IPress press : presses) {
			partsRemaining += press.getRemaningMolds();
		}
		
		assertFalse((partsRemaining != 0) );
		
	}
	
	private InstanceFactory setup() {
		InstanceFactory factory = InstanceFactory.get();
		List<ITimedBasedComponent> timeList = factory.GetAllTimedBasedComponents();
		
		if(!timeList.isEmpty()) {
			factory.reset();
			factory = InstanceFactory.get();
		}
		
		factory.InitializeExtuder(new Extruder(1));
		factory.InitializeExtuder(new Extruder(2));
		
		factory.InitializeScheduler(new Scheduler());
		factory.InitializeReporter(new Reporter() {

			@Override
			public void chargeCompleted(IExtruder extruder, IPress press) {
				
			}
			
		});
		
		return factory;
	}
}
