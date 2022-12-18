package ru.skillbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.skillbox.model.Friendship;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendsRepository extends JpaRepository<Friendship, Integer>, JpaSpecificationExecutor<Friendship> {

    // выводим друзей из таблицы для случаев, когда currentUserId был инициатором дружбы
//    @Query("SELECT dst.dstPerson FROM Friendship src" +
//            " JOIN src.dstPerson.friendsLeft dst" +
//            " WHERE dst.dstPerson.id NOT IN (SELECT f.dstPerson.id FROM Friendship f WHERE f.srcPerson.id = :currentUserId)" +
//            " AND src.srcPerson.id = :currentUserId AND src.statusId.code = 'FRIEND' AND dst.statusId.code = 'FRIEND'")
//    List<Person> findFriendsOfFriendsForward(Long currentUserId, Pageable pageable);
//
//    // выводим друзей из таблицы для случаев, когда другой человек был инициатором дружбы с currentUserId
//    @Query("SELECT dst.srcPerson FROM Friendship cf" +
//            " JOIN cf.srcPerson.friendsRight dst" +
//            " WHERE dst.srcPerson.id NOT IN (SELECT f.srcPerson.id FROM Friendship f WHERE f.dstPerson.id = :currentUserId)" +
//            " AND cf.dstPerson.id = :currentUserId AND cf.statusId.code = 'FRIEND' AND dst.statusId.code = 'FRIEND'")
//    List<Person> findFriendsOfFriendsReverse(Long currentUserId, Pageable pageable);

    Optional<Friendship> findBySrcPersonIdAndDstPersonId(Long srcPersonId, Long dstPersonId);
    Optional <List<Friendship>> findAllBySrcPersonIdOrDstPersonId(Long srcPersonId, Long dstPersonId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Friendship f WHERE f.id = :id")
    void delete(Long id);

    @Query(value = "select * from friendship\n" +
            "join people src on src.id = friendship.src_person_id\n" +
            "join people dst on dst.id = friendship.dst_person_id\n" +
            "where src.email = 'btoxic28@gmail.com'\n" +
            "and (dst.first_name ilike '%b%' or dst.last_name ilike '%b%')", nativeQuery = true)
    List<Friendship> searchFriendship(String email, String name);

    @Query(value = "SELECT * FROM friendship\n" +
            "WHERE status = 'REQUEST_TO'\n" +
            "AND dst_person_id = ?1;", nativeQuery = true)
    List<Friendship> findRequestToFriendshipToUserId(long id);

    @Query(value = "SELECT * FROM friendship\n" +
            "WHERE status = 'BLOCKED'\n" +
            "AND src_person_id = ?1;", nativeQuery = true)
    List<Friendship> findBlockedFriendshipForUserId(long id);



}
