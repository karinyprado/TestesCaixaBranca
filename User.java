package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class User {
    public Connection conectarBD () {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://127.0.0.1/test?user=lopes&password=123";
            conn = DriverManager.getConnection(url);
        }catch (Exception e) { }
        return conn; }

    public String nome="";
    public boolean result = false;
    public boolean verificarUsuario(String login, String senha) {
        String sql = "SELECT nome FROM usuarios WHERE login = ? AND senha = ?";
        try (Connection conn = conectarBD();
            PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, login);
            st.setString(2, senha);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    result = true;
                    nome = rs.getString("nome");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
 //fim da class