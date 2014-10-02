package gio.beatboxer;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

public class BeatsGrid 
{
	Context context;
	SoundBox sb;
	RelativeLayout beatGrid;
	BeatsPallete bt;
	
	//int[]beatGridIDs8 = new int[]{-1,-1,-1,-1,-1,-1,-1,-1};
	Vector<Integer>[] beatGridIDs = new Vector[]{new Vector<Integer>(), new Vector<Integer>(), new Vector<Integer>(), new Vector<Integer>()};
    Vector<Integer>[] beatGridIDs8 = new Vector[]{new Vector<Integer>(), new Vector<Integer>(), new Vector<Integer>(), new Vector<Integer>(),
    		                                      new Vector<Integer>(), new Vector<Integer>(), new Vector<Integer>(), new Vector<Integer>()};
    		                         
  
	
	int currentEffectID;
	
	int EIGHTCOLOR = Color.WHITE;
	int BLANKCOLOR = Color.WHITE;
	int dividerColor = Color.rgb(120, 120,120);
	
	static int GRID_HEIGHT = 360;
	static int GRID_WIDTH = 360;
	
	RelativeLayout[] grids4;
	RelativeLayout.LayoutParams[] grids4Params;
	RelativeLayout[][] grids8;
	RelativeLayout.LayoutParams[][] grids8Params;
	
	public BeatsGrid(Context context, SoundBox sb)
	{
		this.sb = sb;
		this.context = context;
		beatGrid = new RelativeLayout(context);
    	RelativeLayout.LayoutParams beatGridParams = new RelativeLayout.LayoutParams(BeatsGrid.GRID_WIDTH,BeatsGrid.GRID_HEIGHT);
    	beatGridParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
    	beatGrid.setLayoutParams(beatGridParams);
    	beatGrid.setBackgroundColor(Color.GRAY);
	}
	
	public void addSubGrids()
	{
		grids4 = new RelativeLayout[4];
		grids4Params = new RelativeLayout.LayoutParams[4];
		for(int i=0; i <= 3; i++)
		{
			grids4[i] = new RelativeLayout(this.context);
			grids4Params[i] = new RelativeLayout.LayoutParams((BeatsGrid.GRID_WIDTH/2)-2, (BeatsGrid.GRID_HEIGHT/2)-2);
			switch(i)
			{
			case 0:
				grids4Params[i].addRule(RelativeLayout.ALIGN_PARENT_LEFT);
				break;
			
			case 1:
				grids4Params[i].addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
				break;
				
			case 2:
				grids4Params[i].addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
				grids4Params[i].addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
				break;
				
			case 3:
				grids4Params[i].addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
				grids4Params[i].addRule(RelativeLayout.ALIGN_PARENT_LEFT);
				break;
			}
				
			grids4[i].setLayoutParams(grids4Params[i]);
			grids4[i].setBackgroundColor(BLANKCOLOR);
			grids4[i].setId(20  + i);
			grids4[i].setOnTouchListener(new subGridListener(grids4[i]));
			beatGrid.addView(grids4[i]);
		}
	}
	
