// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: AppServices.proto

package Generated.Protobuf;

/**
 * <pre>
 * ------------- REQUESTS -------------
 * </pre>
 *
 * Protobuf enum {@code RequestType}
 */
public enum RequestType
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <code>UnknownRequest = 0;</code>
   */
  UnknownRequest(0),
  /**
   * <code>LoginRequest = 1;</code>
   */
  LoginRequest(1),
  /**
   * <code>LogoutRequest = 2;</code>
   */
  LogoutRequest(2),
  /**
   * <code>GetTrialsRequest = 3;</code>
   */
  GetTrialsRequest(3),
  /**
   * <code>GetAgeCategoriesRequest = 4;</code>
   */
  GetAgeCategoriesRequest(4),
  /**
   * <code>GetParticipantsForTrialRequest = 5;</code>
   */
  GetParticipantsForTrialRequest(5),
  /**
   * <code>RegisterParticipantRequest = 6;</code>
   */
  RegisterParticipantRequest(6),
  /**
   * <code>InitialiseConnectionRequest = 7;</code>
   */
  InitialiseConnectionRequest(7),
  UNRECOGNIZED(-1),
  ;

  /**
   * <code>UnknownRequest = 0;</code>
   */
  public static final int UnknownRequest_VALUE = 0;
  /**
   * <code>LoginRequest = 1;</code>
   */
  public static final int LoginRequest_VALUE = 1;
  /**
   * <code>LogoutRequest = 2;</code>
   */
  public static final int LogoutRequest_VALUE = 2;
  /**
   * <code>GetTrialsRequest = 3;</code>
   */
  public static final int GetTrialsRequest_VALUE = 3;
  /**
   * <code>GetAgeCategoriesRequest = 4;</code>
   */
  public static final int GetAgeCategoriesRequest_VALUE = 4;
  /**
   * <code>GetParticipantsForTrialRequest = 5;</code>
   */
  public static final int GetParticipantsForTrialRequest_VALUE = 5;
  /**
   * <code>RegisterParticipantRequest = 6;</code>
   */
  public static final int RegisterParticipantRequest_VALUE = 6;
  /**
   * <code>InitialiseConnectionRequest = 7;</code>
   */
  public static final int InitialiseConnectionRequest_VALUE = 7;


  public final int getNumber() {
    if (this == UNRECOGNIZED) {
      throw new java.lang.IllegalArgumentException(
          "Can't get the number of an unknown enum value.");
    }
    return value;
  }

  /**
   * @deprecated Use {@link #forNumber(int)} instead.
   */
  @java.lang.Deprecated
  public static RequestType valueOf(int value) {
    return forNumber(value);
  }

  public static RequestType forNumber(int value) {
    switch (value) {
      case 0: return UnknownRequest;
      case 1: return LoginRequest;
      case 2: return LogoutRequest;
      case 3: return GetTrialsRequest;
      case 4: return GetAgeCategoriesRequest;
      case 5: return GetParticipantsForTrialRequest;
      case 6: return RegisterParticipantRequest;
      case 7: return InitialiseConnectionRequest;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<RequestType>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      RequestType> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<RequestType>() {
          public RequestType findValueByNumber(int number) {
            return RequestType.forNumber(number);
          }
        };

  public final com.google.protobuf.Descriptors.EnumValueDescriptor
      getValueDescriptor() {
    return getDescriptor().getValues().get(ordinal());
  }
  public final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptorForType() {
    return getDescriptor();
  }
  public static final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptor() {
    return Generated.Protobuf.AppServices.getDescriptor()
        .getEnumTypes().get(0);
  }

  private static final RequestType[] VALUES = values();

  public static RequestType valueOf(
      com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
    if (desc.getType() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "EnumValueDescriptor is not for this type.");
    }
    if (desc.getIndex() == -1) {
      return UNRECOGNIZED;
    }
    return VALUES[desc.getIndex()];
  }

  private final int value;

  private RequestType(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:RequestType)
}
