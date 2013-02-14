package android.runningbeaver.engine;

import java.util.ArrayList;

public abstract class AInvoker<T> implements IInvoker<T> {

	private ArrayList<T> listenerList = new ArrayList<T>();

	public void register(T object) {
		listenerList.add(object);
	}

	public void unRegister(T object) {
		listenerList.remove(object);
	}

	protected ArrayList<T> getListenerClone() {
		return (ArrayList<T>) listenerList.clone();
	}

	public ArrayList<T> getListenerList() {
		return (ArrayList<T>) listenerList.clone();
	}

	public abstract void run();

}
