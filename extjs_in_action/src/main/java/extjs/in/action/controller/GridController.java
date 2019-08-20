package extjs.in.action.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by DINGJUN on 2019.08.18.
 */
@Controller
public class GridController {

    /**
     * 访问路径: http://localhost/read
     * @return
     */
    @RequestMapping("read")
    @ResponseBody
    public String read() {
        System.out.println("list data");
        String result = "{\"data\": [{\"id\":\"1\",\"departmentId\":\"1\",\"dateHired\":\"20190818\",\"dateFired\":\"20190819\",\"dob\":\"20190816\",\"firstName\":\"f1\",\"lastName\":\"l1\",\"title\":\"abc1\",\"street\":\"sh1\",\"city\":\"sha1\",\"state\":\"s1\",\"zip\":\"z1\"}," +
                "{\"id\":\"2\",\"departmentId\":\"2\",\"dateHired\":\"20190818\",\"dateFired\":\"20190819\",\"dob\":\"20190816\",\"firstName\":\"f2\",\"lastName\":\"l2\",\"title\":\"abc2\",\"street\":\"sh2\",\"city\":\"sha2\",\"state\":\"s2\",\"zip\":\"z2\"}," +
                "{\"id\":\"2\",\"departmentId\":\"3\",\"dateHired\":\"20190814\",\"dateFired\":\"20190811\",\"dob\":\"20190812\",\"firstName\":\"f3\",\"lastName\":\"l3\",\"title\":\"abc3\",\"street\":\"sh3\",\"city\":\"sha3\",\"state\":\"s3\",\"zip\":\"z3\"}]," +
                "\"totalCount\":2,\"pageSize\":50,\"success\":true,\"start\":0,\"limit\":50}";
//        return "GridReadController"; // ajax访问返回视图,会有html标签在外面嵌套
        return result;
    }
    @RequestMapping("combo/read")
    @ResponseBody
    public String comboRead() {
        System.out.println("combo list data");
        String result = "{\"data\":[{\"id\":\"1\",\"state\":\"s1\"},{\"id\":\"2\",\"state\":\"s2\"},{\"id\":\"3\",\"state\":\"s3\"}],success:true}";
        return result;
    }
}
