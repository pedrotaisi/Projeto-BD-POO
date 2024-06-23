package Classes_extras;

import Integraçao.DatabaseUtil;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Consultas {

    public static void mostrarEmprestimosPorMembro(int cartaoId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        BufferedWriter writer = null;

        try {
            conn = DatabaseUtil.getConnection();
            String sql = "SELECT m.nome, l.titulo, e.data_emprestimo, e.data_devolucao " +
                    "FROM membros m " +
                    "JOIN cartoes c ON m.cpf = c.cpf_membro " +
                    "JOIN emprestimos e ON c.cartao_id = e.cartao_id " +
                    "JOIN livros l ON e.livro_id = l.livro_id " +
                    "WHERE c.cartao_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, cartaoId);
            rs = stmt.executeQuery();

            writer = new BufferedWriter(new FileWriter("Resultado_Consultas.txt", false));

            boolean hasResults = false;
            writer.write("Nome do Membro\t\tTítulo do Livro\t\tData de Empréstimo\tData de Devolução");
            writer.newLine();
            writer.write("---------------------------------------------------------------------------------------------");
            writer.newLine();

            while (rs.next()) {
                hasResults = true;
                String nome = rs.getString("nome");
                String titulo = rs.getString("titulo");
                String dataEmprestimo = rs.getString("data_emprestimo");
                String dataDevolucao = rs.getString("data_devolucao");
                writer.write(nome + "\t\t" + titulo + "\t\t" + dataEmprestimo + "\t\t" + dataDevolucao);
                writer.newLine();
            }

            if (!hasResults) {
                writer.write("Nenhum empréstimo encontrado para o cartão de membro: " + cartaoId);
                writer.newLine();
            }

            System.out.println("Consulta realizada com sucesso. Resultados salvos em Resultado_Consultas.txt.");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao executar a consulta SQL.");
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
                if (writer != null) writer.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void mostrarEmprestimosDetalhadosPorMembro() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        BufferedWriter writer = null;

        try {
            // Abrir conexão com o banco de dados
            conn = DatabaseUtil.getConnection();

            // Consulta SQL
            String sql = "SELECT m.nome AS nome_membro, l.titulo AS nome_livro, e.data_emprestimo, e.data_devolucao " +
                    "FROM membros m " +
                    "JOIN cartoes c ON m.cpf = c.cpf_membro " +
                    "JOIN emprestimos e ON c.cartao_id = e.cartao_id " +
                    "JOIN livros l ON e.livro_id = l.livro_id";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            // Abrir arquivo para escrita (modo sobrescrita)
            writer = new BufferedWriter(new FileWriter("Resultado_Consultas.txt", false));

            // Escrever cabeçalho no arquivo
            writer.write("Nome do Membro\t\tNome do Livro\t\tData de Empréstimo\tData de Devolução");
            writer.newLine();
            writer.write("---------------------------------------------------------------------------------------------");
            writer.newLine();

            // Iterar pelos resultados e escrever no arquivo
            while (rs.next()) {
                String nomeMembro = rs.getString("nome_membro");
                String nomeLivro = rs.getString("nome_livro");
                String dataEmprestimo = rs.getString("data_emprestimo");
                String dataDevolucao = rs.getString("data_devolucao");
                writer.write(nomeMembro + "\t\t" + nomeLivro + "\t\t" + dataEmprestimo + "\t\t" + dataDevolucao);
                writer.newLine();
            }

            System.out.println("Consulta realizada com sucesso. Resultados salvos em Resultado_Consultas.txt.");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            // Fechar ResultSet, PreparedStatement, Connection e BufferedWriter
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
                if (writer != null) writer.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void mostrarEmprestimosPorLivro(int livroId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        BufferedWriter writer = null;

        try {
            conn = DatabaseUtil.getConnection();
            String sql = "SELECT m.nome AS nome_membro, l.titulo AS nome_livro, e.data_emprestimo, e.data_devolucao " +
                    "FROM membros m " +
                    "JOIN cartoes c ON m.cpf = c.cpf_membro " +
                    "JOIN emprestimos e ON c.cartao_id = e.cartao_id " +
                    "JOIN livros l ON e.livro_id = l.livro_id " +
                    "WHERE l.livro_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, livroId);
            rs = stmt.executeQuery();

            writer = new BufferedWriter(new FileWriter("Resultado_Consultas.txt", false));

            writer.write("Empréstimos do Livro com ID: " + livroId);
            writer.newLine();
            writer.write("Nome do Membro\t\tTítulo do Livro\t\tData de Empréstimo\t\tData de Devolução");
            writer.newLine();
            writer.write("------------------------------------------------------------------------------------------------");
            writer.newLine();

            boolean hasResults = false;
            while (rs.next()) {
                hasResults = true;
                String nomeMembro = rs.getString("nome_membro");
                String nomeLivro = rs.getString("nome_livro");
                String dataEmprestimo = rs.getString("data_emprestimo");
                String dataDevolucao = rs.getString("data_devolucao");
                writer.write(nomeMembro + "\t\t" + nomeLivro + "\t\t" + dataEmprestimo + "\t\t" + dataDevolucao);
                writer.newLine();
            }

            if (!hasResults) {
                writer.write("Nenhum empréstimo encontrado para o livro com ID: " + livroId);
                writer.newLine();
            }

            System.out.println("Consulta realizada com sucesso. Resultados salvos em Resultado_Consultas.txt.");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao executar a consulta SQL.");
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
                if (writer != null) writer.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void gravarMembrosEmArquivo() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        BufferedWriter writer = null;

        try {
            conn = DatabaseUtil.getConnection();
            String sql = "SELECT m.nome, c.cartao_id " +
                    "FROM membros m " +
                    "JOIN cartoes c ON m.cpf = c.cpf_membro";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            writer = new BufferedWriter(new FileWriter("Membros.txt", false));

            writer.write(String.format("%-20s %-20s", "Nome do Membro", "ID do Cartão"));
            writer.newLine();
            writer.write("--------------------------------------------------------");
            writer.newLine();

            boolean hasResults = false;
            while (rs.next()) {
                hasResults = true;
                String nome = rs.getString("nome");
                int cartaoId = rs.getInt("cartao_id");
                writer.write(String.format("%-20s %-20d", nome, cartaoId));
                writer.newLine();
            }

            if (!hasResults) {
                writer.write("Nenhum membro encontrado.");
                writer.newLine();
            }

            System.out.println("Consulta realizada com sucesso. Resultados salvos em Membros.txt.");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao executar a consulta SQL.");
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
                if (writer != null) writer.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void escreverLivrosEmArquivo() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        BufferedWriter writer = null;

        try {
            conn = DatabaseUtil.getConnection();
            String sql = "SELECT livro_id, titulo FROM livros";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            writer = new BufferedWriter(new FileWriter("Livros.txt", false));

            writer.write("ID do Livro\t\tTítulo do Livro");
            writer.newLine();
            writer.write("------------------------------------");
            writer.newLine();

            boolean hasResults = false;
            while (rs.next()) {
                hasResults = true;
                int livroId = rs.getInt("livro_id");
                String tituloLivro = rs.getString("titulo");
                writer.write(livroId + "\t\t" + tituloLivro);
                writer.newLine();
            }

            if (!hasResults) {
                writer.write("Nenhum livro encontrado.");
                writer.newLine();
            }

            System.out.println("Consulta realizada com sucesso. Resultados salvos em Livros.txt.");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao executar a consulta SQL.");
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
                if (writer != null) writer.close();
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        }


    }
}


