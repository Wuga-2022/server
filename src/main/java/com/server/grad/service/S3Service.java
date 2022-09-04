package com.server.grad.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import lombok.NoArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@Service
@NoArgsConstructor
@Slf4j
public class S3Service {

    private AmazonS3 s3Client;

    public static final String CLOUD_FRONT_DOMAIN_NAME = "d1hop3qdderj3r.cloudfront.net"; //CloudFront 도메인명 (조회 시 사용)


    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.region.static}")
    private String region;

    @PostConstruct
    public void setS3Client() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);

        s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials)) // 자격증명을 통해 S3 Client를 가져오기
                .withRegion(this.region)
                .build();
    }

    public String uploadFile(MultipartFile file){
        File fileObj = convertMultipartFileToFile(file);
        String fileName = System.currentTimeMillis()+"_"+file.getOriginalFilename();
        s3Client.putObject(new PutObjectRequest(bucket, fileName, fileObj));
        fileObj.delete();
        return "File uploaded : " + fileName;
    }

    public List<String> upload(List<MultipartFile> files) throws IOException {
        List<String> fileNames = new ArrayList<>();

        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd"); // 폴더 이름 업로드 날짜로 바꾸기
            String folderName = simpleDateFormat.format(new Date());
            String newFileName = Long.toString(System.nanoTime()) + fileName; // 파일명 변경

            s3Client.putObject(new PutObjectRequest(bucket, folderName + "/" + newFileName, file.getInputStream(), null)
                    .withCannedAcl(CannedAccessControlList.PublicRead)); // read 권한 추가 후 업로드
            fileNames.add(folderName + "/" + newFileName); // S3 객체를 식별하는 key값
        }

        return fileNames;
    }

    public byte[] downloadFile(String fileName){
        S3Object s3Object = s3Client.getObject(bucket, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            byte[] content = IOUtils.toByteArray(inputStream);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String deleteFile(String fileName) {
        s3Client.deleteObject(bucket,fileName);
        return fileName + " removed ...";
    }
    private File convertMultipartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch(IOException e) {
            log. error("Error converting multipartFile to file", e);
        }
        return convertedFile;
    }
}

