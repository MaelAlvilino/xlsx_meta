package com.example.testexlsx;

import lombok.Data;

@Data
public class Usuario {
  @ExcelElement(nome = "Nome completo", tipo = String.class, grupo = "")
  private String nome = "Emmanuel Alvilino";
  @ExcelElement(nome = "Email", tipo = String.class, grupo = "")
  private String email =  "email@void.com";
}
