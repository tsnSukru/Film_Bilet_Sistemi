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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;

import com.classes.Veritabani;

public class UyelikIslemleri {

	Veritabani veritabani = new Veritabani();

	int onaylimi;
	public JFrame frame;
	private JTextField tfAdi;
	private JTextField tfKullaniciAdi;
	private JTextField tfSoyadi;
	private JTextField tfSifresi;
	private JTextField tfArananUye;
	DefaultTableModel uyelerModel = new DefaultTableModel();
	Object[] uyelerKolonu = { "Kullanici Adi", "Adi", "Soyadi", "Sifresi" };
	Object[] uyelerSatiri = new Object[4];
	private JTable tblUyeler;
	private JTable tblPasifUyeler;
	DefaultTableModel PasifUyelerModel = new DefaultTableModel();
	Object[] PasifUyelerKolonu = { "Kullanici Adi", "Adi", "Soyadi", "Sifresi" };
	Object[] PasifUyelerSatiri = new Object[4];
	private JTextField tfKullaniciAdi1;
	private JTextField tfArananUye1;
	private JTextField tfAdi1;
	private JTextField tfSoyadi1;
	private JTextField tfSifre1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UyelikIslemleri window = new UyelikIslemleri();
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
	public UyelikIslemleri() {
		initialize();
		uyeleriYenile();
		pasifUyeleriYenile();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

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
		lyMenu.setBounds(-165, 10, 186, 543);
		frame.getContentPane().add(lyMenu);

		JLabel lbUyelik = new JLabel("Uyelik Islemleri");
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
		lbUyelik.setBounds(10, 55, 162, 30);
		lyMenu.add(lbUyelik);

		JLabel lblBiletleriYonet = new JLabel("Biletleri Yonet");
		lblBiletleriYonet.setBounds(10, 95, 162, 30);
		lyMenu.add(lblBiletleriYonet);
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

		JLabel lblKategorileriYonet = new JLabel("Kategorileri Yonet");
		lblKategorileriYonet.setBounds(10, 135, 162, 30);
		lyMenu.add(lblKategorileriYonet);
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

		JLabel lblFilmleriYonet = new JLabel("Filmleri Yonet");
		lblFilmleriYonet.setBounds(10, 175, 162, 30);
		lyMenu.add(lblFilmleriYonet);
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

		JLabel lblNewLabel_4 = new JLabel("Seanslari Yonet");
		lblNewLabel_4.setBounds(10, 215, 162, 30);
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

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(31, 10, 845, 543);
		frame.getContentPane().add(tabbedPane);
		uyelerModel.setColumnIdentifiers(uyelerKolonu);
		uyelerModel.setColumnIdentifiers(uyelerKolonu);

		JPanel pnlUyelikIslemleri = new JPanel();
		tabbedPane.addTab("Uyelik \u0130slemleri", null, pnlUyelikIslemleri, null);
		pnlUyelikIslemleri.setLayout(null);

		tfAdi = new JTextField();
		tfAdi.setEnabled(false);
		tfAdi.setBounds(10, 130, 180, 19);
		pnlUyelikIslemleri.add(tfAdi);
		tfAdi.setColumns(10);

		tfKullaniciAdi = new JTextField();
		tfKullaniciAdi.setEnabled(false);
		tfKullaniciAdi.setBounds(10, 67, 180, 19);
		pnlUyelikIslemleri.add(tfKullaniciAdi);
		tfKullaniciAdi.setColumns(10);

		JLabel lblNewLabel = new JLabel("Kullanici Adi");
		lblNewLabel.setBounds(10, 38, 180, 19);
		pnlUyelikIslemleri.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Adi");
		lblNewLabel_1.setBounds(10, 104, 180, 16);
		pnlUyelikIslemleri.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Soyadi");
		lblNewLabel_1_1.setBounds(10, 167, 180, 16);
		pnlUyelikIslemleri.add(lblNewLabel_1_1);

		tfSoyadi = new JTextField();
		tfSoyadi.setEnabled(false);
		tfSoyadi.setColumns(10);
		tfSoyadi.setBounds(10, 193, 180, 19);
		pnlUyelikIslemleri.add(tfSoyadi);

		JLabel lblNewLabel_1_1_1 = new JLabel("Sifresi");
		lblNewLabel_1_1_1.setBounds(10, 228, 180, 16);
		pnlUyelikIslemleri.add(lblNewLabel_1_1_1);

		tfSifresi = new JTextField();
		tfSifresi.setEnabled(false);
		tfSifresi.setColumns(10);
		tfSifresi.setBounds(10, 254, 180, 19);
		pnlUyelikIslemleri.add(tfSifresi);

		tfArananUye = new JTextField();
		tfArananUye.addCaretListener(new CaretListener() {
			@Override
			public void caretUpdate(CaretEvent e) {
				try {
					deleteAllRows(uyelerModel);
					Connection connection = DriverManager.getConnection(veritabani.connectionString,
							veritabani.username, veritabani.password);
					PreparedStatement statement = connection.prepareStatement(
							"SELECT * FROM tblkullanicilar WHERE PK_KullaniciID LIKE '" + tfArananUye.getText() + "%'");
					ResultSet rs = statement.executeQuery();
					while (rs.next()) {
						uyelerSatiri[0] = rs.getString("PK_KullaniciID").replace(" ", "");
						uyelerSatiri[1] = rs.getString("KullaniciAdi").replace(" ", "");
						uyelerSatiri[2] = rs.getString("KullaniciSoyadi").replace(" ", "");
						uyelerSatiri[3] = rs.getString("KullaniciSifresi").replace(" ", "");
						uyelerModel.addRow(uyelerSatiri);
						tblUyeler.setModel(uyelerModel);
					}
					connection.close();
				} catch (SQLException b) {
					b.printStackTrace();
				}
			}
		});
		tfArananUye.setColumns(10);
		tfArananUye.setBounds(548, 38, 180, 19);
		pnlUyelikIslemleri.add(tfArananUye);

		JLabel lblAranacakKullanicininKullanici = new JLabel("Aranacak Kullanici Adini Giriniz");
		lblAranacakKullanicininKullanici.setBounds(548, 9, 180, 19);
		pnlUyelikIslemleri.add(lblAranacakKullanicininKullanici);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(431, 122, 399, 384);
		pnlUyelikIslemleri.add(scrollPane);

		tblUyeler = new JTable();
		tblUyeler.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tfKullaniciAdi.setText(tblUyeler.getValueAt(tblUyeler.getSelectedRow(), 0).toString());
				tfAdi.setText(tblUyeler.getValueAt(tblUyeler.getSelectedRow(), 1).toString());
				tfSoyadi.setText(tblUyeler.getValueAt(tblUyeler.getSelectedRow(), 2).toString());
				tfSifresi.setText(tblUyeler.getValueAt(tblUyeler.getSelectedRow(), 3).toString());
			}
		});
		tblUyeler.setBounds(335, 325, -109, -96);
		scrollPane.setViewportView(tblUyeler);

		JButton btnDuzenle = new JButton("Duzenle");
		btnDuzenle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tfAdi.enable(true);
				tfSoyadi.enable(true);
				tfSifresi.enable(true);
			}
		});
		btnDuzenle.setBounds(10, 298, 85, 21);
		pnlUyelikIslemleri.add(btnDuzenle);

		JButton btnKaydet = new JButton("Kaydet");
		btnKaydet.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Connection connection = DriverManager.getConnection(veritabani.connectionString,
							veritabani.username, veritabani.password);
					PreparedStatement statement = connection.prepareStatement(
							"UPDATE tblkullanicilar SET KullaniciAdi='" + tfAdi.getText() + "',KullaniciSoyadi='"
									+ tfSoyadi.getText() + "',KullaniciSifresi='" + tfSifresi.getText()
									+ "' WHERE PK_KullaniciID = '" + tfKullaniciAdi.getText() + "'");
					int kaydedildi = statement.executeUpdate();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				uyeleriYenile();
				tfAdi.enable(false);
				tfSoyadi.enable(false);
				tfSifresi.enable(false);
			}
		});
		btnKaydet.setBounds(174, 298, 85, 21);
		pnlUyelikIslemleri.add(btnKaydet);

		JButton btnSil = new JButton("Sil");
		btnSil.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Connection connection = DriverManager.getConnection(veritabani.connectionString,
							veritabani.username, veritabani.password);
					PreparedStatement statement = connection.prepareStatement(
							"Delete FROM tblkullanicilar WHERE PK_KullaniciID = '" + tfKullaniciAdi.getText() + "'");
					int kaydedildi = statement.executeUpdate();
					connection.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				uyeleriYenile();
			}
		});
		btnSil.setBounds(10, 358, 85, 21);
		pnlUyelikIslemleri.add(btnSil);

		JButton btnYenile = new JButton("Yenile");
		btnYenile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				uyeleriYenile();
			}
		});
		btnYenile.setBounds(174, 358, 85, 21);
		pnlUyelikIslemleri.add(btnYenile);

		JButton btnAskiyaAl = new JButton("Uyeligi Askiya Al");
		btnAskiyaAl.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Connection connection = DriverManager.getConnection(veritabani.connectionString,
							veritabani.username, veritabani.password);
					PreparedStatement statement = connection.prepareStatement("UPDATE tblkullanicilar SET onaylimi='"
							+ 0 + "' WHERE PK_KullaniciID='" + tfKullaniciAdi.getText() + "'");
					int onaylandimi = statement.executeUpdate();
				} catch (SQLException a) {
					a.printStackTrace();
				}
				pasifUyeleriYenile();
				uyeleriYenile();
			}
		});
		btnAskiyaAl.setBounds(77, 421, 113, 21);
		pnlUyelikIslemleri.add(btnAskiyaAl);

		JPanel pnlUyelikOnay = new JPanel();
		tabbedPane.addTab("Uyelik Onay", null, pnlUyelikOnay, null);
		pnlUyelikOnay.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(431, 122, 399, 384);
		pnlUyelikOnay.add(scrollPane_1);

		tblPasifUyeler = new JTable();
		tblPasifUyeler.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tfKullaniciAdi1.setText(tblPasifUyeler.getValueAt(tblPasifUyeler.getSelectedRow(), 0).toString());
				tfAdi1.setText(tblPasifUyeler.getValueAt(tblPasifUyeler.getSelectedRow(), 1).toString());
				tfSoyadi1.setText(tblPasifUyeler.getValueAt(tblPasifUyeler.getSelectedRow(), 2).toString());
				tfSifre1.setText(tblPasifUyeler.getValueAt(tblPasifUyeler.getSelectedRow(), 3).toString());
			}
		});
		PasifUyelerModel.setColumnIdentifiers(PasifUyelerKolonu);
		tblPasifUyeler.setBounds(339, 292, -87, -112);
		scrollPane_1.setViewportView(tblPasifUyeler);

		JButton btnOnayla = new JButton("Onayla");
		btnOnayla.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Connection connection = DriverManager.getConnection(veritabani.connectionString,
							veritabani.username, veritabani.password);
					PreparedStatement statement = connection.prepareStatement("UPDATE tblkullanicilar SET onaylimi='"
							+ 1 + "' WHERE PK_KullaniciID='" + tfKullaniciAdi1.getText() + "'");
					int onaylandimi = statement.executeUpdate();
				} catch (SQLException a) {
					a.printStackTrace();
				}
				pasifUyeleriYenile();
				uyeleriYenile();
			}
		});
		btnOnayla.setBounds(78, 333, 85, 21);
		pnlUyelikOnay.add(btnOnayla);

		JButton btnReddet = new JButton("Reddet");
		btnReddet.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Connection connection = DriverManager.getConnection(veritabani.connectionString,
							veritabani.username, veritabani.password);
					PreparedStatement statement = connection.prepareStatement(
							"Delete FROM tblkullanicilar WHERE PK_KullaniciID = '" + tfKullaniciAdi1.getText() + "'");
					int kaydedildi = statement.executeUpdate();
					connection.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				pasifUyeleriYenile();
			}

		});
		btnReddet.setBounds(258, 333, 85, 21);
		pnlUyelikOnay.add(btnReddet);

		JLabel lblNewLabel_2 = new JLabel("Kullanici Adi");
		lblNewLabel_2.setBounds(10, 39, 180, 19);
		pnlUyelikOnay.add(lblNewLabel_2);

		tfKullaniciAdi1 = new JTextField();
		tfKullaniciAdi1.setEnabled(false);
		tfKullaniciAdi1.setColumns(10);
		tfKullaniciAdi1.setBounds(10, 68, 180, 19);
		pnlUyelikOnay.add(tfKullaniciAdi1);

		JLabel lblAranacakKullanicininKullanici_1 = new JLabel("Aranacak Kullanici Adini Giriniz");
		lblAranacakKullanicininKullanici_1.setBounds(431, 42, 180, 19);
		pnlUyelikOnay.add(lblAranacakKullanicininKullanici_1);

		tfArananUye1 = new JTextField();
		tfArananUye1.addCaretListener(new CaretListener() {

			@Override
			public void caretUpdate(CaretEvent e) {
				try {
					deleteAllRows(PasifUyelerModel);
					Connection connection = DriverManager.getConnection(veritabani.connectionString,
							veritabani.username, veritabani.password);
					PreparedStatement statement = connection
							.prepareStatement("SELECT * FROM tblkullanicilar WHERE PK_KullaniciID LIKE '"
									+ tfArananUye1.getText() + "%'");
					ResultSet rs = statement.executeQuery();
					while (rs.next()) {
						if (rs.getInt("onaylimi") == 0) {
							PasifUyelerSatiri[0] = rs.getString("PK_KullaniciID").replace(" ", "");
							PasifUyelerSatiri[1] = rs.getString("KullaniciAdi").replace(" ", "");
							PasifUyelerSatiri[2] = rs.getString("KullaniciSoyadi").replace(" ", "");
							PasifUyelerSatiri[3] = rs.getString("KullaniciSifresi").replace(" ", "");
							PasifUyelerModel.addRow(PasifUyelerSatiri);
							tblPasifUyeler.setModel(PasifUyelerModel);
						}
					}
					connection.close();
				} catch (SQLException b) {
					b.printStackTrace();
				}
			}
		});
		tfArananUye1.setColumns(10);
		tfArananUye1.setBounds(431, 68, 180, 19);
		pnlUyelikOnay.add(tfArananUye1);

		JLabel lb = new JLabel("Adi");
		lb.setBounds(10, 108, 180, 16);
		pnlUyelikOnay.add(lb);

		tfAdi1 = new JTextField();
		tfAdi1.setEnabled(false);
		tfAdi1.setColumns(10);
		tfAdi1.setBounds(10, 134, 180, 19);
		pnlUyelikOnay.add(tfAdi1);

		JLabel lblNewLabel_1_1_2 = new JLabel("Soyadi");
		lblNewLabel_1_1_2.setBounds(10, 179, 180, 16);
		pnlUyelikOnay.add(lblNewLabel_1_1_2);

		tfSoyadi1 = new JTextField();
		tfSoyadi1.setEnabled(false);
		tfSoyadi1.setColumns(10);
		tfSoyadi1.setBounds(10, 205, 180, 19);
		pnlUyelikOnay.add(tfSoyadi1);

		JLabel lbsifre = new JLabel("Sifresi");
		lbsifre.setBounds(10, 249, 180, 16);
		pnlUyelikOnay.add(lbsifre);

		tfSifre1 = new JTextField();
		tfSifre1.setEnabled(false);
		tfSifre1.setColumns(10);
		tfSifre1.setBounds(10, 275, 180, 19);
		pnlUyelikOnay.add(tfSifre1);
	}

	private void uyeleriYenile() {
		try {
			deleteAllRows(uyelerModel);
			Connection connection = DriverManager.getConnection(veritabani.connectionString, veritabani.username,
					veritabani.password);
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM tblkullanicilar");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				if (rs.getInt("onaylimi") > 0) {
					uyelerSatiri[0] = rs.getString("PK_KullaniciID").replace(" ", "");
					uyelerSatiri[1] = rs.getString("KullaniciAdi").replace(" ", "");
					uyelerSatiri[2] = rs.getString("KullaniciSoyadi").replace(" ", "");
					uyelerSatiri[3] = rs.getString("KullaniciSifresi").replace(" ", "");
					uyelerModel.addRow(uyelerSatiri);
					tblUyeler.setModel(uyelerModel);
				}
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void pasifUyeleriYenile() {
		try {
			deleteAllRows(PasifUyelerModel);
			Connection connection = DriverManager.getConnection(veritabani.connectionString, veritabani.username,
					veritabani.password);
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM tblkullanicilar");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				if (rs.getInt("onaylimi") == 0) {
					PasifUyelerSatiri[0] = rs.getString("PK_KullaniciID").replace(" ", "");
					PasifUyelerSatiri[1] = rs.getString("KullaniciAdi").replace(" ", "");
					PasifUyelerSatiri[2] = rs.getString("KullaniciSoyadi").replace(" ", "");
					PasifUyelerSatiri[3] = rs.getString("KullaniciSifresi").replace(" ", "");
					PasifUyelerModel.addRow(PasifUyelerSatiri);
					tblPasifUyeler.setModel(PasifUyelerModel);
				}
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
