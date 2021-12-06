package appDesktop;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

public class App {
	
	public static void main(String[] args) throws IOException {

		// Clear previous logging configurations.
		LogManager.getLogManager().reset();

		// Get the logger for "org.jnativehook" and set the level to off.
		Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
		logger.setLevel(Level.OFF);

		try {
			GlobalScreen.registerNativeHook();
			
		} catch (NativeHookException ex) {
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());

			System.exit(1);
		}

		// Construct the example object.
		CaptureMouseKeyboard captureActivity = new CaptureMouseKeyboard();
				
		// Add the appropriate listeners for the example object.
		GlobalScreen.addNativeMouseListener(captureActivity);
		GlobalScreen.addNativeMouseMotionListener(captureActivity);
		GlobalScreen.addNativeMouseWheelListener(captureActivity);
		GlobalScreen.addNativeKeyListener(captureActivity);
		
		captureActivity.checkInactivity(60);
				
		//captureActivity.recordEventsLogs();
		 /*Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			 public void run() {		        
		        try {
					Thread.sleep(1000);
					FileUtil.escreverTexto("Programa finalizado");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		      }
		 }));*/
	}
	
}
