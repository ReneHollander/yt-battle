/*
 * package at.er.ytbattle.gui;
 * 
 * import java.awt.event.ActionEvent; import java.awt.event.ActionListener;
 * 
 * import javax.swing.JOptionPane;
 * 
 * public class EditControl implements ActionListener {
 * 
 * private EditView view;
 * 
 * public EditControl(EditView v) { view = v; }
 * 
 * @Override public void actionPerformed(ActionEvent e) { if
 * (view.checkForAdd(e)) { new AddPlayerView(view); }
 * 
 * if (view.checkForRem(e)) { try {
 * view.getPlayersModel().remove(view.getPlayers().getSelectedIndex()); } catch
 * (Exception ex) { } }
 * 
 * if (view.checkForSave(e)) { String l = view.getLifes().getText();
 * 
 * @SuppressWarnings("unused") int lifes = 0;
 * 
 * try { lifes = Integer.parseInt(l); } catch (Exception ex) {
 * JOptionPane.showMessageDialog(view,
 * "Error on saving lifes: Text is not a number!"); System.out.println(l);
 * return; }
 * 
 * view.applyTeamData();
 * 
 * JOptionPane.showMessageDialog(view, "Teamdata was saved successfully!");
 * view.dispose(); } } }
 */