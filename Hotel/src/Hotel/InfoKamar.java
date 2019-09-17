package Hotel;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

import javax.swing.table.DefaultTableModel;

import java.sql.*;

public class InfoKamar extends JFrame implements ActionListener, ItemListener
{
	static JFrame f = new JFrame();
	JLabel lblno = new JLabel("No. Kamar");
	JLabel lbltipe = new JLabel("Tipe Kamar");
	JLabel lbltarif = new JLabel("Tarif Kamar");
	JLabel lblketerangan = new JLabel("keterangan");
	JLabel lblstatuskoneksi = new JLabel("Status koneksi ke database");
	
	static String[] tipekamar = { "Standard Room", "Premium Room", "Junior Suite Room", "Suite Room" };
	static JComboBox cmbtipe = new JComboBox(tipekamar);

	static JTextField txtno = new JTextField();
	static JTextField txtidtipe = new JTextField();
	static JTextField txttarif = new JTextField();
	static JTextArea txtketerangan = new JTextArea();
	static JTextField txtstatuskoneksi = new JTextField();
	static JTextField txtsql = new JTextField();
	
	JButton btnkeluar = new JButton("Exit");
	JButton btnbatal = new JButton("Cancel");
	JButton btnsimpan = new JButton("Save");
	JButton btncari = new JButton("Search");
	JButton btnlihat = new JButton("Look Data");
	JButton btnHapus = new JButton("Delete");
	JButton btnUpdate = new JButton("Update");

	static JTable tabel = new JTable();
	static DefaultTableModel tabMode;
	static private Object[][] dataTable = null;

	static String[] header = { "No. Kamar", "Tipe Kamar", "Tarif Kamar", "Keterangan" };
	JScrollPane tabelkamar = new JScrollPane();
	
