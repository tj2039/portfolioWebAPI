package com._2je7.pofol.Repository.TbUser;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com._2je7.pofol.Entity.TbUserEntity;

@Repository
public interface TbUserRepository extends JpaRepository<TbUserEntity, Long>{
	@Query(value="SELECT * FROM tb_user WHERE user_lgn_id = :user_lgn_id",nativeQuery = true)
	public Optional<TbUserEntity> findBUser_lgn_id(@Param("user_lgn_id") String user_lgn_id);
}
