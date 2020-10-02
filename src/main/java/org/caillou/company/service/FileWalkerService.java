package org.caillou.company.service;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.caillou.company.model.FileItem;
import org.caillou.company.model.Item;
import org.caillou.company.model.Volume;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Duration;

import static java.nio.file.FileVisitResult.CONTINUE;

@ApplicationScoped
public class FileWalkerService extends SimpleFileVisitor<Path> {

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attr) {
        if (attr.isSymbolicLink()) {
            System.out.format("Symbolic link: %s ", file);
        } else if (attr.isRegularFile()) {
            System.out.format("Regular file: %s ", file);
        } else {
            System.out.format("Other: %s ", file);
        }
        System.out.println("(" + attr.size() + "bytes)");
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file,
                                           IOException exc) {
        System.err.println(exc);
        return CONTINUE;
    }

    public Multi<Item> walkFileSystem(Volume volume){

        boolean baseDirectoriesExist = volume.getVolumePathList().stream().allMatch(volumePath ->
                volumePath.getBasePath().toFile().exists()
                        && volumePath.getBasePath().toFile().isDirectory());

//        Uni.createFrom().



        FileItem fileItem = new FileItem();
        fileItem.setPartialFingerPrint("lklk");
        FileItem fileItem2 = new FileItem();
        fileItem2.setPartialFingerPrint("jkj");
        Multi<FileItem> items = Multi.createFrom().items(fileItem, fileItem2);
        return  Multi.createFrom().ticks()
                .every(Duration.ofSeconds(2))
                .onItem()
                .transform(x -> new FileItem());


        //return Multi.createFrom().items(fileItem, fileItem2);
    }

}
