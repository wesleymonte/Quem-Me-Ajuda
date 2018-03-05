package quemMeAjuda2.validacoes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import quemMeAjuda2.Entidades.Aluno.*;
import quemMeAjuda2.Excecoes.DadoInvalidoException;
import quemMeAjuda2.Excecoes.DadoNuloException;

public class Validacoes {
		
	public void nomeInvalidoOuNulo(String nome, String msg) throws Exception {
		if(nome == null) throw new DadoNuloException(msg + "Nome nao pode ser vazio ou nulo");
		if(nome.trim().isEmpty()) throw new DadoInvalidoException(msg + "Nome nao pode ser vazio ou nulo");
	}

	public void emailInvalidoOuNulo(String email, String msg) throws Exception {
		String msgEmailInvalido = "Email invalido";
		if(email == null) throw new DadoNuloException(msg + msgEmailInvalido);
		int a = 0;
		boolean erro = false;
		if(email.trim().isEmpty()) {
			erro = true;
			msgEmailInvalido = "email nao pode ser vazio ou em branco";
		}
		else if(!email.contains("@")) erro = true;
		else {
			a = email.indexOf("@");
			if(a == 0 || a == email.length() - 1) erro = true;
		}
		if(erro) throw new DadoInvalidoException(msg + msgEmailInvalido);
	}
	
	public void tutorEmailNaoCadastrado(String email, Map<String, Aluno> alunos, String msg) throws Exception {
		List<String> emails = new ArrayList<String>();
		for (Aluno a : alunos.values()) 
			if (a.isTutor()) emails.add(a.getEmail());
		if (!emails.contains(email)) throw new DadoInvalidoException (msg + "tutor nao cadastrado");
	}
	
	public void disciplinaJaEhTutor(String disciplina, List<String> disciplinas, String msg) throws Exception {
		if(disciplinas.contains(disciplina)) throw new DadoInvalidoException(msg + "Ja eh tutor dessa disciplina"); 
	}
	
	public void proficienciaInvalida(int proficiencia, String msg) throws Exception{
		if(proficiencia < 0 || proficiencia > 5) throw new DadoInvalidoException(msg + "Proficiencia invalida");
	}
	
	public void horarioInvalido(String horario, String dia, String msg) throws Exception {
		String msgErroHorario = "horario nao pode ser vazio ou em branco";
		String msgErroDia = "dia nao pode ser vazio ou em branco";
		
		if(horario.trim().isEmpty()) throw new DadoInvalidoException(msg + msgErroHorario);
		else if (dia.trim().isEmpty()) throw new DadoInvalidoException(msg + msgErroDia);
	}
	
	public void localInvalidoOuNulo(String local, String msg) throws Exception {
		if(local == null) throw new DadoNuloException(msg + "local nao pode ser vazio ou em branco");
		if(local.trim().isEmpty()) throw new DadoInvalidoException(msg + "local nao pode ser vazio ou em branco");
	}
	
	public void matriculaJaCadastrada(String matricula, Map<String, Aluno> alunos, String msg) throws Exception {
		if(alunos.containsKey(matricula)) throw new DadoInvalidoException(msg + "Aluno de mesma matricula ja cadastrado");
	}
	
	public void alunoNaoCadastrado(String matricula, Map<String, Aluno> alunos, String msg) throws Exception {
		if(!alunos.containsKey(matricula)) throw new DadoInvalidoException(msg + "Aluno nao encontrado");
	}
	
	////////////////////////////// DE ACORDO COM O US4_TEST /////////////////////////////////////////////
	public void matriculaVaziaOuNula(String matricula, String msg) throws Exception {
		if (matricula == null) throw new DadoNuloException(msg + "matricula de aluno nao pode ser vazio ou em branco");
		if (matricula.trim().isEmpty()) throw new DadoInvalidoException(msg + "matricula de aluno nao pode ser vazio ou em branco");
	}
	
	public void disciplinaVaziaOuNula(String disciplina, String msg) throws Exception {
		if (disciplina == null) throw new DadoNuloException(msg + "disciplina nao pode ser vazio ou em branco");
		if (disciplina.trim().isEmpty()) throw new DadoInvalidoException(msg + "disciplina nao pode ser vazio ou em branco");
	}
	
	public void horarioVazioOuNulo(String horario, String msg) throws Exception {
		if (horario == null) throw new DadoNuloException(msg + "horario nao pode ser vazio ou em branco");
		if (horario.trim().isEmpty()) throw new DadoInvalidoException(msg + "horario nao pode ser vazio ou em branco");
	}
	
	public void diaVazioOuNulo(String dia, String msg) throws Exception {
		if (dia == null) throw new DadoNuloException(msg + "dia nao pode ser vazio ou em branco");
		if (dia.trim().isEmpty()) throw new DadoInvalidoException(msg + "dia nao pode ser vazio ou em branco");
	}
	
	public void localVazioOuNulo(String local, String msg) throws Exception {
		if (local == null) throw new DadoNuloException(msg + "local de interesse nao pode ser vazio ou em branco");
		if (local.trim().isEmpty()) throw new DadoInvalidoException(msg + "local de interesse nao pode ser vazio ou em branco");
	}
}