import API from '@apis/apis';
import {getW} from '@constants/appUnits';
import {PressNavigate} from '@userInteraction/pressAction';
import React, {useEffect} from 'react';
import {Text, View} from 'react-native';

function HomePage() {
  useEffect(() => {
    async function apiTest() {
      const res = await API.getBusWithRoute({routeNumber: 133});
      console.log('api res???', res);
    }
    apiTest();
  }, []);

  return (
    <View style={{flex: 1, backgroundColor: 'green'}}>
      <PressNavigate
        style={{width: getW(100), height: getW(100), backgroundColor: 'red'}}
        routeName={'BusDetail'}
      />
      <Text>Hello!</Text>
    </View>
  );
}

export default HomePage;
