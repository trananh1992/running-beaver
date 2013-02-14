package android.runningbeaver.engine;

import android.content.Context;
import android.graphics.Canvas;
import android.runningbeaver.R;
import android.runningbeaver.menu.CenterMessage;
import android.runningbeaver.menu.ScoreBoard;
import android.runningbeaver.models.BitmapImage;
import android.runningbeaver.models.IImage;
import android.runningbeaver.models.MScoreBoard;
import android.runningbeaver.objects.Background;
import android.runningbeaver.objects.Direction;
import android.runningbeaver.objects.Life;
import android.runningbeaver.objects.Meteor;
import android.runningbeaver.objects.Money;
import android.runningbeaver.objects.Player;
import android.runningbeaver.objects.Position;
import android.runningbeaver.skills.Skillbar;
import android.view.MotionEvent;

public final class Game {

	// Game instance
	private static Game instance;

	// Player instance
	private Player player;

	// Content objects
	private transient Context context;
	private transient Canvas canvas;

	// internal objects for game running
	private GameThread gameThread = new GameThread();
	private GameLogic gameLogic = new GameLogic();
	private GameStatistic gameStatistic = new GameStatistic();
	
	// Device
	private Device device;
	
	// SountManager
	private SoundManager soundManager;
	
	// object listeners
	private IInvoker<?> crashInvoker = new CrashInvoker();
	private IInvoker<?> moveInvoker = new MoveInvoker();
	private IInvoker<?> drawInvoker = new DrawInvoker();
	private IInvoker<?> durationInvoker = new DurationInvoker();
	private IInvoker<?> touchInvoker = new TouchInvoker();
	
	// Menu Config
	private MenuConfig menuConfig;

	// background
	private IImage backBitmap;

	// TouchEventHandler
	private TouchEventHandler touchEventHandler;

	// draw state
	private boolean drawState = false;

	/**
	 * construct for Game
	 * 
	 * @param context
	 */
	private Game(Context context, MenuConfig menuConfig, Device device) {
		this.context = context;
		this.menuConfig = menuConfig;
		this.device = device;
	}

	/**
	 * initialize game instance
	 * 
	 * @param context
	 */
	public static void initialize(Context context, MenuConfig menuConfig, Device device) {
		instance = new Game(context, menuConfig, device);
		instance.soundManager = new SoundManager();
		instance.touchEventHandler = new TouchEventHandler();
		instance.start();
	}
	
	/**
	 * set game instance
	 */
	public static void setInstance(Game game) {
		instance = game;
	}
	
	public static void restoreGame(Game game, Context context) {
		instance = game;
		instance.context = context;
	}

	/**
	 * get game instance
	 * 
	 * @return Game
	 */
	public static Game getInstance() {
		// @todo: ex einbauen
		return instance;
	}

	private void start() {
		gameLogic.initGame();
		
		// start game thread
		gameThread.runGame();
	}
	
	/**
	 * get player instance
	 * 
	 * @return Player
	 */
	public Player getPlayer() {
		return player;
	}

