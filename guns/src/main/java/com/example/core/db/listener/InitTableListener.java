package com.example.core.db.listener;

import com.example.core.db.DbInitializer;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;

import java.util.Map;

/**
 * 初始化,创建字典表
 *
 * @author DINGJUN
 * @date 2019.03.13
 */

public class InitTableListener implements ApplicationListener<ApplicationReadyEvent>, Ordered {
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        Map<String, DbInitializer> beansOfType = event.getApplicationContext().getBeansOfType(DbInitializer.class);

        for (Map.Entry<String, DbInitializer> entry : beansOfType.entrySet()) {
            DbInitializer value = entry.getValue();
            value.dbInit();
        }

    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

}
