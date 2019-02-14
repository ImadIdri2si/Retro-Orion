package fourthDim;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSlider;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
/**
 * la classe qui cree l'interface
 * @author XU WEI DUO
 *
 */
public class CREATOR extends JFrame {

	private JPanel contentPane;
	private JSlider sldYZ;
	private JPanel panel;
	private JPanel panel_1;
	private JSlider sldXZ;
	private JPanel panel_2;
	private JSlider sldXY;
	private JPanel panel_3;
	private JSlider sldYW;
	private JPanel panel_4;
	private JSlider sldXW;
	private JPanel panel_5;
	private JSlider sldZW;
	private FormeCreator formes;
	private JPanel panel_6;
	private JSlider sldX;
	private JPanel panel_7;
	private JSlider sldY;
	private JPanel panel_8;
	private JSlider sldZ;
	private JCheckBox chckbxDimensionUp;
	private JTextField tfX;
	private JTextField tfY;
	private JTextField tfZ;
	private JButton btnCreate;
	private JPanel panel_9;
	private JCheckBox cbxDemo;
	private JSpinner spnDemo;
	private JPanel panel_10;

	private boolean linkingMode = false;
	private boolean firstSelected = false;
	
	private double x1,y1,x2,y2;
	private JPanel panel_12;
	private JPanel panel_13;
	private JCheckBox chckbxSetLink;
	private JPanel panel_15;
	private JTabbedPane tabbedPane;
	private JButton btnSave;
	private JButton btnLoad;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JButton btnExtra;
	private JButton btnReset;
	private JLabel lblY;
	private JLabel lblZ;
	private JButton btnCredit;

