/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */

import React from 'react';
import type {Node} from 'react';
import {
  SafeAreaView,
  ScrollView,
  StatusBar,
  StyleSheet,
  Text,
  useColorScheme,
  View,
} from 'react-native';

import {
  Colors,
  DebugInstructions,
  Header,
  LearnMoreLinks,
  ReloadInstructions,
} from 'react-native/Libraries/NewAppScreen';
import OkraView from "okra";


const Section = ({children, title}): Node => {
  const isDarkMode = useColorScheme() === 'dark';
  return (
    <View style={styles.sectionContainer}>
      <Text
        style={[
          styles.sectionTitle,
          {
            color: isDarkMode ? Colors.white : Colors.black,
          },
        ]}>
        {title}
      </Text>
      <Text
        style={[
          styles.sectionDescription,
          {
            color: isDarkMode ? Colors.light : Colors.dark,
          },
        ]}>
        {children}
      </Text>
    </View>
  );
};

const App: () => Node = () => {
  const okraOptions = {
    callback_url: 'https://webhook.site/ded54b3f-f4f5-4fa1-86c3-0def6098fb4d',
    clientName: 'client',
    color: '#953ab7',
    connectMessage: 'Which account do you want to connect with?',
    currency: 'NGN',
    env: 'production', // for sandbox use production-sandbox
    exp: '2020-08-06',
    filter: {
      banks: ['access-bank', 'guaranty-trust-bank'],
      industry_type: 'all',
    },
    options: {saverid: 'this is it'},
    isCorporate: false,
    key: 'fa85e5ce-0e4e-5a88-883d-9ba9b4357683',
    limit: '24',
    logo: 'https://cdn.okra.ng/images/icon.svg',
    products: ['auth', 'balance', 'identity', 'transactions'],
    redirect_url: 'redirect',
    success_message: 'this is the success message',
    success_title: 'it has entered success',
    token: '5da6358130a943486f33dced',
    widget_failed: '',
    widget_success: 'Your account was successfully linked to Okra, Inc',
  };
  return (
      <OkraView
          okraOptions={okraOptions}
          onClose={response => {
            console.log('on close');
          }}
          onSuccess={response => {
            console.log('on success', response);
          }}
          onError={response => {
            console.log('on error');
          }}
      />
  );
};

const styles = StyleSheet.create({
  sectionContainer: {
    marginTop: 32,
    paddingHorizontal: 24,
  },
  sectionTitle: {
    fontSize: 24,
    fontWeight: '600',
  },
  sectionDescription: {
    marginTop: 8,
    fontSize: 18,
    fontWeight: '400',
  },
  highlight: {
    fontWeight: '700',
  },
});

export default App;
