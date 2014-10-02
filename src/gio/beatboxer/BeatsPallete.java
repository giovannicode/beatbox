package gio.beatboxer;

import java.io.IOException;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class BeatsPallete 
{
	int[] effectsIDs;
	SoundPool sndPoolPalleteEffects;
	
	AssetManager sndEffectsManager;
	AssetFileDescriptor descriptor;
	
	Context context;
	
	RelativeLayout beatPalet;
	RelativeLayout.LayoutParams beatPaletParams;
	Button[] paletBtns;
	
	int[] effectsIDs2;
	SoundPool sndPoolGridEffects;
	SoundBox sb;
	
	BeatsGrid bg;
	
	int index = 0;
	
	static int instanceTotal = 0;
	int instanceNumber;
	
	int effectsSize;
		
	BeatsPallete(Context context, BeatsGrid bg, int effectsSize, SoundBox sb)
	{
		this.sb = sb;
		
		
		this.context = context;
		this.bg = bg;
		this.loadPallete();
		this.loadBtns(effectsSize);
		this.loadEffectsArray(effectsSize);
		this.loadEffectsListeners(effectsSize);
	    instanceTotal++;
	    this.instanceNumber = instanceTotal;
		this.effectsSize = effectsSize;
		
	}
	
	private void loadPallete()
	{
		this.beatPalet = new RelativeLayout(this.context);
		this.beatPaletParams = new RelativeLayout.LayoutParams(80, 480);
		beatPalet.setLayoutParams(beatPaletParams);
		beatPalet.setBackgroundColor(Color.rgb(100, 0, 0));
	}
	
	private void loadBtns(int effectsSize)
	{
		this.paletBtns = new Button[effectsSize];
		for(int i = 0; i <= effectsSize -1; i++)
		{
			this.paletBtns[i] = new Button(this.context);
	    	RelativeLayout.LayoutParams buttonParams = new RelativeLayout.LayoutParams(80, 80);
	    	
	    	this.paletBtns[i].setId(i+1);
	    	if(i > 0)
	    	{
	    	   buttonParams.addRule(RelativeLayout.BELOW, i);
	    	}
	    	
	    	this.paletBtns[i].setLayoutParams(buttonParams);
		}
		
		for(int i = 0; i <= effectsSize -1; i++)
		{
			this.beatPalet.addView(paletBtns[i]);
		}	
	}
	
	public void loadEffectsArray(int effectsSize)
	{
		this.sndEffectsManager = context.getAssets();
		this.sndPoolPalleteEffects = new SoundPool(12, AudioManager.STREAM_MUSIC, 0);
		this.descriptor = null;
		
		this.effectsIDs = new int[effectsSize];
		for(int i = 0; i <= effectsSize -1; i++)
		{
			this.effectsIDs[i] = -1;
		}
		
		this.sndPoolGridEffects = new SoundPool(12, AudioManager.STREAM_MUSIC, 0);
		this.effectsIDs2 = new int[effectsSize];
	}
	
	public void loadEffectsListeners(int effectsSize)
	{
		for(int i = 0; i <= effectsSize -1; i++)
		{
			this.paletBtns[i].setOnClickListener(new EffectsListener(this.paletBtns[i]));
		}
		
	}
		
	public void loadEffect(String fileName)
	{		
		try
		{
		descriptor = sndEffectsManager.openFd(fileName);
    	this.effectsIDs[index] = sndPoolPalleteEffects.load(descriptor, 1); 
    	this.effectsIDs2[index] = sndPoolGridEffects.load(descriptor, 1); 
    	index++;
    	
    	sb.loadEffect(fileName);
		}
		
		catch(IOException ex)
		{
			Log.v("BeatsPallete", fileName + "WAS NOT LOADED");
		}
	}
	
	public RelativeLayout getView()
	{
		return this.beatPalet;
	}
	
	public void setBeatsGrid(BeatsGrid bg)
	{
		this.bg = bg;
	}
	
	public void setId(int id)
	{
		this.beatPalet.setId(id);
	}
	
    private class EffectsListener implements View.OnClickListener
    {
    	Button btnEffect;
    	
    	public EffectsListener(Button btnEffect)
    	{
    		this.btnEffect = btnEffect;
    	}

		@Override
		public void onClick(View v) 
		{
				sndPoolPalleteEffects.play(effectsIDs[this.btnEffect.getId()-1], 1, 1, 0, 0, 1);
				sb.currentSoundEffectId = sb.soundpoolIDS.get((this.btnEffect.getId() - 1) + ((instanceNumber-1) * effectsSize));
		}
    }
}
