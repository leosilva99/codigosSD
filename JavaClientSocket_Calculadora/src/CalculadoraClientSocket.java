import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class CalculadoraClientSocket {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		double oper1, oper2;
		int operacao; // 1 - Soma | 2 - Subtração | 3 - Multiplicação | 4 - Divisão
		String result = "";
        int continuar = 1;
        
        while(continuar == 1) {
            Scanner sc = new Scanner(System.in);
			
			System.out.println("Digite o primeiro número");
			oper1 = sc.nextFloat();
			
			System.out.println("\nDigite o segundo número");
			oper2 = sc.nextFloat();
			
			System.out.println("\nDigite o número da operação desejada\n" + 
			"(1 - Soma | 2 - Subtração | 3 - Multiplicação | 4 - Divisão)");
			operacao = sc.nextInt();
			
			if(operacao == 4 && oper2 == 0) {
				System.out.println("\nNão é permitido divisão por 0!\n");
				continue;
			}
            
            try {
                //Conexão com o Servidor
                Socket clientSocket = new Socket("localhost", 9090);
                DataOutputStream socketSaidaServer = new DataOutputStream(clientSocket.getOutputStream());
                
                //Enviando os dados
                socketSaidaServer.writeBytes(""+operacao + '\n');
                socketSaidaServer.writeBytes(""+oper1 + '\n');
                socketSaidaServer.writeBytes(""+oper2 + '\n');
                socketSaidaServer.flush();
    
                //Recebendo a resposta
                BufferedReader messageFromServer = new BufferedReader
                        (new InputStreamReader(clientSocket.getInputStream()));
                result = messageFromServer.readLine();
                
                if(operacao == 1) {
                    System.out.println("\n" + oper1 + " + " + oper2 + " = " + result);
                }

                else if(operacao == 2) {
                    System.out.println("\n" + oper1 + " - " + oper2 + " = " + result);
                }

                else if(operacao == 3) {
                    System.out.println("\n" + oper1 + " * " + oper2 + " = " + result);
                }

                else if(operacao == 4) {
                    System.out.println("\n" + oper1 + " / " + oper2 + " = " + result);
                }

                clientSocket.close();
    
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Perguntar ao usuário se deseja ou não fazer uma nova operação
			System.out.println("\nDeseja fazer uma nova operação? (1 - Sim | 0 - Não)");
			continuar = sc.nextInt();
			
			// Encerrar a execução da calculadora
			if(continuar == 0) {
				System.out.println("\nFim!");
			}

			else System.out.println("\n===============================================================\n");
        }
	}
}
