import React, {createContext} from 'react';

import RootStackNavigator from '@navigators/stack/RootStack';
import {DefaultTheme, NavigationContainer, createNavigationContainerRef} from '@react-navigation/native';

import {CustomToastProvider} from '@platformPackage/Toast';
import {makeLinkConfig} from './navigationFunctions';
import {navigationConfig} from './navigationConfig';
export const _rootNavigationRef = createNavigationContainerRef();

export const NavigationStateContext = createContext();
const reducer = (prevState, action) => {
  switch (action.type) {
    default:
      return prevState;
  }
};
const linkConfig = makeLinkConfig('RootStackNavigator', {}, navigationConfig);
function AppNavigationContainer({navContext}) {
  return (
    <CustomToastProvider>
      <NavigationContainer
        linking={{config: linkConfig}}
        documentTitle={{
          formatter: (options, route) => {
            return 'Busmate';
          },
        }}
        onReady={() => {
          // console.log('nav ready!');
        }}
        ref={_rootNavigationRef}
        theme={{
          ...DefaultTheme,
          colors: {
            ...DefaultTheme.colors,
            background: 'transparent',
          },
        }}>
        <RootStackNavigator />
      </NavigationContainer>
    </CustomToastProvider>
  );
}
export default AppNavigationContainer;
