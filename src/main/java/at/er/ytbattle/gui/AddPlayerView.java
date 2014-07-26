/*
 * package at.er.ytbattle.gui;
 * 
 * import java.awt.BorderLayout; import java.awt.event.ActionEvent;
 * 
 * import javax.swing.JButton; import javax.swing.JFrame; import javax.swing.JTextField; import javax.swing.WindowConstants;
 * 
 * public class AddPlayerView extends JFrame { private static final long serialVersionUID = -2551907156134971678L;
 * 
 * private EditView view;
 * 
 * private JTextField name; private JButton add;
 * 
 * public AddPlayerView(EditView v) { super("Add Player to Team");
 * 
 * view = v; name = new JTextField(10); add = new JButton("Add Player");
 * 
 * add.addActionListener(new AddPlayerControl(this));
 * 
 * this.setSize(320, 100); this.setLocationRelativeTo(null); this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); this.setLayout(new BorderLayout());
 * 
 * this.add(name, BorderLayout.CENTER); this.add(add, BorderLayout.SOUTH);
 * 
 * this.setVisible(true); }
 * 
 * public boolean checkForAdd(ActionEvent e) { if (e.getSource() == add) return true; return false; }
 * 
 * public EditView getView() { return view; }
 * 
 * public void setView(EditView view) { this.view = view; }
 * 
 * public JTextField getNameField() { return name; }
 * 
 * public void setName(JTextField name) { this.name = name; } }
 */