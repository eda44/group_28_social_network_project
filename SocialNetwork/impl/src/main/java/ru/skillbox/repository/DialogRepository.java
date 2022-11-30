package ru.skillbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.skillbox.model.Dialog;
import ru.skillbox.model.Person;

import java.util.List;

@Repository
public interface DialogRepository extends JpaRepository<Dialog, Long> {

    @Query("select d from dialogs d where d.owner = :person or d.conversationPartner = :person")
    List<Dialog> findAllDialogsForPerson(@Param("person") Person person);
}
