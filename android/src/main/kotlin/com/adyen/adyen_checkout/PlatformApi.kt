// Autogenerated from Pigeon (v11.0.1), do not edit directly.
// See also: https://pub.dev/packages/pigeon


import android.util.Log
import io.flutter.plugin.common.BasicMessageChannel
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MessageCodec
import io.flutter.plugin.common.StandardMessageCodec
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer

private fun wrapResult(result: Any?): List<Any?> {
  return listOf(result)
}

private fun wrapError(exception: Throwable): List<Any?> {
  if (exception is FlutterError) {
    return listOf(
      exception.code,
      exception.message,
      exception.details
    )
  } else {
    return listOf(
      exception.javaClass.simpleName,
      exception.toString(),
      "Cause: " + exception.cause + ", Stacktrace: " + Log.getStackTraceString(exception)
    )
  }
}

/**
 * Error class for passing custom error details to Flutter via a thrown PlatformException.
 * @property code The error code.
 * @property message The error message.
 * @property details The error details. Must be a datatype supported by the api codec.
 */
class FlutterError (
  val code: String,
  override val message: String? = null,
  val details: Any? = null
) : Throwable()

enum class Environment(val raw: Int) {
  TEST(0),
  EUROPE(1),
  UNITEDSTATES(2),
  AUSTRALIA(3),
  INDIA(4),
  APSE(5);

  companion object {
    fun ofRaw(raw: Int): Environment? {
      return values().firstOrNull { it.raw == raw }
    }
  }
}

enum class AddressMode(val raw: Int) {
  FULL(0),
  POSTALCODE(1),
  NONE(2);

  companion object {
    fun ofRaw(raw: Int): AddressMode? {
      return values().firstOrNull { it.raw == raw }
    }
  }
}

enum class CardAuthMethod(val raw: Int) {
  PANONLY(0),
  CRYPTOGRAM3DS(1);

  companion object {
    fun ofRaw(raw: Int): CardAuthMethod? {
      return values().firstOrNull { it.raw == raw }
    }
  }
}

enum class TotalPriceStatus(val raw: Int) {
  NOTCURRENTLYKNOWN(0),
  ESTIMATED(1),
  FINALPRICE(2);

  companion object {
    fun ofRaw(raw: Int): TotalPriceStatus? {
      return values().firstOrNull { it.raw == raw }
    }
  }
}

enum class GooglePayEnvironment(val raw: Int) {
  TEST(0),
  PRODUCTION(1);

  companion object {
    fun ofRaw(raw: Int): GooglePayEnvironment? {
      return values().firstOrNull { it.raw == raw }
    }
  }
}

enum class PaymentResultEnum(val raw: Int) {
  CANCELLEDBYUSER(0),
  ERROR(1),
  FINISHED(2);

  companion object {
    fun ofRaw(raw: Int): PaymentResultEnum? {
      return values().firstOrNull { it.raw == raw }
    }
  }
}

enum class PlatformCommunicationType(val raw: Int) {
  PAYMENTCOMPONENT(0),
  ADDITIONALDETAILS(1),
  RESULT(2);

  companion object {
    fun ofRaw(raw: Int): PlatformCommunicationType? {
      return values().firstOrNull { it.raw == raw }
    }
  }
}

enum class DropInResultType(val raw: Int) {
  FINISHED(0),
  ACTION(1),
  ERROR(2);

  companion object {
    fun ofRaw(raw: Int): DropInResultType? {
      return values().firstOrNull { it.raw == raw }
    }
  }
}

enum class CashAppPayEnvironment(val raw: Int) {
  SANDBOX(0),
  PRODUCTION(1);

  companion object {
    fun ofRaw(raw: Int): CashAppPayEnvironment? {
      return values().firstOrNull { it.raw == raw }
    }
  }
}

/** Generated class from Pigeon that represents data sent in messages. */
data class Session (
  val id: String,
  val sessionData: String

) {
  companion object {
    @Suppress("UNCHECKED_CAST")
    fun fromList(list: List<Any?>): Session {
      val id = list[0] as String
      val sessionData = list[1] as String
      return Session(id, sessionData)
    }
  }
  fun toList(): List<Any?> {
    return listOf<Any?>(
      id,
      sessionData,
    )
  }
}

