package Service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IGestionTacheRemote extends Remote {
	
	public void createEmployee() throws RemoteException, Exception;
	public List<Employee> getEmployees() throws RemoteException, Exception;
	public Employee getEmployee(int id) throws RemoteException, Exception;
	public void editEmployee(int id) throws RemoteException;
	public void deleteEmployee(int id) throws RemoteException;
	
	public void createTask() throws RemoteException;
	public List<Task> getTasks() throws RemoteException;
	public Task getTask(int id) throws RemoteException;
	public void editTask (int id) throws RemoteException;
	public void deleteTask (int id) throws RemoteException;
}
