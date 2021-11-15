package appDesktop;

import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

public class App {

	public static void main(String[] args) throws IOException {

		
		/*TimerTask timerTask = new MyTimerTask();
        //running timer task as daemon thread
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, 0, 10*1000);
        System.out.println("TimerTask started");
        //cancel after sometime
        try {
            Thread.sleep(120000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timer.cancel();
        System.out.println("TimerTask cancelled");
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
		
		
		
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
		
		captureActivity.recordEventsLogs();
		
		captureActivity.checkInactivity(10);
		
		
		/*
		 * String copyright = "\n" +
		 * "JNativeHook: Global keyboard and mouse listeners for Java.\n" +
		 * "Copyright (C) 2006-2021 Alexander Barker.  All Rights Reserved.\n" +
		 * "https://github.com/kwhat/jnativehook/\n" + "\n" +
		 * "JNativeHook is free software: you can redistribute it and/or modify\n" +
		 * "it under the terms of the GNU Lesser General Public License as published\n"
		 * + "by the Free Software Foundation, either version 3 of the License, or\n" +
		 * "(at your option) any later version.\n" + "\n" +
		 * "JNativeHook is distributed in the hope that it will be useful,\n" +
		 * "but WITHOUT ANY WARRANTY; without even the implied warranty of\n" +
		 * "MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the\n" +
		 * "GNU General Public License for more details.\n" + "\n" +
		 * "You should have received a copy of the GNU Lesser General Public License\n"
		 * + "along with this program.  If not, see <http://www.gnu.org/licenses/>.\n";
		 * System.out.println(copyright);
		 * 
		 * SwingUtilities.invokeLater(new Runnable() { public void run() { new
		 * NativeHookDemo(); } });
		 */

	}

}
