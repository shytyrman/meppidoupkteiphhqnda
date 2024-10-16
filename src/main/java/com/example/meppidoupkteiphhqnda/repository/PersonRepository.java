package com.example.meppidoupkteiphhqnda.repository;

import com.example.meppidoupkteiphhqnda.model.Person;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

    public Optional<Person> findByPhoneNumber(String phoneNumber);
    public Boolean existsByPhoneNumber(String phoneNumber);
    public void deleteByPhoneNumber(String phoneNumber);

    @Modifying
    @Transactional
    @Query("UPDATE Person p SET p.fullName=IFNULL(:fullName, p.fullName), p.birthday=IFNULL(:birthday, p.birthday), p.phoneNumber=IFNULL(:phoneNumber, p.phoneNumber), p.phoneNumberAdditional=IFNULL(:phoneNumberAdditional, p.phoneNumberAdditional) WHERE p.id=:searchId OR p.phoneNumber=:searchNumber")
//    @Query(value = "UPDATE person SET full_name=coalesce(?1, full_name), birthday=coalesce(?2, birthday), phone_number=coalesce(?3, phone_number), " +
//            "phone_number_additional=coalesce(?4, phone_number_additional) " +
//            "WHERE id=?5 OR phone_number=?6", nativeQuery = true)
    public void update(@Param("fullName") String fullName, @Param("birthday") LocalDate birthday, @Param("phoneNumber")
    String phoneNumber, @Param("phoneNumberAdditional") String phoneNumberAdditional, @Param("searchId") Long id, @Param("searchNumber") String number);

    @Query(value = "SELECT * FROM person ORDER BY id ASC LIMIT ? OFFSET ?",
            nativeQuery = true)
    public List<Person> findAll(Integer limit, Integer offset);
}
