package cn.edu.zjut.entity.Contracts;

import cn.edu.zjut.entity.Contracts.req.ContractsPublishReq;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/*
合同转换器
@Author 项郑毅
@CreateDate 2024/12/17
 */
@Mapper
public interface ContractsConcerter {
    ContractsConcerter INSTANCE = Mappers.getMapper(ContractsConcerter.class);
    Contracts toContracts(ContractsPublishReq contractsPublishReq);
}
