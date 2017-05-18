package biz.netcentric.model;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Java Bean Person
 */
@Data(staticConstructor="of")
public class Person {
    private String name;
    private boolean married;
    private String spouse;
    private List<String> children = new ArrayList<>();
    public static final Person defaultPerson = new Person("Empty Name", "Empty spouse", false, 0);
    private static final List<Person> people = new ArrayList<Person>() {
        {
            add(new Person("Kerstin", "Jose", false, 1));
            add(new Person("Erik", "Dora", true, 3));
            add(new Person("Svajune", "Thomas", true, 0));
        }
    };

    /**
     *
     */
    public Person() {
        this.name = "";
        this.spouse = "";
    }

    /**
     *
     * @param name
     * @param spouse
     * @param isMarried
     * @param numberOfChildren
     */
    public Person(final String name, final String spouse, final boolean isMarried, final int numberOfChildren) {
        this.name = name;
        this.spouse = spouse;
        this.married = isMarried;
        IntStream.range(1, numberOfChildren).forEach(i -> children.add("Child " + i));
    }

    /**
     *
     * @param personId
     * @return
     */
    public static Person lookup(final int personId) {
        if (personId > 0 && personId <= people.size()) {
            return people.get(personId - 1);
        }

        return defaultPerson;
    }

    /**
     *
     * @param personId
     * @return
     */
    public static Person lookup(final String personId) {
        int id = -1;

        if (StringUtils.isNumeric(personId)) {
            id = Integer.parseInt(personId);
        }

        return lookup(id);
    }
}
