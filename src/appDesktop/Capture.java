package appDesktop;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class Capture implements NativeKeyListener {

	public void nativeKeyPressed(NativeKeyEvent e) {

		if (e.getKeyCode() == NativeKeyEvent.VC_ENTER){
			System.out.println("");
		}else if (e.getKeyCode() == NativeKeyEvent.VC_SPACE){
			System.out.print(" ");
		}else{
			System.out.print(NativeKeyEvent.getKeyText(e.getKeyCode()));
		}
	}

	public void nativeKeyReleased(NativeKeyEvent e) {
	}

	public void nativeKeyTyped(NativeKeyEvent e) {
	}

}