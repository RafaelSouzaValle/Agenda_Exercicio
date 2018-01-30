package agenda;

/**
 * Classe que cria o menu de op��es da aplica��o
 * 
 * @author Rafael.Valle
 *
 */
public class Menu {

	/**
	 * Constantes referentes a cada op��o do menu
	 */
	public static final int OPCAO_INSERIR = 1;
	public static final int OPCAO_ALTERAR = 2;
	public static final int OPCAO_EXCLUIR = 3;
	public static final int OPCAO_LISTAR_LETRA = 4;
	public static final int OPCAO_PROCURAR = 5;
	public static final int OPCAO_SAIR = 6;
	
	/**
	 * Imprime as op��es no console
	 * 
	 * @return : int com a op��o escolhida
	 * @throws AgendaException : para op��o inv�lida
	 */
	public int exibirOpcoes() throws AgendaException {
		System.out.println("+++ MENU +++");
		System.out.println("1 - Inserir contato");
		System.out.println("2 - Alterar contato");
		System.out.println("3 - Excluir contato");
		System.out.println("4 - Listar contato(s) por letra");
		System.out.println("5 - Procurar contato");
		System.out.println("6 - Sair");
		System.out.println();
		
		/**
		 * Recebe entrada digitada no console
		 */
		int opcao = Console.readInt();
		
		/**
		 * Verifica se a op��o digitada
		 * est� dentro da faixa v�lida
		 */
		if (opcao < OPCAO_INSERIR || opcao > OPCAO_SAIR) {
			throw new AgendaException("Op��o inv�lida");
		}
		
		/**
		 * Retorna a op��o digitada
		 */
		return opcao;
	}
}
