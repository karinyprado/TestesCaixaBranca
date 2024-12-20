## ETAPA 1

### Dentro do seu repositório, crie um Readme explicando os erros encontrados;

**1 - Classe do Driver incorreta ***(Linha 12)***:**

`Class.forName("com.mysql.Driver.Manager").newInstance();`

A classe para o driver MySQL JDBC deve ser "com.mysql.cj.jdbc.Driver" em vez de "com.mysql.Driver.Manager".
Desde a versão do Java 9, o método newInstace() não é mais utilizado.

**2 - Instrução SQL incorreta ***(Linha 24)***:**

DE: `sql += "select none from usuarios ";`
PARA: `sql += "select nome from usuarios ";`

Provavelmente ocorreu um erro de digitação, pois none não é uma coluna. O correto seria "nome".

**3 - Concatenação insegura de strings para SQL ***(Linhas 25 e 26)***:**

DE: `sql += "where login = " + " ' " + login + " ' ";`
`sql += " and senha = " + " ' " + senha + " '; ";`

PARA: `String sql = "SELECT nome FROM usuarios WHERE login = ? AND senha = ?";`
`PreparedStatement st = conn.prepareStatement(sql);`
`st.setString(1, login);`
`st.setString(2, senha);`

Usar PreparedStatement com parâmetros evita o problema de SQL Injection.
Possuía espaços entre as aspas, podendo causar problemas com a consulta.
Tambem adicionei a importação "import java.sql.PreparedStatement;" para o pacote java.sql.

**4 - Não fechamento de recursos ***(Linhas 19 à 35)***:**

ALTERACAO:

>public boolean verificarUsuario(String login, String senha) {
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


Os recursos Connection, Statement e ResultSet devem ser fechados para evitar vazamento de recursos.

**5 - Adicionei `e.printStackTrace();` dentro dos blocos catch para permitir a saída de mensagens de erro ***(Linha 33)***.**
