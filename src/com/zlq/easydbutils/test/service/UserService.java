package com.zlq.easydbutils.test.service;

import com.zlq.easydbutils.annotation.StratTransaction;
import com.zlq.easydbutils.test.domain.User;

public interface UserService {

public	void login(String username, String password);

@StratTransaction
public void addUser(User user);
}
