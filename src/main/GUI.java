package main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.itextpdf.text.DocumentException;

public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private ContentWriter cw;
	private TemplateWriter tw;
	private PDFWriter pw;
	private Searcher s;
	
	private JPanel contentPane;
	private JPanel panel_3;
	private JPanel panel_5;
	private int headingYPos = 0;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextArea textArea;
	private JTextField textField_5;
	private JTextField textField_6;
	private JPanel panel_16;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		JPanel MainMenu = new JPanel();
		contentPane.add(MainMenu, "MainMenu");
		MainMenu.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_17 = new JPanel();
		MainMenu.add(panel_17);
		GridBagLayout gbl_panel_17 = new GridBagLayout();
		gbl_panel_17.columnWidths = new int[]{0};
		gbl_panel_17.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel_17.columnWeights = new double[]{0.0};
		gbl_panel_17.rowWeights = new double[]{0.0, 0.0};
		panel_17.setLayout(gbl_panel_17);
		
		JButton btnCreateDocument = new JButton("Create Document");
		GridBagConstraints gbc_btnCreateDocument = new GridBagConstraints();
		gbc_btnCreateDocument.fill = GridBagConstraints.HORIZONTAL;;
		gbc_btnCreateDocument.insets = new Insets(0, 0, 0, 5);
		gbc_btnCreateDocument.gridx = 0;
		gbc_btnCreateDocument.gridy = 0;
		panel_17.add(btnCreateDocument, gbc_btnCreateDocument);
		btnCreateDocument.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout cl = (CardLayout)(contentPane.getLayout());
				cl.show(contentPane, "CreateDocument_1");
			}
		});
		
		JButton btnCreateTemplate = new JButton("Create Template");
		GridBagConstraints gbc_btnCreateTemplate = new GridBagConstraints();
		gbc_btnCreateTemplate.fill = GridBagConstraints.HORIZONTAL;;
		gbc_btnCreateTemplate.insets = new Insets(0, 0, 0, 5);
		gbc_btnCreateTemplate.gridx = 0;
		gbc_btnCreateTemplate.gridy = 1;
		panel_17.add(btnCreateTemplate, gbc_btnCreateTemplate);
		btnCreateTemplate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout cl = (CardLayout)(contentPane.getLayout());
				cl.show(contentPane, "CreateTemplate");
			}
		});
		
		JButton btnSearch_1 = new JButton("Search");
		btnSearch_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout cl = (CardLayout)(contentPane.getLayout());
				cl.show(contentPane, "SearchPanel");
			}
		});
		GridBagConstraints gbc_btnSearch_1 = new GridBagConstraints();
		gbc_btnSearch_1.fill = GridBagConstraints.HORIZONTAL;;
		gbc_btnSearch_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnSearch_1.gridx = 0;
		gbc_btnSearch_1.gridy = 2;
		panel_17.add(btnSearch_1, gbc_btnSearch_1);
		
		JButton btnHelp = new JButton("Help");
		GridBagConstraints gbc_btnHelp = new GridBagConstraints();
		gbc_btnHelp.fill = GridBagConstraints.HORIZONTAL;;
		gbc_btnHelp.insets = new Insets(0, 0, 0, 5);
		gbc_btnHelp.gridx = 0;
		gbc_btnHelp.gridy = 3;
		panel_17.add(btnHelp, gbc_btnHelp);
		btnHelp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				createHelpDialog("Choose Create Document to move to the document creation menu where you can create a new resume with a template of your choice.\n" +
						"\n" +
						"Choose Create Template to move to the template creation menu where you can choose headings to add to a template.\n" + 
						"\n" +
						"Choose Exit to close the program entirely.  Be aware that anything unsaved will be lost.\n" + 
						"\n" +
						"Choose Help to display this text again.");
			}
		});
		JButton btnExit = new JButton("Exit");
		GridBagConstraints gbc_btnExit = new GridBagConstraints();
		gbc_btnExit.fill = GridBagConstraints.HORIZONTAL;;
		gbc_btnExit.insets = new Insets(0, 0, 0, 5);
		gbc_btnExit.gridx = 0;
		gbc_btnExit.gridy = 4;
		panel_17.add(btnExit, gbc_btnExit);
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		
		JPanel CreateDocument_1 = new JPanel();
		contentPane.add(CreateDocument_1, "CreateDocument_1");
		CreateDocument_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		CreateDocument_1.add(panel, BorderLayout.SOUTH);
		
		JButton btnBack = new JButton("Back");
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout cl = (CardLayout)(contentPane.getLayout());
				cl.show(contentPane, "MainMenu");
			}
		});
		panel.add(btnBack);
		
		JButton btnHelp_1 = new JButton("Help");
		btnHelp_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				createHelpDialog("Click the Select Template button to browse to the template you wish to use.  NOTE: Templates MUST be HTML files or they will not be recognized by the program.\n" + 
						"\n" + 
						"Click the select Directory button to choose the location where your file will be saved.\n" + 
						"\n" + 
						"Use the text box to name the file that your resume will be saved as. Use only letters and numbers in your file name to guarantee that your file will save correctly.\n" + 
						"\n" + 
						"Click Back to return to the previous menu.  Click Continue when you've filled out this section of the program.  Click Help to show this text again.");
			}
		});
		panel.add(btnHelp_1);
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if (textField.getText().equals("")) {
						createMessageDialog("Please select a file.");
					} else {
						if (textField_3.getText().equals("")) {
							createMessageDialog("No output file name provided, defaulting to " + StringUtil.prependOutput(textField.getText()));
							try {
								cw = new ContentWriter(textField.getText());
							} catch (IOException e1) {
								e1.printStackTrace();
							} catch (InvalidFileException e1) {
								e1.printStackTrace();
							}
						} else {
							try {
								String fileSeparator = System.getProperty("file.separator");
								cw = new ContentWriter(textField.getText(), textField_4.getText() + fileSeparator + textField_3.getText());
								cw.checkNonuniqueHeadings();
							} catch (IOException e1) {
								e1.printStackTrace();
							} catch (InvalidFileException e1) {
								e1.printStackTrace();
							}
						}
						try {
							generateHeadings(panel_3, cw.getHeadingsArray());
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						CardLayout cl = (CardLayout)(contentPane.getLayout());
						cl.show(contentPane, "CreateDocument_2");
					}
				} catch (NonuniqueHeadingException e1) {
					createMessageDialog("Headings in template must be unique.");
				}
			}
		});
		panel.add(btnContinue);
		
		JPanel panel_1 = new JPanel();
		CreateDocument_1.add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{112, 109, 86, 0};
		gbl_panel_1.rowHeights = new int[]{23, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JButton btnSelectTemplate = new JButton("Select Template");
		btnSelectTemplate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String filePath = createFileChooser(false);
				textField.setText(filePath);
			}
		});
		GridBagConstraints gbc_btnSelectTemplate = new GridBagConstraints();
		gbc_btnSelectTemplate.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnSelectTemplate.insets = new Insets(0, 0, 5, 5);
		gbc_btnSelectTemplate.gridx = 1;
		gbc_btnSelectTemplate.gridy = 0;
		panel_1.add(btnSelectTemplate, gbc_btnSelectTemplate);
		
		textField = new JTextField();
		textField.setEditable(false);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 0;
		panel_1.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JButton btnSelectDirectory_1 = new JButton("Select Directory:");
		btnSelectDirectory_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String filePath = createFileChooser(true);
				textField_4.setText(filePath);
			}
		});
		GridBagConstraints gbc_btnSelectDirectory_1 = new GridBagConstraints();
		gbc_btnSelectDirectory_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnSelectDirectory_1.gridx = 1;
		gbc_btnSelectDirectory_1.gridy = 1;
		panel_1.add(btnSelectDirectory_1, gbc_btnSelectDirectory_1);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.insets = new Insets(0, 0, 5, 0);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 2;
		gbc_textField_4.gridy = 1;
		panel_1.add(textField_4, gbc_textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNameOutputFile = new JLabel("Name Output File:");
		GridBagConstraints gbc_lblNameOutputFile = new GridBagConstraints();
		gbc_lblNameOutputFile.anchor = GridBagConstraints.EAST;
		gbc_lblNameOutputFile.insets = new Insets(0, 0, 0, 5);
		gbc_lblNameOutputFile.gridx = 1;
		gbc_lblNameOutputFile.gridy = 2;
		panel_1.add(lblNameOutputFile, gbc_lblNameOutputFile);
		
		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 2;
		gbc_textField_3.gridy = 2;
		panel_1.add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);
		
		JPanel CreateDocument_2 = new JPanel();
		contentPane.add(CreateDocument_2, "CreateDocument_2");
		CreateDocument_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		CreateDocument_2.add(panel_2, BorderLayout.SOUTH);
		
		JButton btnBack_1 = new JButton("Back");
		btnBack_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout cl = (CardLayout)(contentPane.getLayout());
				cl.show(contentPane, "CreateDocument_1");
			}
		});
		panel_2.add(btnBack_1);
		
		JButton btnHelp_2 = new JButton("Help");
		btnHelp_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				createHelpDialog("On this screen, the headings will be displayed in the scrollable panel along with a line where you can enter the text that should appear under the heading.\n" + 
						"\n" + 
						"Click Create to generate a PDF version of your resume.  Click Back to go back to the previous menu.  Click Help to display this text again.");
			}
		});
		panel_2.add(btnHelp_2);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					cw.writeInitialContent(textArea.getText());
					writeAllContent();
					pw = new PDFWriter(cw.getOutputFilename(), StringUtil.replaceExtension(cw.getOutputFilename(), "pdf"));
					pw.writePDF();
					clearCreateDocument();
					createMessageDialog("Created file " + pw.getOutputFilename());
					CardLayout cl = (CardLayout)(contentPane.getLayout());
					cl.show(contentPane, "MainMenu");
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (InvalidFileException e1) {
					e1.printStackTrace();
				} catch (DocumentException e1) {
					e1.printStackTrace();
				}
			}
		});
		panel_2.add(btnCreate);
		
		panel_3 = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane(panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{0};
		gbl_panel_3.rowHeights = new int[]{0};
		gbl_panel_3.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		CreateDocument_2.add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel_10 = new JPanel();
		CreateDocument_2.add(panel_10, BorderLayout.NORTH);
		GridBagLayout gbl_panel_10 = new GridBagLayout();
		gbl_panel_10.columnWidths = new int[]{171, 68, 4, 1, 0};
		gbl_panel_10.rowHeights = new int[]{22, 0};
		gbl_panel_10.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_10.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_10.setLayout(gbl_panel_10);
		
		JLabel lblInitialContent = new JLabel("Initial Content");
		GridBagConstraints gbc_lblInitialContent = new GridBagConstraints();
		gbc_lblInitialContent.anchor = GridBagConstraints.WEST;
		gbc_lblInitialContent.insets = new Insets(0, 0, 0, 5);
		gbc_lblInitialContent.gridx = 1;
		gbc_lblInitialContent.gridy = 0;
		panel_10.add(lblInitialContent, gbc_lblInitialContent);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.anchor = GridBagConstraints.NORTHWEST;
		gbc_textArea.insets = new Insets(0, 0, 0, 5);
		gbc_textArea.gridx = 1;
		gbc_textArea.gridy = 1;
		panel_10.add(textArea, gbc_textArea);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.anchor = GridBagConstraints.WEST;
		gbc_separator.gridx = 1;
		gbc_separator.gridy = 2;
		panel_10.add(separator, gbc_separator);
		
		JPanel CreateTemplate = new JPanel();
		contentPane.add(CreateTemplate, "CreateTemplate");
		CreateTemplate.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		CreateTemplate.add(panel_4, BorderLayout.SOUTH);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{0};
		gbl_panel_4.rowHeights = new int[]{25, 0, 0, 0};
		gbl_panel_4.columnWeights = new double[]{1.0};
		gbl_panel_4.rowWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		panel_4.setLayout(gbl_panel_4);
		
		JPanel panel_6 = new JPanel();
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.insets = new Insets(0, 0, 5, 0);
		gbc_panel_6.fill = GridBagConstraints.BOTH;
		gbc_panel_6.gridx = 0;
		gbc_panel_6.gridy = 0;
		panel_4.add(panel_6, gbc_panel_6);
		
		JButton btnAddHeading = new JButton("Add Heading");
		btnAddHeading.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addHeading(panel_5);
			}
		});
		panel_6.add(btnAddHeading);
		
		JPanel panel_7 = new JPanel();
		GridBagConstraints gbc_panel_7 = new GridBagConstraints();
		gbc_panel_7.insets = new Insets(0, 0, 5, 0);
		gbc_panel_7.fill = GridBagConstraints.BOTH;
		gbc_panel_7.gridx = 0;
		gbc_panel_7.gridy = 3;
		panel_4.add(panel_7, gbc_panel_7);
		panel_7.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnBack_2 = new JButton("Back");
		btnBack_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout cl = (CardLayout)(contentPane.getLayout());
				cl.show(contentPane, "MainMenu");
			}
		});
		panel_7.add(btnBack_2);
		
		JButton btnHelp_3 = new JButton("Help");
		btnHelp_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				createHelpDialog("Click the add heading button to add a blank line to the main panel.  You can name your heading in this line.  Make sure every heading is unique!\n" + 
						"\n" + 
						"Click the Select Directory button to choose the location where your file will be saved to.\n" + 
						"\n" + 
						"Use the Name Template box to name the template. Please use only letters and numbers in your template name to guarantee that your file will save correctly\n" + 
						"\n" + 
						"Click Create to create the template with the desired fields.  Click Back to return to the previous menu.  Click Help to display this text again.");
			}
		});
		panel_7.add(btnHelp_3);
		
		JButton btnCreate_1 = new JButton("Create");
		btnCreate_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String fileSeparator = System.getProperty("file.separator");
				tw = new TemplateWriter(textField_2.getText() + fileSeparator + StringUtil.replaceExtension(textField_1.getText(), "html"));
				appendAllTemplateContent();
				try {
					tw.writeTemplateContent();
					clearCreateTemplate();
					createMessageDialog("Created file " + tw.getTemplateFilename());
					CardLayout cl = (CardLayout)(contentPane.getLayout());
					cl.show(contentPane, "MainMenu");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		panel_7.add(btnCreate_1);
		
		JPanel panel_8 = new JPanel();
		GridBagConstraints gbc_panel_8 = new GridBagConstraints();
		gbc_panel_8.fill = GridBagConstraints.BOTH;
		gbc_panel_8.gridx = 0;
		gbc_panel_8.gridy = 2;
		panel_4.add(panel_8, gbc_panel_8);
		
		JLabel lblNameTemplate = new JLabel("Name Template:");
		panel_8.add(lblNameTemplate);
		
		textField_1 = new JTextField();
		panel_8.add(textField_1);
		textField_1.setColumns(10);
		
		JPanel panel_9 = new JPanel();
		GridBagConstraints gbc_panel_9 = new GridBagConstraints();
		gbc_panel_9.insets = new Insets(0, 0, 5, 0);
		gbc_panel_9.fill = GridBagConstraints.BOTH;
		gbc_panel_9.gridx = 0;
		gbc_panel_9.gridy = 1;
		panel_4.add(panel_9, gbc_panel_9);
		
		JButton btnSelectDirectory = new JButton("Select Directory");
		btnSelectDirectory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String filePath = createFileChooser(true);
				textField_2.setText(filePath);
			}
		});
		panel_9.add(btnSelectDirectory);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		panel_9.add(textField_2);
		textField_2.setColumns(10);
		
		panel_5 = new JPanel();
		
		JScrollPane scrollPane_1 = new JScrollPane(panel_5);
		GridBagLayout gbl_panel_5 = new GridBagLayout();
		gbl_panel_5.columnWidths = new int[]{0};
		gbl_panel_5.rowHeights = new int[]{0};
		gbl_panel_5.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_panel_5.rowWeights = new double[]{Double.MIN_VALUE};
		panel_5.setLayout(gbl_panel_5);
		CreateTemplate.add(scrollPane_1, BorderLayout.CENTER);
		
		JPanel SearchPanel = new JPanel();
		contentPane.add(SearchPanel, "SearchPanel");
		SearchPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_11 = new JPanel();
		SearchPanel.add(panel_11, BorderLayout.SOUTH);
		
		JButton btnBack_3 = new JButton("Back");
		btnBack_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clearSearch();
				CardLayout cl = (CardLayout)(contentPane.getLayout());
				cl.show(contentPane, "MainMenu");
			}
		});
		panel_11.add(btnBack_3);
		
		JButton btnHelp_4 = new JButton("Help");
		btnHelp_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//TODO: help
				createHelpDialog("Click the Select Directory button to choose the directory where the search will be performed.\n" + 
						"\n" + 
						"Enter your query in the Query field.\n" + 
						"\n" + 
						"Click Search to perform a search for the given query in the selected directory. Click Back to go back to the previous menu. Click Help to display this text again.");
			}
		});
		panel_11.add(btnHelp_4);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!(textField_5.equals(""))) {
					s = new Searcher(textField_5.getText());
					try {
						clearSearchResults();
						ArrayList<String> results = s.search(textField_6.getText());
						appendSearchResults(panel_16, results);
					} catch (IOException e) {
					}
				} else {
					createMessageDialog("Please select a directory.");
				}
			}
		});
		panel_11.add(btnSearch);
		
		JPanel panel_12 = new JPanel();
		SearchPanel.add(panel_12, BorderLayout.NORTH);
		GridBagLayout gbl_panel_12 = new GridBagLayout();
		gbl_panel_12.columnWidths = new int[]{212, 212, 0};
		gbl_panel_12.rowHeights = new int[]{23, 23, 23, 0};
		gbl_panel_12.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_12.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_12.setLayout(gbl_panel_12);
		
		JButton btnSelectDirectory_2 = new JButton("Select Directory");
		btnSelectDirectory_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String filePath = createFileChooser(true);
				textField_5.setText(filePath);
			}
		});
		GridBagConstraints gbc_btnSelectDirectory_2 = new GridBagConstraints();
		gbc_btnSelectDirectory_2.fill = GridBagConstraints.BOTH;
		gbc_btnSelectDirectory_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnSelectDirectory_2.gridx = 0;
		gbc_btnSelectDirectory_2.gridy = 0;
		panel_12.add(btnSelectDirectory_2, gbc_btnSelectDirectory_2);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.fill = GridBagConstraints.BOTH;
		gbc_textField_5.insets = new Insets(0, 0, 5, 0);
		gbc_textField_5.gridx = 1;
		gbc_textField_5.gridy = 0;
		panel_12.add(textField_5, gbc_textField_5);
		textField_5.setColumns(10);
		
		JLabel lblQuery = new JLabel("Query");
		lblQuery.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblQuery = new GridBagConstraints();
		gbc_lblQuery.fill = GridBagConstraints.BOTH;
		gbc_lblQuery.insets = new Insets(0, 0, 5, 5);
		gbc_lblQuery.gridx = 0;
		gbc_lblQuery.gridy = 1;
		panel_12.add(lblQuery, gbc_lblQuery);
		
		textField_6 = new JTextField();
		GridBagConstraints gbc_textField_6 = new GridBagConstraints();
		gbc_textField_6.fill = GridBagConstraints.BOTH;
		gbc_textField_6.insets = new Insets(0, 0, 5, 0);
		gbc_textField_6.gridx = 1;
		gbc_textField_6.gridy = 1;
		panel_12.add(textField_6, gbc_textField_6);
		textField_6.setColumns(10);
		
		JPanel panel_13 = new JPanel();
		SearchPanel.add(panel_13, BorderLayout.CENTER);
		panel_13.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_14 = new JPanel();
		panel_13.add(panel_14, BorderLayout.NORTH);
		
		JLabel lblResults = new JLabel("Results:");
		panel_14.add(lblResults);
		
		JPanel panel_15 = new JPanel();
		panel_13.add(panel_15, BorderLayout.CENTER);
		panel_15.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		panel_15.add(scrollPane_2);
		
		panel_16 = new JPanel();
		scrollPane_2.setViewportView(panel_16);
		GridBagLayout gbl_panel_16 = new GridBagLayout();
		gbl_panel_16.columnWidths = new int[]{0};
		gbl_panel_16.rowHeights = new int[]{0};
		gbl_panel_16.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_panel_16.rowWeights = new double[]{Double.MIN_VALUE};
		panel_16.setLayout(gbl_panel_16);
	}
	
	private String createFileChooser(boolean directoriesOnly) {
		JFileChooser fileChooser;
		if (directoriesOnly) {
			fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		} else {
			fileChooser = new JFileChooser();
		}
		fileChooser.showOpenDialog(this);
		return fileChooser.getSelectedFile().getAbsolutePath();
	}
	
	private void createMessageDialog(String message) {
		JOptionPane.showMessageDialog(this, message);
	}
	
	private void createHelpDialog(String help) {
		StringBuilder sb = new StringBuilder();
		sb.append("<html><body>");
		String[] split = help.split("\n");
		for (String s : split) {
			sb.append("<p style='width: 300px;'>");
			sb.append(s);
			sb.append("</p>");
		}
		sb.append("</body></html>");
		String html = sb.toString();
		JOptionPane.showMessageDialog(this, html, "Help", JOptionPane.PLAIN_MESSAGE);
	}
	
	private void generateHeadings(JPanel panel, String[] headings) {
		int gridy = 0;
		for(String s : headings) {
			JLabel label = new JLabel(s);
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.insets = new Insets(0, 0, 5, 0);
			gbc_label.gridx = 0;
			gbc_label.gridy = gridy;
			panel.add(label, gbc_label);
			
			JTextArea textArea = new JTextArea();
			GridBagConstraints gbc_textArea = new GridBagConstraints();
			gbc_textArea.insets = new Insets(0, 0, 5, 0);
			gbc_textArea.fill = GridBagConstraints.BOTH;
			gbc_textArea.gridx = 0;
			gbc_textArea.gridy = gridy + 1;
			panel.add(textArea, gbc_textArea);
			
			JSeparator separator = new JSeparator();
			GridBagConstraints gbc_separator = new GridBagConstraints();
			gbc_separator.fill = GridBagConstraints.HORIZONTAL;
			gbc_separator.insets = new Insets(0, 0, 5, 0);
			gbc_separator.gridx = 0;
			gbc_separator.gridy = gridy + 2;
			panel.add(separator, gbc_separator);
			gridy += 3;
			
			panel.revalidate();
			validate();
		}
	}
	
	private void addHeading(JPanel panel) {
		JTextField textField = new JTextField();
		textField.setText("");
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = headingYPos;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = headingYPos + 1;
		panel.add(separator, gbc_separator);
		headingYPos += 2;
		
		panel.revalidate();
		validate();
	}
	
	private void writeAllContent() {
		Component[] c = panel_3.getComponents();
		for (int i = 0; i < c.length; i++) {
			String heading = ((JLabel) c[i]).getText();
			String content = ((JTextArea) c[i + 1]).getText();
			try {
				cw.writeContent(heading, content);
			} catch (IOException e) {
				e.printStackTrace();
			}
			i += 2;
		}
	}
	
	private void appendAllTemplateContent() {
		Component[] c = panel_5.getComponents();
		for (int i = 0; i < c.length; i++) {
			String heading = ((JTextField) c[i]).getText();
			try {
				tw.appendTemplateContent(heading);
			} catch (NonuniqueHeadingException e) {
				e.printStackTrace();
			}
			i++;
		}
	}
	
	private void appendSearchResults(JPanel panel, ArrayList<String> results) {
		int gridy = 0;
		for (String s : results) {
			JLabel label = new JLabel(s);
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.insets = new Insets(0, 0, 5, 0);
			gbc_label.gridx = 0;
			gbc_label.gridy = gridy;
			panel.add(label, gbc_label);
			gridy++;
			
			panel.revalidate();
			validate();
		}
	}
	
	private void clearCreateDocument() {
		textField.setText("");
		textField_4.setText("");
		textField_3.setText("");
		panel_3.removeAll();
		textArea.setText("");
	}
	
	private void clearCreateTemplate() {
		panel_5.removeAll();
		panel_5.revalidate();
		panel_5.repaint();
		validate();
		textField_1.setText("");
		textField_2.setText("");
		headingYPos = 0;
	}
	
	private void clearSearch() {
		textField_5.setText("");
		textField_6.setText("");
		clearSearchResults();
		validate();
	}
	
	private void clearSearchResults() {
		panel_16.removeAll();
		panel_16.revalidate();
		validate();
	}
	
}
