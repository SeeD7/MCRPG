package com.zeromus.mcr.component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

public class ImageBlinkingLabel extends JLabel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2723586258239723067L;
	private Timer timer;
	private ImageIcon img, img_void;
	private boolean isVoid=true;
	

	public ImageBlinkingLabel(ImageIcon img, ImageIcon img_void) {
		super(img_void);
		this.img=img;
		this.img_void=img_void;
		timer = createTimer();
	}

	private Timer createTimer() {
		ActionListener action = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(isVoid){
					setIcon(img);
					isVoid=false;
				}
				else{
					setIcon(img_void);
					isVoid=true;
				}
			}
		};
		return new Timer(200, action);

	}
	
	public void blink(){
		timer.start();
	}
	
	public void stop(){
		timer.stop();
		setIcon(img_void);
	}
}
