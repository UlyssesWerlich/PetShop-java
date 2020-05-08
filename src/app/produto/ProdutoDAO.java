package app.produto;

import app.conexao.ConnectionFactory;
import app.produto.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

public class ProdutoDAO {

	Connection con = null;
	
	public void adiciona(Produto produto) {
		if (produto != null) {
			try {
				con = ConnectionFactory.getConnection();
				PreparedStatement stmt;
				stmt = con.prepareStatement("insert into produto(descricao, qtd, preco)" + " values(?,?,?)");

				stmt.setString(1, produto.getDescricao());
				stmt.setInt(2, produto.getQtd());
				stmt.setDouble(3, produto.getPreco());

				stmt.execute();
				stmt.close();

			} catch (SQLException ex) {
				Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
	
	public void remove(Produto produto) {
		if (produto != null) {
			try {
				con = ConnectionFactory.getConnection();
				PreparedStatement stmt = con.prepareStatement("delete from produto"
					+ " where id=" + produto.getId());
				stmt.execute();
				ConnectionFactory.closeConnection(con,stmt);
				
			} catch (Exception e){
				JOptionPane.showMessageDialog(null, "Erro ao excluir produto");
			}
		}
	}
	
	public void altera(Produto produto) {
		if (produto != null) {
			try {
				con = ConnectionFactory.getConnection();
				PreparedStatement stmt = con.prepareStatement("update produto set "
						+ "descricao=?, qtd=?, preco=? where id=?");
				stmt.setString(1, produto.getDescricao());
				stmt.setInt(2, produto.getQtd());
				stmt.setDouble(3, produto.getPreco());
				stmt.setInt(4, produto.getId());
				
				stmt.execute();
				ConnectionFactory.closeConnection(con,stmt);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro ao excluir produto"
						+ "\nMensagem:\n" + e.getMessage());
			}
		}
	}
	
	public Produto getProduto(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Produto produto = new Produto();
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement("select * from produto where id=?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				produto.setId(rs.getInt("id"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setQtd(rs.getInt("qtd"));
				produto.setPreco(rs.getDouble("preco"));
			}
			ConnectionFactory.closeConnection(con,stmt, rs );
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao listar produtos" + e.getMessage());
		}
		return produto;
	}
	
	public List<Produto> getListaProduto(){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Produto> produtos = new ArrayList<Produto>();
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement("select * from produto");
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				Produto produto = new Produto();
				
				produto.setId(rs.getInt("id"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setQtd(rs.getInt("qtd"));
				produto.setPreco(rs.getDouble("preco"));
				produtos.add(produto);
			}
			ConnectionFactory.closeConnection(con,stmt, rs );
		} catch (Exception e) {
			throw new RuntimeException(e);
			//JOptionPane.showMessageDialog(null, "Erro ao listar contatos" + e.getMessage());
		}
		return produtos;
	}
	
	public List<Produto> getListaProdutoPorDescricao(String descricao){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Produto> produtos = new ArrayList<Produto>();
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.prepareStatement("select * from produto where descricao like ?");
			stmt.setString(1, "%" + descricao + "%");
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				Produto produto = new Produto();
				
				produto.setId(rs.getInt("id"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setQtd(rs.getInt("qtd"));
				produto.setPreco(rs.getDouble("preco"));
				produtos.add(produto);
			}
			ConnectionFactory.closeConnection(con,stmt, rs );
		} catch (Exception e) {
			throw new RuntimeException(e);
			//JOptionPane.showMessageDialog(null, "Erro ao listar contatos" + e.getMessage());
		}
		return produtos;
	}
}
