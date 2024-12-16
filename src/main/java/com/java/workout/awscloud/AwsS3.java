package com.java.workout.awscloud;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.event.ProgressEvent;
import com.amazonaws.event.ProgressListener;
import com.amazonaws.regions.RegionUtils;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.kms.AWSKMS;
import com.amazonaws.services.kms.AWSKMSClientBuilder;
import com.amazonaws.services.kms.model.CreateKeyResult;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.AmazonS3Encryption;
import com.amazonaws.services.s3.AmazonS3EncryptionClientBuilder;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.BucketLifecycleConfiguration;
import com.amazonaws.services.s3.model.BucketLifecycleConfiguration.Transition;
import com.amazonaws.services.s3.model.BucketNotificationConfiguration;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.CanonicalGrantee;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.CryptoConfiguration;
import com.amazonaws.services.s3.model.CryptoMode;
import com.amazonaws.services.s3.model.EmailAddressGrantee;
import com.amazonaws.services.s3.model.EncryptionMaterials;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.Grant;
import com.amazonaws.services.s3.model.GroupGrantee;
import com.amazonaws.services.s3.model.KMSEncryptionMaterialsProvider;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.Permission;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.QueueConfiguration;
import com.amazonaws.services.s3.model.RestoreObjectRequest;
import com.amazonaws.services.s3.model.S3Event;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.model.SetBucketNotificationConfigurationRequest;
import com.amazonaws.services.s3.model.StaticEncryptionMaterialsProvider;
import com.amazonaws.services.s3.model.StorageClass;
import com.amazonaws.services.s3.model.Tag;
import com.amazonaws.services.s3.model.TopicConfiguration;
import com.amazonaws.services.s3.model.lifecycle.LifecycleAndOperator;
import com.amazonaws.services.s3.model.lifecycle.LifecycleFilter;
import com.amazonaws.services.s3.model.lifecycle.LifecyclePrefixPredicate;
import com.amazonaws.services.s3.model.lifecycle.LifecycleTagPredicate;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;
import com.amazonaws.services.securitytoken.AWSSecurityTokenService;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClientBuilder;
import com.amazonaws.services.securitytoken.model.AssumeRoleRequest;
import com.amazonaws.services.securitytoken.model.AssumeRoleResult;
import com.amazonaws.services.securitytoken.model.Credentials;
import com.amazonaws.util.IOUtils;


//Reference: https://github.com/awsdocs/aws-doc-sdk-examples/tree/main/java/example_code
public class AwsS3 {

	public static void main(String[] arg) {
		try {
			final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.DEFAULT_REGION).build();
			AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
					.withCredentials(new ProfileCredentialsProvider())
					.withRegion("_clientRegion")
					.build();

			s3.copyObject("from_bucket", "object_key", "to_bucket", "object_key"); // Copying object from one place to another place

			List<Bucket> buckets = s3.listBuckets(); // List down buckets
			s3.createBucket("bucket_name"); // create bucket
			s3Client.createBucket(new CreateBucketRequest("bucketName"));// create bucket using s3client

			ObjectListing object_listing = s3.listObjects("bucket_name"); // List down object present in bucket
			for (Iterator<?> iterator = object_listing.getObjectSummaries().iterator(); iterator.hasNext();) { // Iterate object list
				S3ObjectSummary summary = (S3ObjectSummary) iterator.next();
				s3.deleteObject("bucket_name", summary.getKey());
			}
			s3.deleteBucket("bucket_name"); // delete bucket
			s3.deleteObject("bucket_name", "object name"); // delete file/object inside bucket


			// Download the PDF file from S3
			S3Object s3object = s3Client.getObject(new GetObjectRequest("bucket_name", "object name"));
			InputStream inputStream = s3object.getObjectContent();

			// Save the PDF file to disk
			File outputFile = new File("output.pdf");
			FileOutputStream outputStream = new FileOutputStream(outputFile);

			// Copy the input stream to the output stream
			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}
			outputStream.close();
			inputStream.close();


			//Upload file to S3
			TransferManager tm = TransferManagerBuilder.standard()
					.withS3Client(s3Client)
					.build();

