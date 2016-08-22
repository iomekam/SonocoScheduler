package Implementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import interfaces.IExtruder;
import interfaces.IPress;
import interfaces.IReporter;

public class Reporter implements IReporter {
	
	private Map<IExtruder, List<IPress>> _extruderMap;
	
	public Reporter() {
		_extruderMap = new HashMap<IExtruder, List<IPress>>();
	}

	@Override
	public void chargeCompleted(IExtruder extruder, IPress press) {
		if(_extruderMap.containsKey(extruder) == false) {
			_extruderMap.put(extruder, new ArrayList<IPress>());
		}

		_extruderMap.get(extruder).add(press);
		
		System.out.println(
				String.format("Extruder %d has finished creating the charge for Press %d", extruder.getId(), press.getId())
			);
	}

	@Override
	public void limitStatistics() {
		List<IPress> presses = InstanceFactory.get().GetAllPresses();
		
		for(IPress press : presses) {
			System.out.println(
				String.format("Press %d: %d/%d", press.getId(), press.getTotalMoldsCreated(), press.getLimit())
			);
		}
		
	}
}
