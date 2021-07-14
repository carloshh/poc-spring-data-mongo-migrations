package com.github.carloshh.poc.mongo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public record Account(@Id UUID id, String name, Boolean enabled) { }
