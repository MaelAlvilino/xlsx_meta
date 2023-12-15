package com.example.testexlsx;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.List;

@org.springframework.stereotype.Service
public class Service {

  public static <T> void gerarExcel(List<T> dados, Class<T> classeEntidade) {
    try (Workbook workbook = new XSSFWorkbook()) {
      Sheet sheet = workbook.createSheet("Dados");

      Row headerRow = sheet.createRow(0);
      Field[] campos = classeEntidade.getDeclaredFields();
      int colunaAtual = 0;

      for (Field campo : campos) {
        if (campo.isAnnotationPresent(ExcelElement.class)) {
          ExcelElement anotacao = campo.getAnnotation(ExcelElement.class);
          String nomeColuna = anotacao.nome();
          Cell cell = headerRow.createCell(colunaAtual++);
          cell.setCellValue(nomeColuna);
        }
      }

      int linhaAtual = 1;

      for (T dado : dados) {
        Row dataRow = sheet.createRow(linhaAtual++);
        colunaAtual = 0;

        for (Field campo : campos) {
          if (campo.isAnnotationPresent(ExcelElement.class)) {
            campo.setAccessible(true);
            ExcelElement anotacao = campo.getAnnotation(ExcelElement.class);
            Cell cell = dataRow.createCell(colunaAtual++);
            cell.setCellValue(campo.get(dado).toString());
          }
        }
      }

      try (FileOutputStream fileOut = new FileOutputStream("dados_user.xlsx")) {
        workbook.write(fileOut);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
