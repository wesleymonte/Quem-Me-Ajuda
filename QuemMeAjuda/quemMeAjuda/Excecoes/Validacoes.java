package quemMeAjuda.Excecoes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import quemMeAjuda.Entidades.Aluno.*;
import quemMeAjuda.Entidades.PedidoDeAjuda.PedidoDeAjuda;

public class Validacoes implements Serializable{
		
	private static final long serialVersionUID = -4420266001017456149L;

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
		if (!emails.contains(email)) throw new DadoInvalidoException(msg);
	}
	
	public void disciplinaJaEhTutor(String disciplina, List<Disciplina> disciplinas, String msg) throws Exception {
		for(Disciplina d: disciplinas)
			if(d.getNome().equals(disciplina)) throw new DadoInvalidoException(msg + "Ja eh tutor dessa disciplina");
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
	
	public void naoEhTutor(String matricula, Map<String, Aluno> alunos, String msg) throws Exception {
		if(!alunos.containsKey(matricula)) throw new DadoInvalidoException(msg + "Tutor nao encontrado");
	}
	
	public void matriculaVazia(String matricula, String msg) throws Exception {
		if(matricula.trim().isEmpty()) throw new DadoInvalidoException(msg + "matricula de aluno nao pode ser vazio ou em branco");
	}
	
	public void disciplinaVazia(String disciplina, String msg) throws Exception {
		if(disciplina.trim().isEmpty()) throw new DadoInvalidoException(msg + "disciplina nao pode ser vazio ou em branco");
	}
	
	public void horarioVazio(String horario, String msg) throws Exception {
		if(horario.trim().isEmpty()) throw new DadoInvalidoException(msg + "horario nao pode ser vazio ou em branco");
	}
	
	public void diaVazio(String dia, String msg) throws Exception {
		if(dia.trim().isEmpty()) throw new DadoInvalidoException(msg + "dia nao pode ser vazio ou em branco");
	}
	
	public void localVazio(String local, String msg) throws Exception {
		if(local.trim().isEmpty()) throw new DadoInvalidoException(msg + "local de interesse nao pode ser vazio ou em branco");
	}
	
	public void idAjudaInvalido(int id, Map<Integer, PedidoDeAjuda> pedidos, String msg) throws Exception {
		if(id < 0) throw new DadoInvalidoException(msg + "id nao pode menor que zero ");
		if(!(pedidos.containsKey(id))) throw new DadoInvalidoException(msg + "id nao encontrado ");
	}
	
	public void atributoInvalido(String atributo, String msg) throws Exception {
		if(atributo.trim().isEmpty()) throw new DadoInvalidoException(msg + "atributo nao pode ser vazio ou em branco");
		if(!atributo.equals("disciplina") && !atributo.equals("horario") && !atributo.equals("dia") && !atributo.equals("localInteresse")) 
			throw new DadoInvalidoException(msg + "atributo nao encontrado");
	}
	
	public void notaInvalida(double nota, String msg) throws Exception {
		if(nota < 0) throw new DadoInvalidoException(msg + "nota nao pode ser menor que 0");
		else if(nota > 5) throw new DadoInvalidoException(msg + "nota nao pode ser maior que 5");
	}
	
	public void emailTutorInvalidoOuNulo(String email, String msg) throws Exception {
		if(email == null) throw new DadoNuloException(msg + "emailTutor nao pode ser vazio ou nulo");
		else if(email.trim().isEmpty()) throw new DadoInvalidoException(msg + "emailTutor nao pode ser vazio ou nulo");
	}
	
	public void totalCentavosInvalido(int totalCentavos, String msg) throws Exception {
		if(totalCentavos < 0) throw new DadoInvalidoException(msg + "totalCentavos nao pode ser menor que zero");
	}
	
	public void ajudaJaFechada(int id, Map<Integer, PedidoDeAjuda> pedidos, String msg) throws Exception {
		if(pedidos.get(id).getFinalizacao()) throw new DadoInvalidoException(msg + "Ajuda ja avaliada");
	}
}