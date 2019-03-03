import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) throws Exception {

        Reader test = new Reader();

        List<String> areaList = test.read(Main.class.getResource("area.txt").getFile());
        List<String> cityList = test.read(Main.class.getResource("city.txt").getFile());
        List<String> countriesList = test.read(Main.class.getResource("countries.txt").getFile());
        List<String> nameFList = test.read(Main.class.getResource("name_f.txt").getFile());
        List<String> nameMList = test.read(Main.class.getResource("name_m.txt").getFile());
        List<String> patronymicFList = test.read(Main.class.getResource("patronymic_f.txt").getFile());
        List<String> patronymicMList = test.read(Main.class.getResource("patronymic_m.txt").getFile());
        List<String> streetList = test.read(Main.class.getResource("street.txt").getFile());
        List<String> surnameFList = test.read(Main.class.getResource("surname_f.txt").getFile());
        List<String> surnameMList = test.read(Main.class.getResource("surname_m.txt").getFile());


        ThreadLocalRandom random = ThreadLocalRandom.current();

        List<Person> personList = new ArrayList<>();
        Integer randomNumber = random.nextInt(1,30);

        for (int i = 0; i<randomNumber; i++) {
            Person person = new Person();
            Sex sex = random.nextBoolean() ? Sex.MALE : Sex.FEMALE;
            person.setSex(sex);

            if (sex == Sex.FEMALE) {
                person.setName(nameFList.get(random.nextInt(nameFList.size())));
                person.setPatronymic(patronymicFList.get(random.nextInt((patronymicFList.size()))));
                person.setSurname(surnameFList.get(random.nextInt(surnameFList.size())));
            } else {
                person.setName(nameMList.get(random.nextInt(nameMList.size())));
                person.setPatronymic(patronymicMList.get(random.nextInt((patronymicMList.size()))));
                person.setSurname(surnameMList.get(random.nextInt(surnameMList.size())));
            }
            person.setArea(areaList.get(random.nextInt(areaList.size())));
            person.setCity(cityList.get(random.nextInt(cityList.size())));
            person.setCountry(countriesList.get(random.nextInt(countriesList.size())));
            person.setStreet(streetList.get(random.nextInt(streetList.size())));
            person.setHouse(random.nextInt(30));
            person.setAppartment(random.nextInt(50));
            person.setPostalCode(random.nextInt(100000, 200000));
            LocalDate birthDate = createRandomDate(1915, 2018);
            person.setBirthDate(birthDate);
            person.setAge(Period.between(birthDate, LocalDate.now()).getYears());
            person.setInn(random.nextLong(770000000000L, 780000000000L));
            personList.add(person);
        }
        new ExcelExporter().export(personList);
        new PdfExporter().export(personList);
    }
    public static int createRandomIntBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

    public static LocalDate createRandomDate(int startYear, int endYear) {
        int day = createRandomIntBetween(1, 28);
        int month = createRandomIntBetween(1, 12);
        int year = createRandomIntBetween(startYear, endYear);
        return LocalDate.of(year, month, day);
    }
}