package android.runningbeaver.engine;

import android.runningbeaver.R;

public final class Config {
	
	// System
	public static final class system {
		public final static int loopTime = 40;
		public final static int drawHandler = 25;
		public final static int difficultyChange = 40;
	}
	
	public static final class game {
		public final static int levelChange = 400;
		public final static int defaultBackground = R.drawable.background_1;
		public final static int backgroundChange = 10;
	}
	
	// Skill
	public static final class skill {
		public static final int posX = 20;
		public static final int posY = 20;
		
		public static final class absorb {
			public final static int duration = 10000;
			public final static int cooldown = 20000;
			public final static int unlock = 5;
			public final static int points = 20;
		} 
		
		public static final class unstoppable {
			public final static int duration = 5000;
			public final static int cooldown = 40000;
			public final static int unlock = 10;
		} 
		
		public static final class schockWave {
			public final static int duration = 1000;
			public final static int cooldown = 20000;
			public final static int unlock = 15;
			public final static int range = 20;
			public final static int delay = 100;
		} 
		
		public static final class demolition {
			public final static int duration = 0;
			public final static int cooldown = 30000;
			public final static int unlock = 20;
		} 
		
	}
	
	// Player
	public static final class player {
		public static final double move = 6;
		public final static int jumpSpeed = 15;
		public final static int jumpSteps = 30;
		public final static int lifes = 5;
	}
	
	//Meteor 
	public static final class meteor {
		public final static double minSpeed = 2.0; 
		public final static double maxSpeed = 4.0; 
		public final static int numberPerSpawn = 1; 
		public final static int loopSpawnCount = 40;
		public final static int lifeReduce = 1;
		public final static double dxChance = 0.2;
		public final static int dxChange = 10;
	}
	
	// Boni
	public static final class boni {
		public final static int lvlBoni = 50;
		
		public static final class money {
			public final static int score = 50; 
			public final static int spawnHeight = 40;
			public final static int loopSpawnCount = 200; 
		} 
		
		public static final class heart {
			public final static int loopSpawnCount = 400;
			public final static int spawnHeight = 30; 
			public final static int lifeBonus = 1;
		}
	}
	
	//SoundSettings 
	public static final class sounds {
		public static final int music1 = R.raw.music;
		public static final int music2 = R.raw.music2;
		public static final int music3 = R.raw.music3;
		public final static int explosionSound = R.raw.explosion;  
		public final static int jumpSound = R.raw.jump; 
		public final static int coinSound = R.raw.coin; 
		public final static int gameoverSound = R.raw.gameover;
		public final static double soundEffectVolume = 0.2;
	}
	
}
