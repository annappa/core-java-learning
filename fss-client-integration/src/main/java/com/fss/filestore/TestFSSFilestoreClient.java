package com.fss.filestore;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.System;
import java.util.Properties;
import akka.actor.ActorSystem;
import akka.stream.Materializer;
import com.oracle.cegbu.filestream.clientapi.LocalFileStreamClient;
import com.oracle.cegbu.filestream.clientapi.data.UploadResult;
import com.oracle.cegbu.shared.akka.actor.ActorSystemProvider;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.oracle.pgbu.framework.jwt.util.TGSJwtTokenController;
import com.oracle.cegbu.filestore.clientapi.LocalFileStoreClient;
import com.oracle.cegbu.filestore.clientapi.data.FileUploadResult;

public class TestFSSFilestoreClient {
    private static final String STRIPE_NAME = "cegbunfbobjtest02";
    private static final String FILE_NAME = "Sample1.pdf";
    private static final long FILE_SIZE = 209082;
    private static final String MIME_TYPE = ".pdf";

    public static void main(String[] args) {
        System.setProperty("jwt_sat_mount_path", "/Users/annrames/jwt_sat_mount");
        System.setProperty("jwt.token.style", "tgs");
        TGSJwtTokenController controller = new TGSJwtTokenController();

        //System.setProperty("securestorage_api_uri", "http://localhost:8500");
        //System.setProperty("stateful_set_seed_nodes", "http://192.168.0.6:2552");
        System.setProperty("serviceregistry_filestore", "http://192.168.0.6:8080/filestore");
        System.setProperty("serviceregistry_filestream", "http://192.168.0.6:8081/filestream");

        ActorSystem system = ActorSystemProvider.initialize("aconex-babylon-webapp", false, new Properties());
        Materializer materializer = ActorSystemProvider.instance().actorMaterializer();

        LocalFileStoreClient fileStoreClientclient = new LocalFileStoreClient(system, materializer, "babylon@aconex.com");
        LocalFileStreamClient fileStreamClientclient = new LocalFileStreamClient(system, materializer, "babylon@aconex.com");

        CompletableFuture<FileUploadResult> fileStorefuture = fileStoreClientclient.files().uploadFile(STRIPE_NAME, FILE_NAME, FILE_SIZE, MIME_TYPE, "Sample1");
        try {
            FileUploadResult result = fileStorefuture.get();
            System.out.println(result.getUploadURL());
            //final String parURL, final String stripeName, final long fileId, final long fileSize, final String fileName, final InputStream inputStream
            CompletableFuture<UploadResult> fileStreamFuture = fileStreamClientclient.stream().uploadUrl(result.getUploadURL(), STRIPE_NAME, result.getFileId(), FILE_SIZE, FILE_NAME, new FileInputStream("/Users/annrames/Downloads/Sample1.pdf"));
            UploadResult uploadResult = fileStreamFuture.get();
            System.out.println(uploadResult.getStatus().name());
            System.out.println(uploadResult.getFileId());
            System.out.println(uploadResult.getFileName());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
