package copyproperty;

import net.sf.cglib.beans.BeanCopier;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.TimeUnit;

/**
 * @author luoan
 * @version 1.0
 * @date 2019/7/3 16:26
 **/
public class ApacheCopy {
    private static void testCglibBeanCopier(OriginObject origin, int len) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        System.out.println();
        System.out.println("================cglib BeanCopier执行" + len + "次================");
        DestinationObject destination3 = new DestinationObject();

        for (int i = 0; i < len; i++) {
            BeanCopier copier = BeanCopier.create(OriginObject.class, DestinationObject.class, false);
            copier.copy(origin, destination3, null);
        }
        stopwatch.stop();

        System.out.println("testCglibBeanCopier 耗时: " + stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

    private static void testApacheBeanUtils(OriginObject origin, int len)
            throws IllegalAccessException, InvocationTargetException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        System.out.println();
        System.out.println("================apache BeanUtils执行" + len + "次================");
        DestinationObject destination2 = new DestinationObject();
        for (int i = 0; i < len; i++) {
            BeanUtils.copyProperties(destination2, origin);
        }

        stopwatch.stop();

        System.out.println("testApacheBeanUtils 耗时: " + stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

    private static void testSpringFramework(OriginObject origin, int len) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        System.out.println("================springframework执行" + len + "次================");
        DestinationObject destination = new DestinationObject();

        for (int i = 0; i < len; i++) {
            org.springframework.beans.BeanUtils.copyProperties(origin, destination);
        }

        stopwatch.stop();

        System.out.println("testSpringFramework 耗时: " + stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

    private static void testApacheBeanUtilsPropertyUtils(OriginObject origin, int len)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        System.out.println();
        System.out.println("================apache BeanUtils PropertyUtils执行" + len + "次================");
        DestinationObject destination2 = new DestinationObject();
        for (int i = 0; i < len; i++) {
            PropertyUtils.copyProperties(destination2, origin);
        }

        stopwatch.stop();

        System.out.println("testApacheBeanUtilsPropertyUtils 耗时: " + stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }
}
