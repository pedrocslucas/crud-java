package br.com.agenda.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    
    //Nome do usuário MySQL
    private static final String USERNAME = "root";
    //Senha de usuário MySQL
    private static final String PASSWORD = "";
    //Caminho do Banco de Dados, porta, nome do BD
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/agenda";
    
    /*
        Conexão com o Banco de Dados
    */
    public static Connection createConnectionToMySQL() throws ClassNotFoundException{
        try{
            //Faz com que a classe seja carregada pela JVM
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            return connection;
        }catch(SQLException error){
            System.err.println("[ERROR] ConnectionFactory: "+error.getMessage());
            return null;
        }
    }
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //Recuperar uma conexão com o BD
        Connection conn = createConnectionToMySQL();
        if(conn != null){
            System.out.println("Conexão obtida com Sucesso!");
            conn.close();
        }
    }
    
}
