package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Implementation.Extruder;
import Implementation.InstanceFactory;
import Implementation.Press;
import interfaces.IExtruder;
import interfaces.IPress;
import interfaces.IReporter;
import interfaces.IScheduler;
import interfaces.ITimedBasedComponent;

public class ExtruderTests {

	@Test
	public void testNonActiveExtruderStartsCreatingCharge() {
		InstanceFactory.get().InitializeScheduler(new IScheduler() {

			@Override
			public IPress getNextPress() {
				return new Press(1, 0, 0, 0, 0);
			}

			@Override
			public boolean isLimitReachable(int timeInDay) {
				// TODO Auto-generated method stub
				return false;
			}
			
		});
		
		Extruder extruder = new Extruder(1);
		assertFalse(extruder.isActive());
		
		extruder.process();
		assertTrue(extruder.isActive());
	}
	
	@Test
	public void testExtruderSuccessfullyCreatesCharge() {
		final Press press = new Press(1, 0, 20, 0, 0);
		
		InstanceFactory.get().InitializeScheduler(new IScheduler() {

			@Override
			public IPress getNextPress() {
				return press;
			}

			@Override
			public boolean isLimitReachable(int timeInDay) {
				// TODO Auto-generated method stub
				return false;
			}
			
		});
		
		InstanceFactory.get().InitializeReporter(new IReporter() {

			@Override
			public void chargeCompleted(IExtruder extruder, IPress press) {
				
			}

			@Override
			public void limitStatistics() {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		Extruder extruder = new Extruder(1);
		extruder.process();
		
		final int timeRemaining = extruder.getTimeRemaining();
		
		for(int count = 0; count < timeRemaining; count++) {
			assertTrue(extruder.isActive());
			extruder.process();
		}
		
		assertFalse(extruder.isActive());
	}

}