			// TransferManager processes all transfers asynchronously,
			// so this call returns immediately.
			Upload upload = tm.upload("bucket_name", "object name", new File("local filePath"));
			System.out.println("Object upload started");

			// Optionally, wait for the upload to finish before continuing.
			upload.waitForCompletion();
			System.out.println("Object upload complete");


			//upload progression status tracker
			PutObjectRequest request = new PutObjectRequest("bucket_name", "object name", new File("local filePath"));

			// To receive notifications when bytes are transferred, add a
			// ProgressListener to your request.
			request.setGeneralProgressListener(new ProgressListener() {
				public void progressChanged(ProgressEvent progressEvent) {
					System.out.println("Transferred bytes: " + progressEvent.getBytesTransferred());
				}
			});
			// TransferManager processes all transfers asynchronously,
			// so this call returns immediately.
			Upload upload1 = tm.upload(request);

			// Optionally, you can wait for the upload to finish before continuing.
			upload1.waitForCompletion();



			//Set lifecycle rule for objects
			// Create a rule to transition objects to the Standard-Infrequent Access storage
			// class
			// after 30 days, then to Glacier after 365 days. Amazon S3 will delete the
			// objects after 3650 days.
			// The rule applies to all objects with the tag "archive" set to "true".
			BucketLifecycleConfiguration.Rule rule1 = new BucketLifecycleConfiguration.Rule()
					.withId("Archive and then delete rule")
					.withFilter(new LifecycleFilter(new LifecycleTagPredicate(new Tag("archive", "true"))))
					.addTransition(new Transition().withDays(30)
							.withStorageClass(StorageClass.StandardInfrequentAccess))
					.addTransition(new Transition().withDays(365).withStorageClass(StorageClass.Glacier))
					.withExpirationInDays(3650)
					.withStatus(BucketLifecycleConfiguration.ENABLED);

			// Add the rules to a new BucketLifecycleConfiguration.
			BucketLifecycleConfiguration configuration = new BucketLifecycleConfiguration()
					.withRules(Arrays.asList(rule1));
			// Save the configuration.
			s3Client.setBucketLifecycleConfiguration("bucketName", configuration);

			// Retrieve the configuration.
			configuration = s3Client.getBucketLifecycleConfiguration("_bucketName");

			// Add a new rule with both a prefix predicate and a tag predicate.
			configuration.getRules().add(new BucketLifecycleConfiguration.Rule().withId("NewRule")
					.withFilter(new LifecycleFilter(new LifecycleAndOperator(
							Arrays.asList(new LifecyclePrefixPredicate("YearlyDocuments/"),
									new LifecycleTagPredicate(new Tag(
											"expire_after",
											"ten_years"))))))
					.withExpirationInDays(3650)
					.withStatus(BucketLifecycleConfiguration.ENABLED));
			// Save the configuration.
			s3Client.setBucketLifecycleConfiguration("bucketName", configuration);

			// Delete the configuration.
			s3Client.deleteBucketLifecycleConfiguration("bucketName");

			// Create and submit a request to restore an object from Glacier for two days.
			RestoreObjectRequest requestRestore = new RestoreObjectRequest("bucketName", "keyName", 2);
			s3Client.restoreObjectV2(requestRestore);

			// Check the restoration status of the object.
			ObjectMetadata response = s3Client.getObjectMetadata("bucketName", "keyName");
			Boolean restoreFlag = response.getOngoingRestore();
			System.out.format("Restoration status: %s.\n",
					restoreFlag ? "in progress" : "not in progress (finished or failed)");


			// Create the encryption client, transfer file object through encrypt/decrypt
			String rsaKeyDir = System.getProperty("java.io.tmpdir");
			String publicKeyName = "public.key";
			String privateKeyName = "private.key";

			// Generate a 1024-bit RSA key pair.
			KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
			keyGenerator.initialize(1024, new SecureRandom());
			KeyPair origKeyPair = keyGenerator.generateKeyPair();

