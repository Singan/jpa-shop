package jpabook.jpashop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpashopApplication {
	// 멤버 1-n 주문 - 주문상품 - 상품(도서,음반,영화) 카테고리
	public static void main(String[] args) {
		SpringApplication.run(JpashopApplication.class, args);
	}

}
