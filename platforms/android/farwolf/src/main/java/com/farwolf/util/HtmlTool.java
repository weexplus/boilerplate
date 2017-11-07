package com.farwolf.util;

import java.util.LinkedList;
import java.util.List;

import android.text.Html;
import android.text.Spanned;

public class HtmlTool {


	List<Ct> order=new LinkedList<Ct>();	
	public  Spanned ToSpannedText()
	{
		String s="";
		for(Ct ct:order)		
		{
			if (ct.isEnte) {
				s+="<br></br>";
			} else {
				s+="<font "+ct.color+">"+ct.text+"</font>";
			}
			
			
		}
//		Log.i("s",s);
		
		return Html.fromHtml(s);

	}
	
	public void AddColor(String color,String text)
	{						
		String v=" color='"+color+"' ";
		Ct ct=	new Ct();
		ct.color=v;
		ct.text=text;
		order.add(ct);
		
	}
	public void AddEnter()
	{						
		Ct ct=	new Ct();
		ct.isEnte=true;
		order.add(ct);
		
	}
	
	public class Ct
	{
		
		public String text;
		public String color;
		public boolean isEnte=false;
	}
	
	

	
	
	
	
}
