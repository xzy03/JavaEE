package cn.edu.zjut.utils;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

/**
 * ID生成器工具类
 * @Author 项郑毅
 * @Date 2024/12/11
 */
@Slf4j
@Component
public class IdGeneratorUtil implements IdentifierGenerator {
    private final AtomicLong al = new AtomicLong(1);

    public Long nextId(Object entity) {
        return null;
    }
    /**
     * String 类型id生成
     *
     * @param entity
     * @return
     */
    public String nextUUID(Object entity) {
        // 下面是我的生成逻辑，我是获取表首字母缩写(对于Admins表就是A)  + 递增序号，一共5位不足用0补齐
        String prefix = entity.getClass().getSimpleName().substring(0, 1).toUpperCase();
        String id = prefix + String.format("%08d", al.getAndIncrement());
        log.info("生成的ID为：{}", id);
        return id;
    }
}
