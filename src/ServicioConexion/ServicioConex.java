package ServicioConexion;

import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

import javax.swing.JLabel;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import Semaforo.Semaforo;

public class ServicioConex {
	ConnectionFactory conexion;
	boolean flipFlop =false;
	
	public ServicioConex() {
		conexion = new ConnectionFactory();
		
	}
	
	public void conectarServer() {
		try {
			conexion.setUsername("Yun");
			conexion.setPassword("Admin123");
			conexion.setHost("2620:9b::1935:d571");
		} catch (Exception e) {
			System.err.println(e);
		}
		
	}
	
	public void enviarMensaje() throws IOException, TimeoutException, InterruptedException {
			Connection connection = conexion.newConnection();
			Channel channel = connection.createChannel();
			channel.queueDeclare("Josele", true, false, false, null);
			channel.basicPublish("", "Josele", null, "Soy asador".getBytes(StandardCharsets.UTF_8));
	}
	
	public void recibirMensaje(JLabel verde, JLabel rojo) throws IOException, TimeoutException {
		Channel channel;
		Connection connection = conexion.newConnection();
		channel = connection.createChannel();
		channel.queueDeclare("Josele2", true, false, false, null);
					
		DeliverCallback deliverCallback = (consumerTag, delivery) -> {
							flop(verde, rojo);
		};
		
		channel.basicConsume("Josele2", true, deliverCallback, consumerTag -> { });
	}

	public void flop(JLabel verde, JLabel rojo) {
		if(flipFlop) {
			flipFlop=false;
			verde.setVisible(true);
			rojo.setVisible(false);

		}else {
			flipFlop=true;
			verde.setVisible(false);
			rojo.setVisible(true);	
	}
	
	}

}
