/*
package com.grow.AWS;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.io.FilenameUtils;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class AmazonS3Service {
    private static Regions clientRegion = Regions.AP_SOUTH_1;
    private static Logger logger = LoggerFactory.getLogger();
    private AmazonS3 s3Client;
    private String bucketName;
    private SMSInteractionDao smsInteractionDao;

    @Autowired
    public AmazonS3Service(SMSInteractionDao smsInteractionDao, TypedPropertyReader typedPropertyReader) {
        this.smsInteractionDao = smsInteractionDao;
        this.bucketName = typedPropertyReader.getString("amazon.bucket.name");
        String accessKey = typedPropertyReader.getString("aws.access.key.id");
        String secretKey = typedPropertyReader.getString("aws.secret.access.key");
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        this.s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(clientRegion).build();
    }

    public String uploadFile(String filename, String interactionId, byte[] attachmentContent, String fileType, String client) {
        Date date = new Date();
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("YYYY/MM");
        String filePath = client + "/" + simpleDateformat.format(date);
        String fileExtension = FilenameUtils.getExtension(filename);
        String fileObjKeyName = interactionId + "." + fileExtension;
        InputStream content = new ByteArrayInputStream(attachmentContent);
        ObjectMetadata metadata = new ObjectMetadata();
        int fileLength = attachmentContent.length;
        metadata.setContentLength(fileLength);
        metadata.setContentType(fileType);
        String bucket = String.format(bucketName, filePath);
        PutObjectRequest request = new PutObjectRequest(bucket, fileObjKeyName, content, metadata);
        PutObjectResult putObjectResult = s3Client.putObject(request);
        if (null != putObjectResult)
            logger.info("Object SuccessFully uploaded on Amazon S3 storage where keyName is :{} and bucketName is : {} ", fileObjKeyName, bucket);
        return filePath + "/" + fileObjKeyName;
    }

    public void deleteFile(String filePath) {
        try {
            String filename = FilenameUtils.getName(filePath);
            String basePath = FilenameUtils.getPathNoEndSeparator(filePath);
            String bucket = String.format(bucketName, basePath);
            s3Client.deleteObject(new DeleteObjectRequest(bucket, filename));
            logger.info("Object SuccessFully Deleted from S3 where keyName {} and bucketName {} ", filename, bucket);
        } catch (AmazonServiceException e) {
            logger.error("The call was transmitted successfully, but Amazon S3 couldn't process {}", e.getMessage());
        } catch (SdkClientException e) {
            logger.error("Amazon S3 couldn't be contacted for a response, or the client couldn't parse the response from Amazon S3. {}", e.getMessage());
        }

    }
*/
/*
    public Response downloadFile(String interactionId) throws IOException {
        S3Object headerOverrideObject = null;
        try {
            MessengerAttachmentDao messengerAttachmentDao = smsInteractionDao.getMessengerAttachment("documentId");
            ResponseHeaderOverrides headerOverrides = new ResponseHeaderOverrides().withCacheControl("No-cache").withContentDisposition("attachment; filename="+messengerAttachmentDao.getAttachmentName());
            GetObjectRequest getObjectRequestHeaderOverride = new GetObjectRequest(bucketName, interactionId).withResponseHeaders(headerOverrides);
            headerOverrideObject = s3Client.getObject(getObjectRequestHeaderOverride);
            InputStream inputStream=headerOverrideObject.getObjectContent();
            logger.info("Object SuccessFully fetch from S3 where key{} and bucketName {} ", interactionId, bucketName);
            byte[] attachmentData = IOUtils.toByteArray(inputStream);
            StreamingOutput responseEntity = buildResponseEntity(new ByteArrayInputStream(attachmentData));
            if (responseEntity != null) {
                return Response.ok(responseEntity).build();
            }
        } catch (AmazonServiceException e) {
            logger.error("The call was transmitted successfully while gettingFile, but Amazon S3 couldn't process {}", e.getMessage());

        } catch (SdkClientException e) {
            logger.error("Amazon S3 couldn't be contacted for a response  while gettingFile, or the client couldn't parse the response from Amazon S3. {}", e.getMessage());

        } finally {
            if (headerOverrideObject != null) {
                headerOverrideObject.close();
            }
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }*//*


    */
/*public EmailAttachmentDO downloadFileFromS3(String interactionId, String fileName,String filePath) {
        S3Object headerOverrideObject = null;
        EmailAttachmentDO emailAttachmentDO=null;
        try {
            String bucket=bucketName;//"ytcustomer-document/eticket/test/%s";
            //String filePath="";//"YATRA_CORP/2020/02/02a0a93c-6563-4418-8460-3456294174db.pdf";
            String combined=String.format(bucket, filePath);
            String arr[]=combined.split("/");
            String bucketName="";
            for(int i=0;i<arr.length-1;i++)
                bucketName=bucketName +"/"+arr[i];
            filePath=arr[arr.length-1];
            bucketName= bucketName.substring(1, bucketName.length());
            emailAttachmentDO=new EmailAttachmentDO();
            GetObjectRequest getObjectRequestHeaderOverride = new GetObjectRequest(bucketName, filePath);
            headerOverrideObject = s3Client.getObject(getObjectRequestHeaderOverride);
            InputStream inputStream=headerOverrideObject.getObjectContent();
            byte[] attachmentData = IOUtils.toByteArray(inputStream);
            File file = new File(fileName);
            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
            emailAttachmentDO.setAid(interactionId);
            emailAttachmentDO.setAttachmentName(fileName);
            emailAttachmentDO.setAttachmentType(mimeType);
            emailAttachmentDO.setAttachmentContent(attachmentData);
        } catch (AmazonServiceException e) {
            logger.error("The call was transmitted successfully while gettingFile, but Amazon S3 couldn't process {}", e.getMessage());

        } catch (SdkClientException e) {
            logger.error("Amazon S3 couldn't be contacted for a response  while gettingFile, or the client couldn't parse the response from Amazon S3. {}", e.getMessage());

        } catch (Exception ex) {
            logger.error("Amazon S3 couldn't be contacted for a response  while gettingFile, or the client couldn't parse the response from Amazon S3. {}", ex.getMessage());

        }finally {
            if (headerOverrideObject != null) {
                try {
                    headerOverrideObject.close();
                } catch (IOException e) {

                }
            }
        }
        return emailAttachmentDO;
    }


    private StreamingOutput buildResponseEntity(final InputStream inputStream) {
        StreamingOutput responseEntity = null;
        if (inputStream != null){
            responseEntity = new StreamingOutput(){
                public void write(OutputStream outputStream) throws IOException, WebApplicationException {
                    IOUtils.copy(inputStream, outputStream);
                }
            };
        }
        return responseEntity;
    }*//*

}*/
