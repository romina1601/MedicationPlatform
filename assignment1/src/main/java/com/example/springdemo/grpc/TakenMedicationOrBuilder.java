// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: PillDispenser.proto

package com.example.springdemo.grpc;

public interface TakenMedicationOrBuilder extends
    // @@protoc_insertion_point(interface_extends:assign3.grpc.TakenMedication)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 patientId = 1;</code>
   */
  int getPatientId();

  /**
   * <code>int32 medicationId = 2;</code>
   */
  int getMedicationId();

  /**
   * <code>string name = 3;</code>
   */
  String getName();
  /**
   * <code>string name = 3;</code>
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <code>string taken = 4;</code>
   */
  String getTaken();
  /**
   * <code>string taken = 4;</code>
   */
  com.google.protobuf.ByteString
      getTakenBytes();
}
