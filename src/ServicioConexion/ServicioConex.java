package ServicioConexion;

import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class ServicioConex {
	ConnectionFactory conexion;

	public ServicioConex() {
		conexion = new ConnectionFactory();
		try {
			enviarMensaje();
			recibirMensaje();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void conectarServer() {
		conexion.setHost("2620:9b::1935:d571");
		conexion.setUsername("Yun");
		conexion.setPassword("Admin123");
	
	}
	
	public void enviarMensaje() throws IOException, TimeoutException {

		try (Connection connection = conexion.newConnection();
				Channel channel = connection.createChannel()) {
				channel.queueDeclare("Josele", true, false, false, null);
				channel.basicPublish("", "Josele", null, "Soy asador".getBytes(StandardCharsets.UTF_8));
					
				System.out.println("He llegao");
					
			}
			
	}
	
	public void recibirMensaje() throws IOException, TimeoutException {
		
		Channel channel;
		Connection connection = conexion.newConnection();
		channel = connection.createChannel();
		channel.queueDeclare("Josele", true, false, false, null);
					
		DeliverCallback deliverCallback = (consumerTag, delivery) -> {
		System.out.println(new String(delivery.getBody(), "UTF-8"));
						
		};
					
		channel.basicConsume("Josele", true, deliverCallback, consumerTag -> { });

		
	}
	
	

}
