package interfaces;

public interface IScheduler {
	IPress getNextPress(int lastPressPosition);
	boolean isLimitReachable(int timeInDay);
}
