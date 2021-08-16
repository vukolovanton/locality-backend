package com.backend.locality.api.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    UserModel findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    @Modifying
    @Transactional
    @Query("update UserModel u set u.localityId = :localityId where u.id = :userId")
    void updateUserLocalityId(@Param(value = "localityId") Long localityId, @Param(value = "userId") Long userId);
}
