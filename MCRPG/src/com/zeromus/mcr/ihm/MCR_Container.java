package com.zeromus.mcr.ihm;

import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;

public abstract class MCR_Container extends Container implements MouseListener {
	
	private static final long serialVersionUID = 1L;
	protected Frame_Menu parent;

	public abstract void keyPressed(KeyEvent arg0);
	
	public Frame_Menu getParent(){
		return parent;
	}

}
