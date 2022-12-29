package algorithm.myLinked;

import com.aspose.words.Document;
import com.aspose.words.SaveFormat;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class Test01 {
    public static void main(String[] args) throws Exception {
        Test01 test01 = new Test01();
        test01.file2pdf("D:\\", "AA1", ".docx");
    }

    public String file2pdf(String toFilePath, String fileName, String type) throws Exception {
        String htmFileName = "D:\\test10.pdf";
        //获取转换成PDF之后文件名
        //通过转换之后的PDF文件名,创建PDF文件
        File htmlOutputFile = new File(htmFileName);
        //获取文件输出流
        FileOutputStream os = new FileOutputStream(htmlOutputFile);
        //获取Doc文档对象模型
//        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("template/test10.docx");
        FileInputStream is = new FileInputStream("D:\\test12.docx");
        Document doc = new Document(is);
        //将doc文旦转换成PDF文件并输出到之前创建好的pdf文件中
        doc.save(os, SaveFormat.PDF);
        is.close();
        //关闭输出流
        os.close();
        return htmFileName;
    }


}

