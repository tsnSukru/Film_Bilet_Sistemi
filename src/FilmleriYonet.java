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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

import com.classes.Veritabani;

public class FilmleriYonet {

	Veritabani veritabani = new Veritabani();

	public JFrame frame;
	JTextPane tpAciklama;
	private JTextField tfID;
	private JTextField TfAdi;
	private JTable table;
	private JComboBox cbKategori;

	DefaultTableModel filmModel = new DefaultTableModel();
	Object[] FilmKolonu = { "Kategori", "Film ID", "Film Adi", "Film Aciklamasi" };
	Object[] filmSatiri = new Object[4];

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					FilmleriYonet window = new FilmleriYonet();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FilmleriYonet() {
		initialize();
		filmleriYenile();
		comboboxYenile();
	}

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
		lyMenu.setBounds(-86, 0, 150, 563);
		frame.getContentPane().add(lyMenu);

		JLabel lbUyelik = new JLabel("Uyelik Islemleri");
		lbUyelik.setBounds(10, 55, 130, 30);
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
		lblBiletleriYonet.setBounds(10, 95, 130, 30);
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
		lblKategorileriYonet.setBounds(10, 135, 130, 30);
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
		lblFilmleriYonet.setBounds(10, 175, 130, 30);
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
		lblNewLabel_4.setBounds(10, 215, 130, 30);
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
		lblMenu.setBounds(10, 10, 130, 35);
		lyMenu.add(lblMenu);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(628, 67, 248, 486);
		frame.getContentPane().add(scrollPane);

		JButton btnDuzenle = new JButton("Duzenle");
		btnDuzenle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Connection connection = DriverManager.getConnection(veritabani.connectionString,
							veritabani.username, veritabani.password);
					PreparedStatement statement = connection
							.prepareStatement("UPDATE tblfilmler SET FilmAdi='" + TfAdi.getText() + "',KategoriID='"
									+ (cbKategori.getSelectedIndex() + 1) + "',FilmAciklamasi='" + tpAciklama.getText()
									+ "' WHERE PK_FilmID = '" + tfID.getText() + "'");
					int kaydedildi = statement.executeUpdate();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				filmleriYenile();
			}
		});
		btnDuzenle.setBounds(350, 415, 99, 29);
		frame.getContentPane().add(btnDuzenle);

		JButton btnSil = new JButton("Sil");
		btnSil.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Connection connection = DriverManager.getConnection(veritabani.connectionString,
							veritabani.username, veritabani.password);
					PreparedStatement statement = connection
							.prepareStatement("Delete FROM tblfilmler WHERE PK_FilmID = '" + tfID.getText() + "'");
					int kaydedildi = statement.executeUpdate();
					connection.close();
					filmleriYenile();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		btnSil.setBounds(350, 454, 99, 29);
		frame.getContentPane().add(btnSil);

		JButton btnYeniekle = new JButton("Yeni Ekle");
		btnYeniekle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Connection connection = DriverManager.getConnection(veritabani.connectionString,
							veritabani.username, veritabani.password);
					PreparedStatement statement = connection
							.prepareStatement("INSERT INTO tblfilmler (FilmAdi,KategoriID,FilmAciklamasi) VALUES ('"
									+ TfAdi.getText() + "','" + (cbKategori.getSelectedIndex() + 1) + "','"
									+ tpAciklama.getText() + "') ");
					int kaydedildi = statement.executeUpdate();
					connection.close();
					filmleriYenile();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		btnYeniekle.setBounds(350, 495, 99, 29);
		frame.getContentPane().add(btnYeniekle);

		tfID = new JTextField();
		tfID.setEnabled(false);
		tfID.setBounds(312, 133, 177, 19);
		frame.getContentPane().add(tfID);
		tfID.setColumns(10);

		JLabel lblNewLabel = new JLabel("Film ID");
		lblNewLabel.setBounds(312, 93, 99, 30);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblFilmAdi = new JLabel("Film Adi");
		lblFilmAdi.setBounds(312, 162, 99, 30);
		frame.getContentPane().add(lblFilmAdi);

		TfAdi = new JTextField();
		TfAdi.setColumns(10);
		TfAdi.setBounds(312, 202, 177, 19);
		frame.getContentPane().add(TfAdi);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cbKategori.setSelectedItem(table.getValueAt(table.getSelectedRow(), 0).toString());
				tfID.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
				TfAdi.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
				tpAciklama.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
			}
		});
		filmModel.setColumnIdentifiers(FilmKolonu);
		table.setBounds(321, 335, -117, -116);
		scrollPane.setViewportView(table);

		tpAciklama = new JTextPane();
		tpAciklama.setBounds(223, 231, 350, 161);
		frame.getContentPane().add(tpAciklama);

		JLabel lblKategori = new JLabel("Kategori");
		lblKategori.setBounds(312, 23, 99, 30);
		frame.getContentPane().add(lblKategori);

		cbKategori = new JComboBox();
		cbKategori.setBounds(312, 65, 177, 21);
		frame.getContentPane().add(cbKategori);
	}

	private void filmleriYenile() {
		try {
			deleteAllRows(filmModel);
			Connection connection = DriverManager.getConnection(veritabani.connectionString, veritabani.username,
					veritabani.password);
			PreparedStatement statement = connection.prepareStatement(
					"Select * FROM tblfilmler INNER JOIN tblkategoriler ON tblfilmler.KategoriID = tblkategoriler.PK_KategoriID");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				filmSatiri[0] = rs.getString("KategoriAdi");
				filmSatiri[1] = rs.getString("PK_FilmID");
				filmSatiri[2] = rs.getString("FilmAdi");
				filmSatiri[3] = rs.getString("FilmAciklamasi");
				filmModel.addRow(filmSatiri);
				table.setModel(filmModel);

			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void comboboxYenile() {
		try {
			Connection connection = DriverManager.getConnection(veritabani.connectionString, veritabani.username,
					veritabani.password);
			PreparedStatement statement = connection.prepareStatement("Select * FROM tblkategoriler");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				cbKategori.addItem(rs.getString("KategoriAdi"));
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
