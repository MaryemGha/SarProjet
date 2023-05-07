package Service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("serial")
public class GestionTacheImpl extends UnicastRemoteObject implements IGestionTacheRemote {
	
	private Connection conn;
	
	//Constructeur
	public GestionTacheImpl() throws RemoteException{
		super();
		
		try {
			// Connexion à la base de données MySQL
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/taskmanagementdb","root","kanunviolon");
			
		} catch(Exception ex) {
			System.out.println("Erreur lors de la connexion à la base de données");
		}
	}
	
	
	//Ajouter Employee
	public void createEmployee() throws RemoteException, Exception{
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Donner l'id de l'employé: ");
		int id= sc.nextInt();
		sc.nextLine();
		System.out.println("Donner le nom de l'employé: ");
		String nom=sc.nextLine();
		System.out.println("Donner le prénom de l'employé: ");
		String prenom=sc.nextLine();
		System.out.println("Adresse: ");
		String adresse=sc.nextLine();
		System.out.println("Donner le numéro de compte de l'employé: ");
		int numCompte=sc.nextInt();
		sc.nextLine();
		System.out.println("Grade: ");
		String grade=sc.nextLine();
		System.out.println("Id du supérieur");
		int idSup=sc.nextInt();
		sc.nextLine();
		
		sc.close();
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO employee(idEmployee, nom, prenom, adresse, numCompte, grade,idSup) VALUES (?,?,?,?,?,?,?)");
			ps.setInt(1,id);
			ps.setString(2, nom);
			ps.setString(3, prenom);
			ps.setString(4, adresse);
			ps.setInt(5, numCompte);
			ps.setString(6, grade);
			ps.setInt(7, idSup);
			ps.executeUpdate();
		}catch( SQLException ex) {
			System.out.println(ex.toString());
			System.out.println("Erreur lors de l'ajout d'un nouveau employé");
			
		}
}

	
	//Afficher tous les employés
	@Override
	public List<Employee> getEmployees() throws RemoteException, Exception {
		
		List<Employee> list= new ArrayList<Employee>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM employee");
			while(rs.next()) {
				Employee e = new Employee(rs.getInt("idEmployee"),rs.getString("nom"),rs.getString("prenom"),rs.getString("adresse"), rs.getInt("numCompte"), rs.getString("grade"), rs.getInt("idSup"));
				list.add(e);
				
			}
		} catch( SQLException ex) {
			System.out.println("Erreur lors de la récuppération de tous les employés");
		}
		return list;
	}
	
	
	//Affichier un seul employé
	@Override
	public Employee getEmployee(int id) throws RemoteException, Exception {
		
		Employee e=null;
		try {
			PreparedStatement ps = conn.prepareStatement ("SELECT * FROM employee WHERE idEmployee=?");
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				e = new Employee(rs.getInt("idEmployee"),rs.getString("nom"),rs.getString("prenom"),rs.getString("adresse"), rs.getInt("numCompte"),rs.getString("grade"), rs.getInt("idSup"));
			}
		} catch( SQLException ex) {
			System.out.println("Erreur lors de la réxupération d'un employé");
		}

		return e;
		}
	
	
	
	//Mettre à jour un employé
	@Override
	public void editEmployee(Employee e) throws RemoteException {
		try {
		PreparedStatement ps = conn.prepareStatement("Update employee SET nom=?, prenom=?, adresse=?, numCompte=?, grade=?,idSup=? ");
		ps.setString(1,  e.getNom());
		ps.setString(2, e.getPrenom());
		ps.setString(3,e.getAdresse());
		ps.setInt(4, e.getNumCompte());
		ps.setString(5, e.getGrade());
		ps.setInt(6, e.getIdSup());
		ps.executeUpdate();
		}catch(SQLException ex) {
			System.out.println("Erreur lors de la moise à jour d'un employé");
		}
	}
	
	//Supprimer un employé
	@Override
	public void deleteEmployee(int id) throws RemoteException {
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM employee WHERE idEmployee=?");
			ps.setInt(1,  id);
			ps.executeUpdate();
					
		}catch(SQLException ex) {
			System.out.println("Erreur lors de la suppression d'un employé");
		}
		
	}
	
	
	//Ajouter une tâche
	public void createTask() throws RemoteException{
		Scanner sc = new Scanner(System.in);
		System.out.println("Donner l'id de la tâche: ");
		int id= sc.nextInt();
		sc.nextLine();
		System.out.println("Donner la description de la tâche: ");
		String desc=sc.nextLine();
		System.out.println("Donner l'id de l'employé responsable de cette tâche: ");
		int idEmployee=sc.nextInt();
		sc.nextLine();
		sc.close();
		
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO task(idTask,desc, idEmployee) VALUES (?,?,?)");
			ps.setInt(1,id);
			ps.setString(2, desc);
			ps.setInt(3, idEmployee);
			ps.executeUpdate();
		}catch( SQLException ex) {
			System.out.println("Erreur lors de l'ajout d'un nouveau employé");
			
		}
		
	}
	
	//Afficher les tâches
	@Override
	public List<Task> getTasks() throws RemoteException {
		List<Task> list= new ArrayList<Task>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM task");
			while(rs.next()) {
				Task t = new Task(rs.getInt("idTask"),rs.getString("desc"),rs.getInt("idEmployee"));
				list.add(t);
				
			}
		} catch( SQLException ex) {
			System.out.println("Erreur lors de la récuppération des tâches");
		}
		return list;
	}
	
	//Afficher une tâche par id
	@Override
	public Task getTask(int id) throws RemoteException {
		Task t=null;
		try {
			PreparedStatement ps = conn.prepareStatement ("SELECT * FROM task WHERE idTask=?");
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				t = new Task(rs.getInt("idTask"),rs.getString("desc"),rs.getInt("idEmployee"));			}
		} catch( SQLException ex) {
			System.out.println("Erreur lors de la récupération d'une tâche");
		}

		return t;
	}
	@Override
	public void editTask(Task t) throws RemoteException {
		try {
			PreparedStatement ps = conn.prepareStatement("Update employee SET desc=?, idEMployee=? ");
			ps.setString(1,  t.getDescr());
			ps.setInt(2, t.getIdEmployee());
			ps.executeUpdate();
			}catch(SQLException ex) {
				System.out.println("Erreur lors de la mise à jour d'une tâche");
			}
		
	}
	
	//Suppression d'une tâche
	@Override
	public void deleteTask(int id) throws RemoteException {
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM employee WHERE idEmployee=?");
			ps.setInt(1,  id);
			ps.executeUpdate();
					
		}catch(SQLException ex) {
			System.out.println("Erreur lors de la suppression d'un employé");
		}
	
		
	}

}
