package com.northout.controller;

import static com.northout.util.Constants.PRODUCT_DISCOUNT_VALIDATION_RULE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.northout.model.Product;
import com.northout.service.ProductRulesService;

@RestController
public class ProductRulesController {

	
	@Autowired
	private ProductRulesService productRulesService;
	
	@PostMapping(value = PRODUCT_DISCOUNT_VALIDATION_RULE)
	public ResponseEntity<Integer> applyProductDiscountValidation(
			@RequestBody Product product) {
		Integer discountValue = productRulesService
				.applyProductDiscountValidationRules(product);
		return new ResponseEntity<>(discountValue, HttpStatus.OK);

	}
	
	
}
