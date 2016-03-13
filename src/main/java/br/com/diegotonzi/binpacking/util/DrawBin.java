package br.com.diegotonzi.binpacking.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;

import br.com.diegotonzi.binpacking.model.Bin;
import br.com.diegotonzi.binpacking.model.Item;

public class DrawBin extends JComponent {

	private Bin bin = null;
	private final int SCALE = 5;
	private static final long serialVersionUID = 1L;

	public DrawBin() {
		
		
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		this.setPreferredSize(new Dimension(600, 600));
		frame.getContentPane().add(this, BorderLayout.CENTER);
		
		/*
		JScrollPane scrollPane = new JScrollPane(this);  
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);  
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);  
		frame.getContentPane().add(scrollPane);  

		frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		*/
		
		frame.pack();
		frame.setVisible(true);
	}
	
	public void drawing(Bin bin){
		//this.clear();
		this.bin= bin;
		repaint();
	}
	
	public void clear(){
		if(bin != null && bin.getItens() != null){
			this.bin.getItens().clear();
			repaint();
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	
		if(bin != null && bin.getItens() != null){
			
			g.drawString("Bin items: " +  bin.getItens().size(), 5 * SCALE, (bin.getLength().intValue() + 10) * SCALE);
			g.drawString("Bin sizes: [ Width: " + bin.getWidth() + ", Length: " + bin.getLength() + ", Height: " + bin.getHeight() + " ]", 5 * SCALE, (bin.getLength().intValue() + 14) * SCALE);
			g.drawString("--------------------------------------------------------------------------------------------------------------", 5 * SCALE, (bin.getLength().intValue() + 16) * SCALE);
			//int count = 20;
			for (Item item : bin.getItens()) {
				//Color randomColor = new Color((float)Math.random(), (float)Math.random(), (float)Math.random());
				g.drawRect(item.getPoint().getWidth().getBegin().intValue() * SCALE, item.getPoint().getLength().getBegin().intValue() * SCALE, item.getWidth().intValue() * SCALE, item.getLength().intValue() * SCALE);  
				g.setColor(Color.BLACK); 
				//g.setColor(randomColor); 
			    //g.fillRect(item.getPoint().getWidth().getBegin().intValue() * SCALE, item.getPoint().getLength().getBegin().intValue() * SCALE, item.getWidth().intValue() * SCALE, item.getLength().intValue() * SCALE);
				
				//g.drawString(item.toString(), 5 * SCALE, (bin.getLength().intValue() + count) * SCALE);
				//count += 4;
			}
			
		}
	}
	
	
}//Class
