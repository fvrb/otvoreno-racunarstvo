package com.otvrac.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class RefreshService {

    @Autowired
    private MuzejRepository muzejRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public void refreshFiles() {

        List<Muzej> museums = muzejRepository.findAll();

        try {
            // JSON
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            Path jsonPath = Paths.get("data/muzeji.json");

            Files.createDirectories(jsonPath.getParent());

            Map<String, Object> jsonWrapper = new LinkedHashMap<>();
            Map<String, String> context = new LinkedHashMap<>();
            context.put("@vocab", "http://schema.org/");
            context.put("nazivMuzeja", "name");
            context.put("webStranica", "url");

            jsonWrapper.put("@context", context);
            jsonWrapper.put("muzeji", museums);

            objectMapper.writeValue(jsonPath.toFile(), jsonWrapper);

            // CSV
            Path csvPath = Paths.get("data/muzeji.csv");

            StringBuilder csv = new StringBuilder();
            csv.append("nazivMuzeja,drzava,grad,godinaOsnivanja,tipMuzeja,velicinaKolekcije,posjetitelji,izlozbeniProstor,webStranica,onlineSetnja,nazivEksponata,tipEksponata\n");

            museums.forEach(m -> {
                StringBuilder mCsv = new StringBuilder();
                mCsv.append(formatComma(m.getNazivMuzeja())).append(",").append(formatComma(m.getDrzava())).append(",").append(formatComma(m.getGrad())).append(",").append(formatComma(m.getGodinaOsnivanja())).append(",").append(formatComma(m.getTipMuzeja())).append(",").append(formatComma(m.getVelicinaKolekcije())).append(",").append(formatComma(m.getPosjetitelji())).append(",").append(formatComma(m.getIzlozbeniProstor())).append(",").append(formatComma(m.getWebStranica())).append(",").append(formatComma(m.getOnlineSetnja())).append(",");

                if (m.getEksponati().isEmpty()) csv.append(mCsv).append(",").append("\n");
                else m.getEksponati().forEach(e -> {
                        csv.append(mCsv).append(formatComma(e.getNazivEksponata())).append(",").append(formatComma(e.getTipEksponata())).append("\n");
                    });
            });

            Files.write(csvPath, csv.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String formatComma(Object val) {
        if  (val == null) return "";
        String str = val.toString();
        return str.contains(",") ? "\""+str+"\"" : str;
    }
}
