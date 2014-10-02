package gio.beatboxer;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

public class BeatBoxerActivity extends Activity //implements Runnable
{
    /** Called when the activity is first created. */
	
	volatile static boolean isPlaying = false;
	RelativeLayout[] subGrids4;
	RelativeLayout[] subGrids8;
	Thread t;
	
	final int WICOLOR = Color.WHITE;
	final int ACTICOLOR  = Color.rgb(0,80,200);;
	
	boolean b1IsPlaying = false;
	boolean b2IsPlaying = false;
	boolean b3IsPlaying = false;
	boolean b4IsPlaying = false;
	
	uirunner uirun;
	uirunner8 uirun8;
	
	RelativeLayout mainRL;
		
	SoundBox sb;
	
	BeatsPallete bt;
	BeatsPallete bt1;
	BeatsPallete bt2;
	BeatsPallete bt3;
	BeatsPallete bt4;
	
	BeatsGrid bg8;
	BeatsGrid bg4;
	
	BeatsControls bc;
	
	Grid4Runnable g4r;
	Grid8Runnable g8r;
	Grid12Runnable g12r;
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
   {
        //Set is Playing to False;
    	isPlaying = false;
    	
    	//INSTANTIATE MAIN CONTAINER
    	mainRL = new RelativeLayout(this);
    	mainRL.setLayoutParams(new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
    	mainRL.setBackgroundColor(Color.BLACK);
    	
    	//INSTANTIATE VIEWS
    	sb = new SoundBox(this);
    	bg8 = new BeatsGrid(this, sb);
    	bg4 = new BeatsGrid(this, sb);
    	
    	bt = new BeatsPallete(this, bg8, 6, sb);
    	bt1 = new BeatsPallete(this, bg8, 6, sb);
    	bt2 = new BeatsPallete(this, bg8, 6, sb);
    	bt3 = new BeatsPallete(this, bg8, 6, sb);
    	bt4 = new BeatsPallete(this, bg8, 6, sb);
 
    	
    	bc = new BeatsControls(this, bg8, t);
    	
    	g4r = new Grid4Runnable(this, bg4, sb);
    	g8r = new Grid8Runnable(this, bg8, sb);
    	g12r = new Grid12Runnable(this, bg8, bg4, sb);
    	
    	//ADD VIEWS
    	mainRL.addView(bc.getView());
    	mainRL.addView(bg8.getView());
    	mainRL.addView(bg4.getView());
    	mainRL.addView(bt.getView());
    	mainRL.addView(bt1.getView());
    	mainRL.addView(bt2.getView());
    	mainRL.addView(bt3.getView());
    	mainRL.addView(bt4.getView());
    	
    	//SET UP THE MAIN COMPONENTS
    	bg8.addSubGrids8();
    	bg4.addSubGrids();
    	bg4.getView().setVisibility(RelativeLayout.GONE);
    	
    	bt.getView().setId(300);
    	bt1.getView().setId(301);
    	bt2.getView().setId(302);
    	bt3.getView().setId(303);
    	bt4.getView().setId(304);
    	
    	bt.beatPaletParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
    	bt1.beatPaletParams.addRule(RelativeLayout.LEFT_OF, 300);
    	bt2.beatPaletParams.addRule(RelativeLayout.LEFT_OF, 301);
    	bt3.beatPaletParams.addRule(RelativeLayout.LEFT_OF, 302);
    	bt4.beatPaletParams.addRule(RelativeLayout.LEFT_OF, 303);
    	     	
     	bt.loadEffect("bass01.mp3");
     	bt.loadEffect("bass02.mp3");
     	bt.loadEffect("metalHit.ogg");
     	bt.loadEffect("pipeHit.wav");
     	bt.loadEffect("synth02.mp3");
     	bt.loadEffect("hiHat01.mp3");
     	
     	bt1.loadEffect("hiHat02.mp3");
     	bt1.loadEffect("percu01.mp3");
     	bt1.loadEffect("snare01.mp3");
     	bt1.loadEffect("snare02.mp3");
     	bt1.loadEffect("snare02.mp3");
     	bt1.loadEffect("synth01.mp3");
     	
     	bt2.loadEffect("elec01.mp3");
     	bt2.loadEffect("elec02.mp3");
     	bt2.loadEffect("elec03.mp3");
     	bt2.loadEffect("electronic_57.mp3");
     	bt2.loadEffect("electronic_66.mp3");
     	bt2.loadEffect("electronic_81.mp3");
     	
     	bt3.loadEffect("house_01.mp3");
     	bt3.loadEffect("house_05.mp3");
     	bt3.loadEffect("house_06.mp3");
     	bt3.loadEffect("house_09.mp3");
     	bt3.loadEffect("house_15.mp3");
     	bt3.loadEffect("house_16.mp3");
     	
     	bt4.loadEffect("house_17.mp3");
     	bt4.loadEffect("house_26.mp3");
     	bt4.loadEffect("house_31.mp3");
     	bt4.loadEffect("house_35.mp3");
     	bt4.loadEffect("house_42.mp3");
     	bt4.loadEffect("house_78.mp3");
     	
     	
     	bc.setRunnable(g8r);
     	
        //CREATE SUBGRIDS TO HOLD SOUND EFFECT IDS
    	subGrids4 = new RelativeLayout[4];
    	subGrids4[0] = (RelativeLayout) bg4.getChildAt(0);
    	subGrids4[1] = (RelativeLayout) bg4.getChildAt(1);
    	subGrids4[2] = (RelativeLayout) bg4.getChildAt(2);
    	subGrids4[3] = (RelativeLayout) bg4.getChildAt(3);
    	
    	subGrids8 = new RelativeLayout[8];
    	subGrids8[0] = bg8.getChildAt8(0,0);
    	subGrids8[1] = bg8.getChildAt8(0,1);
    	subGrids8[2] = bg8.getChildAt8(1,0);
    	subGrids8[3] = bg8.getChildAt8(1,1);
    	subGrids8[4] = bg8.getChildAt8(2,0);
    	subGrids8[5] = bg8.getChildAt8(2,1);
    	subGrids8[6] = bg8.getChildAt8(3,0);
    	subGrids8[7] = bg8.getChildAt8(3,1);
       
    	
    	//PREPARE THE APP FOR DISPLAY
    	setVolumeControlStream(AudioManager.STREAM_MUSIC);	
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        uirun = new uirunner();
        uirun8 = new uirunner8();
        super.onCreate(savedInstanceState);
        setContentView(mainRL);
    }    
    
    class uirunner implements Runnable
    {
    	long time;
    	
    	public void setTime(long time)
    	{
    		this.time = time;
    	}

		@Override
		public void run() 
		{
			
			if(time <= 667)
			{
					subGrids4[0].setBackgroundColor(ACTICOLOR);
					subGrids4[3].setBackgroundColor(WICOLOR);
			}
			else if(time > 667 && time <= 1334)
			{
					subGrids4[0].setBackgroundColor(WICOLOR);
					subGrids4[1].setBackgroundColor(ACTICOLOR);				
			}
			else if(time > 1334 && time <= 2001)
			{
					subGrids4[1].setBackgroundColor(WICOLOR);
					subGrids4[2].setBackgroundColor(ACTICOLOR);
			}
			else if(time > 2001 && time <= 2668)
			{
					subGrids4[2].setBackgroundColor(WICOLOR);
					subGrids4[3].setBackgroundColor(ACTICOLOR);
			}
			
		}
    }
    
    class uirunner8 implements Runnable
    {
    	long time;
    	
    	public void setTime(long time)
    	{
    		this.time = time;
    	}

		@Override
		public void run() 
		{
			
			if(time <= 334)
			{
				    subGrids8[7].setBackgroundColor(WICOLOR);
					subGrids8[0].setBackgroundColor(ACTICOLOR);
			}
			else if(time > 334 && time <= 667)
			{
					subGrids8[0].setBackgroundColor(WICOLOR);
					subGrids8[1].setBackgroundColor(ACTICOLOR);				
			}
			else if(time > 667 && time <= 1000)
			{
					subGrids8[1].setBackgroundColor(WICOLOR);
					subGrids8[2].setBackgroundColor(ACTICOLOR);				
			}
			else if(time > 1000 && time <= 1334)
			{
					subGrids8[2].setBackgroundColor(WICOLOR);
					subGrids8[3].setBackgroundColor(ACTICOLOR);
			}
			else if(time > 1334 && time <= 1667)
			{
					subGrids8[3].setBackgroundColor(WICOLOR);
					subGrids8[4].setBackgroundColor(ACTICOLOR);
			}
			else if(time > 1667 && time <= 2000)
			{
					subGrids8[4].setBackgroundColor(WICOLOR);
					subGrids8[5].setBackgroundColor(ACTICOLOR);
			}
			else if(time > 2000 && time <= 2334)
			{
					subGrids8[5].setBackgroundColor(WICOLOR);
					subGrids8[6].setBackgroundColor(ACTICOLOR);
			}
			else if(time > 2334 && time <= 2667)
			{
					subGrids8[6].setBackgroundColor(WICOLOR);
					subGrids8[7].setBackgroundColor(ACTICOLOR);
			}
			
		}
    }
}