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
import javax.swing.border.EmptyBorder;

import com.itextpdf.text.DocumentException;

public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private ContentWriter cw;
	private TemplateWriter tw;
	private PDFWriter pw;
	
	private JPanel contentPane;
	private JPanel panel_3;
	private JPanel panel_5;
	private int headingYPos = 0;
	private JTextField textField;
	private JTextField textField_1; //TODO: reset these after the final button in sequence has been pressed
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextArea textArea;

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
					e.printStackTrace();
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
		GridBagLayout gbl_MainMenu = new GridBagLayout();
		gbl_MainMenu.columnWidths = new int[]{0, 0};
		gbl_MainMenu.rowHeights = new int[] {0, 0, 0, 0, 0, 0};
		gbl_MainMenu.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_MainMenu.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		MainMenu.setLayout(gbl_MainMenu);
		
		JButton btnCreateDocument = new JButton("Create Document");
		btnCreateDocument.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout cl = (CardLayout)(contentPane.getLayout());
				cl.show(contentPane, "CreateDocument_1");
			}
		});
		GridBagConstraints gbc_btnCreateDocument = new GridBagConstraints();
		gbc_btnCreateDocument.anchor = GridBagConstraints.CENTER;
		gbc_btnCreateDocument.gridwidth = GridBagConstraints.REMAINDER;
		gbc_btnCreateDocument.insets = new Insets(0, 0, 5, 0);
		gbc_btnCreateDocument.gridx = 0;
		gbc_btnCreateDocument.gridy = 0;
		MainMenu.add(btnCreateDocument, gbc_btnCreateDocument);
		
		JButton btnCreateTemplate = new JButton("Create Template");
		btnCreateTemplate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout cl = (CardLayout)(contentPane.getLayout());
				cl.show(contentPane, "CreateTemplate");
			}
		});
		GridBagConstraints gbc_btnCreateTemplate = new GridBagConstraints();
		gbc_btnCreateTemplate.anchor = GridBagConstraints.CENTER;
		gbc_btnCreateTemplate.gridwidth = GridBagConstraints.REMAINDER;
		gbc_btnCreateTemplate.insets = new Insets(0, 0, 5, 0);
		gbc_btnCreateTemplate.gridx = 0;
		gbc_btnCreateTemplate.gridy = 1;
		MainMenu.add(btnCreateTemplate, gbc_btnCreateTemplate);
		
		JButton btnHelp = new JButton("Help");
		btnHelp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout cl = (CardLayout)(contentPane.getLayout());
				cl.show(contentPane, "Help");
			}
		});
		GridBagConstraints gbc_btnHelp = new GridBagConstraints();
		gbc_btnHelp.anchor = GridBagConstraints.CENTER;
		gbc_btnHelp.gridwidth = GridBagConstraints.REMAINDER;
		gbc_btnHelp.insets = new Insets(0, 0, 5, 0);
		gbc_btnHelp.gridx = 0;
		gbc_btnHelp.gridy = 2;
		MainMenu.add(btnHelp, gbc_btnHelp);		
		JButton btnExit = new JButton("Exit");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		GridBagConstraints gbc_btnExit = new GridBagConstraints();
		gbc_btnExit.anchor = GridBagConstraints.CENTER;
		gbc_btnExit.gridwidth = GridBagConstraints.REMAINDER;
		gbc_btnExit.insets = new Insets(0, 0, 0, 0);
		gbc_btnExit.gridx = 0;
		gbc_btnExit.gridy = 3;
		MainMenu.add(btnExit, gbc_btnExit);
		
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
				CardLayout cl = (CardLayout)(contentPane.getLayout());
				cl.show(contentPane, "Help");
			}
		});
		panel.add(btnHelp_1);
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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
				CardLayout cl = (CardLayout)(contentPane.getLayout());
				cl.show(contentPane, "Help");
			}
		});
		panel_2.add(btnHelp_2);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//TODO: create document button
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
				CardLayout cl = (CardLayout)(contentPane.getLayout());
				cl.show(contentPane, "Help");
			}
		});
		panel_7.add(btnHelp_3);
		
		JButton btnCreate_1 = new JButton("Create");
		btnCreate_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//TODO: create template button
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
		
		JPanel Help = new JPanel();
		//TODO: create Help panel
		contentPane.add(Help, "Help");
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
	
}
