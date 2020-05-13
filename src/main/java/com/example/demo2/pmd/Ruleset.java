package com.example.demo2.pmd;

import java.io.InputStream;

public enum Ruleset {
	JAVA("/pmd-ruleset.xml"),
	BATCH_INTERFACE("/pmd-ruleset-batch.xml"),
	UI("/custom_UI.xml"),
	OI("/custom_OI.xml");
	
	String file;
	Ruleset(String file) {
		this.file = file;
	}
	
	InputStream openStream() {
		return getClass().getResourceAsStream(file);
	}
}