/** Generated class from Pigeon that represents data sent in messages. */
data class Amount (
  val currency: String? = null,
  val value: Long

) {
  companion object {
    @Suppress("UNCHECKED_CAST")
    fun fromList(list: List<Any?>): Amount {
      val currency = list[0] as String?
      val value = list[1].let { if (it is Int) it.toLong() else it as Long }
      return Amount(currency, value)
    }
  }
  fun toList(): List<Any?> {
    return listOf<Any?>(
      currency,
      value,
    )
  }
}

/** Generated class from Pigeon that represents data sent in messages. */
data class AnalyticsOptionsDTO (
  val enabled: Boolean? = null,
  val payload: String? = null

) {
  companion object {
    @Suppress("UNCHECKED_CAST")
    fun fromList(list: List<Any?>): AnalyticsOptionsDTO {
      val enabled = list[0] as Boolean?
      val payload = list[1] as String?
      return AnalyticsOptionsDTO(enabled, payload)
    }
  }
  fun toList(): List<Any?> {
    return listOf<Any?>(
      enabled,
      payload,
    )
  }
}

/** Generated class from Pigeon that represents data sent in messages. */
data class DropInConfigurationDTO (
  val environment: Environment,
  val clientKey: String,
  val countryCode: String,
  val amount: Amount,
  val shopperLocale: String,
  val analyticsOptionsDTO: AnalyticsOptionsDTO? = null,
  val showPreselectedStoredPaymentMethod: Boolean? = null,
  val skipListWhenSinglePaymentMethod: Boolean? = null,
  val cardsConfigurationDTO: CardsConfigurationDTO? = null,
  val applePayConfigurationDTO: ApplePayConfigurationDTO? = null,
  val googlePayConfigurationDTO: GooglePayConfigurationDTO? = null,
  val cashAppPayConfigurationDTO: CashAppPayConfigurationDTO? = null

) {
  companion object {
    @Suppress("UNCHECKED_CAST")
    fun fromList(list: List<Any?>): DropInConfigurationDTO {
      val environment = Environment.ofRaw(list[0] as Int)!!
      val clientKey = list[1] as String
      val countryCode = list[2] as String
      val amount = Amount.fromList(list[3] as List<Any?>)
      val shopperLocale = list[4] as String
      val analyticsOptionsDTO: AnalyticsOptionsDTO? = (list[5] as List<Any?>?)?.let {
        AnalyticsOptionsDTO.fromList(it)
      }
      val showPreselectedStoredPaymentMethod = list[6] as Boolean?
      val skipListWhenSinglePaymentMethod = list[7] as Boolean?
      val cardsConfigurationDTO: CardsConfigurationDTO? = (list[8] as List<Any?>?)?.let {
        CardsConfigurationDTO.fromList(it)
      }
      val applePayConfigurationDTO: ApplePayConfigurationDTO? = (list[9] as List<Any?>?)?.let {
        ApplePayConfigurationDTO.fromList(it)
      }
      val googlePayConfigurationDTO: GooglePayConfigurationDTO? = (list[10] as List<Any?>?)?.let {
        GooglePayConfigurationDTO.fromList(it)
      }
      val cashAppPayConfigurationDTO: CashAppPayConfigurationDTO? = (list[11] as List<Any?>?)?.let {
        CashAppPayConfigurationDTO.fromList(it)
      }
      return DropInConfigurationDTO(environment, clientKey, countryCode, amount, shopperLocale, analyticsOptionsDTO, showPreselectedStoredPaymentMethod, skipListWhenSinglePaymentMethod, cardsConfigurationDTO, applePayConfigurationDTO, googlePayConfigurationDTO, cashAppPayConfigurationDTO)
    }
  }
  fun toList(): List<Any?> {
    return listOf<Any?>(
      environment.raw,
      clientKey,
      countryCode,
      amount.toList(),
      shopperLocale,
      analyticsOptionsDTO?.toList(),
      showPreselectedStoredPaymentMethod,
      skipListWhenSinglePaymentMethod,
      cardsConfigurationDTO?.toList(),
      applePayConfigurationDTO?.toList(),
      googlePayConfigurationDTO?.toList(),
      cashAppPayConfigurationDTO?.toList(),
    )
  }
}

