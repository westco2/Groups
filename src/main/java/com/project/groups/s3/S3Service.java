package com.project.groups.s3;

import org.springframework.beans.factory.annotation.Value;

import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import software.amazon.awssdk.services.s3.model.*;

import org.springframework.stereotype.Service;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

import java.io.*;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class S3Service {

    @Value("${aws_target_bucket}")
    private String aws_target_bucket;

    public void getBuketList(){
        //AwsBasicCredentials credentials = AwsBasicCredentials.create(aws_access_key_id,aws_secret_access_key);
        /*Region region = Region.AP_NORTHEAST_2; //리전


        S3Client s3 = S3Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .region(region)
                .build();

        // List buckets
        ListBucketsRequest listBucketsRequest = ListBucketsRequest.builder().build();
        ListBucketsResponse listBucketsResponse = s3.listBuckets(listBucketsRequest);
        listBucketsResponse.buckets().stream().forEach(x -> System.out.println(x.name()));*/

        Region region = Region.AP_NORTHEAST_2; //리전


        S3Client s3 = S3Client.builder()
                .region(region)
                .build();

        // List buckets
        ListBucketsRequest listBucketsRequest = ListBucketsRequest.builder().build();
        ListBucketsResponse listBucketsResponse = s3.listBuckets(listBucketsRequest);
        listBucketsResponse.buckets().stream().forEach(x -> System.out.println(x.name()));

    }

    public void putS3Object(String objectKey, byte[] objectdata) { //클라이언트에서 입력받은 파일데이터를 올림
        try {


            Region region = Region.AP_NORTHEAST_2;
            S3Client s3 = S3Client.builder()
                    .region(region)
                    .build();

            Map<String, String> metadata = new HashMap<>();
            metadata.put("x-amz-meta-myVal", "test");
            PutObjectRequest putOb = PutObjectRequest.builder()
                    .bucket(aws_target_bucket)
                    .key(objectKey)
                    .metadata(metadata)
                    .build();

            // s3.putObject(putOb, RequestBody.fromFile(new File(objectPath))); //로컬파일을 읽어서 올리는 경우는 이렇게
            s3.putObject(putOb, RequestBody.fromBytes(objectdata)); //클라이언트에서 입력받은 파일의 바이트데이터
            System.out.println("Successfully placed " + objectKey + " into bucket " + aws_target_bucket);

        } catch (S3Exception e) {
            System.err.println(e.getMessage());
            //System.exit(1); //프로그램 종료
        }
    }

    public List<String> listBucketObjects() {

        List<String> list = new ArrayList<>();
        try {

            Region region = Region.AP_NORTHEAST_2;
            S3Client s3 = S3Client.builder()
                    .region(region)
                    .build();
            ListObjectsRequest listObjects = ListObjectsRequest
                    .builder()
                    .bucket(aws_target_bucket)
                    .build();

            ListObjectsResponse res = s3.listObjects(listObjects);
            List<S3Object> objects = res.contents();
            for (S3Object myValue : objects) {
                System.out.print("\n The name of the key is " + myValue.key());
                System.out.print("\n The object is " + calKb(myValue.size()) + " KBs");
                System.out.print("\n The owner is " + myValue.owner());
                list.add(myValue.key());
            }

        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
        return list;

    }
    private static long calKb(Long val) {
        return val / 1024;
    }

    public byte[] getObjectBytes(String keyName, String filename) {
        try {
            Region region = Region.AP_NORTHEAST_2;
            S3Client s3 = S3Client.builder()
                    .region(region)
                    .build();
            GetObjectRequest objectRequest = GetObjectRequest
                    .builder()
                    .key(keyName)
                    .bucket(aws_target_bucket)
                    .build();

            ResponseInputStream<GetObjectResponse> objectBytes = s3.getObject(objectRequest);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = objectBytes.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            byte[] data = outputStream.toByteArray();

            // Write the data to a local file.
            String path = System.getProperty("user.home") + File.separator + "Downloads" + File.separator + filename;
            File myFile = new File(path);
            try (OutputStream os = new FileOutputStream(myFile)) {
                os.write(data);
                System.out.println("Successfully obtained bytes from an S3 object");
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            return data;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            return null;
        }
    }



    public void deleteBucketObjects(String keyName) {
        // Upload three sample objects to the specfied Amazon S3 bucket.

        Region region = Region.AP_NORTHEAST_2;
        S3Client s3 = S3Client.builder()
                .region(region)
                .build();


        ArrayList<ObjectIdentifier> keys = new ArrayList<>();

        ObjectIdentifier objectId;
        objectId = ObjectIdentifier.builder()
                .key(keyName)
                .build();


        keys.add(objectId);
        // Delete multiple objects in one request.
        Delete del = Delete.builder()
                .objects(keys)
                .build();

        try {
            DeleteObjectsRequest multiObjectDeleteRequest = DeleteObjectsRequest.builder()
                    .bucket(aws_target_bucket)
                    .delete(del)
                    .build();

            s3.deleteObjects(multiObjectDeleteRequest);
            System.out.println("Multiple objects are deleted!");

        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            //System.exit(1);
        }
    }

}
