package agenda;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

/**
 * Classe que acessa e altera o arquivo
 * com as informações da agenda.
 * 
 * @author Rafael.Valle
 *
 */
public class ArquivoAgenda {

	/**
	 * Constante com o nome do arquivo que contém a agenda
	 */
	public static final String FILE_AGENDA = "agenda.txt";
	
	/**
	 * Grava os dados de uma coleção
	 * de contatos no arquivo
	 * 
	 * @param contatos
	 * @throws IOException
	 */
	public void gravarDados (Collection<Contato> contatos) throws IOException {
		PrintWriter pWriter = null;
		
		try {
			pWriter = new PrintWriter(FILE_AGENDA);
			
			for (Contato contato : contatos) {
				pWriter.print(contato.getNome());
				pWriter.print(":");
				pWriter.println(contato.getTelefone());
			}
		} finally {
			if (pWriter != null) {
				pWriter.close();
			}
		}
	}
	
	/**
	 * Lê os dados do arquivo de contatos
	 * 
	 * @return : Lista de contatos lidos
	 */
	public List<Contato> leDados () {
		List<Contato> contatos = new ArrayList<>();
		
		Scanner scanner = null;
		
		try {
			scanner = new Scanner (new File(FILE_AGENDA));
			while (scanner.hasNextLine()) {
				String linha = scanner.nextLine();
				String[] tokens = linha.split(":");
				Contato contato = new Contato(tokens[0], tokens[1]);
				contatos.add(contato);
			}
			
			return contatos;
		} catch (FileNotFoundException e) {
			return contatos;
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}
		
	}
}
