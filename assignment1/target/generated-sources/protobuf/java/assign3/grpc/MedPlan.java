// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: PillDispenser.proto

package assign3.grpc;

/**
 * Protobuf type {@code assign3.grpc.MedPlan}
 */
public  final class MedPlan extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:assign3.grpc.MedPlan)
    MedPlanOrBuilder {
private static final long serialVersionUID = 0L;
  // Use MedPlan.newBuilder() to construct.
  private MedPlan(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private MedPlan() {
    medication_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private MedPlan(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
          case 10: {
            if (!((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
              medication_ = new java.util.ArrayList<assign3.grpc.Medication>();
              mutable_bitField0_ |= 0x00000001;
            }
            medication_.add(
                input.readMessage(assign3.grpc.Medication.parser(), extensionRegistry));
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      if (((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
        medication_ = java.util.Collections.unmodifiableList(medication_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return assign3.grpc.PillDispenser.internal_static_assign3_grpc_MedPlan_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return assign3.grpc.PillDispenser.internal_static_assign3_grpc_MedPlan_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            assign3.grpc.MedPlan.class, assign3.grpc.MedPlan.Builder.class);
  }

  public static final int MEDICATION_FIELD_NUMBER = 1;
  private java.util.List<assign3.grpc.Medication> medication_;
  /**
   * <code>repeated .assign3.grpc.Medication medication = 1;</code>
   */
  public java.util.List<assign3.grpc.Medication> getMedicationList() {
    return medication_;
  }
  /**
   * <code>repeated .assign3.grpc.Medication medication = 1;</code>
   */
  public java.util.List<? extends assign3.grpc.MedicationOrBuilder> 
      getMedicationOrBuilderList() {
    return medication_;
  }
  /**
   * <code>repeated .assign3.grpc.Medication medication = 1;</code>
   */
  public int getMedicationCount() {
    return medication_.size();
  }
  /**
   * <code>repeated .assign3.grpc.Medication medication = 1;</code>
   */
  public assign3.grpc.Medication getMedication(int index) {
    return medication_.get(index);
  }
  /**
   * <code>repeated .assign3.grpc.Medication medication = 1;</code>
   */
  public assign3.grpc.MedicationOrBuilder getMedicationOrBuilder(
      int index) {
    return medication_.get(index);
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    for (int i = 0; i < medication_.size(); i++) {
      output.writeMessage(1, medication_.get(i));
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < medication_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, medication_.get(i));
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof assign3.grpc.MedPlan)) {
      return super.equals(obj);
    }
    assign3.grpc.MedPlan other = (assign3.grpc.MedPlan) obj;

    boolean result = true;
    result = result && getMedicationList()
        .equals(other.getMedicationList());
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (getMedicationCount() > 0) {
      hash = (37 * hash) + MEDICATION_FIELD_NUMBER;
      hash = (53 * hash) + getMedicationList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static assign3.grpc.MedPlan parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static assign3.grpc.MedPlan parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static assign3.grpc.MedPlan parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static assign3.grpc.MedPlan parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static assign3.grpc.MedPlan parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static assign3.grpc.MedPlan parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static assign3.grpc.MedPlan parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static assign3.grpc.MedPlan parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static assign3.grpc.MedPlan parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static assign3.grpc.MedPlan parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static assign3.grpc.MedPlan parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static assign3.grpc.MedPlan parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(assign3.grpc.MedPlan prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code assign3.grpc.MedPlan}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:assign3.grpc.MedPlan)
      assign3.grpc.MedPlanOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return assign3.grpc.PillDispenser.internal_static_assign3_grpc_MedPlan_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return assign3.grpc.PillDispenser.internal_static_assign3_grpc_MedPlan_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              assign3.grpc.MedPlan.class, assign3.grpc.MedPlan.Builder.class);
    }

    // Construct using assign3.grpc.MedPlan.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
        getMedicationFieldBuilder();
      }
    }
    public Builder clear() {
      super.clear();
      if (medicationBuilder_ == null) {
        medication_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        medicationBuilder_.clear();
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return assign3.grpc.PillDispenser.internal_static_assign3_grpc_MedPlan_descriptor;
    }

    public assign3.grpc.MedPlan getDefaultInstanceForType() {
      return assign3.grpc.MedPlan.getDefaultInstance();
    }

    public assign3.grpc.MedPlan build() {
      assign3.grpc.MedPlan result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public assign3.grpc.MedPlan buildPartial() {
      assign3.grpc.MedPlan result = new assign3.grpc.MedPlan(this);
      int from_bitField0_ = bitField0_;
      if (medicationBuilder_ == null) {
        if (((bitField0_ & 0x00000001) == 0x00000001)) {
          medication_ = java.util.Collections.unmodifiableList(medication_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.medication_ = medication_;
      } else {
        result.medication_ = medicationBuilder_.build();
      }
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof assign3.grpc.MedPlan) {
        return mergeFrom((assign3.grpc.MedPlan)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(assign3.grpc.MedPlan other) {
      if (other == assign3.grpc.MedPlan.getDefaultInstance()) return this;
      if (medicationBuilder_ == null) {
        if (!other.medication_.isEmpty()) {
          if (medication_.isEmpty()) {
            medication_ = other.medication_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureMedicationIsMutable();
            medication_.addAll(other.medication_);
          }
          onChanged();
        }
      } else {
        if (!other.medication_.isEmpty()) {
          if (medicationBuilder_.isEmpty()) {
            medicationBuilder_.dispose();
            medicationBuilder_ = null;
            medication_ = other.medication_;
            bitField0_ = (bitField0_ & ~0x00000001);
            medicationBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getMedicationFieldBuilder() : null;
          } else {
            medicationBuilder_.addAllMessages(other.medication_);
          }
        }
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      assign3.grpc.MedPlan parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (assign3.grpc.MedPlan) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<assign3.grpc.Medication> medication_ =
      java.util.Collections.emptyList();
    private void ensureMedicationIsMutable() {
      if (!((bitField0_ & 0x00000001) == 0x00000001)) {
        medication_ = new java.util.ArrayList<assign3.grpc.Medication>(medication_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        assign3.grpc.Medication, assign3.grpc.Medication.Builder, assign3.grpc.MedicationOrBuilder> medicationBuilder_;

    /**
     * <code>repeated .assign3.grpc.Medication medication = 1;</code>
     */
    public java.util.List<assign3.grpc.Medication> getMedicationList() {
      if (medicationBuilder_ == null) {
        return java.util.Collections.unmodifiableList(medication_);
      } else {
        return medicationBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .assign3.grpc.Medication medication = 1;</code>
     */
    public int getMedicationCount() {
      if (medicationBuilder_ == null) {
        return medication_.size();
      } else {
        return medicationBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .assign3.grpc.Medication medication = 1;</code>
     */
    public assign3.grpc.Medication getMedication(int index) {
      if (medicationBuilder_ == null) {
        return medication_.get(index);
      } else {
        return medicationBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .assign3.grpc.Medication medication = 1;</code>
     */
    public Builder setMedication(
        int index, assign3.grpc.Medication value) {
      if (medicationBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureMedicationIsMutable();
        medication_.set(index, value);
        onChanged();
      } else {
        medicationBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .assign3.grpc.Medication medication = 1;</code>
     */
    public Builder setMedication(
        int index, assign3.grpc.Medication.Builder builderForValue) {
      if (medicationBuilder_ == null) {
        ensureMedicationIsMutable();
        medication_.set(index, builderForValue.build());
        onChanged();
      } else {
        medicationBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .assign3.grpc.Medication medication = 1;</code>
     */
    public Builder addMedication(assign3.grpc.Medication value) {
      if (medicationBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureMedicationIsMutable();
        medication_.add(value);
        onChanged();
      } else {
        medicationBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .assign3.grpc.Medication medication = 1;</code>
     */
    public Builder addMedication(
        int index, assign3.grpc.Medication value) {
      if (medicationBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureMedicationIsMutable();
        medication_.add(index, value);
        onChanged();
      } else {
        medicationBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .assign3.grpc.Medication medication = 1;</code>
     */
    public Builder addMedication(
        assign3.grpc.Medication.Builder builderForValue) {
      if (medicationBuilder_ == null) {
        ensureMedicationIsMutable();
        medication_.add(builderForValue.build());
        onChanged();
      } else {
        medicationBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .assign3.grpc.Medication medication = 1;</code>
     */
    public Builder addMedication(
        int index, assign3.grpc.Medication.Builder builderForValue) {
      if (medicationBuilder_ == null) {
        ensureMedicationIsMutable();
        medication_.add(index, builderForValue.build());
        onChanged();
      } else {
        medicationBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .assign3.grpc.Medication medication = 1;</code>
     */
    public Builder addAllMedication(
        java.lang.Iterable<? extends assign3.grpc.Medication> values) {
      if (medicationBuilder_ == null) {
        ensureMedicationIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, medication_);
        onChanged();
      } else {
        medicationBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .assign3.grpc.Medication medication = 1;</code>
     */
    public Builder clearMedication() {
      if (medicationBuilder_ == null) {
        medication_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        medicationBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .assign3.grpc.Medication medication = 1;</code>
     */
    public Builder removeMedication(int index) {
      if (medicationBuilder_ == null) {
        ensureMedicationIsMutable();
        medication_.remove(index);
        onChanged();
      } else {
        medicationBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .assign3.grpc.Medication medication = 1;</code>
     */
    public assign3.grpc.Medication.Builder getMedicationBuilder(
        int index) {
      return getMedicationFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .assign3.grpc.Medication medication = 1;</code>
     */
    public assign3.grpc.MedicationOrBuilder getMedicationOrBuilder(
        int index) {
      if (medicationBuilder_ == null) {
        return medication_.get(index);  } else {
        return medicationBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .assign3.grpc.Medication medication = 1;</code>
     */
    public java.util.List<? extends assign3.grpc.MedicationOrBuilder> 
         getMedicationOrBuilderList() {
      if (medicationBuilder_ != null) {
        return medicationBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(medication_);
      }
    }
    /**
     * <code>repeated .assign3.grpc.Medication medication = 1;</code>
     */
    public assign3.grpc.Medication.Builder addMedicationBuilder() {
      return getMedicationFieldBuilder().addBuilder(
          assign3.grpc.Medication.getDefaultInstance());
    }
    /**
     * <code>repeated .assign3.grpc.Medication medication = 1;</code>
     */
    public assign3.grpc.Medication.Builder addMedicationBuilder(
        int index) {
      return getMedicationFieldBuilder().addBuilder(
          index, assign3.grpc.Medication.getDefaultInstance());
    }
    /**
     * <code>repeated .assign3.grpc.Medication medication = 1;</code>
     */
    public java.util.List<assign3.grpc.Medication.Builder> 
         getMedicationBuilderList() {
      return getMedicationFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        assign3.grpc.Medication, assign3.grpc.Medication.Builder, assign3.grpc.MedicationOrBuilder> 
        getMedicationFieldBuilder() {
      if (medicationBuilder_ == null) {
        medicationBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            assign3.grpc.Medication, assign3.grpc.Medication.Builder, assign3.grpc.MedicationOrBuilder>(
                medication_,
                ((bitField0_ & 0x00000001) == 0x00000001),
                getParentForChildren(),
                isClean());
        medication_ = null;
      }
      return medicationBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:assign3.grpc.MedPlan)
  }

  // @@protoc_insertion_point(class_scope:assign3.grpc.MedPlan)
  private static final assign3.grpc.MedPlan DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new assign3.grpc.MedPlan();
  }

  public static assign3.grpc.MedPlan getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<MedPlan>
      PARSER = new com.google.protobuf.AbstractParser<MedPlan>() {
    public MedPlan parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new MedPlan(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<MedPlan> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<MedPlan> getParserForType() {
    return PARSER;
  }

  public assign3.grpc.MedPlan getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
