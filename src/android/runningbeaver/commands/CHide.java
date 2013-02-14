package android.runningbeaver.commands;

import android.runningbeaver.objects.IDrawable;

public final class CHide implements ICommand {
	
	private IDrawable object;

	public CHide(IDrawable object) {
		this.object = object;
	}
	
	@Override
	public void run() {
		object.hide();
	}

}
