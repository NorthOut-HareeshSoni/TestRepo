package com.northout.service.impl;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.northout.model.Product;
import com.northout.service.ProductRulesService;
import com.northout.util.Constants;
import com.northout.util.GenerateDRLFromXLS;

@Service
public class ProductRulesServiceImpl implements ProductRulesService{

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private KieContainer kieContainer;
	
	@Override
	public Integer applyProductDiscountValidationRules(Product productValidationRequest) {
		
		GenerateDRLFromXLS generateDRLFromXLS = new GenerateDRLFromXLS();
		generateDRLFromXLS.generateDRLFromXLS(Constants.PRODUCT_DISCOUNT_RULES_FILE);

		KieSession kieSession = kieContainer.newKieSession(Constants.KSESSION_PRODUCT_DISCOUNT_VALIDATION);

		kieSession.insert(productValidationRequest);

		try {
			kieSession.fireAllRules();
		} catch (Exception e) {
			logger.info(e.getMessage());
		} finally {
			kieSession.dispose();
		}
		
		return productValidationRequest.getDiscount();
	}

}
