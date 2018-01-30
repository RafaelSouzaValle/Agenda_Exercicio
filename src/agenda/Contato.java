package agenda;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe responsável pela
 * criação do contato da agenda
 * 
 * @author Rafael.Valle
 *
 */
public class Contato {

	/**
	 * Nome do contato
	 */
	private String nome;
	
	/**
	 * Telefone do contato
	 */
	private String telefone;

	/**
	 * Construtor
	 * 
	 * @param nome : nome do contato
	 * @param telefone : Telefone do contato
	 */
	public Contato(String nome, String telefone) {
		this.nome = nome;
		this.telefone = telefone;
	}

	/**
	 * @return : nome do contato
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Altera o nome o nome do contato
	 * @param nome : recebe o nome que o atributo receberá
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return : telefone do contato
	 */
	public String getTelefone() {
		return telefone;
	}

	/**
	 * Altera o telefone do contato
	 * @param nome : recebe o telefone que o atributo receberá
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public void validarDados () throws AgendaException {
		validarNome();
		validarTelefone();
	}
	
	/**
	 * Valida se o atributo nome não está vazio
	 * ou se contém apenas espaço(s).
	 * @throws AgendaException
	 */
	public void validarNome () throws AgendaException {
		if (nome == null || nome.trim().length() == 0) {
			throw new AgendaException("O campo não pode ser vazio");
		}
	}
	
	/**
	 * Valida se o atributo telefone corresponde ao padrão Regex
	 * @throws AgendaException
	 */
	public void validarTelefone () throws AgendaException {
		Pattern pRegex = Pattern.compile("[\\(\\d\\d\\)]?\\d\\d\\d\\d-\\d\\d\\d\\d");
		Matcher m = pRegex.matcher(telefone);
		
		if (!(m.matches())) {
			throw new AgendaException("Número de telefone " + telefone + " fora do padrão \"(99)9999-9999\". OBS: O prefixo \"(99)\" é opcional");
		}
	}
	
	@Override
	public String toString() {
		return nome + " - " + telefone;
	}
}
