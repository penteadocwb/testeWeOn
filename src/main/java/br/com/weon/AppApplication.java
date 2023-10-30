package br.com.weon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.weon.negocio.pool.PoolConsumidor;
import br.com.weon.negocio.pool.PoolProdutorChat;
import br.com.weon.negocio.pool.PoolProdutorEmail;
import br.com.weon.negocio.pool.PoolProdutorVoz;
import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@PostConstruct
	public void postConstruct() {
		new PoolProdutorChat().iniciar();
		new PoolProdutorEmail().iniciar();
		new PoolProdutorVoz().iniciar();
		new PoolConsumidor().iniciar();
	}

}
