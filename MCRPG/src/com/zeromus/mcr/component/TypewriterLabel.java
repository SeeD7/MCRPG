package com.zeromus.mcr.component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

import com.zeromus.mcr.ihm.Didactitiel;

public class TypewriterLabel extends JLabel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2723586258239723067L;
	private boolean writing=false;
	private String text;
	private Timer timer;
	private int position=1;
	private Didactitiel parent;

	public TypewriterLabel(String text, Didactitiel parent) {
		super(text.substring(0, 1));
		this.parent=parent;
		this.text=text;
		timer = createTimer();
		timer.start();
	}
	
	public TypewriterLabel(String text, int Constant, Didactitiel parent) {
		super(text.substring(0, 1),Constant);
		this.parent=parent;
		this.text=text;
		timer = createTimer();
		timer.start();
	}

	private Timer createTimer() {
		ActionListener action = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				setText(text.substring(0, position));
				position++;
				if(position>text.length()){
					writing=false;
					timer.stop();
					parent.finishWriting();
				}
			}
		};
		return new Timer(50, action);

	}
	
	public void write(String text){
		this.text=text;
		this.setText(text.substring(0, 1));
		position=1;
		writing=true;
		timer.start();
	}
	
	public void stopWriting(){
		this.setText(text);
		writing=false;
		timer.stop();
	}

	public boolean isWriting() {
		return writing;
	}
}
