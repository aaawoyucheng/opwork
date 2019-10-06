package com.unicloud.controller;

import com.alibaba.fastjson.JSONObject;
import com.unicloud.Service.ExcelService;
import com.unicloud.model.BlogEntity;
import com.unicloud.model.UserEntity;
import com.unicloud.repository.BlogRepository;
import com.unicloud.repository.UserRepository;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class ExcelController {
    @Autowired
    ExcelService excelService;
    //下载练习
    @RequestMapping(value="/excel")
    public ResponseEntity<byte[]> testDownload(HttpServletRequest request){
        ResponseEntity<byte[]> response=null ;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment", "1workbook.xlsx");// new String("线上消费记录".getBytes("GBK"),"iso-8859-1")
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

            ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
            excelService.getWB().write(outByteStream);
            return new ResponseEntity<byte[]>(outByteStream.toByteArray(), headers, HttpStatus.OK);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return response;
    }
}
