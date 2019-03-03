import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

public class PdfExporter {
    private static Font FONT = FontFactory.getFont("font/HelveticaRegular.ttf", "Cp1251", true);

    public void export (List<Person> personList)throws Exception{
        Document document = new Document(PageSize.A3.rotate());
        File file = new File("TinkoffHW3.pdf");
        PdfWriter.getInstance(document, new FileOutputStream(file));

        document.open();

        PdfPTable table = new PdfPTable(14);

        Stream.of("Имя", "Фамилия", "Отчество", "Пол", "Страна", "Область", "Город",
                "Улица", "Дом", "Квартира", "Дата Рождения", "Возраст", "Индекс", "ИНН")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle, FONT));
                    table.addCell(header);
                });

        for (Person person : personList) {

            addCell(table, person.getName());
            addCell(table, person.getSurname());
            addCell(table, person.getPatronymic());
            addCell(table, person.getSex().getDescription());
            addCell(table, person.getCountry());
            addCell(table, person.getArea());
            addCell(table, person.getCity());
            addCell(table, person.getStreet());
            addCell(table, String.valueOf(person.getHouse()));
            addCell(table, String.valueOf(person.getAppartment()));
            addCell(table, person.getBirthDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            addCell(table, String.valueOf(person.getAge()));
            addCell(table, String.valueOf(person.getPostalCode()));
            addCell(table, String.valueOf(person.getInn()));

        }

        document.add(table);
        document.close();
               System.out.println("PDF файл успешно создан! Путь к файлу:" + file.getAbsolutePath());

    }

    private void addCell(PdfPTable table, String text) {
        table.addCell(new Phrase(text, FONT));
    }
}
