import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class CalculadoraClientHTTP {

	public static void main(String[] args) {

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

                URL url = new URL("https://double-nirvana-273602.appspot.com/?hl=pt-BR");
                HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true) ;
        
                //ENVIO DOS PARAMETROS
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
                
                //1-somar 2-subtrair 3-dividir 4-multiplicar
                writer.write("oper1="+oper1+"&oper2="+oper2+"&operacao="+operacao);
                writer.flush();
                writer.close();
                os.close();
        
                int responseCode = conn.getResponseCode();
                System.out.println("\nCódigo de resposta do servidor: " + responseCode);
                if (responseCode == HttpsURLConnection.HTTP_OK) {
        
                    //RECBIMENTO DOS PARAMETROS
                    BufferedReader br = new BufferedReader(
                        new InputStreamReader(conn.getInputStream(), "utf-8"));
                    
                    StringBuilder response = new StringBuilder();
                    String responseLine = null;
                    
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    
                    result = response.toString();
                    System.out.println("\nResposta do Servidor PHP = " + result);
                }
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
