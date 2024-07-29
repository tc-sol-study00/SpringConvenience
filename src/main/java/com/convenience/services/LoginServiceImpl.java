package com.convenience.services;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.convenience.models.entities.UserInfo;
import com.convenience.repositories.UserInfoRepository;

import lombok.RequiredArgsConstructor;

/**
 * ログイン画面 Service
 * 
 */
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

	/** ユーザー情報テーブルDAO */
	private final UserInfoRepository repository;

	/**
	 * ユーザ情報テーブル 主キー検索
	 * 
	 * @param loginId ログインID
	 * @return ユーザ情報テーブルを主キー検索した結果(1件)
	 */
	public Optional<UserInfo> searchUserById(String loginId) {
		return repository.findById(loginId);
	}
}