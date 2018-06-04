package userInterface;

//author GUI Nils
//author logic Nico

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JPasswordField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.JInternalFrame;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import logic.Category;
import logic.ComboBoxPerson;
import logic.ComponentManagement;
import logic.PersonManagement;
import logic.ProductionManagement;
import logic.Order;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.AbstractListModel;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ListSelectionModel;
import javax.swing.JTable;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Frame;

public class GUIFabricationmanagement {

	private JFrame frmElabVerwaltungsprogramm;
	private JTextField textFieldID;
	private JTextField textFieldorderTitel;
	private JTextField textFieldnoteOther;
	private JTextField textFieldpredictedCosts;
	private JTextField textFieldactualCosts;
	private JTextField textFieldorderTitelModify;
	private JTextField textFieldnoteOtherModify;
	private JTextField textFieldpredictedCostsModify;
	private JTextField textFieldactualCostsModify;
	private JTextField textFieldorderSearchModify;
	private JTextField textFieldorderSearchDelete;
	private JTextField textFieldfilePath;
	private JTextField textFieldfilePathModify;
	private JTextField textFieldorderIDModify;
	private JTextField textFieldfileName;
	private JTextField textFieldfileNameModify;


	private JComboBox comboBoxorderTyp;
	private JComboBox comboBoxResponsible;
	private JComboBox comboBoxStandinResponsible;
	private JComboBox comboBoxorderStatus;
	private JComboBox comboBoxStandinResponsiblePersonModify;
	private JComboBox comboBoxResponsiblePersonModify;
	private JComboBox comboBoxtypModify;
	private JComboBox comboBoxModifyStatus;
	private JComboBox comboBoxorderCustomer;
	private JComboBox comboBoxorderCustomerModify;

	private JTable table;
	private JTable TableDeleteOrder;
	private List<String> comboBoxEntries;
	private List<String> comboBoxStatusEntries;

	/**
	 * Initialize the contents of the frame.
	 */
	public GUIFabricationmanagement() {
		comboBoxEntries = new ArrayList<String>();
		comboBoxEntries.add("3D-Druck");
		comboBoxEntries.add("Leiterplatte");
		comboBoxEntries.add("Sonstiges");
		
		comboBoxStatusEntries = new ArrayList<String>();
		comboBoxStatusEntries.add("Angenommen");
		comboBoxStatusEntries.add("Gefertigt");
		comboBoxStatusEntries.add("Kosten kalkuliert");
		comboBoxStatusEntries.add("Abgeholt");
		comboBoxStatusEntries.add("Abgerechnet");
		comboBoxStatusEntries.add("Warten auf Material");
		comboBoxStatusEntries.add("Fertigung unterbrochen/defekt");
		comboBoxStatusEntries.add("Rechnung erzeugt");
		
		frmElabVerwaltungsprogramm = new JFrame();
		frmElabVerwaltungsprogramm.setUndecorated(true);
		frmElabVerwaltungsprogramm.setExtendedState(Frame.MAXIMIZED_BOTH);

		frmElabVerwaltungsprogramm.setTitle("Elab Verwaltungsprogramm");
		frmElabVerwaltungsprogramm.setBounds(100, 100, 1036, 727);
		frmElabVerwaltungsprogramm.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		frmElabVerwaltungsprogramm.getContentPane().setLayout(gridBagLayout);

		JLabel lblorderManagement = new JLabel("Auftragsverwaltung");
		lblorderManagement.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_lblorderManagement = new GridBagConstraints();
		gbc_lblorderManagement.insets = new Insets(0, 0, 5, 0);
		gbc_lblorderManagement.gridx = 0;
		gbc_lblorderManagement.gridy = 0;
		frmElabVerwaltungsprogramm.getContentPane().add(lblorderManagement, gbc_lblorderManagement);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 1;
		frmElabVerwaltungsprogramm.getContentPane().add(tabbedPane, gbc_tabbedPane);

		JPanel paneladdOrder = new JPanel();
		tabbedPane.addTab("Auftrag hinzufügen", null, paneladdOrder, null);
		GridBagLayout gbl_paneladdOrder = new GridBagLayout();
		gbl_paneladdOrder.columnWidths = new int[] { 0, 0, 0 };
		gbl_paneladdOrder.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_paneladdOrder.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_paneladdOrder.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 1.0, Double.MIN_VALUE };
		paneladdOrder.setLayout(gbl_paneladdOrder);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(null);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		paneladdOrder.add(lblNewLabel, gbc_lblNewLabel);

		JLabel lblID = new JLabel("AuftragsID");
		lblID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblID = new GridBagConstraints();
		gbc_lblID.anchor = GridBagConstraints.EAST;
		gbc_lblID.insets = new Insets(0, 0, 5, 5);
		gbc_lblID.gridx = 0;
		gbc_lblID.gridy = 1;
		paneladdOrder.add(lblID, gbc_lblID);

		textFieldID = new JTextField();
		textFieldID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldID.setEditable(false);
		GridBagConstraints gbc_textFieldID = new GridBagConstraints();
		gbc_textFieldID.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldID.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldID.gridx = 1;
		gbc_textFieldID.gridy = 1;
		paneladdOrder.add(textFieldID, gbc_textFieldID);
		textFieldID.setColumns(10);

		JLabel lblorderCustomer = new JLabel("Kunde");
		lblorderCustomer.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblorderCustomer = new GridBagConstraints();
		gbc_lblorderCustomer.anchor = GridBagConstraints.EAST;
		gbc_lblorderCustomer.insets = new Insets(0, 0, 5, 5);
		gbc_lblorderCustomer.gridx = 0;
		gbc_lblorderCustomer.gridy = 2;
		paneladdOrder.add(lblorderCustomer, gbc_lblorderCustomer);