			// To see how it works, save and load the key pair to and from the file system.
			saveKeyPair(rsaKeyDir, publicKeyName, privateKeyName, origKeyPair);
			KeyPair keyPair = loadKeyPair(rsaKeyDir, publicKeyName, privateKeyName, "RSA");
			EncryptionMaterials encryptionMaterials = new EncryptionMaterials(keyPair);
			AmazonS3 s3EncryptionClient = AmazonS3EncryptionClientBuilder.standard()
					.withCredentials(new ProfileCredentialsProvider())
					.withEncryptionMaterials(new StaticEncryptionMaterialsProvider(encryptionMaterials))
					.withRegion("clientRegion")
					.build();

			// Create a new object.
			byte[] plaintext = "S3 Object Encrypted Using Client-Side Asymmetric Master Key.".getBytes();
			S3Object object = new S3Object();
			object.setKey("objectKeyName");
			object.setObjectContent(new ByteArrayInputStream(plaintext));
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentLength(plaintext.length);

			// Upload the object. The encryption client automatically encrypts it.
			// Asymmetric encryption/decryption used which uses two key private for encryption & public key for decryption. It is more secure than Symmetric becoz it uses single same key for encryption/decryption.
			PutObjectRequest putRequest = new PutObjectRequest("bucketName",
					object.getKey(),
					object.getObjectContent(),
					metadata);
			s3EncryptionClient.putObject(putRequest);

			// Download and decrypt the object.
			S3Object downloadedObject = s3EncryptionClient.getObject("bucketName", object.getKey());
			byte[] decrypted = IOUtils.toByteArray(downloadedObject.getObjectContent());

			// Verify that the data that you downloaded is the same as the original data.
			System.out.println("Plaintext: " + new String(plaintext));
			System.out.println("Decrypted text: " + new String(decrypted));


			// ACL-Access Control List
			// Create a bucket with a canned ACL. This ACL will be replaced by the
			// setBucketAcl()
			// calls below. It is included here for demonstration purposes.
			CreateBucketRequest createBucketRequest = new CreateBucketRequest("bucketName", Regions.DEFAULT_REGION.getName())
					.withCannedAcl(CannedAccessControlList.LogDeliveryWrite);
			s3Client.createBucket(createBucketRequest);

			// Create a collection of grants to add to the bucket.
			ArrayList<Grant> grantCollection = new ArrayList<Grant>();

			// Grant the account owner full control.
			Grant grant1 = new Grant(new CanonicalGrantee(s3Client.getS3AccountOwner().getId()),
					Permission.FullControl);
			grantCollection.add(grant1);

			// Grant the LogDelivery group permission to write to the bucket.
			Grant grant2 = new Grant(GroupGrantee.LogDelivery, Permission.Write);
			grantCollection.add(grant2);

			// Save grants by replacing all current ACL grants with the two we just created.
			AccessControlList bucketAcl = new AccessControlList();
			bucketAcl.grantAllPermissions(grantCollection.toArray(new Grant[0]));
			s3Client.setBucketAcl("bucket_name", bucketAcl);

			// Retrieve the bucket's ACL, add another grant, and then save the new ACL.
			AccessControlList newBucketAcl = s3Client.getBucketAcl("bucket_name");
			Grant grant3 = new Grant(new EmailAddressGrantee("jeyanthkumar1@gmail.com"), Permission.Read);
			newBucketAcl.grantAllPermissions(grant3);
			s3Client.setBucketAcl("bucket_name", newBucketAcl);

			//Get ACL for bucket
			AccessControlList acl = s3.getBucketAcl("bucket_name");
			List<Grant> grants = acl.getGrantsAsList();
			for (Grant grant : grants) {
				System.out.format("  %s: %s\n", grant.getGrantee().getIdentifier(),
						grant.getPermission().toString());
			}
			//Get ACL for bucket object
			AccessControlList acl1 = s3.getObjectAcl("bucket_name", "object_key");
			List<Grant> grants1 = acl1.getGrantsAsList();
			for (Grant grant : grants1) {
				System.out.format("  %s: %s\n", grant.getGrantee().getIdentifier(), grant.getPermission().toString());
			}


			// Creating the STS client is part of your trusted code. It has
			// the security credentials you use to obtain temporary security credentials.
			AWSSecurityTokenService stsClient = AWSSecurityTokenServiceClientBuilder.standard()
					.withCredentials(new ProfileCredentialsProvider())
					.withRegion("_clientRegion")
					.build();

