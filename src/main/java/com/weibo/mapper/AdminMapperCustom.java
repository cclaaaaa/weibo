package com.weibo.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.weibo.po.Admin;
import com.weibo.po.AdminExample;

public interface AdminMapperCustom {

	List<Admin> queryAdminByUsername(Admin admin);
}