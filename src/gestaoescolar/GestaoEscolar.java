package gestaoescolar;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class GestaoEscolar {
    public static void main(String[] args) {

        // Criando um usuário associado ao responsável
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setNome("Carlos Silva");
        usuario.setEmail("carlos.silva@email.com");
        usuario.setSenha("123456"); 
        usuario.setTipo("Responsável");
        usuario.setDataCadastro(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        // Criando um responsável e associando ao usuário
        Responsavel responsavel = new Responsavel();
        responsavel.setId(1);
        responsavel.setUsuario(usuario);
        responsavel.setTelefone("21987654321");
        responsavel.setEndereco("Rua das Flores, 123 - Centro");

        // Criando um aluno e associando o responsável
        Aluno aluno = new Aluno();
        aluno.setId(101);
        aluno.setMatricula("A2025001");
        aluno.setTurma("3º Ano B");
        aluno.setResponsavel(responsavel);

        // Exibindo os dados do aluno cadastrado
        System.out.println("Aluno cadastrado:");
        System.out.println("ID: " + aluno.getId());
        System.out.println("Matrícula: " + aluno.getMatricula());
        System.out.println("Turma: " + aluno.getTurma());
        System.out.println("Responsável: " + aluno.getResponsavel().getUsuario().getNome());
        System.out.println("Contato do responsável: " + aluno.getResponsavel().getTelefone());
        System.out.println("Endereço do responsável: " + aluno.getResponsavel().getEndereco());
        System.out.println("Usuário cadastrado em: " + aluno.getResponsavel().getUsuario().getDataCadastro());
        System.out.println("Senha criptografada do usuário: " + aluno.getResponsavel().getUsuario().getSenha());
    }
}

 

