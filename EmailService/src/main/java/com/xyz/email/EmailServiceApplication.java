package com.xyz.email;

/* EMAIL API to send confirmation email to clients who purchased product
 * Create a simple API to send email from  host gmail account to a fixed (client email) –> Done
 * Every time order record is going to get updated, emailservice  will automatically Call orderservice  - fetch "cstomer email" from orderservice, and send email using email service
 * html email will contain order details, approximate delivery date ( need to get purchased date from orderservice) and  a 10% discount coupon for their next order, and may be current offers. 
*/

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages ="com.xyz")
public class EmailServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailServiceApplication.class, args);
	}

}
