package Classes_Bases;

import Classes_extras.Consultas;
import Integraçao.DatabaseUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Livros{

    private String titulo;
    private int autor_id;
    private int livro_id;

    public Livros(String titulo, int autor_id, int livro_id) {
        this.titulo = titulo;
        this.autor_id = autor_id;
        this.livro_id = livro_id;
    }

    public void cadastrarLivro() {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DatabaseUtil.getConnection();
            String sql = "INSERT INTO livros (livro_id, titulo, autor_id) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, this.livro_id);
            stmt.setString(2, this.titulo);
            stmt.setInt(3, this.autor_id);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Livro cadastrado com sucesso.");
            }
            Consultas.escreverLivrosEmArquivo();

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
