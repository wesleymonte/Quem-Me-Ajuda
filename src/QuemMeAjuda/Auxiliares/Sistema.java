package QuemMeAjuda.Auxiliares;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import QuemMeAjuda.Entidades.*;

/**
 * Sistema Central
 * 
 * @author Wesley Monte, Matheus Silva, Lukas Nascimento
 *
 */
public class Sistema {
	private Map<String, Aluno> alunos = new HashMap<>();
	private Validacoes validacoes = new Validacoes();
	
	/**
	 * Cadastra um Aluno no sistema que será identificado por sua matricula.
	 * @param nome 
	 * 		nome do Aluno, no formato String.
	 * @param matricula 
	 * 		matricula do Aluno, no formato String.
	 * @param codigoCurso 
	 * 		inteiro que representa o código do curso.
	 * @param telefone 
	 * 		telefone do Aluno, no formato String.
	 * @param email 
	 * 		email do Aluno, no formato String.
	 *
	 * @throws DadoInvalidoException 
	 */
	public void cadastrarAluno(String nome, String matricula, int codigoCurso, String telefone, String email) throws Exception {
		String erroCadastrarAluno = "Erro no cadastro de aluno: ";
		validacoes.nomeInvalidoOuNulo(nome, erroCadastrarAluno);
		validacoes.emailInvalidoOuNulo(email, erroCadastrarAluno);
		validacoes.matriculaJaCadastrada(matricula, alunos, erroCadastrarAluno);
		alunos.put(matricula, new Aluno(nome, matricula, codigoCurso, telefone, email));
	}
	
	/**
	 * @param matricula 
	 * 		matricula do Aluno, no formato String.
	 * @return Representação textual do Aluno.
	 * @throws DadoInvalidoException 
	 */
	public String recuperaAluno(String matricula) throws Exception {
		String erroRecuperaAluno = "Erro na busca por aluno: ";
		validacoes.alunoNaoCadastrado(matricula, alunos, erroRecuperaAluno);
		return alunos.get(matricula).toString();
	}
	
	/**
	 * @return representação textual de todos os Alunos cadastrados.
	 */

	public String listarAlunos() {
		ArrayList<Aluno> listaDeAlunos = new ArrayList<Aluno>();
		for(Aluno a: alunos.values()) {
			listaDeAlunos.add(a);
		}
		Collections.sort(listaDeAlunos, new ComparadorNomeAluno());
		String listaAlunos = "";
		for(Aluno a: listaDeAlunos) {
			listaAlunos += a.toString() + ", ";
		}
		listaAlunos = listaAlunos.substring(0, listaAlunos.length() - 2);
		return listaAlunos;
	}
	
	/**
	 * @param matricula 
	 * 		matricula do Aluno, no formato String.
	 * @param atributo 
	 * 		atributo desejado do Aluno, no formato String.
	 * @return representação textual do atributo do Aluno.
	 * @throws DadoInvalidoException 
	 */
	public String getInfoAluno(String matricula, String atributo) throws Exception {
		String erroGetInfoAluno = "Erro na obtencao de informacao de aluno: ";
		validacoes.alunoNaoCadastrado(matricula, alunos, erroGetInfoAluno);
		switch (atributo.toLowerCase()){
			case "nome":
				return alunos.get(matricula).getNome();
			case "codigo do curso":
				return Integer.toString(alunos.get(matricula).getCodigoCurso());
			case "telefone":
				return alunos.get(matricula).getTelefone();
			case "email":
				return alunos.get(matricula).getEmail();
			default:
				return null;
		}
	}
	
	/**
	 * Torna o Aluno um Tutor
	 * @param matricula
	 * 		matricula do Aluno que se tornará Tutor, no formato String.
	 * @param disciplina
	 * 		disciplina que o Aluno será o Tutor, no formato String.
	 * @param proficiencia
	 * 		valor inteiro que define o quão hábil na disciplina o Aluno é.
	 * @throws DadoInvalidoException 
	 */
	public void tornarTutor(String matricula, String disciplina, int proficiencia) throws Exception {
		String erroTornarTutor = "Erro na definicao de papel: ";
		validacoes.tutorNaoCadastrado(matricula, alunos, erroTornarTutor);
		Aluno alunoViraTutor = alunos.get(matricula);
		if(alunoViraTutor instanceof Tutor) {
			List<String> disciplinasDoTutor = ((Tutor) alunoViraTutor).getDisciplinas();
			validacoes.disciplinaJaEhTutor(disciplina, disciplinasDoTutor, erroTornarTutor);
			validacoes.proficienciaInvalida(proficiencia, erroTornarTutor);
			disciplinasDoTutor.add(disciplina);
		}
		else {
			Aluno alunoTutor = new Tutor(alunoViraTutor.getNome(), alunoViraTutor.getMatricula(), alunoViraTutor.getCodigoCurso(),
										 alunoViraTutor.getTelefone(), alunoViraTutor.getEmail(), disciplina, proficiencia);
			alunos.remove(matricula);
			alunos.put(matricula, alunoTutor);
		}
	}
	
