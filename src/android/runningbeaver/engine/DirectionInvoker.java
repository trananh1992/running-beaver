package android.runningbeaver.engine;

import android.runningbeaver.objects.IMoveable;

public class DirectionInvoker extends AInvoker<IMoveable> {

	/**
	 * move moveObjects by direction
	 */
	public void run() {

		// iterate listenerList to move all moveObjects
		for (IMoveable moveObject : getListenerClone()) {
			if (moveObject == null) continue;
			moveObject.move(); // move object by direction
		}
	}

}
