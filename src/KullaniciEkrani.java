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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;

import com.classes.Veritabani;

public class KullaniciEkrani {

	Veritabani veritabani = new Veritabani();

	public JFrame frame;
	private JTextField textField;
	private JTextField textField_2;
	private JTable tblBiletler;
	private String kullaniciAdi;
	DefaultTableModel seansModel = new DefaultTableModel();
	Object[] seansKolonu = { "Kategori", "Seans No", "Film Adi", "Film Aciklamasi", "Salon No", "Bos Yer" };
	Object[] seansSatiri = new Object[6];
	DefaultTableModel biletModel = new DefaultTableModel();
	Object[] biletKolonu = { "Bilet Kodu", "Seans No", "Film Adi", "Salon No", "Koltuk No" };
	Object[] biletSatiri = new Object[5];
	private JTable tblSeanslar;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					KullaniciEkrani window = new KullaniciEkrani();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public KullaniciEkrani() {
		initialize();
		seanslariYenile();
		biletleriYenile();
	}

	public KullaniciEkrani(String kullaniciAdi) {
		this.kullaniciAdi = kullaniciAdi;
		initialize();
		seanslariYenile();
		biletleriYenile();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 10, 866, 543);
		frame.getContentPane().add(tabbedPane);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Bilet Al", null, panel, null);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Film Ara");
		lblNewLabel.setBounds(10, 10, 131, 23);
		panel.add(lblNewLabel);

		textField = new JTextField();
		textField.addCaretListener(new CaretListener() {
			@Override
			public void caretUpdate(CaretEvent e) {
				deleteAllRows(seansModel);
				try {
					Connection connection = DriverManager.getConnection(veritabani.connectionString,
							veritabani.username, veritabani.password);
					PreparedStatement statement = connection.prepareStatement(
							"SELECT * FROM tblseanslar INNER JOIN tblfilmler on tblfilmler.PK_FilmID=tblseanslar.FilmID INNER JOIN tblkategoriler on "
									+ "tblfilmler.KategoriID=tblkategoriler.PK_KategoriID WHERE FilmAdi LIKE '%"
									+ textField.getText() + "%'");
					ResultSet rs = statement.executeQuery();
					while (rs.next()) {
						seansSatiri[0] = rs.getString("KategoriAdi");
						seansSatiri[1] = rs.getString("PK_SeansID");
						seansSatiri[2] = rs.getString("FilmAdi");
						seansSatiri[3] = rs.getString("FilmAciklamasi");
						seansSatiri[4] = rs.getString("SalonID");
						seansSatiri[5] = rs.getString("BosYer");
						seansModel.addRow(seansSatiri);
						tblSeanslar.setModel(seansModel);
					}
					connection.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		textField.setBounds(10, 43, 248, 19);
		panel.add(textField);
		textField.setColumns(10);

		JButton btnBiletAl = new JButton("Bilet Al");
		btnBiletAl.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Connection connection = DriverManager.getConnection(veritabani.connectionString,
							veritabani.username, veritabani.password);
					PreparedStatement statement = connection
							.prepareStatement("INSERT INTO tblbiletler (KullaniciID,SeansID,koltukNo) VALUES ('"
									+ kullaniciAdi + "','" + tblSeanslar.getValueAt(tblSeanslar.getSelectedRow(), 1)
									+ "','" + tblSeanslar.getValueAt(tblSeanslar.getSelectedRow(), 5) + "')");
					int kaydedildi = statement.executeUpdate();
					connection.close();
					biletleriYenile();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				try {
					Connection connection = DriverManager.getConnection(veritabani.connectionString,
							veritabani.username, veritabani.password);
					PreparedStatement statement = connection
							.prepareStatement("UPDATE tblseanslar SET BosYer=BosYer - '1' WHERE PK_SeansID='"
									+ tblSeanslar.getValueAt(tblSeanslar.getSelectedRow(), 5) + "'");
					int kaydedildi = statement.executeUpdate();
					connection.close();
					seanslariYenile();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		btnBiletAl.setBounds(128, 438, 85, 21);
		panel.add(btnBiletAl);
		seansModel.setColumnIdentifiers(seansKolonu);
		JTextPane tpAciklama = new JTextPane();
		tpAciklama.setBounds(461, 140, 257, 189);
		panel.add(tpAciklama);

		JLabel lblNewLabel_1 = new JLabel("Film Aciklamasi");
		lblNewLabel_1.setBounds(461, 107, 261, 23);
		panel.add(lblNewLabel_1);

		JScrollPane scSeans = new JScrollPane();
		scSeans.setBounds(10, 66, 336, 319);
		panel.add(scSeans);

		tblSeanslar = new JTable();
		tblSeanslar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tpAciklama.setText(tblSeanslar.getValueAt(tblSeanslar.getSelectedRow(), 3).toString());
			}
		});
		seansModel.setColumnIdentifiers(seansKolonu);
		tblSeanslar.setBounds(47, 346, 125, 78);
		scSeans.setViewportView(tblSeanslar);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Biletlerim", null, panel_1, null);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(300, 135, 248, 270);
		panel_1.add(scrollPane);

		textField_2 = new JTextField();
		textField_2.addCaretListener(new CaretListener() {
			@Override
			public void caretUpdate(CaretEvent e) {
				deleteAllRows(biletModel);
				try {
					Connection connection = DriverManager.getConnection(veritabani.connectionString,
							veritabani.username, veritabani.password);
					PreparedStatement statement = connection.prepareStatement(
							"SELECT * FROM tblbiletler INNER JOIN tblseanslar on tblseanslar.PK_SeansID=tblbiletler.SeansID INNER JOIN tblfilmler on"
									+ "tblfilmler.PK_FilmID=tblseanslar.FilmID WHERE FilmAdi LIKE '%"
									+ textField_2.getText() + "%'");
					ResultSet rs = statement.executeQuery();
					while (rs.next()) {
						biletSatiri[0] = rs.getString("PK_BiletID");
						biletSatiri[1] = rs.getString("SeansID");
						biletSatiri[2] = rs.getString("FilmAdi");
						biletSatiri[3] = rs.getString("SalonID");
						biletSatiri[4] = rs.getString("koltukNo");
						biletModel.addRow(biletSatiri);
						tblBiletler.setModel(biletModel);
					}
					connection.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		textField_2.setColumns(10);
		textField_2.setBounds(300, 106, 248, 19);
		panel_1.add(textField_2);

		JLabel lblFilmAra_1 = new JLabel("Film Ara");
		lblFilmAra_1.setBounds(300, 73, 131, 23);
		panel_1.add(lblFilmAra_1);

		tblBiletler = new JTable();
		biletModel.setColumnIdentifiers(biletKolonu);
		tblBiletler.setBounds(93, 199, -29, -44);
		scrollPane.setViewportView(tblBiletler);

		JButton btnIptal = new JButton("Bileti \u0130ptal Et");
		btnIptal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Connection connection = DriverManager.getConnection(veritabani.connectionString,
							veritabani.username, veritabani.password);
					PreparedStatement statement = connection
							.prepareStatement("DELETE FROM tblbiletler WHERE PK_BiletID = '"
									+ tblBiletler.getValueAt(tblBiletler.getSelectedRow(), 0) + "'");
					int kaydedildi = statement.executeUpdate();
					connection.close();
					biletleriYenile();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		btnIptal.setBounds(348, 434, 157, 21);
		panel_1.add(btnIptal);
	}

	private void seanslariYenile() {
		deleteAllRows(seansModel);
		try {
			Connection connection = DriverManager.getConnection(veritabani.connectionString, veritabani.username,
					veritabani.password);
			PreparedStatement statement = connection.prepareStatement(
					"SELECT * FROM tblseanslar INNER JOIN tblfilmler on tblfilmler.PK_FilmID=tblseanslar.FilmID INNER JOIN tblkategoriler on "
							+ "tblfilmler.KategoriID=tblkategoriler.PK_KategoriID");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				seansSatiri[0] = rs.getString("KategoriAdi");
				seansSatiri[1] = rs.getString("PK_SeansID");
				seansSatiri[2] = rs.getString("FilmAdi");
				seansSatiri[3] = rs.getString("FilmAciklamasi");
				seansSatiri[4] = rs.getString("SalonID");
				seansSatiri[5] = rs.getString("BosYer");
				seansModel.addRow(seansSatiri);
				tblSeanslar.setModel(seansModel);
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void biletleriYenile() {
		deleteAllRows(biletModel);
		try {
			Connection connection = DriverManager.getConnection(veritabani.connectionString, veritabani.username,
					veritabani.password);
			PreparedStatement statement = connection.prepareStatement(
					"SELECT * FROM tblbiletler INNER JOIN tblseanslar on tblseanslar.PK_SeansID=tblbiletler.SeansID INNER JOIN tblfilmler on "
							+ "tblfilmler.PK_FilmID=tblseanslar.FilmID WHERE KullaniciID='" + kullaniciAdi + "'");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				biletSatiri[0] = rs.getString("PK_BiletID");
				biletSatiri[1] = rs.getString("SeansID");
				biletSatiri[2] = rs.getString("FilmAdi");
				biletSatiri[3] = rs.getString("SalonID");
				biletSatiri[4] = rs.getString("koltukNo");
				biletModel.addRow(biletSatiri);
				tblBiletler.setModel(biletModel);
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
