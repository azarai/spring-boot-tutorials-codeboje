package de.codeboje.tutorials.feignintroduction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;

import de.codeboje.tutorials.feignintroduction.model.Board;
import de.codeboje.tutorials.feignintroduction.model.Confirmation;
import de.codeboje.tutorials.feignintroduction.model.User;

@SpringBootApplication
@EnableFeignClients
public class FeignIntroductionApplication implements ApplicationRunner {

	@Autowired
	private KanbanClient kanbanClient;

	public static void main(String[] args) {
		SpringApplication.run(FeignIntroductionApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		String username = "testme5000";
		String password = "mysecretpassword";

		registerUser(username, password);

		final String token = loginUser(username, password);

		Board board = new Board();
		board.setName("my board");
		board = kanbanClient.createBoard(token, board);
		
		board.setName("my board renamed");
		kanbanClient.changeBoard(token, board.getId(), board);
		
		kanbanClient.listBoards(token).forEach(System.out::println);
		
		unregisterUser(password, token);
	}

	private void registerUser(String username, String password) {
		System.out.println("registering user " + username);
		String userId = kanbanClient.registerUser(new User(username, password));
		System.out.println("new user's id is: " + userId);
	}

	private String loginUser(String username, String password) {
		System.out.println("login user " + username);

		ResponseEntity<Void> response = kanbanClient.loginUser("Basic " + buildBasicAuth(username, password));

		System.out.println("User logged in");
		String token = response.getHeaders().getFirst("X-Auth-Token");
		System.out.println("Token is " + token);
		return token;
	}

	private void unregisterUser(String password, String token) {
		ResponseEntity<Void> response;

		System.out.println("unregister user");
		response = kanbanClient.unregisterUser(token, new Confirmation(password));
		System.out.println(response.getStatusCodeValue());
		System.out.println("user successfully unregistered");
	}

	private String buildBasicAuth(String user, String password) {
		String authString = user + ":" + password;
		System.out.println("auth string: " + authString);
		byte[] authEncBytes = Base64Utils.encode(authString.getBytes());
		return new String(authEncBytes);
	}
}
