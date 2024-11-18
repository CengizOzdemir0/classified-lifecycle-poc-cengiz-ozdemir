package com.cengiz.ilanapiproject.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Cengiz ÖZDEMİR
 * @created 12/11/2024
 */

@Component
public class BadWordsLoader {

    @Value("${spring.bad.location:Badwords.txt}")
    private String badPath;

    private Set<String> badWords = new HashSet<>();

    @PostConstruct
    public void loadbadWords() throws IOException {
        Path path = Paths.get(badPath);
        List<String> lines = Files.readAllLines(path);

        for (String line : lines) {
            String[] words = line.split("\\s+");
            for (String word : words) {
                badWords.add(word.toLowerCase());
            }
        }
    }

    public boolean containsbadWord(String text) {
        for (String word : badWords) {
            if (text.toLowerCase().contains(word)) {
                return true;
            }
        }
        return false;
    }
}

