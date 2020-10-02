package org.caillou.company.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Volume {

    UUID id;

    String name;

    List<VolumePath> volumePathList;

}
