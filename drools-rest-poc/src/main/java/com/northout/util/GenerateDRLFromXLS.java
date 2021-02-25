package com.northout.util;

import java.io.InputStream;

import org.drools.decisiontable.InputType;
import org.drools.decisiontable.SpreadsheetCompiler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenerateDRLFromXLS {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public void generateDRLFromXLS(String url) {

		InputStream is = getClass().getClassLoader().getResourceAsStream(url);
		SpreadsheetCompiler ssComp = new SpreadsheetCompiler();
		String s = ssComp.compile(is, InputType.XLS);
		logger.info("=== Begin generated DRL ===");
		logger.info(s);
		logger.info("=== End generated DRL ===");
	}

}
