package agenda;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Representa uma agenda de contatos
 * 
 * @author Rafael.Valle
 *
 */
public class Agenda {

	/**
	 * Map com os contatos, com um String nome como chave
	 * e um contato como valor.
	 */
	private Map<String, Contato> contatosMap = new TreeMap<>();
	
	/**
	 * Map que organiza os contatos, onde cada chave é uma letra
	 * e o valor é a lista de contatos em que o atributo nome
	 * começa pela letra da chave.
	 */
	private Map<Character, List<Contato>> contatosPorLetraMap = new TreeMap<>();
	
	/**
	 * Instância da classe que gerencia o arquivo
	 * onde estão contidos os registros da agenda
	 */
	private ArquivoAgenda arquivo = new ArquivoAgenda();
	
	public Agenda() throws IOException{
		List<Contato> contatos = arquivo.leDados();
		
		for (Contato contato : contatos) {
			try {
				inserir(contato);
			} catch (AgendaException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Insere um contato na agenda
	 * @param contato
	 * @throws AgendaException
	 * @throws IOException
	 */
	public void inserir (Contato contato) throws AgendaException, IOException {
		
		contato.validarDados();
		
		String nome = contato.getNome();
		
		if (contatosMap.containsKey(nome)) {
			throw new AgendaException("O contato " + nome + " já existe na agenda");
		}
		
		contatosMap.put(nome,contato);
		
		char letraInicial = Character.toUpperCase(nome.charAt(0));
		List<Contato> contatosLetra = contatosPorLetraMap.get(letraInicial);
		if (contatosLetra == null) {
			contatosLetra = new ArrayList<>();
			contatosPorLetraMap.put(letraInicial, contatosLetra);
		}
		contatosLetra.add(contato);
		arquivo.gravarDados(contatosMap.values());
	}
	
	/**
	 * Exlui um contato da agenda
	 * 
	 * @param nome : Nome do contato a ser excluído
	 * @throws AgendaException
	 * @throws IOException
	 */
	public void excluir (String nome) throws AgendaException, IOException {
		/**
		 * Verifica a existência do contato na agenda
		 */
		verificarExistenciaContato(nome);
		
		/**
		 * Invoca o contato vinculado ao nome
		 */
		Contato contato = obterContato(nome);
		
		/**
		 * Remove o contato do Map
		 */
		contatosMap.remove(nome);
		
		/**
		 * Remove objeto da lista que está dentro do Map de contatos por letras
		 */
		List<Contato> contatos = contatosPorLetraMap.get(nome.toUpperCase().charAt(0));
		contatos.remove(contato);
		
		/**
		 * Caso não reste contato que se inicie com a determinada letra,
		 * ela é excluída do Map
		 */
		if (contatos.size() == 0) {
			contatosPorLetraMap.remove(nome.toUpperCase().charAt(0));
		}
		
		/**
		 * Atualiza o arquivo para corresponder a remoção
		 */
		arquivo.gravarDados(contatosMap.values());
	}
	
	/**
	 * Altera contato existente
	 * 
	 * @param contato
	 * @throws AgendaException
	 * @throws IOException
	 */
	public void alterar (Contato contato) throws AgendaException, IOException {
		contato.validarDados();
		
		verificarExistenciaContato(contato.getNome());
		
		arquivo.gravarDados(contatosMap.values());
	}
	
	/**
	 * Verifica se o contato relacionado ao
	 * nome indicado como parâmetro já existe.
	 * Caso não exista, lança uma exceção.
	 * 
	 * @param nome
	 * @throws AgendaException
	 */
	private void verificarExistenciaContato(String nome) throws AgendaException {
		if (!contatosMap.containsKey(nome)) {
			throw new AgendaException("Contato não cadastrado: " + nome);
		}
	}
	
	/**
	 * Retorna uma lista de contatos que inicia
	 * com determinada letra
	 * 
	 * @param letra
	 * @return Lista de contatos
	 */
	public List<Contato> listarContatosPorLetra(char letra) {
		List<Contato> contatos = contatosPorLetraMap.get(Character.toUpperCase(letra));
		if (contatos == null) {
			contatos = new ArrayList<>();
		}
		return contatos;
	}
	
	public List<Contato> listarContatoPorParteDoNome (String parteNome) {
		/**
		 * String que recebe o padrão da busca: a parte do nome precedida ou sucedida
		 * por qualquer caracter, ou então caracter nenhum.
		 */
		String regex = "\\w*" + parteNome + "\\w*";
		
		/**
		 * Instância de Pattern que compila e expressão regular
		 * de regex, com a constante CASE_INSENSITIVE como
		 * parâmetroa para indicar que a capitalização é indiferente
		 * (não faz diferenciação entre maiúsculas e minúsculas)
		 */
		Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		
		/**
		 * Lista que recebe os contatos encontrados
		 */
		List<Contato> contatosEncontrados = new ArrayList<>();
		
		/**
		 * Lista recebe os contatos já existentes
		 */
		Collection<Contato> contatosCadastrados = contatosMap.values();
		
		for (Contato contato : contatosCadastrados) {
			Matcher m = p.matcher(contato.getNome());
			if (m.matches()) {
				contatosEncontrados.add(contato);
			}
		}
		
		return contatosEncontrados;
	}
	
	/**
	 * Retorna o objeto do contato
	 * referente ao nome inserido no parâmetro
	 * 
	 * @param nome
	 * @return
	 */
	public Contato obterContato (String nome) {
		return contatosMap.get(nome);
	}
}
