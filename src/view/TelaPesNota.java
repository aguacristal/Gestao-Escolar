/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import gestaoescolar.Aluno;
import gestaoescolar.Nota;
import gestaoescolar.NotaDAO;
import gestaoescolar.Recuperacao;
import gestaoescolar.RecuperacaoDAO;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Juliana
 */
public class TelaPesNota extends javax.swing.JFrame {

    /**
     * Creates new form TelaPesNota
     */
    public TelaPesNota() {
        initComponents();
        preencherTabela();
    }

      private void preencherTabela() {
          NotaDAO notaDao = new NotaDAO();
        RecuperacaoDAO recDao = new RecuperacaoDAO();
        int alunoId = Integer.parseInt(txtNomeAluno.getText());
        
        List<Nota> listaNotas = notaDao.listarNotasPorAluno(alunoId);
        List<Recuperacao> listaRecuperacoes = recDao.listarRecuperacoesPorAluno(alunoId);
        
        DefaultTableModel tabela = (DefaultTableModel) tblAlunoNotas.getModel();
        tabela.setNumRows(0);
        tblAlunoNotas.setRowSorter(new TableRowSorter<>(tabela));
        
        for (Nota nota : listaNotas) {
            tabela.addRow(new Object[]{
                nota.getAluno().getId(),
                nota.getDataLancamento(),
                nota.getMateria().getNome(),
                nota.getNota(),
                "Nota Regular"
            });
        }
        
        for (Recuperacao rec : listaRecuperacoes) {
            tabela.addRow(new Object[]{
                rec.getAluno().getId(),
                rec.getDataRecuperacao(),
                rec.getMateria().getNome(),
                rec.getNota(),
                "Recuperação"
            });
        }
      }
        
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNomeAluno = new javax.swing.JTextField();
        btnvoltar = new javax.swing.JButton();
        btnpesq = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAlunoNotas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 204, 102));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("PESQUISA DE NOTA");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("NOME:");

        txtNomeAluno.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtNomeAlunoCaretUpdate(evt);
            }
        });

        btnvoltar.setText("VOLTAR");
        btnvoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnvoltarActionPerformed(evt);
            }
        });

        btnpesq.setText("PESQUISAR");
        btnpesq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpesqActionPerformed(evt);
            }
        });

        jScrollPane1.setBackground(new java.awt.Color(255, 153, 0));

        tblAlunoNotas.setBackground(new java.awt.Color(255, 153, 0));
        tblAlunoNotas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ALUNO", "DATA", "MATÉRIA", "NOTA"
            }
        ));
        jScrollPane1.setViewportView(tblAlunoNotas);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnpesq)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(155, 155, 155)
                                .addComponent(jLabel1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNomeAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addComponent(btnvoltar))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNomeAluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnvoltar))
                .addGap(18, 18, 18)
                .addComponent(btnpesq)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNomeAlunoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtNomeAlunoCaretUpdate
       preencherTabela();
    }//GEN-LAST:event_txtNomeAlunoCaretUpdate

    private void btnpesqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpesqActionPerformed
      try {
        if (txtNomeAluno.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite o ID do aluno!");
            return;
        }

        preencherTabela(); // Chama o método que preenche a tabela com notas e recuperações.
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "O ID do aluno deve ser um número válido!");
    }  
    }//GEN-LAST:event_btnpesqActionPerformed

    private void btnvoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnvoltarActionPerformed
      TelaMenu men = new TelaMenu();
      men.setVisible(true);
    }//GEN-LAST:event_btnvoltarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPesNota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPesNota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPesNota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPesNota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPesNota().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnpesq;
    private javax.swing.JButton btnvoltar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblAlunoNotas;
    private javax.swing.JTextField txtNomeAluno;
    // End of variables declaration//GEN-END:variables
}
