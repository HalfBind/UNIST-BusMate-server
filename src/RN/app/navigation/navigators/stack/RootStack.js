import React from 'react';
import {THICK_PADDING} from '@constants/appUnits';
import {createStackNavigator} from '@react-navigation/stack';
import {
  TransitionOptions,
  getEmptyHeader,
} from '@components/navigationComponents';
import HomePage from '@routes/normal/HomePage';
import AddAlarmPage from '@routes/normal/AddAlarmPage';
import AlarmListPage from '@routes/normal/AlarmListPage';
import BusDetailPage from '@routes/normal/BusDetailPage';
import RegisterPage from '@routes/modal/RegisterPage';
import HomeSetTime from '@routes/modal/HomeSetTime';
import HomeSetDest from '@routes/modal/HomeSetDest';
// import createStackNavigator from '@navigators/createStackNavigator';
// import {createMyStack} from './MyStackNavigator';

const Stack = createStackNavigator();
function RootStackNavigator({splashOptions = {}}) {
  // console.log('🔥focused route : ', _getFocusedRouteName());

  return (
    <Stack.Navigator
      screenOptions={{
        cardStyle: {
          paddingHorizontal: THICK_PADDING,
          backgroundColor: '#F9F9F9',
          flex: 1,
        },
      }}>
      <Stack.Group screenOptions={{...TransitionOptions.STACK_DEFAULT}}>
        <Stack.Screen
          name="Home"
          component={HomePage}
          options={{...getEmptyHeader()}}
        />
        <Stack.Screen
          name="AddAlarm"
          component={AddAlarmPage}
          options={{...getEmptyHeader()}}
        />
        <Stack.Screen
          name="AlarmList"
          component={AlarmListPage}
          options={{...getEmptyHeader()}}
        />
        <Stack.Screen
          name="BusDetail"
          component={BusDetailPage}
          options={{...getEmptyHeader()}}
        />
      </Stack.Group>

      <Stack.Group screenOptions={{...TransitionOptions.BOTTOM_MODAL}} />
      <Stack.Group screenOptions={{...TransitionOptions.CENTER_MODAL}}>
        <Stack.Screen
          name="Register"
          component={RegisterPage}
          options={{...getEmptyHeader()}}
        />
        <Stack.Screen
          name="HomeSetTime"
          component={HomeSetTime}
          options={{...getEmptyHeader()}}
        />
        <Stack.Screen
          name="HomeSetDest"
          component={HomeSetDest}
          options={{...getEmptyHeader()}}
        />
      </Stack.Group>
    </Stack.Navigator>
  );
}

export default RootStackNavigator;
