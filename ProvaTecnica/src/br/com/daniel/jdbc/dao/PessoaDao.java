package br.com.daniel.jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.daniel.jdbc.ConnectionFactory;
import br.com.daniel.jdbc.modelo.Pessoa;

public class PessoaDao {
	
	private Connection connection;
	
	public PessoaDao() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void adiciona(Pessoa pessoa) {
		String sql = "insert into pessoa "+
				"(cpf, nome, email, dataNascimento, sexo, estadoCivil, ativo)"+
				"values(?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement stmt = connection
					.prepareStatement(sql);
			
			stmt.setString(1, pessoa.getCpf());
			stmt.setString(2, pessoa.getNome());
			stmt.setString(3, pessoa.getEmail());
			stmt.setString(4, pessoa.getDataNascimento());
			stmt.setString(5, pessoa.getSexo());
			stmt.setString(6, pessoa.getEstadoCivil());
			stmt.setBoolean(7, pessoa.isAtivo());
			
			stmt.execute();
			stmt.close();
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}			
	}

	public List<Pessoa> getLista(){
		try {
			List<Pessoa> pessoas = new ArrayList<Pessoa>();
			PreparedStatement stmt = this.connection.prepareStatement("select * from pessoas");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Pessoa pessoa = new Pessoa();
				
				
				pessoa.setCpf(rs.getString("cpf"));
				pessoa.setNome(rs.getString("nome"));
				pessoa.setEmail(rs.getString("email"));
				pessoa.setDataNascimento(rs.getString("dataNascimento"));
				pessoa.setSexo(rs.getString("sexo"));
				pessoa.setEstadoCivil(rs.getString("estadoCivil"));
				pessoa.setAtivo(rs.getBoolean("ativo"));
				
				pessoas.add(pessoa);
			}
			rs.close();
			stmt.close();
			return pessoas;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Pessoa busca(String cpf) {
		Pessoa pessoa = new Pessoa();
		
		String sql = "select * from contato where cpf= ?";
		
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, cpf);
		
			ResultSet rs = stmt.executeQuery();
			stmt = this.connection.prepareStatement(sql);
			
			pessoa.setCpf(cpf);
			pessoa.setNome(rs.getString("nome"));
			pessoa.setEmail(rs.getString("email"));
			pessoa.setDataNascimento(rs.getString("dataNascimento"));
			pessoa.setSexo(rs.getString("sexo"));
			pessoa.setEstadoCivil(rs.getString("estadoCivil"));
			pessoa.setAtivo(rs.getBoolean("ativo"));
			
			
		}catch (SQLException e){
			throw new RuntimeException(e);
		}
		
		return pessoa;
	}
	
	public Pessoa buscaPorNome(String nome) {
		Pessoa pessoa = new Pessoa();
		
		String sql = "select * from contato where nome like ?";
		
		try {
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, nome);
		
			ResultSet rs = stmt.executeQuery();
			stmt = this.connection.prepareStatement(sql);
			
			pessoa.setCpf(rs.getString("cpf"));
			pessoa.setNome(rs.getString("nome"));
			pessoa.setEmail(rs.getString("email"));
			pessoa.setDataNascimento(rs.getString("dataNascimento"));
			pessoa.setSexo(rs.getString("sexo"));
			pessoa.setEstadoCivil(rs.getString("estadoCivil"));
			pessoa.setAtivo(rs.getBoolean("ativo"));
			
			
		}catch (SQLException e){
			throw new RuntimeException(e);
		}
		
		return pessoa;
	}
	
	public void altera(Pessoa pessoa) {
		Pessoa pessoa1 = new Pessoa();
		PreparedStatement stmt;
		String sql = "update Pessoa "
				+ "set nome=?, email=?, dataNascimento=?, sexo=?, estadoCivil=?, ativo=?"
				+ "where cpf=?";
		
		try {
			stmt = this.connection.prepareStatement(sql);
			
			stmt.setString(7, pessoa1.getCpf());
			stmt.setString(1, pessoa1.getNome());
			stmt.setString(2, pessoa1.getEmail());
			stmt.setString(3, pessoa1.getDataNascimento());
			stmt.setString(4, pessoa1.getSexo());
			stmt.setString(5, pessoa1.getEstadoCivil());
			stmt.setBoolean(6, pessoa1.isAtivo());
			
			stmt.execute();
			stmt.close();
		}catch (SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	public void remove(String cpf) {
		PreparedStatement stmt;
		String sql = "detele from Pessoa where cpf=?";
		
		try {
			stmt = this.connection.prepareStatement(sql);
			
			stmt.setString(1, cpf);
			
			stmt.execute();
			stmt.close();
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}

	
