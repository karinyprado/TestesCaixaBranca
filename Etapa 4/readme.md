# Projeto Login - Sistema de Autenticação

Este repositório contém a implementação de uma classe Java responsável pela autenticação de usuários utilizando o banco de dados MySQL. O projeto foi desenvolvido como um exemplo simples para demonstrar o uso de JDBC na integração com bancos de dados e validação de credenciais de login.

## Funcionalidades

- Conexão com o banco de dados MySQL utilizando JDBC.
- Verificação de credenciais de login (usuário e senha).
- Retorno do nome do usuário autenticado, caso a validação seja bem-sucedida.

## Estrutura do Código

### Classe `login`
- **Método `conectarBD()`**: Estabelece a conexão com o banco de dados.
- **Variáveis**:
  - `nome`: Armazena o nome do usuário autenticado.
  - `result`: Indica o sucesso ou falha na autenticação.
- **Método `verificarUsuario(String login, String senha)`**:
  - Recebe as credenciais (login e senha) como parâmetros.
  - Executa uma consulta SQL no banco para verificar a existência do usuário.
  - Retorna `true` se o login for bem-sucedido ou `false` caso contrário.

## Configuração do Ambiente

1. **Banco de Dados**: Certifique-se de ter um banco MySQL configurado.
   - Nome do banco: `test`
   - Tabela: `usuarios`
   - Colunas esperadas: `login`, `senha`, `nome`

2. **Configuração do Driver MySQL**:
   - Baixe o driver JDBC do MySQL e adicione ao classpath do projeto.

3. **Ajustes no Código**:
   - Atualize as credenciais (`user` e `password`) e o endereço do banco de dados na URL de conexão em `conectarBD()`.

## Pontos de Melhorias

1. **Validação SQL**:
   - Atualmente, o código está vulnerável a **SQL Injection**. Recomenda-se utilizar **PreparedStatement** para proteger a aplicação.
   
2. **Tratamento de Exceções**:
   - Os blocos `catch` não estão tratando adequadamente as exceções. É importante adicionar logs ou lançar erros para monitoramento.

3. **Refatoração**:
   - Modularizar as responsabilidades e seguir os princípios de arquitetura definidos para o sistema.