			// Obtain credentials for the IAM role. Note that you cannot assume the role of
			// an AWS root account;
			// Amazon S3 will deny access. You must use credentials for an IAM user or an
			// IAM role.
			AssumeRoleRequest roleRequest = new AssumeRoleRequest()
					.withRoleArn("_roleARN")
					.withRoleSessionName("_roleSessionName");
			AssumeRoleResult roleResponse = stsClient.assumeRole(roleRequest);
			Credentials sessionCredentials = roleResponse.getCredentials();

			// Create a BasicSessionCredentials object that contains the credentials you
			// just retrieved.
			BasicSessionCredentials awsCredentials = new BasicSessionCredentials(
					sessionCredentials.getAccessKeyId(),
					sessionCredentials.getSecretAccessKey(),
					sessionCredentials.getSessionToken());

			// Provide temporary security credentials so that the Amazon S3 client
			// can send authenticated requests to Amazon S3. You create the client
			// using the sessionCredentials object.
			AmazonS3 s3Client1 = AmazonS3ClientBuilder.standard()
					.withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
					.withRegion("_clientRegion")
					.build();

			// Verify that assuming the role worked and the permissions are set correctly
			// by getting a set of object keys from the bucket.
			ObjectListing objects = s3Client.listObjects("_bucketName");
			System.out.println("No. of Objects: " + objects.getObjectSummaries().size());



			//Sending Notification
			BucketNotificationConfiguration notificationConfiguration = new BucketNotificationConfiguration();

			// Add an SNS topic notification.
			notificationConfiguration.addConfiguration("snsTopicConfig", new TopicConfiguration("_snsTopicARN", EnumSet.of(S3Event.ObjectCreated)));

			// Add an SQS queue notification.
			notificationConfiguration.addConfiguration("sqsQueueConfig", new QueueConfiguration("_sqsQueueARN", EnumSet.of(S3Event.ObjectCreated)));

			// Create the notification configuration request and set the bucket notification
			// configuration.
			SetBucketNotificationConfigurationRequest request1 = new SetBucketNotificationConfigurationRequest(
					"bucket_name", notificationConfiguration);
			s3Client.setBucketNotificationConfiguration(request1);
			
			
			kmsEncryptUpload();
			
			authenticatedEncryption_CustomerManagedKey();


		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	private static void saveKeyPair(String dir,
			String publicKeyName,
			String privateKeyName,
			KeyPair keyPair) throws IOException {
		PrivateKey privateKey = keyPair.getPrivate();
		PublicKey publicKey = keyPair.getPublic();

		// Write the public key to the specified file.
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKey.getEncoded());
		FileOutputStream publicKeyOutputStream = new FileOutputStream(dir + File.separator + publicKeyName);
		publicKeyOutputStream.write(x509EncodedKeySpec.getEncoded());
		publicKeyOutputStream.close();

