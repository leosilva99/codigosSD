import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class CalculadoraServerSocket {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket welcomeSocket;
		DataOutputStream socketOutput;     	
	    DataInputStream socketInput;
	    BufferedReader socketEntrada;
	    Calculadora calc = new Calculadora();
		
		try {
			welcomeSocket = new ServerSocket(9090);
			int i = 0; // Número de clientes
	  
	      	System.out.println ("Servidor no ar\n");
	      	while(true) { 
	  
	        	Socket connectionSocket = welcomeSocket.accept(); 
	        	i++;
	           	System.out.println ("Nova conexão");
	           
				// Interpretando dados do servidor
				socketEntrada = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
				int operacao = Integer.parseInt(socketEntrada.readLine());
				double oper1 = Double.parseDouble(socketEntrada.readLine());
				double oper2 = Double.parseDouble(socketEntrada.readLine());
				String result = "";

				// Chamando a calculadora de acordo com a operação selecionada
				if(operacao == 1) {
					System.out.println("Método soma chamado");
					result = "" + calc.soma(oper1, oper2);
				}

				else if(operacao == 2) {
					System.out.println("Método subtração chamado");
					result = "" + calc.subtracao(oper1, oper2);
				}

				else if(operacao == 3) {
					System.out.println("Método multiplicação chamado");
					result = "" + calc.multiplicacao(oper1, oper2);
				}

				else if(operacao == 4) {
					System.out.println("Método divisão chamado");
					result = "" + calc.divisao(oper1, oper2);
				}
				
				//Enviando dados para o servidor
				socketOutput = new DataOutputStream(connectionSocket.getOutputStream());     	
				socketOutput.writeBytes(result + '\n');
				System.out.println ("Resultado = " + result + '\n');	           
				socketOutput.flush();
				socketOutput.close();
	      	}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    
	}

}
