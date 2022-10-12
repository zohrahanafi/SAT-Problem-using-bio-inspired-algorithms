package Interface;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import elements.LireFichier;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class Main_Interface extends JFrame {

	private JPanel contentPane;
	private String path;
	public static   File f;
	private JTable clausesTable;
	public Main_Interface() {
		setTitle("Bio-inspired computing project");
		 
	    this.setLocation(400, 250);
        initComponents();
    }
	 @SuppressWarnings("unchecked")
	    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	    private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// ----------------------------------  partie profondeur -------------------------------
		JLabel profondeur = new JLabel("Deep First Search : ");
		profondeur.setFont(new Font("Tahoma", Font.BOLD, 14));
		profondeur.setBounds(40, 290, 200, 35);
		contentPane.add(profondeur);
				

	
		JButton btnProdondeur = new JButton("Profondeur");
		btnProdondeur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Profondeur_gui p = new Profondeur_gui();
				p.setVisible(true);
				dispose();
			}
		});

		btnProdondeur.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnProdondeur.setBounds(620, 290, 200, 35);
		contentPane.add(btnProdondeur);
		
		// ----------------------------------  partie A* -------------------------------
		JLabel aEtoile = new JLabel("A* Algorithm :");
		aEtoile.setFont(new Font("Tahoma", Font.BOLD, 15));
		aEtoile.setBounds(40, 360, 200, 35);
	
		contentPane.add(aEtoile);
		
		JButton btnAetoile = new JButton("A*");
		btnAetoile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AStar_gui asg = new AStar_gui();
				asg.setVisible(true);
				dispose();
			}
		});

		//pour le bouton browse
		btnAetoile.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAetoile.setBounds(620, 360, 200, 35);
		contentPane.add(btnAetoile);
		
		JButton btnBrowseInstance = new JButton("Browse instance");
		btnBrowseInstance.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnBrowseInstance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				browseInstance(e);
			}
		});
		btnBrowseInstance.setBounds(322, 13, 179, 36);
		contentPane.add(btnBrowseInstance);
		
		
		//pour le tableau
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 62, 790, 220);
		contentPane.add(scrollPane);
		
		clausesTable = new JTable();
		scrollPane.setViewportView(clausesTable);
		
		JButton btnGA = new JButton("GA");
		btnGA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GAinterface ga= new GAinterface();
				ga.setVisible(true);
				dispose();
			}
		});
		btnGA.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnGA.setBounds(620, 430, 200, 35);
		contentPane.add(btnGA);
		
		JLabel labelGA = new JLabel("Genetic Algorithm :");
		labelGA.setFont(new Font("Tahoma", Font.BOLD, 15));
		labelGA.setBounds(40, 430, 200, 35);
		contentPane.add(labelGA);
		
		JLabel acsLabel = new JLabel("Ants Colony System :");
		acsLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		acsLabel.setBounds(40, 500, 200, 22);
		contentPane.add(acsLabel);
		
		JButton btnAcs = new JButton("ACS");
		btnAcs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Acs_gui acsg = new Acs_gui();
				acsg.setVisible(true);
				dispose();
			}
		});
		btnAcs.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAcs.setBounds(620, 500, 200, 35);
		contentPane.add(btnAcs);

	
		
		
		
		
		
	
	}
	 private void browseInstance(java.awt.event.ActionEvent evt) {
		 JFileChooser fc = new JFileChooser();
		 	fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
			fc.setAcceptAllFileFilterUsed(false);
			fc.addChoosableFileFilter(new FileNameExtensionFilter("Conjunctive Normal Form (.cnf)", "cnf"));
			fc.showOpenDialog(null);
	
		 try
		 {
			 	f = fc.getSelectedFile();
			 	path  = f.getAbsolutePath();
			 	
			 	
			 	LireFichier lr = new LireFichier(f);
			 	lr.readFile();
			 	DefaultTableModel tableModel = new DefaultTableModel();
			 	tableModel.addColumn("Clause");
			 	for(int i = 0; i<3;i++)
			 	{
			 		tableModel.addColumn("Literal "+(i+1));
			 	}
			 	
			 	String[] tableRow = new String[4];
				for(int i=0; i<lr.getNombreTotalClauses(); i++) {
					tableRow[0] = String.valueOf(i+1);

					for(int j=1; j<=3; j++)
						tableRow[j] = String.valueOf(lr.getClauses().get(i).getLitteraux().get(j-1).getLitteralNum());

					tableModel.addRow(tableRow);
				}
			 	clausesTable.setModel(tableModel);
	            
		 }catch(Exception e)
		 {
			 System.out.println(e.getStackTrace());
		 }
		 
		
	 }

	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_Interface frame = new Main_Interface();
					frame.setVisible(true);
				
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}); 
	
	}
}
