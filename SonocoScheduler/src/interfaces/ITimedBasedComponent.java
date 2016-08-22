package interfaces;

public interface ITimedBasedComponent {
	void process();
	int getTimeRemaining();
	boolean isActive();
}
