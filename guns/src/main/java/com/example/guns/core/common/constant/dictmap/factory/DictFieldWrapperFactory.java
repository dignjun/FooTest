package com.example.guns.core.common.constant.dictmap.factory;

import com.example.guns.core.common.constant.factory.ConstantFactory;
import com.example.guns.core.common.constant.factory.IConstantFactory;
import com.example.guns.core.common.exception.BizExceptionEnum;
import com.example.model.exception.ServiceException;

import java.lang.reflect.Method;

/**
 * 字典字段的包装器(从ConstantFactory中获取包装值)
 *
 * @author DINGJUN
 * @date 2019.03.14
 */
public class DictFieldWrapperFactory {
    public static Object createFieldWrapper(Object parameter, String methodName) {
        IConstantFactory constantFactory = ConstantFactory.me();
        try {
            Method method = IConstantFactory.class.getMethod(methodName, parameter.getClass());
            return method.invoke(constantFactory, parameter);
        } catch (Exception e) {
            try {
                Method method = IConstantFactory.class.getMethod(methodName, Integer.class);
                return method.invoke(constantFactory, Integer.parseInt(parameter.toString()));
            } catch (Exception e1) {
                throw new ServiceException(BizExceptionEnum.ERROR_WRAPPER_FIELD);
            }
        }
    }
}
