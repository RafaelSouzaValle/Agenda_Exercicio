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
	 */
	public static void main(String[] args) throws IOException {
		Aplicacao aplicacao = new Aplicacao();
		aplicacao.iniciar();
	}

}
