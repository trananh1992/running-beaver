package android.runningbeaver.commands;

import android.runningbeaver.objects.IDestroyable;

public final class CDestroy implements ICommand {
	
	private IDestroyable object;

	public CDestroy(IDestroyable object) {
		this.object = object;
	}
	
	@Override
	public void run() {
		object.destroy();
	}
	
}
