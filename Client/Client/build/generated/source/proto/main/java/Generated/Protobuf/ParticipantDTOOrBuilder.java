// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: AppService.proto

package Generated.Protobuf;

public interface ParticipantDTOOrBuilder extends
    // @@protoc_insertion_point(interface_extends:ParticipantDTO)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>optional string name = 1;</code>
   */
  java.lang.String getName();
  /**
   * <code>optional string name = 1;</code>
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <code>optional int32 age = 2;</code>
   */
  int getAge();

  /**
   * <code>optional .AgeCategoryDTO ageCategory = 3;</code>
   */
  boolean hasAgeCategory();
  /**
   * <code>optional .AgeCategoryDTO ageCategory = 3;</code>
   */
  Generated.Protobuf.AgeCategoryDTO getAgeCategory();
  /**
   * <code>optional .AgeCategoryDTO ageCategory = 3;</code>
   */
  Generated.Protobuf.AgeCategoryDTOOrBuilder getAgeCategoryOrBuilder();
}