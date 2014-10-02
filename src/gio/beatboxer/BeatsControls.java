package gio.beatboxer;

import java.util.Vector;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.RelativeLayout;

public class BeatsControls 
{
	RelativeLayout recorderControls;
	Button play;
	Button stop;
	Button clear;
	Button eightNotes;
	Button syncSound;
	BeatsGrid bg;
	Context context;
	Thread t;
	Runnable gridRunner;
	boolean gridSwitch = true;
	boolean simulIsPlaying = false;
	
	public BeatsControls(Context context, BeatsGrid bg, Thread t)
	{
		//RECORDER CONTROLS
		
		this.bg = bg;
		this.context = context;
    	recorderControls = new RelativeLayout(context);
    	RelativeLayout.LayoutParams recorderControlsParams = new RelativeLayout.LayoutParams(360,120);
    	recorderControlsParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
    	recorderControls.setLayoutParams(recorderControlsParams);
    	recorderControls.setBackgroundColor(Color.BLUE);
    	
    	//RECORDER CONTROLS ELEMENTS
    	play = new Button(context);
    	RelativeLayout.LayoutParams playParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
    	playParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
    	play.setLayoutParams(playParams);
    	play.setId(200);
    	play.setText("PLAY");
    	
    	stop = new Button(context);
    	RelativeLayout.LayoutParams stopParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
    	stopParams.addRule(RelativeLayout.RIGHT_OF, 200);
    	stop.setLayoutParams(stopParams);
    	stop.setId(201);
    	stop.setText("STOP");
    	
    	clear = new Button(context);
    	RelativeLayout.LayoutParams clearParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    	clearParams.addRule(RelativeLayout.RIGHT_OF, 201);
    	clear.setLayoutParams(clearParams);
    	clear.setId(202);
    	clear.setText("CLEAR");
    	
    	eightNotes = new Button(context);
    	RelativeLayout.LayoutParams eightNotesParams = new RelativeLayout.LayoutParams(210, LayoutParams.WRAP_CONTENT);
    	eightNotesParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
    	eightNotesParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
    	eightNotes.setLayoutParams(eightNotesParams);
    	eightNotes.setId(203);
    	eightNotes.setText("Quarter Notes");
    	
    	syncSound = new Button(context);
    	RelativeLayout.LayoutParams syncSoundParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    	syncSoundParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
    	syncSoundParams.addRule(RelativeLayout.RIGHT_OF, 203);
    	syncSound.setLayoutParams(syncSoundParams);
    	syncSound.setText("SYNC");
    	
    	recorderControls.addView(play);
    	recorderControls.addView(stop);
    	recorderControls.addView(clear);
    	recorderControls.addView(eightNotes);
    	recorderControls.addView(syncSound);
    	
    	play.setOnClickListener(new playListener());
    	stop.setOnClickListener(new stopListener());
    	clear.setOnClickListener(new clearListener());
    	eightNotes.setOnClickListener(new switchListener());
    	syncSound.setOnClickListener(new simulListener());
	}
	
	public void setRunnable(Runnable rn)
	{
		this.gridRunner = rn;
	}
	
	public void setBeatsGrid(BeatsGrid bg)
	{
		this.bg = bg;
	}
	
	public BeatsGrid getBG()
	{
		return this.bg;
	}
	
	public RelativeLayout getView()
	{
		return this.recorderControls;
	}
	
	private class playListener implements View.OnClickListener
    {    	
		@Override
		public void onClick(View v) 
		{
			BeatBoxerActivity.isPlaying = true;
			//t = new Thread((Runnable) context);
			t = new Thread(gridRunner);
			t.start();
			play.setEnabled(false);
			eightNotes.setEnabled(false);
			syncSound.setEnabled(false);
		}
    	
    }
    
    private class clearListener implements View.OnClickListener
    {
		@Override
		public void onClick(View v) 
		{
			bg.beatGridIDs = new Vector[]{new Vector<Integer>(),new Vector<Integer>(),new Vector<Integer>(),new Vector<Integer>()};
			bg.beatGridIDs8 = new Vector[]{new Vector<Integer>(), new Vector<Integer>(), new Vector<Integer>(), new Vector<Integer>(),
                    new Vector<Integer>(), new Vector<Integer>(), new Vector<Integer>(), new Vector<Integer>()};
		}
    }
    
    private class stopListener implements View.OnClickListener
    {

		@Override
		public void onClick(View v) 
		{
			BeatBoxerActivity.isPlaying = false;
			while(true)
			{
				try
				{
					t.join();
					break;
				}
				catch(InterruptedException e)
				{
					//retry ?
				}
			}
			play.setEnabled(true);
			eightNotes.setEnabled(true);
			syncSound.setEnabled(true);
			
		}	
    }
    
    private class switchListener implements View.OnClickListener
    {

		@Override
		public void onClick(View v) 
		{
			// TODO Auto-generated method stub
			if(gridSwitch)
			{
				BeatBoxerActivity ba = (BeatBoxerActivity)context;
				BeatsControls.this.setRunnable(ba.g4r);
				bg.removeSubGrids();
				setBeatsGrid(ba.bg4);
				bg.getView().setVisibility(RelativeLayout.VISIBLE);
				ba.bt.setBeatsGrid(bg);
				gridSwitch = false;
				Button rl = (Button) v;
				rl.setText("Eight Notes");
			}
			else
			{
				BeatBoxerActivity ba = (BeatBoxerActivity)context;
				BeatsControls.this.setRunnable(ba.g8r);
				bg.removeSubGrids();
				setBeatsGrid(ba.bg8);
				bg.getView().setVisibility(RelativeLayout.VISIBLE);
				ba.bt.setBeatsGrid(bg);
				Button rl = (Button) v;
				rl.setText("Quarter Notes");
				gridSwitch = true;
			}
			
		}
    	
    }
    
    private class simulListener implements View.OnClickListener
    {

		@Override
		public void onClick(View v) 
		{
			// TODO Auto-generated method stub
			BeatBoxerActivity.isPlaying = true;
			//t = new Thread((Runnable) context);
			BeatBoxerActivity ba = (BeatBoxerActivity)context;
			
			BeatsControls.this.setRunnable(ba.g8r);
			bg.removeSubGrids();
			setBeatsGrid(ba.bg8);
			bg.getView().setVisibility(RelativeLayout.VISIBLE);
			ba.bt.setBeatsGrid(bg);
			
			BeatsControls.this.setRunnable(ba.g12r);
			t = new Thread(gridRunner);
			t.start();
			play.setEnabled(false);
			eightNotes.setEnabled(false);
			
			simulIsPlaying = true;
			
			/*************************/
			gridSwitch = true;
			eightNotes.setText("Quarter Notes");
			/************************/
		}
    	
    }

}
