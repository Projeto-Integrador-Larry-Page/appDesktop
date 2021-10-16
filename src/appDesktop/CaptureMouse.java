package appDesktop;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.jnativehook.NativeHookException;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;

public class CaptureMouse implements NativeMouseInputListener {

	private int eventoAtual;
	private boolean hasStarted = false;
	TimerTask task;

	private String getDateTimeEvent() {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		return dtf.format(now);
	}

	public void nativeMouseClicked(NativeMouseEvent e) {
		// System.out.println("Mouse Clicked: " + e.getClickCount());
		// System.out.println("MOUSE CLICK: " + e.getID());

		if (eventoAtual != e.getID()) {
			System.out.println("--- EVENTO MOUSE CLICK ---");
			System.out.println("ID: " + e.getID() + " Data: " + this.getDateTimeEvent() + " Usuario: "
					+ System.getProperty("user.name"));
			System.out.println("");
			eventoAtual = e.getID();
		}
		if (!hasStarted) {
			checkInactivity(e.getID(), this.getDateTimeEvent());
		}
	}

	public void nativeMousePressed(NativeMouseEvent e) {
		// System.out.println("Mouse Pressed: " + e.getButton());
	}

	public void nativeMouseReleased(NativeMouseEvent e) {
		// System.out.println("Mouse Released: " + e.getButton());
	}

	public void nativeMouseMoved(NativeMouseEvent e) {
		// System.out.println("Mouse Moved: " + e.getX() + ", " +
		// e.getY());
		if (eventoAtual != e.getID()) {
			System.out.println("--- EVENTO MOVEU-SE ---");
			System.out.println("ID: " + e.getID() + " Data: " + this.getDateTimeEvent() + " Usuario: "
					+ System.getProperty("user.name"));
			eventoAtual = e.getID();
		}

		if (!hasStarted) {
			checkInactivity(e.getID(), this.getDateTimeEvent());
		}
	}

	public void nativeMouseDragged(NativeMouseEvent e) {
		// System.out.println("Mouse Dragged: " + e.getX() + ", " +
		// e.getY());
	}

	public void checkInactivity(int eventID, String DateTime) {

		Timer timer = new Timer();

		task = new TimerTask() {
			private int i = 0;

			public void run() {
				if (eventID == eventoAtual) {
					if (i <= 10) {
						hasStarted = true;
						System.out.println("" + i++ + " Segundos no mesmo evento");
					} else {
						System.out.println("--- INATIVO ---");
						System.out.println("ID:" + eventoAtual + " Data: " + DateTime + " Usuario: "
								+ System.getProperty("user.name"));
						System.out.println("");

						hasStarted = false;
						timer.cancel();
					}
				} else {
					hasStarted = false;
					timer.cancel();
				}

			}
		};
		timer.scheduleAtFixedRate(task, 0, 1000); // 1000ms = 1sec

	}

}