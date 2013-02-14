package android.runningbeaver.engine;

import java.util.ArrayList;

public interface IInvoker<T> {

	public void run();
	
	public ArrayList<T> getListenerList();

}
