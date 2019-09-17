package Hotel;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Login extends JFrame implements ActionListener {

	private JLabel Label0 = new JLabel("Aplikasi untuk mengetahui data pada 'New Hotel'");
	private JButton btngambar = new JButton("");
	private JLabel Label1 = new JLabel("Username");
	private JLabel Label2 = new JLabel("Password");
	private JTextField txtuser = new JTextField();
	private JPasswordField txtpass = new JPasswordField(20);
	private JButton btnlogin = new JButton("Login");
	private JButton btnexit = new JButton("Exit");

	public Login() {
		setTitle("New Hotel");
		getContentPane().setBackground(SystemColor.white);
		setIconImage(Toolkit.getDefaultToolkit().getImage("gambar.png"));
		setLocation(500, 200);
		setSize(400, 320);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		getContentPane().setLayout(null);
		getContentPane().add(Label0);
		Label0.setFont(new Font("Afroed Dizzy Yak", Font.PLAIN, 22));
		Label0.setBounds(35, 10, 500, 30);
		btngambar.setBorder(null);
		btngambar.setVerticalAlignment(SwingConstants.BOTTOM);
		btngambar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btngambar.setHorizontalTextPosition(SwingConstants.CENTER);
		btngambar.setIcon(new ImageIcon("gambarr.png"));
		getContentPane().add(btngambar);
		btngambar.setBounds(new Rectangle(150, 50, 100, 100));
		btngambar.addActionListener(this);
		btngambar.setEnabled(true);
		getContentPane().add(Label1);
		Label1.setBounds(50, 165, 80, 20);
		getContentPane().add(txtuser);
		txtuser.setBounds(140, 165, 180, 20);
		txtuser.setToolTipText("Input Username");
		getContentPane().add(Label2);
		Label2.setBounds(50, 190, 80, 20);
		getContentPane().add(txtpass);
		txtpass.setBounds(140, 190, 180, 20);
		getContentPane().add(btnlogin);
		btnlogin.setBounds(90, 230, 90, 25);
		btnlogin.addActionListener(this);
		getContentPane().add(btnexit);
		btnexit.setBounds(200, 230, 90, 25);
		btnexit.addActionListener(this);

	}

	 public static void main(String[] args) {
         Login test = new Login();
         test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     }
     private String user = "",pass = "";
     public void actionPerformed (ActionEvent e){
         if (e.getSource() == btnlogin){
             user = txtuser.getText();
             pass = txtuser.getText();
             if (user.equals("wati") && pass.equals("wati")){
                 JOptionPane.showMessageDialog(null, "Login Success");
                 dispose();
                            
               menu masukmenu = new menu();
               masukmenu.show();
            
             }else{
                 JOptionPane.showMessageDialog(null, "Username and password Wrong");
                 txtuser.setText("");
                 txtpass.setText("");
                 txtuser.requestFocus(true);
             }
         }else if (e.getSource() == btnexit){
             JOptionPane.showMessageDialog(null, "Anda sudah keluar");
             System.exit(0);
             }
         }
     }