package br.com.agenda.dao;

import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.model.Contato;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {
    
    //CRUD (Create, Read, Uptade, Delete)
    
    public void save(Contato contato){
        String sql = "INSERT INTO contatos("
                + "nome, idade, dataCadastro) VALUES ("
                + "?, ?, ?)";
        
        Connection conn = null;
        
        //Preparar um estrutura para executar um java em sql
        PreparedStatement pstm = null;
        
        try{
            //Criar uma conexão com o BD
            conn = ConnectionFactory.createConnectionToMySQL();
            //criando uma PreparedStatement para executar uma query
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            //Adicionando os valores esperados pela query
            pstm.setString(1, contato.getNome());
            pstm.setInt(2, contato.getIdade());
            pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));
            
            //Excecutando query
            pstm.execute();
            
            //Mensagem para o cliente
            System.out.println("Contato salvo com Sucesso!");
            
        }catch(Exception error){
            System.err.println("[ERROR] ContatoDAO: "+error.getMessage());
        }finally{
            //Fechando as conexões
            try{
                if(pstm != null)
                    pstm.close();
                
                if(conn != null)
                    conn.close();
            }catch (Exception err){
                err.printStackTrace();
            }
        }
    }
    
    public List<Contato> getContatos(){
        String sql = "SELECT *FROM contatos";
        
        List<Contato> contatos = new ArrayList<Contato>();
        
        Connection conn = null;
        PreparedStatement pstm = null;
        //Classe que vai recuperar os dados do banco
        ResultSet rset = null;
        
        try{
            conn = ConnectionFactory.createConnectionToMySQL();
            
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            
            rset = pstm.executeQuery();
            
            while(rset.next()){
                Contato contato = new Contato();
                //recuperar o id
                contato.setId(rset.getInt("id"));
                //recuperar o nome
                contato.setNome(rset.getString("nome"));
                //recuperar a idade
                contato.setIdade(rset.getInt("idade"));
                //recuperar a data de cadastro
                contato.setDataCadastro(rset.getDate("dataCadastro"));
                
                contatos.add(contato);
            }
        }catch(Exception err){
            System.err.println("[ERROR] ContatoDAO: "+err.getMessage());
        }finally{
            try{
                if(rset != null)
                    rset.close();
                if(pstm != null)
                    pstm.close();
                if(conn != null)
                    conn.close();
            }catch(Exception err){
                err.printStackTrace();
            }
        }
        return contatos;
    }
    
    public void update(Contato contato){
        
        String sql = "UPDATE contatos SET nome = ?,"
                + "idade = ?, dataCadastro = ?"
                + "WHERE id = ?";
        
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
            //Criar conexão com o Banco
            conn = ConnectionFactory.createConnectionToMySQL();
            //Criar a classe para executar a query
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            //Adicionar os dados para atualizar
            pstm.setString(1, contato.getNome());
            pstm.setInt(2, contato.getIdade());
            pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));
            
            //Qual o ID do registro que deseja executar?
            pstm.setInt(4, contato.getId());
            
            //Executar a query
            pstm.execute();
            
        }catch(Exception err){
            System.err.println("[ERROR] ContatoDAO: "+err.getMessage());
        }finally{
            try{
                if(pstm != null)
                    pstm.close();
                if(conn != null)
                    conn.close();
            }catch(Exception err){
                err.printStackTrace();
            }
        }
    }
    
    public void deleteById(int id){
        String sql = "DELETE FROM contatos WHERE id = ?";
        
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try{
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            
            pstm.setInt(1, id);
            
            pstm.execute();
        }catch(Exception err){
            System.err.println("[ERROR] ContatoDAO: "+err.getMessage());
        }finally{
            try{
                if(pstm != null)
                    pstm.close();
                if(conn != null)
                    conn.close();
            }catch(Exception err){
                err.printStackTrace();
            }
        }
    }
}
