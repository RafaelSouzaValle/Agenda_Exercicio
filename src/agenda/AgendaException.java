package agenda;

/**
 * Responável pelas exceções da aplicação
 * 
 * @author Rafael.Valle
 *
 */
@SuppressWarnings("serial")
public class AgendaException extends Exception {

	/**
	 * Retorna mensagem com detalhe da exceção
	 * @param message
	 */
	public AgendaException(String message) {
		super(message);
	}
}
