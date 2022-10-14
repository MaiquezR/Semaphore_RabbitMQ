package ServicioConexion;

import com.rabbitmq.client.ConnectionFactory;

public class ServicioConex {
	ConnectionFactory conexion;

	public ServicioConex() {
		conexion = new ConnectionFactory();
	}
	
	public void conectarServer() {
		conexion.setHost("25.53.213.113:15672");
		
		
	}

}
