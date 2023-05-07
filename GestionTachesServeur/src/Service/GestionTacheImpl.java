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
			// Connexion � la base de donn�es MySQL
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/taskmanagementdb","root","kanunviolon");
			
		} catch(Exception ex) {
			System.out.println("Erreur lors de la connexion � la base de donn�es");
		}
	}
	
	
	//Ajouter Employee
	public void createEmployee() throws RemoteException, Exception{
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Donner l'id de l'employ�: ");
		int id= sc.nextInt();
		sc.nextLine();
		System.out.println("Donner le nom de l'employ�: ");
		String nom=sc.nextLine();
		System.out.println("Donner le pr�nom de l'employ�: ");
		String prenom=sc.nextLine();
		System.out.println("Adresse: ");
		String adresse=sc.nextLine();
		System.out.println("Donner le num�ro de compte de l'employ�: ");
		int numCompte=sc.nextInt();
		sc.nextLine();
		System.out.println("Grade: ");
		String grade=sc.nextLine();
		System.out.println("Id du sup�rieur");
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
			System.out.println("Erreur lors de l'ajout d'un nouveau employ�");
			
		}
}

	
	//Afficher tous les employ�s
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
			System.out.println("Erreur lors de la r�cupp�ration de tous les employ�s");
		}
		return list;
	}
	
	
	//Affichier un seul employ�
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
			System.out.println("Erreur lors de la r�xup�ration d'un employ�");
		}

		return e;
		}
	
	
	
	//Mettre � jour un employ�
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
			System.out.println("Erreur lors de la moise � jour d'un employ�");
		}
	}
	
	//Supprimer un employ�
	@Override
	public void deleteEmployee(int id) throws RemoteException {
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM employee WHERE idEmployee=?");
			ps.setInt(1,  id);
			ps.executeUpdate();
					
		}catch(SQLException ex) {
			System.out.println("Erreur lors de la suppression d'un employ�");
		}
		
	}
	
	
	//Ajouter une t�che
	public void createTask() throws RemoteException{
		Scanner sc = new Scanner(System.in);
		System.out.println("Donner l'id de la t�che: ");
		int id= sc.nextInt();
		sc.nextLine();
		System.out.println("Donner la description de la t�che: ");
		String desc=sc.nextLine();
		System.out.println("Donner l'id de l'employ� responsable de cette t�che: ");
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
			System.out.println("Erreur lors de l'ajout d'un nouveau employ�");
			
		}
		
	}
	
	//Afficher les t�ches
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
			System.out.println("Erreur lors de la r�cupp�ration des t�ches");
		}
		return list;
	}
	
	//Afficher une t�che par id
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
			System.out.println("Erreur lors de la r�cup�ration d'une t�che");
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
				System.out.println("Erreur lors de la mise � jour d'une t�che");
			}
		
	}
	
	//Suppression d'une t�che
	@Override
	public void deleteTask(int id) throws RemoteException {
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM employee WHERE idEmployee=?");
			ps.setInt(1,  id);
			ps.executeUpdate();
					
		}catch(SQLException ex) {
			System.out.println("Erreur lors de la suppression d'un employ�");
		}
	
		
	}

}
