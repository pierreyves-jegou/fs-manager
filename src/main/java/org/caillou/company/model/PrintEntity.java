package org.caillou.company.model;

import lombok.Data;

import java.nio.file.Path;
import java.util.UUID;

@Data
public class PrintEntity {

    UUID id;
    Volume firstVolume;
    Volume secondVolume;

}