/** Generated class from Pigeon that represents data sent in messages. */
data class CardsConfigurationDTO (
  val holderNameRequired: Boolean,
  val addressMode: AddressMode,
  val showStorePaymentField: Boolean,
  val showCvcForStoredCard: Boolean,
  val showCvc: Boolean,
  val showKcpField: Boolean,
  val showSocialSecurityNumberField: Boolean,
  val supportedCardTypes: List<String?>

) {
  companion object {
    @Suppress("UNCHECKED_CAST")
    fun fromList(list: List<Any?>): CardsConfigurationDTO {
      val holderNameRequired = list[0] as Boolean
      val addressMode = AddressMode.ofRaw(list[1] as Int)!!
      val showStorePaymentField = list[2] as Boolean
      val showCvcForStoredCard = list[3] as Boolean
      val showCvc = list[4] as Boolean
      val showKcpField = list[5] as Boolean
      val showSocialSecurityNumberField = list[6] as Boolean
      val supportedCardTypes = list[7] as List<String?>
      return CardsConfigurationDTO(holderNameRequired, addressMode, showStorePaymentField, showCvcForStoredCard, showCvc, showKcpField, showSocialSecurityNumberField, supportedCardTypes)
    }
  }
  fun toList(): List<Any?> {
    return listOf<Any?>(
      holderNameRequired,
      addressMode.raw,
      showStorePaymentField,
      showCvcForStoredCard,
      showCvc,
      showKcpField,
      showSocialSecurityNumberField,
      supportedCardTypes,
    )
  }
}

/** Generated class from Pigeon that represents data sent in messages. */
data class ApplePayConfigurationDTO (
  val merchantId: String,
  val merchantName: String,
  val allowOnboarding: Boolean

) {
  companion object {
    @Suppress("UNCHECKED_CAST")
    fun fromList(list: List<Any?>): ApplePayConfigurationDTO {
      val merchantId = list[0] as String
      val merchantName = list[1] as String
      val allowOnboarding = list[2] as Boolean
      return ApplePayConfigurationDTO(merchantId, merchantName, allowOnboarding)
    }
  }
  fun toList(): List<Any?> {
    return listOf<Any?>(
      merchantId,
      merchantName,
      allowOnboarding,
    )
  }
}

/** Generated class from Pigeon that represents data sent in messages. */
data class GooglePayConfigurationDTO (
  val merchantAccount: String,
  val allowedCardNetworks: List<String?>,
  val allowedAuthMethods: List<String?>,
  val totalPriceStatus: TotalPriceStatus,
  val allowPrepaidCards: Boolean,
  val billingAddressRequired: Boolean,
  val emailRequired: Boolean,
  val shippingAddressRequired: Boolean,
  val existingPaymentMethodRequired: Boolean,
  val googlePayEnvironment: GooglePayEnvironment

) {
  companion object {
    @Suppress("UNCHECKED_CAST")
    fun fromList(list: List<Any?>): GooglePayConfigurationDTO {
      val merchantAccount = list[0] as String
      val allowedCardNetworks = list[1] as List<String?>
      val allowedAuthMethods = list[2] as List<String?>
      val totalPriceStatus = TotalPriceStatus.ofRaw(list[3] as Int)!!
      val allowPrepaidCards = list[4] as Boolean
      val billingAddressRequired = list[5] as Boolean
      val emailRequired = list[6] as Boolean
      val shippingAddressRequired = list[7] as Boolean
      val existingPaymentMethodRequired = list[8] as Boolean
      val googlePayEnvironment = GooglePayEnvironment.ofRaw(list[9] as Int)!!
      return GooglePayConfigurationDTO(merchantAccount, allowedCardNetworks, allowedAuthMethods, totalPriceStatus, allowPrepaidCards, billingAddressRequired, emailRequired, shippingAddressRequired, existingPaymentMethodRequired, googlePayEnvironment)
    }
  }
  fun toList(): List<Any?> {
    return listOf<Any?>(
      merchantAccount,
      allowedCardNetworks,
      allowedAuthMethods,
      totalPriceStatus.raw,
      allowPrepaidCards,
      billingAddressRequired,
      emailRequired,
      shippingAddressRequired,
      existingPaymentMethodRequired,
      googlePayEnvironment.raw,
    )
  }
}

