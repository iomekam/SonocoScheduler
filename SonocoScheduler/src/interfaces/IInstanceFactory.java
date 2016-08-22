package interfaces;

import java.util.List;

public interface IInstanceFactory {
	List<IExtruder> GetAllExtruders();
	List<IPress> GetAllPresses();
	List<ITimedBasedComponent> GetAllTimedBasedComponents();
	
	void InitializeExtuder(IExtruder extruder);
	void InitializePress(IPress press);
}
