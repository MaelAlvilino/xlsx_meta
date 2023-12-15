package com.example.testexlsx;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

  @GetMapping("/gerar")
  public ResponseEntity<InputStreamResource> gerarExcel() throws IOException {
    List<Usuario> usuarios = new ArrayList<>();
    usuarios.add(new Usuario());

    Service.gerarExcel(usuarios, Usuario.class);

    InputStream inputStream = new FileInputStream("dados.xlsx");

    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Disposition", "attachment; filename=dados.xlsx");

    MediaType mediaType = MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

    return ResponseEntity.ok()
        .headers(headers)
        .contentType(mediaType)
        .body(new InputStreamResource(inputStream));
  }
}
