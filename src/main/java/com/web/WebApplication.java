package com.web;

import com.web.domain.Board;
import com.web.domain.User;
import com.web.domain.enums.BoardType;
import com.web.repository.BoardRepository;
import com.web.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

@SpringBootApplication
public class WebApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner runner(UserRepository userRepository,
	                                BoardRepository boardRepository) { // Bean으로 생성된 메서드에 파라미터로 DI(의존관계 주입)
		// CommandLineRunner를 빈으로 등록한 후 UserRepository, BoardRepository를 주입받음
		return (args -> {
			User user = userRepository.save(User.builder()
					.name("havi")
					.password("test")
					.email("havi@gmail.com")
					.createdDate(LocalDateTime.now())
					.build());
			
			IntStream.rangeClosed(1, 200).forEach(index -> //IntStream : int를 스트림으로 다룰 수 있음
					boardRepository.save(Board.builder()
							.title("게시글" + index)
							.subTitle("순서" + index)
							.content("콘텐츠")
							.boardType(BoardType.free)
							.createdDate(LocalDateTime.now())
							.updatedDate(LocalDateTime.now())
							.user(user).build()
					));
		});
	}

}