	public void addSubGrids8()
	{
		
		grids4 = new RelativeLayout[4];
		grids4Params = new RelativeLayout.LayoutParams[4];
		for(int i=0; i <= 3; i++)
		{
			grids4[i] = new RelativeLayout(this.context);
			grids4Params[i] = new RelativeLayout.LayoutParams((BeatsGrid.GRID_WIDTH/2)-2, (BeatsGrid.GRID_HEIGHT/2)-2);
			switch(i)
			{
			case 0:
				grids4Params[i].addRule(RelativeLayout.ALIGN_PARENT_LEFT);
				break;
			
			case 1:
				grids4Params[i].addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
				break;
				
			case 2:
				grids4Params[i].addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
				grids4Params[i].addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
				break;
				
			case 3:
				grids4Params[i].addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
				grids4Params[i].addRule(RelativeLayout.ALIGN_PARENT_LEFT);
				break;
			}
				
			grids4[i].setLayoutParams(grids4Params[i]);
			grids4[i].setBackgroundColor(dividerColor);
			grids4[i].setId(20  + i);
			grids4[i].setOnTouchListener(new subGridListener(grids4[i]));
			beatGrid.addView(grids4[i]);
		}
		
		grids8 = new RelativeLayout[4][2];
		grids8Params = new RelativeLayout.LayoutParams[4][2];
		for(int i=0; i <= 3; i++)
		{
			grids8[i][0] = new RelativeLayout(this.context);
			grids8[i][1] = new RelativeLayout(this.context);
			
			grids8Params[i][0] = new RelativeLayout.LayoutParams((BeatsGrid.GRID_WIDTH/4)-2, (BeatsGrid.GRID_HEIGHT/2)-2);
			grids8Params[i][1] = new RelativeLayout.LayoutParams((BeatsGrid.GRID_WIDTH/4)-2, (BeatsGrid.GRID_HEIGHT/2)-2);
			
			if(i <= 1)
			{
				grids8Params[i][0].addRule(RelativeLayout.ALIGN_PARENT_LEFT);
				grids8Params[i][1].addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			}
			else
			{
				grids8Params[i][0].addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
				grids8Params[i][1].addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			}
			
			
			grids8[i][0].setLayoutParams(grids8Params[i][0]);
			grids8[i][1].setLayoutParams(grids8Params[i][1]);
			
			Log.v("APPMAN", "" + 110 + (2*1));
			Log.v("APPMAN", "" + 110 + ((2*1) + 1));
			
			System.out.println(110 + (2*i));
			System.out.println(110 + ((2*i) + 1));
			
			grids8[i][0].setBackgroundColor(BLANKCOLOR);
			grids8[i][1].setBackgroundColor(BLANKCOLOR);
			
			grids8[i][0].setId(110 + (2*i));
			grids8[i][1].setId(110 + ((2*i) + 1));
			
			
			
			grids8[i][0].setOnTouchListener(new subGridListener8(grids8[i][0]));
			grids8[i][1].setOnTouchListener(new subGridListener8(grids8[i][1]));
			
			grids4[i].addView(grids8[i][0]);
			grids4[i].addView(grids8[i][1]);
		}		
	}
	
	public void removeSubGrids()
	{
		this.beatGrid.setVisibility(RelativeLayout.GONE);
	}
	
	public RelativeLayout getView()
	{
		return this.beatGrid;
	}
	
	public RelativeLayout getChildAt(int index)
	{
		return (RelativeLayout) this.beatGrid.getChildAt(index);
	}
	
	public RelativeLayout getChildAt8(int index1, int index2)
	{
	      RelativeLayout subgrid = (RelativeLayout) this.beatGrid.getChildAt(index1);
	      return (RelativeLayout)subgrid.getChildAt(index2);
	}
	
    private class subGridListener implements View.OnTouchListener
    {
    	RelativeLayout caller;
    	
    	public subGridListener(RelativeLayout caller)
    	{
    		this.caller = caller;
    	}
		
		@Override
		public boolean onTouch(View v, MotionEvent event) 
		{
			if(event.getAction() == MotionEvent.ACTION_DOWN)
			{
				int index = caller.getId() - 20;
				beatGridIDs[index].add(new Integer(sb.currentSoundEffectId));
			}
			return false;
		}  
    }
    
    private class subGridListener8 implements View.OnTouchListener
    {
    	RelativeLayout caller;
    	
    	public subGridListener8(RelativeLayout caller)
    	{
    		this.caller = caller;
    	}

		@Override
		public boolean onTouch(View v, MotionEvent event) 
		{
			if(event.getAction() == MotionEvent.ACTION_DOWN)
			{
				int index = caller.getId() - 110;
				beatGridIDs8[index].add(new Integer(sb.currentSoundEffectId));

			}
			return false;
		}    	
    }

}
