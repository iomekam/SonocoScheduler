package Implementation;

import java.util.List;

import interfaces.IComponentTimer;
import interfaces.ITimedBasedComponent;

public class ComponentTimer implements IComponentTimer {
	private int TIME = 6000; // 10 minutes
	
	@Override
	public void start() {
		int count = 0;
		while(count < TIME) {
			InstanceFactory instanceFactory = InstanceFactory.get();
			List<ITimedBasedComponent> timedComponentList = instanceFactory.GetAllTimedBasedComponents();
			
			for(ITimedBasedComponent component : timedComponentList) {
				component.process();
			}
			
			count++;
		}
	}

	@Override
	public void stop() {
		
	}
}
