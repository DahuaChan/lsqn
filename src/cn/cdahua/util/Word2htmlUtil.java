package cn.cdahua.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.w3c.dom.Document;

public class Word2htmlUtil {
	public static void main(String argv[]) {
		try {
			convert2Html("D:/test/2.doc", "D:/test/123.html");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void writeFile(String content, String path) {
		FileOutputStream fos = null;
		BufferedWriter bw = null;
		try {
			File file = new File(path);
			fos = new FileOutputStream(file);
			bw = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"));
			bw.write(content);
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fos != null)
					fos.close();
			} catch (IOException ie) {
			}
		}
	}

	public static void convert2Html(String fileName, String outPutFile)
			throws TransformerException, IOException, ParserConfigurationException {
		// 导入文件
		HWPFDocument wordDocument = new HWPFDocument(new FileInputStream(fileName));// WordToHtmlUtils.loadDoc(new
		// 新建转换器 // FileInputStream(inputFile));
		WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(
				DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
		// 给转换器设置图片转换
		wordToHtmlConverter.setPicturesManager(new PicturesManager() {
			public String savePicture(byte[] content, PictureType pictureType, String suggestedName, float widthInches,
					float heightInches) {
				return suggestedName;
			}
		});
		// 开始处理文档
		wordToHtmlConverter.processDocument(wordDocument);
		// 提取文档图片并保存
		List<Picture> pics = wordDocument.getPicturesTable().getAllPictures();
		if (pics != null) {
			for (int i = 0; i < pics.size(); i++) {
				Picture pic = (Picture) pics.get(i);
				try {
					pic.writeImageContent(new FileOutputStream(fileName.substring(0, fileName.indexOf(".")) + pic.suggestFullFileName()));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}

		/*
		 *  DOM对象转化为XML文件 使用 Transformer对象将一个Document节点变换为一个XML文件
		 */
		// 获取DOM对象
		Document htmlDocument = wordToHtmlConverter.getDocument();
		// 建立TransformerFactory对象
		TransformerFactory tf = TransformerFactory.newInstance();
		// 得到Transformer对象并设定属性
		Transformer serializer = tf.newTransformer();
		serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
		serializer.setOutputProperty(OutputKeys.INDENT, "yes");
		serializer.setOutputProperty(OutputKeys.METHOD, "html");
		// 将Document对象封装到一个DOMSource对象中
		DOMSource domSource = new DOMSource(htmlDocument);
		// 将变换得到XML文件对象封装到一个StreamResult对象中
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		StreamResult streamResult = new StreamResult(out);
		// Transformer 对象transformer 调用transform方法实施变换
		serializer.transform(domSource, streamResult);
		// 关闭输出流
		out.close();
		// 输出文件
		writeFile(new String(out.toByteArray()), outPutFile);
	}
}
