package com.convenience.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.convenience.models.entities.UserInfo;


/**
 * ユーザー情報テーブルDAO
 * 
 * @author ys-fj
 *
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {

}