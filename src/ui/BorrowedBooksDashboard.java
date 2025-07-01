/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import dao.BookDAO;
import dao.BorrowingDAO;
import dao.BorrowingDAOImpl;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.BorrowStatus;
import model.BorrowingRecordDisplay;
import service.BorrowBookService;
import service.validation.ValidationResult;
import util.Session;

/**
 *
 * @author bests
 */
public class BorrowedBooksDashboard extends javax.swing.JFrame {

    /**
     * Creates new form BorrowedBooksDashboard
     */
    private String studentId;
    private final BorrowingDAO borrowingDAO = new BorrowingDAOImpl(); // ✅ reuse this
    private List<BorrowingRecordDisplay> currentBorrowings;

    public BorrowedBooksDashboard() {
        initComponents();
        // ❗ Add this to fix radio selection behavior
        buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(lblOverdueCount);
        buttonGroup1.add(lblBorrowedCount);
        buttonGroup1.add(lblReturnedCount);

        studentId = Session.getCurrentStudentId();
        if (studentId == null || studentId.isEmpty()) {
            studentId = "231011400253"; // Fallback
            showMessage("Student ID was empty. Using default: " + studentId);
        }

        setupListeners(); // 👈 Add this line
        lblOverdueCount.setSelected(true); // default to OVERDUE
        loadBorrowingsByStatus(BorrowStatus.OVERDUE);
    }

    private void setupListeners() {
        // Radio button listeners
        lblOverdueCount.addActionListener(e -> loadBorrowingsByStatus(BorrowStatus.OVERDUE));
        lblBorrowedCount.addActionListener(e -> loadBorrowingsByStatus(BorrowStatus.BORROWED));
        lblReturnedCount.addActionListener(e -> loadBorrowingsByStatus(BorrowStatus.RETURNED));

        // Table selection logic
        jTable1.getSelectionModel().addListSelectionListener(e -> {
            int row = jTable1.getSelectedRow();
            if (row < 0) {
                btnReturnBook.setEnabled(false);
            } else {
                String status = lblReturnedCount.isSelected() ? "RETURNED"
                        : lblBorrowedCount.isSelected() ? "BORROWED" : "OVERDUE";
                btnReturnBook.setEnabled(status.equals("BORROWED") || status.equals("OVERDUE"));
            }
        });
    }

    private boolean isBookRowSelected() {
        if (jTable1.getSelectedRow() < 0) {
            showMessage("No book selected.");
            return false;
        }
        return true;
    }

    private String getSelectedBorrowingId() {
        int selectedRow = jTable1.getSelectedRow();
        return jTable1.getValueAt(selectedRow, 0).toString();
    }