/** Generated class from Pigeon that represents data sent in messages. */
data class CashAppPayConfigurationDTO (
  val cashAppPayEnvironment: CashAppPayEnvironment,
  val returnUrl: String

) {
  companion object {
    @Suppress("UNCHECKED_CAST")
    fun fromList(list: List<Any?>): CashAppPayConfigurationDTO {
      val cashAppPayEnvironment = CashAppPayEnvironment.ofRaw(list[0] as Int)!!
      val returnUrl = list[1] as String
      return CashAppPayConfigurationDTO(cashAppPayEnvironment, returnUrl)
    }
  }
  fun toList(): List<Any?> {
    return listOf<Any?>(
      cashAppPayEnvironment.raw,
      returnUrl,
    )
  }
}

/** Generated class from Pigeon that represents data sent in messages. */
data class PaymentResult (
  val type: PaymentResultEnum,
  val reason: String? = null,
  val result: PaymentResultModel? = null

) {
  companion object {
    @Suppress("UNCHECKED_CAST")
    fun fromList(list: List<Any?>): PaymentResult {
      val type = PaymentResultEnum.ofRaw(list[0] as Int)!!
      val reason = list[1] as String?
      val result: PaymentResultModel? = (list[2] as List<Any?>?)?.let {
        PaymentResultModel.fromList(it)
      }
      return PaymentResult(type, reason, result)
    }
  }
  fun toList(): List<Any?> {
    return listOf<Any?>(
      type.raw,
      reason,
      result?.toList(),
    )
  }
}

/** Generated class from Pigeon that represents data sent in messages. */
data class PaymentResultModel (
  val sessionId: String? = null,
  val sessionData: String? = null,
  val resultCode: String? = null,
  val order: OrderResponseModel? = null

) {
  companion object {
    @Suppress("UNCHECKED_CAST")
    fun fromList(list: List<Any?>): PaymentResultModel {
      val sessionId = list[0] as String?
      val sessionData = list[1] as String?
      val resultCode = list[2] as String?
      val order: OrderResponseModel? = (list[3] as List<Any?>?)?.let {
        OrderResponseModel.fromList(it)
      }
      return PaymentResultModel(sessionId, sessionData, resultCode, order)
    }
  }
  fun toList(): List<Any?> {
    return listOf<Any?>(
      sessionId,
      sessionData,
      resultCode,
      order?.toList(),
    )
  }
}

/** Generated class from Pigeon that represents data sent in messages. */
data class OrderResponseModel (
  val pspReference: String,
  val orderData: String,
  val amount: Amount? = null,
  val remainingAmount: Amount? = null

) {
  companion object {
    @Suppress("UNCHECKED_CAST")
    fun fromList(list: List<Any?>): OrderResponseModel {
      val pspReference = list[0] as String
      val orderData = list[1] as String
      val amount: Amount? = (list[2] as List<Any?>?)?.let {
        Amount.fromList(it)
      }
      val remainingAmount: Amount? = (list[3] as List<Any?>?)?.let {
        Amount.fromList(it)
      }
      return OrderResponseModel(pspReference, orderData, amount, remainingAmount)
    }
  }
  fun toList(): List<Any?> {
    return listOf<Any?>(
      pspReference,
      orderData,
      amount?.toList(),
      remainingAmount?.toList(),
    )
  }
}

/** Generated class from Pigeon that represents data sent in messages. */
data class PlatformCommunicationModel (
  val type: PlatformCommunicationType,
  val data: String? = null,
  val paymentResult: PaymentResult? = null

) {
  companion object {
    @Suppress("UNCHECKED_CAST")
    fun fromList(list: List<Any?>): PlatformCommunicationModel {
      val type = PlatformCommunicationType.ofRaw(list[0] as Int)!!
      val data = list[1] as String?
      val paymentResult: PaymentResult? = (list[2] as List<Any?>?)?.let {
        PaymentResult.fromList(it)
      }
      return PlatformCommunicationModel(type, data, paymentResult)
    }
  }
  fun toList(): List<Any?> {
    return listOf<Any?>(
      type.raw,
      data,
      paymentResult?.toList(),
    )
  }
}

