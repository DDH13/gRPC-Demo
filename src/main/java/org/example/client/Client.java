package org.example.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.example.StudentRequest;
import org.example.StudentResponse;
import org.example.StudentServiceGrpc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Client {

    static Logger logger = (Logger) LoggerFactory.getLogger(Client.class);

    public static void main(String[] args) {
        // Establish a gRPC channel to the server running on localhost at port 6565
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 6565).usePlaintext().build();

        // Create a blocking stub for the StudentService
        StudentServiceGrpc.StudentServiceBlockingStub blockingStub = StudentServiceGrpc.newBlockingStub(managedChannel);

        // Make a synchronous gRPC call to get student details for ID 1
        StudentResponse studentResponse = blockingStub.getStudent(StudentRequest.newBuilder().setId(1).build());

        // Log the response received from the gRPC server
        logger.info("response = " + studentResponse.getName() + " " + studentResponse.getAge());
    }

}