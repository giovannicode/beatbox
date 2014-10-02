package gio.beatboxer;

import java.io.IOException;
import java.util.Vector;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

public class SoundBox 
{
	int currentSoundEffectId;
	Vector<Integer> soundpoolIDS;
	SoundPool soundPool;
	
	AssetManager assetsManager;
	AssetFileDescriptor descriptor;
	
	Context context;
	
	public SoundBox(Context context)
	{
		this.context = context;
		this.assetsManager = context.getAssets();
		this.soundPool = new SoundPool(12, AudioManager.STREAM_MUSIC, 0);
		this.descriptor = null;
		this.soundpoolIDS = new Vector<Integer>(100);
	}
	
	public void loadEffect(String fileName)
	{
		try
		{
		descriptor = assetsManager.openFd(fileName);
    	soundpoolIDS.add(new Integer(soundPool.load(descriptor, 1))); 
		}
		catch(IOException ex)
		{
			Log.v("BeatsPallete", "FILE WAS NOT LOADED");
		}
	}
	

}
