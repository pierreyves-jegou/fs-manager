package org.caillou.company.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.nio.file.Path;
import java.util.UUID;

@Getter
@Setter
public class AbstractItem {

    Path path;

    String partialFingerPrint;

    String completeFingerPrint;

    UUID volumeId;

    UUID searchUUID;

}
