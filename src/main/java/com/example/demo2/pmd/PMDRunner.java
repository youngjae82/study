package com.example.demo2.pmd;

import java.util.List;

public class PMDRunner {

	public static List<String> analyzeJavaFiles(List<String> javaFiles) throws Exception {
		return PMD.analyze(Ruleset.JAVA, javaFiles);
	}
	
	public static List<String> analyzeBatchOrInterfaceFiles(List<String> batchOrInterfaceFiles) throws Exception {
		return PMD.analyze(Ruleset.BATCH_INTERFACE, batchOrInterfaceFiles);
	}
	
	public static List<String> analyzeXframeUIFiles(List<String> xframeUIFiles) throws Exception {
		return PMD.analyze(Ruleset.UI, xframeUIFiles);
	}
	
	public static List<String> analyzeXframeOIFiles(List<String> xframeOIFiles) throws Exception {
		return PMD.analyze(Ruleset.OI, xframeOIFiles);
	}

}
