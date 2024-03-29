// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: PillDispenser.proto

package assign3.grpc;

public final class PillDispenser {
  private PillDispenser() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_assign3_grpc_DownloadReq_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_assign3_grpc_DownloadReq_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_assign3_grpc_Medication_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_assign3_grpc_Medication_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_assign3_grpc_MedPlan_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_assign3_grpc_MedPlan_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_assign3_grpc_TakenMedication_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_assign3_grpc_TakenMedication_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_assign3_grpc_EmptyMessage_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_assign3_grpc_EmptyMessage_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\023PillDispenser.proto\022\014assign3.grpc\" \n\013D" +
      "ownloadReq\022\021\n\tpatientId\030\001 \001(\005\"H\n\nMedicat" +
      "ion\022\024\n\014medicationId\030\001 \001(\005\022\014\n\004name\030\002 \001(\t\022" +
      "\026\n\016intakeInterval\030\003 \001(\t\"7\n\007MedPlan\022,\n\nme" +
      "dication\030\001 \003(\0132\030.assign3.grpc.Medication" +
      "\"W\n\017TakenMedication\022\021\n\tpatientId\030\001 \001(\005\022\024" +
      "\n\014medicationId\030\002 \001(\005\022\014\n\004name\030\003 \001(\t\022\r\n\005ta" +
      "ken\030\004 \001(\t\"\016\n\014EmptyMessage2\256\001\n\024PillDispen" +
      "serService\022C\n\017downloadMedPlan\022\031.assign3." +
      "grpc.DownloadReq\032\025.assign3.grpc.MedPlan\022" +
      "Q\n\024sendMedicationStatus\022\035.assign3.grpc.T" +
      "akenMedication\032\032.assign3.grpc.EmptyMessa" +
      "geB\002P\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_assign3_grpc_DownloadReq_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_assign3_grpc_DownloadReq_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_assign3_grpc_DownloadReq_descriptor,
        new java.lang.String[] { "PatientId", });
    internal_static_assign3_grpc_Medication_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_assign3_grpc_Medication_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_assign3_grpc_Medication_descriptor,
        new java.lang.String[] { "MedicationId", "Name", "IntakeInterval", });
    internal_static_assign3_grpc_MedPlan_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_assign3_grpc_MedPlan_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_assign3_grpc_MedPlan_descriptor,
        new java.lang.String[] { "Medication", });
    internal_static_assign3_grpc_TakenMedication_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_assign3_grpc_TakenMedication_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_assign3_grpc_TakenMedication_descriptor,
        new java.lang.String[] { "PatientId", "MedicationId", "Name", "Taken", });
    internal_static_assign3_grpc_EmptyMessage_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_assign3_grpc_EmptyMessage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_assign3_grpc_EmptyMessage_descriptor,
        new java.lang.String[] { });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
