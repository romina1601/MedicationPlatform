package com.example.springdemo.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.16.1)",
    comments = "Source: PillDispenser.proto")
public final class PillDispenserServiceGrpc {

  private PillDispenserServiceGrpc() {}

  public static final String SERVICE_NAME = "assign3.grpc.PillDispenserService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<assign3.grpc.DownloadReq,
      assign3.grpc.MedPlan> getDownloadMedPlanMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "downloadMedPlan",
      requestType = assign3.grpc.DownloadReq.class,
      responseType = assign3.grpc.MedPlan.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<assign3.grpc.DownloadReq,
      assign3.grpc.MedPlan> getDownloadMedPlanMethod() {
    io.grpc.MethodDescriptor<assign3.grpc.DownloadReq, assign3.grpc.MedPlan> getDownloadMedPlanMethod;
    if ((getDownloadMedPlanMethod = PillDispenserServiceGrpc.getDownloadMedPlanMethod) == null) {
      synchronized (PillDispenserServiceGrpc.class) {
        if ((getDownloadMedPlanMethod = PillDispenserServiceGrpc.getDownloadMedPlanMethod) == null) {
          PillDispenserServiceGrpc.getDownloadMedPlanMethod = getDownloadMedPlanMethod =
              io.grpc.MethodDescriptor.<assign3.grpc.DownloadReq, assign3.grpc.MedPlan>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "assign3.grpc.PillDispenserService", "downloadMedPlan"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  assign3.grpc.DownloadReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  assign3.grpc.MedPlan.getDefaultInstance()))
                  .setSchemaDescriptor(new PillDispenserServiceMethodDescriptorSupplier("downloadMedPlan"))
                  .build();
          }
        }
     }
     return getDownloadMedPlanMethod;
  }

  private static volatile io.grpc.MethodDescriptor<assign3.grpc.TakenMedication,
      assign3.grpc.EmptyMessage> getSendMedicationStatusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "sendMedicationStatus",
      requestType = assign3.grpc.TakenMedication.class,
      responseType = assign3.grpc.EmptyMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<assign3.grpc.TakenMedication,
      assign3.grpc.EmptyMessage> getSendMedicationStatusMethod() {
    io.grpc.MethodDescriptor<assign3.grpc.TakenMedication, assign3.grpc.EmptyMessage> getSendMedicationStatusMethod;
    if ((getSendMedicationStatusMethod = PillDispenserServiceGrpc.getSendMedicationStatusMethod) == null) {
      synchronized (PillDispenserServiceGrpc.class) {
        if ((getSendMedicationStatusMethod = PillDispenserServiceGrpc.getSendMedicationStatusMethod) == null) {
          PillDispenserServiceGrpc.getSendMedicationStatusMethod = getSendMedicationStatusMethod =
              io.grpc.MethodDescriptor.<assign3.grpc.TakenMedication, assign3.grpc.EmptyMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "assign3.grpc.PillDispenserService", "sendMedicationStatus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  assign3.grpc.TakenMedication.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  assign3.grpc.EmptyMessage.getDefaultInstance()))
                  .setSchemaDescriptor(new PillDispenserServiceMethodDescriptorSupplier("sendMedicationStatus"))
                  .build();
          }
        }
     }
     return getSendMedicationStatusMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static PillDispenserServiceStub newStub(io.grpc.Channel channel) {
    return new PillDispenserServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static PillDispenserServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new PillDispenserServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static PillDispenserServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new PillDispenserServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class PillDispenserServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void downloadMedPlan(assign3.grpc.DownloadReq request,
        io.grpc.stub.StreamObserver<assign3.grpc.MedPlan> responseObserver) {
      asyncUnimplementedUnaryCall(getDownloadMedPlanMethod(), responseObserver);
    }

    /**
     */
    public void sendMedicationStatus(assign3.grpc.TakenMedication request,
        io.grpc.stub.StreamObserver<assign3.grpc.EmptyMessage> responseObserver) {
      asyncUnimplementedUnaryCall(getSendMedicationStatusMethod(), responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getDownloadMedPlanMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                assign3.grpc.DownloadReq,
                assign3.grpc.MedPlan>(
                  this, METHODID_DOWNLOAD_MED_PLAN)))
          .addMethod(
            getSendMedicationStatusMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                assign3.grpc.TakenMedication,
                assign3.grpc.EmptyMessage>(
                  this, METHODID_SEND_MEDICATION_STATUS)))
          .build();
    }
  }

  /**
   */
  public static final class PillDispenserServiceStub extends io.grpc.stub.AbstractStub<PillDispenserServiceStub> {
    private PillDispenserServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PillDispenserServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected PillDispenserServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PillDispenserServiceStub(channel, callOptions);
    }

    /**
     */
    public void downloadMedPlan(assign3.grpc.DownloadReq request,
        io.grpc.stub.StreamObserver<assign3.grpc.MedPlan> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDownloadMedPlanMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void sendMedicationStatus(assign3.grpc.TakenMedication request,
        io.grpc.stub.StreamObserver<assign3.grpc.EmptyMessage> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSendMedicationStatusMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class PillDispenserServiceBlockingStub extends io.grpc.stub.AbstractStub<PillDispenserServiceBlockingStub> {
    private PillDispenserServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PillDispenserServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected PillDispenserServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PillDispenserServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public assign3.grpc.MedPlan downloadMedPlan(assign3.grpc.DownloadReq request) {
      return blockingUnaryCall(
          getChannel(), getDownloadMedPlanMethod(), getCallOptions(), request);
    }

    /**
     */
    public assign3.grpc.EmptyMessage sendMedicationStatus(assign3.grpc.TakenMedication request) {
      return blockingUnaryCall(
          getChannel(), getSendMedicationStatusMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class PillDispenserServiceFutureStub extends io.grpc.stub.AbstractStub<PillDispenserServiceFutureStub> {
    private PillDispenserServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PillDispenserServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected PillDispenserServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PillDispenserServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<assign3.grpc.MedPlan> downloadMedPlan(
        assign3.grpc.DownloadReq request) {
      return futureUnaryCall(
          getChannel().newCall(getDownloadMedPlanMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<assign3.grpc.EmptyMessage> sendMedicationStatus(
        assign3.grpc.TakenMedication request) {
      return futureUnaryCall(
          getChannel().newCall(getSendMedicationStatusMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_DOWNLOAD_MED_PLAN = 0;
  private static final int METHODID_SEND_MEDICATION_STATUS = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final PillDispenserServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(PillDispenserServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_DOWNLOAD_MED_PLAN:
          serviceImpl.downloadMedPlan((assign3.grpc.DownloadReq) request,
              (io.grpc.stub.StreamObserver<assign3.grpc.MedPlan>) responseObserver);
          break;
        case METHODID_SEND_MEDICATION_STATUS:
          serviceImpl.sendMedicationStatus((assign3.grpc.TakenMedication) request,
              (io.grpc.stub.StreamObserver<assign3.grpc.EmptyMessage>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class PillDispenserServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    PillDispenserServiceBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return assign3.grpc.PillDispenser.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("PillDispenserService");
    }
  }

  private static final class PillDispenserServiceFileDescriptorSupplier
      extends PillDispenserServiceBaseDescriptorSupplier {
    PillDispenserServiceFileDescriptorSupplier() {}
  }

  private static final class PillDispenserServiceMethodDescriptorSupplier
      extends PillDispenserServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    PillDispenserServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (PillDispenserServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new PillDispenserServiceFileDescriptorSupplier())
              .addMethod(getDownloadMedPlanMethod())
              .addMethod(getSendMedicationStatusMethod())
              .build();
        }
      }
    }
    return result;
  }
}
