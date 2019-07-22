package com.anla.rpc.configcenter.provider;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.dubbo.common.utils.StringUtils;

/**
 * 这个类主要是生成一些zk配置，当然也可以使用一些可视化工具进行配置
 * @author anLA7856
 * @date 19-7-22 下午10:52
 * @description
 */
public class ZKConfigCenterTools {
    private static String zookeeperHost = System.getProperty("zookeeper.address", "127.0.0.1");
    private static CuratorFramework client;

    public static void main(String[] args) throws Exception {
        generateDubboProperties();
    }

    public static void generateDubboProperties() {
        client = CuratorFrameworkFactory.newClient(zookeeperHost + ":2181", 60 * 1000, 60 * 1000,
                new ExponentialBackoffRetry(1000, 3));
        client.start();

        generateDubboPropertiesForGlobal();
        generateDubboPropertiesForProvider();
        generateDubboPropertiesForConsumer();
    }

    public static void generateDubboPropertiesForGlobal() {
        String str = "dubbo.registry.address=zookeeper://" + zookeeperHost + ":2181\n" +
                "#global config for consumer\n" +
                "dubbo.consumer.timeout=6000\n" +
                "#global config for provider\n" +
                "dubbo.protocol.port=20990\n" +
                "dubbo.provider.timeout=5000";

        System.out.println(str);

        try {
            String path = "/dubbo/config/dubbo/dubbo.properties";
            if (client.checkExists().forPath(path) == null) {
                client.create().creatingParentsIfNeeded().forPath(path);
            }
            setData(path, str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generateDubboPropertiesForConsumer() {
        String str = "dubbo.consumer.timeout=6666";

        System.out.println(str);

        try {
            String path = "/dubbo/config/consumer/dubbo.properties";
            if (client.checkExists().forPath(path) == null) {
                client.create().creatingParentsIfNeeded().forPath(path);
            }
            setData(path, str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generateDubboPropertiesForProvider() {
        String str = "dubbo.protocol.threadpool=fixed\n" +
                "dubbo.protocol.threads=100";

        System.out.println(str);

        try {
            String path = "/dubbo/config/provider/dubbo.properties";
            if (client.checkExists().forPath(path) == null) {
                client.create().creatingParentsIfNeeded().forPath(path);
            }
            setData(path, str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setData(String path, String data) throws Exception {
        client.setData().forPath(path, data.getBytes());
    }

    private static String pathToKey(String path) {
        if (StringUtils.isEmpty(path)) {
            return path;
        }
        return path.replace("/dubbo/config/", "").replaceAll("/", ".");
    }

}
