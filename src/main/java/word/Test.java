package word;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.poi.POITextExtractor;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.extractor.ExtractorFactory;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;

/**
 * POI 读取 word 2003 和 word 2007 中文字内容的测试类<br />
 * @createDate 2009-07-25
 * @author Carl He
 */
public class Test {
	public static void main(String[] args) {
		try {
			/*InputStream is2 = new FileInputStream(new File("D:\\word\\51job_陈建泽(345462588).doc"));
			//ByteArrayInputStream bs=new ByteArrayInputStream(IOUtils.toByteArray(is));
		    POITextExtractor extractor2 = ExtractorFactory.createExtractor(is2);  
            System.out.println("All:"+extractor2.getText());  */
			
			////word 2003： 图片不会被读取
			InputStream is = new FileInputStream(new File("D:\\word\\Web开发接口文档（1）.doc"));
					
			WordExtractor ex = new WordExtractor(is);//is是WORD文件的InputStream 
			String text2003 = ex.getText();
			System.out.println("2003------->"+text2003);

			//word 2007 图片不会被读取， 表格中的数据会被放在字符串的最后
			OPCPackage opcPackage = POIXMLDocument.openPackage("D:\\word\\智联招聘_李红辰_中文_20160613_41190447.docx");
			POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
			String text2007 = extractor.getText();
			System.out.println("2007------->"+text2007);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}