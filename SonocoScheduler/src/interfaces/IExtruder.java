package interfaces;

public interface IExtruder {
	IPress requestNextPress();
	void handOffCharge(IPress press);
}