/** Generated class from Pigeon that represents data sent in messages. */
data class DropInResult (
  val dropInResultType: DropInResultType,
  val result: String? = null,
  val actionResponse: Map<String?, Any?>? = null,
  val error: DropInError? = null

) {
  companion object {
    @Suppress("UNCHECKED_CAST")
    fun fromList(list: List<Any?>): DropInResult {
      val dropInResultType = DropInResultType.ofRaw(list[0] as Int)!!
      val result = list[1] as String?
      val actionResponse = list[2] as Map<String?, Any?>?
      val error: DropInError? = (list[3] as List<Any?>?)?.let {
        DropInError.fromList(it)
      }
      return DropInResult(dropInResultType, result, actionResponse, error)
    }
  }
  fun toList(): List<Any?> {
    return listOf<Any?>(
      dropInResultType.raw,
      result,
      actionResponse,
      error?.toList(),
    )
  }
}

/** Generated class from Pigeon that represents data sent in messages. */
data class DropInError (
  val errorMessage: String? = null,
  val reason: String? = null,
  val dismissDropIn: Boolean? = null

) {
  companion object {
    @Suppress("UNCHECKED_CAST")
    fun fromList(list: List<Any?>): DropInError {
      val errorMessage = list[0] as String?
      val reason = list[1] as String?
      val dismissDropIn = list[2] as Boolean?
      return DropInError(errorMessage, reason, dismissDropIn)
    }
  }
  fun toList(): List<Any?> {
    return listOf<Any?>(
      errorMessage,
      reason,
      dismissDropIn,
    )
  }
}

@Suppress("UNCHECKED_CAST")
private object CheckoutPlatformInterfaceCodec : StandardMessageCodec() {
  override fun readValueOfType(type: Byte, buffer: ByteBuffer): Any? {
    return when (type) {
      128.toByte() -> {
        return (readValue(buffer) as? List<Any?>)?.let {
          Amount.fromList(it)
        }
      }
      129.toByte() -> {
        return (readValue(buffer) as? List<Any?>)?.let {
          AnalyticsOptionsDTO.fromList(it)
        }
      }
      130.toByte() -> {
        return (readValue(buffer) as? List<Any?>)?.let {
          ApplePayConfigurationDTO.fromList(it)
        }
      }
      131.toByte() -> {
        return (readValue(buffer) as? List<Any?>)?.let {
          CardsConfigurationDTO.fromList(it)
        }
      }
      132.toByte() -> {
        return (readValue(buffer) as? List<Any?>)?.let {
          CashAppPayConfigurationDTO.fromList(it)
        }
      }
      133.toByte() -> {
        return (readValue(buffer) as? List<Any?>)?.let {
          DropInConfigurationDTO.fromList(it)
        }
      }
      134.toByte() -> {
        return (readValue(buffer) as? List<Any?>)?.let {
          DropInError.fromList(it)
        }
      }
      135.toByte() -> {
        return (readValue(buffer) as? List<Any?>)?.let {
          DropInResult.fromList(it)
        }
      }
      136.toByte() -> {
        return (readValue(buffer) as? List<Any?>)?.let {
          GooglePayConfigurationDTO.fromList(it)
        }
      }
      137.toByte() -> {
        return (readValue(buffer) as? List<Any?>)?.let {
          Session.fromList(it)
        }
      }
      else -> super.readValueOfType(type, buffer)
    }
  }
  override fun writeValue(stream: ByteArrayOutputStream, value: Any?)   {
    when (value) {
      is Amount -> {
        stream.write(128)
        writeValue(stream, value.toList())
      }
      is AnalyticsOptionsDTO -> {
        stream.write(129)
        writeValue(stream, value.toList())
      }
      is ApplePayConfigurationDTO -> {
        stream.write(130)
        writeValue(stream, value.toList())
      }
      is CardsConfigurationDTO -> {
        stream.write(131)
        writeValue(stream, value.toList())
      }
      is CashAppPayConfigurationDTO -> {
        stream.write(132)
        writeValue(stream, value.toList())
      }
      is DropInConfigurationDTO -> {
        stream.write(133)
        writeValue(stream, value.toList())
      }
      is DropInError -> {
        stream.write(134)
        writeValue(stream, value.toList())
      }
      is DropInResult -> {
        stream.write(135)
        writeValue(stream, value.toList())
      }
      is GooglePayConfigurationDTO -> {
        stream.write(136)
        writeValue(stream, value.toList())
      }
      is Session -> {
        stream.write(137)
        writeValue(stream, value.toList())
      }
      else -> super.writeValue(stream, value)
    }
  }
}

