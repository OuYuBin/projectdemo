package extractors;
import java.io.File;
import java.io.FileInputStream;
import org.textmining.text.extraction.WordExtractor;

public class WordReader {
 public static String readDoc(String doc) throws Exception {
  // 创建输入流读取doc文件
  FileInputStream in = new FileInputStream(new File(doc));
  WordExtractor extractor = null;
  String text = null;
  // 创建WordExtractor
  extractor = new WordExtractor();
  // 对doc文件进行提取
  text = extractor.extractText(in);
  return text;
 }
 /**
  * @param args
  */
 public static void main(String[] args) {
  // TODO Auto-generated method stub
        try{
         String text = WordReader.readDoc("D:\\word\\Web开发接口文档（1）.doc");
         System.out.println(text);
        }catch(Exception ex){
         ex.printStackTrace();
        }
 }
} 
