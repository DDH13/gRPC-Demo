package org.example.server;


import io.grpc.ServerBuilder;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GRpcService
public class Server {

    Logger logger = LoggerFactory.getLogger(Server.class);
    io.grpc.Server server = ServerBuilder.forPort(6565)
            .addService(new StudentServiceImpl())
            .addService(new TeacherServiceImpl())
            .build();

}