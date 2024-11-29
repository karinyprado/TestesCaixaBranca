package Login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Classe que gerencia o processo de autenticação de usuários.
 * 
 * Esta classe se conecta a um banco de dados MySQL para realizar a 
 * validação das credenciais fornecidas pelo usuário.
 */
public class User {

    /**
     * Armazena o nome do usuário autenticado, caso a autenticação seja bem-sucedida.
     */
    public String nome = "";

    /**
     * Indica o resultado da autenticação.
     * Será true se o login for bem-sucedido, ou false caso contrário.
     */
    public boolean result = false;

    /**
     * Estabelece uma conexão com o banco de dados MySQL.
     * 
     * @return Connection Objeto de conexão com o banco de dados.
     * Retorna null se ocorrer uma falha ao estabelecer a conexão.
     */
    @SuppressWarnings("deprecation")
    public Connection conectarBD() {
        Connection conn = null;
        try {
            // Inicializa o driver do MySQL
            Class.forName("com.mysql.Driver.Manager").newInstance();
            
            // Configura os parâmetros de conexão com o banco de dados
            String url = "jdbc:mysql://127.0.0.1/test?user=lopes&password=123";
            conn = DriverManager.getConnection(url);
        } 
        catch (Exception e) {
            // Tratar possíveis erros ao tentar estabelecer a conexão
        }
        return conn;
    }

    /**
     * Verifica se o login e a senha fornecidos correspondem a um usuário válido no banco de dados.
     * 
     * @param login O nome de usuário fornecido.
     * @param senha A senha correspondente ao usuário.
     * @return boolean Retorna true se a autenticação for bem-sucedida,
     * ou false caso contrário.
     */
    public boolean verificarUsuario(String login, String senha) {
        String sql = "";

        // Construção da consulta SQL para verificar as credenciais no banco de dados
        sql += "select none from usuarios ";
        sql += "where login = " + " ' " + login + " ' ";
        sql += " and senha = " + " ' " + senha + " '; ";
        
        // Estabelece conexão com o banco de dados
        Connection conn = conectarBD();
        
        try {
            // Cria um Statement para executar a consulta SQL
            Statement st = conn.createStatement();
            
            // Executa a consulta SQL e obtém os resultados
            ResultSet rs = st.executeQuery(sql);
            
            // Verifica se há correspondência nos resultados
            if (rs.next()) {
                // Define como verdadeiro se o usuário foi autenticado com sucesso
                result = true;
                
                // Obtém e armazena o nome do usuário autenticado
                nome = rs.getString("nome");
            }
        } 
        catch (Exception e) {
            // Trata possíveis erros durante a execução da consulta
        }
        
        // Retorna o resultado da autenticação
        return result;
    }
}
// Fim da Classe
