
package view;

import gestaoescolar.Responsavel;
import gestaoescolar.ResponsavelDAO;
import javax.swing.JOptionPane;

/**
 *
 * @author Juliana
 */
public class TelaResponsavel extends javax.swing.JFrame {

    /**
     * Creates new form TelaResponsavel
     */
    public TelaResponsavel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txttel = new javax.swing.JTextField();
        txtusu = new javax.swing.JTextField();
        txtend = new javax.swing.JTextField();
        btncad = new javax.swing.JButton();
        btnvol = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 204, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setText("CADASTRO DE RESPONSÁVEL");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("TELEFONE:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 110, 20));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("USUÁRIO:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 90, 20));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("ENDEREÇO:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 110, 20));

        txttel.setBackground(new java.awt.Color(255, 153, 0));
        txttel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttelActionPerformed(evt);
            }
        });
        jPanel1.add(txttel, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 180, -1));

        txtusu.setBackground(new java.awt.Color(255, 153, 0));
        txtusu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtusuActionPerformed(evt);
            }
        });
        jPanel1.add(txtusu, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 180, -1));

        txtend.setBackground(new java.awt.Color(255, 153, 0));
        txtend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtendActionPerformed(evt);
            }
        });
        jPanel1.add(txtend, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 180, -1));

        btncad.setBackground(new java.awt.Color(255, 153, 0));
        btncad.setText("CADASTRAR");
        btncad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncadActionPerformed(evt);
            }
        });
        jPanel1.add(btncad, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 180, -1, -1));

        btnvol.setBackground(new java.awt.Color(255, 153, 0));
        btnvol.setText("VOLTAR");
        btnvol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnvolActionPerformed(evt);
            }
        });
        jPanel1.add(btnvol, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 230, 90, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txttelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttelActionPerformed

    private void txtendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtendActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtendActionPerformed

    private void btncadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncadActionPerformed
 Responsavel res = new Responsavel();
        ResponsavelDAO dao = new ResponsavelDAO();
        boolean status;
        int resposta;

        try {
            res.setId(Integer.parseInt(txtusu.getText())); // Garante que o ID seja um número válido
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID inválido. Digite um número.");
            return; // Sai do método para evitar erros adicionais
        }

        res.setEndereco(txtend.getText());
        res.setTelefone(txttel.getText());

        status = dao.conectar();
        if (!status) {
            JOptionPane.showMessageDialog(null, "Erro de conexão");
        } else {
            resposta = dao.salvar(res); // Corrigido: removido o `/` errado

            if (resposta == 1062) { // Verifica erro de chave duplicada
                JOptionPane.showMessageDialog(null, "Responsável já cadastrado.");
            } else if (resposta == 1) { // Sucesso no cadastro
                JOptionPane.showMessageDialog(null, "Responsável cadastrado com sucesso.");
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao tentar inserir dados.");
            }

            dao.desconectar();
        }
    

                            
    }//GEN-LAST:event_btncadActionPerformed

    private void txtusuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtusuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtusuActionPerformed

    private void btnvolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnvolActionPerformed
     this.dispose();
     TelaAluno alu = new TelaAluno();
     alu.setVisible(true);

    }//GEN-LAST:event_btnvolActionPerformed

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
            java.util.logging.Logger.getLogger(TelaResponsavel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaResponsavel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaResponsavel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaResponsavel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaResponsavel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncad;
    private javax.swing.JButton btnvol;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtend;
    private javax.swing.JTextField txttel;
    private javax.swing.JTextField txtusu;
    // End of variables declaration//GEN-END:variables
}
