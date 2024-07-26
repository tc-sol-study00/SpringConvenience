package com.convenience.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.convenience.models.entities.UserInfo;


/**
 * ユーザー情報テーブルDAO
 * 
 * @author ys-fj
 *
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {

}