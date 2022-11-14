package ru.skillbox.specification;


import org.springframework.data.jpa.domain.Specification;
import ru.skillbox.model.Person;

public class PersonSpecification {
    public static Specification<Person> getUsersByFirstName(String firstName) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(PersonSpecificationRoot.FIRST_NAME.getValue()), firstName);
    }

    public static Specification<Person> getUsersByLastName(String lastName) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(PersonSpecificationRoot.LAST_NAME.getValue()), lastName);
    }

    public static Specification<Person> getUsersByAgeFrom(Long ageFrom) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get(PersonSpecificationRoot.BIRTH_DATE.getValue()), ageFrom);
    }

    public static Specification<Person> getUsersByAgeTo(Long ageTo) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get(PersonSpecificationRoot.BIRTH_DATE.getValue()), ageTo);
    }

    public static Specification<Person> getUsersByEnable() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(PersonSpecificationRoot.IS_ENABLED.getValue()), true);
    }

    public static Specification<Person> skipCurrentPerson(String email) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.notEqual(root.get(PersonSpecificationRoot.EMAIL.getValue()), email);
    }

    public static Specification<Person> getUsersByCity(Integer id) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.notEqual(root.get(PersonSpecificationRoot.CITY_ID.getValue()), id);
    }

    public static Specification<Person> getUsersByCountry(Integer id) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.notEqual(root.get(PersonSpecificationRoot.COUNTRY_ID.getValue()), id);
    }
}
