package com.weibo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weibo.mapper.AdminMapperCustom;
import com.weibo.po.Admin;
import com.weibo.service.AdminService;

@Transactional
@Service("adminService")
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminMapperCustom adminMapperCustom;
	
	@Override
	public List<Admin> queryAdminByUsername(Admin admin) {
		return adminMapperCustom.queryAdminByUsername(admin);
	}

}
