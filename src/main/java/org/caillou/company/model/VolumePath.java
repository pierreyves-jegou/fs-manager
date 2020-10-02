package org.caillou.company.model;

import lombok.Getter;
import lombok.Setter;

import java.nio.file.Path;
import java.util.List;

@Getter
@Setter
public class VolumePath {

    Path basePath;

    List<Path> excludedPaths;

}
