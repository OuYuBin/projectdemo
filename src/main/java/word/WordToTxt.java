package word;
import java.io.FileInputStream;  
import java.io.FileNotFoundException;  
import java.io.IOException;  
  
import org.apache.poi.hwpf.HWPFDocument;    
import org.apache.poi.hwpf.usermodel.Paragraph;    
import org.apache.poi.hwpf.usermodel.Range;    
  
public class WordToTxt {  
    //创建字符串缓冲区  
    /* 
     * 不能写成StringBuffer stringBuffer=null; 
     * 否则会报空指针异常 
     */  
    StringBuffer stringBuffer=new StringBuffer();  
      
    //转换word  
    public String readWord()  
    {  
        //word文档路径  
        String pathword="D://word//智联招聘_李福临_中文_20160613_47337433.doc";  
        try {  
            //创建存储word文档的对象  
            HWPFDocument doc = new HWPFDocument(new FileInputStream(pathword));  
            //用来获得word文档内容  
            Range range=doc.getRange();  
            //文档段落数目  
            int paragraphCount=range.numParagraphs();  
            //遍历段落读取数据  
            for(int i=0;i<paragraphCount;i++)  
            {  
                Paragraph pph=range.getParagraph(i);  
                stringBuffer.append(pph.text());  
                System.out.println(stringBuffer.toString());  
            }  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }   
        return stringBuffer.toString().trim();  
    }  
    
    public static void main(String[] args) {  
        new WordToTxt().readWord();  
    }  
}  