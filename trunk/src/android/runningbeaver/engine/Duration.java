package android.runningbeaver.engine;

import java.util.ArrayList;

import android.runningbeaver.commands.ICommand;
import android.runningbeaver.commands.ICommandList;

public class Duration implements ICommandList {
	
	public static int SHORT = 2000;
	public static int NORMAL = 6000;
	public static int LONG = 12000;
	
	private long startTime;
	private long duration;
	
	private ArrayList<ICommand> commands = new ArrayList<ICommand>();
	
	public Duration(long duration) {
		this.duration = duration;
		this.startTime = android.os.SystemClock.uptimeMillis();
		registerOnInvoker();
	}
	
	private void registerOnInvoker() {
		Game.getInstance().getDurationInvoker().register(this);
	}
	
	private void unRegisterOnInvoker() {
		Game.getInstance().getDurationInvoker().unRegister(this);
	}
	
	public void addCommando(ICommand command) {
		commands.add(command);
	}
	
	public void removeCommando(ICommand command) {
		commands.remove(command);
	}
	
	public boolean isActive() {
		return (startTime + duration > android.os.SystemClock.uptimeMillis());
	}
	
	public void callback() {
		
		// call commands
		for (ICommand command : commands) {
			command.run();
		}
		
		unRegisterOnInvoker();
	}
	
	public long getDuration() {
		return duration;
	}

}
