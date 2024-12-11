package cn.edu.zjut.entity.admins;

import cn.edu.zjut.entity.admins.resp.AdminsLoginResp;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/*
管理员转换器
@author 项郑毅
@createDate 2024/12/11
 */
@Mapper
public interface AdminsConverter {
    AdminsConverter INSTANCE = Mappers.getMapper(AdminsConverter.class);
    AdminsLoginResp toLoginResp(Admins admins);
}
