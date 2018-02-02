package agenda;

import java.io.IOException;
import java.util.List;

/**
 * Controla o fluxo da aplica��o
 * 
 * @author Rafael.Valle
 *
 */
public class Aplicacao {

	/**
	 * Inst�ncia de Menu (Menu de op��es)
	 */
	private Menu menu;

	/**
	 * Agenda de contatos
	 */
	private Agenda agenda;

	/**
	 * Inicia a aplica��o
	 * 
	 * @throws IOException
	 */
	public void iniciar () throws IOException, AgendaException {

		menu = new Menu();

		agenda = new Agenda();

		int opcao = 0;

		while (opcao != Menu.OPCAO_SAIR) {
			try {
				
				opcao = menu.exibirOpcoes();
				
				switch (opcao) {
				case Menu.OPCAO_INSERIR:
					inserir();
					break;
				case Menu.OPCAO_ALTERAR:
					alterar();
					break;
				case Menu.OPCAO_EXCLUIR:
					excluir();
					break;
				case Menu.OPCAO_LISTAR_LETRA:
					listarPorLetra();
					break;
				case Menu.OPCAO_PROCURAR:
					procurar();
					break;
				}
			} catch (AgendaException e) {
				System.out.println("Erro: " + e.getMessage());
				System.out.println();
			}
		}
		
		System.out.println("----FIM----");
	}
	
	/**
	 * Insere contato
	 * 
	 * @throws AgendaException
	 * @throws IOException
	 */
	private void inserir() throws AgendaException, IOException {
		System.out.print("Nome: ");
		String nome = Console.readString();
		System.out.print("Telefone: ");
		String telefone = Console.readString();
		
		Contato contato = new Contato(nome, telefone);
		agenda.inserir(contato);
		
		System.out.println("Contato " + nome + " inserido");
		System.out.println();
	}
	
	/**
	 * Altera um contato j� existente
	 * 
	 * @throws AgendaException
	 * @throws IOException
	 */
	private void alterar () throws AgendaException, IOException {
		System.out.print("Nome: ");
		String nome = Console.readString();
		
		Contato contato = agenda.obterContato(nome);
		
		if (contato == null) {
			throw new AgendaException("O contato " + nome + "n�o existe");
		}
		
		System.out.print("Telefone: ");
		String telefone = Console.readString();
		
		contato.setTelefone(telefone);
		agenda.alterar(contato);
		
		System.out.print("Conato alterado");
		System.out.println();
	}
	/**
	 * Exclui um contato da agenda
	 * 
	 * @throws AgendaException
	 * @throws IOException
	 */
	private void excluir () throws AgendaException, IOException {
		
		System.out.print("Nome: ");
		String nome = Console.readString();
		
		agenda.excluir(nome);
		
		System.out.println("Contato " + nome + " exclu�do");
		System.out.println();
	}
	
	/**
	 * Lista contatos em que o nome come�a
	 * com a letra digitada. Caso n�o hajam
	 * contatos que comecem com a letra inserida,
	 * imprime uma mensagem.
	 * 
	 * @throws AgendaException
	 */
	private void listarPorLetra () throws AgendaException {
		System.out.print("Digite a letra: ");
		char letra = Console.readChar();
		
		List<Contato> contatos = agenda.listarContatosPorLetra(letra);
		
		System.out.println("Contatos que come�am com a letra " + Character.toUpperCase(letra) + ":");
		
		if (contatos.isEmpty()) {
			System.out.println("N�o existem contatos com a letra indicada.");
		} else {
			for (Contato contato : contatos) {
				System.out.println(contato);
			}
		}
		System.out.println();
	}
	
	/**
	 * Op��o de procura por parte do nome
	 * 
	 * @throws AgendaException
	 */
	private void procurar () throws AgendaException {
		System.out.print("Parte do nome: ");
		String parteDoNome = Console.readString();
		
		List<Contato> contatos = agenda.listarContatoPorParteDoNome(parteDoNome);
		
		System.out.println("Contatos encontrados com " + parteDoNome + " no nome: ");
		
		if (contatos.isEmpty()) {
			System.out.println("N�o foram encontrados contatos com o trecho " + parteDoNome);
		} else {
			for (Contato contato : contatos) {
				System.out.println(contato);
			}
		}
		System.out.println();
	}
}
