package Service;

import java.rmi.Naming;
import java.util.List;
import java.util.Scanner;

public class ClientGestionTaches {
	public static int choisirTache() {
		Scanner sc = new Scanner (System.in);
		System.out.println("Merci de choisir la tâche à réaliser : ");
		System.out.println(" 11: Créer un employé \n 12: Afficher la liste des employés \n 13: Afficher un seul employé\n"
				+ "\n 14: Modifier un employé \n 15: Supprimer un employé");
		System.out.println(" 21: Ajouter une tâche \n 22: Afficher les tâches\n 23: Afficher une tâche \n"
				+ "\n 24: Modifier une tâche \n 25: Supprimer une tâche");
		System.out.println("0 pour terminer ");
		int codeTache= sc.nextInt();
		
		sc.close();
		return codeTache;

		
	}
	
	public static void main(String []args)
	{
		
	try {
		IGestionTacheRemote g = (IGestionTacheRemote) Naming.lookup("rmi://localhost:1099/MonSysteme");
		int code;
		
		while (true) {
			 code =choisirTache();
			 switch(code) {
			 case 11:
				 g.createEmployee();
			 case 12: 
			 {
				 List<Employee> list=(List<Employee>)g.getEmployees();
					for(Employee e:list) {
						System.out.println("ID: "+ e.getIdEmployee());
						System.out.println("Nom: "+ e.getNom());
						System.out.println("Prenom: "+ e.getPrenom());
						System.out.println("Adresse: "+ e.getAdresse());
						System.out.println("Grade: "+ e.getGrade());
						System.out.println("Numéro de compte: "+ e.getNumCompte());
						System.out.println("ID supérieur: "+e.getIdSup());
					} 
					break;
			 }
			 case 13: 
			 {
				 Scanner sc = new Scanner(System.in);
					System.out.println("Donner l'id de l'employé: ");
					int id= sc.nextInt();
					
					Employee e=g.getEmployee(id);
					System.out.println("ID: "+ e.getIdEmployee());
					System.out.println("Nom: "+ e.getNom());
					System.out.println("Prenom: "+ e.getPrenom());
					System.out.println("Adresse: "+ e.getAdresse());
					System.out.println("Grade: "+ e.getGrade());
					System.out.println("Numéro de compte: "+ e.getNumCompte());
					System.out.println("ID supérieur: "+e.getIdSup());
					
					sc.close();
				 
			 }
			 case 14:
			 {
				 Scanner sc = new Scanner(System.in);
					System.out.println("Donner l'id de l'employé: ");
					int id= sc.nextInt();
					g.editEmployee(id);
					sc.close();
				 
			 }
			 case 15:
			 {
				Scanner sc = new Scanner(System.in);
				System.out.println("Donner l'id de l'employé: ");
				int id= sc.nextInt();
				g.deleteEmployee(id);
				sc.close();
				break;
			 }
			 case 21:
				 g.createTask();
			 case 22: 
			 {
				 List<Task> list=(List<Task>)g.getTasks();
					for(Task t:list) {
						System.out.println("ID: "+ t.getIdTask());
						System.out.println("Description: "+ t.getDescr());
						System.out.println("Id employé: "+ t.getIdEmployee());
					} 
					break;
			 }
			 case 23: 
			 {
				 Scanner sc = new Scanner(System.in);
					System.out.println("Donner l'id de la tâche: ");
					int id= sc.nextInt();
					
					Task t = g.getTask(id);
					
					System.out.println("ID: "+ t.getIdTask());
					System.out.println("Description: "+ t.getDescr());
					System.out.println("Id employé: "+ t.getIdEmployee());
					
					
					sc.close();
				 
			 }
			 case 24:
			 {
				 Scanner sc = new Scanner(System.in);
					System.out.println("Donner l'id de la tâche: ");
					int id= sc.nextInt();
					g.editTask(id);
					sc.close();
					
				 
			 }
			 case 25:
			 {
				Scanner sc = new Scanner(System.in);
				System.out.println("Donner l'id de la tâche: ");
				int id= sc.nextInt();
				g.deleteTask(id);
				sc.close();
				break;
			 }
			 case 0:
				 break;
			default:
				break;
				 
				 
			 }
				 
					 
			
		}
		
		
	
		
		
	}
	catch(Exception e){
		System.out.println("Erreur d'acces à l'objet distant!");
		System.out.println(e.toString());
	}

}
}
