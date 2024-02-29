import 'dart:async';

import 'package:adyen_checkout/src/common/model/payment_event.dart';
import 'package:adyen_checkout/src/common/model/payment_result.dart';
import 'package:adyen_checkout/src/components/google_pay/base_google_pay_component.dart';
import 'package:adyen_checkout/src/generated/platform_api.g.dart';
import 'package:adyen_checkout/src/logging/adyen_logger.dart';
import 'package:adyen_checkout/src/util/payment_event_handler.dart';

class GooglePayAdvancedComponent extends BaseGooglePayComponent {
  final Future<PaymentEvent> Function(String) onSubmit;
  final Future<PaymentEvent> Function(String) onAdditionalDetails;
  final PaymentEventHandler paymentEventHandler;
  @override
  final String componentId = "GOOGLE_PAY_ADVANCED_COMPONENT";

  GooglePayAdvancedComponent({
    super.key,
    required super.googlePayPaymentMethod,
    required super.googlePayComponentConfiguration,
    required super.onPaymentResult,
    required this.onSubmit,
    required this.onAdditionalDetails,
    required super.theme,
    required super.type,
    required super.cornerRadius,
    required super.width,
    required super.height,
    super.loadingIndicator,
    super.onUnavailable,
    super.unavailableWidget,
    PaymentEventHandler? paymentEventHandler,
    AdyenLogger? adyenLogger,
  }) : paymentEventHandler = paymentEventHandler ?? PaymentEventHandler();

  @override
  void handleComponentCommunication(dynamic event) {
    isButtonClickable.value = true;
    switch (event.type) {
      case ComponentCommunicationType.onSubmit:
        _onSubmit(event);
      case ComponentCommunicationType.additionalDetails:
        _onAdditionalDetails(event);
      case ComponentCommunicationType.result:
        _onResult(event);
      case ComponentCommunicationType.error:
        _onError(event);
      case ComponentCommunicationType.loading:
        _onLoading();
    }
  }

  Future<void> _onSubmit(ComponentCommunicationModel event) async {
    final PaymentEvent paymentEvent = await onSubmit(event.data as String);
    final PaymentEventDTO paymentEventDTO =
        paymentEventHandler.mapToPaymentEventDTO(paymentEvent);
    componentPlatformApi.onPaymentsResult(paymentEventDTO);
  }

  Future<void> _onAdditionalDetails(ComponentCommunicationModel event) async {
    final PaymentEvent paymentEvent =
        await onAdditionalDetails(event.data as String);
    final PaymentEventDTO paymentEventDTO =
        paymentEventHandler.mapToPaymentEventDTO(paymentEvent);
    componentPlatformApi.onPaymentsDetailsResult(paymentEventDTO);
  }

  void _onResult(ComponentCommunicationModel event) {
    String resultCode = event.paymentResult?.resultCode ?? "";
    adyenLogger.print("Google pay advanced flow result code: $resultCode");
    onPaymentResult(PaymentAdvancedFinished(resultCode: resultCode));
  }

  void _onError(ComponentCommunicationModel event) {
    String errorMessage = event.data as String;
    onPaymentResult(PaymentError(reason: errorMessage));
  }

  void _onLoading() => isLoading.value = true;
}
