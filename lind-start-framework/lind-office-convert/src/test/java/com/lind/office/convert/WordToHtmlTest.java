package com.lind.office.convert;

import com.lind.office.convert.utils.FilesUtils;
import org.docx4j.Docx4J;
import org.docx4j.Docx4jProperties;
import org.docx4j.XmlUtils;
import org.docx4j.convert.in.xhtml.XHTMLImporterImpl;
import org.docx4j.convert.out.HTMLSettings;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class WordToHtmlTest {
    @Test
    public void docToHtml() throws Exception {
        WordToHtml.docxToHtml("D:\\接口管理平台-DOClever使用文档v1.0.docx", "d:\\outHtml");
    }
    public static void xhtmlToDocx1(String xhtml, String destinationPath, String fileName)
    {
        File dir = new File (destinationPath);
        File actualFile = new File (dir, fileName);

        WordprocessingMLPackage wordMLPackage = null;
        try
        {
            wordMLPackage = WordprocessingMLPackage.createPackage();
        }
        catch (InvalidFormatException e)
        {
            e.printStackTrace();
        }


        XHTMLImporterImpl XHTMLImporter = new XHTMLImporterImpl(wordMLPackage);

        OutputStream fos = null;
        try
        {
            fos = new ByteArrayOutputStream();

            System.out.println(XmlUtils.marshaltoString(wordMLPackage
                    .getMainDocumentPart().getJaxbElement(), true, true));

            HTMLSettings htmlSettings = Docx4J.createHTMLSettings();
            htmlSettings.setWmlPackage(wordMLPackage);
            Docx4jProperties.setProperty("docx4j.Convert.Out.HTML.OutputMethodXML",
                    true);
            Docx4J.toHTML(htmlSettings, fos, Docx4J.FLAG_EXPORT_PREFER_XSL);
            wordMLPackage.save(actualFile);
        }
        catch (Docx4JException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally{
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
@Test
    public void test(){
    xhtmlToDocx1("<html><p>hello</p></html>","d:\\outHtml","1.docx");

}

    public static void main(String[] args) throws Exception {


        String xhtml=
                FilesUtils.readAll("d:\\outHtml\\fb7c37b3-8919-4dcd-9faf-bb4defdd2913.html");

        // To docx, with content controls
        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();

        XHTMLImporterImpl XHTMLImporter = new XHTMLImporterImpl(wordMLPackage);
        //XHTMLImporter.setDivHandler(new DivToSdt());

        wordMLPackage.getMainDocumentPart().getContent().addAll(
                XHTMLImporter.convert( xhtml, null) );

        System.out.println(XmlUtils.marshaltoString(wordMLPackage
                .getMainDocumentPart().getJaxbElement(), true, true));

		wordMLPackage.save(new java.io.File( "D:\\outHtml\\lOUT_from_XHTML.docx"));

        // Back to XHTML

        HTMLSettings htmlSettings = Docx4J.createHTMLSettings();
        htmlSettings.setWmlPackage(wordMLPackage);


        // output to an OutputStream.
        OutputStream os = new ByteArrayOutputStream();

        // If you want XHTML output
        Docx4jProperties.setProperty("docx4j.Convert.Out.HTML.OutputMethodXML",
                true);
        Docx4J.toHTML(htmlSettings, os, Docx4J.FLAG_EXPORT_PREFER_XSL);

        System.out.println(((ByteArrayOutputStream) os).toString());

    }

}