/** Generated interface from Pigeon that represents a handler of messages from Flutter. */
interface CheckoutPlatformInterface {
  fun getPlatformVersion(callback: (Result<String>) -> Unit)
  fun getReturnUrl(callback: (Result<String>) -> Unit)
  fun startDropInSessionPayment(dropInConfigurationDTO: DropInConfigurationDTO, session: Session)
  fun startDropInAdvancedFlowPayment(dropInConfigurationDTO: DropInConfigurationDTO, paymentMethodsResponse: String)
  fun onPaymentsResult(paymentsResult: DropInResult)
  fun onPaymentsDetailsResult(paymentsDetailsResult: DropInResult)

  companion object {
    /** The codec used by CheckoutPlatformInterface. */
    val codec: MessageCodec<Any?> by lazy {
      CheckoutPlatformInterfaceCodec
    }
    /** Sets up an instance of `CheckoutPlatformInterface` to handle messages through the `binaryMessenger`. */
    @Suppress("UNCHECKED_CAST")
    fun setUp(binaryMessenger: BinaryMessenger, api: CheckoutPlatformInterface?) {
      run {
        val channel = BasicMessageChannel<Any?>(binaryMessenger, "dev.flutter.pigeon.adyen_checkout.CheckoutPlatformInterface.getPlatformVersion", codec)
        if (api != null) {
          channel.setMessageHandler { _, reply ->
            api.getPlatformVersion() { result: Result<String> ->
              val error = result.exceptionOrNull()
              if (error != null) {
                reply.reply(wrapError(error))
              } else {
                val data = result.getOrNull()
                reply.reply(wrapResult(data))
              }
            }
          }
        } else {
          channel.setMessageHandler(null)
        }
      }
      run {
        val channel = BasicMessageChannel<Any?>(binaryMessenger, "dev.flutter.pigeon.adyen_checkout.CheckoutPlatformInterface.getReturnUrl", codec)
        if (api != null) {
          channel.setMessageHandler { _, reply ->
            api.getReturnUrl() { result: Result<String> ->
              val error = result.exceptionOrNull()
              if (error != null) {
                reply.reply(wrapError(error))
              } else {
                val data = result.getOrNull()
                reply.reply(wrapResult(data))
              }
            }
          }
        } else {
          channel.setMessageHandler(null)
        }
      }
      run {
        val channel = BasicMessageChannel<Any?>(binaryMessenger, "dev.flutter.pigeon.adyen_checkout.CheckoutPlatformInterface.startDropInSessionPayment", codec)
        if (api != null) {
          channel.setMessageHandler { message, reply ->
            val args = message as List<Any?>
            val dropInConfigurationDTOArg = args[0] as DropInConfigurationDTO
            val sessionArg = args[1] as Session
            var wrapped: List<Any?>
            try {
              api.startDropInSessionPayment(dropInConfigurationDTOArg, sessionArg)
              wrapped = listOf<Any?>(null)
            } catch (exception: Throwable) {
              wrapped = wrapError(exception)
            }
            reply.reply(wrapped)
          }
        } else {
          channel.setMessageHandler(null)
        }
      }
      run {
        val channel = BasicMessageChannel<Any?>(binaryMessenger, "dev.flutter.pigeon.adyen_checkout.CheckoutPlatformInterface.startDropInAdvancedFlowPayment", codec)
        if (api != null) {
          channel.setMessageHandler { message, reply ->
            val args = message as List<Any?>
            val dropInConfigurationDTOArg = args[0] as DropInConfigurationDTO
            val paymentMethodsResponseArg = args[1] as String
            var wrapped: List<Any?>
            try {
              api.startDropInAdvancedFlowPayment(dropInConfigurationDTOArg, paymentMethodsResponseArg)
              wrapped = listOf<Any?>(null)
            } catch (exception: Throwable) {
              wrapped = wrapError(exception)
            }
            reply.reply(wrapped)
          }
        } else {
          channel.setMessageHandler(null)
        }
      }
      run {
        val channel = BasicMessageChannel<Any?>(binaryMessenger, "dev.flutter.pigeon.adyen_checkout.CheckoutPlatformInterface.onPaymentsResult", codec)
        if (api != null) {
          channel.setMessageHandler { message, reply ->
            val args = message as List<Any?>
            val paymentsResultArg = args[0] as DropInResult
            var wrapped: List<Any?>
            try {
              api.onPaymentsResult(paymentsResultArg)
              wrapped = listOf<Any?>(null)
            } catch (exception: Throwable) {
              wrapped = wrapError(exception)
            }
            reply.reply(wrapped)
          }
        } else {
          channel.setMessageHandler(null)
        }
      }
      run {
        val channel = BasicMessageChannel<Any?>(binaryMessenger, "dev.flutter.pigeon.adyen_checkout.CheckoutPlatformInterface.onPaymentsDetailsResult", codec)
        if (api != null) {
          channel.setMessageHandler { message, reply ->
            val args = message as List<Any?>
            val paymentsDetailsResultArg = args[0] as DropInResult
            var wrapped: List<Any?>
            try {
              api.onPaymentsDetailsResult(paymentsDetailsResultArg)
              wrapped = listOf<Any?>(null)
            } catch (exception: Throwable) {
              wrapped = wrapError(exception)
            }
            reply.reply(wrapped)
          }
        } else {
          channel.setMessageHandler(null)
        }
      }
    }
  }
}
@Suppress("UNCHECKED_CAST")
private object CheckoutFlutterApiCodec : StandardMessageCodec() {
  override fun readValueOfType(type: Byte, buffer: ByteBuffer): Any? {
    return when (type) {
      128.toByte() -> {
        return (readValue(buffer) as? List<Any?>)?.let {
          Amount.fromList(it)
        }
      }
      129.toByte() -> {
        return (readValue(buffer) as? List<Any?>)?.let {
          OrderResponseModel.fromList(it)
        }
      }
      130.toByte() -> {
        return (readValue(buffer) as? List<Any?>)?.let {
          PaymentResult.fromList(it)
        }
      }
      131.toByte() -> {
        return (readValue(buffer) as? List<Any?>)?.let {
          PaymentResultModel.fromList(it)
        }
      }
      132.toByte() -> {
        return (readValue(buffer) as? List<Any?>)?.let {
          PlatformCommunicationModel.fromList(it)
        }
      }
      else -> super.readValueOfType(type, buffer)
    }
  }
  override fun writeValue(stream: ByteArrayOutputStream, value: Any?)   {
    when (value) {
      is Amount -> {
        stream.write(128)
        writeValue(stream, value.toList())
      }
      is OrderResponseModel -> {
        stream.write(129)
        writeValue(stream, value.toList())
      }
      is PaymentResult -> {
        stream.write(130)
        writeValue(stream, value.toList())
      }
      is PaymentResultModel -> {
        stream.write(131)
        writeValue(stream, value.toList())
      }
      is PlatformCommunicationModel -> {
        stream.write(132)
        writeValue(stream, value.toList())
      }
      else -> super.writeValue(stream, value)
    }
  }
}

/** Generated class from Pigeon that represents Flutter messages that can be called from Kotlin. */
@Suppress("UNCHECKED_CAST")
class CheckoutFlutterApi(private val binaryMessenger: BinaryMessenger) {
  companion object {
    /** The codec used by CheckoutFlutterApi. */
    val codec: MessageCodec<Any?> by lazy {
      CheckoutFlutterApiCodec
    }
  }
  fun onDropInSessionResult(sessionPaymentResultArg: PaymentResult, callback: () -> Unit) {
    val channel = BasicMessageChannel<Any?>(binaryMessenger, "dev.flutter.pigeon.adyen_checkout.CheckoutFlutterApi.onDropInSessionResult", codec)
    channel.send(listOf(sessionPaymentResultArg)) {
      callback()
    }
  }
  fun onDropInAdvancedFlowPlatformCommunication(platformCommunicationModelArg: PlatformCommunicationModel, callback: () -> Unit) {
    val channel = BasicMessageChannel<Any?>(binaryMessenger, "dev.flutter.pigeon.adyen_checkout.CheckoutFlutterApi.onDropInAdvancedFlowPlatformCommunication", codec)
    channel.send(listOf(platformCommunicationModelArg)) {
      callback()
    }
  }
}
