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

	//////////////////////////////////////////////////////////////////////////////////
	/*
	 *  testAllPressesReachLimit test that each press will reach its limit in less
	 *  time than the total processing time of every mold made
	 * 
	 */
	
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
	///////////////////////////////////////////////////////////////////////////////////
	/*
	 * testSelectPressWithLimit ensures that if only one press has not reached it's limit the algorithm
	 *  will select it
	 * 
	 */
	@Test
	public void testSelectPressWithLimit() {
		int time = 21;
		InstanceFactory factory = setup();
		Press testPress = new Press(1, 9000, 20, 1, 2);
		
		factory.InitializePress(testPress);
		factory.InitializePress(new Press(2, 1, 20, 0, 1));
		factory.InitializePress(new Press(3, 1, 20, 0, 3));
		factory.InitializePress(new Press(4, 1, 20, 0, 4));
		factory.InitializePress(new Press(5, 1, 20, 0, 5));
		factory.InitializePress(new Press(6, 1, 20, 0, 6));
		
		IComponentTimer timer = new ComponentTimer(time); // time for extruder to make charge and pass it
		factory.InitializeComponentTimer(timer);
		
		timer.start();
		
		assertTrue(testPress.isActive());
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
