package Implementation;

import java.util.List;

import interfaces.IComponentTimer;
import interfaces.ITimedBasedComponent;

public class ComponentTimer implements IComponentTimer {
	private int _time;
	private int _seconds;
	
	public ComponentTimer(int timeInSeconds) {
		_time = timeInSeconds;
		_seconds = 0;
	}
	
	public void start() {
		InstanceFactory instanceFactory = InstanceFactory.get();
		
		while(_seconds < _time) {
			List<ITimedBasedComponent> timedComponentList = instanceFactory.GetAllTimedBasedComponents();
			
			for(ITimedBasedComponent component : timedComponentList) {
				component.process();
			}
			
			_seconds++;
		}
		
		System.out.println();
		
		instanceFactory.getReporter().limitStatistics();
	}

	public int getCurrentSeconds() {
		return _seconds;
	}
}
