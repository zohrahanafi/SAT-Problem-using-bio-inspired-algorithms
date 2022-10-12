package Interface;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import GA.AlgorithmeGenetic;
import GA.Solution;
import elements.LireFichier;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import javax.swing.JTable;
import javax.swing.JScrollPane;
public class GAinterface extends JFrame {
	private long startTimeMillis;
	private JPanel contentPane;
	JPanel panel_1;
	private JTextField nombreilteraion;
	private JTextField taillepopulation;
	private JTextField t_croiement;
	private JTextField t_mutation;
	private JLabel timer;
	private JLabel labelnbrclau_1 ;
	private int k=0;
	private JTable table_1;
	   
	private ArrayList<Integer> data= new ArrayList<Integer>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GAinterface frame = new GAinterface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GAinterface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Applying GA Algorithm");
		this.setLocation(400, 250);
		setBounds(100, 100, 922, 566);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("Genetic Algorithm");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 32));
		lblNewLabel.setBounds(279, 0, 353, 60);
		panel.add(lblNewLabel);
		
		nombreilteraion = new JTextField();
		nombreilteraion.setText("2500");
		nombreilteraion.setFont(new Font("Times New Roman", Font.BOLD, 16));
		nombreilteraion.setBounds(219, 94, 116, 22);
		panel.add(nombreilteraion);
		nombreilteraion.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Number of ilteration :");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(40, 96, 162, 16);
		panel.add(lblNewLabel_1);
		
		JLabel lblTailleDePopulation = new JLabel("Population size:");
		lblTailleDePopulation.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTailleDePopulation.setBounds(40, 142, 125, 16);
		panel.add(lblTailleDePopulation);
		
		JLabel lblTauxDeCroi = new JLabel("Crossover rate : (%)");
		lblTauxDeCroi.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTauxDeCroi.setBounds(40, 179, 162, 16);
		panel.add(lblTauxDeCroi);
		
		JLabel lblTauxDeMutation = new JLabel("Mutation rate: (%)");
		lblTauxDeMutation.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTauxDeMutation.setBounds(40, 221, 162, 16);
		panel.add(lblTauxDeMutation);
		
		taillepopulation = new JTextField();
		taillepopulation.setText("25");
		taillepopulation.setFont(new Font("Times New Roman", Font.BOLD, 16));
		taillepopulation.setColumns(10);
		taillepopulation.setBounds(219, 140, 116, 22);
		panel.add(taillepopulation);
		
		t_croiement = new JTextField();
		t_croiement.setText("50");
		t_croiement.setFont(new Font("Times New Roman", Font.BOLD, 16));
		t_croiement.setColumns(10);
		t_croiement.setBounds(219, 177, 116, 22);
		panel.add(t_croiement);
		
		t_mutation = new JTextField();
		t_mutation.setText("50");
		t_mutation.setFont(new Font("Times New Roman", Font.BOLD, 16));
		t_mutation.setColumns(10);
		t_mutation.setBounds(219, 219, 116, 22);
		panel.add(t_mutation);
		
		
		
		panel_1 = new JPanel();
		panel_1.setBounds(12, 255, 882, 254);
		panel.add(panel_1);
		panel_1.setLayout(null);
		panel_1.setVisible(false);
		
		labelnbrclau_1 = new JLabel();
		labelnbrclau_1.setBounds(663, 77, 116, 36);
		panel_1.add(labelnbrclau_1);
		labelnbrclau_1.setFont(new Font("Times New Roman", Font.BOLD, 26));
		labelnbrclau_1.setVisible(false);
		
		
		JButton btnstart = new JButton("START");
		btnstart.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				long time;
				startTimeMillis= System.currentTimeMillis();
				LireFichier fr = new LireFichier(Main_Interface.f);
				fr.readFile();
				AlgorithmeGenetic ag= new AlgorithmeGenetic();
				// recuperer les parametres :
				int taillePopoltion= getPopulationSize();
				int nombreIlteration= getnumIterGa();
				int tauxMutation =getMutationRate();
				int tauxCroivement=getCrossoverRate();
				
				
				Solution solution= ag.RechercheAvecGA(fr , taillePopoltion, nombreIlteration, tauxMutation, tauxCroivement);
				
				for (int i=0;i<solution.getSol().size();i++) {
					data.add(solution.getSol().get(i).getLitteralNum());	
				}
				k= solution.getFitness();
				time=(System.currentTimeMillis()-startTimeMillis)/1000;
				
				timer.setText(String.valueOf(Math.toIntExact(time)));
				labelnbrclau_1.setText(String.valueOf(k));
				
				timer.setVisible(true);
				labelnbrclau_1.setVisible(true);
				
				panel_1.setVisible(true);
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(12, 13, 433, 228);
				panel_1.add(scrollPane);
				
				table_1 = new JTable();
				scrollPane.setViewportView(table_1);
				table_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
				
				DefaultTableModel tableModel = new DefaultTableModel();
					
		 		tableModel.addColumn("Xi");
		 		tableModel.addColumn("Value");
		 		for (int i=0;i<data.size();i++) {
					String var = "X"+String.valueOf(i+1);
					String value;
					if (data.get(i)>0) {
			 		 value = String.valueOf(1);
					}
					else {
						value = String.valueOf(0);
					}
			 		String[] tableRow = {var,value};
					tableModel.addRow(tableRow);
				}
				table_1.setModel(tableModel);
				table_1.setVisible(true);
				
				
				System.out.println(k);
				
			}
		});
		btnstart.setFont(new Font("Times New Roman", Font.BOLD, 24));
		btnstart.setBounds(650, 123, 162, 51);
		panel.add(btnstart);
		
		JButton btnHome = new JButton("HOME");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main_Interface main = new Main_Interface();
				main.setVisible(true);
				dispose();
			}
		});
		btnHome.setFont(new Font("Times New Roman", Font.BOLD, 24));
		btnHome.setBounds(30, 15, 135, 37);
		panel.add(btnHome);
		
		
		
		
		JLabel lblNewLabel_2 = new JLabel("Number of clauses satisfied :");
		lblNewLabel_2.setBounds(564, 13, 306, 51);
		panel_1.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		
		
		
		timer = new JLabel();
		timer.setBounds(663, 177, 135, 53);
		panel_1.add(timer);
		timer.setText("time");
		timer.setFont(new Font("Times New Roman", Font.BOLD, 24));
		
		JLabel lblTempEn = new JLabel("Time of execution (s) :");
		lblTempEn.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblTempEn.setBounds(564, 124, 205, 51);
		panel_1.add(lblTempEn);
		
	}
	
	public int getPopulationSize() { return Integer.parseInt(taillepopulation.getText().toString()); }
	
	public int getCrossoverRate() { return Integer.parseInt(t_croiement.getText().toString()); }
	
	public int getMutationRate() { return Integer.parseInt(t_mutation.getText().toString()); }
	
	public int getnumIterGa() { return Integer.parseInt(nombreilteraion.getText().toString()); }
}
