package springcloud.club.blog.snowflake.bo;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import springcloud.club.blog.snowflake.dto.BaseAspectDTO;
import springcloud.club.blog.snowflake.util.BaseUtils;

import java.lang.reflect.Method;

/**
 * @author zj
 * @create 2020-08-14 10:14
 * 切面业务逻辑对象类 用于处理切面类和数据转换
 **/
@Slf4j
public class BaseAspectBO {
    //切面数据传输类
    protected BaseAspectDTO baseAspectDTO;
    //切面方法（add、update之类）
    protected Method targetMethod;
    //切面类 (*Dao类)
    protected Class<?> targetClass;
    //aop切面类
    protected ProceedingJoinPoint joinPoint;

    public BaseAspectBO() {
        this(new BaseAspectDTO());
    }

    public BaseAspectBO(BaseAspectDTO baseAspectDTO) {
        this.baseAspectDTO = baseAspectDTO;
    }
    //从切面中获取数据构建切面传输数据对象BaseAspectBO
    protected void buildBaseAspectData(final ProceedingJoinPoint pjp) {
        this.joinPoint = pjp;
        Signature signature = this.joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        this.targetMethod = methodSignature.getMethod();
        //切面类
        Class<?> targetClass = this.joinPoint.getTarget().getClass();
        //构建切面类和切面方法
        this.buildTargetClass(targetClass).buildTargetMethod(targetMethod);
        this.buildTargetClassName(targetClass.getName()).buildTargetMethodName(targetMethod.getName());
        //构建参数类型和参数值
        this.buildTargetParameterTypes(targetMethod.getParameterTypes());
        this.buildTargetParameterValues(this.joinPoint.getArgs());
        //构建返回类和类型
        this.buildTargetReturnType(targetMethod.getReturnType()).buildTargetClass(targetClass);

    }

    /** 获取目标方法执行的返回值---转换为范型class对应的对象 */
    @SuppressWarnings("unchecked")
    public final <T> T getTReturnValue(final Class<T> clazz) {
        if (BaseUtils.isNull(clazz)) {
            return null;
        }
        Object returnValue = proceed();
        return (T) returnValue;
    }

    /**
     *
     * <p>
     * 获取方法参数中范型class对应的值
     * </p>
     *
     * <pre>
     * 若方法行参列表存在多个同类型的参数则默认取第一个
     * </pre>
     *
     * @param clazz
     * @return
     *

     * @创建时间 2018年4月26日 下午11:46:47
     */
    @SuppressWarnings("unchecked")
    public final <T> T getTParam(final Class<T> clazz) {
        if (BaseUtils.isNull(clazz)) {
            return null;
        }
        Object[] args = joinPoint.getArgs();
        if (ArrayUtils.isEmpty(args)) {
            return null;
        }
        for (Object arg : args) {
            if (BaseUtils.isNull(arg)) {
                continue;
            }
            if (clazz.isAssignableFrom(arg.getClass())) {
                return (T) arg;
            }
        }
        return null;
    }

    /** 根据形参位置获取泛型参数值 */
    @SuppressWarnings("unchecked")
    public final <T> T getTParam(final int paramPosition) {
        int tempPosition = paramPosition;
        Object[] args = joinPoint.getArgs();
        if (tempPosition <= 0) {
            tempPosition = 1;
        }
        if (paramPosition > args.length) {
            throw new IndexOutOfBoundsException(
                    "指定参数的位置越界:paramPosition = " + paramPosition + ",argsLengh = " + args.length);
        }
        return (T) args[paramPosition - 1];
    }

    private BaseAspectBO buildTargetParameterValues(Object[] targetParameterValues) {
        this.baseAspectDTO.setTargetParameterValues(targetParameterValues);
        return this;
    }

    private BaseAspectBO buildTargetParameterTypes(Class<?>[] targetParameterTypes) {
        this.baseAspectDTO.setTargetParameterTypes(targetParameterTypes);
        return this;
    }

    private BaseAspectBO buildTargetMethodName(String targetMethodName) {
        this.baseAspectDTO.setTargetMethodName(targetMethodName);
        return this;
    }

    private BaseAspectBO buildTargetClassName(String targetClassName) {
        this.baseAspectDTO.setTargetClassName(targetClassName);
        return this;
    }

    private BaseAspectBO buildTargetReturnType(Class<?> targetReturnType) {
        this.baseAspectDTO.setTargetReturnType(targetReturnType);
        return this;
    }

    protected BaseAspectBO buildTargetReturnValue(Object targetReturnValue) {
        this.baseAspectDTO.setTargetReturnValue(targetReturnValue);
        return this;
    }

    private BaseAspectBO buildTargetClass(Class<?> targetClass) {
        this.targetClass = targetClass;
        return this;
    }

    public BaseAspectBO buildTargetMethod(Method targetMethod) {
        this.targetMethod = targetMethod;
        return this;
    }

    public BaseAspectDTO getBaseAspectDTO() {
        return baseAspectDTO;
    }

    public Object proceed() {
        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            log.error("运行失败",e);
            throw new RuntimeException(e);
        }
    }

    public Method getTargetMethod() {
        return targetMethod;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public ProceedingJoinPoint getJoinPoint() {
        return joinPoint;
    }

}
