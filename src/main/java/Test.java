import com.alibaba.fastjson.JSON;
import com.gaussic.model.BlogEntity;
import com.gaussic.repository.BlogRepository;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xwpf.usermodel.VerticalAlign;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:mvc-dispatcher-servlet.xml"})
public class Test {
    @Autowired
    BlogRepository blogRepository;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        System.out.println("==============单元测试开始=============");
    }
    @org.junit.Test
    public void test(){
        CellRangeAddress cra =null;
        Sort sort=new Sort(Sort.Direction.ASC,"userByUserId");
        XSSFWorkbook wb=new XSSFWorkbook();
        XSSFSheet sheet=wb.createSheet("详情");
        XSSFCellStyle value=wb.createCellStyle();
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
        List<BlogEntity> records=blogRepository.findAll(sort);
        for(int i=0;i<records.size()-1;i++){
//            System.out.println(records.get(i));
            if (i%2==0){
                BlogEntity record=records.get(i);
                BlogEntity record2=records.get(i+1);
                int rownum=(i/2)*6;
                short a=3*256;
                Row row  =sheet.createRow(rownum);
                row.createCell(0).setCellValue("姓名");
                row.createCell(1).setCellValue(record.getUserByUserId().getFirstName());
                row.createCell(2).setCellValue("工号");
                row.createCell(3).setCellValue(record.getUserByUserId().getNickname());
                row.createCell(4).setCellValue("部门");
                row.createCell(5).setCellValue("运营部");
                row.createCell(7).setCellValue("姓名");
                row.createCell(8).setCellValue(record2.getUserByUserId().getFirstName());
                row.createCell(9).setCellValue("工号");
                row.createCell(10).setCellValue(record2.getUserByUserId().getNickname());
                row.createCell(11).setCellValue("部门");
                row.createCell(12).setCellValue("运营部");
                for (int j=0;j<6;j++){
                    XSSFCellStyle title=(XSSFCellStyle)value.clone();
                    title.setFillForegroundColor(HSSFColor.LIME.index);
                    title.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    row.getCell(j).setCellStyle(title);
                    row.getCell(j+7).setCellStyle(title);
                }
                rownum+=1;
                row=sheet.createRow(rownum);
                row.setHeight(a);

                row.createCell(0).setCellValue("事由");
                row.createCell(1).setCellValue(record.getTitle());
                row.createCell(4).setCellValue("事由ID");
                row.createCell(5).setCellValue(record.getTitleId());
                row.createCell(7).setCellValue("事由");
                row.createCell(8).setCellValue(record2.getTitle());
                row.createCell(11).setCellValue("事由ID");
                row.createCell(12).setCellValue(record2.getTitleId());
                List<Integer> cells=Arrays.asList(new Integer[]{0,1,4,5});
                for(int j : cells ){
                    row.getCell(j).setCellStyle(value);
                    row.getCell(j+7).setCellStyle(value);
                }
                cra=new CellRangeAddress(rownum, rownum, 1, 3);
                sheet.addMergedRegion(cra);
                RegionUtil.setBorderBottom(1, cra, sheet,wb); // 下边框
                RegionUtil.setBorderLeft(1, cra, sheet,wb); // 左边框
                RegionUtil.setBorderRight(1, cra, sheet,wb); // 有边框
                RegionUtil.setBorderTop(1, cra, sheet,wb); // 上边框
                cra=new CellRangeAddress(rownum, rownum, 8, 10);
                sheet.addMergedRegion(cra);
                rownum+=1;
                row=sheet.createRow(rownum);
                row.createCell(0).setCellValue("加班时间");
                row.createCell(1).setCellValue(record.getPubDate());
                row.createCell(7).setCellValue("加班时间");
                row.createCell(8).setCellValue(record2.getPubDate());
                cells=Arrays.asList(new Integer[]{0,1});
                for(int j : cells ){
                    row.getCell(j).setCellStyle(value);
                    row.getCell(j+7).setCellStyle(value);
                }                cra=new CellRangeAddress(rownum, rownum, 1, 5);
                sheet.addMergedRegion(cra);
                RegionUtil.setBorderBottom(1, cra, sheet,wb); // 下边框
                RegionUtil.setBorderLeft(1, cra, sheet,wb); // 左边框
                RegionUtil.setBorderRight(1, cra, sheet,wb); // 有边框
                RegionUtil.setBorderTop(1, cra, sheet,wb); // 上边框
                cra=new CellRangeAddress(rownum, rownum, 8, 12);
                RegionUtil.setBorderBottom(1, cra, sheet,wb); // 下边框
                RegionUtil.setBorderLeft(1, cra, sheet,wb); // 左边框
                RegionUtil.setBorderRight(1, cra, sheet,wb); // 有边框
                RegionUtil.setBorderTop(1, cra, sheet,wb); // 上边框
                sheet.addMergedRegion(cra);
                rownum+=1;
                row=sheet.createRow(rownum);
                row.setHeight(a);
                row.createCell(0).setCellValue("工作地点及内容");
                row.createCell(1).setCellValue(record.getContent());
                row.createCell(7).setCellValue("工作地点及内容");
                row.createCell(8).setCellValue(record2.getContent());
                cells=Arrays.asList(new Integer[]{0,1});
                for(int j : cells ){
                    row.getCell(j).setCellStyle(value);
                    row.getCell(j+7).setCellStyle(value);
                }
                cra=new CellRangeAddress(rownum, rownum, 1, 5);
                sheet.addMergedRegion(cra);
                RegionUtil.setBorderBottom(1, cra, sheet,wb); // 下边框
                RegionUtil.setBorderLeft(1, cra, sheet,wb); // 左边框
                RegionUtil.setBorderRight(1, cra, sheet,wb); // 有边框
                RegionUtil.setBorderTop(1, cra, sheet,wb); // 上边框
                cra=new CellRangeAddress(rownum, rownum, 8, 12);
                sheet.addMergedRegion(cra);
                RegionUtil.setBorderBottom(1, cra, sheet,wb); // 下边框
                RegionUtil.setBorderLeft(1, cra, sheet,wb); // 左边框
                RegionUtil.setBorderRight(1, cra, sheet,wb); // 有边框
                RegionUtil.setBorderTop(1, cra, sheet,wb); // 上边框
                rownum+=1;
                row=sheet.createRow(rownum);
                row.createCell(0).setCellValue("部门主管");
                row.createCell(1).setCellValue("董颖");
                row.createCell(2).setCellValue("");
                row.createCell(3).setCellValue("CHRO");
                row.createCell(7).setCellValue("部门主管");
                row.createCell(8).setCellValue("董颖");
                row.createCell(9).setCellValue("");
                row.createCell(10).setCellValue("CHRO");
                cells=Arrays.asList(new Integer[]{0,1,2,3});
                for(int j : cells ){
                    row.getCell(j).setCellStyle(value);
                    row.getCell(j+7).setCellStyle(value);
                }
                cra=new CellRangeAddress(rownum, rownum, 4, 5);
                sheet.addMergedRegion(cra);
                RegionUtil.setBorderBottom(1, cra, sheet,wb); // 下边框
                RegionUtil.setBorderLeft(1, cra, sheet,wb); // 左边框
                RegionUtil.setBorderRight(1, cra, sheet,wb); // 有边框
                RegionUtil.setBorderTop(1, cra, sheet,wb); // 上边框
                cra=new CellRangeAddress(rownum, rownum, 11, 12);
                sheet.addMergedRegion(cra);
                RegionUtil.setBorderBottom(1, cra, sheet,wb); // 下边框
                RegionUtil.setBorderLeft(1, cra, sheet,wb); // 左边框
                RegionUtil.setBorderRight(1, cra, sheet,wb); // 有边框
                RegionUtil.setBorderTop(1, cra, sheet,wb); // 上边框

                sheet.setColumnWidth(0,14*256);
                sheet.setColumnWidth(7,14*256);
                sheet.setColumnWidth(5,40*256);
                sheet.setColumnWidth(12,40*256);
            }
        }
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("YYMMDDHHmmss");
        try {
            FileOutputStream out = out = new FileOutputStream(new File("D:"+File.separator+simpleDateFormat.format(new Date())+"aaa.xlsx"));
            wb.write(out);
            out.flush();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        System.out.println("==============单元测试结束=============");
    }
}
