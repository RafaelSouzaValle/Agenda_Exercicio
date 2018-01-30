package agenda;

/**
 * Respon�vel pelas exce��es da aplica��o
 * 
 * @author Rafael.Valle
 *
 */
@SuppressWarnings("serial")
public class AgendaException extends Exception {

	/**
	 * Retorna mensagem com detalhe da exce��o
	 * @param message
	 */
	public AgendaException(String message) {
		super(message);
	}
}
