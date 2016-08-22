package Implementation;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import interfaces.IComponentTimer;

public class ComponentTimer implements IComponentTimer {
	
	private ScheduledExecutorService _timer;
	private ScheduledFuture<?> _future;
	private Runnable _task;
	private final int INTERVAL = 1000;
	
	@Override
	public void start() {
		_timer = Executors.newSingleThreadScheduledExecutor();
		_task = new Runnable() {
			@Override
			public void run() {
				tick();
			}
		};
		
		_future = _timer.scheduleAtFixedRate(_task, 0, INTERVAL, TimeUnit.MILLISECONDS);
	}

	@Override
	public void stop() {
		_future.cancel(true);
	}
	
	private void tick() {
		
	}
}
