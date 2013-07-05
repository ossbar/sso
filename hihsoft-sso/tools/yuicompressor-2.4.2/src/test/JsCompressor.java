package com.hihsoft.sso.util;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;

import org.junit.Test;
import org.mozilla.javascript.ErrorReporter;
import org.mozilla.javascript.EvaluatorException;

import com.yahoo.platform.yui.compressor.CssCompressor;
import com.yahoo.platform.yui.compressor.JavaScriptCompressor;

public class JsCompressor{
	
	//要处理的目录
	//File dir=new File("D:\\workspace\\u8cerp\\WebRoot\\js");
	File dir=new File("D:/work/hihsoft-sso/WebContent/js_plugins");
	
	int linebreakpos = -1;
	boolean munge=true;
	boolean verbose=false;
	boolean preserveAllSemiColons=false;
	boolean disableOptimizations=false;
	@Test
	public void testMain() throws Exception{
		checkFile(dir);
		
	}
	public void checkFile(File file) throws Exception{

		if(file.getName().endsWith(".svn"))
			return;
		
		
		if(file.isFile()){
			jsZip(file);
			return;
		}
		
		File[] files=file.listFiles();
		if(files==null||files.length==0)
		return;
		
		for(File f:files){
			if(file.getName().endsWith(".svn"))
				return;
			if(file.isFile()){
				jsZip(file);
				continue;
			}
			checkFile(f);
		}
	}
	public void jsZip(File file) throws Exception{
		String fileName=file.getName();
		System.out.println(fileName);
		if(fileName.endsWith(".js")==false&&fileName.endsWith(".css")==false){
			return;
		}
		Reader in=new FileReader(file);
	
		
		String filePath=file.getAbsolutePath();
		File tempFile=new File(filePath+".tempFile");
		Writer out=new FileWriter(tempFile);
		if(fileName.endsWith(".js")){
			JavaScriptCompressor jscompressor=new JavaScriptCompressor(in, new ErrorReporter() {

	            public void warning(String message, String sourceName,
	                    int line, String lineSource, int lineOffset) {
	                if (line < 0) {
	                    System.err.println("\n[WARNING] " + message);
	                } else {
	                    System.err.println("\n[WARNING] " + line + ':' + lineOffset + ':' + message);
	                }
	            }

	            public void error(String message, String sourceName,
	                    int line, String lineSource, int lineOffset) {
	                if (line < 0) {
	                    System.err.println("\n[ERROR] " + message);
	                } else {
	                    System.err.println("\n[ERROR] " + line + ':' + lineOffset + ':' + message);
	                }
	            }

	            public EvaluatorException runtimeError(String message, String sourceName,
	                    int line, String lineSource, int lineOffset) {
	                error(message, sourceName, line, lineSource, lineOffset);
	                return new EvaluatorException(message);
	            }
	        });
			
			jscompressor.compress(out, linebreakpos, munge, verbose, preserveAllSemiColons, disableOptimizations);
		}else if(fileName.endsWith(".css")){
			 CssCompressor csscompressor = new CssCompressor(in);
			 csscompressor.compress(out, linebreakpos);
		}
		
		out.close();
		in.close();
		file.delete();
		tempFile.renameTo(file);
		tempFile.delete();
	}
	
	
}