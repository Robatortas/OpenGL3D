package robatortas.code.files;

public class MainApp {
	
	public static DisplayManager display;
	
	public static void main(String[] args) {
		display = new DisplayManager();
		
		display.create();
		
		System.out.println("Hello World");
	}
}
