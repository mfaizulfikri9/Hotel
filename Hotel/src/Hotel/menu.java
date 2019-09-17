package Hotel;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.sql.*;

public class menu extends JFrame implements ActionListener
{   
   
    public menu()
    {
    	setTitle("Menu Utama New Hotel");
    	getContentPane().setBackground(SystemColor.controlHighlight);
    	setIconImage(Toolkit.getDefaultToolkit().getImage("gambar.png"));
        
       	setLocation(50,150);
    	setSize(400,340);
    	setVisible(true);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    	getContentPane().setLayout(null);
    	
    	JLabel Label0 = new JLabel("New Hotel");
    	getContentPane().add(Label0);
		Label0.setFont(new Font("Afroed Dizzy Yak", Font.PLAIN, 30));
		Label0.setBounds(160, 15, 150, 30);
    	
    	JMenuBar menuBar = new JMenuBar();
    	menuBar.setBounds(60, 62, 265, 143);
    	getContentPane().add(menuBar);
    	
    	JMenu kamar = new JMenu(" Kamar ");
    	kamar.setFont(new Font("Bernard MT Condensed", Font.PLAIN, 12));
    	kamar.addMouseListener(new MouseAdapter() {
    		public void mouseClicked(MouseEvent arg0) {
    			InfoKamar kamar = new InfoKamar();
                kamar.show();
                dispose();
    		}
    	});
    	kamar.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent arg0) {
    			InfoKamar kamar = new InfoKamar();
                kamar.show();
                dispose();
    		}
    	});
    	kamar.setHorizontalAlignment(SwingConstants.CENTER);
    	kamar.setHorizontalTextPosition(SwingConstants.CENTER);
    	kamar.setVerticalTextPosition(SwingConstants.BOTTOM);
    	kamar.setVerticalAlignment(SwingConstants.BOTTOM);
    	kamar.setIcon(new ImageIcon("kamarr.jpg"));
    	menuBar.add(kamar);
    	
    	JMenu tamu = new JMenu(" Tamu ");
    	tamu.setFont(new Font("Bernard MT Condensed", Font.PLAIN, 12));
    	tamu.addMouseListener(new MouseAdapter() {
    		public void mouseClicked(MouseEvent arg0) {
    			InfoTamu tamu = new InfoTamu();
    			tamu.show();
    			dispose();
    		}
    	});
    	tamu.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent arg0) {
    			InfoTamu tamu = new InfoTamu();
    			tamu.show();
    			dispose();
    		}
    	});
    	tamu.setVerticalTextPosition(SwingConstants.BOTTOM);
    	tamu.setVerticalAlignment(SwingConstants.BOTTOM);
    	tamu.setHorizontalTextPosition(SwingConstants.CENTER);
    	tamu.setHorizontalAlignment(SwingConstants.CENTER);
    	tamu.setIcon(new ImageIcon("guess.jpg"));
    	menuBar.add(tamu);
    	
    	JButton btnLogout = new JButton("Logout");
    	btnLogout.setBorder(null);
    	btnLogout.setContentAreaFilled(false);
    	btnLogout.setIcon(new ImageIcon("log.png"));
    	btnLogout.addMouseListener(new MouseAdapter() {
    		public void mouseClicked(MouseEvent e) {
    			JOptionPane.showMessageDialog(null, "Logout Success");
                 dispose();
    		}
    	});
    	btnLogout.setBounds(140, 230, 120, 48);
    	getContentPane().add(btnLogout); 			
		
    }
    
    public void actionPerformed1(ActionEvent e)
    {
    	Object x=e.getSource();
    }
    
    
    
  public static void main(String[] args)
    {
    	
	  menu test2 = new menu();
    	test2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

@Override
public void actionPerformed(ActionEvent arg0) {
	// TODO Auto-generated method stub
	
}
}