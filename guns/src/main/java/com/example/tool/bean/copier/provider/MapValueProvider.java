package com.example.tool.bean.copier.provider;

import com.example.tool.bean.copier.ValueProvider;
import com.example.tool.convert.Convert;
import com.example.tool.map.CaseInsensitiveMap;
import com.example.tool.util.StrUtil;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * map值提供者
 */
public class MapValueProvider implements ValueProvider<String> {
    private Map<?, ?> map;

    /**
     * 构造
     *
     * @param map Map
     * @param ignoreCase 是否忽略key的大小写
     */
    public MapValueProvider(Map<?, ?> map, boolean ignoreCase) {
        if(false == ignoreCase || map instanceof CaseInsensitiveMap) {
            //不忽略大小写或者提供的Map本身为CaseInsensitiveMap则无需转换
            this.map = map;
        }else {
            //转换为大小写不敏感的Map
            this.map = new CaseInsensitiveMap<>(map);
        }
    }

    @Override
    public Object value(String key, Type valueType) {
        Object value = map.get(key);
        if(null == value) {
            //检查下划线模式
            value = map.get(StrUtil.toUnderlineCase(key));
        }
        return Convert.convert(valueType, value);
    }

    @Override
    public boolean containsKey(String key) {
        if(map.containsKey(key)) {
            return true;
        }else if(map.containsKey(StrUtil.toUnderlineCase(key))) {
            //检查下划线模式
            return true;
        }
        return false;
    }

}
