package com.weibo.service;

import java.util.List;

import com.weibo.po.Admin;

public interface AdminService {

	List<Admin> queryAdminByUsername(Admin admin);

}
