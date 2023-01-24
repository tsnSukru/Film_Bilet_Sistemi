import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class YetkiliGirisi {

	public JFrame frame;
	private JTextField tfKullaniciAdi;
	private JTextField tfSifre;

	Veritabani veritabani = new Veritabani();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					YetkiliGirisi window = new YetkiliGirisi();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public YetkiliGirisi() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		tfKullaniciAdi = new JTextField("yetkili1");
		tfKullaniciAdi.setBounds(200, 111, 201, 19);
		frame.getContentPane().add(tfKullaniciAdi);
		tfKullaniciAdi.setColumns(10);

		tfSifre = new JTextField("yetkili");
		tfSifre.setColumns(10);
		tfSifre.setBounds(200, 169, 201, 19);
		frame.getContentPane().add(tfSifre);

		JLabel lblNewLabel = new JLabel("Kullanici Adi");
		lblNewLabel.setBounds(200, 86, 156, 19);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Sifre");
		lblNewLabel_1.setBounds(200, 140, 196, 19);
		frame.getContentPane().add(lblNewLabel_1);

		JButton btnGiris = new JButton("Giris");
		btnGiris.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Connection connection = DriverManager.getConnection(veritabani.connectionString,
							veritabani.username, veritabani.password);
					PreparedStatement statement = connection.prepareStatement(
							"SELECT count(PK_YetkilID) as varmi FROM tblyetkililer WHERE PK_YetkilID='"
									+ tfKullaniciAdi.getText() + "'" + "AND YetkiliSifre='" + tfSifre.getText() + "'");
					java.sql.ResultSet rs = statement.executeQuery();
					while (rs.next()) {
						if (rs.getInt("varmi") == 1) {
							JOptionPane.showMessageDialog(frame, "Giris Basarili");
							UyelikIslemleri frm1 = new UyelikIslemleri();
							frm1.frame.setVisible(true);
							frame.dispose();
						} else {
							JOptionPane.showMessageDialog(frame, "Yanlis Kullanici Adi Veya Sifre");
						}
					}
					connection.close();
				} catch (SQLException a) {
					a.printStackTrace();

				}
			}
		});
		btnGiris.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(frame, "Giris Basarili");
			}
		});
		btnGiris.setBounds(260, 220, 85, 21);
		frame.getContentPane().add(btnGiris);

		JLabel lbYetkiliGirisi = new JLabel("Kullanici Girisi");
		lbYetkiliGirisi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				KullaniciGirisi frm1 = new KullaniciGirisi();
				frm1.frame.setVisible(true);
				frame.dispose();
			}
		});
		lbYetkiliGirisi.setBounds(271, 266, 85, 21);
		frame.getContentPane().add(lbYetkiliGirisi);
	}

}
