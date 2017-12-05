package eu.de.tnd.juo.flash;


import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import javax.swing.JPanel;



class Check_Flash extends JPanel implements Runnable {

	
	public String ecu;
	Thread t;
	boolean displayCircle;
	Instant reportTime_old;	
	Instant reportTime_new;	
	Circle c = new Circle();
	boolean signal;

	
	
	Check_Flash(String ecu) {
		this.ecu = ecu;
		this.reportTime_old = Instant.now(Clock.systemUTC());
		this.reportTime_new = Instant.now(Clock.systemUTC());
		displayCircle=false;		
	}
	
	
	public void signalFrom (String ecu){
		System.out.println("signal from: " + ecu);		
		reportTime_new = Instant.now(Clock.systemUTC());
        checkTime(ecu, reportTime_new);
	}
	


	
	public void checkTime(String ecu, Instant reportTime_new){		
		System.out.println("reportTime_old: " + reportTime_old);
		System.out.println("reportTime_new: " + reportTime_new);
		long delta = Duration.between(reportTime_old,reportTime_new).toNanos();		
		System.out.println("delta: " +delta);

		if (delta>2000000000){ //2 Sekunden
			System.out.println("delta: " +delta + " Alarm! " + ecu + " meldet sich zu spät");
			t = new Thread(this);		
			t.start();
			displayCircle = true;	    				
		}
		else{
			displayCircle=false;
		}

		archiveSignal(reportTime_new);		
		System.out.println("ecu: " +ecu);
			
	}
	
	private void archiveSignal(Instant reportTime_new){
		reportTime_old = reportTime_new;
	}
	

	


	
	public void run() {	
		while (displayCircle) {	
			System.out.println("setze Signal to: " + (displayCircle ? "true" : "false" ));
			c.setSignal(true);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}