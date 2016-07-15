/*package com.bperp.word.word;
package jacob;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.bperp.word.util.StringUtils;



public class WordWriter {

	private WordOperator word;
	
	private String path;

	*//**
	 * 
	 * @param filePath
	 *//*
	public WordWriter(String filePath) {
		word = new WordOperator();
		word.openDocument(filePath);
	}

	*//**
	 * 
	 * @param input
	 * @param filePath
	 * @param fileName
	 * @throws Exception
	 *//*
	public WordWriter(InputStream input, String filePath, String fileName)
			throws Exception {
		path = saveAsDocFile(input, filePath, fileName);
		word = new WordOperator();
		word.openDocument(path);
	}

	*//**
	 * 将word文档输入流保存为本地得到word文件
	 * 
	 * @param input
	 * @param filePath
	 * @param fileName
	 * @throws Exception
	 *//*
	@SuppressWarnings("unused")
	private String saveAsDocFile(InputStream input, String filePath,
			String fileName) throws Exception {
		if (!StringUtils.isValidateString(filePath)
				|| !StringUtils.isValidateString(fileName)) {
			throw new Exception("The filePath or fileName is error");
		}
		if (input == null) {
			throw new Exception("InputStream is null");
		}
		File file = new File(filePath);

		if (!file.exists()) {
			file.mkdirs();
		}
		filePath = validateFilePath(filePath);
		fileName = getRandomFileName(fileName);
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new BufferedInputStream(input);
			out = new BufferedOutputStream(new FileOutputStream(filePath
					+ fileName));
			byte[] b = new byte[1024];
			for (int p = 0; (p = in.read(b)) != -1;) {
				out.write(b);
				out.flush();
			}
		} finally {
			if (out != null) {
				out.close();
			}
			if (in != null) {
				in.close();
			}
		}
		return filePath + fileName;
	}

	*//**
	 * 验证Word文件路径
	 * 
	 * @param filePath
	 * @return
	 *//*
	private String validateFilePath(String filePath) {
		if ((filePath.lastIndexOf("\\\\") == -1)
				&& (filePath.lastIndexOf("/") == -1)) {
			filePath = filePath + "/";
		}
		return filePath;
	}

	*//**
	 * 生成一个新的文件名（保证文件名不相同）
	 * 
	 * @param fileName
	 * @return
	 *//*
	private String getRandomFileName(String fileName) {
		fileName = fileName + "_"
				+ new SimpleDateFormat("yyyyMMddHHmmssZ").format(new Date())
				+ ".doc";
		return fileName;
	}

	*//**
	 * replaceText
	 * 
	 * @param map
	 *//*
	public synchronized  void ReplacementContent(Map<String, String> map) {
		if (map == null) {
			return;
		}
		Set<String> keys = map.keySet();
		Iterator<String> it = keys.iterator();
		while (it.hasNext()) {
			String key = it.next();
			word.replaceAllText(OfficeUtils.prefixReplace
					+ StringUtils.validateString(key), map.get(key));
		}
	}

	*//**
	 * add details
	 * 
	 * @param values
	 *//*
	public synchronized  void insertContextsInRow(List<Map<String, String>> values,
			int tableIndex) {
		if (tableIndex <= 1) {
			tableIndex = 1;
		}
		if (values == null || values.size() <= 0) {
			return;
		}
		int[] p = null;
		Map<String, String> m = values.get(0);
		Set<String> keys = m.keySet();
		Iterator<String> it = keys.iterator();
		Map<String,Integer> indexs=new HashMap<String,Integer>();
		while (it.hasNext()) {
			String str = it.next();
			int[] a = word.getTableCellPostion(OfficeUtils.prefixReplace
					+ StringUtils.validateString(str), tableIndex);
			if (a != null && a[0] != 0) {
				p = a;
				indexs.put(str, a[1]);
			}

		}
		if (p != null && p[0] != 0) {
			for (int i = 1; i < values.size(); i++) {
				word.addTableRow(tableIndex, p[0]);// 在表格插入行数
			}
		}
		Set<String> ikeys=indexs.keySet();
		Iterator<String> it2 = ikeys.iterator();
		while (it2.hasNext()) {
			int row = p[0];
			String str = it2.next();
			for (Map<String, String> map : values) {
				word.putTxtToCell(tableIndex, row, indexs.get(str), map.get(str));
				row++;
			}
		}

	}

	*//**
	 * close document
	 *//*
	public synchronized  void close() {
		word.closeDocument();
		word.close();
	}

	*//**
	 * 依据Word文件完整路径删除文件
	 * 
	 * @param path
	 * @throws Exception
	 *//*
	public synchronized  void deleteWordFile(String path) throws Exception {
		if (!StringUtils.isValidateString(path)) {
			return;
		}
		File f = new File(path);
		if (!f.exists()) {
			throw new Exception("The file is not exists");
		}
		f.delete();
	}

	*//***
	 * 将新生成Word文件转化为输入流
	 *//*
	@SuppressWarnings("unused")
	public synchronized void writeOutputStream(OutputStream out)throws Exception{
		if(!StringUtils.isValidateString(path)){
			return ;
		}
		if(out==null){
			return;
		}
		File f=new File(path);
		if(!f.exists()){
			return ;
		}
		InputStream in=null;
	    OutputStream os=null;
	    try{
	    	in=new BufferedInputStream(new FileInputStream(f));
		    os=new BufferedOutputStream(out);
		    byte[] b=new byte[1024];
		    for(int k=0;(k=in.read(b))!=-1;){
		    	os.write(b);
		    	os.flush();
		    }
	    }catch(Exception e){
	    	throw e;
	    }finally{
	    	if(os!=null){
	    		os.close();
	    	}
	    	if(in!=null){
	    		in.close();
	    	}
	    }
	}
	*//**
	 * 
	 * @param args
	 * @throws Exception
	 *//*
	public static void main(String args[]) throws Exception {
		InputStream in = new FileInputStream("d:\\aaa.doc");
		String path = "d:\\qq";
		String fileName = "aaa";
		WordWriter writer=null;
		try{
	     writer = new WordWriter(in, path, fileName);
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("p21", "上海商哲");
		map1.put("p12", "1550");
		map1.put("subject", "1550");
		//map1.put("p12", "1550");
		writer.ReplacementContent(map1);
		List<Map<String, String>> values = new ArrayList<Map<String, String>>();
		for (int i = 0; i < 10; i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("productcode", "111111111111");
			map.put("productname", "222222222222");
			map.put("quoteprice", "333333333333");
			map.put("subtotal", "444444444444");
			map.put("quantity", "555555555555");
			map.put("6assssaaaaa", "666666666666");
			values.add(map);
		}
		
		writer.insertContextsInRow(values, 1);
		}finally{
			if(writer!=null){
				writer.close();
			}
		}
		writer.writeOutputStream(new FileOutputStream("d:\\fff.doc"));
	}
}
*/