package org.caillou.company.model;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.UUID;

public interface Item {

    Path getPath();

    ItemType getType();

    String getPartialFingerPrint();

    String getCompleteFingerPrint();

    UUID getVolumeId();

    UUID getSearchUUID();

}
