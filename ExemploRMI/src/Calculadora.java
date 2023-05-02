import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Calculadora  implements ICalculadora {

	private static final long serialVersionUID = 1L;
	
	private static int chamadas = 0;

	public float soma(float a, float b) throws RemoteException {
		System.out.println("Método soma chamado: " + ++chamadas + "º operação feita!");
		return a + b;
	}

	public float subtracao(float a, float b) throws RemoteException {
		System.out.println("Método subtração chamado: " + ++chamadas + "º operação feita!");
		return a - b;
	}

	public float multiplicacao(float a, float b) throws RemoteException {
		System.out.println("Método multiplicação chamado: " + ++chamadas + "º operação feita!");
		return a * b;
	}

	public float divisao(float a, float b) throws RemoteException {
		System.out.println("Método divisão chamado: " + ++chamadas + "º operação feita!");
		return a / b;
	}

	public static void main(String[] args) throws AccessException, RemoteException, AlreadyBoundException  {
		Calculadora calculadora = new Calculadora();		
		Registry reg = null;
		ICalculadora stub = (ICalculadora) UnicastRemoteObject.exportObject(calculadora, 1100);
		try {
			System.out.println("Criando registro...\n");
			reg = LocateRegistry.createRegistry(1099);
		} catch (Exception e) {
			try {
				reg = LocateRegistry.getRegistry(1099);
			} catch (Exception e1) {
				System.err.println("Ocorreu um erro para criar o registro!");
				System.exit(0);
			}
		}
		System.out.println("Registro criado com sucesso!\n");
		reg.rebind("calculadora", stub);
	}
}
