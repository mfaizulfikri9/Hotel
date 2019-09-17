package Hotel;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;//event listener ada pada awt

import javax.swing.table.DefaultTableModel;//untuk membuat tabel

import java.sql.*;

public class InfoTamu extends JFrame implements ActionListener, ItemListener
{
	static JFrame f = new JFrame();
	JLabel lblid = new JLabel("ID Tamu");
	JLabel lblktp = new JLabel("No. KTP");
	JLabel lblnama = new JLabel("Nama Tamu");
	JLabel lblJK = new JLabel("Jenis Kelamin");
	JLabel lblalamat = new JLabel("Alamat");
	JLabel lbltelp = new JLabel("Nomor Telp");
	JLabel lblstatuskoneksi = new JLabel("Status koneksi ke database");

	static String[] JenKelComboBox = { "Laki-Laki", "Perempuan" };
	static JComboBox cmbJK = new JComboBox(JenKelComboBox);

	static JTextField txtid = new JTextField();
	static JTextField txtktp = new JTextField();
	static JTextField txtnama = new JTextField();
	static JTextField txttelp = new JTextField();
	static JTextField txtalamat = new JTextField();
	static JTextField txtstatuskoneksi = new JTextField();

	JButton btnkeluar = new JButton("Logout");
	JButton btnbatal = new JButton("Batal");
	JButton btnsimpan = new JButton("Simpan");
	JButton btncari = new JButton("Cari");
	JButton btnlihat = new JButton("Lihat Data");
	JButton btnHapus = new JButton("Hapus");
	JButton btnUpdate = new JButton("Update");

	static JTable tabel = new JTable();
	static DefaultTableModel tabMode;
	static private Object[][] dataTable = null;

	static String[] header = { "ID Tamu", "No KTP", "Nama", "Jenis Kelamin", "Nomor Telp", "Alamat" };
	JScrollPane tabelpenyewa = new JScrollPane();
	
