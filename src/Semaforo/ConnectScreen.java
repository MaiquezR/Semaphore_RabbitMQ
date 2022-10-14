package Semaforo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ServicioConexion.ServicioConex;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class ConnectScreen extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConnectScreen frame = new ConnectScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ConnectScreen() {
		setTitle("SI");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 245, 155);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("Conectar");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 32));
		btnNewButton.setBounds(0, 0, 229, 116);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ServicioConex conexion = new ServicioConex();
				
				conexion.conectarServer();
				
				new Semaforo(conexion);
				
				dispose();
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnNewButton);
	}

}
