package icys.java;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import static icys.java.Utilities.*;

public class SelectionScreen implements Screen {
	LabelButton selectLabel [], back, help;
	BoxButton box [];
	
	public SelectionScreen () {
		
		back = new LabelButton ("back", font, Color.WHITE, lightblue, blue, aqua);
		help = new LabelButton ("help", font, Color.WHITE, lightblue, blue, aqua);
		
		selectLabel = new LabelButton [4];
		selectLabel [0] = new LabelButton ("game", font,
				Color.WHITE, lightblue, blue, aqua);
		selectLabel [1] = new LabelButton ("simulation",  font,
				Color.WHITE, lightblue, blue, aqua);
		selectLabel [2] = new LabelButton ("extinction",  font,
				Color.WHITE, lightblue, blue, aqua);
		selectLabel [3] = new LabelButton ("equilibrium",  font,
				Color.WHITE, lightblue, blue, aqua);
		
		box = new BoxButton [4];
		for (int i = 0 ; i < 4 ; i++) {
			box [i] = new BoxButton ((width - 3 * border) / 2,
					(height - 3 * border - offset) / 2, aqua, Color.WHITE);
		}
		
		main.add (back);
		main.add (help);
		for (int i = 0 ; i < 4 ; i++){
			main.add(selectLabel [i]);
		}
		
		hide ();
	}

	@Override
	public void draw(Graphics g) {
		for (int i = 0 ; i < 4 ; i++)
			box [i].draw(g);
	}

	@Override
	public void show() {
		back.setVisible (true);
		help.setVisible (true);
		for (int i = 0 ; i < 4; i ++)
			selectLabel [i].setVisible(true);
	}

	@Override
	public void hide() {
		back.setVisible (false);
		help.setVisible (false);
		for (int i = 0 ; i < 4; i ++)
			selectLabel [i].setVisible (false);
	}

	@Override
	public void applyGraphics (Graphics g) {
		// TODO Auto-generated method stub
		back.applyGraphics(g);
		help.applyGraphics(g);
		
		back.setLocation(back.getWidth(), (offset + border) / 2);
		help.setLocation(width - help.getWidth(), (offset + border) / 2);
		
		for (int i = 0 ; i < 4 ; i++)
			selectLabel [i].applyGraphics(g);
		
		selectLabel [0].setLocation (
				(width + border) / 4, 
				(height - offset + border) / 4 + offset);
		selectLabel [1].setLocation ( // (3A - 9B)/4+8B/4
				(3 * width - border) / 4, 
				(height - offset + border) / 4 + offset);
		selectLabel [2].setLocation (
				(width + border) / 4,
				(3 * height - 3 * offset - border) / 4 + offset);
		selectLabel [3].setLocation ( // (3A-3C-B)/4
				(3 * width - border) / 4,
				(3 * height - 3 * offset - border) / 4 + offset);
		
		box [0].setLocation (
				(width + border) / 4, 
				(height - offset + border) / 4 + offset);
		box [1].setLocation ( // (3A - 9B)/4+8B/4
				(3 * width - border) / 4, 
				(height - offset + border) / 4 + offset);
		box [2].setLocation (
				(width + border) / 4,
				(3 * height - 3 * offset - border) / 4 + offset);
		box [3].setLocation ( // (3A-3C-B)/4
				(3 * width - border) / 4,
				(3 * height - 3 * offset - border) / 4 + offset);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub	
		int x = e.getX ();
		int y = e.getY ();
		if (back.contains (x, y)) {
			back.setState (1);
			main.repaint ();
		}
		else if (help.contains (x, y)) {
			help.setState (1);
			main.repaint ();
		}
		else {
			for (int i = 0 ; i < 4; i ++) {
				if (box [i].contains (x, y)) {
					box [i].setState (1);
					selectLabel [i].setState (1);
					main.repaint ();
					i = 4;
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if (back.getState () == 1) {
			back.setState(0);
			main.setScreen(StartScreen);
		}
		else if (help.getState() == 1) {
			help.setState (0);
			main.setScreen (HelpScreen);
		}
			
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX ();
		int y = e.getY ();
		if (back.contains (x, y))
			back.setState (1);
		else 
			back.setState (0);
		if (help.contains (x, y))
			help.setState (1);
		else
			help.setState (0);
		for (int i = 0 ; i < 4; i ++) {
			if (box [i].contains (x, y)) {
				box [i].setState (1);
				selectLabel [i].setState (1);
			}
			else {
				box [i].setState (0);
				selectLabel [i].setState (0);
			}
		}
		main.repaint ();
	}
}