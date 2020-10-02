package org.caillou.company.model;

import lombok.*;

import java.nio.file.Path;
import java.util.UUID;

@Getter
@Setter
public class FileItem extends AbstractItem implements Item {

    @Override
    public ItemType getType() {
        return ItemType.FILE;
    }

}
