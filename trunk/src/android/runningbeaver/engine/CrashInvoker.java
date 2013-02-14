package android.runningbeaver.engine;

import java.util.ArrayList;

import android.runningbeaver.objects.ICrashable;
import android.runningbeaver.objects.Surface;

public class CrashInvoker extends AInvoker<ICrashable> {

	/**
	 * test surfaces for crash
	 */
	@Override
	public void run() {

		ArrayList<ICrashable> listeners = getListenerClone();

		// select one object to compare (crashObject)
		for (ICrashable crashObject : listeners) {
			
			if (crashObject == null) continue;

			// get current Surface
			Surface currentSurface = crashObject.getSurface();

			// select possible object for crash (objector)
			for (ICrashable objector : listeners) {

				// dont compare the same object
				if (crashObject.equals(objector))
					continue;

				// some sync problems (copy work)
				if (objector == null)
					continue;

				// if currentSurface touch objector trigger crash
				if (currentSurface.contact(objector.getSurface())) {
					// trigger crash
					crashObject.crash(objector);
				}
			}
		}

	}
}
