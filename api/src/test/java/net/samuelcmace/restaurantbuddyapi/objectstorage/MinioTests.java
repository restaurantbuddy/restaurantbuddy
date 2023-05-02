package net.samuelcmace.restaurantbuddyapi.objectstorage;

import io.minio.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MinioTests {

    @Test
    public void createObjectStorageTest()
    {
        MinioClient client = MinioClient.builder()
                .endpoint("http://localhost:9000/")
                .credentials("restaurantbuddy", "restaurantbuddy")
                .build();

        final String bucketName = "restaurantbuddy";

        try {
            boolean bucketFound = client.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());

            if(!bucketFound)
            {
                System.out.println("Bucket not found. We will create it now.");
                client.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }
            else {
                System.out.println("The bucket already exists.");
            }

            String menuItemId = "portrait";

            client.uploadObject(
                    UploadObjectArgs
                        .builder()
                            .bucket(bucketName)
                            .object("/menu_item_images/" + menuItemId + ".jpg")
                            .filename("C:\\Users\\samue\\Downloads\\" + menuItemId + ".jpg")
                        .build());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