	public MenuConfig getMenuConfig() {
		return menuConfig;
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	public SoundManager getSoundManager() {
		return soundManager;
	}
	
	public Device getDevice() {
		return device;
	}

	/**
	 * draw all drawable objects on context
	 * 
	 * @param canvas
	 */
	public void draw(Canvas canvas) {
		this.canvas = canvas;
		gameLogic.onDraw(canvas);
		drawInvoker.run();
	}

	/**
	 * start game thread (run game)
	 */
	public void runGame() {
		gameThread.runGame();
	}

	/**
	 * freeze game thread (freeze game)
	 */
	public void freezeGame() {
		gameThread.freezeGame();
	}
	
	public void resumeGame() {
		gameThread.resumeGame();
	}

	/**
	 * trigger touch event
	 * 
	 * @param MotionEvent
	 */
	public void touchEvent(MotionEvent ev) {
		touchEventHandler.onTouch(ev);
	}
	
	/**
	 * get TouchEventHandler
	 * @return getTouchEventHandler
	 */
	public TouchEventHandler getTouchEventHandler() {
		return touchEventHandler;
	}

	/**
	 * get Context
	 * 
	 * @return Context
	 */
	public Context getContext() {
		return context;
	}

	/**
	 * get CrashInvoker
	 * 
	 * @return CrashInvoker
	 */
	public CrashInvoker getCrashInvoker() {
		return (CrashInvoker) crashInvoker;
	}

	/**
	 * get DirectionInvoker
	 * 
	 * @return DirectionInvoker
	 */
	public MoveInvoker getMoveInvoker() {
		return (MoveInvoker) moveInvoker;
	}

	/**
	 * get draw invoker
	 * 
	 * @return DrawInvoker
	 */
	public DrawInvoker getDrawInvoker() {
		return (DrawInvoker) drawInvoker;
	}
	
	/**
	 * get duration invoker
	 * @return DurationInvoker
	 */
	public DurationInvoker getDurationInvoker() {
		return (DurationInvoker) durationInvoker;
	}
	
	/**
	 * get duration invoker
	 * @return DurationInvoker
	 */
	public TouchInvoker getTouchInvoker() {
		return (TouchInvoker) touchInvoker;
	}

	/**
	 * ask for draw state and reset the state to false
	 * 
	 * @return
	 */
	public boolean isDrawState() {
		if (drawState == true) {
			drawState = false;
			return true;
		}
		return drawState;
	}
	
	/**
	 * set drawState
	 * @param drawState
	 */
	public void setDrawState(boolean drawState) {
		this.drawState = drawState;
	}

	/**
	 * get game statistics
	 * 
	 * @return GameStatistic
	 */
	public GameStatistic getStatistic() {
		return gameStatistic;
	}

	/**
	 * GameThread implemented the game loop
	 */
	private final class GameThread implements Runnable {

		private long loop = 0; // debug loop count
		private Thread thread;

		/**
		 * constructor for GameThread
		 */
		public GameThread() {
			thread = new Thread(this);
			thread.setName("GameThread");
		}

		/**
		 * run game thread
		 */
		private void runGame() {
			thread.start();
		}

		/**
		 * freeze game thread
		 */
		private void freezeGame() {
			thread.interrupt();
		}
		
		private void resumeGame() {
			if (thread.isInterrupted()) {
				thread.run();
			}
		}

		/**
		 * magic game thread
		 */
		public void run() {
			
			for (;;) {
				
				if (thread.isInterrupted()) {
					break;
				}

				long loopStartTime = android.os.SystemClock.uptimeMillis();
				
				gameLogic.game(loop); // run game logic here :)

				try {

					// calc sleep time
					int sleepTime = (int) (Config.system.loopTime - (android.os.SystemClock
							.uptimeMillis() - loopStartTime));

					if (sleepTime > 0)
						Thread.sleep(sleepTime); // process sleep

					loop++;
					gameStatistic.addLoop(1);
				} catch (InterruptedException ie) {
					thread.interrupt();
					break;
				}
			}
		}

	}

	/**
	 * GameLogic used for game logic
	 */
	private final class GameLogic {
		
		// Skillbar
		private Skillbar skillbar;
		
		// Background-Image
		private Background background;

		/**
		 * facade for game logic
		 */
		public void game(long loop) {

			// change level on time
			processLevel(loop);

			// creates meteors
			processMeteor(loop);

			// creates bonis
			processBoni(loop);
			
			// trigger touch on objects
			touchInvoker.run();

			// move objects
			moveInvoker.run();

			// some objects crashed?
			crashInvoker.run();
			
			// duration expired?
			durationInvoker.run();

			// get sec point
			if (loop % (1000 / Config.system.loopTime) == 0) {
				gameStatistic.addScore(1);
			}
			
			// change draw state for redraw
			drawState = true;

		}
		
		private void initGame() {
			// init score board
			new ScoreBoard(new MScoreBoard(),
					new Position(device.getScreenWidth() - 220, 10),
					DrawInvoker.HIGH);
			
			// init skillbar
			skillbar = new Skillbar(new Position(Config.skill.posX, Config.skill.posY));
			
			// init background
			background = new Background(BitmapImage.load(Config.game.defaultBackground));
			
			// init player
			player = new Player(DrawInvoker.LOW);
			
			// set defaults
			gameStatistic.addLifes(Config.player.lifes);
			
			// start backgroundmusic
			soundManager.startBackgroundMusic();
			
			// new game message
			new CenterMessage("New Game", android.runningbeaver.menu.Message.defaultPaint(), Duration.NORMAL);
		}
		
		private void onDraw(Canvas canvas) {
			background.draw(canvas);
		}

		private void processLevel(long loop) {
			if (loop % Config.game.levelChange == 0 && loop > 0) {
				gameStatistic.addLevel(1);
				gameStatistic.addScore(gameStatistic.system.level
						* Config.boni.lvlBoni);
				
				skillbar.checkUnlock(gameStatistic.system.level);
				
				processBackground();
			}
		}
		
		private void processBackground() {
			if (gameStatistic.system.level % Config.game.backgroundChange == 0) {
				background.setImage(BitmapImage.load(R.drawable.background_1)); // todo add logic for more than one change!
			}
		}

		private void processMeteor(long loop) {
			int spawnCount = getSpawnCountByLevel(gameStatistic.system.level);
			int numberPerSpawn = getNumberPerSpawnByLevel(gameStatistic.system.level);

			// creates a number of meteors, which is specified in the config
			if (loop % spawnCount == 0) {
				for (int i = 0; i <= numberPerSpawn; i++) {
					createNewMeteor();
				}
			}
		}

		private void processBoni(long loop) {
			// creates a number of money, which is specified in the config
			if (loop % Config.boni.money.loopSpawnCount == 0) {
				createNewMoney();
			} 
			
			if (loop % Config.boni.heart.loopSpawnCount == 0) {
				createNewLife();
			} 
		}

		private int getSpawnCountByLevel(int level) {
			// loopSpawnCount - loopSpawnCount * (level / difficultyChange) + 1
			return (int) (Config.meteor.loopSpawnCount - Config.meteor.loopSpawnCount
					* ((float) level / Config.system.difficultyChange)) + 1;
		}

		private int getNumberPerSpawnByLevel(int level) {
			return (int) Config.meteor.numberPerSpawn * (level / 4)
					+ Config.meteor.numberPerSpawn;
		}

		/**
		 * Creates a new Meteor with random position and speed and registers it
		 * at the draw invoker
		 * 
		 * @see DrawInvoker, Meteor, Position, Direction
		 */
		private void createNewMeteor() {
			new Meteor(getRandomMeteorPosition(), getRandomDirection(),
					DrawInvoker.LOW);
		}

		/**
		 * creates a new Money with random position and 0 direction
		 */
		private void createNewMoney() {
			new Money(getRadomBoniPosition(Config.boni.money.spawnHeight),
					new Direction(0.0, 0.0), DrawInvoker.LOW);
		} 
		
		/**
		 * Creates a new life-boni with random position & 0 direction
		 */
		private void createNewLife() {
			new Life(getRadomBoniPosition(Config.boni.heart.spawnHeight), new Direction(0.0, 0.0), DrawInvoker.LOW);
		}

		/**
		 * Calculates a random width as start position for a meteor.
		 * 
		 * @return an instance of Position with random x.
		 * @see Position
		 */
		private Position getRandomMeteorPosition() {

			int maxWidth = device.getScreenWidth();

			int y = - 20;
			int x = (int) (Math.random() * maxWidth + 1);

			return new Position(x, y);
		}

		private Position getRadomBoniPosition(int boniHeight) {
			int maxWidth = device.getScreenWidth();
			int maxHeight = device.getScreenHeight();

			int y = maxHeight - boniHeight;
			int x = (int) (Math.random() * maxWidth + 1);

			return new Position(x, y);
		}

		/**
		 * Calculates a random fall-speed for a meteor, which is between the max
		 * and min value
		 * 
		 * @return a Direction object
		 * @see Direction
		 * @see Config
		 */
		private Direction getRandomDirection() {
			// Falls noch "schräge" Meteoriten erscheinen sollen ,
			// kann hier dx entsprechend verändert werden
			
			double dx;
			
			if (gameStatistic.getSystem().getLevel() >= Config.meteor.dxChange && Math.random() <= Config.meteor.dxChance) {
				
				dx = ((Math.random() * (Config.meteor.maxSpeed - Config.meteor.minSpeed))
				+ Config.meteor.minSpeed) / 2;
				
				if (Math.random() > 0.5) {
					dx *= -1;
				}
				
			} else dx = 0.0;
			
			double dy = (Math.random() * (Config.meteor.maxSpeed - Config.meteor.minSpeed))
					+ Config.meteor.minSpeed;

			return new Direction(dx, dy);
		}

	}

	public class GameStatistic {

		private PlayerStatistic player;
		private SystemStatistic system;

		public GameStatistic() {
			this.player = new PlayerStatistic();
			this.system = new SystemStatistic();
		}

		public PlayerStatistic getPlayer() {
			return player;
		}

		public SystemStatistic getSystem() {
			return system;
		}

		public void addLifes(int lifes) {
			player.lifes += lifes;
		}

		public void addScore(int score) {
			player.score += score;
		}

		public void addLifesLost(int lifesLost) {
			player.lifesLost += lifesLost;
		}

		public void addSecPlayed(int secPlayed) {
			player.secPlayed += secPlayed;
		}

		public void addLevel(int level) {
			system.level += level;
		}

		public void addLoop(long loops) {
			system.loops += loops;
		}

		// Player
		public class PlayerStatistic {

			private int lifes = 0;
			private int score = 0;
			private int lifesLost = 0;
			private int secPlayed = 0;

			public int getLifes() {
				return lifes;
			}

			public int getScore() {
				return score;
			}

			public int getLifesLost() {
				return lifesLost;
			}

			public int getSecPlayed() {
				return secPlayed;
			}

		}

		public class SystemStatistic {

			private int level = 1;
			private long loops = 0;

			public int getLevel() {
				return level;
			}

			public long getLoops() {
				return loops;
			}

		}

	}

}