	public InfoKamar() {
		setTitle("Data Kamar");
		getContentPane().setBackground(SystemColor.LIGHT_GRAY);
		setIconImage(Toolkit.getDefaultToolkit().getImage("gambar.png"));
		setLocation(20, 10);
		setSize(830, 660);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel lblHotel = new JLabel("Data Kamar 'New Hotel'");
		lblHotel.setFont(new Font("Afroed Dizzy Yak", Font.PLAIN, 24));
		lblHotel.setBounds(330, 11, 252, 40);
		getContentPane().add(lblHotel);
		getContentPane().add(lblno);
		getContentPane().add(txtno);
		getContentPane().add(lbltipe);
		getContentPane().add(cmbtipe);
		getContentPane().add(lbltarif);
		getContentPane().add(txttarif);
		getContentPane().add(lblketerangan);
		getContentPane().add(txtketerangan);
		getContentPane().add(lblstatuskoneksi);
		getContentPane().add(txtstatuskoneksi);
		getContentPane().add(txtidtipe);

		lblno.setBounds(new Rectangle(49, 73, 100, 20));
		txtno.setBounds(new Rectangle(190, 73, 100, 20));
		lbltipe.setBounds(new Rectangle(49, 103, 100, 20));
		cmbtipe.setBounds(new Rectangle(190, 103, 200, 20));
		lbltarif.setBounds(new Rectangle(49, 133, 100, 20));
		txttarif.setBounds(new Rectangle(190, 133, 200, 20));
		lblketerangan.setBounds(new Rectangle(49, 163, 100, 20));
		txtketerangan.setBounds(new Rectangle(190, 163, 300, 60));
		txtidtipe.setBounds(new Rectangle(190, 250, 200, 50));
		lblstatuskoneksi.setBounds(new Rectangle(49, 580, 180, 20));
		txtstatuskoneksi.setBounds(new Rectangle(257, 580, 200, 20));
		txtstatuskoneksi.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtstatuskoneksi.setBackground(Color.WHITE);
		
		txtketerangan.setLineWrap(true);
		txtketerangan.setWrapStyleWord(true);
		
		txtidtipe.setVisible(false);
		txtidtipe.setEnabled(false);
		txttarif.setEnabled(false);
		txtketerangan.setEnabled(false);
		txtstatuskoneksi.setEnabled(false);
		
		cmbtipe.setModel(new DefaultComboBoxModel(new String[] { "Pilih Tipe", "Single Room", "Twin Room", "Double Room", "Family Room" }));
		cmbtipe.addItemListener(this);
		
		Object[] row = { "No. Kamar", "Tipe Kamar", "Tarif Kamar", "keterangan" };
		tabMode = new DefaultTableModel(null, row);

		btnsimpan.setBorder(null);
		btnsimpan.setVerticalAlignment(SwingConstants.BOTTOM);
		btnsimpan.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnsimpan.setHorizontalTextPosition(SwingConstants.CENTER);
		btnsimpan.setIcon(new ImageIcon("save.png"));
		getContentPane().add(btnsimpan);
		btnsimpan.setBounds(new Rectangle(105, 495, 60, 62));
		btnsimpan.addActionListener(this);
		btnsimpan.setEnabled(true);

		btnbatal.setBorder(null);
		btnbatal.setVerticalAlignment(SwingConstants.BOTTOM);
		btnbatal.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnbatal.setHorizontalTextPosition(SwingConstants.CENTER);
		btnbatal.setIcon(new ImageIcon("cancel.png"));
		getContentPane().add(btnbatal);
		btnbatal.setBounds(new Rectangle(190, 495, 60, 62));
		btnbatal.addActionListener(this);
		btnbatal.setEnabled(true);

		btncari.setBorder(null);
		btncari.setVerticalAlignment(SwingConstants.BOTTOM);
		btncari.setVerticalTextPosition(SwingConstants.BOTTOM);
		btncari.setHorizontalTextPosition(SwingConstants.CENTER);
		btncari.setIcon(new ImageIcon("search.png"));
		getContentPane().add(btncari);
		btncari.setBounds(new Rectangle(275, 495, 60, 62));
		btncari.addActionListener(this);
		btncari.setEnabled(true);
		
		btnkeluar.setBorder(null);
		btnkeluar.setVerticalAlignment(SwingConstants.BOTTOM);
		btnkeluar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnkeluar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnkeluar.setIcon(new ImageIcon("log.png"));
		getContentPane().add(btnkeluar);
		btnkeluar.setBounds(new Rectangle(530, 495, 60, 62));
		btnkeluar.addActionListener(this);
		btnkeluar.setEnabled(true);
				
		btnlihat.setBorder(null);
		btnlihat.setVerticalAlignment(SwingConstants.BOTTOM);
		btnlihat.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnlihat.setHorizontalTextPosition(SwingConstants.CENTER);
		btnlihat.setIcon(new ImageIcon("look.png"));
		getContentPane().add(btnlihat);
		btnlihat.setBounds(new Rectangle(360, 495, 60, 62));
		btnlihat.addActionListener(this);
		btnlihat.setEnabled(true);
		
		btnHapus.setBorder(null);
		btnHapus.setVerticalAlignment(SwingConstants.BOTTOM);
		btnHapus.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnHapus.setHorizontalTextPosition(SwingConstants.CENTER);
		btnHapus.setIcon(new ImageIcon("delete.png"));
		getContentPane().add(btnHapus);
		btnHapus.setBounds(445, 495, 60, 62);
		btnHapus.addActionListener(this);
		btnHapus.setEnabled(true);
		
		/*btnUpdate.setBorder(null);
		btnUpdate.setVerticalAlignment(SwingConstants.BOTTOM);
		btnUpdate.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnUpdate.setHorizontalTextPosition(SwingConstants.CENTER);
		btnUpdate.setIcon(new ImageIcon("update.png"));
		getContentPane().add(btnUpdate);
		btnUpdate.setBounds(530, 495, 60, 62);
		btnUpdate.addActionListener(this);
		btnUpdate.setEnabled(true);*/
				
		tabelkamar.setViewportBorder(null);
		tabelkamar.setBorder(null);
		tabelkamar.setBounds(new Rectangle(49, 255, 701, 225));
		tabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				getData();
			}
		});
		tabel.setBorder(null);
		tabel.setGridColor(UIManager.getColor("Button.background"));
		tabel.setModel(new DefaultTableModel(new Object[][] {}, new String[] {"ID Tamu", "No KTP", "Nama", "Jenis Kelamin", "Nomor Telp","Alamat" }));
		getContentPane().add(tabelkamar);
		tabelkamar.setViewportView(tabel);
		
		InfoKamar.koneksiDataBase();

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
			InfoKamar.simpanData();
			
		}

		if (x == btnbatal) {
			InfoKamar.Kosong();
			
		}

		if (x == btncari) {
			InfoKamar.cariData();	
			
		}

		if (x == btnlihat) {
			InfoKamar.bacaData();
			tabel.setModel(tabMode);

		}
		
		if (x == btnHapus) {
			InfoKamar.hapusdata();

		}

		/*if (x == btnUpdate) {
			InfoKamar.updatedata();

		} else {
			// sql
		}*/
	}

	public void itemStateChanged(ItemEvent e)
	{
		int index = cmbtipe.getSelectedIndex();
		String str = tipekamar[index];
		String tipe = cmbtipe.getSelectedItem().toString();
		try 
		{
			
			String queryCari = "select * from tipe where tipekamar='" + tipe + "'";

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hotel", "root", "");

			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(queryCari);
			if (rs.next()) {

				txtidtipe.setText(rs.getString(1));
				txttarif.setText(rs.getString(3));
				txtketerangan.setText(rs.getString(4));

			} 
			else 
			{
				
			}
		}

		catch (Exception exc) 
		{
			System.err.println("Salah:" + exc);
		}
		
	}
	
	public static void Kosong() {
		txtno.setText("");
		txttarif.setText("");
		txtketerangan.setText("");
		
		txtstatuskoneksi.setEnabled(false);
		txtno.requestFocus();
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
		String tno = txtno.getText();
		String ttipe = txtidtipe.getText();
		
		{
			String sql = "INSERT INTO kamar VALUES('" + tno + "','" + ttipe + "')";
			//txtsql.setText(sql);
			if (txtno.getText().trim().equals("") && txtidtipe.getText().trim().equals("")) 
			{
				JOptionPane.showMessageDialog(f,"No. Kamar / Tipe Kamar masih kosong", "simpan data", JOptionPane.WARNING_MESSAGE);
				txtno.requestFocus();
			} 
			else 
			{
				try 
				{
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hotel", "root", "");
					Statement stmt = conn.createStatement();
					stmt.executeUpdate(sql);
					txtno.setText("");
					txttarif.setText("");
					txtketerangan.setText("");
					txtidtipe.setText("");
					txtno.requestFocus();
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
		String tid = txtno.getText();

		{
			String sql = "delete from kamar where nokamar='" + tid + "'";

			try {
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hotel", "root", "");
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(sql);
				txtno.setText("");
				txtidtipe.setText("");
				txttarif.setText("");
				txtketerangan.setText("");
				txtno.requestFocus();
				bacaData();
				tabel.setModel(tabMode);
			} 
			catch (Exception exc) 
			{
				System.err.println("Salah:" + exc);
			}

		}
	}

	/*public static void updatedata() {
		String tno = txtno.getText();
		String ttipe = txtidtipe.getText();
				
		{
			String sql = "update kamar set idtipe='" + ttipe + "' where nokamar='" + tno
					+ "'";

			try {
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hotel", "root", "");
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(sql);
				txtno.setText("");
				txtidtipe.setText("");
				txttarif.setText("");
				txtketerangan.setText("");
				txtno.requestFocus();
				bacaData();
				tabel.setModel(tabMode);
			} 
			catch (Exception exc) 
			{
				System.err.println("Salah:" + exc);
			}

		}
	}*/

	public static void bacaData() {
		try 
		{
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hotel", "root", "");

			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery("select kamar.nokamar, tipe.tipekamar, tipe.tarif, tipe.keterangan from kamar, tipe where kamar.idtipe = tipe.idtipe");

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
				dataTable[x][0] = rs.getString("nokamar");
				dataTable[x][1] = rs.getString("tipekamar");
				dataTable[x][2] = rs.getString("tarif");
				dataTable[x][3] = rs.getString("keterangan");

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
			String cari = JOptionPane.showInputDialog("Masukan No. Kamar yang dicari:");
			
			String queryCari = "select kamar.nokamar, tipe.tipekamar, tipe.tarif, tipe.keterangan from kamar, tipe where kamar.idtipe = tipe.idtipe and nokamar='" + cari + "'";

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hotel", "root", "");

			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(queryCari);
			if (rs.next()) {

				txtno.setText(rs.getString(1));
				cmbtipe.setSelectedItem(rs.getString(2));
				txttarif.setText(rs.getString(3));
				txtketerangan.setText(rs.getString(4));

			} 
			else 
			{
				JOptionPane.showMessageDialog(null, "No Kamar tidak ada", "Info...", JOptionPane.INFORMATION_MESSAGE);
			}
		}

		catch (Exception exc) 
		{
			System.err.println("Salah:" + exc);
		}

	}

	public static void getData() {

		Kosong();
	    txtno.setText(tabel.getValueAt(tabel.getSelectedRow(),0).toString());
	    cmbtipe.setSelectedItem(tabel.getValueAt(tabel.getSelectedRow(),1).toString());
	    txttarif.setText(tabel.getValueAt(tabel.getSelectedRow(),2).toString());
	    txtketerangan.setText(tabel.getValueAt(tabel.getSelectedRow(),3).toString());


	}

	public static void main(String[] args) {
		InfoKamar test1 = new InfoKamar();
		test1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}