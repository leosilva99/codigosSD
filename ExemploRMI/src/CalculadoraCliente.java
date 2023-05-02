import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;


public class CalculadoraCliente {
	
	public static void main(String[] args) {
		Registry reg = null;
		ICalculadora calc;
		float num1, num2; // Receber os 2 números
		int operacao; // Identifica qual das 4 operações será feita
		
		int continuar = 1; 
		while(continuar == 1) {
			Scanner sc = new Scanner(System.in);
			
			System.out.println("Digite o primeiro número");
			num1 = sc.nextFloat();
			
			System.out.println("\nDigite o segundo número");
			num2 = sc.nextFloat();
			
			System.out.println("\nDigite o número da operação desejada\n" + 
			"(1 - Soma | 2 - Subtração | 3 - Multiplicação | 4 - Divisão)");
			operacao = sc.nextInt();
			
			if(operacao == 4 && num2 == 0) {
				System.out.println("\nNão é permitido divisão por 0!\n");
				continue;
			}

			try {
				reg = LocateRegistry.getRegistry(1099);
				calc = (ICalculadora) reg.lookup("calculadora");
				
				if(operacao == 1) {
					System.out.println("\n" + num1 + " + " + num2 + " = " +
					calc.soma(num1, num2) + "\n");
				}

				else if (operacao == 2) {
					System.out.println("\n" + num1 + " - " + num2 + " = " +
					calc.subtracao(num1, num2) + "\n");
				}

				else if (operacao == 3) {
					System.out.println("\n" + num1 + " * " + num2 + " = " +
					calc.multiplicacao(num1, num2) + "\n");
				}

				else if (operacao == 4) {
					System.out.println("\n" + num1 + " / " + num2 + " = " +
					calc.divisao(num1, num2) + "\n");
				}

			} catch (RemoteException | NotBoundException e) {
					System.out.println(e);
					System.exit(0);
			}
			// Perguntar ao usuário se deseja ou não fazer uma nova operação
			System.out.println("Deseja fazer uma nova operação? (1 - Sim | 0 - Não)");
			continuar = sc.nextInt();
			
			// Encerrar a execução da calculadora
			if(continuar == 0) {
				System.out.println("\nFim!");
			}

			else System.out.println("\n===============================================================\n");
		}
	}		
}
