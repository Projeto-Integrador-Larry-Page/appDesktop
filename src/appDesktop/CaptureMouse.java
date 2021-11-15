package appDesktop;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;
import org.jnativehook.mouse.NativeMouseWheelEvent;
import org.jnativehook.mouse.NativeMouseWheelListener;

public class CaptureMouse implements NativeMouseInputListener, NativeMouseWheelListener {

	Timer timer = new Timer();

	private boolean isActive = false;
	
	private int count = 0;
	private int eventoMouse;
	private int eventoAtual = 0;
	private int MouseX, MouseY;
	private int AtualX = 0, AtualY = 0;
	
	TimerTask task;

	public void verificarAtividadeMouse(int tempo) {

		Timer timer = new Timer();
		
		
		task = new TimerTask() {
			

			public void run() {

					if (isActive == false && (eventoAtual != 9998 && eventoMouse != 9998)) { 
						
						System.out.println("--- ATIVO ---");
						System.out.println("ID:" + eventoAtual + "; Data: " + getDateTimeEvent() + "; Usuario: " + System.getProperty("user.name"));
						System.out.println("");
						
						isActive = true;			
					}
					
					if(eventoAtual != 9999 || (MouseX != AtualX && MouseY != AtualY)) { 
						count = 0;
						eventoAtual = eventoMouse;
						
						AtualX = MouseX;
					    AtualY = MouseY;
					}							
					
					if (count <= tempo && eventoAtual != 9998) {						
						count++;
						eventoAtual = 9999;							
					} else {
						if (eventoAtual != 9998) {
							
							System.out.println("--- INATIVO ---");
							System.out.println("ID:" + eventoAtual + "; Data: " + getDateTimeEvent() + "; Usuario: " + System.getProperty("user.name"));
							System.out.println("");

							eventoAtual = 9998;
							eventoMouse = eventoAtual;
							isActive = false;
						}
					}				
			}
		};
		
		timer.scheduleAtFixedRate(task, 0, 1000); // 1000ms = 1sec
	}
	
	public void nativeMouseClicked(NativeMouseEvent e) {
//		 System.out.println("Mouse Clicked: " + e.getClickCount());
//		 System.out.println("MOUSE CLICK: " + e.getID());
		
		if (eventoMouse != e.getID()) {

//			System.out.println("--- EVENTO MOUSE CLICAR ---");
//			System.out.println("ID: " + e.getID() + " Data: " + this.getDateTimeEvent() + " Usuario: " + System.getProperty("user.name"));
//			System.out.println("");
			
			eventoMouse = e.getID();
			eventoAtual = 0;
		}
	}

	public void nativeMousePressed(NativeMouseEvent e) {

		if (eventoMouse != e.getID()) {
			
//			System.out.println("--- EVENTO MOUSE PRESSIONAR ---");
//			System.out.println("ID: " + e.getID() + " Data: " + this.getDateTimeEvent() + " Usuario: " + System.getProperty("user.name"));
//			System.out.println("");
			
			eventoMouse = e.getID();
			eventoAtual = 0;
		}		
	}

	public void nativeMouseMoved(NativeMouseEvent e) {
		
		MouseX = e.getX();
		MouseY = e.getY();
//		System.out.println("Mouse Moved: " + e.getX() + ", " + e.getY());
		
		if (eventoMouse != e.getID()) {
				
//			System.out.println("--- EVENTO MOUSE MOVE ---");
//			System.out.println("ID: " + e.getID() + " Data: " + this.getDateTimeEvent() + " Usuario: " + System.getProperty("user.name"));
//			System.out.println("");
			
			eventoMouse = e.getID();
			eventoAtual = 0;
		}
	}

	public void nativeMouseDragged(NativeMouseEvent e) {
		
		if (eventoMouse != e.getID()) {
			
//			System.out.println("--- EVENTO MOUSE SOLTAR ---");
//			System.out.println("ID: " + e.getID() + " Data: " + this.getDateTimeEvent() + " Usuario: " + System.getProperty("user.name"));
//			System.out.println("");
			
			eventoMouse = e.getID();
			eventoAtual = 0;
		}
	}

	public void nativeMouseWheelMoved(NativeMouseWheelEvent e) {
		if (eventoMouse != e.getID()) {
			
//			System.out.println("--- EVENTO MOUSE SCROLLAR ---");
//			System.out.println("ID: " + e.getID() + " Data: " + this.getDateTimeEvent() + " Usuario: " + System.getProperty("user.name"));
//			System.out.println("");
			
			eventoMouse = e.getID();
			eventoAtual = 0;
		}
    }

	public void nativeMouseReleased(NativeMouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	private String getDateTimeEvent() {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		return dtf.format(now);
	}
	
}