package gio.beatboxer;

import android.content.Context;
import android.os.SystemClock;

public class Grid4Runnable implements Runnable
{
	boolean b1IsPlaying = false;
	boolean b2IsPlaying = false;
	boolean b3IsPlaying = false;
	boolean b4IsPlaying = false;
	
	Context context;
	BeatBoxerActivity ba;
	BeatsGrid bg;
	BeatsPallete bt;
	
	SoundBox sb;
	
	public Grid4Runnable(Context context, BeatsGrid bg, SoundBox sb)
	{
		this.context = context;
		ba = (BeatBoxerActivity) this.context;
		this.bg = bg;
		this.sb = sb;
	}

	@Override
	public void run() 
	{		
		   while(BeatBoxerActivity.isPlaying) 
	        {
	    			long time = SystemClock.uptimeMillis() % 2668L;
	    			if(time <= 667)
	    			{
	    				b4IsPlaying = false;
	    				if(!b1IsPlaying)
	    				{
	    					ba.uirun.setTime(time);
	    					ba.runOnUiThread(ba.uirun);
	    					if(bg.beatGridIDs[0].size() > 0)
	    					{
	    						for(int i = 0; i <= bg.beatGridIDs[0].size() - 1; i++)
	    						{
	    						   sb.soundPool.play(bg.beatGridIDs[0].get(i), 1, 1, 0, 0, 1);
	    						}
	    					}
	    					b1IsPlaying = true;
	    				}
	    			}
	    			else if(time > 667 && time <= 1334)
	    			{
	    				b1IsPlaying = false;
	    			
	    			   if(!b2IsPlaying)
	    			   {
	    				   ba.uirun.setTime(time);
	   					   ba.runOnUiThread(ba.uirun);
	    					
	   					    if(bg.beatGridIDs[1].size() > 0)
	    					{
	    						sb.soundPool.play(bg.beatGridIDs[1].get(0), 1, 1, 0, 0, 1);
	    					}
	    					
	    				   b2IsPlaying = true;
	    			   }
	    			}
	    			else if(time > 1334 && time <= 2001)
	    			{
	    				b2IsPlaying = false;			   
	    			   if(!b3IsPlaying)
	    			   {
	    				   ba.uirun.setTime(time);
	   					   ba.runOnUiThread(ba.uirun);
	    					
	    					if(bg.beatGridIDs[2].size() > 0)
	    					{
	    						sb.soundPool.play(bg.beatGridIDs[2].get(0), 1, 1, 0, 0, 1);
	    					}

	    				   b3IsPlaying = true;
	    			   }
	    			     
	    			}
	    			else if(time > 2001 && time <= 2668)
	    			{
	    				b3IsPlaying = false;
	    			
	    			   if(!b4IsPlaying)
	    			   {
	    				   ba.uirun.setTime(time);
	   					   ba.runOnUiThread(ba.uirun);
	    					
	    					if(bg.beatGridIDs[3].size() > 0)
	    					{
	    						sb.soundPool.play(bg.beatGridIDs[3].get(0), 1, 1, 0, 0, 1);
	    					}

	    				   b4IsPlaying = true;
	    			   }
	    			}
	        }	
	}

}
