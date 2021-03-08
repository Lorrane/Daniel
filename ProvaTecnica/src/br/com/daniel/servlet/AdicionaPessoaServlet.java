package br.com.daniel.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.daniel.jdbc.dao.PessoaDao;
import br.com.daniel.jdbc.modelo.Pessoa;

@SuppressWarnings("serial")
@WebServlet("/WEB-INF/jsp/adicionaPessoa")
public class AdicionaPessoaServlet extends HttpServlet{
	
	String cpf, nome, email, dataNascimento, sexo, estadoCivil;
	boolean ativo;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		cpf 		= request.getParameter("cpf");
		nome 		= request.getParameter("nome");
		email		= request.getParameter("email");
		dataNascimento	= request.getParameter("dataNascimento");
		sexo 		= request.getParameter("sexo");
		ativo 		= Boolean.getBoolean(request.getParameter("ativo"));
		
		
		
		//configuração de montagem de model e gravação no banco chamando dao
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(cpf);
		pessoa.setNome(nome);
		pessoa.setEmail(email);
		pessoa.setDataNascimento(dataNascimento);
		pessoa.setSexo(sexo);
		pessoa.setEstadoCivil(estadoCivil);
		pessoa.setAtivo(ativo);
		
		PessoaDao dao = new PessoaDao();
		dao.adiciona(pessoa);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/pessoa-adicionada.jsp");
		rd.forward(request, response);
		
	}

}
