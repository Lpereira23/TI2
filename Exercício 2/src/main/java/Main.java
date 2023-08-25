import java.util.List;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String jdbcUrl = "jdbc:postgresql://localhost:5432/seu_banco_de_dados";
        String usuario = "seu_usuario";
        String senha = "sua_senha";

        try (Connection conexao = DriverManager.getConnection(jdbcUrl, usuario, senha)) {
            PessoaDAO pessoaDAO = new PessoaDAO(conexao);

            int opcao;
            do {
                System.out.println("Menu:");
                System.out.println("1. Listar");
                System.out.println("2. Inserir");
                System.out.println("3. Excluir");
                System.out.println("4. Atualizar");
                System.out.println("5. Sair");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();

                switch (opcao) {
                    case 1:
                        List<Pessoa> pessoas = pessoaDAO.listarTodos();
                        for (Pessoa pessoa : pessoas) {
                            System.out.println(pessoa.getId() + ": " + pessoa.getNome() + " - " + pessoa.getIdade() + " anos");
                        }
                        break;
                    case 2:
                        System.out.print("Nome: ");
                        String nome = scanner.next();
                        System.out.print("Idade: ");
                        int idade = scanner.nextInt();
                        Pessoa novaPessoa = new Pessoa();
                        novaPessoa.setNome(nome);
                        novaPessoa.setIdade(idade);
                        pessoaDAO.inserir(novaPessoa);
                        break;
                    case 3:
                        System.out.print("ID da pessoa a ser excluída: ");
                        int idExclusao = scanner.nextInt();
                        pessoaDAO.excluir(idExclusao);
                        break;
                    case 4:
                        System.out.print("ID da pessoa a ser atualizada: ");
                        int idAtualizacao = scanner.nextInt();
                        Pessoa pessoaAtualizada = pessoaDAO.buscarPorId(idAtualizacao);
                        if (pessoaAtualizada != null) {
                            System.out.print("Novo nome: ");
                            pessoaAtualizada.setNome(scanner.next());
                            System.out.print("Nova idade: ");
                            pessoaAtualizada.setIdade(scanner.nextInt());
                            pessoaDAO.atualizar(pessoaAtualizada);
                        } else {
                            System.out.println("Pessoa não encontrada.");
                        }
                        break;
                    case 5:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            } while (opcao != 5);
        } catch (SQLException e) {
            System.err.println("Erro de banco de dados: " + e.getMessage());
        }
    }
}
