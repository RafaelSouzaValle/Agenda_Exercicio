package agenda;

/**
 * Classe que cria o menu de opções da aplicação
 * 
 * @author Rafael.Valle
 *
 */
public class Menu {

	/**
	 * Constantes referentes a cada opção do menu
	 */
	public static final int OPCAO_INSERIR = 1;
	public static final int OPCAO_ALTERAR = 2;
	public static final int OPCAO_EXCLUIR = 3;
	public static final int OPCAO_LISTAR_LETRA = 4;
	public static final int OPCAO_PROCURAR = 5;
	public static final int OPCAO_SAIR = 6;
	
	/**
	 * Imprime as opções no console
	 * 
	 * @return : int com a opção escolhida
	 * @throws AgendaException : para opção inválida
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
		 * Verifica se a opção digitada
		 * está dentro da faixa válida
		 */
		if (opcao < OPCAO_INSERIR || opcao > OPCAO_SAIR) {
			throw new AgendaException("Opção inválida");
		}
		
		/**
		 * Retorna a opção digitada
		 */
		return opcao;
	}
}