		JComboBox comboBoxoderCustomer = new JComboBox();
		comboBoxoderCustomer.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_comboBoxoderCustomer = new GridBagConstraints();
		gbc_comboBoxoderCustomer.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxoderCustomer.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxoderCustomer.gridx = 1;
		gbc_comboBoxoderCustomer.gridy = 2;
		paneladdOrder.add(comboBoxoderCustomer, gbc_comboBoxoderCustomer);
		try {
			for(int i = 0; i<ProductionManagement.getCustomerArray().length; i++) {
				comboBoxoderCustomer.addItem(ProductionManagement.getCustomerArray()[i]);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JLabel lblorderTitel = new JLabel("Titel");
		lblorderTitel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblorderTitel = new GridBagConstraints();
		gbc_lblorderTitel.anchor = GridBagConstraints.EAST;
		gbc_lblorderTitel.insets = new Insets(0, 0, 5, 5);
		gbc_lblorderTitel.gridx = 0;
		gbc_lblorderTitel.gridy = 3;
		paneladdOrder.add(lblorderTitel, gbc_lblorderTitel);

		textFieldorderTitel = new JTextField();
		textFieldorderTitel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_textFieldorderTitel = new GridBagConstraints();
		gbc_textFieldorderTitel.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldorderTitel.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldorderTitel.gridx = 1;
		gbc_textFieldorderTitel.gridy = 3;
		paneladdOrder.add(textFieldorderTitel, gbc_textFieldorderTitel);
		textFieldorderTitel.setColumns(10);

		JLabel lblorderTyp = new JLabel("Auftragsart");
		lblorderTyp.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblorderTyp = new GridBagConstraints();
		gbc_lblorderTyp.anchor = GridBagConstraints.EAST;
		gbc_lblorderTyp.insets = new Insets(0, 0, 5, 5);
		gbc_lblorderTyp.gridx = 0;
		gbc_lblorderTyp.gridy = 4;
		paneladdOrder.add(lblorderTyp, gbc_lblorderTyp);

		JComboBox comboBoxorderTyp = new JComboBox();
		comboBoxorderTyp.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBoxorderTyp.setModel(new DefaultComboBoxModel(comboBoxEntries.toArray()));
		GridBagConstraints gbc_comboBoxorderTyp = new GridBagConstraints();
		gbc_comboBoxorderTyp.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxorderTyp.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxorderTyp.gridx = 1;
		gbc_comboBoxorderTyp.gridy = 4;
		paneladdOrder.add(comboBoxorderTyp, gbc_comboBoxorderTyp);

		JLabel lblnoteOther = new JLabel("Notiz zu sonstiges");
		lblnoteOther.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblnoteOther = new GridBagConstraints();
		gbc_lblnoteOther.anchor = GridBagConstraints.EAST;
		gbc_lblnoteOther.insets = new Insets(0, 0, 5, 5);
		gbc_lblnoteOther.gridx = 0;
		gbc_lblnoteOther.gridy = 5;
		paneladdOrder.add(lblnoteOther, gbc_lblnoteOther);

		textFieldnoteOther = new JTextField();
		textFieldnoteOther.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_textFieldnoteOther = new GridBagConstraints();
		gbc_textFieldnoteOther.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldnoteOther.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldnoteOther.gridx = 1;
		gbc_textFieldnoteOther.gridy = 5;
		paneladdOrder.add(textFieldnoteOther, gbc_textFieldnoteOther);
		textFieldnoteOther.setColumns(10);

		JLabel lblfileName = new JLabel("Dateiname");
		lblfileName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblfileName = new GridBagConstraints();
		gbc_lblfileName.anchor = GridBagConstraints.EAST;
		gbc_lblfileName.insets = new Insets(0, 0, 5, 5);
		gbc_lblfileName.gridx = 0;
		gbc_lblfileName.gridy = 6;
		paneladdOrder.add(lblfileName, gbc_lblfileName);

		textFieldfileName = new JTextField();
		textFieldfileName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_textFieldfileName = new GridBagConstraints();
		gbc_textFieldfileName.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldfileName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldfileName.gridx = 1;
		gbc_textFieldfileName.gridy = 6;
		paneladdOrder.add(textFieldfileName, gbc_textFieldfileName);
		textFieldfileName.setColumns(10);

		JLabel lblfilePath = new JLabel("Dateipfad");
		lblfilePath.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblfilePath = new GridBagConstraints();
		gbc_lblfilePath.anchor = GridBagConstraints.EAST;
		gbc_lblfilePath.insets = new Insets(0, 0, 5, 5);
		gbc_lblfilePath.gridx = 0;
		gbc_lblfilePath.gridy = 7;
		paneladdOrder.add(lblfilePath, gbc_lblfilePath);

		textFieldfilePath = new JTextField();
		textFieldfilePath.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_textFieldfilePath = new GridBagConstraints();
		gbc_textFieldfilePath.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldfilePath.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldfilePath.gridx = 1;
		gbc_textFieldfilePath.gridy = 7;
		paneladdOrder.add(textFieldfilePath, gbc_textFieldfilePath);
		textFieldfilePath.setColumns(10);

		JLabel lblpredictedCost = new JLabel("Prognostizierte Kosten");
		lblpredictedCost.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblpredictedCost = new GridBagConstraints();
		gbc_lblpredictedCost.anchor = GridBagConstraints.EAST;
		gbc_lblpredictedCost.insets = new Insets(0, 0, 5, 5);
		gbc_lblpredictedCost.gridx = 0;
		gbc_lblpredictedCost.gridy = 8;
		paneladdOrder.add(lblpredictedCost, gbc_lblpredictedCost);

		textFieldpredictedCosts = new JTextField();
		textFieldpredictedCosts.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_textFieldpredictedCosts = new GridBagConstraints();
		gbc_textFieldpredictedCosts.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldpredictedCosts.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldpredictedCosts.gridx = 1;
		gbc_textFieldpredictedCosts.gridy = 8;
		paneladdOrder.add(textFieldpredictedCosts, gbc_textFieldpredictedCosts);
		textFieldpredictedCosts.setColumns(10);

		JLabel lblactualCosts = new JLabel("reele Kosten");
		lblactualCosts.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblactualCosts = new GridBagConstraints();
		gbc_lblactualCosts.anchor = GridBagConstraints.EAST;
		gbc_lblactualCosts.insets = new Insets(0, 0, 5, 5);
		gbc_lblactualCosts.gridx = 0;
		gbc_lblactualCosts.gridy = 9;
		paneladdOrder.add(lblactualCosts, gbc_lblactualCosts);

		textFieldactualCosts = new JTextField();
		textFieldactualCosts.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldactualCosts.setEditable(true);
		GridBagConstraints gbc_textFieldactualCosts = new GridBagConstraints();
		gbc_textFieldactualCosts.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldactualCosts.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldactualCosts.gridx = 1;
		gbc_textFieldactualCosts.gridy = 9;
		paneladdOrder.add(textFieldactualCosts, gbc_textFieldactualCosts);
		textFieldactualCosts.setColumns(10);

		JLabel lblresponsiblePerson = new JLabel("Verantwortlicher");
		lblresponsiblePerson.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblresponsiblePerson.setToolTipText("");
		GridBagConstraints gbc_lblresponsiblePerson = new GridBagConstraints();
		gbc_lblresponsiblePerson.anchor = GridBagConstraints.EAST;
		gbc_lblresponsiblePerson.insets = new Insets(0, 0, 5, 5);
		gbc_lblresponsiblePerson.gridx = 0;
		gbc_lblresponsiblePerson.gridy = 10;
		paneladdOrder.add(lblresponsiblePerson, gbc_lblresponsiblePerson);

		JComboBox comboBoxResponsible = new JComboBox();
		comboBoxResponsible.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_comboBoxResponsible = new GridBagConstraints();
		gbc_comboBoxResponsible.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxResponsible.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxResponsible.gridx = 1;
		gbc_comboBoxResponsible.gridy = 10;
		paneladdOrder.add(comboBoxResponsible, gbc_comboBoxResponsible);
		try {
			for(int i = 0; i<ProductionManagement.getAdvisorArray().length; i++) {
				comboBoxResponsible.addItem(ProductionManagement.getAdvisorArray()[i]);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JLabel lblstandinResponsible = new JLabel("Vertretung");
		lblstandinResponsible.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblstandinResponsible = new GridBagConstraints();
		gbc_lblstandinResponsible.anchor = GridBagConstraints.EAST;
		gbc_lblstandinResponsible.insets = new Insets(0, 0, 5, 5);
		gbc_lblstandinResponsible.gridx = 0;
		gbc_lblstandinResponsible.gridy = 11;
		paneladdOrder.add(lblstandinResponsible, gbc_lblstandinResponsible);

		JComboBox comboBoxStandinResponsible = new JComboBox();
		comboBoxStandinResponsible.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_comboBoxStandinResponsible = new GridBagConstraints();
		gbc_comboBoxStandinResponsible.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxStandinResponsible.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxStandinResponsible.gridx = 1;
		gbc_comboBoxStandinResponsible.gridy = 11;
		paneladdOrder.add(comboBoxStandinResponsible, gbc_comboBoxStandinResponsible);
		try {
			for(int i = 0; i<ProductionManagement.getSecondaryAdvisorArray().length; i++) {
				comboBoxStandinResponsible.addItem(ProductionManagement.getSecondaryAdvisorArray()[i]);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JLabel lblorderStatus = new JLabel("Auftragsstatus");
		lblorderStatus.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblorderStatus = new GridBagConstraints();
		gbc_lblorderStatus.anchor = GridBagConstraints.EAST;
		gbc_lblorderStatus.insets = new Insets(0, 0, 5, 5);
		gbc_lblorderStatus.gridx = 0;
		gbc_lblorderStatus.gridy = 12;
		paneladdOrder.add(lblorderStatus, gbc_lblorderStatus);

		JComboBox comboBoxorderStatus = new JComboBox();
		comboBoxorderStatus.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBoxorderStatus.setModel(new DefaultComboBoxModel(comboBoxStatusEntries.toArray()));
		GridBagConstraints gbc_comboBoxorderStatus = new GridBagConstraints();
		gbc_comboBoxorderStatus.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxorderStatus.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxorderStatus.gridx = 1;
		gbc_comboBoxorderStatus.gridy = 12;
		paneladdOrder.add(comboBoxorderStatus, gbc_comboBoxorderStatus);

		JButton btnaddOrder = new JButton("Auftrag hinzufügen");
		btnaddOrder.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnaddOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ProductionManagement.addOrder(textFieldorderTitel.getText(), comboBoxorderTyp.getSelectedIndex(),
							Double.parseDouble(textFieldpredictedCosts.getText()),
							Double.parseDouble(textFieldactualCosts.getText()),
							getIdCustomer(comboBoxorderCustomer.getSelectedIndex()), getIdAdvisor(comboBoxResponsible.getSelectedIndex()),
							getIdSecondaryAdvisor(comboBoxStandinResponsible.getSelectedIndex()), textFieldfileName.getText() , textFieldfilePath.getText(),
							textFieldnoteOther.getText(), comboBoxorderStatus.getSelectedIndex());
					refreshTable();
					refreshTableDeleteOrder(); 
				} catch (Exception a) {
					a.printStackTrace();
				}
			}
		}); 

		GridBagConstraints gbc_btnaddOrder = new GridBagConstraints();
		gbc_btnaddOrder.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnaddOrder.insets = new Insets(0, 0, 5, 0);
		gbc_btnaddOrder.gridx = 1;
		gbc_btnaddOrder.gridy = 13;
		paneladdOrder.add(btnaddOrder, gbc_btnaddOrder);

		JButton btndeleteallInputs = new JButton("Eingaben löschen");
		btndeleteallInputs.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btndeleteallInputs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldorderTitel.setText("");
				textFieldnoteOther.setText("");
				textFieldpredictedCosts.setText("");
				textFieldfilePath.setText("");
			}
		});

		GridBagConstraints gbc_btndeleteallInputs = new GridBagConstraints();
		gbc_btndeleteallInputs.insets = new Insets(0, 0, 5, 0);
		gbc_btndeleteallInputs.fill = GridBagConstraints.HORIZONTAL;
		gbc_btndeleteallInputs.gridx = 1;
		gbc_btndeleteallInputs.gridy = 14;
		paneladdOrder.add(btndeleteallInputs, gbc_btndeleteallInputs);

		JLabel lbleLabpicture = new JLabel("");
		lbleLabpicture.setIcon(new ImageIcon("C:\\Users\\Nils\\git\\ProPraElab\\ProPraElab\\pictures\\elab.png"));
		GridBagConstraints gbc_lbleLabpicture = new GridBagConstraints();
		gbc_lbleLabpicture.gridwidth = 2;
		gbc_lbleLabpicture.gridx = 0;
		gbc_lbleLabpicture.gridy = 15;
		paneladdOrder.add(lbleLabpicture, gbc_lbleLabpicture);

		JPanel panelmodify = new JPanel();
		tabbedPane.addTab("Auftrag bearbeiten", null, panelmodify, null);
		GridBagLayout gbl_panelmodify = new GridBagLayout();
		gbl_panelmodify.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_panelmodify.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelmodify.columnWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_panelmodify.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0,
				0.0, 0.0, Double.MIN_VALUE };
		panelmodify.setLayout(gbl_panelmodify);

		JLabel lblorderIDModify = new JLabel("ID");
		lblorderIDModify.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblorderIDModify = new GridBagConstraints();
		gbc_lblorderIDModify.gridwidth = 2;
		gbc_lblorderIDModify.anchor = GridBagConstraints.EAST;
		gbc_lblorderIDModify.insets = new Insets(0, 0, 5, 5);
		gbc_lblorderIDModify.gridx = 0;
		gbc_lblorderIDModify.gridy = 0;
		panelmodify.add(lblorderIDModify, gbc_lblorderIDModify);

		textFieldorderIDModify = new JTextField();
		textFieldorderIDModify.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldorderIDModify.setEditable(false);
		GridBagConstraints gbc_textFieldorderIDModify = new GridBagConstraints();
		gbc_textFieldorderIDModify.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldorderIDModify.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldorderIDModify.gridx = 2;
		gbc_textFieldorderIDModify.gridy = 0;
		panelmodify.add(textFieldorderIDModify, gbc_textFieldorderIDModify);
		textFieldorderIDModify.setColumns(10);

		JLabel lblorderCustomerModify = new JLabel("Kunde");
		lblorderCustomerModify.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblorderCustomerModify = new GridBagConstraints();
		gbc_lblorderCustomerModify.anchor = GridBagConstraints.EAST;
		gbc_lblorderCustomerModify.gridwidth = 2;
		gbc_lblorderCustomerModify.insets = new Insets(0, 0, 5, 5);
		gbc_lblorderCustomerModify.gridx = 0;
		gbc_lblorderCustomerModify.gridy = 1;
		panelmodify.add(lblorderCustomerModify, gbc_lblorderCustomerModify);

		JComboBox comboBoxorderCustomerModify = new JComboBox();
		comboBoxorderCustomerModify.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_comboBoxorderCustomerModify = new GridBagConstraints();
		gbc_comboBoxorderCustomerModify.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxorderCustomerModify.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxorderCustomerModify.gridx = 2;
		gbc_comboBoxorderCustomerModify.gridy = 1;
		panelmodify.add(comboBoxorderCustomerModify, gbc_comboBoxorderCustomerModify);
		try {
			for(int i = 0; i<ProductionManagement.getCustomerArray().length; i++) {
				comboBoxorderCustomerModify.addItem(ProductionManagement.getCustomerArray()[i]);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		JLabel lblorderTitelModify = new JLabel("Titel");
		lblorderTitelModify.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblorderTitelModify = new GridBagConstraints();
		gbc_lblorderTitelModify.gridwidth = 2;
		gbc_lblorderTitelModify.anchor = GridBagConstraints.EAST;
		gbc_lblorderTitelModify.insets = new Insets(0, 0, 5, 5);
		gbc_lblorderTitelModify.gridx = 0;
		gbc_lblorderTitelModify.gridy = 2;
		panelmodify.add(lblorderTitelModify, gbc_lblorderTitelModify);

		textFieldorderTitelModify = new JTextField();
		textFieldorderTitelModify.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_textFieldorderTitelModify = new GridBagConstraints();
		gbc_textFieldorderTitelModify.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldorderTitelModify.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldorderTitelModify.gridx = 2;
		gbc_textFieldorderTitelModify.gridy = 2;
		panelmodify.add(textFieldorderTitelModify, gbc_textFieldorderTitelModify);
		textFieldorderTitelModify.setColumns(10);

		JLabel lblorderTypModify = new JLabel("Auftragsart");
		lblorderTypModify.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblorderTypModify = new GridBagConstraints();
		gbc_lblorderTypModify.gridwidth = 2;
		gbc_lblorderTypModify.anchor = GridBagConstraints.EAST;
		gbc_lblorderTypModify.insets = new Insets(0, 0, 5, 5);
		gbc_lblorderTypModify.gridx = 0;
		gbc_lblorderTypModify.gridy = 3;
		panelmodify.add(lblorderTypModify, gbc_lblorderTypModify);

		JComboBox comboBoxtypModify = new JComboBox();
		comboBoxtypModify.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBoxtypModify.setModel(new DefaultComboBoxModel(comboBoxEntries.toArray()));
		GridBagConstraints gbc_comboBoxtypModify = new GridBagConstraints();
		gbc_comboBoxtypModify.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxtypModify.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxtypModify.gridx = 2;
		gbc_comboBoxtypModify.gridy = 3;
		panelmodify.add(comboBoxtypModify, gbc_comboBoxtypModify);

		JLabel lblnoteOtherModify = new JLabel("Notiz zu Sonstiges");
		lblnoteOtherModify.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblnoteOtherModify = new GridBagConstraints();
		gbc_lblnoteOtherModify.gridwidth = 2;
		gbc_lblnoteOtherModify.anchor = GridBagConstraints.EAST;
		gbc_lblnoteOtherModify.insets = new Insets(0, 0, 5, 5);
		gbc_lblnoteOtherModify.gridx = 0;
		gbc_lblnoteOtherModify.gridy = 4;
		panelmodify.add(lblnoteOtherModify, gbc_lblnoteOtherModify);

		textFieldnoteOtherModify = new JTextField();
		textFieldnoteOtherModify.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_textFieldnoteOtherModify = new GridBagConstraints();
		gbc_textFieldnoteOtherModify.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldnoteOtherModify.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldnoteOtherModify.gridx = 2;
		gbc_textFieldnoteOtherModify.gridy = 4;
		panelmodify.add(textFieldnoteOtherModify, gbc_textFieldnoteOtherModify);
		textFieldnoteOtherModify.setColumns(10);

		JLabel lblfileNameModify = new JLabel("Dateiname");
		lblfileNameModify.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblfileNameModify = new GridBagConstraints();
		gbc_lblfileNameModify.anchor = GridBagConstraints.EAST;
		gbc_lblfileNameModify.gridwidth = 2;
		gbc_lblfileNameModify.insets = new Insets(0, 0, 5, 5);
		gbc_lblfileNameModify.gridx = 0;
		gbc_lblfileNameModify.gridy = 5;
		panelmodify.add(lblfileNameModify, gbc_lblfileNameModify);

		textFieldfileNameModify = new JTextField();
		GridBagConstraints gbc_textFieldfileNameModify = new GridBagConstraints();
		gbc_textFieldfileNameModify.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldfileNameModify.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldfileNameModify.gridx = 2;
		gbc_textFieldfileNameModify.gridy = 5;
		panelmodify.add(textFieldfileNameModify, gbc_textFieldfileNameModify);
		textFieldfileNameModify.setColumns(10);

		JLabel lblfilePathModify = new JLabel("Dateipfad");
		lblfilePathModify.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblfilePathModify = new GridBagConstraints();
		gbc_lblfilePathModify.anchor = GridBagConstraints.EAST;
		gbc_lblfilePathModify.gridwidth = 2;
		gbc_lblfilePathModify.insets = new Insets(0, 0, 5, 5);
		gbc_lblfilePathModify.gridx = 0;
		gbc_lblfilePathModify.gridy = 6;
		panelmodify.add(lblfilePathModify, gbc_lblfilePathModify);

		textFieldfilePathModify = new JTextField();
		textFieldfilePathModify.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldfilePathModify.setText("");
		GridBagConstraints gbc_textFieldfilePathModify = new GridBagConstraints();
		gbc_textFieldfilePathModify.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldfilePathModify.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldfilePathModify.gridx = 2;
		gbc_textFieldfilePathModify.gridy = 6;
		panelmodify.add(textFieldfilePathModify, gbc_textFieldfilePathModify);
		textFieldfilePathModify.setColumns(10);

		JLabel lblpredictedCostsModify = new JLabel("Prognostizierte Kosten");
		lblpredictedCostsModify.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblpredictedCostsModify = new GridBagConstraints();
		gbc_lblpredictedCostsModify.gridwidth = 2;
		gbc_lblpredictedCostsModify.anchor = GridBagConstraints.EAST;
		gbc_lblpredictedCostsModify.insets = new Insets(0, 0, 5, 5);
		gbc_lblpredictedCostsModify.gridx = 0;
		gbc_lblpredictedCostsModify.gridy = 7;
		panelmodify.add(lblpredictedCostsModify, gbc_lblpredictedCostsModify);

		textFieldpredictedCostsModify = new JTextField();
		textFieldpredictedCostsModify.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_textFieldpredictedCostsModify = new GridBagConstraints();
		gbc_textFieldpredictedCostsModify.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldpredictedCostsModify.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldpredictedCostsModify.gridx = 2;
		gbc_textFieldpredictedCostsModify.gridy = 7;
		panelmodify.add(textFieldpredictedCostsModify, gbc_textFieldpredictedCostsModify);
		textFieldpredictedCostsModify.setColumns(10);

		JLabel lblactualCostsModify = new JLabel("Reelle Kosten");
		lblactualCostsModify.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblactualCostsModify = new GridBagConstraints();
		gbc_lblactualCostsModify.gridwidth = 2;
		gbc_lblactualCostsModify.anchor = GridBagConstraints.EAST;
		gbc_lblactualCostsModify.insets = new Insets(0, 0, 5, 5);
		gbc_lblactualCostsModify.gridx = 0;
		gbc_lblactualCostsModify.gridy = 8;
		panelmodify.add(lblactualCostsModify, gbc_lblactualCostsModify);

		textFieldactualCostsModify = new JTextField();
		textFieldactualCostsModify.setEditable(false);
		GridBagConstraints gbc_textFieldactualCostsModify = new GridBagConstraints();
		gbc_textFieldactualCostsModify.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldactualCostsModify.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldactualCostsModify.gridx = 2;
		gbc_textFieldactualCostsModify.gridy = 8;
		panelmodify.add(textFieldactualCostsModify, gbc_textFieldactualCostsModify);
		textFieldactualCostsModify.setColumns(10);

		JLabel lblresponsiblePersonModify = new JLabel("Verantwortlicher");
		lblresponsiblePersonModify.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblresponsiblePersonModify = new GridBagConstraints();
		gbc_lblresponsiblePersonModify.gridwidth = 2;
		gbc_lblresponsiblePersonModify.anchor = GridBagConstraints.EAST;
		gbc_lblresponsiblePersonModify.insets = new Insets(0, 0, 5, 5);
		gbc_lblresponsiblePersonModify.gridx = 0;
		gbc_lblresponsiblePersonModify.gridy = 9;
		panelmodify.add(lblresponsiblePersonModify, gbc_lblresponsiblePersonModify);

		JComboBox comboBoxResponsiblePersonModify = new JComboBox();
		comboBoxResponsiblePersonModify.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_comboBoxResponsiblePersonModify = new GridBagConstraints();
		gbc_comboBoxResponsiblePersonModify.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxResponsiblePersonModify.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxResponsiblePersonModify.gridx = 2;
		gbc_comboBoxResponsiblePersonModify.gridy = 9;
		panelmodify.add(comboBoxResponsiblePersonModify, gbc_comboBoxResponsiblePersonModify);
		try {
			for(int i = 0; i<ProductionManagement.getAdvisorArray().length; i++) {
				comboBoxResponsiblePersonModify.addItem(ProductionManagement.getAdvisorArray()[i]);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JLabel lblstandinresponsiblePersonModify = new JLabel("Vertretung");
		lblstandinresponsiblePersonModify.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblstandinresponsiblePersonModify = new GridBagConstraints();
		gbc_lblstandinresponsiblePersonModify.gridwidth = 2;
		gbc_lblstandinresponsiblePersonModify.anchor = GridBagConstraints.EAST;
		gbc_lblstandinresponsiblePersonModify.insets = new Insets(0, 0, 5, 5);
		gbc_lblstandinresponsiblePersonModify.gridx = 0;
		gbc_lblstandinresponsiblePersonModify.gridy = 10;
		panelmodify.add(lblstandinresponsiblePersonModify, gbc_lblstandinresponsiblePersonModify);

		JComboBox comboBoxStandinResponsiblePersonModify = new JComboBox();
		comboBoxStandinResponsiblePersonModify.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_comboBoxStandinResponsiblePersonModify = new GridBagConstraints();
		gbc_comboBoxStandinResponsiblePersonModify.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxStandinResponsiblePersonModify.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxStandinResponsiblePersonModify.gridx = 2;
		gbc_comboBoxStandinResponsiblePersonModify.gridy = 10;
		panelmodify.add(comboBoxStandinResponsiblePersonModify, gbc_comboBoxStandinResponsiblePersonModify);
		try {
			for(int i = 0; i<ProductionManagement.getSecondaryAdvisorArray().length; i++) {
				comboBoxStandinResponsiblePersonModify.addItem(ProductionManagement.getSecondaryAdvisorArray()[i]);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JLabel lblmodifyOrderStatus = new JLabel("Auftragsstatus");
		lblmodifyOrderStatus.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblmodifyOrderStatus = new GridBagConstraints();
		gbc_lblmodifyOrderStatus.gridwidth = 2;
		gbc_lblmodifyOrderStatus.anchor = GridBagConstraints.EAST;
		gbc_lblmodifyOrderStatus.insets = new Insets(0, 0, 5, 5);
		gbc_lblmodifyOrderStatus.gridx = 0;
		gbc_lblmodifyOrderStatus.gridy = 11;
		panelmodify.add(lblmodifyOrderStatus, gbc_lblmodifyOrderStatus);

		JComboBox comboBoxModifyStatus = new JComboBox();
		comboBoxModifyStatus.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBoxModifyStatus.setModel(new DefaultComboBoxModel(comboBoxStatusEntries.toArray()));
		GridBagConstraints gbc_comboBoxModifyStatus = new GridBagConstraints();
		gbc_comboBoxModifyStatus.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxModifyStatus.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxModifyStatus.gridx = 2;
		gbc_comboBoxModifyStatus.gridy = 11;
		panelmodify.add(comboBoxModifyStatus, gbc_comboBoxModifyStatus);

		JScrollPane scrollPanemodifyOrder = new JScrollPane();
		scrollPanemodifyOrder.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPanemodifyOrder.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_scrollPanemodifyOrder = new GridBagConstraints();
		gbc_scrollPanemodifyOrder.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPanemodifyOrder.gridwidth = 4;
		gbc_scrollPanemodifyOrder.fill = GridBagConstraints.BOTH;
		gbc_scrollPanemodifyOrder.gridx = 0;
		gbc_scrollPanemodifyOrder.gridy = 12;
		panelmodify.add(scrollPanemodifyOrder, gbc_scrollPanemodifyOrder);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPanemodifyOrder.setViewportView(TableDeleteOrder);
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		try {
			table.setModel(new OrderTableModel(ProductionManagement.getOrders()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPanemodifyOrder.setViewportView(table);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				handleEditOrderSelectionEvent(e);

			}
		});
		table.clearSelection();
		
		JComboBox comboBoxorderSearchModify = new JComboBox();
		comboBoxorderSearchModify.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBoxorderSearchModify.setToolTipText("Bitte Suchkategorie auswählen");
		comboBoxorderSearchModify.setModel(new DefaultComboBoxModel(new String[] { "Titel", "Auftragsart", "Status" }));
		GridBagConstraints gbc_comboBoxorderSearchModify = new GridBagConstraints();
		gbc_comboBoxorderSearchModify.gridwidth = 2;
		gbc_comboBoxorderSearchModify.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxorderSearchModify.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxorderSearchModify.gridx = 0;
		gbc_comboBoxorderSearchModify.gridy = 13;
		panelmodify.add(comboBoxorderSearchModify, gbc_comboBoxorderSearchModify);

		textFieldorderSearchModify = new JTextField();
		textFieldorderSearchModify.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_textFieldorderSearchModify = new GridBagConstraints();
		gbc_textFieldorderSearchModify.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldorderSearchModify.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldorderSearchModify.gridx = 2;
		gbc_textFieldorderSearchModify.gridy = 13;
		panelmodify.add(textFieldorderSearchModify, gbc_textFieldorderSearchModify);
		textFieldorderSearchModify.setColumns(10);

		JButton btnorderSearchModify = new JButton("Auftrag suchen");
		btnorderSearchModify.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnorderSearchModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String searchValue = textFieldorderSearchModify.getText();
				switch (comboBoxorderSearchModify.getSelectedIndex()) {
				case 0:
					try {
						table.setModel(new OrderTableModel(ProductionManagement.getOrderByTitle(searchValue)));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case 1:
					try {
						table.setModel(new OrderTableModel(ProductionManagement.getOrdersByType(searchValue)));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case 2:
					try {
						table.setModel(new OrderTableModel(ProductionManagement.getOrdersByStatus(searchValue)));
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				}
			}
		});

		GridBagConstraints gbc_btnorderSearchModify = new GridBagConstraints();
		gbc_btnorderSearchModify.insets = new Insets(0, 0, 5, 5);
		gbc_btnorderSearchModify.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnorderSearchModify.gridx = 2;
		gbc_btnorderSearchModify.gridy = 14;
		panelmodify.add(btnorderSearchModify, gbc_btnorderSearchModify);

		JButton btnsaveModifiedValues = new JButton("Änderungen speichern");
		btnsaveModifiedValues.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnsaveModifiedValues.addActionListener(new ActionListener() {

			//comboBoxresponsiblePersonModify
			//comboBoxstandinresponsiblePersonModify
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ProductionManagement.modifyOrder(Integer.parseInt(textFieldorderIDModify.getText()),
							textFieldorderTitelModify.getText(), comboBoxtypModify.getSelectedIndex(),
							Double.parseDouble(textFieldpredictedCostsModify.getText()),
							Double.parseDouble(textFieldactualCostsModify.getText()),
							ProductionManagement.getCustomerArray()[comboBoxorderCustomerModify.getSelectedIndex()].getId(), ProductionManagement.getAdvisorArray()[comboBoxResponsiblePersonModify.getSelectedIndex()].getId(),
							ProductionManagement.getSecondaryAdvisorArray()[comboBoxStandinResponsiblePersonModify.getSelectedIndex()].getId(), textFieldfileNameModify.getText(),
							textFieldfilePathModify.getText(), textFieldnoteOtherModify.getText());
					ProductionManagement.changeOrderStatus(Integer.parseInt(textFieldorderIDModify.getText()), comboBoxModifyStatus.getSelectedIndex());
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				refreshTable();
				refreshTableDeleteOrder();
			}
		});

		JButton btnclearSearchModify = new JButton("Suche aufheben");
		btnclearSearchModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					table.setModel(new OrderTableModel(ProductionManagement.getOrders()));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnclearSearchModify.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_btnclearSearchModify = new GridBagConstraints();
		gbc_btnclearSearchModify.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnclearSearchModify.insets = new Insets(0, 0, 5, 5);
		gbc_btnclearSearchModify.gridx = 2;
		gbc_btnclearSearchModify.gridy = 15;
		panelmodify.add(btnclearSearchModify, gbc_btnclearSearchModify);
		GridBagConstraints gbc_btnsaveModifiedValues = new GridBagConstraints();
		gbc_btnsaveModifiedValues.gridy = 16;
		gbc_btnsaveModifiedValues.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnsaveModifiedValues.insets = new Insets(0, 0, 0, 5);
		gbc_btnsaveModifiedValues.gridx = 2;

		panelmodify.add(btnsaveModifiedValues, gbc_btnsaveModifiedValues);
		panelmodify.add(btnsaveModifiedValues, gbc_btnsaveModifiedValues);


		JPanel paneldeleteOrder = new JPanel();
		tabbedPane.addTab("Auftrag löschen", null, paneldeleteOrder, null);
		GridBagLayout gbl_paneldeleteOrder = new GridBagLayout();
		gbl_paneldeleteOrder.columnWidths = new int[] { 0, 98, 0, 0 };
		gbl_paneldeleteOrder.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_paneldeleteOrder.columnWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_paneldeleteOrder.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		paneldeleteOrder.setLayout(gbl_paneldeleteOrder);

		JScrollPane scrollPanedeleteOrder = new JScrollPane();
		scrollPanedeleteOrder.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPanedeleteOrder.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_scrollPanedeleteOrder = new GridBagConstraints();
		gbc_scrollPanedeleteOrder.gridwidth = 3;
		gbc_scrollPanedeleteOrder.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPanedeleteOrder.fill = GridBagConstraints.BOTH;
		gbc_scrollPanedeleteOrder.gridx = 0;
		gbc_scrollPanedeleteOrder.gridy = 0;
		paneldeleteOrder.add(scrollPanedeleteOrder, gbc_scrollPanedeleteOrder);

		TableDeleteOrder = new JTable();
		TableDeleteOrder.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPanedeleteOrder.setViewportView(TableDeleteOrder);
		TableDeleteOrder.setFont(new Font("Tahoma", Font.PLAIN, 15));
		try {
			TableDeleteOrder.setModel(new OrderTableModel(ProductionManagement.getOrders()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		TableDeleteOrder.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPanedeleteOrder.setViewportView(TableDeleteOrder);
		TableDeleteOrder.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				handleEditOrderSelectionEvent(e);

			}
		});
		TableDeleteOrder.clearSelection();

		JComboBox comboBoxorderSearchDelete = new JComboBox();
		comboBoxorderSearchDelete.setFont(new Font("Tahoma", Font.PLAIN, 15));
		comboBoxorderSearchDelete.setModel(new DefaultComboBoxModel(new String[] { "Titel", "Auftragsart", "Status" }));
		GridBagConstraints gbc_comboBoxorderSearchDelete = new GridBagConstraints();
		gbc_comboBoxorderSearchDelete.gridwidth = 2;
		gbc_comboBoxorderSearchDelete.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxorderSearchDelete.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxorderSearchDelete.gridx = 0;
		gbc_comboBoxorderSearchDelete.gridy = 1;
		paneldeleteOrder.add(comboBoxorderSearchDelete, gbc_comboBoxorderSearchDelete);

		textFieldorderSearchDelete = new JTextField();
		textFieldorderSearchDelete.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_textFieldorderSearchDelete = new GridBagConstraints();
		gbc_textFieldorderSearchDelete.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldorderSearchDelete.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldorderSearchDelete.gridx = 2;
		gbc_textFieldorderSearchDelete.gridy = 1;
		paneldeleteOrder.add(textFieldorderSearchDelete, gbc_textFieldorderSearchDelete);
		textFieldorderSearchDelete.setColumns(10);

		JButton btnorderSearchDelete = new JButton("Suchen");
		btnorderSearchDelete.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnorderSearchDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String searchValue = textFieldorderSearchDelete.getText();
				switch (comboBoxorderSearchDelete.getSelectedIndex()) {
				case 0:
					try {
						TableDeleteOrder.setModel(new OrderTableModel(ProductionManagement.getOrderByTitle(searchValue)));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case 1:
					try {
						TableDeleteOrder.setModel(new OrderTableModel(ProductionManagement.getOrdersByType(searchValue)));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case 2:
					try {
						TableDeleteOrder.setModel(new OrderTableModel(ProductionManagement.getOrdersByStatus(searchValue)));
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				}
			}
		});
		GridBagConstraints gbc_btnorderSearchDelete = new GridBagConstraints();
		gbc_btnorderSearchDelete.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnorderSearchDelete.insets = new Insets(0, 0, 5, 0);
		gbc_btnorderSearchDelete.gridx = 2;
		gbc_btnorderSearchDelete.gridy = 2;
		paneldeleteOrder.add(btnorderSearchDelete, gbc_btnorderSearchDelete);

		JButton btndeleteOrder = new JButton("Auftrag löschen");
		btndeleteOrder.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_btndeleteOrder = new GridBagConstraints();
		btndeleteOrder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ProductionManagement.deleteOrder(Integer
							.parseInt(TableDeleteOrder.getValueAt(TableDeleteOrder.getSelectedRow(), 0).toString()));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				refreshTableDeleteOrder();
				refreshTable();
			}
		});

		JButton btnclearSearchDelete = new JButton("Suche aufheben");
		btnclearSearchDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TableDeleteOrder.setModel(new OrderTableModel(ProductionManagement.getOrders()));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btnclearSearchDelete.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_btnclearSearchDelete = new GridBagConstraints();
		gbc_btnclearSearchDelete.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnclearSearchDelete.insets = new Insets(0, 0, 5, 0);
		gbc_btnclearSearchDelete.gridx = 2;
		gbc_btnclearSearchDelete.gridy = 3;
		paneldeleteOrder.add(btnclearSearchDelete, gbc_btnclearSearchDelete);
		gbc_btndeleteOrder.fill = GridBagConstraints.HORIZONTAL;
		gbc_btndeleteOrder.gridx = 2;
		gbc_btndeleteOrder.gridy = 4;
		paneldeleteOrder.add(btndeleteOrder, gbc_btndeleteOrder);

		JMenuBar menuBar = new JMenuBar();
		frmElabVerwaltungsprogramm.setJMenuBar(menuBar);

		JMenu mnNewMenuOptions = new JMenu("Menü");
		mnNewMenuOptions.setFont(new Font("Segoe UI", Font.BOLD, 15));
		menuBar.add(mnNewMenuOptions);
		
		JMenuItem mntmNewMenuItemMinimize = new JMenuItem("Minimieren");
		mntmNewMenuItemMinimize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			frmElabVerwaltungsprogramm.setState(JFrame.ICONIFIED);
			
			}
		});
		mntmNewMenuItemMinimize.setFont(new Font("Segoe UI", Font.BOLD, 15));
		mnNewMenuOptions.add(mntmNewMenuItemMinimize);

		JMenuItem mntmNewMenuItembacktoMain = new JMenuItem("Hauptmenü");
		mntmNewMenuItembacktoMain.setFont(new Font("Segoe UI", Font.BOLD, 15));
		mnNewMenuOptions.add(mntmNewMenuItembacktoMain);
		mntmNewMenuItembacktoMain.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GuiMenue gui = new GuiMenue();
				frmElabVerwaltungsprogramm.dispose();
			}
		});

		JMenuItem mntmNewMenuItemlogOut = new JMenuItem("Ausloggen");
		mntmNewMenuItemlogOut.setFont(new Font("Segoe UI", Font.BOLD, 15));
		mnNewMenuOptions.add(mntmNewMenuItemlogOut);
		mntmNewMenuItemlogOut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GuiLogin gui = new GuiLogin();
				frmElabVerwaltungsprogramm.dispose();
			}
		});

		JMenuItem mntmNewMenuItemcloseapplication = new JMenuItem("Anwendung verlassen");
		mntmNewMenuItemcloseapplication.setFont(new Font("Segoe UI", Font.BOLD, 15));
		mnNewMenuOptions.add(mntmNewMenuItemcloseapplication);
		mntmNewMenuItemcloseapplication.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		JMenu mnNewMenuhelpWindow = new JMenu("?");
		mnNewMenuhelpWindow.setFont(new Font("Segoe UI", Font.BOLD, 15));

		menuBar.add(mnNewMenuhelpWindow);

		JMenuItem mntmNewMenuItemshowManual = new JMenuItem("Anleitung anzeigen");
		mntmNewMenuItemshowManual.setFont(new Font("Segoe UI", Font.BOLD, 15));
		mntmNewMenuItemshowManual.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// ToDo
			}
		});
		mnNewMenuhelpWindow.add(mntmNewMenuItemshowManual);

		frmElabVerwaltungsprogramm.setVisible(true);
	}
	
	protected void refreshCustomerCombobox() {
		comboBoxorderCustomer.removeAllItems();
		comboBoxorderCustomerModify.removeAllItems();
		// TODO Auto-generated method stub
		try {
			for(int i = 0; i<ProductionManagement.getCustomerArray().length; i++) {
				comboBoxorderCustomer.addItem(ProductionManagement.getCustomerArray()[i]);
				comboBoxorderCustomerModify.addItem(ProductionManagement.getCustomerArray()[i]);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	    

	}
	
	protected void refreshAdvisorCombobox() {
		comboBoxResponsible.removeAllItems();
		comboBoxResponsiblePersonModify.removeAllItems();
		// TODO Auto-generated method stub
		try {
			for(int i = 0; i<ProductionManagement.getAdvisorArray().length; i++) {
				comboBoxResponsible.addItem(ProductionManagement.getAdvisorArray()[i]);
				comboBoxResponsiblePersonModify.addItem(ProductionManagement.getAdvisorArray()[i]);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	    

	}
	
	protected void refreshSecondaryAdvisorCombobox() {
		comboBoxStandinResponsible.removeAllItems();
		comboBoxStandinResponsiblePersonModify.removeAllItems();
		// TODO Auto-generated method stub
		try {
			for(int i = 0; i<ProductionManagement.getSecondaryAdvisorArray().length; i++) {
				comboBoxStandinResponsible.addItem(ProductionManagement.getSecondaryAdvisorArray()[i]);
				comboBoxStandinResponsiblePersonModify.addItem(ProductionManagement.getSecondaryAdvisorArray()[i]);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	    

	}

	protected int getIdAdvisor(int selectedIndex) {
		// TODO Auto-generated method stub
		try {
			return ProductionManagement.getAdvisorArray()[selectedIndex].getId();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return 0;
	}

	protected int getIdSecondaryAdvisor(int selectedIndex) {
		// TODO Auto-generated method stub
		try {
			return ProductionManagement.getSecondaryAdvisorArray()[selectedIndex].getId();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return 0;
	}

	protected int getIdCustomer(int selectedIndex) {
		// TODO Auto-generated method stub
		try {
			return ProductionManagement.getCustomerArray()[selectedIndex].getId();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return 0;
	}

	protected void handleEditOrderSelectionEvent(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		if (table.getSelectedRow() > -1) {
			textFieldorderIDModify.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
			textFieldorderTitelModify.setText((String) table.getValueAt(table.getSelectedRow(), 1));
			comboBoxtypModify.setSelectedIndex(comboBoxEntries.indexOf((String) table.getValueAt(table.getSelectedRow(), 2)));
			textFieldnoteOtherModify.setText((String) table.getValueAt(table.getSelectedRow(), 10));
			textFieldfilePathModify.setText((String) table.getValueAt(table.getSelectedRow(), 9));
			textFieldfileNameModify.setText((String) table.getValueAt(table.getSelectedRow(), 8));
			textFieldpredictedCostsModify.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
			textFieldactualCostsModify.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
			comboBoxModifyStatus.setSelectedIndex(comboBoxStatusEntries.indexOf((String) table.getValueAt(table.getSelectedRow(), 11)));
			comboBoxorderCustomer.setSelectedIndex(findCorrectIndexFromCustomer(table.getValueAt(table.getSelectedRow(), 5).toString()));
			comboBoxResponsible.setSelectedIndex(findCorrectIndexFromAdvisor(table.getValueAt(table.getSelectedRow(), 6).toString()));
			comboBoxStandinResponsiblePersonModify.setSelectedIndex(findCorrectIndexFromSecondaryAdvisor(table.getValueAt(table.getSelectedRow(), 7).toString()));
		}

	}
	
	private int findCorrectIndexFromCustomer(String value) {
		// TODO Auto-generated method stub
		int x = Integer.parseInt(value);

		try {
			ComboBoxPerson [] array = ProductionManagement.getCustomerArray();
			for(int i = 0; i<array.length; i++) {
				if(array[i].getId() == x) {
					return i; 
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return -1;
	}
	
	private int findCorrectIndexFromAdvisor(String value) {
		// TODO Auto-generated method stub
		int x = Integer.parseInt(value);

		try {
			ComboBoxPerson [] array = ProductionManagement.getAdvisorArray();
			for(int i = 0; i<array.length; i++) {
				if(array[i].getId() == x) {
					return i; 
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return -1;
	}
	
	private int findCorrectIndexFromSecondaryAdvisor(String value) {
		// TODO Auto-generated method stub
		int x = Integer.parseInt(value);

		try {
			ComboBoxPerson [] array = ProductionManagement.getSecondaryAdvisorArray();
			for(int i = 0; i<array.length; i++) {
				if(array[i].getId() == x) {
					return i; 
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return -1;
	}

	protected void refreshTable() {
		try {
			table.setModel(new OrderTableModel(ProductionManagement.getOrders()));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		table.clearSelection();
	}

	protected void refreshTableDeleteOrder() {
		try {
			TableDeleteOrder.setModel(new OrderTableModel(ProductionManagement.getOrders()));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		TableDeleteOrder.clearSelection();
	}

}
