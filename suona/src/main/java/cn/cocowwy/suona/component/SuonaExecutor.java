package cn.cocowwy.suona.component;

import cn.cocowwy.handler.IMethodHandler;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Suona 方法调度中心
 * @author Cocowwy
 * @create 2022-04-04-21:37
 */
public class SuonaExecutor {
    private static final ConcurrentMap<String, IMethodHandler> methodRepository = new ConcurrentHashMap<>(16);

    private SuonaExecutor() {
    }

    public static IMethodHandler registMethod(String name, IMethodHandler method) {
        return methodRepository.put(name, method);
    }

    public static void execute(String name) throws Exception {
        methodRepository.get(name).execute();
    }
}
