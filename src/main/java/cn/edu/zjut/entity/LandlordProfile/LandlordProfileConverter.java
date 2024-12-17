package cn.edu.zjut.entity.LandlordProfile;

import cn.edu.zjut.entity.LandlordProfile.resp.LandlordProfileLoginResp;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LandlordProfileConverter {
    LandlordProfileConverter INSTANCE = Mappers.getMapper(LandlordProfileConverter.class);
    LandlordProfileLoginResp toLandlordLoginResp(LandlordProfile landlordProfile);
}
