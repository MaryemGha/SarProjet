package Service;

import java.rmi.Naming;
import java.util.List;
import java.util.Scanner;

public class ClientGestionTaches {
	public static int choisirTache() {
		Scanner sc = new Scanner (System.in);
		System.out.println("Merci de choisir la t�che � r�aliser : ");
		System.out.println(" 11: Cr�er un employ� \n 12: Afficher la liste des employ�s \n 13: Afficher un seul employ�\n"
				+ "\n 14: Modifier un employ� \n 15: Supprimer un employ�");
		System.out.println(" 21: Ajouter une t�che \n 22: Afficher les t�ches\n 23: Afficher une t�che \n"
				+ "\n 24: Modifier une t�che \n 25: Supprimer une t�che");
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
						System.out.println("Num�ro de compte: "+ e.getNumCompte());
						System.out.println("ID sup�rieur: "+e.getIdSup());
					} 
					break;
			 }
			 case 13: 
			 {
				 Scanner sc = new Scanner(System.in);
					System.out.println("Donner l'id de l'employ�: ");
					int id= sc.nextInt();
					
					Employee e=g.getEmployee(id);
					System.out.println("ID: "+ e.getIdEmployee());
					System.out.println("Nom: "+ e.getNom());
					System.out.println("Prenom: "+ e.getPrenom());
					System.out.println("Adresse: "+ e.getAdresse());
					System.out.println("Grade: "+ e.getGrade());
					System.out.println("Num�ro de compte: "+ e.getNumCompte());
					System.out.println("ID sup�rieur: "+e.getIdSup());
					
					sc.close();
				 
			 }
			 case 14:
			 {
				 Scanner sc = new Scanner(System.in);
					System.out.println("Donner l'id de l'employ�: ");
					int id= sc.nextInt();
					g.editEmployee(id);
					sc.close();
				 
			 }
			 case 15:
			 {
				Scanner sc = new Scanner(System.in);
				System.out.println("Donner l'id de l'employ�: ");
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
						System.out.println("Id employ�: "+ t.getIdEmployee());
					} 
					break;
			 }
			 case 23: 
			 {
				 Scanner sc = new Scanner(System.in);
					System.out.println("Donner l'id de la t�che: ");
					int id= sc.nextInt();
					
					Task t = g.getTask(id);
					
					System.out.println("ID: "+ t.getIdTask());
					System.out.println("Description: "+ t.getDescr());
					System.out.println("Id employ�: "+ t.getIdEmployee());
					
					
					sc.close();
				 
			 }
			 case 24:
			 {
				 Scanner sc = new Scanner(System.in);
					System.out.println("Donner l'id de la t�che: ");
					int id= sc.nextInt();
					g.editTask(id);
					sc.close();
					
				 
			 }
			 case 25:
			 {
				Scanner sc = new Scanner(System.in);
				System.out.println("Donner l'id de la t�che: ");
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
		System.out.println("Erreur d'acces � l'objet distant!");
		System.out.println(e.toString());
	}

}
}
