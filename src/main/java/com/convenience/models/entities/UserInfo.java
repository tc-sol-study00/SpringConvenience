package com.convenience.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * ユーザ情報テーブル Entity
 * 
 *
 */
@Data
@Entity
@Table(name = "user_info")
public class UserInfo {

	/** ログインID */
	@Id
	@Column(name = "login_id")
	private String loginId;

	/** パスワード */
	@Column(name = "password")
	private String password;
	
	/** ユーザ名フルネーム */
	@Column(name = "userfullname")
	private String userfullname;
}