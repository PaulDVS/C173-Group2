package order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
		// validation check by spring boot : check password
		// @Column(columnDefinition = "VARCHAR(60) CHECK (status IN ('DONE', 'PENDING')))
		// private String orderStatus
	}

}
