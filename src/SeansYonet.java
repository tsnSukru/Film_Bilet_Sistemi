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
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;

import com.classes.Veritabani;

public class SeansYonet {

	Veritabani veritabani = new Veritabani();

	public JFrame frame;
	private JTextField tfSeansID;
	private JTextField tfFilmAdi;
	private JTextField tfBosYer;
	private JTable tblSeans;
	private JTable tblFilm;
	private JComboBox cbSalon;

	DefaultTableModel seansModel = new DefaultTableModel();
	Object[] seansKolonu = { "Seans ID", "Film Adi", "Salon ID", "Bos Yer" };
	Object[] seansSatiri = new Object[4];
	DefaultTableModel filmModel = new DefaultTableModel();
	Object[] filmKolonu = { "Film ID", "Film Adi" };
	Object[] filmSatiri = new Object[2];
	private JTextField tfarananfilm;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					SeansYonet window = new SeansYonet();
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
	public SeansYonet() {
		initialize();
		filmleriYenile();
		seanslariYenile();
		comboboxYenile();
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
		lyMenu.setBounds(-98, 0, 150, 563);
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
		lblBiletleriYonet.setBounds(10, 95, 162, 30);
		lyMenu.add(lblBiletleriYonet);

		JLabel lblKategorileriYonet = new JLabel("Kategorileri Yonet");
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
		lblKategorileriYonet.setBounds(10, 135, 162, 30);
		lyMenu.add(lblKategorileriYonet);

		JLabel lblFilmleriYonet = new JLabel("Filmleri Yonet");
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
		lblFilmleriYonet.setBounds(10, 175, 162, 30);
		lyMenu.add(lblFilmleriYonet);

		JLabel lblNewLabel_4 = new JLabel("Seanslari Yonet");
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

		tfSeansID = new JTextField();
		tfSeansID.setEnabled(false);
		tfSeansID.setBounds(121, 115, 170, 19);
		frame.getContentPane().add(tfSeansID);
		tfSeansID.setColumns(10);

		JLabel lblNewLabel = new JLabel("Seans ID");
		lblNewLabel.setBounds(121, 86, 113, 19);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblFilmId = new JLabel("Film Adi");
		lblFilmId.setBounds(121, 144, 113, 19);
		frame.getContentPane().add(lblFilmId);

		tfFilmAdi = new JTextField();
		tfFilmAdi.setEnabled(false);
		tfFilmAdi.setColumns(10);
		tfFilmAdi.setBounds(121, 173, 170, 19);
		frame.getContentPane().add(tfFilmAdi);

		JLabel lblNewLabel_1_1 = new JLabel("Bos Yer");
		lblNewLabel_1_1.setBounds(121, 202, 113, 19);
		frame.getContentPane().add(lblNewLabel_1_1);

		tfBosYer = new JTextField();
		tfBosYer.setColumns(10);
		tfBosYer.setBounds(121, 231, 170, 19);
		frame.getContentPane().add(tfBosYer);

		JLabel lblNewLabel_1_1_1 = new JLabel("Salon ID");
		lblNewLabel_1_1_1.setBounds(121, 260, 113, 19);
		frame.getContentPane().add(lblNewLabel_1_1_1);

		JScrollPane scrollSeans = new JScrollPane();
		scrollSeans.setBounds(378, 231, 248, 332);
		frame.getContentPane().add(scrollSeans);

		JScrollPane scrollFilm = new JScrollPane();
		scrollFilm.setBounds(636, 231, 248, 332);
		frame.getContentPane().add(scrollFilm);

		tblSeans = new JTable();
		tblSeans.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cbSalon.setSelectedItem(tblSeans.getValueAt(tblSeans.getSelectedRow(), 2).toString());
				tfFilmAdi.setText(tblSeans.getValueAt(tblSeans.getSelectedRow(), 1).toString());
				tfBosYer.setText(tblSeans.getValueAt(tblSeans.getSelectedRow(), 3).toString());
				tfSeansID.setText(tblSeans.getValueAt(tblSeans.getSelectedRow(), 0).toString());
			}
		});
		seansModel.setColumnIdentifiers(seansKolonu);
		tblSeans.setBounds(431, 175, 113, 101);
		scrollSeans.setViewportView(tblSeans);

		tblFilm = new JTable();
		filmModel.setColumnIdentifiers(filmKolonu);
		tblFilm.setBounds(725, 175, 113, 101);
		scrollFilm.setViewportView(tblFilm);
		JButton btnSeansEkle = new JButton("Ekle");
		btnSeansEkle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Connection connection = DriverManager.getConnection(veritabani.connectionString,
							veritabani.username, veritabani.password);
					PreparedStatement statement = connection
							.prepareStatement("INSERT INTO tblseanslar (FilmID,BosYer,SalonID) values ('"
									+ tblFilm.getValueAt(tblFilm.getSelectedRow(), 0) + "','" + tfBosYer.getText()
									+ "','" + cbSalon.getSelectedItem() + "')");
					int kaydedildi = statement.executeUpdate();
					connection.close();
					seanslariYenile();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		btnSeansEkle.setBounds(121, 367, 113, 21);
		frame.getContentPane().add(btnSeansEkle);

		JButton btnSil = new JButton("Sil");
		btnSil.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Connection connection = DriverManager.getConnection(veritabani.connectionString,
							veritabani.username, veritabani.password);
					PreparedStatement statement = connection
							.prepareStatement("DELETE FROM tblseanslar WHERE PK_SeansID='" + tfSeansID.getText() + "'");
					int kaydedildi = statement.executeUpdate();
					connection.close();
					seanslariYenile();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		btnSil.setBounds(121, 431, 113, 21);
		frame.getContentPane().add(btnSil);

		tfarananfilm = new JTextField();
		tfarananfilm.addCaretListener(new CaretListener() {
			@Override
			public void caretUpdate(CaretEvent e) {
				deleteAllRows(filmModel);
				try {
					Connection connection = DriverManager.getConnection(veritabani.connectionString,
							veritabani.username, veritabani.password);
					PreparedStatement statement = connection.prepareStatement(
							"SELECT * FROM tblseanslar INNER JOIN tblfilmler on tblfilmler.PK_FilmID=tblseanslar.FilmID INNER JOIN tblkategoriler on "
									+ "tblfilmler.KategoriID=tblkategoriler.PK_KategoriID WHERE FilmAdi LIKE '%"
									+ tfarananfilm.getText() + "%'");
					ResultSet rs = statement.executeQuery();
					while (rs.next()) {
						filmSatiri[0] = rs.getString("PK_FilmID");
						filmSatiri[1] = rs.getString("FilmAdi");
						filmModel.addRow(filmSatiri);
						tblFilm.setModel(filmModel);
					}
					connection.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		tfarananfilm.setColumns(10);
		tfarananfilm.setBounds(636, 190, 180, 19);
		frame.getContentPane().add(tfarananfilm);

		JLabel lblNewLabel_2 = new JLabel("Film Ara");
		lblNewLabel_2.setBounds(636, 167, 90, 13);
		frame.getContentPane().add(lblNewLabel_2);

		cbSalon = new JComboBox();
		cbSalon.setBounds(121, 289, 170, 21);
		frame.getContentPane().add(cbSalon);
	}

	private void filmleriYenile() {
		deleteAllRows(filmModel);
		try {
			Connection connection = DriverManager.getConnection(veritabani.connectionString, veritabani.username,
					veritabani.password);
			PreparedStatement statement = connection.prepareStatement("Select * FROM tblfilmler");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				filmSatiri[0] = rs.getString("PK_FilmID");
				filmSatiri[1] = rs.getString("FilmAdi");
				filmModel.addRow(filmSatiri);
				tblFilm.setModel(filmModel);
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void seanslariYenile() {
		deleteAllRows(seansModel);
		try {
			Connection connection = DriverManager.getConnection(veritabani.connectionString, veritabani.username,
					veritabani.password);
			PreparedStatement statement = connection.prepareStatement(
					"Select * FROM tblseanslar INNER JOIN tblfilmler ON tblseanslar.FilmID = tblfilmler.PK_FilmID");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				seansSatiri[0] = rs.getString("PK_SeansID");
				seansSatiri[1] = rs.getString("FilmAdi");
				seansSatiri[2] = rs.getString("SalonID");
				seansSatiri[3] = rs.getString("BosYer");
				seansModel.addRow(seansSatiri);
				tblSeans.setModel(seansModel);
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
			PreparedStatement statement = connection.prepareStatement("Select * FROM tblsalonlar");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				cbSalon.addItem(rs.getString("PK_SalonID"));
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
