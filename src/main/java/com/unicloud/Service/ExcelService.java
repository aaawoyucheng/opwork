package com.unicloud.Service;

import com.alibaba.fastjson.JSONObject;
import com.unicloud.model.BlogEntity;
import com.unicloud.model.UserEntity;
import com.unicloud.repository.BlogRepository;
import com.unicloud.repository.UserRepository;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ExcelService {
    @Autowired
    BlogRepository blogRepository;
    @Autowired
    UserRepository userRepository;

    public XSSFWorkbook getWB() {
        {
//            ServletOutputStream out = response.getOutputStream();
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFRow row = null;
            XSSFSheet sheet = null;
            Sort sort = new Sort(Sort.Direction.ASC, "user");
            List<BlogEntity> records = blogRepository.findAll(sort);
            CellRangeAddress cra = null;
            sheet = wb.createSheet("加班详情");
            XSSFCellStyle value = wb.createCellStyle();
            value.setBottomBorderColor(HSSFColor.BLACK.index);
            value.setTopBorderColor(HSSFColor.BLACK.index);
            value.setLeftBorderColor(HSSFColor.BLACK.index);
            value.setRightBorderColor(HSSFColor.BLACK.index);
            value.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            value.setBorderTop(HSSFCellStyle.BORDER_THIN);
            value.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            value.setBorderRight(HSSFCellStyle.BORDER_THIN);
            value.setVerticalAlignment(VerticalAlignment.CENTER);
            value.setWrapText(true);
            for (int i = 0; i < records.size() ; i++) {
                BlogEntity record = records.get(i);
                if (i % 2 == 0) {
                    int rownum = (i / 2) * 6;
                    short a = 2 * 256;
                    row = sheet.createRow(rownum);
                    row.createCell(0).setCellValue("姓名");
                    row.createCell(1).setCellValue(record.getuser().getname());
                    row.createCell(2).setCellValue("工号");
                    row.createCell(3).setCellValue(record.getuser().getnumber());
                    row.createCell(4).setCellValue("部门");
                    row.createCell(5).setCellValue("运营部");
                    for (int j = 0; j < 6; j++) {
                        XSSFCellStyle title = (XSSFCellStyle) value.clone();
                        title.setFillForegroundColor(HSSFColor.LIME.index);
                        title.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                        row.getCell(j).setCellStyle(title);
                    }
                    rownum += 1;
                    row = sheet.createRow(rownum);
                    row.setHeight(a);
                    row.createCell(0).setCellValue("事由");
                    row.createCell(1).setCellValue(record.getTitle());
                    row.createCell(4).setCellValue("事由ID");
                    row.createCell(5).setCellValue(record.getTitleId());
                    List<Integer> cells = Arrays.asList(new Integer[]{0, 1, 4, 5});
                    for (int j : cells) {
                        row.getCell(j).setCellStyle(value);
                    }
                    cra = new CellRangeAddress(rownum, rownum, 1, 3);
                    sheet.addMergedRegion(cra);
                    RegionUtil.setBorderBottom(1, cra, sheet, wb); // 下边框
                    RegionUtil.setBorderLeft(1, cra, sheet, wb); // 左边框
                    RegionUtil.setBorderRight(1, cra, sheet, wb); // 有边框
                    RegionUtil.setBorderTop(1, cra, sheet, wb); // 上边框
                    rownum += 1;
                    row = sheet.createRow(rownum);
                    row.createCell(0).setCellValue("加班时间");
                    row.createCell(1).setCellValue(record.getPubDate());
                    cells = Arrays.asList(new Integer[]{0, 1});
                    for (int j : cells) {
                        row.getCell(j).setCellStyle(value);
                    }
                    cra = new CellRangeAddress(rownum, rownum, 1, 5);
                    sheet.addMergedRegion(cra);
                    RegionUtil.setBorderBottom(1, cra, sheet, wb); // 下边框
                    RegionUtil.setBorderLeft(1, cra, sheet, wb); // 左边框
                    RegionUtil.setBorderRight(1, cra, sheet, wb); // 有边框
                    RegionUtil.setBorderTop(1, cra, sheet, wb); // 上边框
                    rownum += 1;
                    row = sheet.createRow(rownum);
                    row.setHeight(a);
                    row.createCell(0).setCellValue("工作地点及内容");
                    row.createCell(1).setCellValue(record.getContent());
                    cells = Arrays.asList(new Integer[]{0, 1});
                    for (int j : cells) {
                        row.getCell(j).setCellStyle(value);
                    }
                    cra = new CellRangeAddress(rownum, rownum, 1, 5);
                    sheet.addMergedRegion(cra);
                    RegionUtil.setBorderBottom(1, cra, sheet, wb); // 下边框
                    RegionUtil.setBorderLeft(1, cra, sheet, wb); // 左边框
                    RegionUtil.setBorderRight(1, cra, sheet, wb); // 有边框
                    RegionUtil.setBorderTop(1, cra, sheet, wb); // 上边框
                    rownum += 1;
                    row = sheet.createRow(rownum);
                    row.createCell(0).setCellValue("部门主管");
                    row.createCell(1).setCellValue("董颖");
                    row.createCell(2).setCellValue("");
                    row.createCell(3).setCellValue("CHRO");
                    cells = Arrays.asList(new Integer[]{0, 1, 2, 3});
                    for (int j : cells) {
                        row.getCell(j).setCellStyle(value);
                    }
                    cra = new CellRangeAddress(rownum, rownum, 4, 5);
                    sheet.addMergedRegion(cra);
                    RegionUtil.setBorderBottom(1, cra, sheet, wb); // 下边框
                    RegionUtil.setBorderLeft(1, cra, sheet, wb); // 左边框
                    RegionUtil.setBorderRight(1, cra, sheet, wb); // 有边框
                    RegionUtil.setBorderTop(1, cra, sheet, wb); // 上边框
                } else {
                    int rownum = ((i - 1) / 2) * 6;
                    short a = 2 * 256;
                    row = sheet.getRow(rownum);
                    row.createCell(7).setCellValue("姓名");
                    row.createCell(8).setCellValue(record.getuser().getname());
                    row.createCell(9).setCellValue("工号");
                    row.createCell(10).setCellValue(record.getuser().getnumber());
                    row.createCell(11).setCellValue("部门");
                    row.createCell(12).setCellValue("运营部");
                    for (int j = 0; j < 6; j++) {
                        XSSFCellStyle title = (XSSFCellStyle) value.clone();
                        title.setFillForegroundColor(HSSFColor.LIME.index);
                        title.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                        row.getCell(j + 7).setCellStyle(title);
                    }
                    rownum += 1;
                    row = sheet.getRow(rownum);
                    row.setHeight(a);
                    row.createCell(7).setCellValue("事由");
                    row.createCell(8).setCellValue(record.getTitle());
                    row.createCell(11).setCellValue("事由ID");
                    row.createCell(12).setCellValue(record.getTitleId());
                    List<Integer> cells = Arrays.asList(new Integer[]{0, 1, 4, 5});
                    for (int j : cells) {
                        row.getCell(j + 7).setCellStyle(value);
                    }
                    cra = new CellRangeAddress(rownum, rownum, 8, 10);
                    sheet.addMergedRegion(cra);
                    rownum += 1;
                    row = sheet.getRow(rownum);
                    row.createCell(7).setCellValue("加班时间");
                    row.createCell(8).setCellValue(record.getPubDate());
                    cells = Arrays.asList(new Integer[]{0, 1});
                    for (int j : cells) {
                        row.getCell(j + 7).setCellStyle(value);
                    }
                    cra = new CellRangeAddress(rownum, rownum, 8, 12);
                    RegionUtil.setBorderBottom(1, cra, sheet, wb); // 下边框
                    RegionUtil.setBorderLeft(1, cra, sheet, wb); // 左边框
                    RegionUtil.setBorderRight(1, cra, sheet, wb); // 有边框
                    RegionUtil.setBorderTop(1, cra, sheet, wb); // 上边框
                    sheet.addMergedRegion(cra);
                    rownum += 1;
                    row = sheet.getRow(rownum);
                    row.setHeight(a);
                    row.createCell(7).setCellValue("工作地点及内容");
                    row.createCell(8).setCellValue(record.getContent());
                    cells = Arrays.asList(new Integer[]{0, 1});
                    for (int j : cells) {
                        row.getCell(j + 7).setCellStyle(value);
                    }
                    cra = new CellRangeAddress(rownum, rownum, 8, 12);
                    sheet.addMergedRegion(cra);
                    RegionUtil.setBorderBottom(1, cra, sheet, wb); // 下边框
                    RegionUtil.setBorderLeft(1, cra, sheet, wb); // 左边框
                    RegionUtil.setBorderRight(1, cra, sheet, wb); // 有边框
                    RegionUtil.setBorderTop(1, cra, sheet, wb); // 上边框
                    rownum += 1;
                    row = sheet.getRow(rownum);
                    row.createCell(7).setCellValue("部门主管");
                    row.createCell(8).setCellValue("董颖");
                    row.createCell(9).setCellValue("");
                    row.createCell(10).setCellValue("CHRO");
                    cells = Arrays.asList(new Integer[]{0, 1, 2, 3});
                    for (int j : cells) {
                        row.getCell(j + 7).setCellStyle(value);
                    }
                    cra = new CellRangeAddress(rownum, rownum, 11, 12);
                    sheet.addMergedRegion(cra);
                    RegionUtil.setBorderBottom(1, cra, sheet, wb); // 下边框
                    RegionUtil.setBorderLeft(1, cra, sheet, wb); // 左边框
                    RegionUtil.setBorderRight(1, cra, sheet, wb); // 有边框
                    RegionUtil.setBorderTop(1, cra, sheet, wb); // 上边框
                }
                sheet.setColumnWidth(5, 40 * 256);
                sheet.setColumnWidth(12, 40 * 256);
            }
            sheet = wb.createSheet("加班汇总");
            sheet.setColumnWidth(4, 14 * 256);
            sheet.setColumnWidth(5, 12 * 256);
            sheet.setColumnWidth(6, 18 * 256);
            sheet.setColumnWidth(7, 10 * 256);
            ArrayList<JSONObject> collect = new ArrayList();
            row = sheet.createRow(1);
            row.createCell(1).setCellValue("部门");
            row.createCell(2).setCellValue("工号");
            row.createCell(3).setCellValue("姓名");
            row.createCell(4).setCellValue("加班时长分类");
            XSSFCellStyle value2 = wb.createCellStyle();
            java.awt.Color color2 = new java.awt.Color(101, 151, 237);
            XSSFColor color = new XSSFColor(color2);
            Font font2 = wb.createFont();
            font2.setFontName("Microsoft YaHei");
            font2.setFontHeightInPoints((short) 10);
            font2.setColor(HSSFColor.WHITE.index);
            font2.setBold(true);
            value2.setFont(font2);
            value2.setFillForegroundColor(color);
            value2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            value2.setBorderRight(HSSFCellStyle.BORDER_THIN);
            value2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            value2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            value2.setBorderTop(HSSFCellStyle.BORDER_THIN);
            value2.setBottomBorderColor(new XSSFColor(new java.awt.Color(192, 192, 192)));
            value2.setTopBorderColor(new XSSFColor(new java.awt.Color(192, 192, 192)));
            value2.setLeftBorderColor(new XSSFColor(new java.awt.Color(192, 192, 192)));
            value2.setRightBorderColor(new XSSFColor(new java.awt.Color(192, 192, 192)));
            value2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            value2.setBorderTop(HSSFCellStyle.BORDER_THIN);
            value2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            value2.setBorderRight(HSSFCellStyle.BORDER_THIN);
            value2.setAlignment(HorizontalAlignment.CENTER);
            for (int j = 1; j < 5; j++) {
                row.getCell(j).setCellStyle(value2);
            }
            row=sheet.createRow(2);
            row.createCell(4).setCellValue("工作日加班时长");
            row.createCell(5).setCellValue("周末加班时长");
            row.createCell(6).setCellValue("法定节假日加班时长");
            row.createCell(7).setCellValue("总加班时长");
            for (int j = 4; j < 8; j++) {
                row.getCell(j).setCellStyle(value2);
            }
            cra = new CellRangeAddress(0, 0, 1, 7);
            sheet.addMergedRegion(cra);
            RegionUtil.setBorderBottom(1, cra, sheet, wb); // 下边框
            RegionUtil.setBorderLeft(1, cra, sheet, wb); // 左边框
            RegionUtil.setBorderRight(1, cra, sheet, wb); // 有边框
            RegionUtil.setBorderTop(1, cra, sheet, wb); // 上边框
            cra = new CellRangeAddress(1, 2, 1, 1);
            sheet.addMergedRegion(cra);
            RegionUtil.setBorderBottom(1, cra, sheet, wb); // 下边框
            RegionUtil.setBorderLeft(1, cra, sheet, wb); // 左边框
            RegionUtil.setBorderRight(1, cra, sheet, wb); // 有边框
            RegionUtil.setBorderTop(1, cra, sheet, wb); // 上边框
            cra = new CellRangeAddress(1, 2, 2, 2);
            sheet.addMergedRegion(cra);
            RegionUtil.setBorderBottom(1, cra, sheet, wb); // 下边框
            RegionUtil.setBorderLeft(1, cra, sheet, wb); // 左边框
            RegionUtil.setBorderRight(1, cra, sheet, wb); // 有边框
            RegionUtil.setBorderTop(1, cra, sheet, wb); // 上边框
            cra = new CellRangeAddress(1, 2, 3, 3);
            sheet.addMergedRegion(cra);
            RegionUtil.setBorderBottom(1, cra, sheet, wb); // 下边框
            RegionUtil.setBorderLeft(1, cra, sheet, wb); // 左边框
            RegionUtil.setBorderRight(1, cra, sheet, wb); // 有边框
            RegionUtil.setBorderTop(1, cra, sheet, wb); // 上边框
            cra = new CellRangeAddress(1, 1, 4, 7);
            sheet.addMergedRegion(cra);
            RegionUtil.setBorderBottom(1, cra, sheet, wb); // 下边框
            RegionUtil.setBorderLeft(1, cra, sheet, wb); // 左边框
            RegionUtil.setBorderRight(1, cra, sheet, wb); // 有边框
            RegionUtil.setBorderTop(1, cra, sheet, wb); // 上边框
            for (UserEntity userEntity : userRepository.findAll()) {
                float normal = 0;
                float weekend = 0;
                float holiday = 0;
                float total = 0;
                for (BlogEntity entity : userEntity.getblogs()) {
                    total += entity.getCosttime();
                    switch (entity.getType()) {
                        case "日常":
                            normal += entity.getCosttime();
                            break;
                        case "周末":
                            weekend += entity.getCosttime();
                            break;
                        case "节假日":
                            holiday += entity.getCosttime();
                            break;
                    }
                }
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", userEntity.getname());
                jsonObject.put("number", userEntity.getnumber());
                jsonObject.put("normal", normal);
                jsonObject.put("weekend", weekend);
                jsonObject.put("holiday", holiday);
                jsonObject.put("total", total);
                collect.add(jsonObject);
            }
            for (int i = 0; i < collect.size(); i++) {
                System.out.println(collect.get(i));
                row = sheet.createRow(i + 3);
                row.createCell(1).setCellValue("运营部");
                row.createCell(2).setCellValue(collect.get(i).getString("number"));
                row.createCell(3).setCellValue(collect.get(i).getString("name"));
                row.createCell(4).setCellValue(collect.get(i).getFloat("normal"));
                row.createCell(5).setCellValue(collect.get(i).getFloat("weekend"));
                row.createCell(6).setCellValue(collect.get(i).getFloat("holiday"));
                row.createCell(7).setCellValue(collect.get(i).getFloat("total"));
                value2 = wb.createCellStyle();
                color2 = new java.awt.Color(240, 240, 240);
                color = new XSSFColor(color2);
                value2.setFillForegroundColor(color);
                value2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                value2.setBottomBorderColor(new XSSFColor(new java.awt.Color(192, 192, 192)));
                value2.setTopBorderColor(new XSSFColor(new java.awt.Color(192, 192, 192)));
                value2.setLeftBorderColor(new XSSFColor(new java.awt.Color(192, 192, 192)));
                value2.setRightBorderColor(new XSSFColor(new java.awt.Color(192, 192, 192)));
                value2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                value2.setBorderTop(HSSFCellStyle.BORDER_THIN);
                value2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                value2.setBorderRight(HSSFCellStyle.BORDER_THIN);

                Font font = wb.createFont();
                font.setFontName("Microsoft YaHei");
                font.setFontHeightInPoints((short) 9);
                value2.setFont(font);
                for (int j = 1; j < 8; j++) {
                    row.getCell(j).setCellStyle(value2);
                }
            }
            return wb;
        }
    }
}
