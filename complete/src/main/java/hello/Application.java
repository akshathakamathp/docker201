package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import redis.clients.jedis.Jedis;

@SpringBootApplication
@RestController
public class Application {

	@RequestMapping("/")
	public String home() {
		return "Hello Docker World";
	}

	@GetMapping("/redis")
	public String getRedisData() {

		try (Jedis jedis = new Jedis("srvstfop52v", 6379, 5000)) {
			System.out.println("Connection successful");
			System.out.println("Getting response from the server: " + jedis.ping());
			String pingReply = jedis.ping();
			return "Getting response from the server: " + pingReply;
		} catch (Exception e) {
			e.printStackTrace();
			return e.getStackTrace().toString();
		}
		
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
