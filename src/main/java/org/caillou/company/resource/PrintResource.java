package org.caillou.company.resource;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.caillou.company.model.Item;
import org.caillou.company.model.PrintEntity;
import org.caillou.company.service.FileWalkerService;
import org.jboss.resteasy.annotations.SseElementType;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.Duration;
import java.util.UUID;

@Path("/prints")
public class PrintResource {

    @Inject
    FileWalkerService fileWalkerService;

    @PUT
    @Consumes("application/json")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @SseElementType(MediaType.APPLICATION_JSON)
    public Multi<Item> createPartialPrint(PrintEntity printEntity){
        Multi<Item> firstVolumeItems = fileWalkerService.walkFileSystem(printEntity.getFirstVolume());
        Multi<Item> secondVolumeItems = fileWalkerService.walkFileSystem(printEntity.getSecondVolume());
        return Multi.createBy().merging().streams(firstVolumeItems, secondVolumeItems);
    }

    @GET
    @Path("/test")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @SseElementType(MediaType.TEXT_PLAIN)
    public Multi<String> test(){
        return Multi.createFrom().ticks().every(Duration.ofSeconds(1))
                .onItem().transform(
                        n ->
                        {
                        return String.format("hello %s - %d", UUID.randomUUID(), n);
                        })
                ;
    }

    @GET
    @Path("/testons")
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<String> testons(){

        //Uni.createFrom().publisher()

        Uni<String> rr = Uni.createFrom().item(() -> {
            System.out.println("je suis créer où");
            return "toto";
        });

        System.out.println("tata");
        return rr;
    }



}
