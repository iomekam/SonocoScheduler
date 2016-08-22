package interfaces;

import java.util.List;

public interface IInstanceFactory {
	List<IExtruder> GetAllExtruders();
	List<IPress> GetAllPresses();
	List<ITimedBasedComponent> GetAllTimedBasedComponents();
	
	IScheduler getSceduler();
	IReporter getReporter();
	IComponentTimer getComponentTimer();
	
	void InitializeExtuder(IExtruder extruder);
	void InitializePress(IPress press);
	void InitializeScheduler(IScheduler scheduler);
	void InitializeReporter(IReporter reporter);
	void InitializeComponentTimer(IComponentTimer timer);
}