    private boolean confirmReturn() {
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to return this book?",
                "Confirm Return",
                JOptionPane.YES_NO_OPTION
        );
        return confirm == JOptionPane.YES_OPTION;
    }

    private void performReturn(String studentId, String isbn) {
        try {
            BookDAO bookDAO = new BookDAO();
            BorrowBookService borrowService = new BorrowBookService(bookDAO, borrowingDAO);

            ValidationResult result = borrowService.borrow(studentId, isbn);
            showMessage(result.getMessage());

            if (result.isValid()) {
                loadBorrowingsByStatus(BorrowStatus.BORROWED);
            }

        } catch (Exception e) {
            e.printStackTrace();
            showMessage("Unexpected error: " + e.getMessage());
        }
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    private void updateStatusCounts() {
        try {
            int borrowedCount = borrowingDAO.getBorrowingRecordDisplayByStatus(studentId, BorrowStatus.BORROWED).size();
            int overdueCount = borrowingDAO.getBorrowingRecordDisplayByStatus(studentId, BorrowStatus.OVERDUE).size();
            int returnedCount = borrowingDAO.getBorrowingRecordDisplayByStatus(studentId, BorrowStatus.RETURNED).size();

            lblBorrowedCount.setText("Borrowed (" + borrowedCount + ")");
            lblOverdueCount.setText("Overdue (" + overdueCount + ")");
            lblReturnedCount.setText("Returned (" + returnedCount + ")");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadBorrowingsByStatus(BorrowStatus status) {
        try {
            currentBorrowings = borrowingDAO.getBorrowingRecordDisplayByStatus(studentId, status); // ✔️ fixed here

            // Auto fallback logic
            if (currentBorrowings.isEmpty()) {
                if (status == BorrowStatus.OVERDUE) {
                    lblBorrowedCount.setSelected(true);
                    loadBorrowingsByStatus(BorrowStatus.BORROWED);
                } else if (status == BorrowStatus.BORROWED) {
                    lblReturnedCount.setSelected(true);
                    loadBorrowingsByStatus(BorrowStatus.RETURNED);
                } else {
                    updateTable(new ArrayList<>());
                }
            } else {
                updateTable(currentBorrowings);
            }

            updateStatusCounts(); // update badges

        } catch (Exception e) {
            e.printStackTrace();
            showMessage("Failed to load borrowings: " + e.getMessage());
        }
    }

    private void updateTable(List<BorrowingRecordDisplay> list) {
        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"BorrowID", "Title", "BorrowDate", "DueDate", "ReturnDate"}, 0
        );

        for (BorrowingRecordDisplay b : list) {
            model.addRow(new Object[]{
                b.getBorrowId(),
                b.getTitle(),
                b.getBorrowDate(),
                b.getDueDate(),
                b.getReturnDate() != null ? b.getReturnDate() : "-"
            });
        }

        jTable1.setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        lblOverdueCount = new javax.swing.JRadioButton();
        lblBorrowedCount = new javax.swing.JRadioButton();
        lblReturnedCount = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnReturnBook = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel1.setText("Borrowed Books");

        lblOverdueCount.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblOverdueCount.setText("Overdue");
        lblOverdueCount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblOverdueCountActionPerformed(evt);
            }
        });

        lblBorrowedCount.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblBorrowedCount.setText("Borrowed");

        lblReturnedCount.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblReturnedCount.setText("Returned");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        btnReturnBook.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnReturnBook.setText("Return");
        btnReturnBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnBookActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setText("Back to Dashboard");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnReturnBook)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(392, 392, 392)
                            .addComponent(lblOverdueCount)
                            .addGap(46, 46, 46)
                            .addComponent(lblBorrowedCount)
                            .addGap(33, 33, 33)
                            .addComponent(lblReturnedCount))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(36, 36, 36)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(414, 414, 414)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBorrowedCount)
                    .addComponent(lblOverdueCount)
                    .addComponent(lblReturnedCount))
                .addGap(31, 31, 31)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnReturnBook)
                .addGap(38, 38, 38))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblOverdueCountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblOverdueCountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblOverdueCountActionPerformed

    private void btnReturnBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnBookActionPerformed
        // TODO add your handling code here: 
        if (!isBookRowSelected()) {
            return;
        }

        int row = jTable1.getSelectedRow();
        int borrowId = (int) jTable1.getValueAt(row, 0);
        String status = lblReturnedCount.isSelected() ? "RETURNED" : (lblBorrowedCount.isSelected() ? "BORROWED"
                : "OVERDUE");

        if (!status.equals("BORROWED") && !status.equals("OVERDUE")) {
            showMessage("Only BORROWED or OVERDUE books can be returned.");
            return;
        }

        if (!confirmReturn()) {
            return;
        }

        try {
            boolean success = borrowingDAO.returnBook(borrowId);
            if (success) {
                showMessage("Book returned successfully.");
                loadBorrowingsByStatus(BorrowStatus.valueOf(status));
            } else {
                showMessage("Failed to return book.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showMessage("Unexpected error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnReturnBookActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        new Dashboard().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(BorrowedBooksDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BorrowedBooksDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BorrowedBooksDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BorrowedBooksDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BorrowedBooksDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReturnBook;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JRadioButton lblBorrowedCount;
    private javax.swing.JRadioButton lblOverdueCount;
    private javax.swing.JRadioButton lblReturnedCount;
    // End of variables declaration//GEN-END:variables
}
