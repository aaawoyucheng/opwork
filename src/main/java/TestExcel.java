import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.unicloud.Service.ExcelService;
import com.unicloud.model.BlogEntity;
import com.unicloud.model.UserEntity;
import com.unicloud.repository.BlogRepository;
import com.unicloud.repository.UserRepository;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:mvc-dispatcher-servlet.xml"})

public class TestExcel {
    @Autowired
    ExcelService service;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        System.out.println("==============单元测试开始=============");
    }
    @Test
    @Transactional
    public void test(){

        try {
            FileOutputStream outputStream=new FileOutputStream(new File("D:"+File.separator+new SimpleDateFormat("YYMMddHHmmss").format(new Date())+"aaa.xlsx"));
            service.getWB().write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        System.out.println("==============单元测试结束=============");
    }
}
