package interfaces;

public interface IScheduler {
	IPress getNextPress();
	boolean isLimitReachable(int timeInDay);
}
