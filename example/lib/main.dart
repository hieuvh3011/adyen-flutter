import 'dart:async';

import 'package:adyen_checkout/adyen_checkout.dart';
import 'package:adyen_checkout_example/config.dart';
import 'package:adyen_checkout_example/network/service.dart';
import 'package:adyen_checkout_example/repositories/adyen_sessions_repository.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() {
  runApp(const MaterialApp(home: MyApp()));
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _platformVersion = 'Unknown';
  final _adyenCheckout = AdyenCheckout();
  late AdyenSessionsRepository _adyenSessionRepository;

  @override
  void initState() {
    super.initState();
    initPlatformState();
    _adyenCheckout.enableLogging(loggingEnabled: true);
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initPlatformState() async {
    _adyenSessionRepository = AdyenSessionsRepository(
      adyenCheckout: _adyenCheckout,
      service: Service(),
    );

    String platformVersion;
    // Platform messages may fail, so we use a try/catch PlatformException.
    // We also handle the message potentially returning null.
    try {
      platformVersion = await _adyenCheckout.getPlatformVersion();
    } on PlatformException {
      platformVersion = 'Failed to get platform version.';
    }

    // If the widget was removed from the tree while the asynchronous platform
    // message was in flight, we want to discard the reply rather than calling
    // setState to update our non-existent appearance.
    if (!mounted) return;

    setState(() {
      _platformVersion = platformVersion;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Center(child: Text('Checkout example app')),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text('Running on: $_platformVersion\n'),
            TextButton(
                onPressed: () async {
                  final result = await startDropInSessions();
                  _dialogBuilder(context, result);
                },
                child: const Text("DropIn sessions")),
            TextButton(
                onPressed: () async {
                  final result = await startDropInAdvancedFlow();
                  _dialogBuilder(context, result);
                },
                child: const Text("DropIn advanced flow"))
          ],
        ),
      ),
    );
  }

  Future<PaymentResult> startDropInSessions() async {
    final Session session = await _adyenSessionRepository.createSession(
      Config.amount,
      Config.environment,
    );

    final CardsConfiguration cardsConfiguration = CardsConfiguration(
      showStorePaymentField: true,
    );

    final StoredPaymentMethodConfiguration storedPaymentMethodConfiguration =
        StoredPaymentMethodConfiguration(
      showPreselectedStoredPaymentMethod: true,
      isRemoveStoredPaymentMethodEnabled: true,
      deleteStoredPaymentMethodCallback:
          _adyenSessionRepository.deleteStoredPaymentMethod,
    );

    final CashAppPayConfiguration cashAppPayConfiguration =
        await _createCashAppPayConfiguration();

    final DropInConfiguration dropInConfiguration = DropInConfiguration(
      environment: Environment.test,
      clientKey: Config.clientKey,
      countryCode: Config.countryCode,
      amount: Config.amount,
      shopperLocale: Config.shopperLocale,
      cardsConfiguration: cardsConfiguration,
      storedPaymentMethodConfiguration: storedPaymentMethodConfiguration,
      cashAppPayConfiguration: cashAppPayConfiguration,
    );

    return await _adyenCheckout.startPayment(
      paymentFlow: DropInSession(
        dropInConfiguration: dropInConfiguration,
        session: session,
      ),
    );
  }

  Future<PaymentResult> startDropInAdvancedFlow() async {
    final String paymentMethodsResponse =
        await _adyenSessionRepository.fetchPaymentMethods();

    final CardsConfiguration cardsConfiguration =
        CardsConfiguration(showStorePaymentField: true);

    final ApplePayConfiguration applePayConfiguration = ApplePayConfiguration(
      merchantId: Config.merchantAccount,
      merchantName: Config.merchantName,
    );

    final GooglePayConfiguration googlePayConfiguration =
        GooglePayConfiguration(
      googlePayEnvironment: GooglePayEnvironment.test,
      shippingAddressRequired: true,
      billingAddressRequired: true,
    );

    final CashAppPayConfiguration cashAppPayConfiguration =
        await _createCashAppPayConfiguration();

    final StoredPaymentMethodConfiguration storedPaymentMethodConfiguration =
        StoredPaymentMethodConfiguration(
      showPreselectedStoredPaymentMethod: false,
      isRemoveStoredPaymentMethodEnabled: true,
      deleteStoredPaymentMethodCallback:
          _adyenSessionRepository.deleteStoredPaymentMethod,
    );

    final DropInConfiguration dropInConfiguration = DropInConfiguration(
      environment: Environment.test,
      clientKey: Config.clientKey,
      countryCode: Config.countryCode,
      shopperLocale: Config.shopperLocale,
      amount: Config.amount,
      cardsConfiguration: cardsConfiguration,
      applePayConfiguration: applePayConfiguration,
      googlePayConfiguration: googlePayConfiguration,
      cashAppPayConfiguration: cashAppPayConfiguration,
      storedPaymentMethodConfiguration: storedPaymentMethodConfiguration,
    );

    return await _adyenCheckout.startPayment(
      paymentFlow: DropInAdvancedFlow(
        dropInConfiguration: dropInConfiguration,
        paymentMethodsResponse: paymentMethodsResponse,
        postPayments: _adyenSessionRepository.postPayments,
        postPaymentsDetails: _adyenSessionRepository.postPaymentsDetails,
      ),
    );
  }

  //To support CashAppPay please add "pod 'Adyen/CashAppPay'" to your Podfile.
  Future<CashAppPayConfiguration> _createCashAppPayConfiguration() async {
    return CashAppPayConfiguration(
      CashAppPayEnvironment.sandbox,
      await _adyenSessionRepository.determineExampleReturnUrl(),
    );
  }


  _dialogBuilder(BuildContext context, PaymentResult paymentResult) {
    String message = "";
    if (paymentResult.result != null) {
      message = "Result code: ${paymentResult.result?.resultCode}";
    } else {
      message = "Error: ${paymentResult.reason}";
    }

    return showDialog(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          title: Text(paymentResult.type.name),
          content: Text(message),
          actions: <Widget>[
            TextButton(
              style: TextButton.styleFrom(
                textStyle: Theme.of(context).textTheme.labelLarge,
              ),
              child: const Text('Close'),
              onPressed: () {
                Navigator.of(context).pop();
              },
            ),
          ],
        );
      },
    );
  }
}
