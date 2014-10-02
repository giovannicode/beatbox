package gio.beatboxer;

import android.content.Context;
import android.os.SystemClock;

public class Grid12Runnable implements Runnable
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
	BeatsGrid bg1;
	BeatsGrid bg2;
	BeatsPallete bt;
	
	SoundBox sb;
		
	public Grid12Runnable(Context context, BeatsGrid bg1, BeatsGrid bg2, SoundBox sb)
	{
		this.context = context;
		ba = (BeatBoxerActivity) this.context;
		this.bg1 = bg1;
		this.bg2 = bg2;
		this.sb = sb;
	}

	@Override
	public void run() 
	{
		// TODO Auto-generated method stub
        /*while(BeatBoxerActivity.isPlaying) 
        {
    			long time = SystemClock.uptimeMillis() % 2668L;
    			if(time <= 334)
    			{
    				b8IsPlaying = false;
    				if(!b1IsPlaying)
    				{
    					ba.uirun8.setTime(time);
    					ba.runOnUiThread(ba.uirun8);
    					if(bg1.beatGridIDs8[0] != -1)
    					{
    						sb.soundPool.play(bg1.beatGridIDs8[0], 1, 1, 0, 0, 1);
    					}
    					if(bg2.beatGridIDs[0].size() > 0)
    					{
    						sb.soundPool.play(bg2.beatGridIDs[0].get(0), 1, 1, 0, 0, 1);
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
    					
    					if(bg1.beatGridIDs8[1] != -1)
    					{
    						sb.soundPool.play(bg1.beatGridIDs8[1], 1, 1, 0, 0, 1);
    					}
    					else
    					{

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
    					
    					if(bg1.beatGridIDs8[2] != -1)
    					{
    						sb.soundPool.play(bg1.beatGridIDs8[2], 1, 1, 0, 0, 1);
    					}
    					if(bg2.beatGridIDs[0].size() > 0)
    					{
    						sb.soundPool.play(bg2.beatGridIDs[1].get(0), 1, 1, 0, 0, 1);
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
    					
    					if(bg1.beatGridIDs8[3] != -1)
    					{
    						sb.soundPool.play(bg1.beatGridIDs8[3], 1, 1, 0, 0, 1);
    					}
    					else
    					{
    						
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
    					
    					if(bg1.beatGridIDs8[4] != -1)
    					{
    						sb.soundPool.play(bg1.beatGridIDs8[4], 1, 1, 0, 0, 1);
    					}
    					if(bg2.beatGridIDs[0].size() > 0)
    					{
    						sb.soundPool.play(bg2.beatGridIDs[2].get(0), 1, 1, 0, 0, 1);
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
    					
    					if(bg1.beatGridIDs8[5] != -1)
    					{
    						sb.soundPool.play(bg1.beatGridIDs8[5], 1, 1, 0, 0, 1);
    					}
    					else
    					{
    						
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
    					
    					if(bg1.beatGridIDs8[6] != -1)
    					{
    						sb.soundPool.play(bg1.beatGridIDs8[6], 1, 1, 0, 0, 1);
    					}
    					if(bg2.beatGridIDs[0].size() > 0)
    					{
    						sb.soundPool.play(bg2.beatGridIDs[3].get(0), 1, 1, 0, 0, 1);
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
    					
    					if(bg1.beatGridIDs8[7] != -1)
    					{
    						sb.soundPool.play(bg1.beatGridIDs8[7], 1, 1, 0, 0, 1);
    					}
    					else
    					{
    						
    					}
    				   b8IsPlaying = true;
    			   }
    			}
        }	*/
	}
}
