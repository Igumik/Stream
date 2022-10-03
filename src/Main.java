import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        //TODO №1
        long count = persons.stream()
                .filter(age -> age.getAge() < 18)
                .count();
        System.out.println(count);

        //TODO №2
        List<String> conscript = persons.stream()
                .filter(minAge -> minAge.getAge() > 18)
                .filter(maxAge -> maxAge.getAge() < 27)
                .filter(man -> man.getSex() == Sex.MAN)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println(conscript);

        //TODO №3
        List<String> worker = persons.stream()
                .filter(educ -> educ.getEducation() == Education.HIGHER)
                .filter(sex -> {if (sex.getAge() > 18 && sex.getAge() < 60)
                    return sex.getSex() == Sex.WOMAN;
                else if (sex.getAge() > 18 && sex.getAge() < 65)
                    return sex.getSex() == Sex.MAN;
                else return false;})
                .map(Person::getFamily)
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
        System.out.println(worker);
    }
}
