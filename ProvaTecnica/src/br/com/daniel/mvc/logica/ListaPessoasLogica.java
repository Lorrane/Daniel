package br.com.daniel.mvc.logica;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.daniel.jdbc.dao.PessoaDao;
import br.com.daniel.jdbc.modelo.Pessoa;

public class ListaPessoasLogica implements Logica{
	
	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		List <Pessoa> pessoas = new PessoaDao().getLista();
		request.setAttribute("pessoas", pessoas);
		
		return "/jsp/lista-pessoa.jsp";
	}

}
