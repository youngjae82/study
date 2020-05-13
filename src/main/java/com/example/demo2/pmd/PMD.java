package com.example.demo2.pmd;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.sourceforge.pmd.cli.PMDCommandLineInterface;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public class PMD {
	
	public static String getVersion() {
		String version = net.sourceforge.pmd.PMD_523.VERSION;
		if ("unknown".equals(version.toLowerCase())) {
			version = net.sourceforge.pmd.PMD_523.class.getSimpleName().replaceAll("PMD_", "");
		}
		
		return version;
	}
	
	public static List<String> analyze(Ruleset ruleset, List<String> files) throws Exception {
		return analyze(ruleset, files, true);
	}

	public static List<String> analyze(Ruleset ruleset, List<String> files, boolean pretty) throws Exception {
		if (files == null || files.isEmpty()) {
			return null;
		}
		
		String rulesetPath = copyRulesetToTempDir(ruleset);
		
		List<String> result = new ArrayList<String>();
		
		boolean shouldExcludeHeader = false;
		for (String file : files) {
			String reportPath = System.getProperty("java.io.tmpdir") + UUID.randomUUID().toString() + ".txt";
			
			String[] arguments = new String[] {"-d ", file, "-f", "csv", "-R", rulesetPath, "-reportfile", reportPath};
			
			PMDCommandLineInterface.run(arguments);

			File reportFile = new File(reportPath);
			List<String> report = FileUtils.readLines(reportFile, "UTF-8");
			/* Important : Please do not make a NPE defense logic to 'report' object. it must be throw up */
			if (shouldExcludeHeader) {
				report.remove(0);
			} else {
				shouldExcludeHeader = true;
			}
			result.addAll(report);
			reportFile.delete();
		}
		
		return pretty ? prettify(result) : result;
	}
	
	private static String copyRulesetToTempDir(Ruleset ruleset) throws IOException {
		String rulesetPath = System.getProperty("java.io.tmpdir") + UUID.randomUUID().toString() + ".xml";
		
		InputStream in = ruleset.openStream();
		FileOutputStream out = new FileOutputStream(new File(rulesetPath));
		
		IOUtils.copy(in, out);
		
		IOUtils.closeQuietly(in);
		IOUtils.closeQuietly(out);
		
		return rulesetPath;
	}
	
	private static List<String> prettify(List<String> rawResult) {
		List<String> prettyResult = new ArrayList<String>();
		
		// index 0 is useless header. so this prettify must be starts from index 1.
		for (int i=1, j=rawResult.size(); i<j; i++) {
			rawResult.set(i, removeDoubleQuoteAtFirstAndLast(rawResult.get(i)));
			String[] columns = rawResult.get(i).split("\",\"");
			int problemNumber = Integer.valueOf(columns[ResultColumn.Problem.getIndex()]);
			boolean shouldPrintFileInfo = (problemNumber == 1);
			if (shouldPrintFileInfo) {
				String file = columns[ResultColumn.File.getIndex()];
				prettyResult.add(file);
			}

			int line = Integer.valueOf(columns[ResultColumn.Line.getIndex()]);
			String description = columns[ResultColumn.Description.getIndex()];
			String ruleset = columns[ResultColumn.Rule_Set.getIndex()];
			String rule = columns[ResultColumn.Rule.getIndex()];
			prettyResult.add(String.format("\t%d Line - %s [%s | %s]", line, description, ruleset, rule));
		}
		
		return prettyResult;
	}
	
	private static String removeDoubleQuoteAtFirstAndLast(String value) {
		return value.substring(1, value.length()-1);
	}
	
}