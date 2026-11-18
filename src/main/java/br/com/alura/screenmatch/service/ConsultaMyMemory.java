package br.com.alura.screenmatch.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ConsultaMyMemory {
    public static String obterTraducao(String texto) {
        try {
            String textoCodificado = URLEncoder.encode(texto, StandardCharsets.UTF_8);
            String langpair = URLEncoder.encode("en|pt-br", StandardCharsets.UTF_8);
            String url = "https://api.mymemory.translated.net/get?q=" + textoCodificado + "&langpair=" + langpair;

            String json = new ConsumoApi().obterDados(url);
            JsonNode raiz = new ObjectMapper().readTree(json);
            return raiz.path("responseData").path("translatedText").asText(texto);
        } catch (Exception e) {
            System.out.println("Não foi possível traduzir: " + e.getMessage());
            return texto;
        }
    }
}