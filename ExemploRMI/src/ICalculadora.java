import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICalculadora extends Remote{

	public float soma(float a, float b) throws RemoteException;

	public float subtracao(float a, float b) throws RemoteException;

	public float multiplicacao(float a, float b) throws RemoteException;

	public float divisao(float a, float b) throws RemoteException;
}
