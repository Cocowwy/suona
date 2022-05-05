package cn.cocowwy.suona.component.communication;


import cn.cocowwy.suona.context.SuonaContextHolder;
import cn.cocowwy.suona.handler.SuonaExecutor;
import cn.cocowwy.suona.model.CallBack;
import cn.cocowwy.suona.model.CallMethods;
import org.springframework.web.bind.annotation.*;

/**
 * 节点通讯：
 * V1：采用内嵌 HTTP 接口的形式进行
 *
 * @author cocowwy.cn
 * @create 2022-04-04-21:01
 */
@RestController
@RequestMapping("suona")
public class SuonaReceive {

    @PostMapping("call")
    public CallBack call(@RequestBody CallMethods call) {

        try {
            suonaBiz(call);
        } catch (Exception exception) {
            //todo fixme

            return CallBack.notOk();
        } finally {
            SuonaContextHolder.clean();
        }

        return CallBack.oK();
    }

    @GetMapping("/aa")
    public CallBack call() {

        System.out.println("1111");
        return CallBack.oK();
    }

    /**
     * 使用 ThreadLocal进行线程标记，
     * 来解决 Suona 的 网状循环（reticular circulation）调用问题
     * @param call
     */
    private void suonaBiz(CallMethods call) throws Exception {
        SuonaContextHolder.label();
        SuonaExecutor.execute(call.getName());
    }
}
