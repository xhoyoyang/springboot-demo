package com.springboot.demo.validate;

import com.springboot.demo.validate.annotation.EnumValidAnnotation;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class EnumValidator implements ConstraintValidator<EnumValidAnnotation, Object> {


    private Class<?>[] cls;

    @Override
    public void initialize(EnumValidAnnotation constraintAnnotation) {
        cls = constraintAnnotation.target();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {

        if (null != value) {
            for (Class<?> cl : cls) {
                try {
                    if (cl.isEnum()) {
                        Object[] objs = cl.getEnumConstants();
                        Method method = cl.getMethod("getCode");
                        for (Object obj : objs) {
                            Object code = method.invoke(obj, null);
                            //if (value.equals(code.toString())) {
                            //    return true;
                            //}
                            String typeName = obj.toString();
                            if (value.toString().equals(typeName)) {
                                return true;
                            }
                        }
                    }
                } catch (NoSuchMethodException e) {
                    log.error(e.getMessage(), e);
                } catch (IllegalAccessException e) {
                    log.error(e.getMessage(), e);
                } catch (InvocationTargetException e) {
                    log.error(e.getMessage(), e);
                }
            }
        } else {
            return true;
        }

        return false;
    }
}
