package android.runningbeaver.skills;

import java.util.ArrayList;

import android.runningbeaver.models.MAbsorb;
import android.runningbeaver.models.MDemolition;
import android.runningbeaver.models.MSchockWave;
import android.runningbeaver.models.MUnstoppable;
import android.runningbeaver.objects.Position;

public class Skillbar {
	
	private ArrayList<ISkill> skills = new ArrayList<ISkill>();
	private Position position;

	public Skillbar(Position position) {
		this.position = position;
		createSkills();
	}

	private void createSkills() {
		
		skills.add(new Absorb(new MAbsorb(), new Position(position.getX(), position.getY())));
		
		skills.add(new Unstoppable(new MUnstoppable(), new Position(position.getX() + 80, position.getY())));
		
		skills.add(new SchockWave(new MSchockWave(), new Position(position.getX() + 160, position.getY())));
		
		skills.add(new Demolition(new MDemolition(), new Position(position.getX() + 240, position.getY())));
		
	}
	
	public void checkUnlock (int lvl) {
		
		for (ISkill skill : skills) {
			skill.unlock(lvl);
		}
		
	}

}