	/**
	 * @param matricula
	 * 		matricula do Tutor procurado, no formato String.
	 * @return representação textual do Tutor procurado.
	 * @throws DadoInvalidoException 
	 */
	public String recuperaTutor(String matricula) throws Exception {
		String erroRecuperaTutor = "Erro na busca por tutor: ";
		validacoes.tutorNaoCadastrado(matricula, alunos, erroRecuperaTutor);
		return alunos.get(matricula).toString();
	}
	
	/**
	 * @return representação textual de todos os Tutores cadastrados.
	 */
	public String listarTutores() {
		ArrayList<Aluno> listaDeTutores = new ArrayList<Aluno>();
		for(Aluno a: alunos.values()) {
			if(a instanceof Tutor) listaDeTutores.add(a);
		}
		Collections.sort(listaDeTutores, new ComparadorNomeAluno());
		String listaTutores = "";
		for(Aluno a: listaDeTutores) {
			listaTutores += a.toString() + ", ";
		}
		return listaTutores.substring(0, listaTutores.length() - 2);
	}
	
	/**
	 * Cadastro de um Horario (que representa o horario de atendimento) do Tutor.
	 * @param email 
	 * 		String email do tutor.
	 * @param horario
	 * 		String horario a cadastrar.
	 * @param dia
	 * 		String dia a cadastrar.
	 * @throws DadoInvalidoException 
	 */
	public void cadastrarHorario(String email, String horario, String dia) throws Exception {
		String erroCadastrarHorario = "Erro no cadastrar horario: ";
		validacoes.emailInvalidoOuNulo(email, erroCadastrarHorario);
		validacoes.horarioInvalido(horario, dia, erroCadastrarHorario);
		validacoes.tutorEmailNaoCadastrado(email, alunos, erroCadastrarHorario);
		for(Aluno tutor : alunos.values())
			if(tutor.getEmail().equals(email))
				if(tutor instanceof Tutor)
					((Tutor) tutor).cadastrarHorario(horario, dia);
	}
	
	/**
	 * Cadastro de local para atendimento de um Tutor. O local não está relacionado aos horarios do Tutor.
	 * @param email
	 * 		String email do Tutor.
	 * @param local
	 * 		String local de atendimento do Tutor.
	 * @throws DadoInvalidoException 
	 */
	public void cadastrarLocalDeAtendimento(String email, String local) throws Exception {
		String erroCadastrarLocal = "Erro no cadastrar local de atendimento: ";
		validacoes.localInvalidoOuNulo(local, erroCadastrarLocal);
		validacoes.emailInvalidoOuNulo(email, erroCadastrarLocal);
		validacoes.tutorEmailNaoCadastrado(email, alunos, erroCadastrarLocal);
		for(Aluno tutor : alunos.values())
			if(tutor.getEmail().equals(email))
				if(tutor instanceof Tutor)
					((Tutor) tutor).cadastrarLocal(local);
	}

	/**
	 * Consulta de Horario de um tutor.
	 * @param email
	 * 		String email do Tutor
	 * @param horario
	 * 		String horario de atendimento do Tutor a ser verificado
	 * @param dia
	 * 		String dia de atendimento do Tutor a ser verificado
	 * @return boolean que indica a consulta de um Horario.
	 */
	public boolean consultaHorario(String email, String horario, String dia) {
		Horario horarioProcurado = new Horario(horario, dia);
		for(Aluno tutor : alunos.values())
			if(tutor.getEmail().equals(email))
				if(tutor instanceof Tutor)
					for(Horario h : ((Tutor) tutor).getHorarios())
						if(h.equals(horarioProcurado)) return true;
		return false;
	}
	
	/**
	 * Consulta de um local de atendimento de um Tutor.
	 * @param email
	 * 		String email do Tutor.
	 * @param local
	 * 		String local de atendimento a ser verificado de um Tutor.
	 * @return boolean que indica a consulta de um Local.
	 */
	public boolean consultaLocal(String email, String local) {
		for(Aluno tutor : alunos.values())
			if(tutor.getEmail().equals(email))
				if(tutor instanceof Tutor)
					return ((Tutor) tutor).getLocais().contains(local);
		return false;
	}
}