package Classes_Bases;

import Classes_extras.Consultas;
import Integraçao.DatabaseUtil;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;


public class Cartao {
    private int numero_cartao;
    private String cpf_membro;
    private int cartao_id;

    public Cartao(int numero_catao, String cpf_membro, int cartao_id) {
        this.numero_cartao = numero_catao;
        this.cpf_membro = cpf_membro;
        this.cartao_id = cartao_id;
    }

    public void cadastrarCartao() {

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DatabaseUtil.getConnection();
            String sql = "INSERT INTO cartoes (cartao_id, numero_cartao, cpf_membro) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, this.cartao_id);
            stmt.setInt(2, this.numero_cartao);
            stmt.setString(3, this.cpf_membro);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Inserção realizada com sucesso.");
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


    public static void deletarCartao(String cpf) {
        // Primeiro, buscar o cartao_id correspondente ao CPF informado
        int cartaoIdParaDeletar = buscarCartaoIdPorCpf(cpf);

        // Se o cartaoIdParaDeletar for -1, significa que não encontrou o CPF
        if (cartaoIdParaDeletar == -1) {
            System.out.println("Não foi encontrado um cartão associado ao CPF informado.");
            return;
        }

        Emprestimos.deletarEmprestimosPorIdCartao(cartaoIdParaDeletar);

        // Deletar o cartão com o cartao_id encontrado
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DatabaseUtil.getConnection();
            String sql = "DELETE FROM cartoes WHERE cartao_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, cartaoIdParaDeletar);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Cartão deletado com sucesso.");
            } else {
                System.out.println("Nenhum cartão foi deletado. Verifique o cartão_id encontrado.");
            }
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

    private static int buscarCartaoIdPorCpf(String cpf) {
        int cartaoId = -1; // Valor padrão caso não encontre o cartão

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseUtil.getConnection();
            String sql = "SELECT cartao_id FROM cartoes WHERE cpf_membro = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cpf);
            rs = stmt.executeQuery();

            if (rs.next()) {
                cartaoId = rs.getInt("cartao_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return cartaoId;
    }

}
