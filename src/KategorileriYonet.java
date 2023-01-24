import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.classes.Veritabani;

public class KategorileriYonet {

	Veritabani veritabani = new Veritabani();

	public JFrame frame;
	DefaultTableModel KategoriModel = new DefaultTableModel();
	Object[] KategoriKolonu = { "Kategori ID", "Kategori Adi" };
	Object[] KategoriSatiri = new Object[2];
	private JTextField tfkategoriId;
	private JTextField tfKategoriIsmi;
	private JTable tblkategoriler;
	private JButton btnDuzenle, btnYeniEkle;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					KategorileriYonet window = new KategorileriYonet();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public KategorileriYonet() {
		initialize();
		kategorileriYenile();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLayeredPane lyMenu = new JLayeredPane();
		lyMenu.setBackground(new Color(250, 240, 230));
		lyMenu.setOpaque(true);
		lyMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				for (int i = -165; i <= 10; i++) {
					lyMenu.setBounds(i, 10, 180, 543);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				for (int i = 10; i >= -165; i--) {
					lyMenu.setBounds(i, 10, 180, 543);
				}
			}
		});
		frame.getContentPane().setLayout(null);
		lyMenu.setBounds(-85, 10, 134, 543);
		frame.getContentPane().add(lyMenu);

		JLabel lbUyelik = new JLabel("Uyelik Islemleri");
		lbUyelik.setBounds(10, 55, 114, 30);
		lbUyelik.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UyelikIslemleri frm1 = new UyelikIslemleri();
				frm1.frame.setVisible(true);
				frame.dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				for (int i = -165; i <= 10; i++) {
					lyMenu.setBounds(i, 10, 180, 543);
				}
			}
		});
		lyMenu.setLayout(null);
		lyMenu.add(lbUyelik);

		JLabel lblBiletleriYonet = new JLabel("Biletleri Yonet");
		lblBiletleriYonet.setBounds(10, 95, 114, 30);
		lblBiletleriYonet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				for (int i = -165; i <= 10; i++) {
					lyMenu.setBounds(i, 10, 180, 543);
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				BiletleriYonet frm2 = new BiletleriYonet();
				frm2.frame.setVisible(true);
				frame.dispose();
			}
		});
		lyMenu.add(lblBiletleriYonet);

		JLabel lblKategorileriYonet = new JLabel("Kategorileri Yonet");
		lblKategorileriYonet.setBounds(10, 135, 114, 30);
		lblKategorileriYonet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				for (int i = -165; i <= 10; i++) {
					lyMenu.setBounds(i, 10, 180, 543);
				}

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				KategorileriYonet frm3 = new KategorileriYonet();
				frm3.frame.setVisible(true);
				frame.dispose();
			}
		});
		lyMenu.add(lblKategorileriYonet);

		JLabel lblFilmleriYonet = new JLabel("Filmleri Yonet");
		lblFilmleriYonet.setBounds(10, 175, 114, 30);
		lblFilmleriYonet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				for (int i = -165; i <= 10; i++) {
					lyMenu.setBounds(i, 10, 180, 543);
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				FilmleriYonet frm4 = new FilmleriYonet();
				frm4.frame.setVisible(true);
				frame.dispose();
			}
		});
		lyMenu.add(lblFilmleriYonet);

		JLabel lblNewLabel_4 = new JLabel("Seanslari Yonet");
		lblNewLabel_4.setBounds(10, 215, 114, 30);
		lyMenu.add(lblNewLabel_4);
		lblNewLabel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SeansYonet frm5 = new SeansYonet();
				frm5.frame.setVisible(true);
				frame.dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				for (int i = -165; i <= 10; i++) {
					lyMenu.setBounds(i, 10, 180, 543);
				}
			}
		});

		JLabel lblMenu = new JLabel("Menu");
		lblMenu.setBounds(10, 10, 162, 35);
		lyMenu.add(lblMenu);
		KategoriModel.setColumnIdentifiers(KategoriKolonu);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(628, 55, 248, 498);
		frame.getContentPane().add(scrollPane);

		JLabel lblNewLabel = new JLabel("Kategori ID");
		lblNewLabel.setBounds(352, 84, 143, 21);
		frame.getContentPane().add(lblNewLabel);

		tfkategoriId = new JTextField();
		tfkategoriId.setEnabled(false);
		tfkategoriId.setColumns(10);
		tfkategoriId.setBounds(352, 115, 210, 19);
		frame.getContentPane().add(tfkategoriId);

		tfKategoriIsmi = new JTextField();
		tfKategoriIsmi.setColumns(10);
		tfKategoriIsmi.setBounds(352, 176, 210, 19);
		frame.getContentPane().add(tfKategoriIsmi);

		JLabel lblKategoriIsmi = new JLabel("Kategori Ismi");
		lblKategoriIsmi.setBounds(352, 145, 143, 21);
		frame.getContentPane().add(lblKategoriIsmi);

		btnDuzenle = new JButton("Duzenle");
		btnDuzenle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Connection connection = DriverManager.getConnection(veritabani.connectionString,
							veritabani.username, veritabani.password);
					PreparedStatement statement = connection.prepareStatement("UPDATE tblkategoriler SET KategoriAdi='"
							+ tfKategoriIsmi.getText() + "' WHERE PK_KategoriID = '" + tfkategoriId.getText() + "'");
					int kaydedildi = statement.executeUpdate();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				deleteAllRows(KategoriModel);
				kategorileriYenile();
			}
		});
		btnDuzenle.setBounds(319, 258, 113, 21);
		frame.getContentPane().add(btnDuzenle);

		btnYeniEkle = new JButton("Yeni Ekle");
		btnYeniEkle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Connection connection = DriverManager.getConnection(veritabani.connectionString,
							veritabani.username, veritabani.password);
					PreparedStatement statement = connection.prepareStatement(
							"INSERT INTO tblkategoriler (KategoriAdi) VALUES('" + tfKategoriIsmi.getText() + "')");
					int kaydedildi = statement.executeUpdate();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				deleteAllRows(KategoriModel);
				kategorileriYenile();
			}
		});
		btnYeniEkle.setBounds(478, 258, 113, 21);
		frame.getContentPane().add(btnYeniEkle);

		tblkategoriler = new JTable();
		tblkategoriler.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tfkategoriId.setText(tblkategoriler.getValueAt(tblkategoriler.getSelectedRow(), 0).toString());
				tfKategoriIsmi.setText(tblkategoriler.getValueAt(tblkategoriler.getSelectedRow(), 1).toString());
			}
		});
		KategoriModel.setColumnIdentifiers(KategoriKolonu);
		tblkategoriler.setBounds(383, 411, -40, -29);
		scrollPane.setViewportView(tblkategoriler);

		JButton btnSil = new JButton("Sil");
		btnSil.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Connection connection = DriverManager.getConnection(veritabani.connectionString,
							veritabani.username, veritabani.password);
					PreparedStatement statement = connection.prepareStatement(
							"Delete FROM tblkategoriler WHERE PK_KategoriID = '" + tfkategoriId.getText() + "'");
					int kaydedildi = statement.executeUpdate();
					connection.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				kategorileriYenile();
			}
		});
		btnSil.setBounds(419, 334, 85, 21);
		frame.getContentPane().add(btnSil);
	}

	private void kategorileriYenile() {
		try {
			deleteAllRows(KategoriModel);
			Connection connection = DriverManager.getConnection(veritabani.connectionString, veritabani.username,
					veritabani.password);
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM tblkategoriler");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				KategoriSatiri[0] = rs.getString("PK_KategoriID");
				KategoriSatiri[1] = rs.getString("KategoriAdi");
				KategoriModel.addRow(KategoriSatiri);
				tblkategoriler.setModel(KategoriModel);
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void deleteAllRows(final DefaultTableModel model) {
		for (int i = model.getRowCount() - 1; i >= 0; i--) {
			model.removeRow(i);
		}
	}
}
