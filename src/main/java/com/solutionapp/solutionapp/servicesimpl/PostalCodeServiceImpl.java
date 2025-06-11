/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.solutionapp.solutionapp.servicesimpl;

import com.solutionapp.solutionapp.services.PostalCodeService;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author macbookpro
 */
@Service
public class PostalCodeServiceImpl implements PostalCodeService {

    @Override
    public Pair<HttpStatus, HashMap<String, Object>> get(String postalCode) {
          HashMap<String, Object> responseMap = new HashMap<>();
        HttpStatus status;

        try {
            String urlString = "https://viacep.com.br/ws/" + postalCode + "/json/";
            URL url = new URL(urlString);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            BufferedReader in;

            if (responseCode == HttpURLConnection.HTTP_OK) {
                in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder content = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }

                in.close();

                ObjectMapper mapper = new ObjectMapper();
                HashMap<String, Object> result = mapper.readValue(content.toString(), HashMap.class);

                // Verifica se o CEP é inválido (API retorna "erro": true)
                if (result.containsKey("erro")) {
                    responseMap.put("mensagem", "CEP não encontrado.");
                    status = HttpStatus.NOT_FOUND;
                } else {
                    responseMap.putAll(result);
                    status = HttpStatus.OK;
                }

            } else {
                responseMap.put("mensagem", "Erro ao consultar a API do ViaCEP.");
                status = HttpStatus.BAD_GATEWAY;
            }

        } catch (Exception e) {
            responseMap.put("mensagem", "Erro inesperado: " + e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return Pair.of(status, responseMap);
        
    }
    
}
