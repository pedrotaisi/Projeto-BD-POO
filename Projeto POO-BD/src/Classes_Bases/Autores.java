package Classes_Bases;

import Classes_extras.NumerosAlearios;
import Integraçao.DatabaseUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Autores {

    private String nome;
    private int autor_id;
    private String titulo_livro;
    Livros livros;

    public Autores(String nome, String titulo_livro) {
        this.nome = nome;
        this.autor_id = NumerosAlearios.GerarNumeroAleatorio();
        this.titulo_livro = titulo_livro;
        int id_livro = NumerosAlearios.GerarNumeroAleatorio();
        livros = new Livros(titulo_livro, this.autor_id, id_livro);
    }

    public void cadastrarAutor() {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DatabaseUtil.getConnection();
            String sql = "INSERT INTO autores (autor_id, nome) VALUES (?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, this.autor_id);
            stmt.setString(2, this.nome);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Autor cadastrado com sucesso.");
            }
            livros.cadastrarLivro();
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

    public static void adicionarLivro(String nome, String titulo_livro) {
        int id_livro = NumerosAlearios.GerarNumeroAleatorio();
        int autor_id = Autores.buscarIdPorNome(nome);
        Livros livros = new Livros(titulo_livro, autor_id , id_livro);
        livros.cadastrarLivro();
    }

    private static int buscarIdPorNome(String nome) {
        int autorId = -1; // Valor padrão caso não encontre o autor

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseUtil.getConnection();
            String sql = "SELECT autor_id FROM autores WHERE nome = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            rs = stmt.executeQuery();

            if (rs.next()) {
                autorId = rs.getInt("autor_id");
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

        return autorId;
    }

    public static void limparTabela() {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DatabaseUtil.getConnection();
            String sql = "TRUNCATE TABLE autores";
            stmt = conn.prepareStatement(sql);

            stmt.executeUpdate();
            System.out.println("Tabela emprestimos limpa com sucesso.");
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