	/**
	 * execute l'application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CREATOR frame = new CREATOR();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * creation de l'interface avec tous ses fonctions necessaires
	 */
	public CREATOR() {
		setTitle("The 4th dimention - vR");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		setBounds((int)(width/2-694/2), (int)(height/2-708/2), 694, 708);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel_12 = new JPanel();
		panel_12.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "ROTATIONS", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_12.setBounds(10, 358, 666, 309);
		contentPane.add(panel_12);
		panel_12.setLayout(null);
		
		panel_6 = new JPanel();
		panel_6.setBounds(10, 66, 212, 73);
		panel_12.add(panel_6);
		panel_6.setLayout(null);
		panel_6.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "rotation axe X", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		sldX = new JSlider();
		sldX.setValue(0);
		sldX.setPaintTicks(true);
		sldX.setPaintLabels(true);
		sldX.setMinorTickSpacing(10);
		sldX.setMaximum(360);
		sldX.setMajorTickSpacing(90);
		sldX.setBounds(6, 17, 200, 46);
		panel_6.add(sldX);
		
		panel_7 = new JPanel();
		panel_7.setBounds(10, 149, 212, 73);
		panel_12.add(panel_7);
		panel_7.setLayout(null);
		panel_7.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "rotation axe Y", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		sldY = new JSlider();
		sldY.setValue(0);
		sldY.setPaintTicks(true);
		sldY.setPaintLabels(true);
		sldY.setMinorTickSpacing(10);
		sldY.setMaximum(360);
		sldY.setMajorTickSpacing(90);
		sldY.setBounds(6, 17, 200, 46);
		panel_7.add(sldY);
		
		panel_8 = new JPanel();
		panel_8.setBounds(10, 220, 212, 73);
		panel_12.add(panel_8);
		panel_8.setLayout(null);
		panel_8.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "rotation axe Z", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		sldZ = new JSlider();
		sldZ.setValue(0);
		sldZ.setPaintTicks(true);
		sldZ.setPaintLabels(true);
		sldZ.setMinorTickSpacing(10);
		sldZ.setMaximum(360);
		sldZ.setMajorTickSpacing(90);
		sldZ.setBounds(6, 17, 200, 46);
		panel_8.add(sldZ);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(232, 42, 422, 251);
		
		panel_12.add(tabbedPane);
		
		panel_15 = new JPanel();
		//tabbedPane.addTab("4D Rotations", null, panel_15, null);
		tabbedPane.addTab("ROTATION EN 4D", null, panel_15, null);
		panel_15.setLayout(null);
		
		panel_4 = new JPanel();
		panel_4.setBounds(208, 6, 212, 73);
		panel_15.add(panel_4);
		panel_4.setLayout(null);
		panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "rotation plan XW", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		sldXW = new JSlider();
		
		sldXW.setValue(0);
		sldXW.setPaintTicks(true);
		sldXW.setPaintLabels(true);
		sldXW.setMinorTickSpacing(10);
		sldXW.setMaximum(360);
		sldXW.setMajorTickSpacing(90);
		sldXW.setBounds(6, 17, 200, 46);
		panel_4.add(sldXW);
		
		panel_3 = new JPanel();
		panel_3.setBounds(208, 79, 212, 73);
		panel_15.add(panel_3);
		panel_3.setLayout(null);
		panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "rotation plan YW", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		sldYW = new JSlider();
		sldYW.setValue(0);
		sldYW.setPaintTicks(true);
		sldYW.setPaintLabels(true);
		sldYW.setMinorTickSpacing(10);
		sldYW.setMaximum(360);
		sldYW.setMajorTickSpacing(90);
		sldYW.setBounds(6, 17, 200, 46);
		panel_3.add(sldYW);
		
		panel_5 = new JPanel();
		panel_5.setBounds(208, 152, 212, 73);
		panel_15.add(panel_5);
		panel_5.setLayout(null);
		panel_5.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "rotation plan ZW", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		sldZW = new JSlider();
		sldZW.setValue(0);
		sldZW.setPaintTicks(true);
		sldZW.setPaintLabels(true);
		sldZW.setMinorTickSpacing(10);
		sldZW.setMaximum(360);
		sldZW.setMajorTickSpacing(90);
		sldZW.setBounds(6, 17, 200, 46);
		panel_5.add(sldZW);
		
		panel_2 = new JPanel();
		panel_2.setBounds(0, 152, 212, 73);
		panel_15.add(panel_2);
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "rotation plan XY", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		sldXY = new JSlider();
		
		sldXY.setValue(0);
		sldXY.setPaintTicks(true);
		sldXY.setPaintLabels(true);
		sldXY.setMinorTickSpacing(10);
		sldXY.setMaximum(360);
		sldXY.setMajorTickSpacing(90);
		sldXY.setBounds(6, 17, 200, 46);
		panel_2.add(sldXY);
		
		panel_1 = new JPanel();
		panel_1.setBounds(0, 79, 212, 73);
		panel_15.add(panel_1);
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "rotation plan XZ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		sldXZ = new JSlider();
		
		sldXZ.setValue(0);
		sldXZ.setPaintTicks(true);
		sldXZ.setPaintLabels(true);
		sldXZ.setMinorTickSpacing(10);
		sldXZ.setMaximum(360);
		sldXZ.setMajorTickSpacing(90);
		sldXZ.setBounds(6, 17, 200, 46);
		panel_1.add(sldXZ);
		
		panel = new JPanel();
		panel.setBounds(0, 6, 212, 73);
		panel_15.add(panel);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "rotation plan YZ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setLayout(null);
		
		sldYZ = new JSlider();
		
		
		sldYZ.setPaintTicks(true);
		sldYZ.setPaintLabels(true);
		sldYZ.setMajorTickSpacing(90);
		sldYZ.setMaximum(360);
		sldYZ.setMinorTickSpacing(10);
		sldYZ.setValue(0);
		sldYZ.setBounds(6, 17, 200, 46);
		panel.add(sldYZ);
		
		panel_13 = new JPanel();
		//tabbedPane.addTab("Forme creator", null, panel_13, null);
		tabbedPane.addTab("CREATEUR DE FORME 4D", null, panel_13, null);
		tabbedPane.setEnabledAt(1,false);
		panel_13.setBorder(null);
		panel_13.setLayout(null);
		
		panel_9 = new JPanel();
		panel_9.setBounds(6, 10, 400, 140);
		panel_13.add(panel_9);
		//panel_9.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Point Creator", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_9.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "CREATEUR DU POINT", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_9.setLayout(null);
		
		tfX = new JTextField();
		tfX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		tfX.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				//begin
				KeyLock(e);
				//fin
			}
		});
		tfX.setHorizontalAlignment(SwingConstants.CENTER);
		tfX.setBounds(43, 17, 347, 21);
		panel_9.add(tfX);
		tfX.setColumns(10);
		
		tfY = new JTextField();
		tfY.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				//begin
				KeyLock(e);
				//fin
			}
		});
		tfY.setHorizontalAlignment(SwingConstants.CENTER);
		tfY.setBounds(43, 48, 347, 21);
		panel_9.add(tfY);
		tfY.setColumns(10);
		
		tfZ = new JTextField();
		tfZ.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				//begin
				KeyLock(e);
				//fin
			}
		});
		tfZ.setToolTipText("");
		tfZ.setHorizontalAlignment(SwingConstants.CENTER);
		tfZ.setBounds(43, 79, 347, 21);
		panel_9.add(tfZ);
		tfZ.setColumns(10);
		
		//btnCreate = new JButton("CREAT");
		btnCreate = new JButton("CREER");
		btnCreate.setBackground(Color.PINK);
		btnCreate.setBounds(6, 110, 241, 23);
		panel_9.add(btnCreate);
		
		JLabel lblX = new JLabel("X:");
		lblX.setBounds(10, 20, 39, 14);
		panel_9.add(lblX);
		
		lblY = new JLabel("Y:");
		lblY.setBounds(10, 51, 39, 14);
		panel_9.add(lblY);
		
		lblZ = new JLabel("Z:");
		lblZ.setBounds(10, 82, 39, 14);
		panel_9.add(lblZ);
		
		//JCheckBox chckbxClearWhenCreating = new JCheckBox("Clear when creating");
		JCheckBox chckbxClearWhenCreating = new JCheckBox("EFFACER APRES AVOIR CREER");
		chckbxClearWhenCreating.setFont(new Font("Tahoma", Font.PLAIN, 8));
		chckbxClearWhenCreating.setBounds(253, 110, 141, 23);
		panel_9.add(chckbxClearWhenCreating);
		
		//chckbxSetLink = new JCheckBox("set link");
		chckbxSetLink = new JCheckBox("RELIER");
		chckbxSetLink.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxSetLink.setFont(new Font("Segoe UI Black", Font.PLAIN, 30));
		chckbxSetLink.setBounds(131, 153, 153, 62);
		panel_13.add(chckbxSetLink);
		
		//btnSave = new JButton("SAVE");
		btnSave = new JButton("SAUVEGARDER");
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formes.save();
			}
		});
		btnSave.setBounds(6, 153, 122, 66);
		panel_13.add(btnSave);
		
		//btnLoad = new JButton("LOAD");
		btnLoad = new JButton("CHARGER");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				formes.load();
			}
		});
		btnLoad.setBounds(290, 161, 116, 23);
		panel_13.add(btnLoad);
		
		//btnReset = new JButton("RESET");
		btnReset = new JButton("REINITIALISER");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//begin
				formes.resetAll();
				tfX.setText("");
				tfY.setText("");
				tfZ.setText("");
				sldX.setValue(0);
				sldY.setValue(0);
				sldZ.setValue(0);
				sldXY.setValue(0);
				sldXZ.setValue(0);
				sldYZ.setValue(0);
				sldXW.setValue(0);
				sldYW.setValue(0);
				sldZW.setValue(0);
				//fin
			}
		});
		btnReset.setBounds(290, 195, 116, 23);
		panel_13.add(btnReset);
		
		//chckbxDimensionUp = new JCheckBox("DIMENSION UP");
		chckbxDimensionUp = new JCheckBox("AUGMENTER EN 4EME DIMENSION");
		chckbxDimensionUp.setBounds(232, 13, 422, 23);
		panel_12.add(chckbxDimensionUp);
		chckbxDimensionUp.setBackground(Color.PINK);
		chckbxDimensionUp.setHorizontalAlignment(SwingConstants.CENTER);
		
		panel_10 = new JPanel();
		panel_10.setBounds(10, 17, 212, 47);
		panel_12.add(panel_10);
		panel_10.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Demo ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_10.setLayout(null);
		
		//cbxDemo = new JCheckBox("DemoOn");
		cbxDemo = new JCheckBox("DEMO ACTIVEE");
		cbxDemo.setFocusable(false);
		cbxDemo.setBackground(Color.PINK);
		cbxDemo.setSelected(true);
		cbxDemo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//begin
				if(cbxDemo.isSelected()) {
					spnDemo.setEnabled(true);
					formes.setDemoOn(true);
					tabbedPane.setEnabledAt(0,true);
					tabbedPane.setEnabledAt(1,false);
					tabbedPane.setSelectedIndex(0);
					
				}else {
					spnDemo.setEnabled(false);
					formes.setDemoOn(false);
					if(chckbxDimensionUp.isSelected()) {
						chckbxDimensionUp.setSelected(false);
						formes.setDimUp(false);
					}
					tabbedPane.setEnabledAt(0,false);
					tabbedPane.setEnabledAt(1,true);
					tabbedPane.setSelectedIndex(1);
				}
				//fin
			}
		});
		cbxDemo.setBounds(6, 17, 112, 23);
		panel_10.add(cbxDemo);
		
		spnDemo = new JSpinner();
		spnDemo.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				formes.setDemoChoice((int)spnDemo.getValue());
			}
		});
		spnDemo.setModel(new SpinnerNumberModel(1, 1, 3, 1));
		spnDemo.setBounds(173, 18, 29, 22);
		JFormattedTextField spnEditable=((JSpinner.DefaultEditor)spnDemo.getEditor()).
				getTextField(); spnEditable.setEditable(false); 
		panel_10.add(spnDemo);
		chckbxDimensionUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//begin
				if(chckbxDimensionUp.isSelected()) {
					formes.setDimUp(true);
					if(!cbxDemo.isSelected()) {
						tabbedPane.setEnabledAt(0,true);
						tabbedPane.setEnabledAt(1,false);
						tabbedPane.setSelectedIndex(0);
					}
					
					
				}else {
					formes.setDimUp(false);
					if(!cbxDemo.isSelected()) {
						tabbedPane.setEnabledAt(0,false);
						tabbedPane.setEnabledAt(1,true);
						tabbedPane.setSelectedIndex(1);
					}
					
				}
				//fin
			}
		});
		chckbxSetLink.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//begin
				if(chckbxSetLink.isSelected()) {
					 linkingMode = true;
				}else{
					 linkingMode = false;
				}
				//fin
			}
		});
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tfX.getText().length()==0) tfX.setText("0");
				if(tfY.getText().length()==0) tfY.setText("0");
				if(tfZ.getText().length()==0) tfZ.setText("0");
				double x = Double.parseDouble(tfX.getText());
				double y = Double.parseDouble(tfY.getText());
				double z = Double.parseDouble(tfZ.getText());
				formes.newPoint(x, y, z);
				if(chckbxClearWhenCreating.isSelected()) {
					tfX.setText("");
					tfY.setText("");
					tfZ.setText("");
				}
				
				
				//formes.creationFinished();
			}
		});
		sldYZ.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				formes.setRot(0,sldYZ.getValue());
			}
		});
		sldYZ.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				sldYZ.setValue(sldYZ.getValue()+10*e.getWheelRotation());
			}
		});
		sldXZ.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				formes.setRot(1,sldXZ.getValue());
			}
		});
		sldXZ.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				sldXZ.setValue(sldXZ.getValue()+10*e.getWheelRotation());
			}
		});
		sldXY.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				formes.setRot(2,sldXY.getValue());
			}
		});
		sldXY.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				sldXY.setValue(sldXY.getValue()+10*e.getWheelRotation());
			}
		});
		sldZW.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				formes.setRot(5,sldZW.getValue());
			}
		});
		sldZW.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				sldZW.setValue(sldZW.getValue()+10*e.getWheelRotation());
			}
		});
		sldYW.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				formes.setRot(3,sldYW.getValue());
			}
		});
		sldYW.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				sldYW.setValue(sldYW.getValue()+10*e.getWheelRotation());
			}
		});
		sldXW.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				formes.setRot(4,sldXW.getValue());
			}
		});
		sldXW.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				sldXW.setValue(sldXW.getValue()+10*e.getWheelRotation());
			}
		});
		sldZ.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				formes.setRot(8,sldZ.getValue());
			}
		});
		sldZ.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				sldZ.setValue(sldZ.getValue()+10*e.getWheelRotation());
			}
		});
		sldY.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				formes.setRot(7,sldY.getValue());
			}
		});
		sldY.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				sldY.setValue(sldY.getValue()+10*e.getWheelRotation());
			}
		});
		sldX.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				formes.setRot(6,sldX.getValue());
			}
		});
		sldX.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				sldX.setValue(sldX.getValue()+10*e.getWheelRotation());
			}
		});
		
		formes = new FormeCreator();
		formes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(linkingMode && firstSelected) {
					x2 = e.getX();
					y2 = e.getY();
					formes.search(x1,y1,x2, y2);
					formes.setLink();
					firstSelected = false;
					//System.out.println("searched");
				}else {
					x1 = e.getX();
					y1 = e.getY();
					formes.search(x1, y1);
					firstSelected = true;
				}
				
			}
		});
		formes.setBounds(10, 28, 666, 320);
		contentPane.add(formes);
		
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 688, 21);
		contentPane.add(menuBar);
		
		//mnNewMenu = new JMenu("HELP");
		mnNewMenu = new JMenu("AIDE");
		menuBar.add(mnNewMenu);
		
		btnExtra = new JButton("INFOS");
		btnExtra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//begin
				/**
						JOptionPane.showMessageDialog(null, "1. View Demo: 3 types of demo available "
						+ "\n you can click on DIMENSION Up to view the correspond 4D shape of the 3D shape of the demo"
						+ "\n2.Create your own shape:"
						+ "\n   -step 1: close the demo"
						+ "\n   -ste 2: enter correspond x,y,and z value of a point and press create"
						+ "\n   -step 3: link two points together by activating the set link case"
						+ "\n3. after creating a shape"
						+ "\n you can click on DIMENSION UP to view the correspond 4D shape of the 3D shape you created"
						+ "\n4. you can also make a save or a load for a shape created"
						+ "\n5. you can press on reset to reset the creator");
				**/
				JOptionPane.showMessageDialog(null,"1. Voir la DEMO: 3 types de DEMO disponibles"
						+ "\n     vous pouvez cliquer sur AUGMENTER EN 4EME DIMENSION pour voir la forme 4D "
						+ "\n     correspondante de la forme 3D de la DEMO"
						+ "\n2. CREER votre propre forme:"
						+ "\n     -\u00E9tape 1: ferme la DEMO"
						+ "\n     -\u00E9tape 2: entrez les valeurs x, y et z correspondantes d'un point et appuyez sur CREER"
						+ "\n     -\u00E9tape 3: lier deux points ensemble en cochant la case RELIER"
						+ "\n3. Apr\u00E8s avoir cr\u00E9\u00E9 une forme"
						+ "\n     vous pouvez cliquer sur AUGMENTER EN 4EME DIMENSION pour voir la forme en 4D "
						+ "\n     correspondant de la forme en 3D que vous avez cr¨¦¨¦e"
						+ "\n4. Vous pouvez aussi faire une sauvegarde ou un chargement pour une forme cr\u00E9\u00E9e"
						+ "\n5. Vous pouvez appuyer sur REINITIALISER pour r\u00E9initialiser le cr\u00E9ateur" );
				//fin
			}
		});
		mnNewMenu.add(btnExtra);
		
		btnCredit = new JButton("CREDIT");
		btnCredit.setFocusPainted(false);
		btnCredit.setFocusable(false);
		btnCredit.setFocusTraversalKeysEnabled(false);
		menuBar.add(btnCredit);
		btnCredit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//begin
				JOptionPane.showMessageDialog(null, "CREATED by vR \n <('OwO`)~<:3{¡Ô¡Ô¡Ô");
				//fin
			}
		});
	}
	
	/**
	 * cette methode permet a verouiller certains keys 
	 * @param e, evenement KeyEvent
	 */
	public void KeyLock(KeyEvent e) {
		if(e.getKeyChar()!='.'&&(e.getKeyChar() <'0' || e.getKeyChar() >'9')) {
			e.consume();
			getToolkit().beep();
	}
	}
}
