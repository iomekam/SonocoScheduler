package Implementation;

import java.util.List;

import interfaces.IComponentTimer;
import interfaces.ITimedBasedComponent;

public class ComponentTimer implements IComponentTimer {
	private int _time;
	
	public ComponentTimer(int timeInSeconds) {
		_time = timeInSeconds;
	}
	
	@Override
	public void start() {
		int count = 0;
		InstanceFactory instanceFactory = InstanceFactory.get();
		
		while(count < _time) {
			List<ITimedBasedComponent> timedComponentList = instanceFactory.GetAllTimedBasedComponents();
			
			for(ITimedBasedComponent component : timedComponentList) {
				component.process();
			}
			
			count++;
		}
		
		instanceFactory.getReporter().limitStatistics();
	}
}
