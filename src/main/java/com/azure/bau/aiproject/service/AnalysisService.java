package com.azure.bau.aiproject.service;
import com.azure.ai.textanalytics.TextAnalyticsClient;
import com.azure.ai.textanalytics.TextAnalyticsClientBuilder;
import com.azure.bau.aiproject.model.Analysis;
import com.azure.bau.aiproject.repository.AnalysisRepository;
import com.azure.core.credential.AzureKeyCredential;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AnalysisService {

    private final TextAnalyticsClient client;
    private final AnalysisRepository repository;

    public List<String> extractKeyPhrases(String text) {
        // Extracting key phrases for a document
        List<String> keyPhrases = client.extractKeyPhrases(text).stream().collect(Collectors.toList());
        Analysis analysis = new Analysis();
        analysis.setText(text);
        analysis.setKeyPhrases(String.join(", ", keyPhrases));
        repository.save(analysis);
        return keyPhrases;
    }

    public Page<Analysis> getHistory(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
