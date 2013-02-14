package android.runningbeaver.engine;

import android.runningbeaver.objects.IMoveable;

public class MoveInvoker extends AInvoker<IMoveable> {

	/**
	 * move moveObjects by direction
	 */
	@Override
	public void run() {

		// iterate listenerList to move all moveObjects
		for (IMoveable moveObject : getListenerClone()) {
			moveObject.move(); // move object by direction
		}
	}

}
