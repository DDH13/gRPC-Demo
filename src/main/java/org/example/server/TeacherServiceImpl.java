package org.example.server;

import io.grpc.stub.StreamObserver;
import org.example.TeacherRequest;
import org.example.TeacherResponse;
import org.example.TeacherServiceGrpc;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GRpcService
public class TeacherServiceImpl extends TeacherServiceGrpc.TeacherServiceImplBase{

    Logger logger = LoggerFactory.getLogger(TeacherServiceImpl.class);
    TeacherResponse response = TeacherResponse.newBuilder()
            .setAge(10).setName("Lupin")
            .build();
    @Override
    public void getTeacher(TeacherRequest request, StreamObserver<TeacherResponse> responseObserver) {
        logger.info("got request");
        responseObserver.onNext(response);
        responseObserver.onCompleted();
        logger.info("sent response");
    }

    @Override
    public void getTeacherStream(TeacherRequest request, StreamObserver<TeacherResponse> responseObserver) {
        logger.info("got request");
        for(int i=0;i<1000000;i++){
            logger.info("going to send response number "+ i);
            responseObserver.onNext(response);
        }
        responseObserver.onCompleted();
        logger.info("sent response");
    }

    @Override
    public StreamObserver<TeacherRequest> sendTeacherStream(StreamObserver<TeacherResponse> responseObserver) {
        logger.info("got request");
        var responseStream = new StreamObserver<TeacherRequest>() {
            @Override
            public void onNext(TeacherRequest studentRequest) {
                logger.info("received request to server");
            }

            @Override
            public void onError(Throwable throwable) {
                logger.error("got some error "+ throwable.getMessage());
            }

            @Override
            public void onCompleted() {
                logger.info("completed");
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            }
        };
        logger.info("sent response");
        return responseStream;
    }


    public StreamObserver<TeacherRequest> sendAndGetTeacherStream(StreamObserver<TeacherResponse> responseObserver) {
        logger.info("going to send and get");
        var res = new StreamObserver<TeacherRequest>() {
            @Override
            public void onNext(TeacherRequest studentRequest) {
                logger.info("got the request.. sending response");
                responseObserver.onNext(response);
            }

            @Override
            public void onError(Throwable throwable) {
                logger.error("got error = "+ throwable.getMessage());
            }

            @Override
            public void onCompleted() {
                logger.info("completed");
                responseObserver.onCompleted();
            }
        };

        logger.info("sent response");
        return res;

    }

}