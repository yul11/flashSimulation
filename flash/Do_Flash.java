package eu.de.tnd.juo.flash;


class Do_Flash implements Runnable {
	
	
	
	private String ecu;
	Check_Flash cf;
	
	
	Do_Flash(String ecu) {
		this.ecu = ecu;		
		cf = new Check_Flash(ecu);
	}
	
	
	
	
	
	
	void warte() {
		int zeit;
		try {
			zeit = 5000;			
			long l = Math.round(zeit * Math.random());			
			System.out.println("warte: " + l + " ms...\n");			
			Thread.sleep(l);
		} 
		catch (InterruptedException e) {
		}
	}

	
	public void run() {
		while (true) {			
			System.out.println("Flashe: " +ecu);	
			cf.signalFrom(ecu);				
			warte();					
		}
	}
}