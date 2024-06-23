package Classes_Bases;

import Classes_extras.Consultas;
import Classes_extras.NumerosAlearios;
import Integraçao.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Membros  {

    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    Cartao cartao;

    public Membros(String nome, String cpf, String email, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;

        int cartao_id = NumerosAlearios.GerarNumeroAleatorio();
        int numero_cartao = NumerosAlearios.GerarNumeroAleatorio();
        cartao = new Cartao(numero_cartao, this.cpf, cartao_id);
    }

    public void cadastrarMembro() {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DatabaseUtil.getConnection();
            String sql = "INSERT INTO membros (nome, cpf, email, telefone) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, this.nome);
            stmt.setString(2, this.cpf);
            stmt.setString(3, this.email);
            stmt.setString(4, this.telefone);


            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Inserção realizada com sucesso.");
            }
            cartao.cadastrarCartao();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    public static void atualizarCadastro(String parametro, String cpf, String novoDado) {
        String sql = null;
        switch (parametro) {
            case "telefone":
                sql = "UPDATE membros SET telefone = ? WHERE cpf = ?";
                break;
            case "nome":
                sql = "UPDATE membros SET nome = ? WHERE cpf = ?";
                break;
            case "email":
                sql = "UPDATE membros SET email = ? WHERE cpf = ?";
                break;
            default:
                System.out.println("Parâmetro inválido.");
                return;
        }

        if (sql != null) {
            executarAtualizacao(sql, novoDado, cpf);
        }
    }

    private static void executarAtualizacao(String sql, String novoDado, String cpf) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, novoDado);
            stmt.setString(2, cpf);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Atualização realizada com sucesso.");
            } else {
                System.out.println("Nenhuma linha foi atualizada. Verifique o CPF informado.");
            }

            Consultas.gravarMembrosEmArquivo();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void deletarCadastro(String cpf) {
        Cartao.deletarCartao(cpf);

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DatabaseUtil.getConnection();
            String sql = "DELETE FROM membros WHERE cpf = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cpf);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Cadastro deletado com sucesso.");
            } else {
                System.out.println("Nenhuma linha foi deletada. Verifique o CPF informado.");
            }

            Consultas.gravarMembrosEmArquivo();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
