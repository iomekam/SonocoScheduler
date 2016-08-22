package Implementation;

import java.util.ArrayList;
import java.util.List;

import interfaces.IComponentTimer;
import interfaces.IExtruder;
import interfaces.IInstanceFactory;
import interfaces.IPress;
import interfaces.IReporter;
import interfaces.IScheduler;
import interfaces.ITimedBasedComponent;

public class InstanceFactory implements IInstanceFactory {
	
	private static InstanceFactory _instanceFactory;
	private List<IPress> _pressList;
	private List<IExtruder> _extruderList;
	private IScheduler _scheduler;
	private IReporter _reporter;
	private IComponentTimer _timer;
	
	private InstanceFactory() {
		_pressList = new ArrayList<IPress>();
		_extruderList = new ArrayList<IExtruder>();
	}

	@Override
	public List<IExtruder> GetAllExtruders() {
		return _extruderList;
	}

	@Override
	public List<IPress> GetAllPresses() {
		return _pressList;
	}

	@Override
	public List<ITimedBasedComponent> GetAllTimedBasedComponents() {
		List<ITimedBasedComponent> componentList = new ArrayList<ITimedBasedComponent>();
		
		for(IExtruder extruder : _extruderList) {
			if(extruder instanceof ITimedBasedComponent) {
				componentList.add((ITimedBasedComponent)extruder);
			}
		}
		
		for(IPress press : _pressList) {
			if(press instanceof ITimedBasedComponent) {
				componentList.add((ITimedBasedComponent)press);
			}
		}
		
		return componentList;
	}

	@Override
	public void InitializeExtuder(IExtruder extruder) {
		_extruderList.add(extruder);

	}

	@Override
	public void InitializePress(IPress press) {
		_pressList.add(press);

	}

	public static InstanceFactory get() {
		if(_instanceFactory == null) {
			_instanceFactory = new InstanceFactory();
		}
		
		return _instanceFactory;
	}

	@Override
	public IScheduler getSceduler() {
		return _scheduler;
	}

	@Override
	public void InitializeScheduler(IScheduler scheduler) {
		_scheduler = scheduler;
	}

	@Override
	public IReporter getReporter() {
		return _reporter;
	}

	@Override
	public void InitializeReporter(IReporter reporter) {
		_reporter = reporter;
		
	}

	@Override
	public IComponentTimer getComponentTimer() {
		return _timer;
	}

	@Override
	public void InitializeComponentTimer(IComponentTimer timer) {
		_timer = timer;
		
	}
}
