package Service;


@SuppressWarnings("serial")
public class Employee implements java.io.Serializable {
	

	public int idEmployee;
	public String nom;
	public String prenom;
	public String adresse;
	public int numCompte;
	public String grade;
	public int idSup;
	
	public Employee(int idEmployee, String nom, String prenom, String adresse, int numCompte, String grade, int idSup) {
		super();
		this.idEmployee = idEmployee;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.numCompte = numCompte;
		this.grade = grade;
		this.idSup = idSup;
	}

	public Employee() {
		super();
	}

	public int getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(int idEmployee) {
		this.idEmployee = idEmployee;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public int getNumCompte() {
		return numCompte;
	}

	public void setNumCompte(int numCompte) {
		this.numCompte = numCompte;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public int getIdSup() {
		return idSup;
	}

	public void setIdSup(int idSup) {
		this.idSup = idSup;
	}
	
	
	
	

}
