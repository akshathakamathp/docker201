package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public String getRedisData(@RequestParam String key) {
		try (Jedis jedis = new Jedis("172.17.0.2", 6379, 5000)) {
			System.out.println("Connection successful");
			System.out.println("Getting response from the server: " + jedis.get(key));
			String pingReply = jedis.get(key);
			return "Getting response from the server: " + pingReply;
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		
	}
	
	@PutMapping("/redis")
	public String putRedisData(@RequestParam String key,@RequestParam String value) {
		try (Jedis jedis = new Jedis("172.17.0.2", 6379, 5000)) {
			System.out.println("Connection successful");
			jedis.set(key, value);
			String pingReply = jedis.ping();
			return "Getting response from the server: " + pingReply;
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
