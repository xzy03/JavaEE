package cn.edu.zjut.entity.TenantProfile;

import cn.edu.zjut.entity.TenantProfile.resq.TenantLoginResp;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TenantConverter {
    TenantConverter INSTANCE = Mappers.getMapper(TenantConverter.class);
    TenantLoginResp toLoginResp(TenantProfile tenantProfile);
}