import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.classes.Veritabani;

public class KayitEkrani {

	Veritabani veritabani = new Veritabani();

	public JFrame frame;
	private JTextField tfKullaniciAdi;
	private JTextField tfAd;
	private JTextField tfSoyad;
	private JTextField tfSifre;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					KayitEkrani window = new KayitEkrani();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public KayitEkrani() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		tfKullaniciAdi = new JTextField();
		tfKullaniciAdi.setBounds(219, 103, 136, 19);
		frame.getContentPane().add(tfKullaniciAdi);
		tfKullaniciAdi.setColumns(10);

		JLabel lblNewLabel = new JLabel("Kullanici Adi");
		lblNewLabel.setBounds(219, 80, 136, 13);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Ad");
		lblNewLabel_1.setBounds(219, 132, 45, 13);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel3 = new JLabel("Soyad");
		lblNewLabel3.setBounds(219, 179, 136, 13);
		frame.getContentPane().add(lblNewLabel3);

		tfAd = new JTextField();
		tfAd.setBounds(219, 155, 136, 19);
		frame.getContentPane().add(tfAd);
		tfAd.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Sifre");
		lblNewLabel_3.setBounds(219, 231, 136, 13);
		frame.getContentPane().add(lblNewLabel_3);

		tfSoyad = new JTextField();
		tfSoyad.setBounds(219, 202, 136, 19);
		frame.getContentPane().add(tfSoyad);
		tfSoyad.setColumns(10);

		tfSifre = new JTextField();
		tfSifre.setBounds(219, 254, 136, 19);
		frame.getContentPane().add(tfSifre);
		tfSifre.setColumns(10);

		JButton btnKaydet = new JButton("Kaydet");
		btnKaydet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Connection connection = DriverManager.getConnection(veritabani.connectionString,
							veritabani.username, veritabani.password);
					PreparedStatement statement = connection.prepareStatement(
							"SELECT count(PK_KullaniciID) as varmi FROM tblkullanicilar WHERE PK_KullaniciID='"
									+ tfKullaniciAdi.getText() + "'");
					java.sql.ResultSet rs = statement.executeQuery();
					while (rs.next()) {
						if (rs.getInt("varmi") == 0) {
							uyeEkle();
						} else {
							JOptionPane.showMessageDialog(frame, "Kullanici Adi Daha �nceden Alinmis!!");
						}
					}
					connection.close();
				} catch (SQLException a) {
					a.printStackTrace();
				}
			}
		});
		btnKaydet.setBounds(244, 295, 85, 21);
		frame.getContentPane().add(btnKaydet);
	}

	private void uyeEkle() {
		try {
			Connection connection = DriverManager.getConnection(veritabani.connectionString, veritabani.username,
					veritabani.password);
			PreparedStatement statement1 = connection.prepareStatement(
					"INSERT INTO tblkullanicilar (PK_KullaniciID,KullaniciAdi,KullaniciSoyadi,KullaniciSifresi,onaylimi) VALUES('"
							+ tfKullaniciAdi.getText() + "','" + tfAd.getText() + "','" + tfSoyad.getText() + "','"
							+ tfSifre.getText() + "','0')");
			int kaydedildi = statement1.executeUpdate();
			JOptionPane.showMessageDialog(frame, "Kayit basarili, uyeliginiz onaylandiginda giris yapabilirsiniz");

			KullaniciGirisi frm1 = new KullaniciGirisi();
			frm1.frame.setVisible(true);
			frame.dispose();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}
}
