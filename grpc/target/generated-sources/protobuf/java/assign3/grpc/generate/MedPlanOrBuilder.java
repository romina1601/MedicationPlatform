// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: PillDispenser.proto

package assign3.grpc.generate;

public interface MedPlanOrBuilder extends
    // @@protoc_insertion_point(interface_extends:assign3.grpc.generate.MedPlan)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated .assign3.grpc.generate.Medication medication = 1;</code>
   */
  java.util.List<assign3.grpc.generate.Medication> 
      getMedicationList();
  /**
   * <code>repeated .assign3.grpc.generate.Medication medication = 1;</code>
   */
  assign3.grpc.generate.Medication getMedication(int index);
  /**
   * <code>repeated .assign3.grpc.generate.Medication medication = 1;</code>
   */
  int getMedicationCount();
  /**
   * <code>repeated .assign3.grpc.generate.Medication medication = 1;</code>
   */
  java.util.List<? extends assign3.grpc.generate.MedicationOrBuilder> 
      getMedicationOrBuilderList();
  /**
   * <code>repeated .assign3.grpc.generate.Medication medication = 1;</code>
   */
  assign3.grpc.generate.MedicationOrBuilder getMedicationOrBuilder(
      int index);
}
