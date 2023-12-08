import 'dart:io';

import 'package:adyen_checkout/adyen_checkout.dart';
import 'package:adyen_checkout_example/config.dart';
import 'package:adyen_checkout_example/network/service.dart';
import 'package:adyen_checkout_example/repositories/payment_flow_outcome_handler.dart';

class AdyenBaseRepository {
  AdyenBaseRepository({
    required this.adyenCheckout,
    required this.service,
  });

  final AdyenCheckout adyenCheckout;
  final Service service;
  final PaymentFlowOutcomeHandler paymentFlowOutcomeHandler =
      PaymentFlowOutcomeHandler();

  Future<String> determineBaseReturnUrl() async {
    if (Platform.isAndroid) {
      return await adyenCheckout.getReturnUrl();
    } else if (Platform.isIOS) {
      return Config.iOSReturnUrl;
    } else {
      throw Exception("Unsupported platform");
    }
  }

  String determineChannel() {
    if (Platform.isAndroid) {
      return "Android";
    }

    if (Platform.isIOS) {
      return "iOS";
    }

    throw Exception("Unsupported platform");
  }
}