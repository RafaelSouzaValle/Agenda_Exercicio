package agenda;

import java.io.IOException;
import java.util.List;

/**
 * Controla o fluxo da aplicação
 * 
 * @author Rafael.Valle
 *
 */
public class Aplicacao {

	/**
	 * Instância de Menu (Menu de opções)
	 */
	private Menu menu;

	/**
	 * Agenda de contatos
	 */
	private Agenda agenda;

	/**
	 * Inicia a aplicação
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
	 * Altera um contato já existente
	 * 
	 * @throws AgendaException
	 * @throws IOException
	 */
	private void alterar () throws AgendaException, IOException {
		System.out.print("Nome: ");
		String nome = Console.readString();
		
		Contato contato = agenda.obterContato(nome);
		
		if (contato == null) {
			throw new AgendaException("O contato " + nome + "não existe");
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
		
		System.out.println("Contato " + nome + " excluído");
		System.out.println();
	}
	
	/**
	 * Lista contatos em que o nome começa
	 * com a letra digitada. Caso não hajam
	 * contatos que comecem com a letra inserida,
	 * imprime uma mensagem.
	 * 
	 * @throws AgendaException
	 */
	private void listarPorLetra () throws AgendaException {
		System.out.print("Digite a letra: ");
		char letra = Console.readChar();
		
		List<Contato> contatos = agenda.listarContatosPorLetra(letra);
		
		System.out.println("Contatos que começam com a letra " + Character.toUpperCase(letra) + ":");
		
		if (contatos.isEmpty()) {
			System.out.println("Não existem contatos com a letra indicada.");
		} else {
			for (Contato contato : contatos) {
				System.out.println(contato);
			}
		}
		System.out.println();
	}
	
	/**
	 * Opção de procura por parte do nome
	 * 
	 * @throws AgendaException
	 */
	private void procurar () throws AgendaException {
		System.out.print("Parte do nome: ");
		String parteDoNome = Console.readString();
		
		List<Contato> contatos = agenda.listarContatoPorParteDoNome(parteDoNome);
		
		System.out.println("Contatos encontrados com " + parteDoNome + " no nome: ");
		
		if (contatos.isEmpty()) {
			System.out.println("Não foram encontrados contatos com o trecho " + parteDoNome);
		} else {
			for (Contato contato : contatos) {
				System.out.println(contato);
			}
		}
		System.out.println();
	}
}
