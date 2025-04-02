/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestaoescolar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juliana
 */
public class AlunoDAO {
     private Connection conn;
    private PreparedStatement st;
    private ResultSet rs;

    // Método para conectar ao banco de dados
    public boolean conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestao_escolar_integrada", "root", "cla12345ra");
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return false;
        }
    }
     public int salvar(Aluno alu) {
        if (!conectar()) {
            return -1; // Indica erro de conexão
        }

        int status;
        try {
            st = conn.prepareStatement("INSERT INTO alunos (id, matricula, turma, responsavel_id) VALUES (?, ?, ?, ?)");
            st.setInt(1, alu.getId());
            
            st.setString(2, alu.getMatricula());
            st.setString(3, alu.getTurma());
           if (alu.getResponsavel() != null) {
    st.setInt(4, alu.getResponsavel().getId());
} else {
    st.setNull(4, java.sql.Types.INTEGER);
}
            status = st.executeUpdate();
            return status; // Retorna 1 se for bem-sucedido
        } catch (SQLException ex) {
            System.out.println("Erro ao salvar: " + ex.getMessage());
            return ex.getErrorCode();
        } finally {
            desconectar();
        }
    }

    // Método para consultar um filme pelo ID
    public Aluno consultar(int id) {
        if (!conectar()) {
            return null;
        }

        try {
            Aluno alu = new Aluno();
            st = conn.prepareStatement("SELECT * FROM alunos WHERE id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                alu.setId(rs.getInt("id"));
                alu.setMatricula(rs.getString("matricula"));
                alu.setTurma(rs.getString("turma"));
                int responsavelId = rs.getInt("responsavel_id");
                Responsavel responsavel = new Responsavel();
                responsavel.setId(responsavelId);
                alu.setResponsavel(responsavel);
                return alu;
            } else {
                return null; // Retorna null se não encontrar o filme
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar: " + ex.getMessage());
            return null;
        } finally {
            desconectar();
        }
    }


    // Método para buscar filmes pelo nome (retorna uma lista)
    public List<Aluno> getMatricula(String matricula) {
        List<Aluno> listaAluno = new ArrayList<>();
        
        if (!conectar()) { 
            return listaAluno; 
        }

        String sql = "SELECT * FROM alunos WHERE matricula LIKE ?";
        try {
            st = conn.prepareStatement(sql);
            st.setString(1, "%" + matricula + "%");
            rs = st.executeQuery();

            while (rs.next()) {
                Aluno alu = new Aluno();
                alu.setId(rs.getInt("id"));
                alu.setMatricula(rs.getString("matricula"));
                alu.setTurma(rs.getString("turma"));
                 int responsavelId = rs.getInt("responsavel");
            Responsavel responsavel = setId(responsavelId);
            alu.setResponsavel(responsavel);

                listaAluno.add(alu);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar filme: " + e.getMessage());
        } finally {
            desconectar();
        }
        return listaAluno;
    }

    // Método para desconectar do banco de dados
    public void desconectar() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException ex) {
            // Pode ser ignorado para evitar mensagens desnecessárias ao usuário
        }
    }

    private Responsavel setId(int responsavelId) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}

