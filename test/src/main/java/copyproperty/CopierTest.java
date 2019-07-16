package copyproperty;

import com.google.common.base.Stopwatch;
import net.sf.cglib.beans.BeanCopier;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.TimeUnit;

/**
 * @author luoan
 * @version 1.0
 * @date 2019/7/3 16:26
 **/
public class CopierTest {
    private static void testCglibBeanCopier(BaseDog origin, int len) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        System.out.println();
        System.out.println("================cglib BeanCopier执行" + len + "次================");
        Dog dog = new Dog();

        for (int i = 0; i < len; i++) {
            BeanCopier copier = BeanCopier.create(BaseDog.class, Dog.class, false);
            copier.copy(origin, dog, null);
        }
        stopwatch.stop();

        System.out.println("testCglibBeanCopier 耗时: " + stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

    private static void testApacheBeanUtils(BaseDog origin, int len)
            throws IllegalAccessException, InvocationTargetException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        System.out.println();
        System.out.println("================apache BeanUtils执行" + len + "次================");
        Dog dog = new Dog();
        for (int i = 0; i < len; i++) {
            BeanUtils.copyProperties(origin, dog);
        }

        stopwatch.stop();

        System.out.println("testApacheBeanUtils 耗时: " + stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

    private static void testSpringFramework(BaseDog origin, int len) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        System.out.println("================springframework执行" + len + "次================");
        Dog dog = new Dog();

        for (int i = 0; i < len; i++) {
            org.springframework.beans.BeanUtils.copyProperties(origin, dog);
        }

        stopwatch.stop();

        System.out.println("testSpringFramework 耗时: " + stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

    private static void testApacheBeanUtilsPropertyUtils(BaseDog origin, int len)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        System.out.println();
        System.out.println("================apache BeanUtils PropertyUtils执行" + len + "次================");
        Dog dog = new Dog();
        for (int i = 0; i < len; i++) {
            PropertyUtils.copyProperties(origin, dog);
        }

        stopwatch.stop();

        System.out.println("testApacheBeanUtilsPropertyUtils 耗时: " + stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }
}
