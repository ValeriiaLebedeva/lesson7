import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestFile {


    @Test
    @DisplayName("File upload by relative path ")
    void fileNameShouldBeDisplayedAfterUploadActionRelativeTest() {
        open("https://fineuploader.com/demos.html");
        $("input[type='file']").uploadFromClasspath("2.png");
        $(".qq-upload-list").shouldHave(text("2"));
    }

    @Test
    @DisplayName("���������� PDF �����: �������")
    void firstPdfFileDownloadTest() throws IOException {
        open("https://alfabank.ru/get-money/credit-cards/100-days/");
        File pdf = $(byLinkText("������� � ������������� ������� �� ��������� �����")).download();
        PDF parsedPdf = new PDF(pdf);
        Assertions.assertEquals(2, parsedPdf.numberOfPages);
    }


    @Test
    @DisplayName("���������� PDF �����: ������")
    void secondPdfFileDownloadTest() throws IOException {
        open("https://alfabank.ru/get-money/credit-cards/100-days/");
        File pdf = $(byLinkText("��������� ����� 100 ���� ��� % ������")).download();
        PDF parsedPdf = new PDF(pdf);
        Assertions.assertEquals(true, parsedPdf.text.contains("������������� ������"));

    }


    @Test
    @DisplayName("���������� XLS �����")
    void xlsFileDownloadTest() throws IOException {
        open("https://ckmt.ru/price-download.html");
        File file = $(byLinkText("�������")).download();
        XLS parsedXls = new XLS(file);
        boolean checkPassed = parsedXls.excel.
                getSheetAt(0).
                getRow(25).
                getCell(2).
                getStringCellValue().
                contains("3.0");


        assertTrue(checkPassed);
    }



}
