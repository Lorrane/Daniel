package br.com.daniel.mvc.logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.daniel.jdbc.dao.PessoaDao;
import br.com.daniel.jdbc.modelo.Pessoa;

public class MostraPessoaLogica implements Logica {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Pessoa pessoa = new PessoaDao().busca(request.getParameter("id"));
		request.setAttribute("pessoa", pessoa);
		
		return "/jsp/altera-pessoa.jsp";
	}

}
