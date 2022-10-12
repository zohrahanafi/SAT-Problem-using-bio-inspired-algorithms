package Interface;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import elements.*;

import javax.swing.JTable;
import javax.swing.JScrollPane;

import A_etoile.*;
public class AStar_gui extends JFrame {

	private static JPanel contentPane;
	private static JTable table;
	private static JLabel labelnbrclau_1;

	public AStar_gui() {
		setTitle("Applying A * Algorithm");
		 
	    this.setLocation(400, 250);
        initComponents();
    }
	 @SuppressWarnings("unchecked")
	    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	    private void initComponents() 
	 {
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
		
		
		JButton btnStart = new JButton("Start Algorithm A*");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				essai(e);
				
			}
		});
		btnStart.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(btnStart);
		
		btnStart.setBounds(294, 34, 190, 39);
		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(30, 110, 730, 400);
		contentPane.add(scrollPane1);
		
		table = new JTable();
		scrollPane1.setViewportView(table);
		
		
		
	 }
	 
	 public static void essai(java.awt.event.ActionEvent evt)
	 {
		 try
		 {
			 String sat;
			 String time;
			 
		LireFichier fr = new LireFichier(Main_Interface.f);
			fr.readFile();
			AStar.chercheSolution(fr);
			
			sat=AStar.Sat;
			time=AStar.time;
			
			ObtenirResultat obr = new ObtenirResultat();
			Map<Integer, Integer> map  = obr.obtenir(AStar.solution);
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
		 }catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		 	
			
	 }
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AStar_gui frame = new AStar_gui();
					frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}); 
	
	}
}
