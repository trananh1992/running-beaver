package android.runningbeaver.engine;

import java.util.ArrayList;

public abstract class LayerInvoker<T> implements IInvoker<T> {

	// draw layer flags
	public static final int LOW = 0;
	public static final int NORMAL = 1;
	public static final int HIGH = 2;

	// normal and high layer
	private ArrayList<T> listenerListLow = new ArrayList<T>();
	private ArrayList<T> listenerListNormal = new ArrayList<T>();
	private ArrayList<T> listenerListHigh = new ArrayList<T>();

	private ArrayList<T> getListByLayer(int layer) {
		switch (layer) {
		case LOW:
			return listenerListNormal;
		case NORMAL:
			return listenerListHigh;
		default:
			return listenerListLow;
		}
	}

	public void register(T object, int layer) {
		unRegister(object); // unregister object on all layers
		getListByLayer(layer).add(object);
	}

	public void unRegister(T object) {
		listenerListLow.remove(object);
		listenerListNormal.remove(object);
		listenerListHigh.remove(object);
	}

	protected ArrayList<T> getListenerClone(int layer) {
		return (ArrayList<T>) getListByLayer(layer).clone();
	}

}
