package eu.de.tnd.juo.flash;


public class Start {

	public static void main(String[] args) {
		
		
		new Thread(new Do_Flash   ("ZBR")).start();
		//new Thread(new Do_Flash   ("Kombi")).start();
		
		//new Thread(new Check_Flash("FFR")).start();

	}
}
