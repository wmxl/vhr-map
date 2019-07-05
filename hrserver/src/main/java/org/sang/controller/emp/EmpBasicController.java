package org.sang.controller.emp;

import org.sang.bean.Employee;
import org.sang.bean.Position;
import org.sang.bean.RespBean;
import org.sang.common.EmailRunnable;
import org.sang.common.poi.PoiUtils;
import org.sang.service.EmpService;
import org.sang.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * Created by sang on 2018/1/12.
 */
@RestController
@RequestMapping("/employee/basic")
public class EmpBasicController {
    @Autowired
    EmpService empService;
    @Autowired
    PositionService positionService;//职位 -> 公司性质
    @Autowired
    ExecutorService executorService;
    @Autowired
    TemplateEngine templateEngine;
    @Autowired
    JavaMailSender javaMailSender;

    @RequestMapping(value = "/basicdata", method = RequestMethod.GET)
    //这里的名字为啥叫getAllNations? 有时间改了试试
    public Map<String, Object> getAllNations() {
        Map<String, Object> map = new HashMap<>();
//        map.put("positions", positionService.getAllPos());
        return map;
    }

    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    public RespBean addEmp(Employee employee) {
        System.err.println("进入添加controller:");
        if (empService.addEmp(employee) == 1) {
            System.err.println("1:");

            executorService.execute(new EmailRunnable(employee,
                    javaMailSender, templateEngine));
            System.err.println(employee);
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败!");
    }

    @RequestMapping(value = "/emp", method = RequestMethod.PUT)
    public RespBean updateEmp(Employee employee) {
        if (empService.updateEmp(employee) == 1) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @RequestMapping(value = "/emp/{ids}", method = RequestMethod.DELETE)
    public RespBean deleteEmpById(@PathVariable String ids) {
        if (empService.deleteEmpById(ids)) {
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

    @RequestMapping(value = "/emp", method = RequestMethod.GET)
    public Map<String, Object> getEmployeeByPage(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "") String keywords,
            @RequestParam(defaultValue = "") String keywords_range,
            @RequestParam(defaultValue = "") String a,
            @RequestParam(defaultValue = "") String keywords_products,
            Double propertyValueMin
            ,Double propertyValueMax
            ,Double registerCapitalMin
            ,Double registerCapitalMax
    ) {
        System.err.println("进入 GET EmpBasicController: getEmployeeByPage");
        System.out.println("keywords_range = " + keywords_range);
        System.out.println("a = " + a);
        System.out.println("keywords_products = " + keywords_products);
        Map<String, Object> map = new HashMap<>();
        List<Employee> employeeByPage = empService.getEmployeeByPage(page, size, keywords, keywords_range, keywords_products,
                propertyValueMin, propertyValueMax, registerCapitalMin, registerCapitalMax );
        Long count = empService.getCountByKeywords(keywords, keywords_range, keywords_products,
                propertyValueMin, propertyValueMax, registerCapitalMin, registerCapitalMax);
        map.put("emps", employeeByPage);
        map.put("count", count);
        return map;
    }

    @RequestMapping(value = "/exportEmp", method = RequestMethod.GET)
    public ResponseEntity<byte[]> exportEmp() {
        return PoiUtils.exportEmp2Excel(empService.getAllEmployees());
    }

    @RequestMapping(value = "/importEmp", method = RequestMethod.POST)
    public RespBean importEmp(MultipartFile file) {
        List<Employee> emps = PoiUtils.importEmp2List(file,
                positionService.getAllPos()
        );
        System.err.println("EmpBasicController:");
        System.err.println(emps);

        System.err.println("emps.size() = " + emps.size());
        int a = empService.addEmps(emps);
        System.err.println("empService.addEmps(emps) = " + a);

        if (a == emps.size()) {
            return RespBean.ok("导入成功!");
        }
        return RespBean.error("导入失败!");
    }
}
