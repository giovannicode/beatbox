package gio.beatboxer;

import android.content.Context;
import android.os.SystemClock;

public class Grid8Runnable implements Runnable
{
	boolean b1IsPlaying = false;
	boolean b2IsPlaying = false;
	boolean b3IsPlaying = false;
	boolean b4IsPlaying = false;
	boolean b5IsPlaying = false;
	boolean b6IsPlaying = false;
	boolean b7IsPlaying = false;
	boolean b8IsPlaying = false;
	
	Context context;
	BeatBoxerActivity ba;
	BeatsGrid bg;
	BeatsPallete bt;
	
	SoundBox sb;
		
	public Grid8Runnable(Context context, BeatsGrid bg, SoundBox sb)
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
    			if(time <= 334)
    			{
    				b8IsPlaying = false;
    				if(!b1IsPlaying)
    				{
    					ba.uirun8.setTime(time);
    					ba.runOnUiThread(ba.uirun8);
    					if(bg.beatGridIDs8[0].size() > 0)
    					{
    						for(int i = 0; i <= bg.beatGridIDs8[0].size() - 1; i++)
    						{
    						   sb.soundPool.play(bg.beatGridIDs8[0].get(i), 1, 1, 0, 0, 1);
    						}
    					}
    					b1IsPlaying = true;
    				}
    			}
    			else if(time > 334 && time <= 667)
    			{
    				b1IsPlaying = false;
    			
    			   if(!b2IsPlaying)
    			   {
    				   ba.uirun8.setTime(time);
   					   ba.runOnUiThread(ba.uirun8);
    					
    					if(bg.beatGridIDs8[1].size() > 0)
    					{
    						for(int i = 0; i <= bg.beatGridIDs8[1].size() - 1; i++)
    						{
    						   sb.soundPool.play(bg.beatGridIDs8[1].get(i), 1, 1, 0, 0, 1);
    						}
    					}
    					
    				   b2IsPlaying = true;
    			   }
    			}
    			else if(time > 667 && time <= 1000)
    			{
    				b2IsPlaying = false;			   
    			   if(!b3IsPlaying)
    			   {
    				   ba.uirun8.setTime(time);
   					   ba.runOnUiThread(ba.uirun8);
    					
    					if(bg.beatGridIDs8[2].size() > 0)
    					{

    						for(int i = 0; i <= bg.beatGridIDs8[2].size() - 1; i++)
    						{
    						   sb.soundPool.play(bg.beatGridIDs8[2].get(i), 1, 1, 0, 0, 1);
    						}
    					}

    				   b3IsPlaying = true;
    			   }
    			     
    			}
    			else if(time > 1000 && time <= 1334)
    			{
    				b3IsPlaying = false;
    			
    			   if(!b4IsPlaying)
    			   {
    				   ba.uirun8.setTime(time);
   					   ba.runOnUiThread(ba.uirun8);
    					
    					if(bg.beatGridIDs8[3].size() > 0)
    					{
    						for(int i = 0; i <= bg.beatGridIDs8[3].size() - 1; i++)
    						{
    						   sb.soundPool.play(bg.beatGridIDs8[3].get(i), 1, 1, 0, 0, 1);
    						}
    					}

    				   b4IsPlaying = true;
    			   }
    			}
    			else if(time > 1334 && time <= 1667)
    			{
    				b4IsPlaying = false;
    			
    			   if(!b5IsPlaying)
    			   {
    				   ba.uirun8.setTime(time);
   					   ba.runOnUiThread(ba.uirun8);
    					
    					if(bg.beatGridIDs8[4].size() > 0)
    					{
    						for(int i = 0; i <= bg.beatGridIDs8[4].size() - 1; i++)
    						{
    						   sb.soundPool.play(bg.beatGridIDs8[4].get(i), 1, 1, 0, 0, 1);
    						}
    					}

    				   b5IsPlaying = true;
    			   }
    			}
    			else if(time > 1667 && time <= 2000)
    			{
    				b5IsPlaying = false;
    			
    			   if(!b6IsPlaying)
    			   {
    				   ba.uirun8.setTime(time);
   					   ba.runOnUiThread(ba.uirun8);
    					
    					if(bg.beatGridIDs8[5].size() > 0)
    					{
    						for(int i = 0; i <= bg.beatGridIDs8[5].size() - 1; i++)
    						{
    						   sb.soundPool.play(bg.beatGridIDs8[5].get(i), 1, 1, 0, 0, 1);
    						}
    					}
    				   b6IsPlaying = true;
    			   }
    			}
    			else if(time > 2000 && time <= 2334)
    			{
    				b6IsPlaying = false;
    			
    			   if(!b7IsPlaying)
    			   {
    				   ba.uirun8.setTime(time);
   					   ba.runOnUiThread(ba.uirun8);
    					
    					if(bg.beatGridIDs8[6].size() > 0)
    					{
    						for(int i = 0; i <= bg.beatGridIDs8[6].size() - 1; i++)
    						{
    						   sb.soundPool.play(bg.beatGridIDs8[6].get(i), 1, 1, 0, 0, 1);
    						}
    					}

    				   b7IsPlaying = true;
    			   }
    			}
    			else if(time > 2334 && time <= 2667)
    			{
    				b7IsPlaying = false;
    			
    			   if(!b8IsPlaying)
    			   {
    				   ba.uirun8.setTime(time);
   					   ba.runOnUiThread(ba.uirun8);
    					
    					if(bg.beatGridIDs8[7].size() > 0)
    					{
    						for(int i = 0; i <= bg.beatGridIDs8[7].size() - 1; i++)
    						{
    						   sb.soundPool.play(bg.beatGridIDs8[7].get(i), 1, 1, 0, 0, 1);
    						}
    					}
    				   b8IsPlaying = true;
    			   }
    			}
        }	
	}

}
