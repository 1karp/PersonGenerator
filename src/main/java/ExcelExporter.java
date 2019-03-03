import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ExcelExporter {
    public void export(List<Person> personList) {

        // создание самого excel файла в памяти
        HSSFWorkbook workbook = new HSSFWorkbook();
        // создание листа с названием "Просто лист"
        HSSFSheet sheet = workbook.createSheet("Просто лист");
        // счетчик для строк
        int rowNum = 0;

        // создаем подписи к столбцам (это будет первая строчка в листе Excel файла)
        Row row = sheet.createRow(rowNum);
        row.createCell(0).setCellValue("Имя");
        row.createCell(1).setCellValue("Фамилия");
        row.createCell(2).setCellValue("Отчество");
        row.createCell(3).setCellValue("Пол");
        row.createCell(4).setCellValue("Страна");
        row.createCell(5).setCellValue("Область");
        row.createCell(6).setCellValue("Город");
        row.createCell(7).setCellValue("Улица");
        row.createCell(8).setCellValue("Дом");
        row.createCell(9).setCellValue("Квартира");
        row.createCell(10).setCellValue("Дата Рождения");
        row.createCell(11).setCellValue("Возраст");
        row.createCell(12).setCellValue("Индекс");
        row.createCell(13).setCellValue("ИНН");

        for (Person person : personList) {
            Row rowItem = sheet.createRow(++rowNum);
            rowItem.createCell(0).setCellValue(person.getName());
            rowItem.createCell(1).setCellValue(person.getSurname());
            rowItem.createCell(2).setCellValue(person.getPatronymic());
            rowItem.createCell(3).setCellValue(person.getSex().getDescription());
            rowItem.createCell(4).setCellValue(person.getCountry());
            rowItem.createCell(5).setCellValue(person.getArea());
            rowItem.createCell(6).setCellValue(person.getCity());
            rowItem.createCell(7).setCellValue(person.getStreet());
            rowItem.createCell(8).setCellValue(person.getHouse());
            rowItem.createCell(9).setCellValue(person.getAppartment());
            rowItem.createCell(10).setCellValue(person.getBirthDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            rowItem.createCell(11).setCellValue(person.getAge());
            rowItem.createCell(12).setCellValue(person.getPostalCode());
            rowItem.createCell(13).setCellValue(person.getInn());
        }
        // записываем созданный в памяти Excel документ в файл
        File file = new File("TinkoffHW3.xls");
        try (FileOutputStream out = new FileOutputStream(file)) {
            workbook.write(out);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Excel файл успешно создан! Путь к файлу:" + file.getAbsolutePath());


    }
}
