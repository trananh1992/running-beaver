package android.runningbeaver.objects;

import java.util.ArrayList;

import android.runningbeaver.commands.ICommand;
import android.runningbeaver.commands.ICommandList;
import android.runningbeaver.engine.Game;

public class TouchObject implements ITouchable, ICommandList {

	protected Surface surface;
	protected int touchLayer;
	
	protected ArrayList<ICommand> commands = new ArrayList<ICommand>();
	
	public TouchObject(Surface surface, int touchLayer) {
		this.surface = surface;
		this.touchLayer = touchLayer;
		touchable();
	}
	
	public void addCommando(ICommand command) {
		commands.add(command);
	}
	
	public void removeCommando(ICommand command) {
		commands.remove(command);
	}

	@Override
	public void touchable() {
		Game.getInstance().getTouchInvoker().register(this, touchLayer);
	}

	@Override
	public void unTouchable() {
		Game.getInstance().getTouchInvoker().unRegister(this);
	}
	
	@Override
	public Surface getSurface() {
		return surface;
	}

	@Override
	public void onTouch() {
		
		for (ICommand command : commands) {
			command.run();
		}
		
	}

}
