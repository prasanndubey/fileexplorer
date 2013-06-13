package com.example.file.explorer;

import java.io.*;
import java.util.*;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;
public class MainActivity extends ListActivity implements OnClickListener {
  private List<String> item = null;
	private List<String> path = null;
	private String root="";
	private TextView myPath;
	File f = new File(root);
	File[] files = f.listFiles();
	Button name;
	Button size;
	View.OnClickListener sort_name = new View.OnClickListener(){
		@Override
		public void onClick(View v) {
		int i,j;
		for(i=0;i<files.length;i++)
		{
		for(j=i;j<files.length;j++)
		{
			if(files[j].compareTo(files[j+1])>0)
		{
		File t=files[j];
		files[j]=files[j+1];
		files[j+1]=t;
		}
		}
		}
		}
		};

		View.OnClickListener sort_size= new View.OnClickListener() {
		@Override
		public void onClick(View v) {
		int i,j;
		for(i=0;i<files.length;i++)
		{
		for(j=0;j<files.length;j++)
		{
		if( files[j].length()>files[j+1].length())
		{
		File t= files[j];
		files[j]=files[j+1];
		files[j+1]=t;
		}
		}
		}
		}
		};
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=(Button)findViewById(R.id.button1);
        size=(Button)findViewById(R.id.button2);
        name.setOnClickListener(sort_name);
        size.setOnClickListener(sort_size);
        myPath = (TextView)findViewById(R.id.textView1);
        getDir(root);
    } 
    public void getDir(String dirPath)
    {
     myPath.setText("Location: " + dirPath);   
     item = new ArrayList<String>();
     path = new ArrayList<String>();  
     if(!dirPath.equals(root))
     {
     item.add(root);
     path.add(root);
     item.add("/");
     path.add(f.getParent());
     }
     for(int i=0; i < files.length; i++)
     {
     File file = files[i];
     path.add(file.getPath());
     if(file.isDirectory())
     item.add(file.getName() + "/");
     else
     item.add(file.getName());
     ArrayAdapter<String> fileList =
     new ArrayAdapter<String>(this, R.layout.row, item);
     setListAdapter(fileList);    
    }
    }
    
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
}
