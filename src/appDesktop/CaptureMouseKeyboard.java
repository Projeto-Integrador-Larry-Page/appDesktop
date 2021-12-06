package appDesktop;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;
import org.jnativehook.mouse.NativeMouseWheelEvent;
import org.jnativehook.mouse.NativeMouseWheelListener;

public class CaptureMouseKeyboard implements NativeMouseInputListener, NativeMouseWheelListener, NativeKeyListener {

	Timer timer = new Timer();
	static Logger logger = Logger.getLogger("MyEvents");  
	
	private boolean isActive = false;
	
	private int count = 0;
	private int MouseKeyEvent;
	private int CurrentEvent = 0;
	private int MouseX, MouseY;
	private int AtualX = 0, AtualY = 0;
	
	TimerTask task;

	public void checkInactivity(int tempo) {

		Timer timer = new Timer();
		
		
		task = new TimerTask() {
			

			public void run() {
				
					//System.out.println("CurrentEvent = " + CurrentEvent + "\nMouseKeyEvent = " + MouseKeyEvent + "\n" + "isActive = " + isActive + "\n");
					
					if (isActive == false && (CurrentEvent != 1 && MouseKeyEvent != 1)) { 
						
						FileUtil.escreverTexto("ATIVO;" + CurrentEvent + ";" + getDateTimeEvent() + ";" + System.getProperty("user.name"));
						
						
						System.out.println("--- ATIVO ---");
						System.out.println("ID:" + CurrentEvent + "; Data: " + getDateTimeEvent() + "; Usuario: " + System.getProperty("user.name"));
						System.out.println("");
						
						isActive = true;			
					}
					
					if(CurrentEvent != 1 || (MouseX != AtualX && MouseY != AtualY)) { 
						count = 0;
						CurrentEvent = MouseKeyEvent;
						
						AtualX = MouseX;
					    AtualY = MouseY;
					}							
					
					if (count <= tempo && isActive) {						
						count++;
						
//						System.out.println(count + " Segundos de Inatividade...\n");
						
						CurrentEvent = 1;							
					} else {
						if (isActive) {

							FileUtil.escreverTexto("INATIVO;" + CurrentEvent + ";" + getDateTimeEvent() + ";" + System.getProperty("user.name"));
							
							System.out.println("--- INATIVO ---");
							System.out.println("ID:" + CurrentEvent + "; Data: " + getDateTimeEvent() + "; Usuario: " + System.getProperty("user.name"));
							System.out.println("");
							
							MouseKeyEvent = CurrentEvent;
							isActive = false;
						}
					}
					
			};
		};
		
		timer.scheduleAtFixedRate(task, 0, 1000); // 1000ms = 1sec
	}
	
	public void nativeMouseClicked(NativeMouseEvent e) {
//		 System.out.println("Mouse Clicked: " + e.getClickCount());
//		 System.out.println("MOUSE CLICK: " + e.getID());
		
		if (MouseKeyEvent != e.getID()) {

//			System.out.println("--- EVENTO MOUSE CLICAR ---");
//			System.out.println("ID: " + e.getID() + " Data: " + this.getDateTimeEvent() + " Usuario: " + System.getProperty("user.name"));
//			System.out.println("");
			
			MouseKeyEvent = e.getID();
			CurrentEvent = 0;
		}
	}

	public void nativeMousePressed(NativeMouseEvent e) {

		if (MouseKeyEvent != e.getID()) {
			
//			System.out.println("--- EVENTO MOUSE PRESSIONAR ---");
//			System.out.println("ID: " + e.getID() + " Data: " + this.getDateTimeEvent() + " Usuario: " + System.getProperty("user.name"));
//			System.out.println("");
			
			MouseKeyEvent = e.getID();
			CurrentEvent = 0;
		}		
	}

	public void nativeMouseMoved(NativeMouseEvent e) {
		
		MouseX = e.getX();
		MouseY = e.getY();
//		System.out.println("Mouse Moved: " + e.getX() + ", " + e.getY());
		
		if (MouseKeyEvent != e.getID()) {
				
//			System.out.println("--- EVENTO MOUSE MOVE ---");
//			System.out.println("ID: " + e.getID() + " Data: " + this.getDateTimeEvent() + " Usuario: " + System.getProperty("user.name"));
//			System.out.println("");
			
			MouseKeyEvent = e.getID();
			CurrentEvent = 0;
		}
	}

	public void nativeMouseDragged(NativeMouseEvent e) {
		
		if (MouseKeyEvent != e.getID()) {
			
//			System.out.println("--- EVENTO MOUSE SOLTAR ---");
//			System.out.println("ID: " + e.getID() + " Data: " + this.getDateTimeEvent() + " Usuario: " + System.getProperty("user.name"));
//			System.out.println("");
			
			MouseKeyEvent = e.getID();
			CurrentEvent = 0;
		}
	}

	public void nativeMouseWheelMoved(NativeMouseWheelEvent e) {
		if (MouseKeyEvent != e.getID()) {
			
//			System.out.println("--- EVENTO MOUSE SCROLLAR ---");
//			System.out.println("ID: " + e.getID() + " Data: " + this.getDateTimeEvent() + " Usuario: " + System.getProperty("user.name"));
//			System.out.println("");
			
			MouseKeyEvent = e.getID();
			CurrentEvent = 0;
		}
    }

	public void nativeMouseReleased(NativeMouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nativeKeyPressed(NativeKeyEvent e) {
		if (MouseKeyEvent != e.getID()) {
			
//			System.out.println("--- EVENTO TECLA PRECIONADA ---");
//			System.out.println("ID: " + e.getID() + " Data: " + this.getDateTimeEvent() + " Usuario: " + System.getProperty("user.name"));
//			System.out.println("");
			
			MouseKeyEvent = e.getID();
			CurrentEvent = 0;
		}
		
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent e) {
		if (MouseKeyEvent != e.getID()) {
			
//			System.out.println("--- EVENTO TECLA SOLTADA ---");
//			System.out.println("ID: " + e.getID() + " Data: " + this.getDateTimeEvent() + " Usuario: " + System.getProperty("user.name"));
//			System.out.println("");
			
			MouseKeyEvent = e.getID();
			CurrentEvent = 0;
		}
		
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent e) {
		if (MouseKeyEvent != e.getID()) {
			
//			System.out.println("--- EVENTO TECLA DIGITADA ---");
//			System.out.println("ID: " + e.getID() + " Data: " + this.getDateTimeEvent() + " Usuario: " + System.getProperty("user.name"));
//			System.out.println("");
			
			MouseKeyEvent = e.getID();
			CurrentEvent = 0;
		}
		
	}
	
	private String getDateTimeEvent() {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		return dtf.format(now);
	}

	
	
}