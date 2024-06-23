package Classes_Bases;

import Classes_extras.NumerosAlearios;
import Integraçao.DatabaseUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Emprestimos {

    private int Emprestimo_id;
    private int cartao_id;
    private int livro_id;
    private String data_empretimo;
    private String data_devolucao;

    public Emprestimos(int cartao_id, int livro_id, String data_empretimo, String data_devolucao) {
        this.Emprestimo_id = NumerosAlearios.GerarNumeroAleatorio();
        this.cartao_id = cartao_id;
        this.livro_id = livro_id;
        this.data_empretimo = data_empretimo;
        this.data_devolucao = data_devolucao;
    }

    public void cadastraEmprestimo() {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DatabaseUtil.getConnection();
            String sql = "INSERT INTO emprestimos (Emprestimo_id, cartao_id, livro_id, data_emprestimo, data_devolucao) VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, this.Emprestimo_id);
            stmt.setInt(2, this.cartao_id );
            stmt.setInt(3, this.livro_id);
            stmt.setString(4, this.data_empretimo);
            stmt.setString(5, this.data_devolucao);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Empréstimo cadastrado com sucesso.");
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

    public static void deletarEmprestimosPorIdCartao(int cartao_id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DatabaseUtil.getConnection();
            String sql = "DELETE FROM emprestimos WHERE cartao_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, cartao_id);

            stmt.executeUpdate();
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
