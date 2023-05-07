package Service;


import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;


public class ServeurGestionTaches {
	
	public static void main(String[] args) {
		
		try {
			
			LocateRegistry.createRegistry(1099);
			GestionTacheImpl g=new GestionTacheImpl();
						
			Naming.rebind("rmi://localhost:1099/MonSysteme", g);
			System.out.println("Serveur prêt");
			
			
		}
		
		catch(Exception  e) {
			System.out.println("erreur de liaison!!");
			System.out.println(e.toString());
		}
	}

}
