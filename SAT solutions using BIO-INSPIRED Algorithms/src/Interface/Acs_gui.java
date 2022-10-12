package Interface;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ACS.ACO;
import elements.LireFichier;
import elements.Litteral;
import elements.ObtenirResultat;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Vector;

import javax.swing.JScrollPane;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class Acs_gui extends JFrame {
	private static JTextField ro;
	private JPanel contentPane;
	private static JTable table;
	private static JTextField alpha;
	private static JTextField beta;
	private static JTextField q0;
	private static JTextField nbrFourmis;
	private static JTextField nbrIteration;
	private static JLabel nbrClausesSat;
	private static long startTimeMillis;
	private static JLabel lblexec;
	private JLabel lblAntsColonySystem;
	public Acs_gui() 
	{
		setTitle("Applying ACS Algorithm");
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

			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(30, 272, 708, 176);
			contentPane.add(scrollPane);
			
			table = new JTable();
			scrollPane.setViewportView(table);
			
			JButton btnStart = new JButton("Start Algorithm");
			btnStart.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					essai(e);
				}
			});
			btnStart.setFont(new Font("Tahoma", Font.BOLD, 15));
			contentPane.add(btnStart);
			btnStart.setBounds(285, 210, 190, 39);
			
			alpha = new JTextField();
			alpha.setText("2.1");
			alpha.setBounds(84, 82, 116, 22);
			contentPane.add(alpha);
			alpha.setColumns(10);
			
			beta = new JTextField();
			beta.setText("0.001");
			beta.setBounds(359, 82, 116, 22);
			contentPane.add(beta);
			beta.setColumns(10);
			
			JLabel lblAlpha = new JLabel("Alpha : ");
			lblAlpha.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblAlpha.setBounds(16, 85, 56, 16);
			contentPane.add(lblAlpha);
			
			JLabel lblBeta = new JLabel("Beta :");
			lblBeta.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblBeta.setBounds(258, 85, 56, 16);
			contentPane.add(lblBeta);
			
			q0 = new JTextField();
			q0.setText("0.001");
			q0.setBounds(654, 82, 116, 22);
			contentPane.add(q0);
			q0.setColumns(10);
			
			JLabel lblQ = new JLabel("Q0 :");
			lblQ.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblQ.setBounds(512, 85, 56, 16);
			contentPane.add(lblQ);
			
			JLabel lblNbrFourmis = new JLabel("Ants Number :");
			lblNbrFourmis.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNbrFourmis.setBounds(258, 144, 116, 16);
			contentPane.add(lblNbrFourmis);
			
			nbrFourmis = new JTextField();
			nbrFourmis.setText("30");
			nbrFourmis.setBounds(359, 141, 116, 22);
			contentPane.add(nbrFourmis);
			nbrFourmis.setColumns(10);
			
			nbrIteration = new JTextField();
			nbrIteration.setText("70");
			nbrIteration.setBounds(654, 141, 116, 22);
			contentPane.add(nbrIteration);
			nbrIteration.setColumns(10);
			
			JLabel lblNombreDitrations = new JLabel("Iteration Number :");
			lblNombreDitrations.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNombreDitrations.setBounds(512, 144, 120, 16);
			contentPane.add(lblNombreDitrations);
			
			ro = new JTextField();
			ro.setBounds(84, 141, 116, 22);
			contentPane.add(ro);
			ro.setColumns(10);
			ro.setText("0.03");
			JLabel lblRo = new JLabel("Ro : ");
			lblRo.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblRo.setBounds(16, 144, 56, 16);
			contentPane.add(lblRo);
			
			nbrClausesSat = new JLabel("New label");
			nbrClausesSat.setFont(new Font("Tahoma", Font.BOLD, 13));
			nbrClausesSat.setBounds(322, 461, 174, 30);
			contentPane.add(nbrClausesSat);
			nbrClausesSat.setVisible(false);
			lblexec = new JLabel("New label");
			lblexec.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblexec.setBounds(295, 504, 211, 30);
			contentPane.add(lblexec);
			
			lblAntsColonySystem = new JLabel("Ant Colony System");
			lblAntsColonySystem.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblAntsColonySystem.setBounds(285, 11, 311, 37);
			contentPane.add(lblAntsColonySystem);
			lblexec.setVisible(false);
	 }
	 
	 public static  float getAlpha() { return Float.parseFloat(alpha.getText().toString()); }
	 public static float getBeta() { return Float.parseFloat(beta.getText().toString()); }
	 public static float getQ0() {  return Float.parseFloat(q0.getText().toString()); }
	 public static float getRo() {  return Float.parseFloat(ro.getText().toString()); }
	 public static int getNbrIter() { return Integer.parseInt(nbrIteration.getText().toString()); } 
	 public static int getNbrFourmis() { return Integer.parseInt(nbrFourmis.getText().toString()); } 
	 
	 
	 public static void essai(java.awt.event.ActionEvent evt)
	 {
		 try
		 {
		LireFichier fr = new LireFichier(Main_Interface.f);
			fr.readFile();
			
			float alpha = getAlpha();
			float beta = getBeta();
			int nbrIter = getNbrIter();
			int nbrFourmis = getNbrFourmis();
			float ro = getRo();
			float q0 = getQ0();
			long time;
			startTimeMillis= System.currentTimeMillis();
		
			ACO.algorithmeACO(fr, alpha, beta, q0, ro, nbrFourmis, nbrIter);
			time=(System.currentTimeMillis()-startTimeMillis)/1000;
			ObtenirResultat obr = new ObtenirResultat();
			Vector<Litteral> lit = new Vector<Litteral>();
			for(int i = 0; i< ACO.bestAnt.getSolution().size(); i++)
			{
				lit.add(new Litteral(ACO.bestAnt.getSolution().get(i)));
			}
			Map<Integer, Integer> map  = obr.obtenir(lit);
			DefaultTableModel tableModel = new DefaultTableModel();
		 			
	 		tableModel.addColumn("Xi");
	 		tableModel.addColumn("Value");
	 	
		
	 	table.setModel(tableModel);
		
	 	for (Map.Entry<Integer, Integer> entry : map.entrySet()) 
		{ 
	 		String var = "X"+String.valueOf(entry.getKey());
	 		String value = String.valueOf(entry.getValue());
	 		String[] tableRow = {var,value};
			tableModel.addRow(tableRow);
			
		}
	 	table.setModel(tableModel);
	 	int sat = ACO.bestAnt.getEvaluation();
	 	nbrClausesSat.setText(String.valueOf(Math.toIntExact(sat))+" Satisfied clauses");
	 	lblexec.setText("Execution time : "+String.valueOf(Math.toIntExact(time))+" Seconds");
	 	nbrClausesSat.setVisible(true);
	 	lblexec.setVisible(true);
		 }catch(Exception e)
		 {
			 e.printStackTrace();
		 }
	 }
	 
	 public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Acs_gui frame = new Acs_gui();
						frame.setVisible(true);
						
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}); 
		
		}
}