	public InfoTamu() {
		setTitle("Data Tamu");
		getContentPane().setBackground(SystemColor.pink);
		setIconImage(Toolkit.getDefaultToolkit().getImage("gambar.png"));
		setLocation(20, 10);
		setSize(830, 660);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel lblHotel = new JLabel("Data Tamu 'New Hotel'");
		lblHotel.setFont(new Font("Afroed Dizzy Yak", Font.PLAIN, 24));
		lblHotel.setBounds(330, 11, 252, 40);
		getContentPane().add(lblHotel);
		getContentPane().add(lblid);
		getContentPane().add(txtid);
		getContentPane().add(lblktp);
		getContentPane().add(txtktp);
		getContentPane().add(lblnama);
		getContentPane().add(txtnama);
		getContentPane().add(lblalamat);
		getContentPane().add(txtalamat);
		getContentPane().add(lbltelp);
		getContentPane().add(txttelp);
		getContentPane().add(lblstatuskoneksi);
		getContentPane().add(txtstatuskoneksi);

		lblid.setBounds(new Rectangle(49, 73, 100, 20));
		txtid.setBounds(new Rectangle(190, 73, 100, 20));
		lblktp.setBounds(new Rectangle(49, 103, 100, 20));
		txtktp.setBounds(new Rectangle(190, 103, 100, 20));
		lblnama.setBounds(new Rectangle(49, 135, 100, 20));
		txtnama.setBounds(new Rectangle(190, 135, 200, 20));
		lblalamat.setBounds(new Rectangle(49, 197, 100, 20));
		txtalamat.setBounds(new Rectangle(190, 197, 200, 20));
		lbltelp.setBounds(new Rectangle(49, 228, 100, 20));
		txttelp.setBounds(new Rectangle(190, 228, 100, 20));
		lblstatuskoneksi.setBounds(new Rectangle(49, 580, 180, 20));
		txtstatuskoneksi.setBounds(new Rectangle(257, 580, 200, 20));
		txtstatuskoneksi.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtstatuskoneksi.setBackground(Color.WHITE);
		txtstatuskoneksi.setEnabled(false);
		
		Object[] row = { "ID Tamu", "No KTP", "Nama", "Jenis Kelamin","Nomor Telp", "Alamat" };
		tabMode = new DefaultTableModel(null, row);

		getContentPane().add(lblJK);
		lblJK.setBounds(new Rectangle(49, 166, 100, 21));
		cmbJK.setModel(new DefaultComboBoxModel(new String[] { "Laki-Laki", "Perempuan" }));

		getContentPane().add(cmbJK);
		cmbJK.setBounds(new Rectangle(190, 166, 131, 20));
		cmbJK.addItemListener(this);

		btnsimpan.setBorder(null);
		btnsimpan.setVerticalAlignment(SwingConstants.CENTER);
		btnsimpan.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnsimpan.setHorizontalTextPosition(SwingConstants.CENTER);
		btnsimpan.setIcon(new ImageIcon("save.png"));
		getContentPane().add(btnsimpan);
		btnsimpan.setBounds(new Rectangle(105, 495, 60, 62));
		btnsimpan.addActionListener(this);
		btnsimpan.setEnabled(true);

		btnbatal.setBorder(null);
		btnbatal.setVerticalAlignment(SwingConstants.CENTER);
		btnbatal.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnbatal.setHorizontalTextPosition(SwingConstants.CENTER);
		btnbatal.setIcon(new ImageIcon("cancel.png"));
		getContentPane().add(btnbatal);
		btnbatal.setBounds(new Rectangle(190, 495, 60, 62));
		btnbatal.addActionListener(this);
		btnbatal.setEnabled(true);

		btncari.setBorder(null);
		btncari.setVerticalAlignment(SwingConstants.CENTER);
		btncari.setVerticalTextPosition(SwingConstants.BOTTOM);
		btncari.setHorizontalTextPosition(SwingConstants.CENTER);
		btncari.setIcon(new ImageIcon("search.png"));
		getContentPane().add(btncari);
		btncari.setBounds(new Rectangle(275, 495, 60, 62));
		btncari.addActionListener(this);
		btncari.setEnabled(true);
		
		btnkeluar.setBorder(null);
		btnkeluar.setVerticalAlignment(SwingConstants.CENTER);
		btnkeluar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnkeluar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnkeluar.setIcon(new ImageIcon("log.png"));
		getContentPane().add(btnkeluar);
		btnkeluar.setBounds(new Rectangle(615, 495, 60, 62));
		btnkeluar.addActionListener(this);
		btnkeluar.setEnabled(true);
				
		btnlihat.setBorder(null);
		btnlihat.setVerticalAlignment(SwingConstants.CENTER);
		btnlihat.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnlihat.setHorizontalTextPosition(SwingConstants.CENTER);
		btnlihat.setIcon(new ImageIcon("look.png"));
		getContentPane().add(btnlihat);
		btnlihat.setBounds(new Rectangle(360, 495, 60, 62));
		btnlihat.addActionListener(this);
		btnlihat.setEnabled(true);
		
		btnHapus.setBorder(null);
		btnHapus.setVerticalAlignment(SwingConstants.CENTER);
		btnHapus.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnHapus.setHorizontalTextPosition(SwingConstants.CENTER);
		btnHapus.setIcon(new ImageIcon("delete.png"));
		getContentPane().add(btnHapus);
		btnHapus.setBounds(445, 495, 60, 62);
		btnHapus.addActionListener(this);
		btnHapus.setEnabled(true);
		
		btnUpdate.setBorder(null);
		btnUpdate.setVerticalAlignment(SwingConstants.CENTER);
		btnUpdate.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnUpdate.setHorizontalTextPosition(SwingConstants.CENTER);
		btnUpdate.setIcon(new ImageIcon("update.png"));
		getContentPane().add(btnUpdate);
		btnUpdate.setBounds(530, 495, 60, 62);
		btnUpdate.addActionListener(this);
		btnUpdate.setEnabled(true);
				
		tabelpenyewa.setViewportBorder(null);
		tabelpenyewa.setBorder(null);
		tabelpenyewa.setBounds(new Rectangle(49, 270, 701, 210));
		tabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				getData();
			}
		});
		tabel.setBorder(null);
		tabel.setGridColor(UIManager.getColor("Button.background"));
		tabel.setModel(new DefaultTableModel(new Object[][] {}, new String[] {"ID Tamu", "No KTP", "Nama", "Jenis Kelamin", "Nomor Telp","Alamat" }));
		getContentPane().add(tabelpenyewa);
		tabelpenyewa.setViewportView(tabel);
		
		InfoTamu.koneksiDataBase();

	}

	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnkeluar) {
			JOptionPane.showMessageDialog(null, "Keluar");
			dispose();
			menu masukmenu = new menu();
			masukmenu.show();

		}
		Object x = e.getSource();

		if (x == btnsimpan)
		{
			InfoTamu.simpanData();
			
		}

		if (x == btnbatal) {
			InfoTamu.Kosong();
			
		}

		if (x == btncari) {
			InfoTamu.cariData();	
			
		}

		if (x == btnlihat) {
			InfoTamu.bacaData();
			tabel.setModel(tabMode);

		}
		
		if (x == btnHapus) {
			InfoTamu.hapusdata();

		}

		if (x == btnUpdate) {
			InfoTamu.updatedata();

		} else {
			// sql
		}
	}

	public void itemStateChanged(ItemEvent e)
	{
		int index = cmbJK.getSelectedIndex();
		String str = JenKelComboBox[index];
	}

	public static void Kosong() {
		txtid.setText("");
		txtktp.setText("");
		txtnama.setText("");
		txtalamat.setText("");
		txttelp.setText("");
		txtstatuskoneksi.setText("");

		txtstatuskoneksi.setEnabled(false);
		txtid.requestFocus();
	}

	public static void koneksiDataBase() {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("driver loaded");
			
		} catch (ClassNotFoundException cnfe) {
			
			System.err.println(cnfe);
		}

		catch (Exception E) {
			E.printStackTrace();
		}

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/hotel","root", "");
			txtstatuskoneksi.setText("Database is Connecting, OK!");

		} catch (SQLException se) {
			txtstatuskoneksi.setText("Connection Failed");

		}

	}

	public static void simpanData() {
		int tid = Integer.parseInt(txtid.getText());
		String tktp = txtktp.getText();
		String tnama = txtnama.getText();
		String talamat = txtalamat.getText();
		String ttelp = txttelp.getText();
		String tjenkel = cmbJK.getSelectedItem().toString();
		
		{
			String sql = "INSERT INTO tamu VALUES('" + tid + "','" + tktp + "','" + tnama + "','" + tjenkel + "','" + ttelp + "','"	+ talamat + "')";
			if (txtid.getText().trim().equals("") && txtktp.getText().trim().equals("")) 
			{
				JOptionPane.showMessageDialog(f,"ID Tamu / No. KTP masih kosong", "simpan data", JOptionPane.WARNING_MESSAGE);
				txtid.requestFocus();
			} 
			else 
			{
				try 
				{
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hotel", "root", "");
					Statement stmt = conn.createStatement();
					stmt.executeUpdate(sql);
					txtid.setText("");
					txtktp.setText("");
					txtnama.setText("");
					txtalamat.setText("");
					txttelp.setText("");
					txtid.requestFocus();
					bacaData();//
					tabel.setModel(tabMode);
				} 
				catch (Exception exc) 
				{
					System.err.println("Salah:" + exc);
				}

			}
		}
	}

	public static void hapusdata() {
		String tid = txtid.getText();

		{
			String sql = "delete from tamu where idtamu='" + tid + "'";

			try {
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hotel", "root", "");
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(sql);
				txtid.setText("");
				txtktp.setText("");
				txtnama.setText("");
				txtalamat.setText("");
				txttelp.setText("");
				txtid.requestFocus();
				bacaData();
				tabel.setModel(tabMode);
			} 
			catch (Exception exc) 
			{
				System.err.println("Salah:" + exc);
			}

		}
	}

	public static void updatedata() {
		String tid = txtid.getText();
		String tnoktp = txtktp.getText();
		String tnama = txtnama.getText();
		String talamat = txtalamat.getText();
		String ttelp = txttelp.getText();
		String tjenkel = cmbJK.getSelectedItem().toString();
		
		{
			String sql = "update tamu set noktp='" + tnoktp + "', nama='"
					+ tnama + "', telepon='" + ttelp + "', alamat='" + talamat
					+ "',  jeniskelamin='" + tjenkel + "' where idtamu='" + tid
					+ "'";

			try {
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hotel", "root", "");
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(sql);
				txtid.setText("");
				txtktp.setText("");
				txtnama.setText("");
				txtalamat.setText("");
				txttelp.setText("");
				txtid.requestFocus();
				bacaData();
				tabel.setModel(tabMode);
			} 
			catch (Exception exc) 
			{
				System.err.println("Salah:" + exc);
			}

		}
	}

	public static void bacaData() {
		try 
		{
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hotel", "root", "");

			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery("select * from tamu");

			tabMode = new DefaultTableModel(null, header);

			ResultSetMetaData meta = (ResultSetMetaData) rs.getMetaData();
			int col = meta.getColumnCount();
			int baris = 0;
			while (rs.next()) {
				baris = rs.getRow();
			}

			dataTable = new Object[baris][col];
			int x = 0;
			rs.beforeFirst();
			while (rs.next()) {
				dataTable[x][0] = rs.getString("idtamu");
				dataTable[x][1] = rs.getString("noktp");
				dataTable[x][2] = rs.getString("nama");
				dataTable[x][3] = rs.getString("jeniskelamin");
				dataTable[x][4] = rs.getString("telepon");
				dataTable[x][5] = rs.getString("alamat");

				x++;
		}
			tabMode = new DefaultTableModel(dataTable, header);
		} 
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, e);
		}
	}

	public static void cariData() {
		try 
		{
			String cari = JOptionPane.showInputDialog("Masukan ID Tamu yang dicari:");
			
			String queryCari = "select * from tamu where idtamu='" + cari + "'";

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hotel", "root", "");

			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(queryCari);
			if (rs.next()) {

				txtid.setText(rs.getString(1));
				txtktp.setText(rs.getString(2));
				txtnama.setText(rs.getString(3));
				cmbJK.setSelectedItem(rs.getString(4));
				txttelp.setText(rs.getString(5));
				txtalamat.setText(rs.getString(6));

			} 
			else 
			{
				JOptionPane.showMessageDialog(null, "ID Tamu tidak ada", "Info...", JOptionPane.INFORMATION_MESSAGE);
			}
		}

		catch (Exception exc) 
		{
			System.err.println("Salah:" + exc);
		}

	}

	public static void getData() {

		Kosong();
	    txtid.setText(tabel.getValueAt(tabel.getSelectedRow(),0).toString());
	    txtktp.setText(tabel.getValueAt(tabel.getSelectedRow(),1).toString());
	    txtnama.setText(tabel.getValueAt(tabel.getSelectedRow(),2).toString());
	    cmbJK.setSelectedItem(tabel.getValueAt(tabel.getSelectedRow(),3).toString());
	    txttelp.setText(tabel.getValueAt(tabel.getSelectedRow(),4).toString());
	    txtalamat.setText(tabel.getValueAt(tabel.getSelectedRow(),5).toString());
	    	    
	}

	public static void main(String[] args) {
		InfoTamu test1 = new InfoTamu();
		test1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}