package cn.edu.zjut.utils;
//
//import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.atomic.AtomicLong;
//
///**
// * ID生成器工具类
// * @Author 项郑毅
// * @Date 2024/12/11
// */
//@Slf4j
//@Component
//public class IdGeneratorUtil implements IdentifierGenerator {
//    private final AtomicLong al = new AtomicLong(1);
//
//    public Long nextId(Object entity) {
//        return null;
//    }
//    /**
//     * String 类型id生成
//     *
//     * @param entity
//     * @return
//     */
//    public String nextUUID(Object entity) {
//        // 下面是我的生成逻辑，我是获取表首字母缩写(对于Admins表就是A)  + 递增序号，一共5位不足用0补齐
//        String prefix = entity.getClass().getSimpleName().substring(0, 1).toUpperCase();
//        String id = prefix + String.format("%08d", al.getAndIncrement());
//        log.info("生成的ID为：{}", id);
//        return id;
//    }
//}
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.HashMap;
import java.util.Map;
@Slf4j
@Component
public class IdGeneratorUtil implements IdentifierGenerator {
    // 用一个Map来为每个表维护一个AtomicLong
    private final Map<String, AtomicLong> tableIdMap = new HashMap<>();
    private static final String ID_STORAGE_FILE = "id_storage.json";  // 用来保存ID的文件路径

    private ObjectMapper objectMapper = new ObjectMapper();

    public IdGeneratorUtil() {
        // 启动时加载保存的ID数据
        try {
            // 加载存储的ID
            loadStoredIds();
        } catch (Exception e) {
            log.error("IdGeneratorUtil 构造函数出错: ", e);
            throw e;  // 保证异常被抛出
        }
    }

    /**
     * 获取指定表的唯一ID
     * @param entity 实体对象
     * @return 唯一的Long类型ID
     */
    @Override
    public Long nextId(Object entity) {
        return null;
    }
    /**
     * String 类型id生成
     *
     * @param entity 实体对象
     * @return 生成的ID字符串
     */
    public String nextUUID(Object entity) {
        String tableName = entity.getClass().getSimpleName();  // 获取实体类名作为表名
        AtomicLong atomicLong = tableIdMap.computeIfAbsent(tableName, k -> new AtomicLong(getStoredId(tableName)));
        String prefix = tableName.substring(0, 1).toUpperCase();  // 表名首字母作为前缀
        String id = prefix + String.format("%08d", atomicLong.getAndIncrement());
        log.info("生成的ID为：{}", id);
        saveIdsToFile();  // 保存ID状态到文件
        return id;
    }

    /**
     * 获取存储的ID，如果没有则返回0
     * @param tableName 表名
     * @return 存储的ID
     */
    private long getStoredId(String tableName) {
        // 读取存储的ID值，若没有则返回初始值0
        Map<String, Long> storedIds = loadStoredIdsFromFile();
        return storedIds.getOrDefault(tableName, 0L);
    }

    /**
     * 加载存储的ID
     */
    private void loadStoredIds() {
        Map<String, Long> storedIds = loadStoredIdsFromFile();
        for (Map.Entry<String, Long> entry : storedIds.entrySet()) {
            tableIdMap.put(entry.getKey(), new AtomicLong(entry.getValue()));
        }
    }

    /**
     * 从文件加载已保存的ID
     * @return 已保存的ID数据
     */
    private Map<String, Long> loadStoredIdsFromFile() {
        try {
            File file = new File(ID_STORAGE_FILE);
            if (file.exists()) {
                Map<String, Object> storedIds = objectMapper.readValue(file, Map.class);

                // 处理类型转换，确保 ID 为 Long 类型
                Map<String, Long> result = new HashMap<>();
                for (Map.Entry<String, Object> entry : storedIds.entrySet()) {
                    Long id = (entry.getValue() instanceof Integer) ? ((Integer) entry.getValue()).longValue() : (Long) entry.getValue();
                    result.put(entry.getKey(), id);
                }
                return result;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }

    /**
     * 保存当前ID状态到文件
     */
    public void saveIdsToFile() {
        Map<String, Long> idsToSave = new HashMap<>();
        for (Map.Entry<String, AtomicLong> entry : tableIdMap.entrySet()) {
            idsToSave.put(entry.getKey(), entry.getValue().get());
        }
        try {
            objectMapper.writeValue(new File(ID_STORAGE_FILE), idsToSave);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
