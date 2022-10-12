package Interface;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import A_etoile.AStar;
import DFS.Dfs;
import elements.LireFichier;
import elements.Litteral;
import elements.ObtenirResultat;

import java.awt.Font;

public class Profondeur_gui extends JFrame {

	private static JPanel contentPane;
	private JDialog dialogue;
	private String data;
	private String path ="";
	private static JTable table;
	private static JLabel labelnbrclau_1;

	public Profondeur_gui() {
		setTitle("Applying Deep First Search Algorithm");
		 
	    this.setLocation(400, 250);
        initComponents();
    }
	 @SuppressWarnings("unchecked")
	    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	    private void initComponents() {
		 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(550, 200, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnHome = new JButton("HOME");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main_Interface main = new Main_Interface();
				main.setVisible(true);
				dispose();
			}
		});
		btnHome.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnHome.setBounds(12, 13, 99, 37);
		contentPane.add(btnHome);
		table = new JTable();
		
		JButton btnStart = new JButton("Start Algorithm DFS");
		btnStart.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				essai(e);
			}
		});
		btnStart.setBounds(294, 34, 190, 39);
		contentPane.add(btnStart);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 110, 730, 400);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		
		
	 }
	 
	 public static void essai(java.awt.event.ActionEvent evt)
	 {
		 String sat;
		 String time;
		 
		 LireFichier fr = new LireFichier(Main_Interface.f);
			fr.readFile();
			Dfs.chercheSolution(fr);
			
			sat=Dfs.Sat;
			time=Dfs.time;
			
			ObtenirResultat obr = new ObtenirResultat();
			Map<Integer, Integer> map  = obr.obtenir(Dfs.solution);
			DefaultTableModel tableModel = new DefaultTableModel();
		 
		 	
			
		 		tableModel.addColumn("Xi");
		 		tableModel.addColumn("Value");
		 		labelnbrclau_1 = new JLabel();
				labelnbrclau_1.setBounds(45, 77, 300, 36);
				contentPane.add(labelnbrclau_1);
				labelnbrclau_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
				labelnbrclau_1.setText(sat);
				labelnbrclau_1.setVisible(true);
			
		 	table.setModel(tableModel);
			
		 	for (Map.Entry<Integer, Integer> entry : map.entrySet()) 
			{ 
		 		String var = "X"+String.valueOf(entry.getKey());
		 		String value = String.valueOf(entry.getValue());
		 		String[] tableRow = {var,value};
				tableModel.addRow(tableRow);
				
			}
		 	table.setModel(tableModel);
	 }
	 
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Profondeur_gui frame = new Profondeur_gui();
					frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}); 
	
	}
}
