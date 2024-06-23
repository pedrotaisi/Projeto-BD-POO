package Classes_Bases;

import Classes_extras.Consultas;
import Classes_extras.Validacao;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println();
        System.out.println("Bem-vindo ao nosso protótipo de um sistema de gerenciamento de Biblioteca");
        System.out.println();

        boolean again = true;

        while (true) {


            System.out.println("O que deseja fazer em nosso sistema?");
            System.out.println("Cadastrar um usuario - 1");
            System.out.println("Atualizar cadastro - 2");
            System.out.println("Deletar cadastro - 3");
            System.out.println("Cadastrar um autor e um Livro - 4");
            System.out.println("Adicionar um Livro a um autor - 5");
            System.out.println("Adicionar um emprestimo - 6");
            System.out.println("Buscar Informacoes - 7");
            System.out.println("sair do sistema - 8");
            System.out.println();

            System.out.print("Digite um numero: ");
            int number = sc.nextInt();

            while (number <= 0 || number > 8) {
                System.out.println("numero invalido, tente novamente");
                number = sc.nextInt();
            }

            switch (number) {
                case 1:
                    sc.nextLine();
                    System.out.println("Por favor, cadastre um usuário: ");

                    do {
                        System.out.print("Nome: ");
                        String nome = sc.nextLine();

                        System.out.print("CPF: ");
                        String cpf = sc.nextLine();

                        while(!Validacao.validarCPF(cpf)){
                            System.out.println("CPF invalido, tente novamente");
                            cpf = sc.nextLine();
                        }

                        System.out.print("Email: ");
                        String email = sc.nextLine();

                        while(!Validacao.validarEmail(email)){
                            System.out.println("Email invalido, tente novamente");
                            email = sc.nextLine();
                        }

                        System.out.print("Telefone: ");
                        String telefone = sc.nextLine();

                        while(!Validacao.validarTelefone(telefone)){
                            System.out.println("Telefone invalido, tente novamente");
                            telefone = sc.nextLine();
                        }

                        System.out.println();

                        Membros membro = new Membros(nome, cpf, email, telefone);
                        membro.cadastrarMembro();

                        System.out.println("Deseja cadastrar um novo usuário? y/n");
                        char resposta = sc.next().charAt(0);

                        // Consome a nova linha que fica pendente após next().charAt(0)
                        sc.nextLine();

                        while (resposta != 'y' && resposta != 'n') {
                            System.out.println("Argumento inválido, tente novamente.");
                            System.out.println("Deseja cadastrar um novo usuário? y/n");
                            resposta = sc.next().charAt(0);
                            sc.nextLine();  // Consome a nova linha pendente
                        }

                        if (resposta == 'n') {
                            break;
                        }

                    } while (true);
                    break;

                case 2:

                    do {

                        if (sc.hasNextLine()) {
                            sc.nextLine();
                        }

                        System.out.println("Por favor, nos informe qual campo deseja atualizar (nome, email ou telefone)");

                        String parametro = sc.nextLine().toLowerCase();

                        System.out.println("Por favor, informe o CPF do membro que está tentando alterar os dados");
                        String cpf = sc.nextLine();

                        System.out.println("Favor informar o novo atributo a ser trocado");
                        String atributo = sc.nextLine();

                        Membros.atualizarCadastro(parametro, cpf, atributo);

                        System.out.println("Deseja atualizar mais algum atributo de algum Membro? y/n");

                        char resposta = sc.next().charAt(0);

                        while (resposta != 'y' && resposta != 'n') {
                            System.out.println("Argumento inválido, tente novamente.");
                            System.out.println("Deseja atualizar mais algum atributo de algum Membro? y/n");
                            resposta = sc.next().charAt(0);
                            sc.nextLine();  // Consome a nova linha pendente
                        }

                        if (resposta == 'n') {
                            break;
                        }

                    } while (true);
                    break;

                case 3:

                    do {
                        if (sc.hasNextLine()) {
                            sc.nextLine();
                        }

                        System.out.println("favor informe o cpf do membro que deseja deletar os dados");
                        String cpf_a_deletar = sc.nextLine();

                        Membros.deletarCadastro(cpf_a_deletar);

                        System.out.println("Deseja deletar mais algum Membro? y/n");

                        char resposta = sc.next().charAt(0);

                        while (resposta != 'y' && resposta != 'n') {
                            System.out.println("Argumento inválido, tente novamente.");
                            System.out.println("Deseja deletar mais algum Membro? y/n");
                            resposta = sc.next().charAt(0);
                            sc.nextLine();  // Consome a nova linha pendente
                        }

                        if (resposta == 'n') {
                            break;
                        }

                    } while (true);
                    break;

                case 4:

                    do {

                        if (sc.hasNextLine()) {
                            sc.nextLine();
                        }

                        System.out.println("por favor informe o nome do autor e o livro de sua autoria");
                        System.out.print("Nome do autor: ");
                        String nome = sc.nextLine().toLowerCase();

                        System.out.print("nome do livro: ");
                        String nomeLivro = sc.nextLine();

                        Autores autor = new Autores(nome, nomeLivro);
                        autor.cadastrarAutor();

                        System.out.println("Deseja cadastrar mais algum autor? y/n");

                        char resposta = sc.next().charAt(0);

                        while (resposta != 'y' && resposta != 'n') {
                            System.out.println("Argumento inválido, tente novamente.");
                            System.out.println("Deseja cadastrar mais algum autor? y/n");
                            resposta = sc.next().charAt(0);
                            sc.nextLine();  // Consome a nova linha pendente
                        }

                        if (resposta == 'n') {
                            break;
                        }

                    } while (true);
                    break;

                case 5:

                    do {

                        if (sc.hasNextLine()) {
                            sc.nextLine();
                        }

                        System.out.println("favor inserir o nome do autor e livro de sua autoria que sera adicionado");
                        System.out.print("Nome do autor: ");
                        String nome = sc.nextLine().toLowerCase();

                        System.out.print("nome do livro: ");
                        String nomeLivro = sc.nextLine();

                        Autores.adicionarLivro(nome, nomeLivro);

                        System.out.println("Deseja adicionar mais algum livro a algum autor? y/n");

                        char resposta = sc.next().charAt(0);

                        while (resposta != 'y' && resposta != 'n') {
                            System.out.println("Argumento inválido, tente novamente.");
                            System.out.println("Deseja cadastrar mais algum autor? y/n");
                            resposta = sc.next().charAt(0);
                            sc.nextLine();  // Consome a nova linha pendente
                        }

                        if (resposta == 'n') {
                            break;
                        }

                    } while (true);
                    break;

                case 6:

                    do {
                        if (sc.hasNextLine()) {
                            sc.nextLine();
                        }

                        System.out.println("por favor informe o id de seu cartao, o id do livro que foi pego emprestado, data de devolucao e data de retirada");
                        System.out.print("Id do cartao: ");
                        int cartao = sc.nextInt();

                        System.out.print("id do livro: ");
                        int livro = sc.nextInt();

                        sc.nextLine();

                        System.out.print("data de retirada: ");
                        String dataRetirada = sc.nextLine();

                        System.out.print("data de devolucao: ");
                        String dataDevolucao = sc.nextLine();

                        Emprestimos emprestimos = new Emprestimos(cartao, livro, dataRetirada, dataDevolucao);
                        emprestimos.cadastraEmprestimo();

                        System.out.println("Deseja adicionar mais algum emprestimo? y/n");

                        char resposta = sc.next().charAt(0);

                        while (resposta != 'y' && resposta != 'n') {
                            System.out.println("Argumento inválido, tente novamente.");
                            System.out.println("Deseja adicionar mais algum emprestimo? y/n");
                            resposta = sc.next().charAt(0);
                            sc.nextLine();  // Consome a nova linha pendente
                        }

                        if (resposta == 'n') {
                            break;
                        }

                    } while (true);
                    break;

                case 7:

                    do {
                        if (sc.hasNextLine()) {
                            sc.nextLine();
                        }

                        System.out.println("Qual tipo de consulta deseja realizar?");
                        System.out.println("Busca em relacao a um nome de um membro - 1");
                        System.out.println("Busca em relacao a um nome de um livro - 2");
                        System.out.println("Ver tabela de Emprestimo detalhada - 3");

                        int numbers = sc.nextInt();

                        while (numbers <= 0 || numbers > 3) {
                            System.out.println("numero invalido, tente novamente.");
                            numbers = sc.nextInt();
                        }

                        sc.nextLine();

                        switch (numbers) {
                            case 1:
                                System.out.println("digite o id do cartao do membro o qual dseja fazer a busca: ");
                                int cartao = sc.nextInt();
                                Consultas.mostrarEmprestimosPorMembro(cartao);
                                break;

                            case 2:
                                System.out.println("Digite o id do livro que fazer a busca: ");
                                int livro = sc.nextInt();
                                Consultas.mostrarEmprestimosPorLivro(livro);
                                break;

                            case 3:
                                System.out.println("Aqui esta a tabela de emprestimos de forma detalhada");
                                Consultas.mostrarEmprestimosDetalhadosPorMembro();
                        }

                        System.out.println("Deseja fazer mais alguma consulta? y/n");

                        char resposta = sc.next().charAt(0);

                        while (resposta != 'y' && resposta != 'n') {
                            System.out.println("Argumento inválido, tente novamente.");
                            System.out.println("Deseja fazer mais alguma consulta? y/n");
                            resposta = sc.next().charAt(0);
                            sc.nextLine();  // Consome a nova linha pendente
                        }

                        if (resposta == 'n') {
                            break;
                        }


                    } while (true);
                    break;

                case 8:
                    System.out.println("obrigado por testar nosso sistema, ate mais");
                    again = false;
                    break;
            }

            if (!again) {
                break;
            }

        }

    }
}
