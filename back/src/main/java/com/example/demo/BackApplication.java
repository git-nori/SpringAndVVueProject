package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// @SpringBootApplicationは下記の2つを自動追加する
// @EnableAutoConfiguration => クラスパス設定、他のBean、およびさまざまなプロパティ設定に基づいてBeanの追加を開始するようにSpring Bootに指示する
// @Configuration => アプリケーションコンテキストのBean定義のソースとしてクラスにタグを付ける
@SpringBootApplication
@EnableJpaAuditing // JPA監査を有効にする
public class BackApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackApplication.class, args);
	}

}
