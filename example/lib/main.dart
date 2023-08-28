import 'dart:async';

import 'package:adyen_checkout/adyen_checkout.dart';
import 'package:adyen_checkout/platform_api.g.dart';
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
        title: const Text('Plugin example app'),
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

  Future<DropInResultModel> startDropInSessions() async {
    final SessionModel sessionModel =
        await _adyenSessionRepository.createSession(
      Config.amount,
      Config.environment,
    );
    final DropInConfigurationModel dropInConfiguration =
        DropInConfigurationModel(
      environment: Environment.test,
      clientKey: Config.clientKey,
      amount: Config.amount,
      countryCode: Config.countryCode,
    );

    return await _adyenCheckout.startPayment(
      paymentFlow: PaymentFlow.dropInSessions(
        dropInConfiguration: dropInConfiguration,
        sessionModel: sessionModel,
      ),
    );
  }

  Future<DropInResultModel> startDropInAdvancedFlow() async {
    final String paymentMethodsResponse =
        await _adyenSessionRepository.fetchPaymentMethods();
    DropInConfigurationModel dropInConfiguration = DropInConfigurationModel(
      environment: Environment.test,
      clientKey: Config.clientKey,
      amount: Config.amount,
      countryCode: Config.countryCode,
    );

    return await _adyenCheckout.startPayment(
      paymentFlow: PaymentFlow.dropInAdvancedFlow(
        dropInConfiguration: dropInConfiguration,
        paymentMethodsResponse: paymentMethodsResponse,
        postPayments: _adyenSessionRepository.postPayments,
        postPaymentsDetails: _adyenSessionRepository.postPaymentsDetails,
      ),
    );
  }

  _dialogBuilder(BuildContext context, DropInResultModel dropInResultModel) {
    return showDialog(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          title: Text(dropInResultModel.sessionDropInResult.name),
          content: Text("Result code: ${dropInResultModel.result?.resultCode}"),
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
