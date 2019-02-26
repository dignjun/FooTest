package hello;

import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 控制器
 * Created by DINGJUN on 2018/4/25.
 */
@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

    /**
     * restful请求，一个参数的使用
     * @param param
     * @return
     */
    @RequestMapping(value = "/restful/{param}",method = RequestMethod.GET)
    public String restful(@PathVariable String param) {
        System.out.println("------");
        System.out.println(param);
        System.out.println("------");

        return param + System.currentTimeMillis();
    }

    /**
     * restful请求，两个参数的使用
     * @param param
     * @param param2
     * @return
     */
    @GetMapping("/restful/{param}/2/{param2}")
    public String restful(@PathVariable String param,@PathVariable String param2) {
        return param + param2;
    }

}
