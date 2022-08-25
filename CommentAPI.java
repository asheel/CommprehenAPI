package com.example.comment;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.comprehend.ComprehendClient;
import software.amazon.awssdk.services.comprehend.model.ComprehendException;
import software.amazon.awssdk.services.comprehend.model.DetectPiiEntitiesRequest;
import software.amazon.awssdk.services.comprehend.model.DetectPiiEntitiesResponse;
import software.amazon.awssdk.services.comprehend.model.PiiEntity;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CommentAPI {

    @RequestMapping("/getPII/{text}")
    @ResponseBody
    public List<PIIEntity> getPII(@PathVariable String text){

        Region region = Region.US_EAST_2;
        ComprehendClient comClient = ComprehendClient.builder()
                .region(region)
                .credentialsProvider(ProfileCredentialsProvider.create())
                .build();

        System.out.println("Calling DetectEntities");
        //detectAllEntities(comClient, text);
        List<PIIEntity> result=detectPIIEntities(comClient,text);
        comClient.close();

        return result;
    }
    public static List<PIIEntity> detectPIIEntities(ComprehendClient comClient,String text ) {

        List<PiiEntity> entList=null;
        List<PIIEntity> entityList=new ArrayList<>();
        try {
            DetectPiiEntitiesRequest detectEntitiesRequest = DetectPiiEntitiesRequest.builder()
                    .text(text)
                    .languageCode("en")
                    .build();

            DetectPiiEntitiesResponse detectEntitiesResult = comClient.detectPiiEntities(detectEntitiesRequest);
            entList = detectEntitiesResult.entities();
            for (PiiEntity entity : entList) {
                System.out.println("Entity : " + entity.toString());
                entityList.add(new PIIEntity(entity.score(),entity.typeAsString(),entity.beginOffset(),entity.endOffset()));
            }

        } catch (ComprehendException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
        return entityList;
    }
}
