package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class InfoController {

    @GetMapping("/requisicao")
    public String requisicao(HttpServletRequest request) {
        String metodo = request.getMethod();
        String uri = request.getRequestURI();
        String protocolo = request.getProtocol();

        return String.format(
            "Método: %s<br>URI do Pedido: %s<br>Protocolo: %s",
            metodo, uri, protocolo
        );
    }

    @GetMapping("/cabecalhos")
    public String cabecalhos(
            @RequestHeader("host") String host,
            @RequestHeader("user-agent") String userAgent,
            @RequestHeader(value = "accept-encoding", required = false) String acceptEncoding,
            @RequestHeader(value = "accept-language", required = false) String acceptLanguage) {

        return String.format(
            "Host: %s<br>User-Agent: %s<br>Accept-Encoding: %s<br>Accept-Language: %s",
            host,
            userAgent,
            acceptEncoding != null ? acceptEncoding : "Não informado",
            acceptLanguage != null ? acceptLanguage : "Não informado"
        );
    }
}
