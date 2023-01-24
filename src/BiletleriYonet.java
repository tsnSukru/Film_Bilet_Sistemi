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
import javax.swing.table.DefaultTableModel;

import com.classes.Veritabani;

public class BiletleriYonet {

	Veritabani veritabani = new Veritabani();

	public JFrame frame;
	private JTable tblBiletler;

	DefaultTableModel biletModel = new DefaultTableModel();
	Object[] biletKolonu = { "Bilet ID", "Kullanici Adi", "Seans ID", "Koltuk No" };
	Object[] biletSatiri = new Object[4];

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					BiletleriYonet window = new BiletleriYonet();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BiletleriYonet() {
		initialize();
		biletleriYenile();
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
		lyMenu.setBounds(-93, 10, 150, 543);
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
		lbUyelik.setBounds(10, 55, 130, 30);
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
		lblBiletleriYonet.setBounds(10, 95, 130, 30);
		lyMenu.add(lblBiletleriYonet);

		JLabel lblKategorileriYonet = new JLabel("Kategorileri Yonet");
		lblKategorileriYonet.setBounds(10, 135, 130, 30);
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
		lblFilmleriYonet.setBounds(10, 175, 130, 30);
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
		scrollPane.setBounds(190, 67, 686, 486);
		frame.getContentPane().add(scrollPane);

		tblBiletler = new JTable();
		biletModel.setColumnIdentifiers(biletKolonu);
		tblBiletler.setBounds(97, 94, 13, 56);
		scrollPane.setViewportView(tblBiletler);

		JButton btnSil = new JButton("Sil");
		btnSil.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Connection connection = DriverManager.getConnection(veritabani.connectionString,
							veritabani.username, veritabani.password);
					PreparedStatement statement = connection
							.prepareStatement("Delete FROM tblbiletler WHERE PK_BiletID = '"
									+ tblBiletler.getValueAt(tblBiletler.getSelectedColumn(), 0) + "'");
					int kaydedildi = statement.executeUpdate();
					connection.close();
					biletleriYenile();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		btnSil.setBounds(78, 267, 85, 21);
		frame.getContentPane().add(btnSil);
	}

	private void biletleriYenile() {
		try {
			deleteAllRows(biletModel);
			Connection connection = DriverManager.getConnection(veritabani.connectionString, veritabani.username,
					veritabani.password);
			PreparedStatement statement = connection.prepareStatement("Select * FROM tblbiletler");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				biletSatiri[0] = rs.getString("PK_BiletID");
				biletSatiri[1] = rs.getString("KullaniciID");
				biletSatiri[2] = rs.getString("SeansID");
				biletSatiri[3] = rs.getString("koltukNo");
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
