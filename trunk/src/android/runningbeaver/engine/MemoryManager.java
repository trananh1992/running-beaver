package android.runningbeaver.engine;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.content.Context;

public class MemoryManager {
	
	public static void saveGame() {
		
		Game.getInstance().freezeGame();
		
		try {
			
			ObjectOutputStream oos = new ObjectOutputStream(Game.getInstance().getContext().openFileOutput("savegame.dat", Context.MODE_PRIVATE));
			oos.writeObject(Game.getInstance());
			oos.close();
			
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {
			
		}
		
		Game.getInstance().resumeGame();
		
	}
	
	public static void loadGame(Context context) {
		
		try {
			
			ObjectInputStream ois = new ObjectInputStream(context.openFileInput("savegame.dat"));			
			Game.restoreGame((Game) ois.readObject(), context);
			
		} catch (ClassNotFoundException e) {
		
		} catch (FileNotFoundException e) {
		
		} catch (IOException e) {
		
		}
		
		Game.getInstance().resumeGame();
		
	}

}
