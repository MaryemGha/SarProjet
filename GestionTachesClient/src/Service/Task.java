package Service;

@SuppressWarnings("serial")
public class Task implements java.io.Serializable {
	
	public int idTask;
	public String descr;
	public int idEmployee;
	
	public Task(int idTask, String descr, int idEmployee) {
		super();
		this.idTask = idTask;
		this.descr = descr;
		this.idEmployee = idEmployee;
	}

	public Task() {
		super();
	}

	public int getIdTask() {
		return idTask;
	}

	public void setIdTask(int idTask) {
		this.idTask = idTask;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public int getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(int idEmployee) {
		this.idEmployee = idEmployee;
	}
	
	
	
	

}
