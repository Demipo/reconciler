package com.steady.reconciler;

import com.steady.reconciler.service.CoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class ReconcilerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReconcilerApplication.class, args);
		CoreService.readSpreadSheets();

	}

}
