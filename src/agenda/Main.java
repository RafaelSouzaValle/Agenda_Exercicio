package agenda;

import java.io.IOException;

/**
 * Classe principal da aplicação.
 * Cria uma instância da aplicação
 * no método principal e
 * invoca o método que a inicializa.
 * 
 * @author Rafael.Valle
 *
 */
public class Main {

	/**
	 * Método principal
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
