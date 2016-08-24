package Implementation;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import interfaces.IExtruder;
import interfaces.IPress;
import interfaces.IReporter;

public class Reporter implements IReporter {
	
	private Map<IExtruder, List<IPress>> _extruderMap;
	private 
	List<String> outputList;
	
	public Reporter() {
		_extruderMap = new HashMap<IExtruder, List<IPress>>();
		 outputList = new ArrayList<String>();
	}

	@Override
	public void chargeCompleted(IExtruder extruder, IPress press) {
		if(_extruderMap.containsKey(extruder) == false) {
			_extruderMap.put(extruder, new ArrayList<IPress>());
		}

		_extruderMap.get(extruder).add(press);
		outputList.add(String.format("[%s]: Extruder %d has finished creating the charge for Press %d",  
						secondsToTime(InstanceFactory.get().getComponentTimer().getCurrentSeconds()), extruder.getId(), press.getId())
			);
	}

	@Override
	public void limitStatistics() {
		List<IPress> presses = InstanceFactory.get().GetAllPresses();
		
		for(IPress press : presses) {
			System.out.println(
				String.format("Press %d: %d/%d, Score of %d sec", press.getId(), press.getTotalMoldsCreated(), press.getLimit(), press.getScore())
			);
		}
		
		try {
			Path file = Paths.get("output.txt");
			Files.write(file, outputList, Charset.forName("UTF-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String secondsToTime(int totalSeconds) {		
		int hours = totalSeconds / 3600;
		int minutes = (totalSeconds % 3600) / 60;
		int seconds = totalSeconds % 60;
		
		return String.format("%02d:%02d:%02d", hours, minutes, seconds); 
	}
}
