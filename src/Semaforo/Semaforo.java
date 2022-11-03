package Semaforo;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ServicioConexion.ServicioConex;

import java.awt.GridLayout;
import javax.swing.JLabel;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Semaforo extends JFrame {

	private JPanel contentPane;
	ServicioConex conexion;

	/**
	 * Create the frame.
	 */
	public Semaforo(ServicioConex conexion) {
		setResizable(false);
		setTitle("CMASFORO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 339, 398);
		
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel(new ImageIcon("./res/rojo.png"), JLabel.CENTER);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel(new ImageIcon("./res/verde.png"), JLabel.CENTER);
		contentPane.add(lblNewLabel);
		
		try {
			conexion.recibirMensaje(lblNewLabel, lblNewLabel_1);
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (TimeoutException e1) {
			e1.printStackTrace();
		}
		
		JButton btnNewButton = new JButton("CAMBIAR");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conexion.flop(lblNewLabel, lblNewLabel_1);
					conexion.enviarMensaje();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (TimeoutException e1) {
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnNewButton);
		
		this.conexion = conexion;
		this.setVisible(true);
	}
	
	

}