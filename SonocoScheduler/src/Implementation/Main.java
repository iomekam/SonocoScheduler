package Implementation;

public class Main {

	public static void main(String[] args) {
		InstanceFactory factory = InstanceFactory.get();
		
		factory.InitializePress(new Press(1));
		factory.InitializePress(new Press(2));
		factory.InitializePress(new Press(3));
		
		ComponentTimer timer = new ComponentTimer();
		timer.start();
	}
}