		// Write the private key to the specified file.
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey.getEncoded());
		FileOutputStream privateKeyOutputStream = new FileOutputStream(dir + File.separator + privateKeyName);
		privateKeyOutputStream.write(pkcs8EncodedKeySpec.getEncoded());
		privateKeyOutputStream.close();
	}

	private static KeyPair loadKeyPair(String dir,
			String publicKeyName,
			String privateKeyName,
			String algorithm)
					throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
		// Read the public key from the specified file.
		File publicKeyFile = new File(dir + File.separator + publicKeyName);
		FileInputStream publicKeyInputStream = new FileInputStream(publicKeyFile);
		byte[] encodedPublicKey = new byte[(int) publicKeyFile.length()];
		publicKeyInputStream.read(encodedPublicKey);
		publicKeyInputStream.close();

		// Read the private key from the specified file.
		File privateKeyFile = new File(dir + File.separator + privateKeyName);
		FileInputStream privateKeyInputStream = new FileInputStream(privateKeyFile);
		byte[] encodedPrivateKey = new byte[(int) privateKeyFile.length()];
		privateKeyInputStream.read(encodedPrivateKey);
		privateKeyInputStream.close();

		// Convert the keys into a key pair.
		KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
		X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(encodedPublicKey);
		PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

		PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(encodedPrivateKey);
		PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

		return new KeyPair(publicKey, privateKey);
	}
	
	public static void authenticatedEncryption_CustomerManagedKey() throws NoSuchAlgorithmException {
		
		final String BUCKET_NAME = "bucketscott2"; // add your bucket name
		final String ENCRYPTED_KEY = "enc-key";
		final String NON_ENCRYPTED_KEY = "some-key";

        SecretKey secretKey = KeyGenerator.getInstance("AES").generateKey();
        AmazonS3Encryption s3Encryption = AmazonS3EncryptionClientBuilder
                .standard()
                .withRegion(Regions.US_WEST_2)
                .withCryptoConfiguration(new CryptoConfiguration(CryptoMode.AuthenticatedEncryption))
                .withEncryptionMaterials(new StaticEncryptionMaterialsProvider(new EncryptionMaterials(secretKey)))
                .build();

        AmazonS3 s3NonEncrypt = AmazonS3ClientBuilder.standard().withRegion(Regions.DEFAULT_REGION).build();
        // snippet-end:[s3.java1.s3_encrypt.authenticated_encryption_build]

        s3Encryption.putObject(BUCKET_NAME, ENCRYPTED_KEY, "some contents");
        s3NonEncrypt.putObject(BUCKET_NAME, NON_ENCRYPTED_KEY, "some other contents");
        System.out.println(s3Encryption.getObjectAsString(BUCKET_NAME, ENCRYPTED_KEY));
        System.out.println(s3Encryption.getObjectAsString(BUCKET_NAME, NON_ENCRYPTED_KEY));
    }
	
	public static void kmsEncryptUpload()
	{
		String bucketName = "*** Bucket name ***";
        String keyName = "*** Object key name ***";
        Regions clientRegion = Regions.DEFAULT_REGION;
        String kms_cmk_id = "*** AWS KMS customer master key ID ***";
        int readChunkSize = 4096;

        try {
            // Optional: If you don't have a KMS key (or need another one),
            // create one. This example creates a key with AWS-created
            // key material.
            AWSKMS kmsClient = AWSKMSClientBuilder.standard()
                    .withRegion(clientRegion)
                    .build();
            CreateKeyResult keyResult = kmsClient.createKey();
            kms_cmk_id = keyResult.getKeyMetadata().getKeyId();

            // Create the encryption client.
            KMSEncryptionMaterialsProvider materialProvider = new KMSEncryptionMaterialsProvider(kms_cmk_id);
            CryptoConfiguration cryptoConfig = new CryptoConfiguration()
                    .withAwsKmsRegion(RegionUtils.getRegion(clientRegion.toString()));
            AmazonS3Encryption encryptionClient = AmazonS3EncryptionClientBuilder.standard()
                    .withCredentials(new ProfileCredentialsProvider())
                    .withEncryptionMaterials(materialProvider)
                    .withCryptoConfiguration(cryptoConfig)
                    .withRegion(clientRegion).build();

            // Upload an object using the encryption client.
            String origContent = "S3 Encrypted Object Using KMS-Managed Customer Master Key.";
            int origContentLength = origContent.length();
            encryptionClient.putObject(bucketName, keyName, origContent);

            // Download the object. The downloaded object is still encrypted.
            S3Object downloadedObject = encryptionClient.getObject(bucketName, keyName);
            S3ObjectInputStream input = downloadedObject.getObjectContent();

            // Decrypt and read the object and close the input stream.
            byte[] readBuffer = new byte[readChunkSize];
            ByteArrayOutputStream baos = new ByteArrayOutputStream(readChunkSize);
            int bytesRead = 0;
            int decryptedContentLength = 0;

            while ((bytesRead = input.read(readBuffer)) != -1) {
                baos.write(readBuffer, 0, bytesRead);
                decryptedContentLength += bytesRead;
            }
            input.close();

            // Verify that the original and decrypted contents are the same size.
            System.out.println("Original content length: " + origContentLength);
            System.out.println("Decrypted content length: " + decryptedContentLength);
        } catch (AmazonServiceException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process
            // it, so it returned an error response.
            e.printStackTrace();
        } catch (SdkClientException e) {
            // Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3.
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
	}
}
