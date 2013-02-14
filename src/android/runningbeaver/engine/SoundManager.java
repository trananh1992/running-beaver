package android.runningbeaver.engine;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

/**
 * Manages background-music & sound-effects
 * @author Marius
 *
 */ 
public class SoundManager {  
	
	public static SoundManager instance; 
	private MediaPlayer backMusicPlayer; 
	private SoundPool soundPlayer; 
	private int[] playlist = new int[3]; 
	private int songCount = 0;  
	private final int EXPLOSION_SOUND; 
	private final int GAMEOVER_SOUND;
	private final int JUMP_SOUND; 
	private final int COIN_SOUND;
	
	/**
	 * Constructor.  
	 * Puts all songs into an array as playlist 
	 * Loads animation sounds
	 */
	public SoundManager() {   
		playlist[0] = Config.sounds.music1; 
		playlist[1] = Config.sounds.music2; 
		playlist[2] = Config.sounds.music3;  
		backMusicPlayer = MediaPlayer.create(Game.getInstance().getContext(), playlist[2]); 
		soundPlayer = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);  
		EXPLOSION_SOUND = soundPlayer.load(Game.getInstance().getContext(),Config.sounds.explosionSound, 1); 
		GAMEOVER_SOUND = soundPlayer.load(Game.getInstance().getContext(), Config.sounds.gameoverSound, 1);
		JUMP_SOUND = soundPlayer.load(Game.getInstance().getContext(), Config.sounds.jumpSound, 1); 
		COIN_SOUND = soundPlayer.load(Game.getInstance().getContext(), Config.sounds.coinSound, 1);
	}
	
	/**
	 * Starts the background music 
	 */
	public void startBackgroundMusic() {
		try { 
			if(!backMusicPlayer.isPlaying() && Game.getInstance().getMenuConfig().getMusic()) { 
				backMusicPlayer.setLooping(true);
				backMusicPlayer.start();
			}
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
		}
	} 
	
	/**
	 * Stops background music
	 */
	public void stopBackgroundMusic() {
		if(backMusicPlayer.isPlaying()) {
			backMusicPlayer.stop();
		}  
		backMusicPlayer.release(); 
		backMusicPlayer = MediaPlayer.create(Game.getInstance().getContext(), playlist[2]); 
	} 
	 
	/**
	 * Stops the player annd starts again with the next song.
	 */
	@SuppressWarnings("unused")
	private void playNextSong() {
		this.stopBackgroundMusic();
		backMusicPlayer = MediaPlayer.create(Game.getInstance().getContext(), playlist[songCount]); 
		this.startBackgroundMusic();
	} 
	
	/**
	 * Plays an jump sound, which can be used when the beaver jumps. 
	 * The beaver is cute!
	 */
	public void playJumpSound() {
		soundPlayer.play(JUMP_SOUND, (float) 1.0, (float) 1.0, 0, 0, 1);
	}
	
	/**
	 * Plays an exlosion sound, which can be used when a meteor hits an 
	 * other objects / the ground
	 */
	public void playExplosionSound() {
		soundPlayer.play(EXPLOSION_SOUND, (float) Config.sounds.soundEffectVolume, (float) Config.sounds.soundEffectVolume, 0, 0, 1);
	}
	
	/**
	 * Plays an gameover sound, which can be used when player is dead. 
	 * muhahahahahahahaha
	 */
	public void playGameOverSound() {
		soundPlayer.play(GAMEOVER_SOUND, (float) 1.0, (float) 1.0, 0, 0, 1);
	} 
	
	/**
	 * Plays an gameover sound, which can be used when player is dead. 
	 * muhahahahahahahaha
	 */
	public void playCoinSound() {
		soundPlayer.play(COIN_SOUND, (float) Config.sounds.soundEffectVolume, (float) Config.sounds.soundEffectVolume, 0, 0, 1);
	} 
	
	/** 
	 * Before an application is closed, call this method to release all resources 
	 * which are hold by the musicplayer / soundplayer.
	 */
	public void release() {
		this.stopBackgroundMusic(); 
		soundPlayer.release();
		backMusicPlayer.release();
	}
	
}
