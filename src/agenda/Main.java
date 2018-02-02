package agenda;

import java.io.IOException;

/**
 * Classe principal da aplica��o.
 * Cria uma inst�ncia da aplica��o
 * no m�todo principal e
 * invoca o m�todo que a inicializa.
 * 
 * @author Rafael.Valle
 *
 */
public class Main {

	/**
	 * M�todo principal
	 * 
	 * @param args
	 * @throws IOException
	 * @throws AgendaException 
	 */
	public static void main(String[] args) throws IOException, AgendaException {
		Aplicacao aplicacao = new Aplicacao();
		aplicacao.iniciar();
	}

}
