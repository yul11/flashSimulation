package eu.de.tnd.juo.flash;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Circle extends JPanel implements Runnable {
	
    private int xM;
    private int yM;
    protected Graphics2D g2d;
    protected int angleSec;
    protected boolean AlarmSignal;
    private   Thread thread;

    
    Circle(){
    	JPanel    mainPanel   = new JPanel();		
    	mainPanel.setLayout(new BorderLayout());   	
    	mainPanel.add("Center", this);   	
        JFrame frame    = new JFrame();
        frame.setSize(500, 500);
        frame.setLocation(xM,yM);          
        frame.add(mainPanel);       
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);   	
    }

    
    public void setSignal(boolean signal){
    	this.AlarmSignal = signal;
    	System.out.println("signal in Circle() angekommen, Wert: " + signal);
    	if (AlarmSignal){
        	thread = new Thread(this);
        	thread.start();
    	}
    		
    }
	
	
    public void paintComponent(Graphics g) {	    
	    super.paintComponent(g);	    
	    Dimension d = getSize();
		int w = d.width;
		int h = d.height;
		xM = w/2;
		yM = h/2;
	    g2d = (Graphics2D) g;
	    g2d.setColor(Color.RED);
	    g2d.setStroke(new BasicStroke(10));	    
	    g2d.drawArc(0, 0, w, h, 90, (360-angleSec));
    }


	public void run() {	
		angleSec = 0;
	    while (AlarmSignal) {	
	    	angleSec++;
	    	System.out.println("___________set angleSecto: " + angleSec);
	        repaint();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (angleSec>359)
				angleSec=0;
	    }		
	}
}
